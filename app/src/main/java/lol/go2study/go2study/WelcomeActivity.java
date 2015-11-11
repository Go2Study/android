package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;

public class WelcomeActivity extends AppCompatActivity {

    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        //Add button event click to redirect to Fontys oAuth webpage
        final Button btnLogin = (Button) findViewById(R.id.btnLoginFontys);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                btnLogin.setText("Redirecting to Fontys...");
            }
        });

        // If the activity was started with intent data (redirected back from Fontys)
        Uri uri = getIntent().getData();
        if (uri != null) {
            //Save and extract the access token from the URL
            settings.SaveAccessToken(uri);
            Log.v("TOOKEN", settings.getAccess_Token());

            //Make a test call to make sure that the access token works
            if (!isValidToken(settings.getAccess_Token())) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                btnLogin.setText("Redirecting to Fontys...");
            }

            addAccessTokenToConfig(settings.getAccess_Token(), pref);
        }

        //Check if there is existing access Token saved already. If there is - redirect to User Account Creation / Home
        if (isLoggedIn(pref)){
            if (isExistingUser(settings.getAccess_Token())){
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            }
            else {
                startActivity(new Intent(WelcomeActivity.this, CreateUserActivity.class));
            }
        }
    }

    // HELPER METHODS

    // Checks validity of a token
    private boolean isValidToken(String accessToken){
        try{
            PeopleApi peopleApi = new PeopleApi();
            Person p = peopleApi.peopleMe(accessToken);
            Log.v("Me", p.toString());
            if (p == null)
                return false;
        }
        catch (ApiException e){
            e.printStackTrace();
        }
        return true;
    }
    private void addAccessTokenToConfig(String accessToken, SharedPreferences pref){
        //Time now + 1 hour converted to UNIX timestamp
        String expiryTimestamp = String.valueOf((System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)) / 1000l);

        //Create JSON containing the access token and its expiry timestamp
        JSONObject access = new JSONObject();
        try {
            access.put("accessToken", settings.getAccess_Token());
            access.put("expiration", expiryTimestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Save the access token in the shared preferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("AccessToken", String.valueOf(access));// Store Access Token
        editor.commit();
    }

    private boolean isLoggedIn(SharedPreferences pref){
        String storedToken = pref.getString("accessToken", "0");
        if (storedToken != null){
            try{
                JSONObject accessJson = new JSONObject(storedToken);
                long timestampNow = System.currentTimeMillis()/1000l;
                long timestampExpiry = Long.valueOf(accessJson.get("expiration").toString());

                //If the session is expired
                if (timestampNow >= timestampExpiry){
                    return false;
                }

                //The user is logged in and the session has not expired
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    private boolean isExistingUser(String accessToken){
        PeopleApi peopleApi = new PeopleApi();
        UsersApi usersApi = new UsersApi();
        String personPcn = "";
        try{
            //Try to find the data of the user, who holds this token
            Person p = peopleApi.peopleMe(accessToken);
            if (p != null){
                personPcn = p.getId();
            }
            else
                return false;

            // Try to find the user by pcn from the existing users
            User u = usersApi.usersPcnGet(personPcn);
            if (u != null){
                return true;
            }
            else
                return false;
        }
        catch (ApiException e){
            e.printStackTrace();
        }
        catch (Go2Study.Invoker.ApiException g2se){
            g2se.printStackTrace();
        }

        return false;
    }


}
