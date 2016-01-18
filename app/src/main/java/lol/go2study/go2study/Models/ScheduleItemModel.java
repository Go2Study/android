package lol.go2study.go2study.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import FontysICT.Models.Person;
import FontysICT.Models.ScheduleItem;
import Go2Study.Models.Event;

/**
 * Created by Todor on 1/17/2016.
 */
@Table(name = "ScheduleItemModel")
public class ScheduleItemModel extends Model {

    // If name is omitted, then the field name is used.
    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "scheduleID")
    public String ID;

    @Column(name = "startTime")
    public String startTime;

    @Column(name = "endTime")
    public String endTime;

    @Column(name = "location")
    public String location;



    public ScheduleItemModel() {
        super();
    }


    public ScheduleItemModel (Event e) {
        super();
        this.title = e.getTitle();
        this.description = e.getDescription();
        this.ID = String.valueOf(e.getId());
        this.startTime = e.getStartTime();
        this.endTime = e.getEndTime();
        this.location =  e.getLocation();
    }

    public static List<Event> getAllScheduleItems() throws UnsupportedEncodingException, DecoderException {
        List<ScheduleItemModel> scheduleItemsFromDB = new Select()
                .from(ScheduleItemModel.class)
                .orderBy("startTime ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<Event> scheduleList = new ArrayList<Event>();
        for (ScheduleItemModel s: scheduleItemsFromDB) {
            Event scheduleItem = new Event();
            scheduleItem.setTitle(s.title);
            scheduleItem.setDescription(s.description);
            scheduleItem.setId(Integer.parseInt(s.ID));
            scheduleItem.setStartTime(s.startTime);
            scheduleItem.setEndTime(s.endTime);
            scheduleItem.setLocation(s.location);

            //Add to list of people
            scheduleList.add(scheduleItem);
        }
        return scheduleList;
    }

    public static void deleteAll()
    {
        new Delete().from(ScheduleItemModel.class).execute();
    }
}
