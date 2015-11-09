package FontysICT.Models;

import java.util.Date;


import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;



/**
 * News item, from a specific feed.
 **/
@ApiModel(description = "News item, from a specific feed.")
public class NewsItem  {
  
  @SerializedName("id")
  private String id = null;
  @SerializedName("url")
  private String url = null;
  @SerializedName("pubDate")
  private Date pubDate = null;
  @SerializedName("title")
  private String title = null;
  @SerializedName("author")
  private String author = null;
  @SerializedName("image")
  private String image = null;
  @SerializedName("thumbnail")
  private String thumbnail = null;
  @SerializedName("link")
  private String link = null;
  @SerializedName("content")
  private String content = null;

  
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
   * Item publicated on
   **/
  @ApiModelProperty(value = "Item publicated on")
  public Date getPubDate() {
    return pubDate;
  }
  public void setPubDate(Date pubDate) {
    this.pubDate = pubDate;
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
   * Author
   **/
  @ApiModelProperty(value = "Author")
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }

  
  /**
   * Image URL
   **/
  @ApiModelProperty(value = "Image URL")
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  
  /**
   * Thumb of image
   **/
  @ApiModelProperty(value = "Thumb of image")
  public String getThumbnail() {
    return thumbnail;
  }
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  
  /**
   * URL of original article
   **/
  @ApiModelProperty(value = "URL of original article")
  public String getLink() {
    return link;
  }
  public void setLink(String link) {
    this.link = link;
  }

  
  /**
   * HTML string with the content
   **/
  @ApiModelProperty(value = "HTML string with the content")
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsItem {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  url: ").append(url).append("\n");
    sb.append("  pubDate: ").append(pubDate).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  author: ").append(author).append("\n");
    sb.append("  image: ").append(image).append("\n");
    sb.append("  thumbnail: ").append(thumbnail).append("\n");
    sb.append("  link: ").append(link).append("\n");
    sb.append("  content: ").append(content).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


