package FontysICT.Models;

import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * An item from the shared calendar
 **/
@ApiModel(description = "An item from the shared calendar")
public class CalendarItem  {
  
  @SerializedName("start")
  private Date start = null;
  @SerializedName("end")
  private Date end = null;
  @SerializedName("title")
  private String title = null;
  @SerializedName("location")
  private String location = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("allDay")
  private Boolean allDay = null;

  
  /**
   * Start of the calendar item
   **/
  @ApiModelProperty(value = "Start of the calendar item")
  public Date getStart() {
    return start;
  }
  public void setStart(Date start) {
    this.start = start;
  }

  
  /**
   * End of the calendar item
   **/
  @ApiModelProperty(value = "End of the calendar item")
  public Date getEnd() {
    return end;
  }
  public void setEnd(Date end) {
    this.end = end;
  }

  
  /**
   * Title of the calendar item
   **/
  @ApiModelProperty(value = "Title of the calendar item")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Location  of the calendar item
   **/
  @ApiModelProperty(value = "Location  of the calendar item")
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  
  /**
   * Optional description
   **/
  @ApiModelProperty(value = "Optional description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * True for all-day event
   **/
  @ApiModelProperty(value = "True for all-day event")
  public Boolean getAllDay() {
    return allDay;
  }
  public void setAllDay(Boolean allDay) {
    this.allDay = allDay;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalendarItem {\n");
    
    sb.append("  start: ").append(start).append("\n");
    sb.append("  end: ").append(end).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  allDay: ").append(allDay).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


