package lol.go2study.go2study;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Api.ScheduleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import FontysICT.Models.ScheduleQueryItem;
import Go2Study.Api.UsersApi;

public class CreateUserActivity extends AppCompatActivity {
    SharedPreferences pref;
    OAuthSettings settings;
    private JSONObject userCreatedResponseStatus;
    ScheduleApi schedule;
    UsersApi usersApi;
    ArrayList classNames;
    private MyDBHandler dbHandler;

    //IMAGE
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 1888;


    private static Bitmap Image = null;
    private static Bitmap rotateImage = null;





    private Callback classCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {

            if (response.isSuccessful()) {
                try {
                    String responseRaw = response.body().string();

                    List<ScheduleQueryItem> classes = (List<ScheduleQueryItem>)ApiInvoker.deserialize(responseRaw, "list", ScheduleQueryItem.class);

                    for (ScheduleQueryItem c : classes) {
                        classNames.add(c.getName());
                    }

                    // Populate the class selector spinner
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Spinner s = (Spinner) findViewById(R.id.classesSelector);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateUserActivity.this,
                                    android.R.layout.simple_spinner_item, classNames);
                            s.setAdapter(adapter);
                        }
                    });
                }
                catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Callback userCreateCallback = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    String responseBody = response.body().string();

                    userCreatedResponseStatus = new JSONObject(responseBody);

                    if (userCreatedResponseStatus.getString("firstName") != null){
                        startActivity(new Intent(CreateUserActivity.this, HomeActivity.class));
                    } else {

                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            } else {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        usersApi = new UsersApi();

        settings = new OAuthSettings();
        schedule = new ScheduleApi();
        classNames = new ArrayList();

        //Initialize DB helper
        dbHandler = new MyDBHandler(this, "People", null, 1);

        //Get the access token from the shared settings
        pref = this.getSharedPreferences("TokenPref", MODE_PRIVATE);

        //Extract the accessToken from the JSON shared pref
        String accessToken = settings.getAccessTokenFromSharedPreferences(pref);

        //If there is an access token obtained, proceed with populating the class
        if (!accessToken.equals("")) {
            try {
                schedule.scheduleAutoComplete(accessToken, classCallback, "class", "");
            } catch (ApiException e) {

            }
        }

        Button createButton = (Button)findViewById(R.id.btnCreateUser);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Spinner classSpinner = (Spinner)findViewById(R.id.classesSelector);
                String className = classSpinner.getSelectedItem().toString();
                Person p = dbHandler.getPerson();
                //Log.v("Person from DB:", p.toString());
                try {
                   // UsersApi apiUser = new UsersApi();
                   // Log.v("PRE","BEFORE REQUEST");
                    usersApi.usersPost(userCreateCallback, p.getGivenName(), p.getSurName(), p.getId(), p.getMail(), className,  "", "");
                    //Log.v("Create users call sent","@@@@@@@@@@@@@@@@@@@@@@");
                }
                catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }


            }
        });

        imageView = (ImageView) findViewById(R.id.photoImageView);
        Button browsebtn = (Button)findViewById(R.id.browsebtn);
        browsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, RESULT_LOAD_IMAGE);
            //}
            public void onClick(View v) {


                imageView.setImageBitmap(null);


                if (Image != null) Image.recycle();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });

        Button selfieBtn = (Button) findViewById(R.id.selfiebtn);
        selfieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });



    }

    //IMAGE FROM GALLERY AND CAMERA
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode != 0) {


            Uri mImageUri = data.getData();
            try {
                Image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);


                if (getOrientation(getApplicationContext(), mImageUri) != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(getOrientation(getApplicationContext(), mImageUri));

                    if (rotateImage != null)
                        rotateImage.recycle();
                    rotateImage = Bitmap.createBitmap(Image, 0, 0, Image.getWidth(), Image.getHeight(), matrix, true);
                    imageView.setImageBitmap(rotateImage);
                } else
                    imageView.setImageBitmap(Image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView = (ImageView) findViewById(R.id.photoImageView);
            imageView.setImageBitmap(photo);
        }


    }


    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },null, null, null);
        if (cursor.getCount() != 1) {
            return -1;
        }
        cursor.moveToFirst();
        return cursor.getInt(0);
    }








}
