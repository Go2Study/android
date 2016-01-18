package lol.go2study.go2study.CallBack;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import Go2Study.Models.Group;
import Go2Study.Models.User;
import lol.go2study.go2study.Models.GroupModel;
import lol.go2study.go2study.Models.UserModel;

/**
 * Created by Todor on 1/4/2016.
 */
public class GroupsCallbacks {

    public  class GetGroupsCallback implements Callback
    {
        public  List<Group> groupsList;
        @Override
        public void onFailure(Request request, IOException e) {
            Log.v("Failiar", "fail");
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if(response.isSuccessful()){
                String responseRaw = response.body().string();
                try {
                    groupsList = (List<Group>) Go2Study.Invoker.ApiInvoker.deserialize(responseRaw,"list",Group.class);

                    try {
                        ActiveAndroid.beginTransaction();
                        if(groupsList != null || !groupsList.isEmpty() || groupsList.size() > 0) {
                            new Delete().from(GroupModel.class).execute();
                            for (Group group : groupsList) {

                                GroupModel groupsModel = new GroupModel(group);
                                groupsModel.save();

                            }
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

    public class PostGroupCallBack implements Callback
    {

            @Override
            public void onFailure(Request request, IOException e) {
                //do something to indicate error
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseRaw = response.body().string();
                    Log.v(" The response  is is:::", responseRaw);
                }
            }


    }
}
