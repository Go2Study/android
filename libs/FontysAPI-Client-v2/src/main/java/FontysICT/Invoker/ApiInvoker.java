package FontysICT.Invoker;


import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Callback;

import android.util.Log;


import java.io.File;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.URLEncoder;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonParseException;

public class ApiInvoker {
  private static ApiInvoker INSTANCE = new ApiInvoker();
  private Map<String, String> defaultHeaderMap = new HashMap<String, String>();


  /**
   * ISO 8601 date time format.
   * @see https://en.wikipedia.org/wiki/ISO_8601
   */
  public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  /**
   * ISO 8601 date format.
   * @see https://en.wikipedia.org/wiki/ISO_8601
   */
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  static {
    // Use UTC as the default time zone.
    DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));

    // Set default User-Agent.
    setUserAgent("Android-Java-Swagger");
  }

  public static void setUserAgent(String userAgent) {
    INSTANCE.addDefaultHeader("User-Agent", userAgent);
  }

  public static Date parseDateTime(String str) {
    try {
      return DATE_TIME_FORMAT.parse(str);
    } catch (java.text.ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static Date parseDate(String str) {
    try {
      return DATE_FORMAT.parse(str);
    } catch (java.text.ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String formatDateTime(Date datetime) {
    return DATE_TIME_FORMAT.format(datetime);
  }

  public static String formatDate(Date date) {
    return DATE_FORMAT.format(date);
  }

  public static String parameterToString(Object param) {
    if (param == null) {
      return "";
    } else if (param instanceof Date) {
      return formatDateTime((Date) param);
    } else if (param instanceof Collection) {
      StringBuilder b = new StringBuilder();
      for(Object o : (Collection)param) {
        if(b.length() > 0) {
          b.append(",");
        }
        b.append(String.valueOf(o));
      }
      return b.toString();
    } else {
      return String.valueOf(param);
    }
  }

  /*
    Format to {@code Pair} objects.
  */
  public static List<Pair> parameterToPairs(String collectionFormat, String name, Object value){
    List<Pair> params = new ArrayList<Pair>();

    // preconditions
    if (name == null || name.isEmpty() || value == null) return params;

    Collection valueCollection = null;
    if (value instanceof Collection) {
      valueCollection = (Collection) value;
    } else {
      params.add(new Pair(name, parameterToString(value)));
      return params;
    }

    if (valueCollection.isEmpty()){
      return params;
    }

    // get the collection format
    collectionFormat = (collectionFormat == null || collectionFormat.isEmpty() ? "csv" : collectionFormat); // default: csv

    // create the params based on the collection format
    if (collectionFormat.equals("multi")) {
      for (Object item : valueCollection) {
        params.add(new Pair(name, parameterToString(item)));
      }

      return params;
    }

    String delimiter = ",";

    if (collectionFormat.equals("csv")) {
      delimiter = ",";
    } else if (collectionFormat.equals("ssv")) {
      delimiter = " ";
    } else if (collectionFormat.equals("tsv")) {
      delimiter = "\t";
    } else if (collectionFormat.equals("pipes")) {
      delimiter = "|";
    }

    StringBuilder sb = new StringBuilder() ;
    for (Object item : valueCollection) {
      sb.append(delimiter);
      sb.append(parameterToString(item));
    }

    params.add(new Pair(name, sb.substring(1)));

    return params;
  }

  public ApiInvoker() {
  }

  public static ApiInvoker getInstance() {
    return INSTANCE;
  }


  public void addDefaultHeader(String key, String value) {
    defaultHeaderMap.put(key, value);
  }

  public String escapeString(String str) {
    return str;
  }

  public static Object deserialize(String json, String containerType, Class cls) throws ApiException {
    try{
      if("list".equalsIgnoreCase(containerType) || "array".equalsIgnoreCase(containerType)) {
        return JsonUtil.deserializeToList(json, cls);
      }
      else if(String.class.equals(cls)) {
        if(json != null && json.startsWith("\"") && json.endsWith("\"") && json.length() > 1)
          return json.substring(1, json.length() - 1);
        else
          return json;
      }
      else {
        return JsonUtil.deserializeToObject(json, cls);
      }
    }
    catch (JsonParseException e) {
      throw new ApiException(500, e.getMessage());
    }
  }

  public static String serialize(Object obj) throws ApiException {
    try {
      if (obj != null)
        return JsonUtil.serialize(obj);
      else
        return null;
    }
    catch (Exception e) {
      throw new ApiException(500, e.getMessage());
    }
  }

  public String invokeAPI(String host, String path, String method, List<Pair> queryParams, Object body, Map<String, String> headerParams, Map<String, String> formParams, String contentType, String accessToken, Callback callback) throws ApiException {

    StringBuilder b = new StringBuilder();
    b.append("?");
    if (queryParams != null){
      for (Pair queryParam : queryParams){
        if (!queryParam.getName().isEmpty()) {
          b.append(escapeString(queryParam.getName()));
          b.append("=");
          b.append(escapeString(queryParam.getValue()));
          b.append("&");
        }
      }
    }

    String querystring = b.substring(0, b.length() - 1);
    String url = host + path + querystring;

    HashMap<String, String> headers = new HashMap<String, String>();

    for(String key : headerParams.keySet()) {
      headers.put(key, headerParams.get(key));
    }

    for(String key : defaultHeaderMap.keySet()) {
      if(!headerParams.containsKey(key)) {
        headers.put(key, defaultHeaderMap.get(key));
      }
    }
    headers.put("Accept", "application/json");

    // URL encoded string from form parameters
    String formParamStr = null;

    // for form data
    if ("application/x-www-form-urlencoded".equals(contentType)) {
      StringBuilder formParamBuilder = new StringBuilder();

      // encode the form params
      for (String key : formParams.keySet()) {
        String value = formParams.get(key);
        if (value != null && !"".equals(value.trim())) {
          if (formParamBuilder.length() > 0) {
            formParamBuilder.append("&");
          }
          try {
            formParamBuilder.append(URLEncoder.encode(key, "utf8")).append("=").append(URLEncoder.encode(value, "utf8"));
          }
          catch (Exception e) {
            // move on to next
          }
        }
      }
      formParamStr = formParamBuilder.toString();
    }

    //OkHttpClient and MEDIA TYPE configs
    OkHttpClient client = new OkHttpClient();
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    
    if ("GET".equals(method)) {
          Request request = new Request.Builder()
      .url(url)
      .header("Authorization", "Bearer "+accessToken)
      .build();

      client.newCall(request).enqueue(callback);
    }
    else if ("POST".equals(method)) {


      RequestBody reqBody = RequestBody.create(JSON, formParamStr);
        Request request = new Request.Builder()
      .url(url)
      .header("Authorization", accessToken)
      .post(reqBody)
      .header("Content-Type", contentType)
      .build();

      client.newCall(request).enqueue(callback);
    }
    else if ("PUT".equals(method)) {

      RequestBody reqBody = RequestBody.create(JSON, formParamStr);
        Request request = new Request.Builder()
      .url(url)
      .header("Authorization", accessToken)
      .put(reqBody)
      .header("Content-Type", contentType)
      .build();

      client.newCall(request).enqueue(callback);
    }
    else if ("DELETE".equals(method)) {

      RequestBody reqBody = RequestBody.create(JSON, formParamStr);
      Request request = new Request.Builder()
      .url(url)
      .header("Authorization", accessToken)
      .delete(reqBody)
      .header("Content-Type", contentType)
      .build();

      client.newCall(request).enqueue(callback);
    }
    else if ("PATCH".equals(method)) {
      RequestBody reqBody = RequestBody.create(JSON, formParamStr);
      Request request = new Request.Builder()
      .url(url)
      .header("Authorization", accessToken)
      .patch(reqBody)
      .header("Content-Type", contentType)
      .build();

      client.newCall(request).enqueue(callback);
    }

    return "";
  }
}
