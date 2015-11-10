package FontysICT.Models;

import FontysICT.Models.Mapcoordinate;
import java.util.*;
import FontysICT.Models.Mapinfo;
import FontysICT.Models.Geocoordinate;
import FontysICT.Models.Statistics;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Location record of User
 **/
@ApiModel(description = "Location record of User")
public class WirelessLocation  {
  
  @SerializedName("currentlyTracked")
  private Boolean currentlyTracked = null;
  @SerializedName("confidenceFactor")
  private Float confidenceFactor = null;
  @SerializedName("ipAddress")
  private List<String> ipAddress = null;
  @SerializedName("ssId")
  private String ssId = null;
  @SerializedName("band")
  private String band = null;
  @SerializedName("apMacAddress")
  private String apMacAddress = null;
  @SerializedName("isGuestUser")
  private Boolean isGuestUser = null;
  @SerializedName("dot11Status")
  private String dot11Status = null;
  @SerializedName("mapInfo")
  private Mapinfo mapInfo = null;
  @SerializedName("mapCoordinate")
  private Mapcoordinate mapCoordinate = null;
  @SerializedName("statistics")
  private Statistics statistics = null;
  @SerializedName("geoCoordinate")
  private Geocoordinate geoCoordinate = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getCurrentlyTracked() {
    return currentlyTracked;
  }
  public void setCurrentlyTracked(Boolean currentlyTracked) {
    this.currentlyTracked = currentlyTracked;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getConfidenceFactor() {
    return confidenceFactor;
  }
  public void setConfidenceFactor(Float confidenceFactor) {
    this.confidenceFactor = confidenceFactor;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<String> getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(List<String> ipAddress) {
    this.ipAddress = ipAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getSsId() {
    return ssId;
  }
  public void setSsId(String ssId) {
    this.ssId = ssId;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getBand() {
    return band;
  }
  public void setBand(String band) {
    this.band = band;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getApMacAddress() {
    return apMacAddress;
  }
  public void setApMacAddress(String apMacAddress) {
    this.apMacAddress = apMacAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getIsGuestUser() {
    return isGuestUser;
  }
  public void setIsGuestUser(Boolean isGuestUser) {
    this.isGuestUser = isGuestUser;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getDot11Status() {
    return dot11Status;
  }
  public void setDot11Status(String dot11Status) {
    this.dot11Status = dot11Status;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Mapinfo getMapInfo() {
    return mapInfo;
  }
  public void setMapInfo(Mapinfo mapInfo) {
    this.mapInfo = mapInfo;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Mapcoordinate getMapCoordinate() {
    return mapCoordinate;
  }
  public void setMapCoordinate(Mapcoordinate mapCoordinate) {
    this.mapCoordinate = mapCoordinate;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Statistics getStatistics() {
    return statistics;
  }
  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Geocoordinate getGeoCoordinate() {
    return geoCoordinate;
  }
  public void setGeoCoordinate(Geocoordinate geoCoordinate) {
    this.geoCoordinate = geoCoordinate;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class WirelessLocation {\n");
    
    sb.append("  currentlyTracked: ").append(currentlyTracked).append("\n");
    sb.append("  confidenceFactor: ").append(confidenceFactor).append("\n");
    sb.append("  ipAddress: ").append(ipAddress).append("\n");
    sb.append("  ssId: ").append(ssId).append("\n");
    sb.append("  band: ").append(band).append("\n");
    sb.append("  apMacAddress: ").append(apMacAddress).append("\n");
    sb.append("  isGuestUser: ").append(isGuestUser).append("\n");
    sb.append("  dot11Status: ").append(dot11Status).append("\n");
    sb.append("  mapInfo: ").append(mapInfo).append("\n");
    sb.append("  mapCoordinate: ").append(mapCoordinate).append("\n");
    sb.append("  statistics: ").append(statistics).append("\n");
    sb.append("  geoCoordinate: ").append(geoCoordinate).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


