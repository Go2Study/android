package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Room Occupancy where all the hours and set as a bit in an integer.
 **/
@ApiModel(description = "Room Occupancy where all the hours and set as a bit in an integer.")
public class RoomOccupancy  {
  
  @SerializedName("roomId")
  private String roomId = null;
  @SerializedName("mask")
  private Integer mask = null;

  
  /**
   * The ID of the room
   **/
  @ApiModelProperty(value = "The ID of the room")
  public String getRoomId() {
    return roomId;
  }
  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  
  /**
   * A bitmask of all the hours for this day.
   **/
  @ApiModelProperty(value = "A bitmask of all the hours for this day.")
  public Integer getMask() {
    return mask;
  }
  public void setMask(Integer mask) {
    this.mask = mask;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoomOccupancy {\n");
    
    sb.append("  roomId: ").append(roomId).append("\n");
    sb.append("  mask: ").append(mask).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


