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

import FontysICT.Models.ScheduleItem;
import Go2Study.Models.Event;

/**
 * Created by Todor on 1/17/2016.
 */
@Table(name = "TeacherScheduleItemModel")
public class TeacherScheduleItemModel extends Model {

    // If name is omitted, then the field name is used.
    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "scheduleID" ,unique = true,onUniqueConflict = Column.ConflictAction.IGNORE, index =  true)
    public String ID;

    @Column(name = "startTime")
    public String startTime;

    @Column(name = "endTime")
    public String endTime;

    @Column(name = "location")
    public String location;

    @Column(name = "teacherAbr")
    public String teacherAbr;



    public TeacherScheduleItemModel() {
        super();
    }


    public TeacherScheduleItemModel(ScheduleItem e) {
        super();
        this.title = e.getSubject();
        this.description = e.getDescription();
        this.ID = e.getUid();
        this.startTime = e.getStart();
        this.endTime = e.getEnd();
        this.location =  e.getRoom();
        this.teacherAbr = e.getTeacherAbbreviation();
    }

    public static List<ScheduleItem> getAllScheduleItems() throws UnsupportedEncodingException, DecoderException {
        List<TeacherScheduleItemModel> scheduleItemsFromDB = new Select()
                .from(TeacherScheduleItemModel.class)
                .orderBy("startTime ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<ScheduleItem> scheduleList = new ArrayList<ScheduleItem>();
        for (TeacherScheduleItemModel s: scheduleItemsFromDB) {
            ScheduleItem scheduleItem = new ScheduleItem();
            scheduleItem.setSubject(s.title);
            scheduleItem.setDescription(s.description);
            scheduleItem.setUid(s.ID);
            scheduleItem.setStart(s.startTime);
            scheduleItem.setEnd(s.endTime);
            scheduleItem.setRoom(s.location);
            scheduleItem.setTeacherAbbreviation(s.teacherAbr);

            //Add to list of people
            scheduleList.add(scheduleItem);
        }
        return scheduleList;
    }

    public static List<ScheduleItem> getScheduleItemsForTeacher(String teacherAbr) throws UnsupportedEncodingException, DecoderException {
        List<TeacherScheduleItemModel> scheduleItemsFromDB = new Select()
                .from(TeacherScheduleItemModel.class)
                .where("teacherAbr = ?", teacherAbr)
                .orderBy("startTime ASC")
                .execute();

        //Create List<Person> using the Fontys Person model and return it
        List<ScheduleItem> scheduleList = new ArrayList<ScheduleItem>();
        for (TeacherScheduleItemModel s: scheduleItemsFromDB) {
            ScheduleItem scheduleItem = new ScheduleItem();
            scheduleItem.setSubject(s.title);
            scheduleItem.setDescription(s.description);
            scheduleItem.setUid(s.ID);
            scheduleItem.setStart(s.startTime);
            scheduleItem.setEnd(s.endTime);
            scheduleItem.setRoom(s.location);
            scheduleItem.setTeacherAbbreviation(s.teacherAbr);

            //Add to list of people
            scheduleList.add(scheduleItem);
        }
        return scheduleList;
    }

    public static void deleteAll()
    {
        new Delete().from(TeacherScheduleItemModel.class).execute();
    }
}
