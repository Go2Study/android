package lol.go2study.go2study.CallBack;

import com.activeandroid.ActiveAndroid;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Models.Schedule;
import FontysICT.Models.ScheduleItem;
import lol.go2study.go2study.Models.TeacherScheduleItemModel;

/**
 * Created by Todor on 1/17/2016.
 */
public class TeacherScheduleCallback implements Callback {

    public Schedule teacherSchedule;

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response.isSuccessful())
        {
            String responseRaw = response.body().string();

            // Fontys schedule request, works differently than Go2Study, because it has different data
            try {
                try {
                    teacherSchedule = (Schedule) ApiInvoker.deserialize(responseRaw, "", Schedule.class);
                } catch (ApiException e) {
                    e.printStackTrace();
                }

                if (teacherSchedule != null && !teacherSchedule.getData().isEmpty()) {
                    ActiveAndroid.beginTransaction();

                    for (ScheduleItem s : teacherSchedule.getData()) {
                        TeacherScheduleItemModel scheduleItemModel = new TeacherScheduleItemModel(s);
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
