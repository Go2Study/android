package lol.go2study.go2study.CallBack;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
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
import FontysICT.Models.Schedule;
import FontysICT.Models.ScheduleItem;
import Go2Study.Models.Event;
import lol.go2study.go2study.Models.PersonModel;
import lol.go2study.go2study.Models.ScheduleItemModel;
import lol.go2study.go2study.Models.TeacherScheduleItemModel;

/**
 * Created by Todor on 1/17/2016.
 */
public class FontysScheduleCallback implements Callback {

    public Schedule fontysSchedule;
    public List<Event> gtsSchedule;


    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful())
        {
            String responseRaw = response.body().string();

            try {
                try {
                    fontysSchedule = (Schedule) ApiInvoker.deserialize(responseRaw, "", Schedule.class);
                } catch (ApiException e) {
                    e.printStackTrace();
                }

                if (fontysSchedule != null && !fontysSchedule.getData().isEmpty()) {
                    ActiveAndroid.beginTransaction();

                    for (ScheduleItem s : fontysSchedule.getData()) {
                        Event scheduleItem = new Event();
                        scheduleItem.setTitle(s.getSubject());
                        scheduleItem.setDescription(s.getDescription());
                        scheduleItem.setId(s.getHoursMask());
                        scheduleItem.setStartTime(s.getStart());
                        scheduleItem.setEndTime(s.getEnd());
                        scheduleItem.setLocation(s.getRoom());

                        ScheduleItemModel scheduleItemModel = new ScheduleItemModel(scheduleItem);
                        //personModels.add(personModel);
                        scheduleItemModel.save();
                    }
                    ActiveAndroid.setTransactionSuccessful();
                }
            } finally {
                ActiveAndroid.endTransaction();
            }
        }
    }
}
