import java.util.Scanner;

public class Ones {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);     // Scanner to get input

        while(s.hasNextInt()) {
            int num = s.nextInt();      // number from input
            int digits = 0;             // number of digits
            int multiple = num;         // intermediate multiple

            // While the intermediate number is not 0
            while(multiple != 0) {

                // if the rightmost digit is one, remove last digit
                if(multiple % 10 == 1) {
                    digits++;                       // increment digits
                    multiple = multiple / 10;       // ignore that digit now
                }
                // else, add the number to the intermediate multiple
                else {
                    multiple += num;
                }
            }

            // Output the number of digits
            System.out.println(digits);
        }
    }
}
