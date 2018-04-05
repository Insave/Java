// Author: Patrick Fief
// Course: Java Programming
// Set 2 Program 2
// References: 

import java.util.Scanner;


public class AbecedarianPF {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a single word: ");
        String word = input.next();
        boolean abc = true;
        char previous = word.charAt(0);
        
        if(word.length() > 1)
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) > previous) {
                    previous = word.charAt(i);
                }
                else
                    abc = false;
            }
        
        if(abc)
            System.out.println("The word " + word + " is abecedarian.");
        else
            System.out.println("The word " + word + " is not abecedarian.");
    }
}