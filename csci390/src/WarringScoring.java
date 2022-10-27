import java.util.Scanner;

public class WarringScoring {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // variables to hold Yraglac's scores
        int y_score = 0;                            // current score
        int y_lead = 0;                             // best lead
        int y_bestStreak = 0;                       // best streak
        int y_streak = 0;                           // current streak

        // variables to hold Notnomde's scores
        int n_score = 0;                            // current score
        int n_lead = 0;                             // best lead
        int n_bestStreak = 0;                       // best streak
        int n_streak = 0;                           // current streak

        // Get all points scored from input
        int numPoints = s.nextInt();
        for (int i = 0; i < numPoints; i++) {
            // If Yraglac scored
            if(s.next().equals("Yraglac")) {
                y_score++;                          // increment score
                y_streak++;                         // increment streak
                n_streak = 0;                       // reset other player's streak
                if(y_streak > y_bestStreak)         // store best streak if better
                    y_bestStreak = y_streak;
            }
            // Else Notnomde scored
            else {
                n_score++;
                n_streak++;
                y_streak = 0;
                if(n_streak > n_bestStreak)
                    n_bestStreak = n_streak;
            }

            // if lead is better, then store best lead
            if(y_score - n_score > y_lead)
                y_lead = y_score - n_score;
            if(n_score - y_score > n_lead)
                n_lead = n_score - y_score;
        }

        // calculate the two different scoring types
        // Positive means Yraglac won, 0 means a tie, and Negative means Notnomde won
        int n_game = y_bestStreak - n_bestStreak;       // Notnomde's scoring
        int y_game = y_lead - n_lead;                   // Yraglac's scoring


        // If players agree
        if( (y_game > 0 && y_bestStreak > n_bestStreak)  ||     // if y had a greater lead and better streak
            (y_game < 0 && n_bestStreak > y_bestStreak)  ||     // if n had a greater lead and better streak
            (y_game == 0 && n_game == 0))                       // if players tied on leads and streaks
            System.out.println("Agree");
        // Else, players disagree
        else
            System.out.println("Disagree");


    }
}
