package FontysICT.Models;

import java.io.Serializable;
import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Period defined by startdate and enddate with a specific title
 **/
@ApiModel(description = "Period defined by startdate and enddate with a specific title")
public class Period  implements Serializable {
  
  @SerializedName("title")
  private String title = null;
  @SerializedName("start")
  private Date start = null;
  @SerializedName("end")
  private Date end = null;

  
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
   * Startdate
   **/
  @ApiModelProperty(value = "Startdate")
  public Date getStart() {
    return start;
  }
  public void setStart(Date start) {
    this.start = start;
  }

  
  /**
   * Enddate
   **/
  @ApiModelProperty(value = "Enddate")
  public Date getEnd() {
    return end;
  }
  public void setEnd(Date end) {
    this.end = end;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Period {\n");
    
    sb.append("  title: ").append(title).append("\n");
    sb.append("  start: ").append(start).append("\n");
    sb.append("  end: ").append(end).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


