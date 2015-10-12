package lol.go2study.go2study;

import android.net.Uri;

import java.net.URI;

/**
 * Created by Ashish on 12/10/15.
 */
public class OAuthSettings
{
    private  String clientID ;
    private  String requestedScopes ;
    private  String redirectUrl;

    public OAuthSettings()
    {
        clientID = "i271628-go2study-implicit";
        requestedScopes = "fhict";
        redirectUrl = "go2study://oauth/authorize";

    }

    public Uri getRequestUri()
    {
        String uri = "https://tas.fhict.nl/identity/connect/authorize?client_id="+clientID+ "&scope="+ requestedScopes + "&response_type=token&redirect_uri="+ redirectUrl;
        return Uri.parse(uri);
    }



}
