package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.ScheduleQueryItem;
import Go2Study.Models.User;

public class CreateUserActivity extends AppCompatActivity {
    SharedPreferences pref;
    OAuthSettings settings;
    private String userCreated;
    ScheduleApi schedule;
    List<String> classNames;

    private Callback classCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();
                    List<ScheduleQueryItem> classes = (List<ScheduleQueryItem>) ApiInvoker.deserialize(responseRaw, "list", ScheduleQueryItem.class);

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
                    User user = (User) Go2Study.Invoker.ApiInvoker.deserialize(responseRaw, "", User.class);

                    if (user != null) {
                        startActivity(new Intent(CreateUserActivity.this, HomeActivity.class));
                    }


                } catch (Go2Study.Invoker.ApiException e) {
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
        settings = new OAuthSettings();
        schedule = new ScheduleApi();
        classNames = new ArrayList();

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
    }

}
