package Go2Study.Api;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Invoker.Pair;
import Go2Study.Models.Event;


public class EventsApi {
  String basePath = "http://api.go2study.lol:8080";
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
   * Get list of events, according to the search query. Default \&quot;all\&quot; - returns all public events
   * 
   * @param query Non-specific query search term. Can be used for filtering the results by name of group, event, pcn, class, department &amp; more.
   * @return List<Event>
   */
   public List<Event>  eventsGet (Callback callback, String query) throws ApiException { {
    Object postBody = null;
    

    // create path and map variables
    String path = "/events".replaceAll("\\{format\\}","json");

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
   * Create a new event
   * 
   * @param name Name of group/event
   * @param startime Start time of an event
   * @param location Location of the event
   * @param startimeopt Start time of an event
   * @param description Description of event.
   * @return void
   */
   public void  eventsPost (Callback callback, String name, Date startime, String location, Double startimeopt, String description) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'name' is set
    if (name == null) {
       throw new ApiException(400, "Missing the required parameter 'name' when calling eventsPost");
    }
    
    // verify the required parameter 'startime' is set
    if (startime == null) {
       throw new ApiException(400, "Missing the required parameter 'startime' when calling eventsPost");
    }
    
    // verify the required parameter 'location' is set
    if (location == null) {
       throw new ApiException(400, "Missing the required parameter 'location' when calling eventsPost");
    }
    

    // create path and map variables
    String path = "/events".replaceAll("\\{format\\}","json");

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
      if (name != null) {
        builder.addTextBody("name", ApiInvoker.parameterToString(name), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (startime != null) {
        builder.addTextBody("startime", ApiInvoker.parameterToString(startime), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (startimeopt != null) {
        builder.addTextBody("startimeopt", ApiInvoker.parameterToString(startimeopt), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (location != null) {
        builder.addTextBody("location", ApiInvoker.parameterToString(location), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formBody = new FormEncodingBuilder()
      .add("name", ApiInvoker.parameterToString(name))
      .add("startime", ApiInvoker.parameterToString(startime))
      .add("startimeopt", ApiInvoker.parameterToString(startimeopt))
      .add("description", ApiInvoker.parameterToString(description))
      .add("location", ApiInvoker.parameterToString(location))
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, formBody, headerParams, formParams, contentType, callback);
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
   * Get event by id
   * Get an event by id
   * @param id ID of unspecified type. Used for events/groups identification.
   * @return Event
   */
   public Event  eventsIdGet (Callback callback, Integer id) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling eventsIdGet");
    }
    

    // create path and map variables
    String path = "/events/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, formBody, headerParams, formParams, contentType, callback);
      if(response != null){
        return (Event) ApiInvoker.deserialize(response, "", Event.class);
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
   * Update details about an existing event
   * 
   * @param id ID of unspecified type. Used for events/groups identification.
   * @param nameopt Name of group/event (used in PUT calls)
   * @param startimeopt Start time of an event
   * @param durationopt Duration of the event (used in PUT calls)
   * @param description Description of event.
   * @param locationopt Location of the event.
   * @return void
   */
   public void  eventsIdPut (Callback callback, Integer id, String nameopt, Date startimeopt, Double durationopt, String description, String locationopt) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling eventsIdPut");
    }
    

    // create path and map variables
    String path = "/events/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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
      if (nameopt != null) {
        builder.addTextBody("nameopt", ApiInvoker.parameterToString(nameopt), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (startimeopt != null) {
        builder.addTextBody("startimeopt", ApiInvoker.parameterToString(startimeopt), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (durationopt != null) {
        builder.addTextBody("durationopt", ApiInvoker.parameterToString(durationopt), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (description != null) {
        builder.addTextBody("description", ApiInvoker.parameterToString(description), ApiInvoker.TEXT_PLAIN_UTF8);
      }
      
      if (locationopt != null) {
        builder.addTextBody("locationopt", ApiInvoker.parameterToString(locationopt), ApiInvoker.TEXT_PLAIN_UTF8);
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
      formBody = new FormEncodingBuilder()
      .add("nameopt", ApiInvoker.parameterToString(nameopt))
      .add("startimeopt", ApiInvoker.parameterToString(startimeopt))
      .add("durationopt", ApiInvoker.parameterToString(durationopt))
      .add("description", ApiInvoker.parameterToString(description))
      .add("locationopt", ApiInvoker.parameterToString(locationopt))
      
        .build();
      
    }

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, formBody, headerParams, formParams, contentType, callback);
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
   * Remove event from my calendar
   * 
   * @param id ID of unspecified type. Used for events/groups identification.
   * @return void
   */
   public void  eventsIdDelete (Callback callback, Integer id) throws ApiException { {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling eventsIdDelete");
    }
    

    // create path and map variables
    String path = "/events/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

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

