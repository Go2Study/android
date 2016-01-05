package Go2Study.Api;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Invoker.Pair;

import Go2Study.Models.*;

import java.util.*;

import Go2Study.Models.Group;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class UserGroupsApi {
  String basePath = "http://go2study.lol";
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
   * Get list of all groups, which you are part of
   * 
   * @param pcn User ID
   * @param query Non-specific query search term. Can be used for filtering the results by name of group, event, pcn, class, department &amp; more.
   * @return List<Group>
   */
   public List<Group>  usersPcnGroupsGet (Callback callback, String pcn, String query) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnGroupsGet");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/groups".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    RequestBody formBody = null;

    
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
      formBody = new FormEncodingBuilder()
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, formBody, headerParams, formParams, contentType, callback);
      if(response != null){
        return (List<Group>) ApiInvoker.deserialize(response, "array", Group.class);
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
   * Remove myself from a group
   * 
   * @param pcn User ID
   * @param groupid ID of unspecified type. Used for events/groups identification.
   * @return void
   */
   public void  usersPcnGroupsGroupidDelete (Callback callback, String pcn, Integer groupid) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnGroupsGroupidDelete");
    }
    
    // verify the required parameter 'groupid' is set
    if (groupid == null) {
       throw new ApiException(400, "Missing the required parameter 'groupid' when calling usersPcnGroupsGroupidDelete");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/groups/{groupid}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString())).replaceAll("\\{" + "groupid" + "\\}", apiInvoker.escapeString(groupid.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    RequestBody formBody = null;

    

    

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
      formBody = new FormEncodingBuilder()
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, formBody, headerParams, formParams, contentType, callback);
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

