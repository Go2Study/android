package lol.go2study.go2study.CallBack;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import Go2Study.Models.Group;
import lol.go2study.go2study.Models.GroupModel;

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
                    ActiveAndroid.beginTransaction();
                    try {

                        for (Group group: groupsList) {

                            GroupModel groupsModel = new GroupModel(group);
                            groupsModel.save();

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
