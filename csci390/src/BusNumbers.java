import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BusNumbers {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<Integer> buses = IntStream.range(0, s.nextInt())
                .mapToObj(i -> s.nextInt())
                .collect(Collectors.toList());

        Collections.sort(buses);
        buses.add(0);

        int start = 0;
        for (int i = 0; i < buses.size()-1; i++) {
            if(buses.get(i)+1 != buses.get(i+1)) {

                if (start != i) {
                    if(start +1 == i)
                        System.out.print(buses.get(start) + " " + buses.get(i) + " ");
                    else
                        System.out.print(buses.get(start) + "-" + buses.get(i) + " ");

                }

                else
                    System.out.print(buses.get(i) + " ");

                start = i+1;
            }
        }

    }
}
