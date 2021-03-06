import java.util.*;

public class GCD {
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

    System.out.println(gcd(a, b));
  }
}
