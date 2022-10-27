import java.util.Scanner;

public class Crne {

    public static void main(String[] args) {

        // Get input as a long since input can be up to 10^9
        Scanner s = new Scanner(System.in);
        long cuts = s.nextLong();

        // The optimal way to cut the board is an even number of cuts horizontally and vertically
        long pieces = (cuts / 2) + 1;           // resulting pieces is (cuts/2 + 1) ^ 2
        long result = pieces * pieces;

        // If an odd number of cuts, another cut adds (cuts/2 + 1) pieces
        if(cuts % 2 == 1)
            result += pieces;

        // Print result
        System.out.println(result);

    }
}
