package lol.go2study.go2study;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import Go2Study.Api.UsersApi;
import Go2Study.Models.User;


public class HomeActivity extends AppCompatActivity
{
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    private static final String FIRST_TIME = "first_time";
    //private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;

    private UsersApi userApi;

    //
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    SharedPreferences pref;
    static List<Person> people;
    static List<User> userList;
    static List<Bitmap> staffImages;

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
        for (Person p :personList) {
            byte[] data;
            try {
                data = p.getThumbnailData().getBytes("UTF-8");
                byte[] byteImage = Base64.decode(data, Base64.DEFAULT);
                listOfImages.add(byteImage);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
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
        //Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        //New NavigationDrawerFragment
        mDrawer = (NavigationView) findViewById(R.id.main_navigation_Viewiew);
        mDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mSelectedId = menuItem.getItemId();
                navigate(mSelectedId);
                return true;
            }

        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if (!didUserSeeDrawer()) {
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }
        mSelectedId = savedInstanceState == null ? R.id.navigation_itemHOME : savedInstanceState.getInt(SELECTED_ITEM_ID);
        navigate(mSelectedId);

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
                    userApi.usersGet(getUsersAppCallBack,"");

                } catch (ApiException e) {
                    e.printStackTrace();
                } catch (Go2Study.Invoker.ApiException j) {
                    j.printStackTrace();
                }
            }
        }

    }


    //UI LOGIC
    //
    private boolean didUserSeeDrawer() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void markDrawerSeen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = true;
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }

    private void showDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void navigate(int mSelectedId) {
        Intent intent = null;

        if (mSelectedId == R.id.navigation_itemPEOPLE) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, PeopleActivity.class);
            startActivity(intent);
        }
        if (mSelectedId == R.id.navigation_itemCALENDAR) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        }
        if (mSelectedId == R.id.navigation_itemMESSAGING) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, MessagingActivity.class);
            startActivity(intent);
        }
        if (mSelectedId == R.id.navigation_itemSETTINGS) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            intent = new Intent(this, MessagingActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {        /////////////////////////////////////////////////////////////////////
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
