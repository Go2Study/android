package FontysICT.Api;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Invoker.Pair;

import FontysICT.Models.*;

import java.util.*;

import FontysICT.Models.Calendar;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class CalendarApi {
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
   * Request possible calendar tags for a certain group.
   * 
   * @param kind Group kind (students/staff)
   * @return List<String>
   */
  public List<String>  calendarTags (String accessToken, Callback callback, String kind) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'kind' is set
    if (kind == null) {
       throw new ApiException(400, "Missing the required parameter 'kind' when calling calendarTags");
    }
    

    // create path and map variables
    String path = "/calendar/tags/{kind}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "kind" + "\\}", apiInvoker.escapeString(kind.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, accessToken, callback);
      if(response != null){
        return (List<String>) ApiInvoker.deserialize(response, "array", String.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Request a calendar for a specific group of students/staff members.
   * 
   * @param kind Group kind (students/staff)
   * @param tag Calendar Tag, possible values can be requested with tags/{kind}
   * @return Calendar
   */
  public Calendar  calendarShared (String accessToken, Callback callback, String kind, String tag) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'kind' is set
    if (kind == null) {
       throw new ApiException(400, "Missing the required parameter 'kind' when calling calendarShared");
    }
    
    // verify the required parameter 'tag' is set
    if (tag == null) {
       throw new ApiException(400, "Missing the required parameter 'tag' when calling calendarShared");
    }
    

    // create path and map variables
    String path = "/calendar/{kind}/{tag}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "kind" + "\\}", apiInvoker.escapeString(kind.toString())).replaceAll("\\{" + "tag" + "\\}", apiInvoker.escapeString(tag.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType, accessToken, callback);
      if(response != null){
        return (Calendar) ApiInvoker.deserialize(response, "", Calendar.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

