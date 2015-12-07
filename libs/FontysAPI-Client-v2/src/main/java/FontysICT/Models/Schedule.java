package FontysICT.Models;

import FontysICT.Models.ScheduleItem;
import FontysICT.Models.Period;
import java.util.*;
import FontysICT.Models.Person;
import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * A schedule for a user/class/room/subject
 **/
@ApiModel(description = "A schedule for a user/class/room/subject")
public class Schedule  {
  
  @SerializedName("title")
  private String title = null;
  @SerializedName("data")
  private List<ScheduleItem> data = null;
  @SerializedName("numberOfDays")
  private Integer numberOfDays = null;
  @SerializedName("start")
  private Date start = null;
  @SerializedName("teachers")
  private List<Person> teachers = null;
  @SerializedName("weeks")
  private List<Period> weeks = null;
  @SerializedName("latestUpdate")
  private Date latestUpdate = null;

  
  /**
   * Title
   **/
  @ApiModelProperty(value = "Title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Schedule Items
   **/
  @ApiModelProperty(value = "Schedule Items")
  public List<ScheduleItem> getData() {
    return data;
  }
  public void setData(List<ScheduleItem> data) {
    this.data = data;
  }

  
  /**
   * Number of days requested
   **/
  @ApiModelProperty(value = "Number of days requested")
  public Integer getNumberOfDays() {
    return numberOfDays;
  }
  public void setNumberOfDays(Integer numberOfDays) {
    this.numberOfDays = numberOfDays;
  }

  
  /**
   * Requested startdate of the schedule
   **/
  @ApiModelProperty(value = "Requested startdate of the schedule")
  public Date getStart() {
    return start;
  }
  public void setStart(Date start) {
    this.start = start;
  }

  
  /**
   * All the teachers in the current schedule
   **/
  @ApiModelProperty(value = "All the teachers in the current schedule")
  public List<Person> getTeachers() {
    return teachers;
  }
  public void setTeachers(List<Person> teachers) {
    this.teachers = teachers;
  }

  
  /**
   * All the current schoolweeks (sadly in dutch)
   **/
  @ApiModelProperty(value = "All the current schoolweeks (sadly in dutch)")
  public List<Period> getWeeks() {
    return weeks;
  }
  public void setWeeks(List<Period> weeks) {
    this.weeks = weeks;
  }

  
  /**
   * The higest value of 'updatedAt' of the current Data
   **/
  @ApiModelProperty(value = "The higest value of 'updatedAt' of the current Data")
  public Date getLatestUpdate() {
    return latestUpdate;
  }
  public void setLatestUpdate(Date latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Schedule {\n");
    
    sb.append("  title: ").append(title).append("\n");
    sb.append("  data: ").append(data).append("\n");
    sb.append("  numberOfDays: ").append(numberOfDays).append("\n");
    sb.append("  start: ").append(start).append("\n");
    sb.append("  teachers: ").append(teachers).append("\n");
    sb.append("  weeks: ").append(weeks).append("\n");
    sb.append("  latestUpdate: ").append(latestUpdate).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


