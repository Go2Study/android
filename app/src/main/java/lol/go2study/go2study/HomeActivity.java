package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
    ViewPager pager;
    List<Person> people;
    TabLayout tabLayout;


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
                    Person p;
                    //List<Person> people = new ArrayList<>();
                    people = (List<Person>) ApiInvoker.deserialize(responseRaw, "list", Person.class);
                    Log.v("PEOPLE", people.toString());
                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                }


            }
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        pager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        // Fragment manager to add fragment in viewpager we will pass object of Fragment manager to adpater class.
        FragmentManager manager=getSupportFragmentManager();

        //object of PagerAdapter passing fragment manager object as a parameter of constructor of PagerAdapter class.
        PagerAdapter adapter=new PagerAdapter(manager);

        //set Adapter to view pager
        pager.setAdapter(adapter);

        //set tablayout with viewpager
        tabLayout.setupWithViewPager(pager);

        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting tabs from adpater
        tabLayout.setTabsFromPagerAdapter(adapter);

        //API clients
        peopleApi = new PeopleApi();

        //setContentView(R.layout.activity_welcome);

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

}
