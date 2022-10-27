import java.util.Scanner;

public class AnotherCandies {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);     // Scanner to get input
        int TEST_CASES = s.nextInt();           // get number of test cases

        // run for each test case
        for (int i = 0; i < TEST_CASES; i++) {
            s.nextLine();                       // discard first line
            int numKids = s.nextInt();          // get number of kids for test case

            // get number of candies from input (must be a long as kids can have up to 2^63 candies)
            long candies = 0;
            for (int j = 0; j < numKids; j++) {
                candies += s.nextLong() % numKids;   // mod each amount, only need the candies that cannot be split
                candies = candies % numKids;         // mod after adding
            }

            // If the candies left over are 0, they can be evenly divided
            if(candies == 0)
                System.out.println("YES");
            else                             // else print no
                System.out.println("NO");
        }
    }
}
