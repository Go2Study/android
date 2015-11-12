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
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        //Add button event click to redirect to Fontys oAuth webpage
        final Button btnLogin = (Button) findViewById(R.id.btnLoginFontys);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                btnLogin.setText("Redirecting to Fontys...");
            }
        });

        //Check if there is existing access Token saved already. If there is - redirect to User Account Creation / Home
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")){
            if (isLoggedIn(accessJSON) && isValidToken(accessToken)){
                for (int i=0;i<=10;i++){
                    Log.v("Logged", "TRUE");
                }

                if (isExistingUser(getPCN())){
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                }
                else {
                    startActivity(new Intent(WelcomeActivity.this, CreateUserActivity.class));
                }
            }
        } else {
            Log.v("Not logged", "TRUE");
        }

    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (this.getIntent()!=null && this.getIntent().getData()!=null) {
            Uri uri = getIntent().getData();

            final Button btnLogin = (Button) findViewById(R.id.btnLoginFontys);
            btnLogin.setText("Logging in...");

            //Save and extract the access token from the URL
            addAccessTokenToConfig(settings.extractAccessToken(uri));

            //Make a test call to make sure that the access token works
            if (!isValidToken(settings.getAccessTokenFromSharedPreferences(pref))) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                btnLogin.setText("Redirecting to Fontys...");
            }
        }

        //Check if there is existing access Token saved already. If there is - redirect to User Account Creation / Home
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        if (isLoggedIn(accessJSON) && isValidToken(accessToken)){
            Log.v("Logged", "TRUE");

            if (isExistingUser(getPCN())){
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
            if (p == null)
                return false;
        }
        catch (ApiException e){
            e.printStackTrace();
        }
        return true;
    }
    private void addAccessTokenToConfig(String accessToken){
        //Expiry timestamp is 2 hours from time now
        String expiryTimestamp = String.valueOf((System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)) / 1000l);

        //Create JSON containing the access token and its expiry timestamp
        JSONObject access = new JSONObject();
        try {
            access.put("accessToken", accessToken);
            access.put("expiration", expiryTimestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Save the access token in the shared preferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("AccessToken", String.valueOf(access));// Store Access Token
        editor.apply();
    }

    private boolean isLoggedIn(JSONObject accessJSON){

        if (accessJSON.length() != 0){
            try{
                long timestampNow = System.currentTimeMillis()/1000l;
                long timestampExpiry = Long.valueOf(accessJSON.get("expiration").toString());

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
    private boolean isValidAccessToken(String accessToken){
        PeopleApi peopleApi = new PeopleApi();

        try {
            // Find the data of the user, who holds this token
            Person p = peopleApi.peopleMe(accessToken);

            if (p != null){
                return true;
            }
            else
                return false;
        }
        catch (ApiException e){
            e.printStackTrace();
        }
        return false;
    }
    private boolean isExistingUser(String pcn){
        UsersApi usersApi = new UsersApi();
        String personPcn;

        try {
            // Find the user by pcn from the existing users
            User u = usersApi.usersPcnGet(pcn);
            if (u != null){
                return true;
            }
            else
                return false;
        }
        catch (Go2Study.Invoker.ApiException e){
            e.printStackTrace();
        }

        return false;
    }

    private String getPCN(){
        PeopleApi peopleApi = new PeopleApi();
        try {
            Person p = peopleApi.peopleMe(settings.getAccessTokenFromSharedPreferences(pref));
            return p.getId();
        }
        catch (ApiException e) {
            e.printStackTrace();
        }
        return "";
    }


}
