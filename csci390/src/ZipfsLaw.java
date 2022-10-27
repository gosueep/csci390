import java.util.*;

public class ZipfsLaw {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        while(s.hasNext()) {
            int occurences = s.nextInt();
            TreeMap<String, Integer> words = new TreeMap<>();

            String line = s.next();
            while (!Objects.equals(line, "EndOfText")) {

                for (String word : line.split("[^a-zA-Z]")) {
                    word = word.toLowerCase();
                    if (words.containsKey(word))
                        words.put(word, words.get(word) + 1);
                    else
                        words.put(word, 1);
                }

                line = s.next();
            }


            boolean noWordsFound = true;
            for (Map.Entry<String, Integer> entry : words.entrySet()) {
                if (entry.getValue() == occurences) {
                    System.out.println(entry.getKey());
                    noWordsFound = false;
                }
            }

            if(noWordsFound)
                System.out.println("There is no such word.");

        }
    }
}
