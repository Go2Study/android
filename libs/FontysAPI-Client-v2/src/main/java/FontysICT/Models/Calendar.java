package FontysICT.Models;

import FontysICT.Models.CalendarItem;
import java.util.*;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * A certain calendar, either for students or for teachers.
 **/
@ApiModel(description = "A certain calendar, either for students or for teachers.")
public class Calendar  {
  
  @SerializedName("title")
  private String title = null;
  @SerializedName("items")
  private List<CalendarItem> items = null;
  @SerializedName("tag")
  private String tag = null;

  
  /**
   * Title of the calendar
   **/
  @ApiModelProperty(value = "Title of the calendar")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Calendar items
   **/
  @ApiModelProperty(value = "Calendar items")
  public List<CalendarItem> getItems() {
    return items;
  }
  public void setItems(List<CalendarItem> items) {
    this.items = items;
  }

  
  /**
   * The requested tag
   **/
  @ApiModelProperty(value = "The requested tag")
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Calendar {\n");
    
    sb.append("  title: ").append(title).append("\n");
    sb.append("  items: ").append(items).append("\n");
    sb.append("  tag: ").append(tag).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


