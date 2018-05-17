import java.util.*;

public class LCM {
    private static long lcm(int a, int b) {
        return ((long) a * b) / (long)gcd(a,b);
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        int aPrime = a % b;

        return gcd(b, aPrime);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        System.out.println(lcm(a, b));
    }
}
