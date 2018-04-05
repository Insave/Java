// Author: Patrick Fief
// Course: Java Programming
// Program Set 1 #2
// References: 

import java.util.Scanner;

public class Prob1089PF{

   public static void main (String[] args){
	  //Initial prompt of user input
      Scanner reader = new Scanner(System.in);
      System.out.println("Enter a three digit number, the first and last digit differing by at least 2: ");
	  String sNumber = reader.nextLine();
	  int valid = validateEntered(sNumber);
	  
	  //Input validation
	  while(!(valid == 0)){
		  if(valid == 1) System.out.println("The number must be 3 digits...");
		  if(valid == 2)System.out.println("The first and last digit must differ by at least 2...");
		  //System.out.println("Enter a three digit number, the first and last digit differing by at least 2: ");
		  sNumber = reader.nextLine();
		  valid = validateEntered(sNumber);
	  }
	  
	  //String conversion and follow up display of information
	  int number = Integer.parseInt(sNumber);
	  String sReverse = reverseString(sNumber);
	  int reverse = Integer.parseInt(sReverse);
	  
      System.out.println("Your number: " + sNumber);
	  System.out.println("Your number reversed: " + sReverse);
	  
	  int difference = number > reverse? number - reverse : reverse - number;
	  int revDifference = Integer.parseInt(reverseString(String.valueOf(difference)));
      System.out.println("Difference: " + difference);
	  System.out.println("Difference reversed: " + revDifference);
	  System.out.println("Sum of the difference and difference reversed: " + (difference + revDifference));
   }
   
   //Helper method to reverse a string parameter
   public static String reverseString(String str){
	   String reverse = "";
	   for(int i = str.length() - 1; i >= 0; i--)
       {
			reverse = reverse + str.charAt(i);
       }
	   return reverse;
   }
   
   //Helper method to determine if the user entered valid data
   public static int validateEntered(String value){
	   if(!(value.length() == 3)) return 1;
	   int first = Character.getNumericValue(value.charAt(0));
	   int last = Character.getNumericValue(value.charAt(2));
	   if(first - last >= 2 || last - first >= 2) return 0;
	   return 2;
   }
}
