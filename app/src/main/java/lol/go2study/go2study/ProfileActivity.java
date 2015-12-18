package lol.go2study.go2study;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import FontysICT.Models.Image;
import FontysICT.Models.Person;

public class ProfileActivity extends AppCompatActivity {

    private String displayName;
    private String mobileNumber;
    private String office;
    private String mail;
    private String initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getString("displayname") != null) ;
            {
                displayName = extras.getString("displayname");

            }
            if (extras.getString("mobilenumber") != null) ;
            {
                mobileNumber = extras.getString("mobilenumber");

            }
            if (extras.getString("office") != null) ;
            {
                office = extras.getString("office");

            }
            if (extras.getString("mail") != null) ;
            {
                mail = extras.getString("mail");

            }
            if (extras.getString("initial") != null) ;
            {
                initial = extras.getString("initial");

            }

        }

        Log.v("YES YES ", initial + mail + displayName);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = (TextView) findViewById(R.id.lbName);
                TextView mailTextView = (TextView) findViewById(R.id.lbEmail);
                TextView phoneTextView = (TextView) findViewById(R.id.lbPhone);

                ImageView callIcon = (ImageView) findViewById(R.id.imageView3);
                callIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        String phNum = "tel:" + mobileNumber;

                       // callIntent.setData(Uri.parse(phNum));
                        Toast.makeText(getBaseContext(),phNum,Toast.LENGTH_SHORT).show();
                        //startActivity(callIntent);
                    }
                });

                nameTextView.setText(displayName);
                mailTextView.setText(mail);
                phoneTextView.setText(mobileNumber);
            }
        });


    }

}
