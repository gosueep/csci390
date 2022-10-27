import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class PairingSocks {
    public static void main(String[] args) {

        // Gets input
        Scanner s = new Scanner(System.in);

        int numSocks = s.nextInt() * 2;         // number of individual socks, not pairs

        Stack<Integer> pile1 = new Stack<>();   // initial pile of socks as a stack
        for (int i = 0; i < numSocks; i++) {
            pile1.add(s.nextInt());
        }

        // Initialize aux pile and numMoves
        Stack<Integer> pile2 = new Stack<>();   // empty stack for the auxiliary pile
        int numMoves = 0;

        // Iterate through pile 1, removing pairs if possible
        while(!pile1.isEmpty()) {

            // If the top sock from each pile matches, remove socks and make pair
            if(!pile2.isEmpty() && Objects.equals(pile1.peek(), pile2.peek())) {
                pile1.pop();
                pile2.pop();
            }
            // Else, if they don't match, move the sock from the initial pile to the aux pile
            //      Note: there is no point to moving socks from the aux pile to the initial pile
            else {
                pile2.add(pile1.pop());
            }

            // increment the number of moves
            numMoves++;
        }

        // if the aux pile is not empty, there was a mismatch
        if(pile2.size() > 0)
            System.out.println("impossible");
        // else, print the number of moves it took to pair all the socks
        else
            System.out.println(numMoves);
    }
}
