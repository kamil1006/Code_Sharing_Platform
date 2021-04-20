package platform.service;

import org.springframework.stereotype.Component;
import platform.entity.Informacja;
import platform.entity.Wpis;

import java.util.*;

@Component
public class Informacje {

   int licznik;
   Map<Integer,Informacja> zbior;


    public  Informacje(){
        this.zbior = new LinkedHashMap<>();

        licznik=0;




    }



    public Map<Integer, Informacja> getZbior() {
        return zbior;
    }



    public int getLicznik() {
        return licznik;
    }

    public int putInformacja(Informacja inf){
        licznik++;
        this.zbior.put(licznik,inf);


        return licznik;

    }
    public Informacja getInformacja(int t){
      Informacja a = null;
       for(Map.Entry<Integer, Informacja> i:zbior.entrySet()){
           if(i.getKey()==t) {
              a = i.getValue();
           }
        }
       if(a.equals(null)) return null;
       else 
        return a;
    }
    //-------------------------------------
    public List<Wpis> podajListeWpisow(){
        LinkedList<Wpis> lista= new LinkedList<>();
        int x=0;



        for(Map.Entry<Integer, Informacja> i:zbior.entrySet()){
            lista.add(new Wpis(i.getValue().getKod(),i.getValue().getTimeFormatted()));


        }

        List<Wpis> listaBack= new ArrayList<>();
        for(int i=lista.size()-1;i>=0;i--){
            listaBack.add(lista.get(i));
            x++;
            if(x>=10) break;
        }


        return listaBack;
    }
    //-------------------------------------

}
