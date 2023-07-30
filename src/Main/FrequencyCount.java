package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Function to count the frequency of a word in all available files
public class FrequencyCount {

	public static void wordCount(String wordToCount ) throws FileNotFoundException, IOException {

		try {

			File fileFolder = new File("cleanedFile"); //opening a file which is holding all the converted text files

			File[] allFiles = fileFolder.listFiles();

			for (int i = 0; i < allFiles.length; i++) {

				int count = 0;
				if (allFiles[i].isFile()) {

					File file = new File("src\\CleanedFile" + allFiles[i].getName());
					InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
					BufferedReader in = new BufferedReader(read);

					StringBuffer findWord = new StringBuffer(); //Buffer to store content of the file

					String str = null;

					//Read each line of the file and append it to the findWord buffer
					while ((str = in.readLine()) != null) {

						findWord.append(str);

					}

					Pattern pattern = Pattern.compile(wordToCount); //Compile the pattern to find the word
					Matcher matcher = pattern.matcher(findWord);//Match the pattern with the content of the file

					while (matcher.find()) {

						count++;

					}

					in.close();

					System.out.println("The word to count : " + wordToCount + "\nTotal Count : " + count + " times\nThe file Name : " + allFiles[i].getName() + "\n");
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	//main function
	public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the word you'd like to know the frequency of: ");
		String wordToCount = s.nextLine();
		FrequencyCount.wordCount(wordToCount); //Call the wordCount method to count the word frequency
	}
}
