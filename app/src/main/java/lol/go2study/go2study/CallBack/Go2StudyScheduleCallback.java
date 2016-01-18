package lol.go2study.go2study.CallBack;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Schedule;
import FontysICT.Models.ScheduleItem;
import Go2Study.Models.Event;
import lol.go2study.go2study.Models.ScheduleItemModel;
import lol.go2study.go2study.Models.TeacherScheduleItemModel;

/**
 * Created by Todor on 1/17/2016.
 */
public class Go2StudyScheduleCallback implements Callback {

    public List<Event> gtsSchedule;


    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            String responseRaw = response.body().string();

            try {
                gtsSchedule = (List<Event>) ApiInvoker.deserialize(responseRaw, "list", Event.class);
            } catch (ApiException e) {
                e.printStackTrace();
            }

            try {
                if (gtsSchedule != null && !gtsSchedule.isEmpty()) {
                    ActiveAndroid.beginTransaction();

                    for (Event e : gtsSchedule) {
                        ScheduleItemModel scheduleItemModel = new ScheduleItemModel(e);
                        scheduleItemModel.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                ActiveAndroid.endTransaction();
            }
        }
    }
}
