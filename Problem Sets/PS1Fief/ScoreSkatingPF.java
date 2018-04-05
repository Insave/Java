// Author: Patrick Fief
// Course: Java Programming
// Program Set 1 #3
// References:

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class ScoreSkatingPF {

    public static void main (String[] args) {
        //Initial prompt of user input
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter 12 judge scores: ");
        String sScores = reader.nextLine();
        int valid = validateEntered(sScores);

        //Input validation
        while (!(valid == 0)) {
            if (valid == 1) System.out.println("There must be 12 scores...");
            sScores = reader.nextLine();
            valid = validateEntered(sScores);
        }
        reader.close();
        reader = new Scanner(sScores);
        ArrayList<Integer> scores = new ArrayList<Integer>();
        
        while (reader.hasNextInt()){
            scores.add(reader.nextInt());
        }
        
        //Remove enough scores randomly to end with 9 scores left
        while (scores.size() != 9){
            scores.remove((int)(Math.random() * scores.size()));
        }
        
        System.out.print("Nine randomly selected scores: ");
        for(int i : scores) {
            int score = i;
            System.out.print(i + " ");
        }
        
        //Remove the highest and lowest without sorting the original list
        ArrayList<Integer> sortScores = new ArrayList<Integer>(scores);
        Collections.sort(sortScores);
        scores.remove(scores.indexOf(sortScores.get(0)));
        scores.remove(scores.indexOf(sortScores.get(sortScores.size()-1)));
        
        System.out.print("\nList of scores minus the highest and lowest: ");
        int total = 0;
        for(int i : scores) {
            int score = i;
            System.out.print(i + " ");
            total += i;
        }
        
        //Average the scores
        double avg = total / (double) scores.size();
        System.out.println("\nFinal score: " + String.format("%.3f", avg));
        
    }

//Helper method to determine if the user entered valid data
    public static int validateEntered(String value) {
        int numScores = 0;
        Scanner numScan = new Scanner(value);
        while (numScan.hasNextInt()){
            numScores++;
            numScan.nextInt();
        }
        if (numScores == 12) return 0;
        return 1;
    }
}

