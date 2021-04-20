package platform.entity;



import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Informacja {

    String kod;
    String kodJson;
    String timeRestriction;
    String viewsRestriction;

    LocalDateTime localDateTime;
    private static final String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    // JsonObject jo;


    public String getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(String timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public String getViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(String viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    public Informacja(String kod) {
        this.kod = kod;
        this.localDateTime=LocalDateTime.now();

        Gson gson = new Gson();
        Map<String,String> mapka= new LinkedHashMap<>();//new HashMap<>();
        mapka.put("code",kod);
        mapka.put("date",localDateTime.format(formatter));

        kodJson=gson.toJson(mapka);
      // jo = new JsonParser().parse(kod).getAsJsonObject();


    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
        this.localDateTime=LocalDateTime.now();

        Gson gson = new Gson();
        Map<String,String> mapka= new LinkedHashMap<>();
        mapka.put("code",kod);
        mapka.put("date",localDateTime.format(formatter));

        kodJson=gson.toJson(mapka);

    }

    public String getKodJson() {
        return kodJson;
    }

    public void setKodJson(String kodJson) {
        this.kodJson = kodJson;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
    public String getTimeFormatted(){return localDateTime.format(formatter); }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        Gson gson = new Gson();
        Map<String,String> mapka= new LinkedHashMap<>();
        mapka.put("code",kod);
        mapka.put("date",localDateTime.format(formatter));

        kodJson=gson.toJson(mapka);



    }
    public String pokazCzas(){
        return localDateTime.format(formatter);
    }
    public void ustawCzas(){
        this.localDateTime=LocalDateTime.now();
        Gson gson = new Gson();
        Map<String,String> mapka= new LinkedHashMap<>();
        mapka.put("code",kod);
        mapka.put("date",localDateTime.format(formatter));

        kodJson=gson.toJson(mapka);
    }
}
