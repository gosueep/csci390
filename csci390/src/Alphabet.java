import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Alphabet {

    static int[][] memo;
    static String s1;
    static String s2 = "abcdefghijklmnopqrstuvwxyz";

    public static int distance(int i1, int i2) {

        if(memo[i1][i2] != 0)
            return memo[i1][i2];

        if(i1 == 0) {
            memo[i1][i2] = i2;
            return memo[i1][i2];
        }
        if(i2 == 0) {
            memo[i1][i2] = i1;
            return memo[i1][i2];
        }

        int match = Integer.MAX_VALUE;
        if(s1.charAt(i1) == s2.charAt(i2))
            match = distance(i1-1, i2-1);
        int del = distance(i1-1, i2);
        int ins = 1 + distance(i1, i2-1);

        memo[i1][i2] = Math.min(match, Math.min(del, ins));
        return memo[i1][i2];
    }

    public static void main(String[] args) {

        // Get inputs and setup DP matrix
        Scanner s = new Scanner(System.in);
        s1 = s.next();

        memo = new int[s1.length()+1][26+1];            // extra row/col for 0's
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < 26; j++) {
                memo[i][j] = 0;                         // instantiate all with 0's
            }
        }
        System.out.println(distance(s1.length()-1, s2.length()-1));
        System.out.println();
//
//        // Find the max alphabetic letters in the string
//        for (int i = 0; i < s1.length(); i++) {
//            for (int j = 0; j < 26; j++) {
//                if(s1.charAt(i) == s2.charAt(j))
//                    memo[i+1][j+1] = 1 + memo[i][j];
//                else
//                    memo[i+1][j+1] = Math.max(memo[i+1][j], memo[i][j+1]);
//            }
//        }
//
//        // Subtract 26 from max letters in alphabetic order - all the remaining letters must be added
//        System.out.println(26 - memo[s1.length()][26]);
    }
}
