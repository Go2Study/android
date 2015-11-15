package lol.go2study.go2study;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.ScheduleQueryItem;

public class CreateUserActivity extends AppCompatActivity implements Callback{
    SharedPreferences pref;
    OAuthSettings settings;
    private String userCreated;

    // CALLBACK METHODS
    @Override
    public void onFailure(Request request, IOException e) {
        //do something to indicate error
        userCreated = request.body().toString();
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            userCreated = response.body().string();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the access token from the shared settings
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        //Extract the accessToken from the JSON shared pref
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        //If there is an access token obtained, proceed with populating the class
        if (!accessToken.equals("")) {
            //Initialize empty list for the names of classes, which are obtained through the Fontys API
            List<String> classNames = new ArrayList<>();

            try {
                ScheduleApi schedule = new ScheduleApi();
                List<ScheduleQueryItem> classes = schedule.scheduleAutoComplete(accessToken, this, "class", "");

                if (classes != null) {
                    // Save the names of classes, provided by Fontys
                    for (ScheduleQueryItem c : classes) {
                        classNames.add(c.getName());
                    }
                }
            } catch (ApiException e) {
                Log.v("Error", e.getMessage());
            }

            //Populate the spinner
            Spinner s = (Spinner) findViewById(R.id.classesSelector);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, classNames);
            s.setAdapter(adapter);
        }
    }

}
