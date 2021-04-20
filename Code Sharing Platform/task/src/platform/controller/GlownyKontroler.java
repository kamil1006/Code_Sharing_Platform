package platform.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.CodeSharingPlatform;
import platform.entity.Informacja;
import platform.entity.Wpis;
import platform.entity.Wpis2;
import platform.pakowanie.Pakowarka;
import platform.repository.Wpis2Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class GlownyKontroler {

  //Informacja kot;
/*
    public GlownyKontroler() {
        kot=new Informacja("public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}");
    }

 */

    @Autowired
    Wpis2Repository repository;


    //-----------------------------------------
    @GetMapping("/code1")
    @ResponseBody
    public String kode() {




        return CodeSharingPlatform.kot.getKod();
    }
    //-----------------------------------------
    @GetMapping("/api/code1")
    @ResponseBody
    public String apiKode() {
      return CodeSharingPlatform.kot.getKodJson();

    }
    //-----------------------------------------
    @GetMapping("/api/code")
    @ResponseBody
    public ResponseEntity<String> apiKode1() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

        CodeSharingPlatform.kot.ustawCzas();
        String kodJson = CodeSharingPlatform.kot.getKodJson();
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(kodJson);



    }

    //-----------------------------------------
    @GetMapping("/code2")
    @ResponseBody
    public ResponseEntity<String> kode1() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","text/html");

        Pakowarka pakowarka= new Pakowarka(CodeSharingPlatform.kot);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(pakowarka.getStrona());



    }


    //-----------------------------------------
    //@PostMapping(path="/code/new")
    public ResponseEntity<String> kodeNewSubmit(@ModelAttribute  Informacja inf, Model model){


        System.out.print("z informacji ---");
        System.out.println( inf.getKodJson());
        System.out.print("z modelu ---");
        System.out.println( model.getAttribute("informacja"));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");
      return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("{}");

    }
    //-----------------------------------------
    //z jsona postman
    @PostMapping(path="api/code/new", consumes = "application/json")
    public  ResponseEntity<String> kodeNewSubmit1(@RequestBody com.fasterxml.jackson.databind.JsonNode inf,Model model){

        System.out.println("++++");
        //System.out.println(inf);
        //System.out.println("++++");
        String s = inf.toString();

        String code = inf.get("code").asText();
        String timeRestrictions = inf.get("time").asText();
        String viewsRestrictions = inf.get("views").asText();

        int tR=0;
        int vR=0;
        boolean restrykcje=false;
        if( !timeRestrictions.equals("null"))
            if(timeRestrictions.length()>0 )
            {  try{tR= Integer.parseInt(timeRestrictions);}catch (Exception e){ } }

        if(!viewsRestrictions.equals("null"))
            if(viewsRestrictions.length()>0 )
        {  try{vR= Integer.parseInt(viewsRestrictions);}catch (Exception e){ } }
        if(tR>0) restrykcje =true;
        if(vR>0) restrykcje=true;

        CodeSharingPlatform.kot.setKod(code);

        Informacja iii= new Informacja(code);
       // CodeSharingPlatform.kot.setKodJson(s);
        int i = CodeSharingPlatform.zbiorKotow.putInformacja(iii);

        //--------------------- h2 h2 --------------------
        List<Wpis2> top1ByOrderByIdDesc = repository.findTop1ByOrderByIdDesc();
        int nr;
        if (top1ByOrderByIdDesc.isEmpty())
            nr= 0+1;
        else nr=top1ByOrderByIdDesc.get(0).getId()+1;

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        Wpis2 w;
        w=new Wpis2(iii.getKod(),iii.getTimeFormatted());
        w.setLinq(randomUUIDString);
        w.setRestrykcje(restrykcje);
        w.setCzasWyswietlania(tR);
        w.setWyswietlenia(vR);

        repository.save(w);

        //int nr =repository.findByDate(iii.getTimeFormatted()).getId();

        //--------------------- h2 h2 --------------------


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

     return ResponseEntity.ok()
                .headers(responseHeaders)
             .body("{\"id\" : \""+randomUUIDString+"\"}");
        //  .body("{\"id\" : \""+nr+"\"}");
            //   .body("{\"id\" : \""+i+"\"}");
    }
    //-----------------------------------------

    /**
     *
     * https://stackoverflow.com/questions/34782025/http-post-request-with-content-type-application-x-www-form-urlencoded-not-workin
     */
    // z formularza
    @PostMapping(path="/code/new",  consumes = "application/x-www-form-urlencoded")
    public   ResponseEntity<String> kodeNewSubmit1(@ModelAttribute Informacja inf) {

        String code = inf.getKod();


        //CodeSharingPlatform.kot.setKod(code);
        //CodeSharingPlatform.kot.setKodJson(inf.getKodJson());
       // CodeSharingPlatform.zbiorKotow.putInformacja(inf);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("{}");

    }
    //-----------------------------------------
    @GetMapping("/api/code/latest")
    @ResponseBody
    public ResponseEntity<String> apiKodeLatest() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

        Map<Integer,Informacja> zbior = CodeSharingPlatform.zbiorKotow.getZbior();

      /*  List<Wpis>
        odpowiedz= CodeSharingPlatform.zbiorKotow.podajListeWpisow();
     */

        /*
        for(Map.Entry<Integer, Informacja> i:zbior.entrySet()){
            String kod = i.getValue().getKod();
            String dataCzas = i.getValue().getTimeFormatted();
            odpowiedz.add(new Wpis(kod,dataCzas));
        }
        */
        //--------------------- h2 h2 --------------------

       // List<Wpis2> odpowiedz2= repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        //List<Wpis2> odpowiedz2= repository.findTop10ByOrderByIdDesc();
        List<Wpis2> odpowiedz2=repository.findTop10ByOrderByIdDescBezRestrykcji();

        List<Wpis> odpowiedz = new LinkedList<>();
        for(Wpis2 w:odpowiedz2){
            odpowiedz.add(new Wpis(w.getCode(), w.getDate(),0,0));
        }

        //--------------------- h2 h2 --------------------
        Gson gson = new Gson();
        String s = gson.toJson(odpowiedz);


       // CodeSharingPlatform.kot.ustawCzas();
       // String kodJson = CodeSharingPlatform.kot.getKodJson();
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(s);
                // .body(kodJson);



    }
//-----------------------------------------
@GetMapping(path="/api/code/{id}")
@ResponseBody
public ResponseEntity<String> apiKode2( @PathVariable("id") /*int*/String id) {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Content-Type","application/json");

   // Informacja informacja = CodeSharingPlatform.zbiorKotow.getInformacja(id);
   // Wpis w= new Wpis(informacja.getKod(),informacja.getTimeFormatted());
    Gson gson= new Gson();
    //--------------------- h2 h2 --------------------
   // Wpis2 wpis2  = repository.findById(id);
    Wpis2 wpis2  = repository.findByLinq(id);
    Wpis w = null;
    String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    boolean kasowanie=false;
    try{
        boolean restrykcje= wpis2.isRestrykcje();
        boolean czyCzasWyzerowany=false;
        boolean czyWyswietleniaWyzerowane=false;
        //*************

        LocalDateTime wpisDT=LocalDateTime.parse(wpis2.getDate(),formatter);
        System.out.println(wpisDT);
        LocalDateTime czasBiezacy=LocalDateTime.now();
       Duration roznicaCzasow=Duration.between(wpisDT,czasBiezacy);
        int czasWyswietlania = wpis2.getCzasWyswietlania();

        if(restrykcje) {
            if(czasWyswietlania>0)
            if(czasWyswietlania-roznicaCzasow.toSeconds()>0){
                int l = (int) (czasWyswietlania - roznicaCzasow.toSeconds());
                wpis2.setCzasWyswietlania(l);
                repository.save(wpis2);
            }
            else {

                czyCzasWyzerowany=true;

                //repository.delete(wpis2);
               // kasowanie=true;
            }


        }
        //*************
        int wyswietlenia = wpis2.getWyswietlenia();
        if(restrykcje){
            if(wyswietlenia==0){

            }else
            if(wyswietlenia>0){
                wyswietlenia--;
                wpis2.setWyswietlenia(wyswietlenia);
                repository.save(wpis2);
                if(wyswietlenia==0) czyWyswietleniaWyzerowane=true;
            }


        }

        //*************
        if(!czyCzasWyzerowany)
        w= new Wpis(wpis2.getCode(), wpis2.getDate(),wpis2.getCzasWyswietlania(),wpis2.getWyswietlenia());
        else {
            repository.delete(wpis2);
            kasowanie=true;
        }

        if(czyWyswietleniaWyzerowane) repository.delete(wpis2);

    }catch (NullPointerException e){
        return ResponseEntity.notFound()
                .headers(responseHeaders).build();
    }

    //--------------------- h2 h2 --------------------
   if(!kasowanie) return ResponseEntity.ok()
            .headers(responseHeaders)
            .body(gson.toJson(w));
   else {
       System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
       System.out.println(kasowanie);
       System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
       System.out.println(wpis2);
       System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
       System.out.println(w);
       System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
       return ResponseEntity.notFound()
               .headers(responseHeaders).build();
   }
}
//-----------------------------------------


//-----------------------------------------

}
