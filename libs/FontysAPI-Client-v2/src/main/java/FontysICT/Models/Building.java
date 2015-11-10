package FontysICT.Models;

import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * This class is used to describe a building
 **/
@ApiModel(description = "This class is used to describe a building")
public class Building  {
  
  @SerializedName("id")
  private String id = null;
  @SerializedName("url")
  private String url = null;
  @SerializedName("abbr")
  private String abbr = null;
  @SerializedName("name")
  private String name = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("address")
  private String address = null;
  @SerializedName("postalCode")
  private String postalCode = null;
  @SerializedName("city")
  private String city = null;
  @SerializedName("lat")
  private Double lat = null;
  @SerializedName("long")
  private Double _long = null;
  @SerializedName("lastModified")
  private Date lastModified = null;

  
  /**
   * Building ID
   **/
  @ApiModelProperty(value = "Building ID")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Building URL (entity)
   **/
  @ApiModelProperty(value = "Building URL (entity)")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  
  /**
   * Building abbreviation
   **/
  @ApiModelProperty(value = "Building abbreviation")
  public String getAbbr() {
    return abbr;
  }
  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }

  
  /**
   * Name of the building
   **/
  @ApiModelProperty(value = "Name of the building")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * Building description
   **/
  @ApiModelProperty(value = "Building description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Streetaddress
   **/
  @ApiModelProperty(value = "Streetaddress")
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  
  /**
   * Postalcode of building
   **/
  @ApiModelProperty(value = "Postalcode of building")
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  
  /**
   * City of the building
   **/
  @ApiModelProperty(value = "City of the building")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  
  /**
   * Latitude of the building
   **/
  @ApiModelProperty(value = "Latitude of the building")
  public Double getLat() {
    return lat;
  }
  public void setLat(Double lat) {
    this.lat = lat;
  }

  
  /**
   * Longitude of the building
   **/
  @ApiModelProperty(value = "Longitude of the building")
  public Double getLong() {
    return _long;
  }
  public void setLong(Double _long) {
    this._long = _long;
  }

  
  /**
   * Last modified
   **/
  @ApiModelProperty(value = "Last modified")
  public Date getLastModified() {
    return lastModified;
  }
  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Building {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  url: ").append(url).append("\n");
    sb.append("  abbr: ").append(abbr).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  address: ").append(address).append("\n");
    sb.append("  postalCode: ").append(postalCode).append("\n");
    sb.append("  city: ").append(city).append("\n");
    sb.append("  lat: ").append(lat).append("\n");
    sb.append("  _long: ").append(_long).append("\n");
    sb.append("  lastModified: ").append(lastModified).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


