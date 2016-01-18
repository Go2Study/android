package Go2Study.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel(description = "")
public class User implements Serializable {
  
  @SerializedName("pcn")
  private String pcn = null;
  @SerializedName("firstName")
  private String firstName = null;
  @SerializedName("lastName")
  private String lastName = null;
  @SerializedName("displayName")
  private String displayName = null;
  @SerializedName("className")
  private String className = null;
  @SerializedName("email")
  private String email = null;
  @SerializedName("gps_location")
  private String gpsLocation = null;
  @SerializedName("ipaddress")
  private String ipaddress = null;
  @SerializedName("privateLocation")
  private Boolean privateLocation = null;
  @SerializedName("privateAgenda")
  private Boolean privateAgenda = null;
  @SerializedName("schedule")
  private List<Object> schedule = null;
  @SerializedName("photo")
  private String photo = null;
  @SerializedName("minStartTime")
  private String minStartTime = null;
  @SerializedName("maxEndTime")
  private String maxEndTime = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getPcn() {
    return pcn;
  }
  public void setPcn(String pcn) {
    this.pcn = pcn;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getClassName() {
    return className;
  }
  public void setClassName(String className) {
    this.className = className;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getGpsLocation() {
    return gpsLocation;
  }
  public void setGpsLocation(String gpsLocation) {
    this.gpsLocation = gpsLocation;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getIpaddress() {
    return ipaddress;
  }
  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getPrivateLocation() {
    return privateLocation;
  }
  public void setPrivateLocation(Boolean privateLocation) {
    this.privateLocation = privateLocation;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getPrivateAgenda() {
    return privateAgenda;
  }
  public void setPrivateAgenda(Boolean privateAgenda) {
    this.privateAgenda = privateAgenda;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public void setSchedule(List<Object> schedule) {
    this.schedule = schedule;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  
  /**
   * The earliest time of the day, acceptable for event invitations
   **/
  @ApiModelProperty(value = "The earliest time of the day, acceptable for event invitations")
  public String getMinStartTime() {
    return minStartTime;
  }
  public void setMinStartTime(String minStartTime) {
    this.minStartTime = minStartTime;
  }

  
  /**
   * The latest time of the day, acceptable for event invitations
   **/
  @ApiModelProperty(value = "The latest time of the day, acceptable for event invitations")
  public String getMaxEndTime() {
    return maxEndTime;
  }
  public void setMaxEndTime(String maxEndTime) {
    this.maxEndTime = maxEndTime;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("  pcn: ").append(pcn).append("\n");
    sb.append("  firstName: ").append(firstName).append("\n");
    sb.append("  lastName: ").append(lastName).append("\n");
    sb.append("  displayName: ").append(displayName).append("\n");
    sb.append("  className: ").append(className).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  gpsLocation: ").append(gpsLocation).append("\n");
    sb.append("  ipaddress: ").append(ipaddress).append("\n");
    sb.append("  privateLocation: ").append(privateLocation).append("\n");
    sb.append("  privateAgenda: ").append(privateAgenda).append("\n");
    sb.append("  schedule: ").append(schedule).append("\n");
    sb.append("  photo: ").append(photo).append("\n");
    sb.append("  minStartTime: ").append(minStartTime).append("\n");
    sb.append("  maxEndTime: ").append(maxEndTime).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


