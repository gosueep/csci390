import java.lang.reflect.Array;
import java.util.Scanner;

public class VaccineEfficacy {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int trials = s.nextInt();
        int[] control_infected = {0, 0, 0};
        int[] control_okay = {0, 0, 0};
        int[] vaccinated_infected = {0, 0, 0};
        int[] vaccinated_okay = {0, 0, 0};

        for (int i = 0; i < trials; i++) {
            String result = s.next();

            for (int j = 1; j < 4; j++) {
                if (result.charAt(0) == 'Y') {
                    if (result.charAt(j) == 'Y')
                        vaccinated_infected[j-1] += 1;
                    else
                        vaccinated_okay[j-1] += 1;
                }
                else {
                    if (result.charAt(j) == 'Y')
                        control_infected[j - 1] += 1;
                    else
                        control_okay[j - 1] += 1;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            double control_rate = (double) control_infected[i] / (control_okay[i] + control_infected[i]);
            double vacc_rate = (double) vaccinated_infected[i] / (vaccinated_okay[i] + vaccinated_infected[i]);
            double efficacy = (1 - (vacc_rate / control_rate)) * 100;
            if(efficacy <= 0)
                System.out.println("Not Effective");
            else
                System.out.println(efficacy);
        }
    }
}
