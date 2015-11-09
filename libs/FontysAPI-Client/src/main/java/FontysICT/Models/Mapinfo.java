package FontysICT.Models;

import FontysICT.Models.Dimension;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Information about the used map.
 **/
@ApiModel(description = "Information about the used map.")
public class Mapinfo  {
  
  @SerializedName("mapHierarchyString")
  private String mapHierarchyString = null;
  @SerializedName("floorRefId")
  private Long floorRefId = null;
  @SerializedName("dimension")
  private Dimension dimension = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getMapHierarchyString() {
    return mapHierarchyString;
  }
  public void setMapHierarchyString(String mapHierarchyString) {
    this.mapHierarchyString = mapHierarchyString;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Long getFloorRefId() {
    return floorRefId;
  }
  public void setFloorRefId(Long floorRefId) {
    this.floorRefId = floorRefId;
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Mapinfo {\n");
    
    sb.append("  mapHierarchyString: ").append(mapHierarchyString).append("\n");
    sb.append("  floorRefId: ").append(floorRefId).append("\n");
    sb.append("  dimension: ").append(dimension).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


