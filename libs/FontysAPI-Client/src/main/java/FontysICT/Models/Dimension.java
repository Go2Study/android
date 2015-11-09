package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Dimension of the map, to display the location on the map.
 **/
@ApiModel(description = "Dimension of the map, to display the location on the map.")
public class Dimension  {
  
  @SerializedName("length")
  private Float length = null;
  @SerializedName("width")
  private Float width = null;
  @SerializedName("height")
  private Float height = null;
  @SerializedName("offsetX")
  private Float offsetX = null;
  @SerializedName("offsetY")
  private Float offsetY = null;
  @SerializedName("unit")
  private String unit = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getLength() {
    return length;
  }
  public void setLength(Float length) {
    this.length = length;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getWidth() {
    return width;
  }
  public void setWidth(Float width) {
    this.width = width;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getHeight() {
    return height;
  }
  public void setHeight(Float height) {
    this.height = height;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getOffsetX() {
    return offsetX;
  }
  public void setOffsetX(Float offsetX) {
    this.offsetX = offsetX;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getOffsetY() {
    return offsetY;
  }
  public void setOffsetY(Float offsetY) {
    this.offsetY = offsetY;
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
    sb.append("class Dimension {\n");
    
    sb.append("  length: ").append(length).append("\n");
    sb.append("  width: ").append(width).append("\n");
    sb.append("  height: ").append(height).append("\n");
    sb.append("  offsetX: ").append(offsetX).append("\n");
    sb.append("  offsetY: ").append(offsetY).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


