package lol.go2study.go2study;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Models.Person;
import Go2Study.Api.GroupsApi;
import Go2Study.Api.UsersApi;
import Go2Study.Models.Group;
import Go2Study.Models.User;
import lol.go2study.go2study.androidchat.ChatMainActivity;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{


    private UsersApi userApi;
    private GroupsApi groupsApi;
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    SharedPreferences pref;
    public static List<Person> people;
    public static List<User> userList;
    public static List<Group> groupsList;


    public Callback getAllGroups  = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            Log.v("Failiar","fail");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            Log.v("some", response.body().toString());
            if(response.isSuccessful()){
                Log.v("some","syccce");
                String responseRaw = response.body().string();
                try {
                    groupsList = (List<Group>) Go2Study.Invoker.ApiInvoker.deserialize(responseRaw,"list",Group.class);

                    Log.v("groups", groupsList.toString());
                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }

            }
            else
            {
                Log.v("else","sle");
            }

        }
    };






    public Callback getUsersAppCallBack = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {



        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {
                    userList = (List<User>) Go2Study.Invoker.ApiInvoker.deserialize(responseRaw, "list", User.class);

                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //API clients
        peopleApi = new PeopleApi();
        userApi = new UsersApi();
        groupsList = new ArrayList<>();
        groupsApi = new GroupsApi();
        //setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);
        userList = new ArrayList<>();
        //Add button event click to redirect to Fontys oAuth webpage
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (WelcomeActivity.isLoggedIn(accessJSON)) {
                try {
                    userApi.usersGet(getUsersAppCallBack, "");
                    groupsApi.groupsGet(getAllGroups,"");

                } catch (Go2Study.Invoker.ApiException j) {
                    j.printStackTrace();
                }
            }
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_drawer, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.navigation_itemHOME) {
            // Handle the camera action
        } else if (id == R.id.navigation_itemPEOPLE) {
            intent = new Intent(this, PeopleActivity.class);
            startActivity(intent);

        } else if (id == R.id.navigation_itemCALENDAR) {
           // intent = new Intent(this, CalendarActivity.class);
            //startActivity(intent);

        } else if (id == R.id.navigation_itemMESSAGING) {
            intent = new Intent(this, ChatMainActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
