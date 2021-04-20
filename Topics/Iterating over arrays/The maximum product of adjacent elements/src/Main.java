import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        int ilosc=scanner.nextInt();
        int[] tablica= new int[ilosc];

        for( int i=0;i<ilosc;i++)
        {
            tablica[i]=scanner.nextInt();
        }
        int maxIloczyn=0;
        for( int i=1;i<ilosc;i++)
        {
            if(tablica[i]*tablica[i-1]>maxIloczyn){
                maxIloczyn=tablica[i]*tablica[i-1];
            }
        }
        System.out.println(maxIloczyn);
    }
}