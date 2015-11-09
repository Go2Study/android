package lol.go2study.go2study;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import FontysICT.Api.PeopleApi;
import FontysICT.Invoker.ApiException;
import FontysICT.Models.Person;

/**
 * Created by cinjo on 11/9/2015.
 */
class RequestTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String ... params) {
        PeopleApi people = new PeopleApi();
        List<Person> response;
        Log.v("Params:"," query: "+params[0]+"access: "+params[1]);
        try{
            response = people.peopleSearch(params[0], params[1]);
        }
        catch (ApiException e){
            return e.getMessage();
        }
        return response.toString();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
        Log.v("Response:", result);
    }
}
