package Go2Study.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Event  {
  
  @SerializedName("id")
  private Integer id = null;
  @SerializedName("startTime")
  private String startTime = null;
  @SerializedName("endTime")
  private String endTime = null;
  @SerializedName("location")
  private String location = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("title")
  private String title;


  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getStartTime() {
    return startTime;
  }
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getEndTime() {
    return endTime;
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  startTime: ").append(startTime).append("\n");
    sb.append("  endTime: ").append(endTime).append("\n");
    sb.append("  location: ").append(location).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  title: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


