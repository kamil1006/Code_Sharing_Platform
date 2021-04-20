package platform.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.CodeSharingPlatform;
import platform.entity.Informacja;
import platform.entity.Wpis;
import platform.entity.Wpis2;
import platform.repository.Wpis2Repository;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CodeWebController {

    @Autowired
    Wpis2Repository repository;



    @GetMapping(path="/code/new")
    public String kodeNew(HttpServletResponse response, Model model) {
        response.addHeader("Content-Type", "text/html");
        model.addAttribute("powitanie", "Podaj tekst do zapisania:");
        model.addAttribute("informacyjka",new Informacja(""));
       // System.out.println("dede");
       return "kodeNew";
    }
    //-----------------------------------------


    //-----------------------------------------
    @GetMapping(path="/code/new1")
    public String dodawanie() {
        //System.out.println("dodawanie");
        return "dodaj";
    }
    //-----------------------------------------
    @GetMapping("/code")
    public String pakzuje(Model model) {
        LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
        CodeSharingPlatform.kot.setLocalDateTime(localDateTime);
        model.addAttribute("informacyjka1", CodeSharingPlatform.kot.getKod());

        model.addAttribute("czasWyswietlania", CodeSharingPlatform.kot.pokazCzas());
        return "pokaz";
    }
    //-----------------------------------------
    @GetMapping("/code/latest")
    public String pakzujeLatesty(Model model) {
        LocalDateTime localDateTime = LocalDateTime.now(); //get current date time
        CodeSharingPlatform.kot.setLocalDateTime(localDateTime);

        //--------------------- h2 h2 --------------------

        // List<Wpis2> odpowiedz2= repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        //List<Wpis2> odpowiedz2= repository.findTop10ByOrderByIdDesc();
        List<Wpis2> odpowiedz2=repository.findTop10ByOrderByIdDescBezRestrykcji();

        List<Wpis> odpowiedz = new LinkedList<>();
        for(Wpis2 w:odpowiedz2){
            odpowiedz.add(new Wpis(w.getCode(), w.getDate()));
        }

        //--------------------- h2 h2 --------------------

        List<Wpis> zbior = odpowiedz;

       // List<Wpis> zbior = CodeSharingPlatform.zbiorKotow.podajListeWpisow();

        model.addAttribute("informacyjki", zbior);

        model.addAttribute("czasWyswietlania", CodeSharingPlatform.kot.pokazCzas());
        return "pokazLatest";
    }

    //-----------------------------------------
    @GetMapping("/code/{id}")

    public String apiKode2(@PathVariable("id") /*int*/ String id, Model model) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","application/json");

        //--------------------- h2 h2 --------------------
        //Wpis2 wpis2  = repository.findById(id);
        //Wpis w= new Wpis(wpis2.getCode(), wpis2.getDate());

        Wpis2 wpis2  = repository.findByLinq(id);
        Wpis w = null;
        String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        boolean kasowanie=false;
        boolean czyCzasWyzerowany=false;
        boolean czyWyswietleniaWyzerowane=false;

        try{
            boolean restrykcje= wpis2.isRestrykcje();

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
//            return ResponseEntity.notFound()
//                    .headers(responseHeaders).build();
        }



        //--------------------- h2 h2 --------------------

        if(!kasowanie && w!=null ) {

            System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(kasowanie);
            System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(wpis2);
            System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(w);
            System.out.println("+++$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");







            //  Informacja informacja = CodeSharingPlatform.zbiorKotow.getInformacja(id);
            //Wpis w= new Wpis(informacja.getKod(),informacja.getTimeFormatted());
            Gson gson = new Gson();
            model.addAttribute("informacyjka1", w.getCode());
            model.addAttribute("czasWyswietlania", w.getDate());
            String tt = "t";
            if (w.getTime() > 0) {
                model.addAttribute("sekundy", w.getTime());
                tt = "tt";
            }
            model.addAttribute("tt", tt);
            String ww = "w";
            if (w.getViews() > 0 || czyWyswietleniaWyzerowane==true) {
                model.addAttribute("odslony", w.getViews());
                ww = "ww";
            }
            model.addAttribute("ww", ww);

            return "pokaz";

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        }




    }
    //-----------------------------------------


}
