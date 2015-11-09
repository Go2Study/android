package FontysICT.Models;



import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



@ApiModel(description = "")
public class Image  {
  
  @SerializedName("imageName")
  private String imageName = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  public String getImageName() {
    return imageName;
  }
  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    
    sb.append("  imageName: ").append(imageName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


