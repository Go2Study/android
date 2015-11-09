package FontysICT.Models;

import java.util.*;
import FontysICT.Models.Person;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Group defined for FHICT
 **/
@ApiModel(description = "Group defined for FHICT")
public class Group  {
  
  @SerializedName("id")
  private String id = null;
  @SerializedName("url")
  private String url = null;
  @SerializedName("groupName")
  private String groupName = null;
  public enum GroupTypeEnum {
     Generic,  Personal,  Klas, 
  };
  @SerializedName("groupType")
  private GroupTypeEnum groupType = null;
  public enum RoleEnum {
     Administrator,  Member, 
  };
  @SerializedName("role")
  private RoleEnum role = null;
  @SerializedName("members")
  private List<Person> members = null;

  
  /**
   * Unique ID
   **/
  @ApiModelProperty(value = "Unique ID")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Group URL (member information)
   **/
  @ApiModelProperty(value = "Group URL (member information)")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  
  /**
   * GroupName
   **/
  @ApiModelProperty(value = "GroupName")
  public String getGroupName() {
    return groupName;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  
  /**
   * Type of group [Generic/Personal/Klas]
   **/
  @ApiModelProperty(value = "Type of group [Generic/Personal/Klas]")
  public GroupTypeEnum getGroupType() {
    return groupType;
  }
  public void setGroupType(GroupTypeEnum groupType) {
    this.groupType = groupType;
  }

  
  /**
   * Role of the listed user [Administrator (for personal groups)/Member]
   **/
  @ApiModelProperty(value = "Role of the listed user [Administrator (for personal groups)/Member]")
  public RoleEnum getRole() {
    return role;
  }
  public void setRole(RoleEnum role) {
    this.role = role;
  }

  
  /**
   * All members (should be requested specificly)
   **/
  @ApiModelProperty(value = "All members (should be requested specificly)")
  public List<Person> getMembers() {
    return members;
  }
  public void setMembers(List<Person> members) {
    this.members = members;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Group {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  url: ").append(url).append("\n");
    sb.append("  groupName: ").append(groupName).append("\n");
    sb.append("  groupType: ").append(groupType).append("\n");
    sb.append("  role: ").append(role).append("\n");
    sb.append("  members: ").append(members).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


