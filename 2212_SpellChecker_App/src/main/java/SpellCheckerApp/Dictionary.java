package SpellCheckerApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

/**
 * This class represents a dictionary used to spellcheck words within a document.
 * It uses a HashSet to store the words for efficient lookup.
 * @author Theo Cho
 */
public class Dictionary {

	// HashSet to store the words in the dictionary for efficient lookup
	private HashSet<String> dictwords = new HashSet<String>();

	/**
	 * Constructor that initializes the dictionary with words from a file.
	 * It reads the file line by line and adds each word to the HashSet.
	 * @param fileName The name of the file containing the dictionary words.
	 * @throws IOException if there's an issue reading the file.
	 */
	public Dictionary(String fileName) throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;

			// Read the file line by line
			while ((line = br.readLine()) != null) {
				// Split the line into words
				String[] words = line.split("\\s+");
				// Add all the words to the dictionary
				Collections.addAll(this.dictwords, words);
			}
		}
	}

	/**
	 * Getter for the dictionary words.
	 * @return A HashSet containing all the words in the dictionary.
	 */
	public HashSet<String> getDict() {
		return this.dictwords;
	}

	/**
	 * Checks if a word exists in the dictionary.
	 * It strips punctuation from the beginning and end of the word before checking.
	 * @param word The word to check.
	 * @return true if the word exists in the dictionary, false otherwise.
	 */
	public boolean doesWordExist(String word) {
		// Strip punctuation from the beginning and end of the word
		String strippedWord = word.replaceAll("^[\\p{Punct}\\s]+|[\\p{Punct}\\s]+$", "");
		// Check if the stripped word exists in the dictionary
		return this.dictwords.contains(strippedWord);
	}
}
