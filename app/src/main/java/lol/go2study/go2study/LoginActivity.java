package lol.go2study.go2study;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public  class LoginActivity extends Activity
{


    Intent browserIntent;
    private OAuthSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //SET WHICH LAYOUT VIEW TO DISPLAY
        setContentView(R.layout.activity_login);

        settings = new OAuthSettings();


        browserIntent = new Intent(Intent.ACTION_VIEW, settings.getRequestUri());
        final Button login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                RequestTask task = new RequestTask();
                Log.v("TEST", "12355");
                task.execute("", "b9af7841dda256d7e07a214ca48b5b40");
                //login.setText("hi");
                //startActivity(browserIntent);

            }
        });


        try
        {
            Uri uri = getIntent().getData();
            settings.SaveAccessToken(uri);
        }
        catch (NullPointerException e)
        {
            Log.v("mess :", e.getMessage());
        }
    }

}