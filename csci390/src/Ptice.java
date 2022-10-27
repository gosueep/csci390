import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Ptice {

    // sequence class to hold the name, sequence, and count of a sequence
    static class Sequence {

        public String name;         // name of the sequence
        public String seq;          // the actual sequence
        public int seqCount;        // count of correct guesses from the sequence

        // constructor taking in name and sequence
        public Sequence(String name, String seq) {
            this.name = name;
            this.seq = seq;
        }

        // checks if the sequence is a correct guess and stores it
        public void checkSequence(char currChar, int pos) {
            if(currChar == seq.charAt(pos % seq.length()))
                seqCount++;
        }
    }

    // main method to get input and find the best sequences
    public static void main(String[] args) {

        // The three different proposed sequences
        ArrayList<Sequence> sequences = new ArrayList<>();
        sequences.add(new Sequence("Adrian", "ABC"));
        sequences.add(new Sequence("Bruno", "BABC"));
        sequences.add(new Sequence("Goran", "CCAABB"));

        // Get input
        Scanner s = new Scanner(System.in);
        int numChars = s.nextInt();             // number of chars/answers
        String answers = s.next();              // length of the answer sequence

        for (int i = 0; i < numChars; i++) {
            char currChar = answers.charAt(i);

            for(Sequence seq : sequences)
                seq.checkSequence(currChar, i);
        }

        // Collect all the counts in a list
        ArrayList<Integer> correctCount = new ArrayList<>();
        for(Sequence seq : sequences)
            correctCount.add(seq.seqCount);

        // find the max count of correct answers
        int max = Collections.max(correctCount);
        System.out.println(max);

        // output the best sequences in alphabetical order (assumes the list is in alphabetical order)
        for(Sequence seq : sequences)
            if(max == seq.seqCount)
                System.out.println(seq.name);

    }
}
