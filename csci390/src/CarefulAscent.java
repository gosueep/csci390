import java.util.Scanner;

public class CarefulAscent {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);     // Scanner to get input

        int x_pos = s.nextInt();            // relative x position of the ship
        int y_pos = s.nextInt();            // relative y position of the ship
        int shields = s.nextInt();          // number of shields
        double total_distance = 0;          // total relative distance covered at 1km/min upwards

        // Go through each shield
        for (int i = 0; i < shields; i++) {
            int start = s.nextInt();                // y-pos start of shield
            int stop = s.nextInt();                 // y-pos stop of shield
            double multiplier = s.nextDouble();     // speed multiplier of shield

            total_distance += Math.abs(stop - start) * multiplier;      // horizontal distance covered while in this shield
            y_pos -= Math.abs(stop - start);                            // subtract vertical distance in shield
        }

        // Add remaining distance not in shield
        total_distance += y_pos;

        // find velocity from actual position and relative distance
        System.out.println(x_pos / total_distance);

    }
}
