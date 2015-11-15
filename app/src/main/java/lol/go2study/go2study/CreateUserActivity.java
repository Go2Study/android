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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.ScheduleQueryItem;

public class CreateUserActivity extends AppCompatActivity implements Callback{
    SharedPreferences pref;
    OAuthSettings settings;
    private String userCreated;
    ScheduleApi schedule;
    List<String> classNames;

    // CALLBACK METHODS
    @Override
    public void onFailure(Request request, IOException e) {
        //do something to indicate error
        userCreated = request.body().toString();
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                JSONArray responseRaw = new JSONArray(response.body().string());
                if (responseRaw.get(0).toString().contains("kind")) {
                    List<ScheduleQueryItem> classes = (List<ScheduleQueryItem>) ApiInvoker.deserialize(responseRaw.toString(), "list", ScheduleQueryItem.class);

                    for (ScheduleQueryItem c : classes) {
                        classNames.add(c.getName());
                    }

                    runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Populate the class selector spinner
                        Spinner s = (Spinner) findViewById(R.id.classesSelector);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateUserActivity.this,
                                android.R.layout.simple_spinner_item, classNames);
                        s.setAdapter(adapter);
                        }
                    });

                } else {
                    userCreated = responseRaw.toString();
                }
            }
            catch (JSONException j) {
                j.printStackTrace();
            }
            catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

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
                schedule.scheduleAutoComplete(accessToken, this, "class", "");
            } catch (ApiException e) {
                Log.v("Error", e.getMessage());
            }
        }
    }

}
