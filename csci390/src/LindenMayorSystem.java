import javax.management.openmbean.ArrayType;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LindenMayorSystem {
    public static void main(String[] args) {

        // Get input
        Scanner s = new Scanner(System.in);
        int ruleNum = s.nextInt();              // number of rules
        int iterations = s.nextInt();           // number of iterations

        // Gets the rules as char, string map
        HashMap<Character, String> rules = new HashMap<>();
        for (int i = 0; i < ruleNum; i++) {
            char initial = s.next().charAt(0);      // char rule as key
            s.next();                               // discard arrow '->'
            String result = s.next();               // result of conversion as value
            rules.put(initial, result);             // put into map
        }

        // Get initial seed/state of tree
        String tree = s.next();

        // Apply iterations of rules to tree to get final result
        for (int i = 0; i < iterations; i++) {

            // temporary string to hold result of iteration
            String newTree = "";

            // Iterate over the current tree and apply the rules to it
            for (int j = 0; j < tree.length(); j++) {
                if(rules.containsKey(tree.charAt(j)))           // If there's a rule for the char,
                    newTree += rules.get(tree.charAt(j));       // add to the temporary tree
                else                                            // Else, add current char
                    newTree += tree.charAt(j);
            }

            // set current tree to temporary tree
            tree = newTree;

        }

        // output result
        System.out.println(tree);
    }
}
