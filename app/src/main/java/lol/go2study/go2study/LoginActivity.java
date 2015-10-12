package lol.go2study.go2study;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URL;


public  class LoginActivity extends Activity {


    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //SET WHICH LAYOUT VIEW TO DISPLAY
        setContentView(R.layout.activity_login);

        settings = new OAuthSettings();

        browserIntent  = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());


        final Button login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            int counter = 0;

            public void onClick(View v) {
                counter++;
                login.setText("hi");
                startActivity(browserIntent);

                //setContentView(R.layout.activity_people);
                Log.v("ACCESS TOKEN::::  ", "dadad");
                System.out.print("Todor Sksks");
                //login.setText(counter++);
                // Do something in response to button click
            }
        });


        //
    }


}