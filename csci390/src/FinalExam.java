import java.util.Scanner;

public class FinalExam {
    public static void main(String[] args) {

        // Scanner to get input
        Scanner s = new Scanner(System.in);

        // get the number of lines
        int lines = s.nextInt();

        // Get the answers and check the amount of equal adjacent answers
        String last = "";
        int correct = 0;
        for (int i = 0; i < lines; i++) {

            // get the answer on the current line
            String current = s.next();

            // if the current answer is the same as the previous, increment correct amount
            if (last.equals(current))
                correct++;

            // store current answer as last
            last = current;
        }

        // print number of correct answers
        System.out.println(correct);
    }
}
