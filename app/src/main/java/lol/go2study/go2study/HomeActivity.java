package lol.go2study.go2study;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Group;
import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{


    private UsersApi userApi;
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    SharedPreferences pref;
    static List<Person> people;
    static List<User> userList;
    static List<Bitmap> staffImages;
    MyDBHandler dbHandler;

    public Callback getPeopleStaff = new Callback() {

        @Override
        public void onFailure(Request request, IOException e) {
            //do something to indicate error
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();
                    Person p;
                    //List<Person> people = new ArrayList<>();
                    people = (List<Person>) ApiInvoker.deserialize(responseRaw, "list", Person.class);

                    Collections.sort(people, new Comparator<Person>() {
                        @Override
                        public int compare(Person lhs, Person rhs) {
                            return lhs.getDisplayName().compareToIgnoreCase(rhs.getDisplayName());
                        }

                    });

                    BitMapImages(people);

                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                }
            }
        }
    };



    private void BitMapImages(List<Person> personList)
    {
        List<byte[]> listOfImages = new ArrayList<>();
        Log.v("People list size",String.valueOf(personList.size()));
        for (Person p :personList) {
            byte[] data = null;
            try {
                if(p.getThumbnailData() != "" && p.getThumbnailData() != null && !p.getThumbnailData().equals("") ) {
                    data = p.getThumbnailData().getBytes("UTF-8");

                    byte[] byteImage = Base64.decode(data, Base64.DEFAULT);
                    listOfImages.add(byteImage);
                }
            } catch (UnsupportedEncodingException e) {
                Log.v("data",data.toString());
                e.printStackTrace();
            }
        }
        Log.v("List of IMG Length",String.valueOf(listOfImages.size()));
        for (byte[] b:listOfImages) {
            Bitmap bitmap   = BitmapFactory.decodeByteArray(b, 0, b.length);
            staffImages.add(bitmap);
        }
    }

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
        //setContentView(R.layout.activity_welcome);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);
        userList = new ArrayList<>();
        staffImages = new ArrayList<>();
        //Add button event click to redirect to Fontys oAuth webpage
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (WelcomeActivity.isLoggedIn(accessJSON)) {
                try {
                    peopleApi.peopleList(accessToken, getPeopleStaff, true);
                    userApi.usersGet(getUsersAppCallBack, "");

                } catch (ApiException e) {
                    e.printStackTrace();
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
    getMenuInflater().inflate(R.menu.activity_test_drawer, menu);
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
            intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
