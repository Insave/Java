import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        int n = numbers.length;
        if(n == 2) return (long)numbers[0] * (long)numbers[1];
        int largest;
        int large;
        if(numbers[0] > numbers[1]) {
            largest = numbers[0];
            large = numbers[1];
        } else {
            largest = numbers[1];
            large = numbers[0];
        }
        for (int i = 2; i < n; ++i) {
            if(numbers[i] > largest) {
                int temp = largest;
                largest = numbers[i];
                large = temp;
            } else if(numbers[i] > large) {
                large = numbers[i];
            }
        }
        return (long)largest * (long)large;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}