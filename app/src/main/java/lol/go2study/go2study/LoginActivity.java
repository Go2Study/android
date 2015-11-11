package lol.go2study.go2study;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.Person;


public  class LoginActivity extends Activity {

    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        //Add button event click to redirect to Fontys oAuth webpage
        final Button login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                login.setText("Redirecting to Fontys...");
            }
        });


        Uri uri = getIntent().getData();
        if (uri != null) {
            //Save and extract the access token from the URL
            settings.SaveAccessToken(uri);

            //Make a test call to make sure that the access token works
            if (!isValidToken(settings.getAccess_Token())) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                login.setText("Redirecting to Fontys...");
            }

            addAccessTokenToConfig(settings.getAccess_Token(), pref);
        }

    }
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

}