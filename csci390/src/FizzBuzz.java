import java.util.Scanner;

public class FizzBuzz {

    public static void main(String[] args) {

        // Gets input
        Scanner s = new Scanner(System.in);
        int fizz = s.nextInt();                 // divisor for fizz
        int buzz = s.nextInt();                 // divisor for buzz
        int maxNum = s.nextInt();               // the number to stop at, from 1

        // for numbers 1 to maxNum, output Fizz, Buzz, FizzBuzz or the number
        for (int i = 1; i <= maxNum; i++) {

            // if divisible by fizz divisor
            if(i % fizz == 0) {
                // if also divisible by buzz divisor, print "FizzBuzz"
                if(i % buzz == 0)
                    System.out.println("FizzBuzz");
                // else just print fizz
                else
                    System.out.println("Fizz");
            }
            // if divisible by only buzz divisor, print "Buzz"
            else if(i % buzz == 0)
                System.out.println("Buzz");
            // Else, not divisible by either divisor, print the number
            else
                System.out.println(i);
        }
    }
}