import java.util.Scanner;

public class Skener {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // get input of the matrix
        int rows = s.nextInt();         // number of rows
        int cols = s.nextInt();         // number of columns
        int rowMult = s.nextInt();      // multiplier for rows
        int colMult = s.nextInt();      // multiplier for columns

        // Get input and print the "zoomed in" version
        for (int r = 0; r < rows; r++) {

            // get input
            String row = s.next();

            // multiplying/zooming in on the rows
            for (int rM = 0; rM < rowMult; rM++) {

                // multiplying/zooming in on the columns
                for (int c = 0; c < cols; c++) {
                    for (int cM = 0; cM < colMult; cM++) {
                        System.out.print(row.charAt(c));        // print the current character
                    }
                }

                // print a newline between rows
                System.out.println();
            }
        }
    }
}
