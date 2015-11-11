package lol.go2study.go2study;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.ScheduleQueryItem;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the access token from the shared settings
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String accessToken = pref.getString("AccessToken", "unauthorized");

        //Initialize empty list for the names of classes, which are obtained through the Fontys API
        List<String> classNames = new ArrayList<>();

        try{
            ScheduleApi schedule = new ScheduleApi();
            List<ScheduleQueryItem> classes = schedule.scheduleAutoComplete(accessToken,"class","");

            // Save the names of classes, provided by Fontys
            for (ScheduleQueryItem c : classes) {
                classNames.add(c.getName());
            }
        }
        catch (ApiException e){
            Log.v("Error",e.getMessage());
        }

        //Populate the spinner
        Spinner s = (Spinner) findViewById(R.id.classesSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, classNames);
        s.setAdapter(adapter);
    }

}
