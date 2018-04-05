// Author: Patrick Fief
// Course: Java Programming
// Set 2 Program 3
// References: 

import java.util.*;

public class OOSkunkPF {
    
    public static void main(String[] args) {
        
        //Ask if they want to play again and loop through another game instance
        Scanner input = new Scanner(System.in);
        boolean playing = true;
            do {
                Skunk game =  new Skunk();
                System.out.print("Would you like to play again? (Y/N): ");
                String entered = input.nextLine().toUpperCase();
                if(entered.equals("Y")) playing = true;
                else if (entered.equals("N")) playing = false;
                else System.out.println("Invalid answer.");
            } while (playing);
        
    }
    
    
    public static class Skunk {
        int WIN_POINTS = 100;
        int points[];
        
        public Skunk() {
            points = new int[4];
            int currentPlayer = 0;
            
            while(IsPlaying() == -1) {
                //Print start of turn points
                PrintColumns("Player " + currentPlayer, "Dice", "Roll", "Score");
                PrintColumns("Start of turn", "", "", "" + points[currentPlayer]);
                
                int turnPoints = PlayTurn(currentPlayer); //Play a turn
                
                if(turnPoints == -1) //Player rolled a double skunk
                    points[currentPlayer] = 0;
                else 
                    points[currentPlayer] += turnPoints;
                
                //Print the end turn points
                PrintColumns("End of turn", "", "", "" + points[currentPlayer]);
                System.out.println("\n\n");
                
                currentPlayer++;
                if(currentPlayer == 4) currentPlayer = 0;
            }
            
            System.out.println("Player " + IsPlaying() + " has won the game of skunk!");
            if(IsPlaying() == 0) System.out.println("You won!");
        }
        
        public void PrintColumns(String comment, String die1, String die2, String score) {
            System.out.printf("%-22s%-5s%-5s%-6s\n", comment, die1, die2, score);
        }
        
        public int PlayTurn(int player) {
            int turnPoints = 0;
            int currentRoll = 1;
            boolean wantToRoll = true;
            
            while(wantToRoll) {
                int die1 = RollDie();
                int die2 = RollDie();
                
                turnPoints += die1 + die2;
                //Print out the results of the round of rolling
                PrintColumns("Roll " + currentRoll, "" + die1, "" + die2, "" + (turnPoints + points[player]));
                
                if (die1 == 1 && die2 == 1) return -1; //Player rolled a double skunk
                else if (die1 == 1 || die2 == 1) return 0; //Player rolled a single skunk
                
                
                if(player == 0) {
                    if(!WantsToRoll()) return turnPoints;
                }
                else if (player == 1) {
                    if(currentRoll == 3) return turnPoints;
                } else if (player == 2) {
                    if(turnPoints >= 20) return turnPoints;
                } else if (player == 3 && turnPoints >= 100){ //Player 3 wants to get 100 points in one turn
                    return turnPoints;
                }
                
                currentRoll++;
            }
            return 0;
        }
        
        public int RollDie() {
            return (int) (Math.random() * 6) + 1;
        }
        
        //Ask Player if they want to roll again
        public boolean WantsToRoll() {
            Scanner input = new Scanner(System.in);
            do {
                System.out.print("Would you like to roll again? (Y/N): ");
                String entered = input.nextLine().toUpperCase();
                if(entered.equals("Y")) return true;
                else if (entered.equals("N")) return false;
                System.out.println("Invalid answer.");
            } while (true);
        }
        
        public int IsPlaying() {
            for (int i = 0; i < points.length; i++) {
                if (points[i] >= WIN_POINTS)
                    return i;
            }
            return -1;
        }
    }
}