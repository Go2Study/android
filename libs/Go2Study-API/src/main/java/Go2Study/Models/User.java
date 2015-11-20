package Go2Study.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class User  {
  
  @SerializedName("id")
  private String id = null;
  @SerializedName("im_status")
  private String imStatus = null;
  @SerializedName("gps_location")
  private String gpsLocation = null;
  @SerializedName("photo")
  private String photo = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * User's online/offline status for messages
   **/
  @ApiModelProperty(value = "User's online/offline status for messages")
  public String getImStatus() {
    return imStatus;
  }
  public void setImStatus(String imStatus) {
    this.imStatus = imStatus;
  }

  
  /**
   * Longitude, Latitude
   **/
  @ApiModelProperty(value = "Longitude, Latitude")
  public String getGpsLocation() {
    return gpsLocation;
  }
  public void setGpsLocation(String gpsLocation) {
    this.gpsLocation = gpsLocation;
  }

  
  /**
   * URL to User's photograph on server
   **/
  @ApiModelProperty(value = "URL to User's photograph on server")
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  imStatus: ").append(imStatus).append("\n");
    sb.append("  gpsLocation: ").append(gpsLocation).append("\n");
    sb.append("  photo: ").append(photo).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


