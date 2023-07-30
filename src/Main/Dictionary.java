package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {

	// Function to create a dictionary from words in local text files
	public static void createDictionary() {
		String line, token;

		File folder = new File("cleanedFile/");
		File[] files = folder.listFiles();
		Set<String> set = new HashSet<>();
		ArrayList<String> dictionary = new ArrayList<String>();

		//Process each file in the folder
		for (File file : files)
		{
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null) {
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(line);

						// Tokenize the line and process each word
						while (scan.hasNext()) {
							token = scan.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();  // Remove non-alphabetic characters and convert to lowercase - text cleaning
							set.add(token);
						}
					}
					reader.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Add the unique words from the set to the dictionary ArrayList
		dictionary.addAll(set);
		Collections.sort(dictionary);

		try {
			FileWriter writer = new FileWriter("dictionary.txt");  //a FileWriter is created to write the dictionary to a file

			for(String word: dictionary) // Write each word from the dictionary to the file
				writer.write(word + System.lineSeparator());

			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//main function
	public static void main(String[] args) {
		Dictionary.createDictionary(); //createDictionary function call to create the dictionary
		System.out.println("Dictionary created.");
	}
}
