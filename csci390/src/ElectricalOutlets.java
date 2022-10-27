import java.util.ArrayList;
import java.util.Scanner;

public class ElectricalOutlets {
    public static void main(String[] args) {
        // Declare Scanner object
        Scanner s = new Scanner(System.in);

        // Get input
        int testCases = s.nextInt();            // get number of test cases
        for (int i = 0; i < testCases; i++) {

            // Get number of power strips
            int numStrips = s.nextInt();

            // Get outlets on each powerstrip as an int ArrayList
            ArrayList<Integer> powerstrips = new ArrayList<Integer>();
            for (int j = 0; j < numStrips; j++) {
                powerstrips.add(s.nextInt());
            }

            // Call maxAppliances and print
            System.out.println(maxAppliances(powerstrips));
        }
    }

    // Returns the max amount of appliances that can be powered,
    // Given an ArrayList of the outlets on each powerstrip
    // Assumes only one Wall Outlet is available
    static int maxAppliances(ArrayList<Integer> powerstrips) {

        // if no powerstrips, only the wall outlet is available
        if (powerstrips.size() == 0)
            return 1;

        // Sums up the outlets of each powerstrip
        int outlets = 0;
        for (int strip : powerstrips) {
            outlets += strip;
        }

        // Subtract the outlets used to connect other powerstrips
        return outlets - (powerstrips.size() - 1);
    }
}
