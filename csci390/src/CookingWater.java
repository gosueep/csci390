import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CookingWater {
    public static void main(String[] args) {

        // Get Input
        Scanner s = new Scanner(System.in);
        int boils = s.nextInt();

        // While parsing through boil start/stop times, find the max and min respectively
        int maxStart = 0, minStop = Integer.MAX_VALUE;
        for (int i = 0; i < boils; i++) {

            // get current start time and compare to max start time
            int start = s.nextInt();
            if(start > maxStart)
                maxStart = start;

            // get current stop time and compare to min stop time
            int stop = s.nextInt();
            if(stop < minStop)
                minStop = stop;
        }

        // if the there is a starting time after a stop time, not all pots started boiling at the same time
        if (maxStart > minStop)
            System.out.println("edward is right");
        // Else, we do not know if all the pots started boiling at the same time
        else
            System.out.println("gunilla has a point");

    }
}
