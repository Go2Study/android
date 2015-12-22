package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import FontysICT.Api.PeopleApi;
import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Calendar;
import FontysICT.Models.Schedule;

public class ProfileActivity extends AppCompatActivity {

    private String displayName;
    private String mobileNumber;
    private String office;
    private String mail;
    private String initial;
    private String personalTitle;
    private OAuthSettings settings;
    private PeopleApi peopleApi; // Fontys
    private Bitmap photo;
    private MLRoundedImageView roundedImageView;
    private ScheduleApi scheduleApi;
    SharedPreferences pref;
    private JSONObject person;
    private Schedule teacherSchedule;

    public Callback scheduleCallBack = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
                if (response.isSuccessful())
                {
                    Log.v("DATATA", response.body().toString());
                    String responseRaw = response.body().string();
                    try{
                         Log.v("YES YES", (((Schedule) ApiInvoker.deserialize(responseRaw, "", Schedule.class))).toString());
                        teacherSchedule = (Schedule) ApiInvoker.deserialize(responseRaw, "", Schedule.class);

                    }catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        roundedImageView = new MLRoundedImageView(getBaseContext());
        peopleApi = new PeopleApi();
        settings = new OAuthSettings();
        InitializeProfileInformation();
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);
        scheduleApi = new ScheduleApi();

        //Check if there is existing access Token saved already. If there is - redirect to User Account Creation / Home
        JSONObject accessJSON = settings.getAccessTokenJSONFromSharedPreferences(pref);
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        //GET USERS


        if (accessJSON.length() != 0 && accessToken != null && !accessToken.equals("")) {
            if (isLoggedIn(accessJSON)) {
                try {
                    if (isValidAccessToken(accessToken)) {
                        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                       // String currentDateandTime = sdf.format(new Date());
                        scheduleApi.scheduleForQuery(accessToken,scheduleCallBack,"Teacher",personalTitle,31,true,null,false,false);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }



        //Teacher or student image rounded
        final Bitmap roundedImage = roundedImageView.getCroppedBitmap(photo,90);


        TextView nameTextView = (TextView) findViewById(R.id.lbName);
        TextView mailTextView = (TextView) findViewById(R.id.lbEmail);
        TextView phoneTextView = (TextView) findViewById(R.id.lbPhone);
        TextView roomTextView = (TextView) findViewById(R.id.lbRoom);
        ImageView imageView = (ImageView)findViewById(R.id.profileImage);
        ImageView callIcon = (ImageView) findViewById(R.id.imageView3);
        ImageView mailIcon = (ImageView)findViewById(R.id.imageViewMail);

        mailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ProfileActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
            mailTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail});
                    i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                    i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ProfileActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            phoneTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    if(mobileNumber == "")
                    {
                        Toast.makeText(getBaseContext(),"No number to call",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mobileNumber, null));
                        startActivity(intent);
                        // callIntent.setData(Uri.parse(phNum));
                        Toast.makeText(getBaseContext(), mobileNumber, Toast.LENGTH_SHORT).show();
                    }

                }
            });

            callIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    if (mobileNumber == null) {
                        Toast.makeText(getBaseContext(), "No number to call", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mobileNumber, null));
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), mobileNumber, Toast.LENGTH_SHORT).show();
                    }
                }


            });

            nameTextView.setText(displayName);
            phoneTextView.setText(mobileNumber);
            mailTextView.setText(mail);
            roomTextView.setText(office);
            imageView.setImageBitmap(roundedImage);

            FloatingActionButton btnShedule = (FloatingActionButton)findViewById(R.id.fabShedule);
            btnShedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();

                    i.putExtra("schedule",  teacherSchedule);
                    i.setClass(ProfileActivity.this, CalendarActivity.class);
                    startActivity(i);

                }
            });
        }

    private void InitializeProfileInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString("displayname") != null) ;{
                displayName = extras.getString("displayname");
            }
            if (extras.getString("mobilenumber") != null) ;{
                mobileNumber = extras.getString("mobilenumber");
            }
            if (extras.getString("office") != null) ;{
                office = extras.getString("office");

            }
            if (extras.getString("mail") != null) ;{
                mail = extras.getString("mail");

            }
            if (extras.getString("initial") != null) ;{
                initial = extras.getString("initial");

            }
            if(extras.getString("personalTitle" ) != null)
            {
                personalTitle =  extras.getString("personalTitle");
            }
            if(extras.getParcelable("photo") != null) {
                photo = extras.getParcelable("photo");
            }

        }
    }




    Callback PeopleMe = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {

                    JSONObject responseJSON = new JSONObject(responseRaw);
                    if (responseJSON.getString("initials") != null) {
                        person = responseJSON;


                    }

                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };



    // HELPER METHODS
    private void addAccessTokenToConfig(String accessToken){
        //Expiry timestamp is 2 hours from time now
        String expiryTimestamp = String.valueOf((System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)) / 1000l);

        //Create JSON containing the access token and its expiry timestamp
        JSONObject access = new JSONObject();
        try {
            access.put("accessToken", accessToken);
            access.put("expiration", expiryTimestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Save the access token in the shared preferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("AccessToken", String.valueOf(access));// Store Access Token
        editor.apply();
    }

    public static boolean isLoggedIn(JSONObject accessJSON) {

        if (accessJSON.length() != 0){
            try{
                long timestampNow = System.currentTimeMillis()/1000l;
                long timestampExpiry = Long.valueOf(accessJSON.get("expiration").toString());

                //If the session is expired
                if (timestampNow >= timestampExpiry){
                    return false;
                }
                //The user is logged in and the session has not expired
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    private boolean isValidAccessToken(String accessToken) throws InterruptedException {

        try {
            // Find the data of the user, who holds this token
            peopleApi.peopleMe(accessToken, PeopleMe);

        }
        catch (ApiException e){
            e.printStackTrace();
        }

        long timestampNow = System.currentTimeMillis();

        while (person == null){
            if (System.currentTimeMillis() - timestampNow >= 3000l){
                return false;
            }
        }

        return true;
    }




}


