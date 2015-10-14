package lol.go2study.go2study;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;


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
                login.setText("hi");
                startActivity(browserIntent);

            }
        });

        try
        {
            Uri uri = getIntent().getData();
            settings.SaveAccessToken(uri);
          //  makeGetRequest();

        }
        catch (NullPointerException e)
        {
            Log.v("mess :", e.getMessage());
        }
    }

    private void makeGetRequest()
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            String url = settings.getBaseUrl().concat( settings.getPeopleEndPoint());
            HttpGet get = new HttpGet(url);
            //get.setHeader();
            get.addHeader("Authorization", "Bearer " + settings.getAccess_Token());
            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            if (resEntityGet != null)
            {
                // do something with the response
                String response = EntityUtils.toString(resEntityGet);
                Log.i("GET RESPONSE", response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       // Log.d("Response of GET request", response.toString());
    }

}