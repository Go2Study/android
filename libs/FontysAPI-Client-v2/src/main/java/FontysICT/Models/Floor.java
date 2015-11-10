package FontysICT.Models;

import FontysICT.Models.Accesspoint;
import java.util.*;
import FontysICT.Models.Dimension;
import FontysICT.Models.Locationfilterregion;
import FontysICT.Models.Image;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Floor  {
  
  @SerializedName("objectVersion")
  private Integer objectVersion = null;
  @SerializedName("name")
  private String name = null;
  @SerializedName("isOutdoor")
  private Boolean isOutdoor = null;
  @SerializedName("floorNumber")
  private Integer floorNumber = null;
  @SerializedName("dimension")
  private Dimension dimension = null;
  @SerializedName("image")
  private Image image = null;
  @SerializedName("gpsMarker")
  private List<Object> gpsMarker = null;
  @SerializedName("zone")
  private List<Object> zone = null;
  @SerializedName("obstacle")
  private List<Object> obstacle = null;
  @SerializedName("accessPoint")
  private List<Accesspoint> accessPoint = null;
  @SerializedName("referenceMarker")
  private List<Object> referenceMarker = null;
  @SerializedName("exciter")
  private List<Object> exciter = null;
  @SerializedName("locationFilterRegion")
  private List<Locationfilterregion> locationFilterRegion = null;
  @SerializedName("locationFilterRail")
  private List<Object> locationFilterRail = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getObjectVersion() {
    return objectVersion;
  }
  public void setObjectVersion(Integer objectVersion) {
    this.objectVersion = objectVersion;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getIsOutdoor() {
    return isOutdoor;
  }
  public void setIsOutdoor(Boolean isOutdoor) {
    this.isOutdoor = isOutdoor;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getFloorNumber() {
    return floorNumber;
  }
  public void setFloorNumber(Integer floorNumber) {
    this.floorNumber = floorNumber;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Dimension getDimension() {
    return dimension;
  }
  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Image getImage() {
    return image;
  }
  public void setImage(Image image) {
    this.image = image;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getGpsMarker() {
    return gpsMarker;
  }
  public void setGpsMarker(List<Object> gpsMarker) {
    this.gpsMarker = gpsMarker;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getZone() {
    return zone;
  }
  public void setZone(List<Object> zone) {
    this.zone = zone;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getObstacle() {
    return obstacle;
  }
  public void setObstacle(List<Object> obstacle) {
    this.obstacle = obstacle;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Accesspoint> getAccessPoint() {
    return accessPoint;
  }
  public void setAccessPoint(List<Accesspoint> accessPoint) {
    this.accessPoint = accessPoint;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getReferenceMarker() {
    return referenceMarker;
  }
  public void setReferenceMarker(List<Object> referenceMarker) {
    this.referenceMarker = referenceMarker;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getExciter() {
    return exciter;
  }
  public void setExciter(List<Object> exciter) {
    this.exciter = exciter;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Locationfilterregion> getLocationFilterRegion() {
    return locationFilterRegion;
  }
  public void setLocationFilterRegion(List<Locationfilterregion> locationFilterRegion) {
    this.locationFilterRegion = locationFilterRegion;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Object> getLocationFilterRail() {
    return locationFilterRail;
  }
  public void setLocationFilterRail(List<Object> locationFilterRail) {
    this.locationFilterRail = locationFilterRail;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Floor {\n");
    
    sb.append("  objectVersion: ").append(objectVersion).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  isOutdoor: ").append(isOutdoor).append("\n");
    sb.append("  floorNumber: ").append(floorNumber).append("\n");
    sb.append("  dimension: ").append(dimension).append("\n");
    sb.append("  image: ").append(image).append("\n");
    sb.append("  gpsMarker: ").append(gpsMarker).append("\n");
    sb.append("  zone: ").append(zone).append("\n");
    sb.append("  obstacle: ").append(obstacle).append("\n");
    sb.append("  accessPoint: ").append(accessPoint).append("\n");
    sb.append("  referenceMarker: ").append(referenceMarker).append("\n");
    sb.append("  exciter: ").append(exciter).append("\n");
    sb.append("  locationFilterRegion: ").append(locationFilterRegion).append("\n");
    sb.append("  locationFilterRail: ").append(locationFilterRail).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


