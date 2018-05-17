import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        if (n <= 1)
            return n;
        
        int minusTwo = 0;
        int minusOne = 1;
        int current = 1;

        for(int i = 2; i < n; i++) {
            int temp = current;
            current = (current + minusOne) % 10;
            minusTwo = minusOne;
            minusOne = temp;
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        scanner.close();
        System.out.println(c);
    }
}

