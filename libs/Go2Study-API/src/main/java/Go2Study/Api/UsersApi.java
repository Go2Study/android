package Go2Study.Api;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Invoker.Pair;
import Go2Study.Models.User;


public class UsersApi {
  String basePath = "http://go2study.lol/api";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  
  /**
   * All Users
   * Get a list of all users registered with Go2Study
   * @param query Non-specific query search term. Can be used for filtering the results by name of group, event, pcn, class, department &amp; more.
   * @return List<User>
   */
  public List<User>  usersGet (String query) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/users".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "query", query));
    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      RequestBody requestBody = new MultipartBuilder()
        .type(MultipartBuilder.FORM)
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"title\""),
            RequestBody.create(null, "Square Logo"))
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"image\""),
            RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
        .build();
        */
      postBody = "";
    } else {
      // normal form params
      RequestBody formBody = new FormEncodingBuilder()
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<User>) ApiInvoker.deserialize(response, "array", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Create User
   * Save information when a User logs in for the first time
   * @param firstName The last name of the user
   * @param lastName The last name of the user
   * @param accountpcn Pcn of the user to be created
   * @param email The fontys email of the user
   * @param photo Photograph
   * @param ipaddress The current IP address, which the client can be identified with. Default value = \&quot;deactivate\&quot; - removes the currently set IP address, this way marking him as not online and delegating all communications through push notifications.
   * @return void
   */
  public void  usersPost (String firstName, String lastName, String accountpcn, String email, String photo, String ipaddress) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'firstName' is set
    if (firstName == null) {
       throw new ApiException(400, "Missing the required parameter 'firstName' when calling usersPost");
    }
    
    // verify the required parameter 'lastName' is set
    if (lastName == null) {
       throw new ApiException(400, "Missing the required parameter 'lastName' when calling usersPost");
    }
    
    // verify the required parameter 'accountpcn' is set
    if (accountpcn == null) {
       throw new ApiException(400, "Missing the required parameter 'accountpcn' when calling usersPost");
    }
    
    // verify the required parameter 'email' is set
    if (email == null) {
       throw new ApiException(400, "Missing the required parameter 'email' when calling usersPost");
    }
    

    // create path and map variables
    String path = "/users".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "photo", photo));
    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      if (firstName != null) {
        builder.addTextBody("firstName", ApiInvoker.parameterToString(firstName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (lastName != null) {
        builder.addTextBody("lastName", ApiInvoker.parameterToString(lastName), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (accountpcn != null) {
        builder.addTextBody("accountpcn", ApiInvoker.parameterToString(accountpcn), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (email != null) {
        builder.addTextBody("email", ApiInvoker.parameterToString(email), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (ipaddress != null) {
        builder.addTextBody("ipaddress", ApiInvoker.parameterToString(ipaddress), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      RequestBody requestBody = new MultipartBuilder()
        .type(MultipartBuilder.FORM)
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"title\""),
            RequestBody.create(null, "Square Logo"))
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"image\""),
            RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
        .build();
        */
      postBody = "";
    } else {
      // normal form params
      RequestBody formBody = new FormEncodingBuilder()
      .add("firstName", ApiInvoker.parameterToString(firstName))
      .add("lastName", ApiInvoker.parameterToString(lastName))
      .add("accountpcn", ApiInvoker.parameterToString(accountpcn))
      .add("email", ApiInvoker.parameterToString(email))
      .add("ipaddress", ApiInvoker.parameterToString(ipaddress))
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get user by pcn
   * Get a user by pcn
   * @param pcn User ID
   * @return User
   */
  public User  usersPcnGet (String pcn) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnGet");
    }
    

    // create path and map variables
    String path = "/users/{pcn}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      RequestBody requestBody = new MultipartBuilder()
        .type(MultipartBuilder.FORM)
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"title\""),
            RequestBody.create(null, "Square Logo"))
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"image\""),
            RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
        .build();
        */
      postBody = "";
    } else {
      // normal form params
      RequestBody formBody = new FormEncodingBuilder()
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (User) ApiInvoker.deserialize(response, "", User.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update user
   * Update user information, which is stored on top of the existing data in the Fontys system.
   * @param pcn User ID
   * @param photo Photograph
   * @return void
   */
  public void  usersPcnPut (String pcn, String photo) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnPut");
    }
    

    // create path and map variables
    String path = "/users/{pcn}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "photo", photo));
    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      /*
      RequestBody requestBody = new MultipartBuilder()
        .type(MultipartBuilder.FORM)
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"title\""),
            RequestBody.create(null, "Square Logo"))
        .addPart(
            Headers.of("Content-Disposition", "form-data; name=\"image\""),
            RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
        .build();
        */
      postBody = "";
    } else {
      // normal form params
      RequestBody formBody = new FormEncodingBuilder()
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return ;
      }
      else {
        return ;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

