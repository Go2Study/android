package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Inzet item voor docenten
 **/
@ApiModel(description = "Inzet item voor docenten")
public class InzetItem  {
  
  @SerializedName("projectNaam")
  private String projectNaam = null;
  @SerializedName("taakNaam")
  private String taakNaam = null;
  @SerializedName("kwartaal")
  private Integer kwartaal = null;
  @SerializedName("aantalUren")
  private Double aantalUren = null;
  @SerializedName("oercodeNaam")
  private String oercodeNaam = null;
  @SerializedName("opmerkingen")
  private String opmerkingen = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getProjectNaam() {
    return projectNaam;
  }
  public void setProjectNaam(String projectNaam) {
    this.projectNaam = projectNaam;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getTaakNaam() {
    return taakNaam;
  }
  public void setTaakNaam(String taakNaam) {
    this.taakNaam = taakNaam;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Integer getKwartaal() {
    return kwartaal;
  }
  public void setKwartaal(Integer kwartaal) {
    this.kwartaal = kwartaal;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public Double getAantalUren() {
    return aantalUren;
  }
  public void setAantalUren(Double aantalUren) {
    this.aantalUren = aantalUren;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getOercodeNaam() {
    return oercodeNaam;
  }
  public void setOercodeNaam(String oercodeNaam) {
    this.oercodeNaam = oercodeNaam;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getOpmerkingen() {
    return opmerkingen;
  }
  public void setOpmerkingen(String opmerkingen) {
    this.opmerkingen = opmerkingen;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InzetItem {\n");
    
    sb.append("  projectNaam: ").append(projectNaam).append("\n");
    sb.append("  taakNaam: ").append(taakNaam).append("\n");
    sb.append("  kwartaal: ").append(kwartaal).append("\n");
    sb.append("  aantalUren: ").append(aantalUren).append("\n");
    sb.append("  oercodeNaam: ").append(oercodeNaam).append("\n");
    sb.append("  opmerkingen: ").append(opmerkingen).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


