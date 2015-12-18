package lol.go2study.go2study;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
    private Bitmap photo;
    private MLRoundedImageView roundedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        roundedImageView = new MLRoundedImageView(getBaseContext());


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
            if(extras.getParcelable("photo") != null)
            {
                photo = extras.getParcelable("photo");
            }

        }
        final Bitmap roundedImage = roundedImageView.getCroppedBitmap(photo,90);
        Log.v("YES YES ", initial + mail + displayName);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView nameTextView = (TextView) findViewById(R.id.lbName);
                TextView mailTextView = (TextView) findViewById(R.id.lbEmail);
                TextView phoneTextView = (TextView) findViewById(R.id.lbPhone);
                TextView roomTextView = (TextView) findViewById(R.id.lbRoom);
                ImageView imageView = (ImageView)findViewById(R.id.profileImageView);
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
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mobileNumber, null));
                        startActivity(intent);
                        // callIntent.setData(Uri.parse(phNum));
                        Toast.makeText(getBaseContext(), mobileNumber, Toast.LENGTH_SHORT).show();
                        //startActivity(callIntent);
                    }
                });

                callIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mobileNumber, null));
                        startActivity(intent);
                        // callIntent.setData(Uri.parse(phNum));
                        Toast.makeText(getBaseContext(), mobileNumber, Toast.LENGTH_SHORT).show();
                        //startActivity(callIntent);
                    }
                });

                nameTextView.setText(displayName);
                phoneTextView.setText(mobileNumber);
                mailTextView.setText(mail);
                roomTextView.setText(office);
                imageView.setImageBitmap(roundedImage);
            }
        });


    }

}
