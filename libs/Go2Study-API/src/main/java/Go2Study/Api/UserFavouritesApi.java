package Go2Study.Api;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import Go2Study.Invoker.ApiException;
import Go2Study.Invoker.ApiInvoker;
import Go2Study.Invoker.Pair;

import Go2Study.Models.*;

import java.util.*;

import Go2Study.Models.Pcnlist;



import java.util.Map;
import java.util.HashMap;
import java.io.File;


public class UserFavouritesApi {
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
   * Get list of all favourite users
   * 
   * @param pcn User ID
   * @return Pcnlist
   */
  public Pcnlist  usersPcnFavouritesGet (String pcn) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnFavouritesGet");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/favourites".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

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
        return (Pcnlist) ApiInvoker.deserialize(response, "", Pcnlist.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Add a user to your list of favourites
   * 
   * @param pcn User ID
   * @param favouritepcn Pcn of the user to be added as favourite
   * @return void
   */
  public void  usersPcnFavouritesPost (String pcn, String favouritepcn) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnFavouritesPost");
    }
    
    // verify the required parameter 'favouritepcn' is set
    if (favouritepcn == null) {
       throw new ApiException(400, "Missing the required parameter 'favouritepcn' when calling usersPcnFavouritesPost");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/favourites".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString()));

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
      if (favouritepcn != null) {
        builder.addTextBody("favouritepcn", ApiInvoker.parameterToString(favouritepcn), ApiInvoker.TEXT_PLAIN_UTF8);
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
      .add("favouritepcn", ApiInvoker.parameterToString(favouritepcn))
      
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
   * Remove user from favourites list
   * 
   * @param pcn User ID
   * @param favpcn Pcn of the user to be removed from favourites
   * @return void
   */
  public void  usersPcnFavouritesFavpcnDelete (String pcn, String favpcn) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'pcn' is set
    if (pcn == null) {
       throw new ApiException(400, "Missing the required parameter 'pcn' when calling usersPcnFavouritesFavpcnDelete");
    }
    
    // verify the required parameter 'favpcn' is set
    if (favpcn == null) {
       throw new ApiException(400, "Missing the required parameter 'favpcn' when calling usersPcnFavouritesFavpcnDelete");
    }
    

    // create path and map variables
    String path = "/users/{pcn}/favourites/{favpcn}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "pcn" + "\\}", apiInvoker.escapeString(pcn.toString())).replaceAll("\\{" + "favpcn" + "\\}", apiInvoker.escapeString(favpcn.toString()));

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
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, postBody, headerParams, formParams, contentType);
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

