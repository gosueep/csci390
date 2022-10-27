import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parking {

    static int NUM_TRUCKS = 3;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // List of rates, in increasing amounts of trucks, starting at 1
        List<Integer> rates = IntStream.range(0, NUM_TRUCKS)                // For this, just {A,B,C}
                .mapToObj(i -> s.nextInt())
                .collect(Collectors.toList());

        // Get arrival and departure times as sets - only need to know when trucks arrive/depart
        Map<Integer, Integer> arrivals = new HashMap<>();
        Map<Integer, Integer> depts = new HashMap<>();
        for (int i = 0; i < NUM_TRUCKS; i++) {
            // get arrival and departure time for the truck
            int arrive = s.nextInt(), dept = s.nextInt();

            // if not in map add, else increment number of trucks departing at this time
            if(!arrivals.containsKey(arrive))
                arrivals.put(arrive, 1);
            else arrivals.put(arrive, arrivals.get(arrive)+1);
            // if not in map, add, else increment number of trucks departing at this time
            if(!depts.containsKey(dept))
                depts.put(dept, 1);
            else depts.put(dept, depts.get(dept)+1);
        }

        // Since the bounds are only 100 minutes total, just iterate over all 100 minutes
        int numCars = 0;
        int cost = 0;
        for (int i = 0; i <= 100; i++) {
            if(arrivals.containsKey(i))        // add all cars that arrived at this time
                numCars += arrivals.get(i);
            if(depts.containsKey(i))           // subtract all cars that arrived at this time
                numCars -= depts.get(i);
            if(numCars > 0)                 // if at least 1 car, calculate cost and add to total
                cost += numCars * rates.get(numCars-1);
        }

        // print total cost
        System.out.println(cost);
    }
}
