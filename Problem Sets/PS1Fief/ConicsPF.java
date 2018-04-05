// Author: Patrick Fief
// Course: Java Programming
// Program Set 1 #4
// References: http://www.purplemath.com/
// https://stackoverflow.com/questions/8895337/how-do-i-limit-the-number-of-decimals-printed-for-a-double

import java.util.Scanner;
import java.text.DecimalFormat;

public class ConicsPF {

    public static void main (String[] args) {
        boolean isRunning = true;
        do {
            //Initial prompt of user input
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter the coefficients for: Ax^2 + Bxy + Cy^2 + Dx + Ey + F = 0: ");
            String sCoefficients = reader.nextLine();
            
            int valid = validateEntered(sCoefficients, 'n');
            //Input validation
            while (!(valid == 0)) {
                if (valid == 1) System.out.println("There must be 6 coefficients...");
                sCoefficients = reader.nextLine();
                valid = validateEntered(sCoefficients, 'n');
            }
            
            //Parse the string into an int array
            String[] splitCo = sCoefficients.split(" ");
            int[] coeff = new int[6];
            for(int index = 0; index < splitCo.length; index++) {
                coeff[index] = Integer.parseInt(splitCo[index]);
            }
            
            //A = C and B = 0 :Circle 
            if(coeff[0] == coeff[2] && coeff[1] == 0) {
                //Divide all terms by major coefficient
                for(int index = coeff.length-1; index >= 0; index--) {
                    coeff[index] /= coeff[0];
                }
                //Move the last coefficient to the other side and add the squared half of D and E
                double radius = (double)-coeff[5] + (Math.pow(coeff[3]*.5, 2) + Math.pow(coeff[4]*.5, 2));
                
                System.out.println("\nConic: Circle");
                DecimalFormat numberformat = new DecimalFormat("#.#");
                System.out.println(String.format("Center: (%s, %s)", numberformat.format(-coeff[3]*.5), numberformat.format(-coeff[4]*.5)));
                System.out.println(String.format("Radius: %s", numberformat.format(Math.pow(radius, .5))));
            } 
            //A or C = 0. B = 0 :Parabola
            else if((coeff[0] == 0 || coeff[2] == 0) && coeff[1] == 0){
                boolean isX;
                double xLoc, yLoc;
                if(coeff[0] == 0) { //x^2 == 0, use y^2 and y
                    yLoc = (-coeff[3]) / (2.0 * coeff[0]); //-b/2a
                    xLoc = Math.pow(coeff[0] * yLoc, 2) +  coeff[3] * yLoc + coeff[5];
                    isX = false;
                }
                else{ //coeff[2] == 0 y^2 == 0, use x^2 and x
                    xLoc = (-coeff[3]) / (2.0 * coeff[0]); //-b/2a
                    yLoc = Math.pow(coeff[0] * xLoc, 2) +  coeff[3] * xLoc + coeff[5];
                    isX = true;
                }
                
                System.out.println("\nConic: Parabola");
                DecimalFormat numFormat = new DecimalFormat("#.###");
                System.out.println(String.format("Vertex: (%s, %s)", numFormat.format(xLoc), numFormat.format(yLoc)));
                if(isX)
                    System.out.println(String.format("Axis of symmetry: x = %s", xLoc));
                else 
                    System.out.println(String.format("Axis of symmetry: y = %s", yLoc));
            }
            //A ≠ C but have the same sign. B = 0 :Ellipse
            else if(coeff[0] != coeff[2] && coeff[1] == 0 && (coeff[0] < 0 == coeff[2] < 0)){
                coeff[3] /= -coeff[0];
                coeff[0] /= coeff[0];
                coeff[4] /= -coeff[2];
                coeff[2] /= coeff[2];
                
                System.out.println("\nConic: Ellipse");
                DecimalFormat numberformat = new DecimalFormat("#.#");
                System.out.println(String.format("Center: (%s, %s)", numberformat.format(coeff[3]*.5), numberformat.format(coeff[4]*.5)));
                double mLength = Math.abs(coeff[3]) > Math.abs(coeff[4])? coeff[3]: coeff[4];
                System.out.println(String.format("Length of major axis: %s", numberformat.format(Math.abs(mLength))));
            }
            //A and C have different signs. B = 0 :Hyperbola
            else if((coeff[0] < 0 != coeff[2] < 0) && coeff[1] == 0){
                coeff[3] /= coeff[0];
                coeff[0] /= coeff[0];
                coeff[4] /= coeff[2];
                coeff[2] /= coeff[2];
                
                double axis;
                boolean isX;
                if(coeff[0] > 0) {
                    axis = -coeff[4] / 2.0; //parallel to the x axis, y = #
                    isX = false;
                }
                else {
                    axis = -coeff[3] / 2.0; //parallel to the y axis, x = #
                    isX = true;
                }
                
                System.out.println("\nConic: Hyperbola");
                DecimalFormat numberformat = new DecimalFormat("#.#");
                System.out.println(String.format("Center: (%s, %s)", numberformat.format(-coeff[3]*.5), numberformat.format(-coeff[4]*.5)));
                if(isX)
                    System.out.println(String.format("Principal axis: x = %s", numberformat.format(axis)));
                else
                    System.out.println(String.format("Principal axis: y = %s", numberformat.format(axis)));
            }
            //None of the above
            else {
                System.out.println("\nThe entered values do not match a conic shape...\n");
            }
            
            //Prompt rerunning the evaluation 
            System.out.println("Would you like to run again? (Y/N)");
            String feedback = reader.nextLine().toLowerCase().trim();
            valid = validateEntered(feedback, 'y');
            while (!(valid == 0 || valid == 1)) {
                if (valid == -1) System.out.println("Would you like to run again? (Y/N)");
                feedback = reader.nextLine().toLowerCase().trim();
                valid = validateEntered(feedback, 'y');
            }
            isRunning = (valid == 0);
            
        } while (isRunning);
    }

    //Helper method to determine if the user entered valid data
    public static int validateEntered(String value, char type) {
        //numeric validation
        if(type == 'n'){
            int numCoefficients = 0;
            Scanner numScan = new Scanner(value);
            while (numScan.hasNextInt()){
                numCoefficients++;
                numScan.nextInt();
            }
            if (numCoefficients == 6) return 0;
            else return 1;
        }
        //yes/ no validation
        else if (type == 'y') {
            if (value.equals("y")) return 0;
            else if (value.equals("n")) return 1;
        }
        return -1;
    }
}

