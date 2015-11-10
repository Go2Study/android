package FontysICT.Models;

import java.util.*;
import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * An item in a schedule.
 **/
@ApiModel(description = "An item in a schedule.")
public class ScheduleItem  {
  
  @SerializedName("classes")
  private List<String> classes = null;
  @SerializedName("room")
  private String room = null;
  @SerializedName("subject")
  private String subject = null;
  @SerializedName("teacherAbbreviation")
  private String teacherAbbreviation = null;
  @SerializedName("start")
  private Date start = null;
  @SerializedName("end")
  private Date end = null;
  @SerializedName("uid")
  private String uid = null;
  @SerializedName("hoursMask")
  private Integer hoursMask = null;
  @SerializedName("description")
  private String description = null;

  
  /**
   * Classes
   **/
  @ApiModelProperty(value = "Classes")
  public List<String> getClasses() {
    return classes;
  }
  public void setClasses(List<String> classes) {
    this.classes = classes;
  }

  
  /**
   * Room
   **/
  @ApiModelProperty(value = "Room")
  public String getRoom() {
    return room;
  }
  public void setRoom(String room) {
    this.room = room;
  }

  
  /**
   * Subject (vak)
   **/
  @ApiModelProperty(value = "Subject (vak)")
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

  
  /**
   * Abbreviation (afkorting) of teacher
   **/
  @ApiModelProperty(value = "Abbreviation (afkorting) of teacher")
  public String getTeacherAbbreviation() {
    return teacherAbbreviation;
  }
  public void setTeacherAbbreviation(String teacherAbbreviation) {
    this.teacherAbbreviation = teacherAbbreviation;
  }

  
  /**
   * Start of the lesson
   **/
  @ApiModelProperty(value = "Start of the lesson")
  public Date getStart() {
    return start;
  }
  public void setStart(Date start) {
    this.start = start;
  }

  
  /**
   * End of the lesson
   **/
  @ApiModelProperty(value = "End of the lesson")
  public Date getEnd() {
    return end;
  }
  public void setEnd(Date end) {
    this.end = end;
  }

  
  /**
   * Lesson UID (for syncing)
   **/
  @ApiModelProperty(value = "Lesson UID (for syncing)")
  public String getUid() {
    return uid;
  }
  public void setUid(String uid) {
    this.uid = uid;
  }

  
  /**
   * Bitmask of the schoolhours (1st,3th,4th = 00001101 = 13)
   **/
  @ApiModelProperty(value = "Bitmask of the schoolhours (1st,3th,4th = 00001101 = 13)")
  public Integer getHoursMask() {
    return hoursMask;
  }
  public void setHoursMask(Integer hoursMask) {
    this.hoursMask = hoursMask;
  }

  
  /**
   * Description (if profived)
   **/
  @ApiModelProperty(value = "Description (if profived)")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleItem {\n");
    
    sb.append("  classes: ").append(classes).append("\n");
    sb.append("  room: ").append(room).append("\n");
    sb.append("  subject: ").append(subject).append("\n");
    sb.append("  teacherAbbreviation: ").append(teacherAbbreviation).append("\n");
    sb.append("  start: ").append(start).append("\n");
    sb.append("  end: ").append(end).append("\n");
    sb.append("  uid: ").append(uid).append("\n");
    sb.append("  hoursMask: ").append(hoursMask).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


