import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FindingAnA {

    // Regex pattern matching the first a and any characters following it
    static Pattern firstA = Pattern.compile("a.*");

    /*
        Returns the suffix of a string, starting at the first a
     */
    public static String getSuffix(String str) {

        // matches the firstA pattern to the incoming string
        Matcher matcher = firstA.matcher(str);

        // If the pattern is matched, output the match
        if(matcher.find()) {
            return matcher.group();
        }

        // else, return the original string as no A was found.
        return str;
    }

    public static void main(String[] args) {

        // Gets the string input
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        // Call getSuffix and print the result
        System.out.println(getSuffix(str));
    }
}
