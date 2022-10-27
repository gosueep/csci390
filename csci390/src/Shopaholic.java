import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Shopaholic {
    public static void main(String[] args) {
        // Gets input
        Scanner s = new Scanner(System.in);
        int items = s.nextInt();                         // number of items
        ArrayList<Integer> prices = new ArrayList<>();   // input prices list
        for (int i = 0; i < items; i++) {
            prices.add(s.nextInt());
        }

        // Sort the prices list to pick best discounts
        Collections.sort(prices);
        Collections.reverse(prices);

        // Store the discount in a long since the result can be larger than int
        long discount = 0;

        // Discount every third item in the list
        for(int i = 2; i < items; i+=3) {
            discount += prices.get(i);
        }

        // output the maximum discount
        System.out.println(discount);
    }
}
