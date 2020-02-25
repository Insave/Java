import java.util.Scanner;

public class ChangeMaker {

    public static final String ERR_MSG = "Dollar amount must be non-negative!";

    /* Method to convert a double to
     * an integer
     *
     * @param num number to convert
     * @return converted value
     */
    public static int convertToInt(double num) {
        return (int) Math.round(num);
    }

    // TODO: document this method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total amount: $");
        double total = sc.nextDouble();

        if (total < 0) {
            System.out.println(ERR_MSG);
        } else {
            int quarters;
            int dimes;
            int nickles;
            int pennies;

            quarters = convertToInt(Math.floor(total / .25));
            total %= .25;
            if (quarters == 1) {
                System.out.printf("You have %d quarter, ", quarters);
            } else {
                System.out.printf("You have %d quarters, ", quarters);
            }

            dimes = convertToInt(Math.floor(total / .1));
            total %= .1;
            if (dimes == 1) {
                System.out.printf("%d dime, ", dimes);
            } else {
                System.out.printf("%d dimes, ", dimes);
            }

            nickles = convertToInt(Math.floor(total / .05));
            total %= .05;
            if (nickles == 1) {
                System.out.printf("%d nickel, ", nickles);
            } else {
                System.out.printf("%d nickels, ", nickles);
            }

            pennies = convertToInt(total / .01);
            if (pennies == 1) {
                System.out.printf("and %d penny.%n", pennies);
            } else {
                System.out.printf("and %d pennies.%n", pennies);
            }

        }

    }
}