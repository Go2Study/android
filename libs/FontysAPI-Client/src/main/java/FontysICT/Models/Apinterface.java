package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Apinterface  {
  
  @SerializedName("band")
  private String band = null;
  @SerializedName("slotNumber")
  private Integer slotNumber = null;
  @SerializedName("channelAssignment")
  private Integer channelAssignment = null;
  @SerializedName("channelNumber")
  private Integer channelNumber = null;
  @SerializedName("txPowerLevel")
  private Integer txPowerLevel = null;
  @SerializedName("antennaPattern")
  private String antennaPattern = null;
  @SerializedName("antennaAngle")
  private Float antennaAngle = null;
  @SerializedName("antennaElevAngle")
  private Float antennaElevAngle = null;
  @SerializedName("antennaGain")
  private Float antennaGain = null;

  
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
  public Integer getSlotNumber() {
    return slotNumber;
  }
  public void setSlotNumber(Integer slotNumber) {
    this.slotNumber = slotNumber;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getChannelAssignment() {
    return channelAssignment;
  }
  public void setChannelAssignment(Integer channelAssignment) {
    this.channelAssignment = channelAssignment;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getChannelNumber() {
    return channelNumber;
  }
  public void setChannelNumber(Integer channelNumber) {
    this.channelNumber = channelNumber;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getTxPowerLevel() {
    return txPowerLevel;
  }
  public void setTxPowerLevel(Integer txPowerLevel) {
    this.txPowerLevel = txPowerLevel;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getAntennaPattern() {
    return antennaPattern;
  }
  public void setAntennaPattern(String antennaPattern) {
    this.antennaPattern = antennaPattern;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getAntennaAngle() {
    return antennaAngle;
  }
  public void setAntennaAngle(Float antennaAngle) {
    this.antennaAngle = antennaAngle;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getAntennaElevAngle() {
    return antennaElevAngle;
  }
  public void setAntennaElevAngle(Float antennaElevAngle) {
    this.antennaElevAngle = antennaElevAngle;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Float getAntennaGain() {
    return antennaGain;
  }
  public void setAntennaGain(Float antennaGain) {
    this.antennaGain = antennaGain;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Apinterface {\n");
    
    sb.append("  band: ").append(band).append("\n");
    sb.append("  slotNumber: ").append(slotNumber).append("\n");
    sb.append("  channelAssignment: ").append(channelAssignment).append("\n");
    sb.append("  channelNumber: ").append(channelNumber).append("\n");
    sb.append("  txPowerLevel: ").append(txPowerLevel).append("\n");
    sb.append("  antennaPattern: ").append(antennaPattern).append("\n");
    sb.append("  antennaAngle: ").append(antennaAngle).append("\n");
    sb.append("  antennaElevAngle: ").append(antennaElevAngle).append("\n");
    sb.append("  antennaGain: ").append(antennaGain).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


