package Main;

public class EditDistance {

    public static int editDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[] C = new int[len2 + 1];

        for (int j = 0; j <= len2; j++)
            C[j] = j;

        for (int i = 0; i < len1; i++) {
            int prevDiagonal = C[0]; // To keep track of the previous diagonal value.
            C[0] = i + 1;

            for (int j = 0; j < len2; j++) {
                int temp = C[j + 1];
                if (word1.charAt(i) == word2.charAt(j)) {
                    C[j + 1] = prevDiagonal;
                } else {
                    int replace = C[j] + 1;
                    int insert = C[j + 1] + 1;
                    int delete = prevDiagonal + 1;
                    C[j + 1] = Math.min(Math.min(replace, insert), delete);
                }
                prevDiagonal = temp; // Update prevDiagonal for the next iteration.
            }
        }

        return C[len2];
    }
}
