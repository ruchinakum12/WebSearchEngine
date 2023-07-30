package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Suggestion {
    public static void suggest(String input) throws FileNotFoundException, IOException {

        File file = new File("dictionary.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<String> dictionary = new ArrayList<String>();
            String line;

            while ((line = reader.readLine()) != null)
                dictionary.add(line);
            reader.close();

            ArrayList<String> suggestions = new ArrayList<String>();
            int minEditDistance = Integer.MAX_VALUE;

            for (String word : dictionary) {
                int editDistance = EditDistance.editDistance(word, input);

                if (editDistance < minEditDistance) {
                    minEditDistance = editDistance;
                    suggestions.clear(); // Clear previous suggestions as we found a better match.
                    suggestions.add(word);
                } else if (editDistance == minEditDistance) {
                    suggestions.add(word);
                }
            }

            if (!suggestions.isEmpty()) {
                System.out.println("Did you mean:");
                for (String suggestion : suggestions) {
                    System.out.println(suggestion);
                }
            } else {
                System.out.println("No suggestions found.");
            }
        }
    }
}
