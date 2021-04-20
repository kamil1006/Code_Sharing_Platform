import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
     Scanner scanner=new Scanner(System.in);

        int M =Math.abs(Integer.parseInt(scanner.nextLine()));

        String input=scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(input);
        int count= tokenizer.countTokens();
        int[] lista = new int[count];
        for(int i=0;i<count;i++){

            lista[i]=Integer.parseInt(tokenizer.nextToken());
        }

        input=scanner.nextLine();
         tokenizer = new StringTokenizer(input);
        int[] lista2 = new int[count];
        for(int i=0;i<count;i++){

            lista2[i]=Integer.parseInt(tokenizer.nextToken());
        }

        int max=1;
        double k=0;
        for (int i=0;i<count;i++){
            if(lista[i]*lista2[i]>k){
              k=  lista[i]*lista2[i];
                max=i+1;
            }
        }
        System.out.println(max);
    }
}
