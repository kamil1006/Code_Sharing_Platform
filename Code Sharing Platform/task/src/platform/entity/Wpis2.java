package platform.entity;

import javax.persistence.*;

@Entity
@Table(name="tablica_wpisow")
public class Wpis2 {
    @Id
    @GeneratedValue
    int id;

    @Column
    String code;

    @Column
    String date;

    @Column
    int czasWyswietlania;

    @Column
    int wyswietlenia;

    @Column
    String linq;

    @Column
    boolean restrykcje;



    public Wpis2(){}

    public Wpis2(String code, String date) {
        this.code = code;
        this.date = date;
    }

    public Wpis2(int id, String code, String date) {
        this.id = id;
        this.code = code;
        this.date = date;
    }

    public Wpis2(String code, String date, int czasWyswietlania, int wyswietlenia, String linq) {
        this.code = code;
        this.date = date;
        this.czasWyswietlania = czasWyswietlania;
        this.wyswietlenia = wyswietlenia;
        this.linq = linq;
    }

    public Wpis2(String code, String date, int czasWyswietlania, int wyswietlenia, String linq, boolean restrykcje) {
        this.code = code;
        this.date = date;
        this.czasWyswietlania = czasWyswietlania;
        this.wyswietlenia = wyswietlenia;
        this.linq = linq;
        this.restrykcje = restrykcje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCzasWyswietlania() {
        return czasWyswietlania;
    }

    public void setCzasWyswietlania(int czasWyswietlania) {
        this.czasWyswietlania = czasWyswietlania;
    }

    public int getWyswietlenia() {
        return wyswietlenia;
    }

    public void setWyswietlenia(int wyswietlenia) {
        this.wyswietlenia = wyswietlenia;
    }

    public String getLinq() {
        return linq;
    }

    public void setLinq(String linq) {
        this.linq = linq;
    }
    public boolean isRestrykcje() {
        return restrykcje;
    }

    public void setRestrykcje(boolean restrykcje) {
        this.restrykcje = restrykcje;
    }

    @Override
    public String toString() {
        return "Wpis2{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", czasWyswietlania=" + czasWyswietlania +
                ", wyswietlenia=" + wyswietlenia +
                ", linq='" + linq + '\'' +
                ", restrykcje=" + restrykcje +
                '}';
    }
}
