import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JollyJumpers {

    public static boolean isJolly(List<Integer> sequence) {

        HashSet<Integer> diffs = new HashSet<>();

        for (int i = 0; i < sequence.size()-1; i++) {
            int diff = Math.abs(sequence.get(i) - sequence.get(i+1));
            if(diffs.contains(diff) || diff >= sequence.size() || diff < 1)
                return false;
            else
                diffs.add(diff);
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(s.hasNext()) {
            int n = s.nextInt();
            List<Integer> sequence = IntStream.range(0, n)
                    .mapToObj(i -> s.nextInt())
                    .collect(Collectors.toList());

            if (isJolly(sequence))
                System.out.println("Jolly");
            else
                System.out.println("Not jolly");

        }

    }
}
