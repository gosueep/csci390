import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Recount {
    public static void main(String[] args) {

        // Scanner object for input
        Scanner s = new Scanner(System.in);

        // Map to hold candidate names (K) and their vote count (V)
        Map<String, Integer> candidates = new HashMap<>();

        // Get the ballots, inserting counts into the candidates map
        String cand = s.nextLine();                    // get first ballot (assumes there is at least 1 ballot cast)
        while(!Objects.equals(cand, "***")) {       // Stops input after "***"
            if(candidates.containsKey(cand))                    // increment count if already in map
                candidates.put(cand, candidates.get(cand)+1);
            else                                                // add candidate to map with 1 vote if not in map
                candidates.put(cand, 1);

            cand = s.nextLine();                       // get next ballot
        }


        // Go through the candidates map, keeping track of the best candidate and the runner-up
        int maxVotes = Integer.MIN_VALUE, runnerUp = 0;
        String bestCand = "";
        for(Map.Entry<String, Integer> entry : candidates.entrySet()) {

            // if the current candidate tied or had more votes, set as best and track the runner-up
            if (entry.getValue() >= maxVotes) {
                runnerUp = maxVotes;
                maxVotes = entry.getValue();
                bestCand = entry.getKey();
            }
        }

        // if there is no tie with the runner-up, the winner is truly the winner
        if(maxVotes > runnerUp)
            System.out.println(bestCand);
        // else, a tie so a runoff is required
        else
            System.out.println("Runoff!");

    }
}
