package FontysICT.Api;

import FontysICT.Invoker.ApiException;
import FontysICT.Invoker.ApiInvoker;
import FontysICT.Invoker.Pair;

import FontysICT.Models.*;

import java.util.*;

import FontysICT.Models.RoomOccupancy;
import java.util.Date;


import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class RoomsApi {
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
   * List all the rooms, and it they are occupied.
   * Currently only returns rooms from the schedule (since we don&#39;t have a database with all rooms yet)
   * @param date Specify the date you want to compute the date for. (2015-07-28) [default = today]
   * @return List<RoomOccupancy>
   */
  public List<RoomOccupancy>  roomsOccupancy (Date date) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'date' is set
    if (date == null) {
       throw new ApiException(400, "Missing the required parameter 'date' when calling roomsOccupancy");
    }
    

    // create path and map variables
    String path = "/rooms/occupancy/{date}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "date" + "\\}", apiInvoker.escapeString(date.toString()));

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
        return (List<RoomOccupancy>) ApiInvoker.deserialize(response, "array", RoomOccupancy.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}

