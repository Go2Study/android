package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import FontysICT.Api.GroupsApi;
import FontysICT.Api.PeopleApi;
import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.Group;
import Go2Study.Api.UsersApi;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Models.User;

public class WelcomeActivity extends AppCompatActivity  {

    Intent browserIntent;
    private OAuthSettings settings;
    SharedPreferences pref;
    private List<User> userList;
    private JSONObject person;
    private GroupsApi groupsApi;
    private PeopleApi peopleApi; // Fontys
    private UsersApi usersApi; // Go2Study db
    private User user;
    private MyDBHandler dbHandler;
    private List<Group> groupsList;
    private ScheduleApi scheduleApi;




    // CALLBACK METHODS
    public Callback getGroupsCallBack = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.v("ERROR FROM groups","");


        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {
                    groupsList = (List<Group>)ApiInvoker.deserialize(responseRaw,"list",Group.class);
                    Log.v("GROUP::::",groupsList.toString());

                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public Callback GetScheduleCallBack = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {
                    user = (User)ApiInvoker.deserialize(responseRaw,"",User.class);
                    Log.v("USERR::::",user.toString());

                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    Callback GetUser = new Callback() {
         @Override
         public void onFailure(Request request, IOException e) {
             //do something to indicate error

         }

         @Override
         public void onResponse(Response response) throws IOException {

             if (response.isSuccessful()) {
                 // If it's response from Fontys
                 String responseRaw = response.body().string();
                 try {
                     user = (User)ApiInvoker.deserialize(responseRaw,"",User.class);
                     Log.v("USERR::::",user.toString());

                 } catch (Go2Study.Invoker.ApiException e) {
                     e.printStackTrace();
                 }
             }

         }
     };


    Callback PeopleMe = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {

                    JSONObject responseJSON = new JSONObject(responseRaw);
                    if (responseJSON.getString("initials") != null) {
                        person = responseJSON;
                        dbHandler.AddPerson(person);
                        Log.v("DEGUGGG:::",dbHandler.getPerson().toString());
                    }

                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //API clients
        peopleApi = new PeopleApi();
        usersApi = new UsersApi();

        //DB Helper
        dbHandler = new MyDBHandler(this, "People", null, 1);
        groupsList = new ArrayList<>();
        scheduleApi = new ScheduleApi();
        groupsApi = new GroupsApi();
       // scheduleApi.scheduleForQuery();
        setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();
        userList = new ArrayList<>();
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

        //GET USERS


        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (isLoggedIn(accessJSON)) {
                try {
                    if (isValidAccessToken(accessToken)) {
                        if (isExistingUser(person.getString("id"))) {
                            //Home
                            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                        } else {
                            //CreateUserActivity
                            startActivity(new Intent(WelcomeActivity.this, CreateUserActivity.class));
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
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

        }

        //Check if there is existing access Token saved already. If there is - redirect to User Account Creation / Home
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (isLoggedIn(accessJSON)) {
                try {
                    if (isValidAccessToken(accessToken)) {
                        if (isExistingUser(person.getString("id")) ) {

                            groupsApi.groupsList(accessToken,getGroupsCallBack);
                            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));

                        } else {
                            startActivity(new Intent(WelcomeActivity.this, CreateUserActivity.class));
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch(JSONException q)
                {
                    q.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    // HELPER METHODS
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
    public static boolean isLoggedIn(JSONObject accessJSON) {

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
    private boolean isValidAccessToken(String accessToken) throws InterruptedException {

        try {
            // Find the data of the user, who holds this token
            peopleApi.peopleMe(accessToken, PeopleMe);

        }
        catch (ApiException e){
            e.printStackTrace();
        }

        long timestampNow = System.currentTimeMillis();

        while (person == null){
            if (System.currentTimeMillis() - timestampNow >= 3000l){
                return false;
            }
        }

        return true;
    }
    private boolean isExistingUser(String pcn) throws InterruptedException {

        // Async HTTP GET user
        boolean flag = false;
        try {
            usersApi.usersPcnGet(GetUser, pcn);
        }
        catch (Go2Study.Invoker.ApiException e){
            e.printStackTrace();
        }

        long timestampNow = System.currentTimeMillis();

        while (user == null){
            if (System.currentTimeMillis() - timestampNow >= 3000l){
                return flag;
            }
        }

        Log.v("STATUS::::::::::::", "EXISTS");
        return true;
    }


}
