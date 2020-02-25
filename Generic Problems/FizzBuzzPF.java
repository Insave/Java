// Author: Patrick Fief
// Course: Systems Analysis and Design
// Fizzbuzz
// Write a program that prints the numbers from 1 to 100. 
// But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz". 
// For numbers which are multiples of both three and five print "FizzBuzz".
// References: 

public class FizzBuzzPF{

    public static void main(String[] args) {
        for (int i=1; i<=100; i++) {
            if (i%3 == 0)
                System.out.print("Fizz");
            if (i%5 == 0) 
                System.out.print("Buzz");
            if (i%3!=0 && i%5!=0)
                System.out.print(i);
            System.out.print("\n");
        }
    }
}