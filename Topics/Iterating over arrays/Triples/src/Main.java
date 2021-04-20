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
        int trojaczki=0;
        if(ilosc>=3){
            for( int i=2;i<ilosc;i++)
            {
                if(tablica[i-2]+1==tablica[i-1] &&tablica[i-1]+1==tablica[i]){
                    trojaczki++;
                }
            }

        }

        System.out.println(trojaczki);
    }



}