package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.plus.People;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import FontysICT.Models.ScheduleQueryItem;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;

public class CreateUserActivity extends AppCompatActivity {
    SharedPreferences pref;
    OAuthSettings settings;
    private JSONObject userCreated;
    ScheduleApi schedule;
    UsersApi usersApi;
    ArrayList classNames;
    private MyDBHandler dbHandler;


    private Callback classCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();
                    List<ScheduleQueryItem> classes = (List<ScheduleQueryItem>)ApiInvoker.deserialize(responseRaw, "list", ScheduleQueryItem.class);

                    for (ScheduleQueryItem c : classes) {
                        classNames.add(c.getName());
                    }

                    // Populate the class selector spinner
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Spinner s = (Spinner) findViewById(R.id.classesSelector);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateUserActivity.this,
                                    android.R.layout.simple_spinner_item, classNames);
                            s.setAdapter(adapter);
                        }
                    });
                }
                catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Callback userCreateCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();
                    userCreated = new JSONObject(responseRaw);

                    startActivity(new Intent(CreateUserActivity.this, HomeActivity.class));
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usersApi = new UsersApi();

        settings = new OAuthSettings();
        schedule = new ScheduleApi();
        classNames = new ArrayList();

        //Initialize DB helper
        dbHandler = new MyDBHandler(this, "People", null, 1);

        //Get the access token from the shared settings
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        //Extract the accessToken from the JSON shared pref
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        //If there is an access token obtained, proceed with populating the class
        if (!accessToken.equals("")) {
            try {
                schedule.scheduleAutoComplete(accessToken, classCallback, "class", "");
            } catch (ApiException e) {
                Log.v("Error", e.getMessage());
            }
        }

        Button createButton = (Button)findViewById(R.id.btnCreateUser);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Spinner classSpinner = (Spinner)findViewById(R.id.classesSelector);
                String className = classSpinner.getSelectedItem().toString();
                Person p = dbHandler.getPerson();
                Log.v("Person from DB:", p.toString());
                try {
                    UsersApi apiUser = new UsersApi();
                    apiUser.usersPost(userCreateCallback, p.getGivenName(), p.getSurName(), p.getId(), className, p.getMail(), "", "");
                }
                catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
