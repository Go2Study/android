package lol.go2study.go2study;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setContentView(R.layout.activity_login);

        //Initialize helper class, which is used for the Fontys oAuth
        settings = new OAuthSettings();

        //Initialize shared preferences, used for storing the access token and other authorizations
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        //Add button event click to redirect to Fontys oAuth webpage
        final Button btnLogin = (Button) findViewById(R.id.btnLoginFontys);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
                startActivity(browserIntent);

                btnLogin.setText("Redirecting to Fontys...");
            }
        });
    }


}
