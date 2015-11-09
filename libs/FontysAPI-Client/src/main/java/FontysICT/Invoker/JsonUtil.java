package FontysICT.Invoker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import FontysICT.Models.*;

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
    
    if ("Building".equalsIgnoreCase(className)) {
      return new TypeToken<List<Building>>(){}.getType();
    }
    
    if ("Calendar".equalsIgnoreCase(className)) {
      return new TypeToken<List<Calendar>>(){}.getType();
    }
    
    if ("CalendarItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<CalendarItem>>(){}.getType();
    }
    
    if ("Group".equalsIgnoreCase(className)) {
      return new TypeToken<List<Group>>(){}.getType();
    }
    
    if ("Person".equalsIgnoreCase(className)) {
      return new TypeToken<List<Person>>(){}.getType();
    }
    
    if ("InzetItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<InzetItem>>(){}.getType();
    }
    
    if ("WirelessLocation".equalsIgnoreCase(className)) {
      return new TypeToken<List<WirelessLocation>>(){}.getType();
    }
    
    if ("Mapinfo".equalsIgnoreCase(className)) {
      return new TypeToken<List<Mapinfo>>(){}.getType();
    }
    
    if ("Mapcoordinate".equalsIgnoreCase(className)) {
      return new TypeToken<List<Mapcoordinate>>(){}.getType();
    }
    
    if ("Statistics".equalsIgnoreCase(className)) {
      return new TypeToken<List<Statistics>>(){}.getType();
    }
    
    if ("Geocoordinate".equalsIgnoreCase(className)) {
      return new TypeToken<List<Geocoordinate>>(){}.getType();
    }
    
    if ("Dimension".equalsIgnoreCase(className)) {
      return new TypeToken<List<Dimension>>(){}.getType();
    }
    
    if ("Floor".equalsIgnoreCase(className)) {
      return new TypeToken<List<Floor>>(){}.getType();
    }
    
    if ("Image".equalsIgnoreCase(className)) {
      return new TypeToken<List<Image>>(){}.getType();
    }
    
    if ("Accesspoint".equalsIgnoreCase(className)) {
      return new TypeToken<List<Accesspoint>>(){}.getType();
    }
    
    if ("Locationfilterregion".equalsIgnoreCase(className)) {
      return new TypeToken<List<Locationfilterregion>>(){}.getType();
    }
    
    if ("Apinterface".equalsIgnoreCase(className)) {
      return new TypeToken<List<Apinterface>>(){}.getType();
    }
    
    if ("NewsFeed".equalsIgnoreCase(className)) {
      return new TypeToken<List<NewsFeed>>(){}.getType();
    }
    
    if ("NewsItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<NewsItem>>(){}.getType();
    }
    
    if ("KeyValuePair[String,String]".equalsIgnoreCase(className)) {
      return new TypeToken<List<KeyValuePair>>(){}.getType();
    }
    
    if ("RoomOccupancy".equalsIgnoreCase(className)) {
      return new TypeToken<List<RoomOccupancy>>(){}.getType();
    }
    
    if ("Schedule".equalsIgnoreCase(className)) {
      return new TypeToken<List<Schedule>>(){}.getType();
    }
    
    if ("ScheduleItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<ScheduleItem>>(){}.getType();
    }
    
    if ("ScheduleQueryItem".equalsIgnoreCase(className)) {
      return new TypeToken<List<ScheduleQueryItem>>(){}.getType();
    }
    
    if ("Period".equalsIgnoreCase(className)) {
      return new TypeToken<List<Period>>(){}.getType();
    }
    
    return new TypeToken<List<Object>>(){}.getType();
  }

  public static Type getTypeForDeserialization(Class cls) {
    String className = cls.getSimpleName();
    
    if ("Building".equalsIgnoreCase(className)) {
      return new TypeToken<Building>(){}.getType();
    }
    
    if ("Calendar".equalsIgnoreCase(className)) {
      return new TypeToken<Calendar>(){}.getType();
    }
    
    if ("CalendarItem".equalsIgnoreCase(className)) {
      return new TypeToken<CalendarItem>(){}.getType();
    }
    
    if ("Group".equalsIgnoreCase(className)) {
      return new TypeToken<Group>(){}.getType();
    }
    
    if ("Person".equalsIgnoreCase(className)) {
      return new TypeToken<Person>(){}.getType();
    }
    
    if ("InzetItem".equalsIgnoreCase(className)) {
      return new TypeToken<InzetItem>(){}.getType();
    }
    
    if ("WirelessLocation".equalsIgnoreCase(className)) {
      return new TypeToken<WirelessLocation>(){}.getType();
    }
    
    if ("Mapinfo".equalsIgnoreCase(className)) {
      return new TypeToken<Mapinfo>(){}.getType();
    }
    
    if ("Mapcoordinate".equalsIgnoreCase(className)) {
      return new TypeToken<Mapcoordinate>(){}.getType();
    }
    
    if ("Statistics".equalsIgnoreCase(className)) {
      return new TypeToken<Statistics>(){}.getType();
    }
    
    if ("Geocoordinate".equalsIgnoreCase(className)) {
      return new TypeToken<Geocoordinate>(){}.getType();
    }
    
    if ("Dimension".equalsIgnoreCase(className)) {
      return new TypeToken<Dimension>(){}.getType();
    }
    
    if ("Floor".equalsIgnoreCase(className)) {
      return new TypeToken<Floor>(){}.getType();
    }
    
    if ("Image".equalsIgnoreCase(className)) {
      return new TypeToken<Image>(){}.getType();
    }
    
    if ("Accesspoint".equalsIgnoreCase(className)) {
      return new TypeToken<Accesspoint>(){}.getType();
    }
    
    if ("Locationfilterregion".equalsIgnoreCase(className)) {
      return new TypeToken<Locationfilterregion>(){}.getType();
    }
    
    if ("Apinterface".equalsIgnoreCase(className)) {
      return new TypeToken<Apinterface>(){}.getType();
    }
    
    if ("NewsFeed".equalsIgnoreCase(className)) {
      return new TypeToken<NewsFeed>(){}.getType();
    }
    
    if ("NewsItem".equalsIgnoreCase(className)) {
      return new TypeToken<NewsItem>(){}.getType();
    }
    
    if ("KeyValuePair".equalsIgnoreCase(className)) {
      return new TypeToken<KeyValuePair>(){}.getType();
    }
    
    if ("RoomOccupancy".equalsIgnoreCase(className)) {
      return new TypeToken<RoomOccupancy>(){}.getType();
    }
    
    if ("Schedule".equalsIgnoreCase(className)) {
      return new TypeToken<Schedule>(){}.getType();
    }
    
    if ("ScheduleItem".equalsIgnoreCase(className)) {
      return new TypeToken<ScheduleItem>(){}.getType();
    }
    
    if ("ScheduleQueryItem".equalsIgnoreCase(className)) {
      return new TypeToken<ScheduleQueryItem>(){}.getType();
    }
    
    if ("Period".equalsIgnoreCase(className)) {
      return new TypeToken<Period>(){}.getType();
    }
    
    return new TypeToken<Object>(){}.getType();
  }

};
