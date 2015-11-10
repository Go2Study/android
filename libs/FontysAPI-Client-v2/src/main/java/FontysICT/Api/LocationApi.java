package FontysICT.Api;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Invoker.Pair;

import FontysICT.Models.*;

import java.util.*;

import FontysICT.Models.WirelessLocation;
import FontysICT.Models.Floor;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class LocationApi {
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
   * Request the current user&#39;s location (you&#39;ll need fhict_location)
   * Returns partial data from Cisco MSE for the current user https://developer.cisco.com/site/cmx-mobility-services/documents/api-reference-manual/index.gsp#get-location
   * @return WirelessLocation
   */
  public WirelessLocation  locationCurrent () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/location/current".replaceAll("\\{format\\}","json");

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
        return (WirelessLocation) ApiInvoker.deserialize(response, "", WirelessLocation.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Request the location history for the current user. (you&#39;ll need fhict_location)
   * Returns partial data from Cisco MSE for the current user https://developer.cisco.com/site/cmx-mobility-services/documents/api-reference-manual/index.gsp#get-location-history
   * @return List<WirelessLocation>
   */
  public List<WirelessLocation>  locationHistory () throws ApiException {
    Object postBody = null;
    

    // create path and map variables
    String path = "/location/history".replaceAll("\\{format\\}","json");

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
        return (List<WirelessLocation>) ApiInvoker.deserialize(response, "array", WirelessLocation.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Image of specific floor
   * 
   * @param campus Name of the campus
   * @param building Name of the building
   * @param floor Name of the floor
   * @return Object
   */
  public Object  locationMapImage (String campus, String building, String floor) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'campus' is set
    if (campus == null) {
       throw new ApiException(400, "Missing the required parameter 'campus' when calling locationMapImage");
    }
    
    // verify the required parameter 'building' is set
    if (building == null) {
       throw new ApiException(400, "Missing the required parameter 'building' when calling locationMapImage");
    }
    
    // verify the required parameter 'floor' is set
    if (floor == null) {
       throw new ApiException(400, "Missing the required parameter 'floor' when calling locationMapImage");
    }
    

    // create path and map variables
    String path = "/location/mapimage/{campus}/{building}/{floor}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "campus" + "\\}", apiInvoker.escapeString(campus.toString())).replaceAll("\\{" + "building" + "\\}", apiInvoker.escapeString(building.toString())).replaceAll("\\{" + "floor" + "\\}", apiInvoker.escapeString(floor.toString()));

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
        return (Object) ApiInvoker.deserialize(response, "", Object.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Image of a specific floor
   * 
   * @param source Source as returned from current location
   * @return Object
   */
  public Object  locationMapImage_1 (String source) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'source' is set
    if (source == null) {
       throw new ApiException(400, "Missing the required parameter 'source' when calling locationMapImage_1");
    }
    

    // create path and map variables
    String path = "/location/mapimage/{source}.jpg".replaceAll("\\{format\\}","json").replaceAll("\\{" + "source" + "\\}", apiInvoker.escapeString(source.toString()));

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
        return (Object) ApiInvoker.deserialize(response, "", Object.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Image of a specific floor
   * 
   * @param source Source as returned from current location
   * @return Object
   */
  public Object  locationMapImage_2 (String source) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'source' is set
    if (source == null) {
       throw new ApiException(400, "Missing the required parameter 'source' when calling locationMapImage_2");
    }
    

    // create path and map variables
    String path = "/location/mapimage/{source}.png".replaceAll("\\{format\\}","json").replaceAll("\\{" + "source" + "\\}", apiInvoker.escapeString(source.toString()));

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
        return (Object) ApiInvoker.deserialize(response, "", Object.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Information about a map, returned from current location
   * 
   * @param campus Name of the campus
   * @param building Name of the building
   * @param floor Name of the floor
   * @return Floor
   */
  public Floor  locationMapInfo (String campus, String building, String floor) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'campus' is set
    if (campus == null) {
       throw new ApiException(400, "Missing the required parameter 'campus' when calling locationMapInfo");
    }
    
    // verify the required parameter 'building' is set
    if (building == null) {
       throw new ApiException(400, "Missing the required parameter 'building' when calling locationMapInfo");
    }
    
    // verify the required parameter 'floor' is set
    if (floor == null) {
       throw new ApiException(400, "Missing the required parameter 'floor' when calling locationMapInfo");
    }
    

    // create path and map variables
    String path = "/location/mapinfo/{campus}/{building}/{floor}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "campus" + "\\}", apiInvoker.escapeString(campus.toString())).replaceAll("\\{" + "building" + "\\}", apiInvoker.escapeString(building.toString())).replaceAll("\\{" + "floor" + "\\}", apiInvoker.escapeString(floor.toString()));

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
        return (Floor) ApiInvoker.deserialize(response, "", Floor.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

