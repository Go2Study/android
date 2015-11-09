package lol.go2study.go2study;

import android.net.Uri;
import android.util.Log;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Ashish on 12/10/15.
 */
public class OAuthSettings
{
    private  String clientID ;
    private  String requestedScopes ;
    private  String callbackURL;
    private  String oauthURL;
    private  String accessToken;
    private  String baseUrl;
    private  String peopleEndPoint;

    public OAuthSettings()
    {
        clientID = "i271628-go2study-implicit";
        requestedScopes = "fhict";
        callbackURL = "go2study://oauth/authorize";
        oauthURL = "https://tas.fhict.nl/identity/connect/authorize?client_id="+clientID+ "&scope="+ requestedScopes + "&response_type=token&redirect_uri="+ callbackURL;
        baseUrl = "https://tas.fhict.nl:443/api/v1/";
        peopleEndPoint = "people";
    }

    public String getPeopleEndPoint()
    {
        return peopleEndPoint;
    }

    public Uri getRequestUri()
    {
        return Uri.parse(oauthURL);
    }

    public String getsClientID()
    {
        return  clientID;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public String getAccess_Token()
    {
        return accessToken;

    }
    private void setAccessToken(String token)
    {
        accessToken = token;
    }

    public void SaveAccessToken(Uri uri)
    {
       // accessToken = token;
        if (uri != null)
        {

            String[] temp = String.valueOf(uri).split("#");
            String[] urlComponents = temp[1].split("&");
            Dictionary<String, String> dictionary = new Hashtable<>();
            for(String s: urlComponents)
            {
                String[] pairComponents = s.split("=");
                String key = pairComponents[0];
                String value = pairComponents[1];
                dictionary.put(key,value);
            }
            setAccessToken(dictionary.get("access_token"));
            Log.v("Access_Token:::::", getAccess_Token());



        }
    }


}
