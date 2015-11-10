package FontysICT.Models;

import FontysICT.Models.Apinterface;
import FontysICT.Models.Mapcoordinate;
import java.util.*;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Accesspoint  {
  
  @SerializedName("name")
  private String name = null;
  @SerializedName("radioMacAddress")
  private String radioMacAddress = null;
  @SerializedName("ethMacAddress")
  private String ethMacAddress = null;
  @SerializedName("ipAddress")
  private String ipAddress = null;
  @SerializedName("numOfSlots")
  private Integer numOfSlots = null;
  @SerializedName("apMode")
  private String apMode = null;
  @SerializedName("mapCoordinate")
  private Mapcoordinate mapCoordinate = null;
  @SerializedName("apInterface")
  private List<Apinterface> apInterface = null;

  
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
  public String getRadioMacAddress() {
    return radioMacAddress;
  }
  public void setRadioMacAddress(String radioMacAddress) {
    this.radioMacAddress = radioMacAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getEthMacAddress() {
    return ethMacAddress;
  }
  public void setEthMacAddress(String ethMacAddress) {
    this.ethMacAddress = ethMacAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getNumOfSlots() {
    return numOfSlots;
  }
  public void setNumOfSlots(Integer numOfSlots) {
    this.numOfSlots = numOfSlots;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getApMode() {
    return apMode;
  }
  public void setApMode(String apMode) {
    this.apMode = apMode;
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
  public List<Apinterface> getApInterface() {
    return apInterface;
  }
  public void setApInterface(List<Apinterface> apInterface) {
    this.apInterface = apInterface;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Accesspoint {\n");
    
    sb.append("  name: ").append(name).append("\n");
    sb.append("  radioMacAddress: ").append(radioMacAddress).append("\n");
    sb.append("  ethMacAddress: ").append(ethMacAddress).append("\n");
    sb.append("  ipAddress: ").append(ipAddress).append("\n");
    sb.append("  numOfSlots: ").append(numOfSlots).append("\n");
    sb.append("  apMode: ").append(apMode).append("\n");
    sb.append("  mapCoordinate: ").append(mapCoordinate).append("\n");
    sb.append("  apInterface: ").append(apInterface).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


