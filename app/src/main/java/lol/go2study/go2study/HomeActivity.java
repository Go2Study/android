package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.plus.People;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import FontysICT.Models.ScheduleQueryItem;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;

public class HomeActivity extends AppCompatActivity implements Callback
{
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    SharedPreferences pref;


        @Override
        public void onFailure(Request request, IOException e)
        {
            //do something to indicate error
        }

        @Override
        public void onResponse(Response response) throws IOException
        {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();

                    JSONArray object = new JSONArray(responseRaw);
                    Person p;
                    List<Person> people = new ArrayList<>();
                    for (int i=0;i<object.length();i++) {
                        try {
                            p = (Person) ApiInvoker.deserialize(object.getJSONObject(i).toString(), "", Person.class);
                            Log.v("ResponseRaw", p.toString());
                            people.add(p);
                        }
                        catch (ApiException e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (JSONException q)
                {
                    q.printStackTrace();
                }
            }
        }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //API clients
        peopleApi = new PeopleApi();

        setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        //Add button event click to redirect to Fontys oAuth webpage
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (WelcomeActivity.isLoggedIn(accessJSON)) {
                try {
                    peopleApi.peopleList(accessToken, this);

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

/*
super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        peopleApi= new PeopleApi();
        settings = new OAuthSettings();
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        //try {
        // peopleApi.peopleList(settings.getAccessTokenFromSharedPreferences(pref), this);
        // }
        //catch (ApiException e)
        //{
        //    e.printStackTrace();
        // }

        //  String[] people = {"JAN", "FEB", "JOHN", "TODOR","ASHISH","CONSTANTINE"};
        // ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,people);


        String[] people = {"JAN", "FEB", "JOHN", "TODOR","ASHISH","CONSTANTINE"};
        try{
            ListAdapter adapter = new CustomAdapter(this,people);
            //ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,people);
            ListView listView = (ListView)findViewById(R.id.peopleListView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        //On-click item in the Listview
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String item = String.valueOf(parent.getItemAtPosition(position));
                            Toast.makeText(HomeActivity.this, item, Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
        catch (Exception e)
        {

        }
 */

}
