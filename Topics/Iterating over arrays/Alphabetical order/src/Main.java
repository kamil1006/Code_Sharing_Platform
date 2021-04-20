import java.util.Scanner;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
         String input=scanner.nextLine();


        StringTokenizer tokenizer = new StringTokenizer(input);
       int count= tokenizer.countTokens();
       String[] lista = new String[count];
        for(int i=0;i<count;i++){

            lista[i]=tokenizer.nextToken();
           }


        boolean b=true;
        for(int i=0;i<count;i++){
            if(i+1<count){
                int k=lista[i].compareTo(lista[i+1]);
                if (k>0) b=false;
            }
        }
        System.out.println(b);
    }
}
