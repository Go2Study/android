package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Geo information
 **/
@ApiModel(description = "Geo information")
public class Geocoordinate  {
  
  @SerializedName("lattitude")
  private Float lattitude = null;
  @SerializedName("longitude")
  private Float longitude = null;
  @SerializedName("unit")
  private String unit = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getLattitude() {
    return lattitude;
  }
  public void setLattitude(Float lattitude) {
    this.lattitude = lattitude;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getLongitude() {
    return longitude;
  }
  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getUnit() {
    return unit;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Geocoordinate {\n");
    
    sb.append("  lattitude: ").append(lattitude).append("\n");
    sb.append("  longitude: ").append(longitude).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


