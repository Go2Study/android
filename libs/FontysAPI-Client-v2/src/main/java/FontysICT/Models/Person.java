package FontysICT.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * This object describes a person
 **/
@ApiModel(description = "This object describes a person")
public class Person  {
  
  @SerializedName("id")
  private String id = null;
  @SerializedName("url")
  private String url = null;
  @SerializedName("givenName")
  private String givenName = null;
  @SerializedName("surName")
  private String surName = null;
  @SerializedName("initials")
  private String initials = null;
  @SerializedName("displayName")
  private String displayName = null;
  @SerializedName("mail")
  private String mail = null;
  @SerializedName("office")
  private String office = null;
  @SerializedName("telephoneNumber")
  private String telephoneNumber = null;
  @SerializedName("mobileNumber")
  private String mobileNumber = null;
  @SerializedName("photo")
  private String photo = null;
  @SerializedName("department")
  private String department = null;
  @SerializedName("title")
  private String title = null;
  @SerializedName("personalTitle")
  private String personalTitle = null;
  @SerializedName("groups")
  private List<Group> groups = null;
  @SerializedName("lat")
  private Double lat = null;
  @SerializedName("long")
  private Double _long = null;
  @SerializedName("affiliations")
  private List<String> affiliations = null;
  @SerializedName("updatedAt")
  private String updatedAt = null;
  @SerializedName("uid")
  private String uid = null;

  
  /**
   * Unique ID (username)
   **/
  @ApiModelProperty(value = "Unique ID (username)")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * URL of the information about this user.
   **/
  @ApiModelProperty(value = "URL of the information about this user.")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  
  /**
   * Givenname or firstname
   **/
  @ApiModelProperty(value = "Givenname or firstname")
  public String getGivenName() {
    return givenName;
  }
  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  
  /**
   * Surname or lastname
   **/
  @ApiModelProperty(value = "Surname or lastname")
  public String getSurName() {
    return surName;
  }
  public void setSurName(String surName) {
    this.surName = surName;
  }

  
  /**
   * Initials
   **/
  @ApiModelProperty(value = "Initials")
  public String getInitials() {
    return initials;
  }
  public void setInitials(String initials) {
    this.initials = initials;
  }

  
  /**
   * Display name formatted like lastname, Givenname initials
   **/
  @ApiModelProperty(value = "Display name formatted like lastname, Givenname initials")
  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  
  /**
   * Emailaddress
   **/
  @ApiModelProperty(value = "Emailaddress")
  public String getMail() {
    return mail;
  }
  public void setMail(String mail) {
    this.mail = mail;
  }

  
  /**
   * Office (only availeble for teachers/staff)
   **/
  @ApiModelProperty(value = "Office (only availeble for teachers/staff)")
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }

  
  /**
   * Phonenumber
   **/
  @ApiModelProperty(value = "Phonenumber")
  public String getTelephoneNumber() {
    return telephoneNumber;
  }
  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  
  /**
   * Mobile number (if availible and allowed)
   **/
  @ApiModelProperty(value = "Mobile number (if availible and allowed)")
  public String getMobileNumber() {
    return mobileNumber;
  }
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  
  /**
   * Photo URL, you'll need to add the token to the url (as ?access_token=) or use the header like you would normally
   **/
  @ApiModelProperty(value = "Photo URL, you'll need to add the token to the url (as ?access_token=) or use the header like you would normally")
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  
  /**
   * Department
   **/
  @ApiModelProperty(value = "Department")
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }

  
  /**
   * \"Job\" title
   **/
  @ApiModelProperty(value = "\"Job\" title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Schedule information either teacher abbreviation or classess
   **/
  @ApiModelProperty(value = "Schedule information either teacher abbreviation or classess")
  public String getPersonalTitle() {
    return personalTitle;
  }
  public void setPersonalTitle(String personalTitle) {
    this.personalTitle = personalTitle;
  }

  
  /**
   * Usergroups (only when requesting a single user)
   **/
  @ApiModelProperty(value = "Usergroups (only when requesting a single user)")
  public List<Group> getGroups() {
    return groups;
  }
  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  
  /**
   * Last known latitude (Only for teachers/staff)
   **/
  @ApiModelProperty(value = "Last known latitude (Only for teachers/staff)")
  public Double getLat() {
    return lat;
  }
  public void setLat(Double lat) {
    this.lat = lat;
  }

  
  /**
   * Last known longitude (Only for teachers/staff)
   **/
  @ApiModelProperty(value = "Last known longitude (Only for teachers/staff)")
  public Double getLong() {
    return _long;
  }
  public void setLong(Double _long) {
    this._long = _long;
  }

  
  /**
   * User Affliations with FHICT
   **/
  @ApiModelProperty(value = "User Affliations with FHICT")
  public List<String> getAffiliations() {
    return affiliations;
  }
  public void setAffiliations(List<String> affiliations) {
    this.affiliations = affiliations;
  }

  
  /**
   * Person last updated. (May not be accurate)
   **/
  @ApiModelProperty(value = "Person last updated. (May not be accurate)")
  public String getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  
  /**
   * Global Unique ID
   **/
  @ApiModelProperty(value = "Global Unique ID")
  public String getUid() {
    return uid;
  }
  public void setUid(String uid) {
    this.uid = uid;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Person {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  url: ").append(url).append("\n");
    sb.append("  givenName: ").append(givenName).append("\n");
    sb.append("  surName: ").append(surName).append("\n");
    sb.append("  initials: ").append(initials).append("\n");
    sb.append("  displayName: ").append(displayName).append("\n");
    sb.append("  mail: ").append(mail).append("\n");
    sb.append("  office: ").append(office).append("\n");
    sb.append("  telephoneNumber: ").append(telephoneNumber).append("\n");
    sb.append("  mobileNumber: ").append(mobileNumber).append("\n");
    sb.append("  photo: ").append(photo).append("\n");
    sb.append("  department: ").append(department).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  personalTitle: ").append(personalTitle).append("\n");
    sb.append("  groups: ").append(groups).append("\n");
    sb.append("  lat: ").append(lat).append("\n");
    sb.append("  _long: ").append(_long).append("\n");
    sb.append("  affiliations: ").append(affiliations).append("\n");
    sb.append("  updatedAt: ").append(updatedAt).append("\n");
    sb.append("  uid: ").append(uid).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


