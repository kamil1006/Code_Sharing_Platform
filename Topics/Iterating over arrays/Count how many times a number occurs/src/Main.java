import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);

        int size= scanner.nextInt();

       int[] tablica = new int[size];
        for( int i=0;i<size;i++){
            tablica[i]= scanner.nextInt();
        }
        String text = scanner.nextLine();
        int liczba=scanner.nextInt();



       int licznik =0;
        for( int i=0;i<size;i++){
          if(tablica[i]==liczba) licznik++;

        }
        System.out.println(licznik);

    }
}