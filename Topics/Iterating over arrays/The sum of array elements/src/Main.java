import java.util.Scanner;

class Main {
    public static void main(String[] args) {
          Scanner scanner= new Scanner(System.in);
        int wiersz = scanner.nextInt();
        
        int sum=0;

        while (scanner.hasNextInt()){
            int elem=scanner.nextInt();
            sum+=elem;
        }
        System.out.println(sum);
    }
}
