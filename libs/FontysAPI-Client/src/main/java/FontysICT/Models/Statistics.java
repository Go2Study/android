package FontysICT.Models;

import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Server statistics (from Cisco Core Location)
 **/
@ApiModel(description = "Server statistics (from Cisco Core Location)")
public class Statistics  {
  
  @SerializedName("currentServerTime")
  private Date currentServerTime = null;
  @SerializedName("firstLocatedTime")
  private Date firstLocatedTime = null;
  @SerializedName("lastLocatedTime")
  private Date lastLocatedTime = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Date getCurrentServerTime() {
    return currentServerTime;
  }
  public void setCurrentServerTime(Date currentServerTime) {
    this.currentServerTime = currentServerTime;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Date getFirstLocatedTime() {
    return firstLocatedTime;
  }
  public void setFirstLocatedTime(Date firstLocatedTime) {
    this.firstLocatedTime = firstLocatedTime;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Date getLastLocatedTime() {
    return lastLocatedTime;
  }
  public void setLastLocatedTime(Date lastLocatedTime) {
    this.lastLocatedTime = lastLocatedTime;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Statistics {\n");
    
    sb.append("  currentServerTime: ").append(currentServerTime).append("\n");
    sb.append("  firstLocatedTime: ").append(firstLocatedTime).append("\n");
    sb.append("  lastLocatedTime: ").append(lastLocatedTime).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


