package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Coordinate on the map (relative to the map size)
 **/
@ApiModel(description = "Coordinate on the map (relative to the map size)")
public class Mapcoordinate  {
  
  @SerializedName("x")
  private Float x = null;
  @SerializedName("y")
  private Float y = null;
  @SerializedName("unit")
  private String unit = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getX() {
    return x;
  }
  public void setX(Float x) {
    this.x = x;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getY() {
    return y;
  }
  public void setY(Float y) {
    this.y = y;
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
    sb.append("class Mapcoordinate {\n");
    
    sb.append("  x: ").append(x).append("\n");
    sb.append("  y: ").append(y).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


