// Author: Patrick Fief
// Course: Java Programming
// Set MU Program 1
// References: https://stackoverflow.com/questions/3911966/how-to-convert-number-to-words-in-java

import java.util.Scanner;
import java.text.DecimalFormat;

public class CosmicNumbersPF {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a number (0 - 1,000,000): ");
        long entered = sc.nextLong();
        String curNum = convert(entered);
        
        while (!curNum.equals("four")) {
            System.out.println(curNum + " is " + convert(curNum.replaceAll("\\s", "").length()));
            curNum = convert(curNum.replaceAll("\\s", "").length());
        }
        
        System.out.println(curNum + " is cosmic \n");
    }
    
    private static String convertLessThanOneThousand(int number) {
        String strBuild;

        if (number % 100 < 20){
          strBuild = numNames[number % 100];
          number /= 100;
        }
        else {
          strBuild = numNames[number % 10];
          number /= 10;

          strBuild = tensNames[number % 10] + strBuild;
          number /= 10;
        }
        if (number == 0) return strBuild;
        return numNames[number] + " hundred" + strBuild;
    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

        String strNumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        strNumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(strNumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(strNumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(strNumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(strNumber.substring(9,12));

        String tradBillions;
        switch (billions) {
            case 0:
              tradBillions = "";
              break;
            case 1 :
              tradBillions = convertLessThanOneThousand(billions)
              + " billion ";
              break;
            default :
              tradBillions = convertLessThanOneThousand(billions)
              + " billion ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
              tradMillions = "";
              break;
            case 1 :
              tradMillions = convertLessThanOneThousand(millions)
                 + " million ";
              break;
            default :
              tradMillions = convertLessThanOneThousand(millions)
                 + " million ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
            switch (hundredThousands) {
            case 0:
              tradHundredThousands = "";
              break;
            case 1 :
              tradHundredThousands = "one thousand ";
              break;
            default :
              tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                 + " thousand ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }
    
    private static final String[] tensNames = {
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
    };

    private static final String[] numNames = {
        "",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen"
    };
    
}