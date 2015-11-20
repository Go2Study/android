package Go2Study.Api;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Invoker.Pair;

import Go2Study.Models.*;

import java.util.*;

import Go2Study.Models.Event;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class UserEventsApi {
  String basePath = "https://api.go2study.lol/1.0";
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
   * Get list of all events for this user
   * 
   * @param pcn User ID
   * @param query Non-specific query search term. Can be used for filtering the results by name of group, event, pcn, class, department &amp; more.
   * @return List<Event>
   */
   public List<Event>  usersPcnEventsGet (Callback callback, String pcn, String query) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnEventsGet");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/events".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, callback);
      if(response != null){
        return (List<Event>) ApiInvoker.deserialize(response, "array", Event.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
}
  
  /**
   * Update information about an existing event, which the user is part of
   * 
   * @param pcn User ID
   * @param id ID of unspecified type. Used for events/groups identification.
   * @param name Name of group/event
   * @return void
   */
   public void  usersPcnEventsIdPut (Callback callback, String pcn, Integer id, String name) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnEventsIdPut");
    }
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling usersPcnEventsIdPut");
    }
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling usersPcnEventsIdPut");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/events/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
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
      .add("name", ApiInvoker.parameterToString(name))
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, postBody, headerParams, formParams, contentType, callback);
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
  
  /**
   * Remove user from favourites list
   * 
   * @param pcn User ID
   * @param id ID of unspecified type. Used for events/groups identification.
   * @return void
   */
   public void  usersPcnEventsIdDelete (Callback callback, String pcn, Integer id) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnEventsIdDelete");
    }
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling usersPcnEventsIdDelete");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/events/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType, callback);
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
  
}

