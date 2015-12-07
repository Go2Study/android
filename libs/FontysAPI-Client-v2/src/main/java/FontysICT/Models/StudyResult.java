package FontysICT.Models;

import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * A student study result. (Always display the rights url!)
 **/
@ApiModel(description = "A student study result. (Always display the rights url!)")
public class StudyResult  {
  
  @SerializedName("date")
  private Date date = null;
  @SerializedName("item")
  private String item = null;
  @SerializedName("itemCode")
  private String itemCode = null;
  @SerializedName("grade")
  private Double grade = null;
  @SerializedName("passed")
  private Boolean passed = null;

  
  /**
   * The date the teacher specified as exam date
   **/
  @ApiModelProperty(value = "The date the teacher specified as exam date")
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }

  
  /**
   * The nice description of the item
   **/
  @ApiModelProperty(value = "The nice description of the item")
  public String getItem() {
    return item;
  }
  public void setItem(String item) {
    this.item = item;
  }

  
  /**
   * The official code of the item
   **/
  @ApiModelProperty(value = "The official code of the item")
  public String getItemCode() {
    return itemCode;
  }
  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  
  /**
   * The Grade of the Subject
   **/
  @ApiModelProperty(value = "The Grade of the Subject")
  public Double getGrade() {
    return grade;
  }
  public void setGrade(Double grade) {
    this.grade = grade;
  }

  
  /**
   * If the student passed with this try, (may not always be accurate)
   **/
  @ApiModelProperty(value = "If the student passed with this try, (may not always be accurate)")
  public Boolean getPassed() {
    return passed;
  }
  public void setPassed(Boolean passed) {
    this.passed = passed;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudyResult {\n");
    
    sb.append("  date: ").append(date).append("\n");
    sb.append("  item: ").append(item).append("\n");
    sb.append("  itemCode: ").append(itemCode).append("\n");
    sb.append("  grade: ").append(grade).append("\n");
    sb.append("  passed: ").append(passed).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


