package FontysICT.Api;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Invoker.Pair;

import FontysICT.Models.*;

import java.util.*;

import FontysICT.Models.ScheduleQueryItem;
import FontysICT.Models.Period;
import FontysICT.Models.Schedule;
import java.util.Date;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class ScheduleApi {
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
   * Get all possible values to query the schedule
   * 
   * @param kind What kind of autocomplete are you requesting [Any,Class,Room,Subject,Teacher]
   * @param filter Filter the possible values [name.ToLower().Contains(filter)]
   * @return List<ScheduleQueryItem>
   */
  public List<ScheduleQueryItem>  scheduleAutoComplete (String kind, String filter) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'kind' is set
    if (kind == null) {
       throw new ApiException(400, "Missing the required parameter 'kind' when calling scheduleAutoComplete");
    }
    

    // create path and map variables
    String path = "/schedule/autocomplete/{kind}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "kind" + "\\}", apiInvoker.escapeString(kind.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "filter", filter));
    

    

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
        return (List<ScheduleQueryItem>) ApiInvoker.deserialize(response, "array", ScheduleQueryItem.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Request school holidays
   * 
   * @return List<Period>
   */
  public List<Period>  scheduleHolidays () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/schedule/holidays".replaceAll("\\{format\\}","json");

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
        return (List<Period>) ApiInvoker.deserialize(response, "array", Period.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get your personal schedule.
   * 
   * @param expandTeacher Expand the teacher information (default = false)
   * @param days Number of days to retrieve (default = 14)
   * @param start First day to request format yyyy-mm-dd (defaults = today)
   * @param startLastMonday Request the schedule starting last monday. This overrides the start parameter (default = false)
   * @return Schedule
   */
  public Schedule  scheduleMe (Boolean expandTeacher, Integer days, Date start, Boolean startLastMonday) throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/schedule/me".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expandTeacher", expandTeacher));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "days", days));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start", start));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "startLastMonday", startLastMonday));
    

    

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
        return (Schedule) ApiInvoker.deserialize(response, "", Schedule.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Request school week numbers
   * 
   * @return List<Period>
   */
  public List<Period>  scheduleWeeks () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/schedule/weeks".replaceAll("\\{format\\}","json");

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
        return (List<Period>) ApiInvoker.deserialize(response, "array", Period.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Query the schedule for a specific class/room/subject/teacher
   * 
   * @param kind What kind of schedule are you requesting [Any,Class,Room,Subject,Teacher,User]
   * @param query The class/room/subject/teacher abbreviation/user
   * @param days Number of days to retrieve (default = 14)
   * @param expandTeacher Expand the teacher information (default = false)
   * @param start First day to request format yyyy-mm-dd (default = today)
   * @param startLastMonday Request the schedule starting last monday (default = false)
   * @return Schedule
   */
  public Schedule  scheduleForQuery (String kind, String query, Integer days, Boolean expandTeacher, Date start, Boolean startLastMonday) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'kind' is set
    if (kind == null) {
       throw new ApiException(400, "Missing the required parameter 'kind' when calling scheduleForQuery");
    }
    
    // verify the required parameter 'query' is set
    if (query == null) {
       throw new ApiException(400, "Missing the required parameter 'query' when calling scheduleForQuery");
    }
    

    // create path and map variables
    String path = "/schedule/{kind}/{query}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "kind" + "\\}", apiInvoker.escapeString(kind.toString())).replaceAll("\\{" + "query" + "\\}", apiInvoker.escapeString(query.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    // header params
    Map<String, String> headerParams = new HashMap<String, String>();
    // form params
    Map<String, String> formParams = new HashMap<String, String>();

    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "days", days));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "expandTeacher", expandTeacher));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "start", start));
    
    queryParams.addAll(ApiInvoker.parameterToPairs("", "startLastMonday", startLastMonday));
    

    

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
        return (Schedule) ApiInvoker.deserialize(response, "", Schedule.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

