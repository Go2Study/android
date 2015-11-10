package FontysICT.Models;

import FontysICT.Models.Mapcoordinate;
import java.util.*;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Locationfilterregion  {
  
  @SerializedName("regionType")
  private String regionType = null;
  @SerializedName("mapCoordinate")
  private List<Mapcoordinate> mapCoordinate = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getRegionType() {
    return regionType;
  }
  public void setRegionType(String regionType) {
    this.regionType = regionType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public List<Mapcoordinate> getMapCoordinate() {
    return mapCoordinate;
  }
  public void setMapCoordinate(List<Mapcoordinate> mapCoordinate) {
    this.mapCoordinate = mapCoordinate;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Locationfilterregion {\n");
    
    sb.append("  regionType: ").append(regionType).append("\n");
    sb.append("  mapCoordinate: ").append(mapCoordinate).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


