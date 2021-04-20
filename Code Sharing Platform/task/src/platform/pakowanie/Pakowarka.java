package platform.pakowanie;

import platform.entity.Informacja;

public class Pakowarka {

    Informacja  inf;
    String strona;
    //--------------------------------------
    public Pakowarka(Informacja inf) {
        this.inf = inf;
        pakuj();
    }
    //--------------------------------------
    public void pakuj() {
        String przed;
        String po;
        przed="<html>\n" +
                "<head>\n" +
                "    <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <pre>";


        po="</pre>\n" +
                "</body>\n" +
                "</html>";
        strona=przed+inf.getKod()+po;

    }
    //--------------------------------------

    public Informacja getInf() {
        return inf;
    }

    public String getStrona() {
        return strona;
    }


    //--------------------------------------

}
