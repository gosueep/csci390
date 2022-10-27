import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Toflur {
    public static void main(String[] args) {

        // Get input tiles and sort - to minimize the differences in adjacent tiles, you must sort
        Scanner s = new Scanner(System.in);
        List<Integer> tiles = IntStream.range(0, s.nextInt())
                .mapToObj(i -> s.nextInt())
                .sorted()
                .collect(Collectors.toList());

        // count up the score
        long score = 0;
        for (int i = 0; i < tiles.size()-1; i++) {
            long diff = tiles.get(i+1) - tiles.get(i);   // get difference of adjacent tiles
            score += diff * diff;                       // square difference and add to score
        }

        // print score
        System.out.println(score);
    }
}
