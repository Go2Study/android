package lol.go2study.go2study;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.google.android.gms.plus.People;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import FontysICT.Models.ScheduleQueryItem;

public class HomeActivity extends AppCompatActivity implements Callback {



    @Override
    public void onFailure(Request request, IOException e) {
        //do something to indicate error
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            // If it's response from Fontys
            String responseRaw = response.body().string();
            try {
                JSONObject responseJSON = new JSONObject(responseRaw);

                if (responseJSON.getString("initials") != null) {
                    List<Person> persons = (List<Person>)ApiInvoker.deserialize(responseRaw, "list", Person.class);
                    Log.v("PERSONSS:::::::", persons.toString());
                } else {

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String[] people = {"JAN", "FEB", "JOHN", "TODOR","ASHISH","CONSTANTINE"};
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,people);


    }



}
