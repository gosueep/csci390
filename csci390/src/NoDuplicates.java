import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NoDuplicates {

    // given a phrase, return true if there are repeated words
    static boolean repeatedWords(String phrase) {

        // Set to hold words
        Set<String> words = new HashSet<>();

        // go through each space separated words in the phrase
        for (String str : phrase.split(" ")) {

            // if word already in set, return false
            if(words.contains(str))
                return true;

            // add word to the set
            words.add(str);
        }

        // return false if no repeated words found
        return false;
    }

    // main function to get input phrase and print if there are repeated words in the phrase
    public static void main(String[] args) {

        // get input phrase
        Scanner s = new Scanner(System.in);
        String phrase = s.nextLine();

        // if there are repeated words, print no
        if(repeatedWords(phrase))
            System.out.println("no");
        else                            // else print yes
            System.out.println("yes");
    }
}
