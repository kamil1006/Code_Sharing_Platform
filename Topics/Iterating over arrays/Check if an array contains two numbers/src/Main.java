import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        int n= scanner.nextInt();
        int m= scanner.nextInt();

        String regexy=" ";
        String[] split = line.split(regexy);

        boolean flag=false;
        if(size>1)
        for (int x=1;x<size;x++){
            var b = split[x - 1] ;
            var s = split[x];

            var s1 = Integer.toString(n);
            var s2 = Integer.toString(m);

            if((b.equals(s1) && s.equals(s2)))
                flag=true;
                if(s.equals( s1) && b.equals( s2))
            {

                flag=true;

            }


        }
        System.out.println(flag);


    }
}