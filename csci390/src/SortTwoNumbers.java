import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SortTwoNumbers {

    // Amount of numbers to be sorted
    static int NUMS_TO_SORT = 2;

    public static void main(String[] args) {

        // Get input in a list, depending on NUMS_TO_SORT
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < NUMS_TO_SORT; i++) {
            nums.add(s.nextInt());
        }

        // Sort the numbers
        Collections.sort(nums);

        // Output the sorted nums
        for (int num : nums) {
            System.out.print(num);
            System.out.print(' ');
        }
    }
}