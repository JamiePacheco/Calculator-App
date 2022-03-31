import java.util.ArrayList;
import java.lang.Math;

public class calculations {

    public static double calculating(String[] operation, char operator) {

        double total = 0;

        for (int i=0; i<operation.length; i++){

            if (operation[i].split("~").length > 1){
                operation[i] = String.valueOf(calculating(operation[i].split("~"), '-'));
            } else if (operation[i].split("\\*").length > 1){
                operation[i] = String.valueOf(calculating(operation[i].split("\\*"), '*'));
            } else if (operation[i].split("/").length > 1) {
                operation[i] = String.valueOf(calculating(operation[i].split("/"), '/'));
            }
        }


        for (int i = 0; i < operation.length; i++) {

            if (i == 0) {
                total += Double.parseDouble(operation[i]);
                continue;
            }

            switch (operator) {

                case '+':
                    total += Double.parseDouble(operation[i]);
                    break;
                case '-':
                    total -= Double.parseDouble(operation[i]);
                    break;
                case '*':
                    total *= Double.parseDouble(operation[i]);
                    break;
                case '/':
                    total /= Double.parseDouble(operation[i]);
                    break;
            }
        }

        return total;

    }

}
