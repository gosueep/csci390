import java.util.Scanner;

public class FYI {

    static String OPERATOR_CODE = "555";

    public static void main(String[] args) {

        // Get telephone number input as a String
        Scanner s = new Scanner(System.in);
        String phoneNum = s.nextLine();

        // If the first 3 digits of the telephone number are the OPERATOR_CODE, print 1
        if (phoneNum.startsWith(OPERATOR_CODE))
            System.out.println(1);
        // Else, print 0
        else
            System.out.println(0);
    }
}
