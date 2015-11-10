package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Auto-complete of values to request a schedule
 **/
@ApiModel(description = "Auto-complete of values to request a schedule")
public class ScheduleQueryItem  {
  
  @SerializedName("name")
  private String name = null;
  public enum KindEnum {
     Any,  Class,  Room,  Subject,  Teacher,  User, 
  };
  @SerializedName("kind")
  private KindEnum kind = null;
  @SerializedName("description")
  private String description = null;

  
  /**
   * Unique name for requesting the schedule
   **/
  @ApiModelProperty(value = "Unique name for requesting the schedule")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * Schedule kind
   **/
  @ApiModelProperty(value = "Schedule kind")
  public KindEnum getKind() {
    return kind;
  }
  public void setKind(KindEnum kind) {
    this.kind = kind;
  }

  
  /**
   * Description of the item (only for teachers, contains the teachers name)
   **/
  @ApiModelProperty(value = "Description of the item (only for teachers, contains the teachers name)")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleQueryItem {\n");
    
    sb.append("  name: ").append(name).append("\n");
    sb.append("  kind: ").append(kind).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


