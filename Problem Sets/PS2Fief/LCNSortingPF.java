// Author: Patrick Fief
// Course: Java Programming
// Set 2 Program 5
// References: https://regexr.com/ for testing regex sequences

import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LCNSortingPF {
    
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Enter filename: ");
        Path path = Paths.get(stdIn.nextLine());
        System.out.println("");
        
        try (Scanner scFile = new Scanner(path)) {
            
            int inc = 1;
            ArrayList<BookLCN> LCNs = new ArrayList<BookLCN>();
            
            while(scFile.hasNext()) {
                String fileLCN = scFile.nextLine(); //Get the next LCN
                String[] curLine = fileLCN.split(" ");
                ArrayList<BookLCN> lineLCNs = new ArrayList<BookLCN>();
                
                for (int i = 0; i < curLine.length; i++) {
                    //Separate the LCN into parts and store it
                    LCNSortingPF.BookLCN currentLCN = new LCNSortingPF.BookLCN(GetCallLetters(curLine[i]), GetCallNumbers(curLine[i]), GetCutter(curLine[i]));
                    lineLCNs.add(currentLCN);
                    LCNs.add(currentLCN);
                }
                
                //Sort the line of LCNs
                Collections.sort(lineLCNs);
                //Print the line of LCNs
                System.out.print("Set " + inc++ + ": ");
                for (int i = 0; i < lineLCNs.size(); i++) {
                    System.out.print(lineLCNs.get(i) + " ");
                }
                System.out.println("");
            }
            System.out.println("\nAll " + LCNs.size() + " LCNs in order: \n");
            
            //Sort all LCNs and print them
            Collections.sort(LCNs);
            //Print all LCNs
            for (int i = 0; i < LCNs.size(); i++) {
                System.out.print(LCNs.get(i) + " ");
            }
            
        } catch(IOException e) {
            System.out.println("Invalid filename.");
        }
    }
    
    public static String GetCallLetters(String lcn) {
        return lcn.substring(0, 2);
    }
    
    public static String GetCallNumbers(String lcn) {
        String work = lcn.substring(2);
        Pattern pattern = Pattern.compile("[1-9]\\d*(\\.\\d+)?");
        Matcher matcher = pattern.matcher(work);
        matcher.find();
        return matcher.group();
    }
    
    public static String GetCutter(String lcn) {
        String work = lcn.substring(2);
        Pattern pattern = Pattern.compile("[A-Z]\\d*(\\.\\d+)?");
        Matcher matcher = pattern.matcher(work);
        matcher.find();
        return matcher.group();
    }
    
    public static class BookLCN implements Comparable<BookLCN> {
        String callLetters;
        String callNumber;
        String cutter;
        
        public BookLCN (String letters, String number, String cutter) {
            callLetters = letters;
            callNumber = number;
            this.cutter = cutter;
        }
        
        @Override
        public int compareTo(BookLCN other){
            //First compare the call letters
            int c = this.callLetters.compareTo(other.callLetters);
            if(c == 0) //Compare the call number
                c = Double.compare(Double.parseDouble(this.callNumber), Double.parseDouble(other.callNumber)); 
            if(c == 0) { //Compare the cutter letter
                c = Character.valueOf(this.cutter.charAt(0)).compareTo(Character.valueOf(other.cutter.charAt(0)));
            }
            if(c == 0) { //Compare the cutter numbers
                double thisCutter = Double.parseDouble(this.cutter.substring(1));
                double otherCutter = Double.parseDouble(other.cutter.substring(1));
                c = Double.compare(thisCutter, otherCutter);
            }
            return c;
        }
        
        @Override
        public String toString() {
            return String.format(callLetters + callNumber + cutter);
        }
    }
}