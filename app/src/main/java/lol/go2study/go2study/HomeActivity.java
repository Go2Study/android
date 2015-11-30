package lol.go2study.go2study;


import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;


public class HomeActivity extends AppCompatActivity implements Callback
{
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    SharedPreferences pref;
    List<Person> people;



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


        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);
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
