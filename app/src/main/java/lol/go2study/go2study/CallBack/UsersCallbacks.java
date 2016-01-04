package lol.go2study.go2study.CallBack;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import Go2Study.Models.User;

/**
 * Created by Todor on 1/4/2016.
 */
public class UsersCallbacks  {



   public class GetUsersCallBack implements Callback
    {
        public List<User> userList;
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

                } catch (Go2Study.Invoker.ApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
