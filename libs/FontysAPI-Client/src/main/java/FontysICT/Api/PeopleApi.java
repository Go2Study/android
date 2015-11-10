package FontysICT.Api;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Invoker.Pair;
import FontysICT.Models.Person;


public class PeopleApi {
  String basePath = "https://tas.fhict.nl:443/api/v1";
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
   * List all teachers and staff.
   * This endpoint also supports oData queries for filtering, ordering and paging
   * @return List<Person>
   */
  public List<Person>  peopleList () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/people".replaceAll("\\{format\\}","json");

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
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (List<Person>) ApiInvoker.deserialize(response, "array", Person.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Information about the user who authorized the request.
   * Only works if you requested the personal scope
   * @return Person
   */
  public Person  peopleMe () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/people/me".replaceAll("\\{format\\}","json");

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
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Person) ApiInvoker.deserialize(response, "", Person.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Search for faculty and staff
   * 
   * @param query Part of name/office, start of PersonalTitle/Id/Department
   * @return List<Person>
   */
  public List<Person>  peopleSearch (String query, String accessToken) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'query' is set
    if (query == null) {
       throw new ApiException(400, "Missing the required parameter 'query' when calling peopleSearch");
    }
    

    // create path and map variables
    String path = "/people/search/{query}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "query" + "\\}", apiInvoker.escapeString(query.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Log.v("query params",queryParams);
    Map<String, String> headerParams = new HashMap<String, String>();
    headerParams.put("Authorization", "Bearer " + accessToken);
    Log.v("Authz", headerParams.get("Authorization"));
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    String[] contentTypes = {
      
    };
    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if (contentType.startsWith("multipart/form-data")) {
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      Log.v("invoke response",response);
      if(response != null){
        Log.v("RAW RESPONSE: ", response.toString());
        return (List<Person>) ApiInvoker.deserialize(response, "array", Person.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Information about a specific user
   * 
   * @param id Username of the user
   * @return Person
   */
  public Person  peopleById (String id) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling peopleById");
    }
    

    // create path and map variables
    String path = "/people/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
      // file uploading
      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      

      HttpEntity httpEntity = builder.build();
      postBody = httpEntity;
    } else {
      // normal form params
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
      if(response != null){
        return (Person) ApiInvoker.deserialize(response, "", Person.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

