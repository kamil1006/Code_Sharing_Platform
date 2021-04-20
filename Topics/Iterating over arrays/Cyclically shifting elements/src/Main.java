import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        Deque<Integer> stack = new ArrayDeque<>();

        int ilosc= scanner.nextInt();
        int[] tab= new int[ilosc];

        for(int i =0;i<ilosc;i++){
                tab[i]=scanner.nextInt();
                stack.add(tab[i]);


        }

        System.out.print(stack.pollLast()+" ");
        int size = stack.size();
        for(int i = 0; i< size; i++)
            System.out.print(stack.pollFirst()+" ");




    }
}