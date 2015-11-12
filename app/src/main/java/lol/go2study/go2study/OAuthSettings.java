package lol.go2study.go2study;

import android.content.SharedPreferences;
import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

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
        requestedScopes = "fhict+fhict_personal+fhict_location";
        callbackURL = "go2study://oauth/authorize";
        oauthURL = "https://tas.fhict.nl/identity/connect/authorize?client_id="+clientID+ "&scope="+ requestedScopes + "&response_type=token&redirect_uri="+ callbackURL;
        baseUrl = "https://tas.fhict.nl:443/api/v1/";
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

    public String extractAccessToken(Uri uri)
    {
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
            return dictionary.get("access_token");
        }

        //Default
        return "";
    }

    public String getAccessTokenFromSharedPreferences(SharedPreferences pref) {
        if (!pref.getString("AccessToken","").equals("")) {
            try {
                JSONObject accessJSON = new JSONObject(pref.getString("AccessToken", "{}"));
                String accessToken = accessJSON.getString("accessToken");
                return accessToken;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public JSONObject getAccessTokenJSONFromSharedPreferences(SharedPreferences pref){
        if (!pref.getString("AccessToken","").equals("")) {
            try {
                JSONObject accessJSON = new JSONObject(pref.getString("AccessToken", "{}"));
                return accessJSON;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
