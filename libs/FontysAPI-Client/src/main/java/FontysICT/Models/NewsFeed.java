package FontysICT.Models;

import java.util.*;
import java.util.Date;
import FontysICT.Models.NewsItem;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * Newsfeed
 **/
@ApiModel(description = "Newsfeed")
public class NewsFeed  {
  
  public enum IdEnum {
     Fhict,  FhictMededelingen,  FhictMedewerkersplein,  Fontys,  FontysCommunity,  FontysOnderwijs,  FontysOnderzoek, 
  };
  @SerializedName("id")
  private IdEnum id = null;
  @SerializedName("url")
  private String url = null;
  @SerializedName("title")
  private String title = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("updated")
  private Date updated = null;
  @SerializedName("items")
  private List<NewsItem> items = null;

  
  /**
   * Unique ID
   **/
  @ApiModelProperty(value = "Unique ID")
  public IdEnum getId() {
    return id;
  }
  public void setId(IdEnum id) {
    this.id = id;
  }

  
  /**
   * Entity URL
   **/
  @ApiModelProperty(value = "Entity URL")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  
  /**
   * Title
   **/
  @ApiModelProperty(value = "Title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Description
   **/
  @ApiModelProperty(value = "Description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Last item updated on
   **/
  @ApiModelProperty(value = "Last item updated on")
  public Date getUpdated() {
    return updated;
  }
  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  
  /**
   * Newsfeed items (should be requested)
   **/
  @ApiModelProperty(value = "Newsfeed items (should be requested)")
  public List<NewsItem> getItems() {
    return items;
  }
  public void setItems(List<NewsItem> items) {
    this.items = items;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsFeed {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  url: ").append(url).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  updated: ").append(updated).append("\n");
    sb.append("  items: ").append(items).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


