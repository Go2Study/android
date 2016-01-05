package lol.go2study.go2study.CallBack;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import FontysICT.Models.Person;
import Go2Study.Models.User;
import lol.go2study.go2study.Models.PersonModel;
import lol.go2study.go2study.Models.UserModel;

/**
 * Created by Todor on 1/4/2016.
 */
public class UsersCallbacks  {


    public List<User> userList;
   public class GetUsersCallBack implements Callback
    {

        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                // If it's response from Fontys
                String responseRaw = response.body().string();
                try {
                    userList = (List<User>) Go2Study.Invoker.ApiInvoker.deserialize(responseRaw, "list", User.class);

                    ActiveAndroid.beginTransaction();
                    try {

                        for (User user: userList) {

                            UserModel userModel = new UserModel(user);

                            userModel.save();

                        }
                        ActiveAndroid.setTransactionSuccessful();
                    }
                    finally {
                        ActiveAndroid.endTransaction();
                    }

                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
