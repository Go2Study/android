package lol.go2study.go2study.CallBack;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Person;
import lol.go2study.go2study.Models.PersonModel;
import lol.go2study.go2study.MyDBHandler;

/**
 * Created by Todor on 1/4/2016.
 */
public class PeopleCallback implements Callback {

    public List<Person> people;
    private List<PersonModel> personModels;

    @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                try {
                    people = new ArrayList<>();
                    String responseRaw = response.body().string();
                    people = (List<Person>) ApiInvoker.deserialize(responseRaw, "list", Person.class);
                    personModels =new ArrayList<>();


                    //Cache in SQlite

                    try {

                        if(people != null || !people.isEmpty() || people.size() > 0){
                            ActiveAndroid.beginTransaction();
                            for (Person p: people) {

                                PersonModel personModel = new PersonModel(p);
                                //personModels.add(personModel);
                                personModel.save();

                            }
                            ActiveAndroid.setTransactionSuccessful();
                        }

                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
            }
        }
}