package lol.go2study.go2study;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;


public  class LoginActivity extends Activity
{

    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        //Add button event click to redirect to Fontys oAuth webpage
        final Button login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                login.setText("Redirecting to Fontys...");
            }
        });

        try
        {
            Uri uri = getIntent().getData();
            if (uri != null){
                //Save and extract the access token from the URL
                settings.SaveAccessToken(uri);

                //Save the access token in the shared preferences
                Editor editor = pref.edit();
                editor.putString("AccessToken", settings.getAccess_Token());// Store Access Token
                editor.commit();
            } else {
                //TODO Show the login screen
            }

            try{
                PeopleApi peopleApi = new PeopleApi();
                peopleApi.peopleSearch(pref.getString("AccessToken", "020f29750b369d70c4fd8318d8ce3789"),"maas");
            }
            catch (ApiException e){
                Log.v("ApiException",e.getMessage());
            }
        }
        catch (NullPointerException e)
        {
            Log.v("mess :", e.getMessage());
        }
    }

}