import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HardwoodSpecies {

    public static void main(String[] args) throws IOException {

        BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> species = new HashMap<>();
        int population = 0;

        String tree = s.readLine();

        while(tree != null && !tree.equals("")) {

            population++;

            if(species.containsKey(tree))
                species.put(tree, species.get(tree)+1);
            else
                species.put(tree, 1);

            tree = s.readLine();
        }

        Object[] keys = species.keySet().toArray();
        Arrays.sort(keys);

        StringBuilder output = new StringBuilder();
        for(Object k : keys) {
            output.append(k).append(" ").append(((double) species.get(k) / population) * 100).append("\n");
        }

        System.out.println(output);
    }
}
