import java.util.Scanner;

public class Fibonacci {
    private static long[] completed;
    
    private static long calc_fib(int n) {
        if(n <= 1)
            return n;
        completed[0] = 0;
        completed[1] = 1;

        for(int i = 2; i < completed.length; i++) {
            completed[i] = completed[i-1] + completed[i - 2];
        }

        return completed[n];
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        
        completed = new long[n+1];
        System.out.println(calc_fib(n));
    }
}
