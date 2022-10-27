import java.util.Scanner;

public class HiddenPassword {

    // Checks if the hidden string is valid for the password
    static boolean checkValid(String pass, String hidden) {

        // keeps track of the current password character index
        int passChar = 0;

        // Loop over hidden string, checking if valid
        for (int i = 0; i < hidden.length() && passChar < pass.length(); i++) {

            char currHiddenChar = hidden.charAt(i); // current char in hidden being checked

            // if current passchar and hidden char are equal, valid current char
            if(pass.charAt(passChar) == currHiddenChar)
                passChar++;             // move to next password char

            // else, check these chars are not the other chars in pass
            else {
                // loop over the remaining chars in pass, checking they aren't the current hidden char
                for (int j = passChar + 1; j < pass.length(); j++) {
                    if(currHiddenChar == pass.charAt(j))
                        return false;
                }
            }
        }

        // Final check - if password hasn't all been used, need to return false
        return passChar == pass.length();
    }

    public static void main(String[] args) {

        // Get input password and hidden string
        Scanner s = new Scanner(System.in);
        String pass = s.next();
        String hidden = s.next();

        // If valid hidden string for password, print PASS, else print FAIL
        if(checkValid(pass, hidden))
            System.out.println("PASS");
        else
            System.out.println("FAIL");
    }
}
