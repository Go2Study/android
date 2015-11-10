package lol.go2study.go2study;

import android.os.AsyncTask;
import android.util.Log;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;

/**
 * Created by cinjo on 11/9/2015.
 */
class RequestTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String ... params) {
        PeopleApi people = new PeopleApi();
        String response;
        try{
            Log.v("Response",people.peopleSearch(params[0], params[1]));
        }
        catch (ApiException e){
            return e.getMessage();
        }
        return "THEEND";

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
        Log.v("Response:", result);
    }
}
