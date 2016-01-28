package lol.go2study.go2study;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Api.PictureApi;
import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import Go2Study.Api.GroupsApi;
import Go2Study.Api.UsersApi;
import lol.go2study.go2study.CallBack.FontysScheduleCallback;
import lol.go2study.go2study.CallBack.UsersCallbacks;
import lol.go2study.go2study.Models.ScheduleItemModel;
import lol.go2study.go2study.androidchat.ChatMainActivity;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{


    private UsersApi userApi;
    private static Bitmap bitmap;
    private PeopleApi peopleApi;
    private OAuthSettings settings;
    private GroupsApi groupsApi;
    private SharedPreferences pref;
    private String firstName;
    private String lastName;
    private PictureApi pictureApi;
    private ScheduleApi scheduleApi;
    ImageView imageView;
    private String pcn;
    private MLRoundedImageView roundedImageView;
    private final String SIZE = "Large";

    public static List<Bitmap> staffImages;


    private  Callback pictureCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                byte[] responseRaw = response.body().bytes();

                bitmap = BitmapFactory.decodeByteArray(responseRaw, 0, responseRaw.length);
                Log.v("IMAAGE", bitmap.toString());
             final    Bitmap roundedImage = roundedImageView.getCroppedBitmap(bitmap,90);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(roundedImage);
                    }
                });
            }
        }
    };
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        pictureApi = new PictureApi();

        this.roundedImageView =  new MLRoundedImageView(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        TextView fName = (TextView)view.findViewById(R.id.firstNameTextView);
        TextView sName = (TextView)view.findViewById(R.id.secondNameTextView);
        imageView = (ImageView)view.findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            firstName =  extras.getString("firstName");
            lastName = extras.getString("lastName");
            pcn = extras.getString("pcn");
            className = extras.getString("className");

        }
        Log.v("PCN", pcn + SIZE);

        fName.setText(firstName);
        sName.setText(lastName );

        //API clients
        peopleApi = new PeopleApi();
        userApi = new UsersApi(); // For getting all the latest information about the users
        groupsApi = new GroupsApi(); // For getting groups information
        settings = new OAuthSettings();
        scheduleApi = new ScheduleApi(); // For obtaining the schedule information from Fontys


        UsersCallbacks usersCallbacks = new UsersCallbacks();
        UsersCallbacks.GetUsersCallBack getUsersAppCallBack = usersCallbacks.new GetUsersCallBack();
        FontysScheduleCallback fontysScheduleCallback = new FontysScheduleCallback();

        //Initialize shared preferences, used for storing the access token and other authorizations
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);
        staffImages = new ArrayList<>();

        //Add button event click to redirect to Fontys oAuth webpage
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);
        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (WelcomeActivity.isLoggedIn(accessJSON)) {
                try {

                    userApi.usersGet(getUsersAppCallBack, "");
                    pictureApi.pictureImage(accessToken, pictureCallback, pcn, SIZE);

                    // Clear the schedule db cache and get new one
                    ScheduleItemModel.deleteAll();

                    scheduleApi.scheduleForQuery(accessToken, fontysScheduleCallback,"class","ei14", 31, true, null, false, false);


                } catch (Go2Study.Invoker.ApiException j) {
                    j.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
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

        }
        else if (id == R.id.navigation_itemPEOPLE) {
            intent = new Intent(this, PeopleActivity.class);
            startActivity(intent);

        } else if (id == R.id.navigation_itemCALENDAR) {
           intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);

        } else if (id == R.id.navigation_itemMESSAGING) {
            intent = new Intent(this, ChatMainActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
