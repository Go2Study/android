package Go2Study.Invoker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import Go2Study.Models.*;

public class JsonUtil {
  public static GsonBuilder gsonBuilder;

  static {
    gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  }

  public static Gson getGson() {
    return gsonBuilder.create();
  }

  public static String serialize(Object obj){
    return getGson().toJson(obj);
  }

  public static <T> T deserializeToList(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
  }

  public static <T> T deserializeToObject(String jsonString, Class cls){
    return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
  }

  public static Type getListTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("User".equalsIgnoreCase(className)) {
      return new TypeToken<List<User>>(){}.getType();
    }
    
    if ("Event".equalsIgnoreCase(className)) {
      return new TypeToken<List<Event>>(){}.getType();
    }
    
    if ("Group".equalsIgnoreCase(className)) {
      return new TypeToken<List<Group>>(){}.getType();
    }
    
    if ("Pcnlist".equalsIgnoreCase(className)) {
      return new TypeToken<List<Pcnlist>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("User".equalsIgnoreCase(className)) {
      return new TypeToken<User>(){}.getType();
    }
    
    if ("Event".equalsIgnoreCase(className)) {
      return new TypeToken<Event>(){}.getType();
    }
    
    if ("Group".equalsIgnoreCase(className)) {
      return new TypeToken<Group>(){}.getType();
    }
    
    if ("Pcnlist".equalsIgnoreCase(className)) {
      return new TypeToken<Pcnlist>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};
