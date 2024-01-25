package SpellCheckerApp;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class represents a user-defined dictionary used to spellcheck words within a document.
 * It extends the Dictionary class and adds functionality for adding and removing words.
 *
 * @author Ameen
 */
public class UserDictionary extends Dictionary {

    // The words added by the user
    private HashSet<String> userAddedWords;
    // The path to the file storing the user-added words
    private String userWordsFilePath = "userWords.txt";

    /**
     * Constructor for the UserDictionary class.
     * It initializes the dictionary with words from a file and loads the user-added words.
     *
     * @param fileName The name of the file containing the dictionary words
     * @throws IOException If an I/O error occurs
     */
    public UserDictionary(String fileName) throws IOException {
        super(fileName);
        this.userAddedWords = new HashSet<>();
        loadUserAddedWords();
    }

    /**
     * Loads the user-added words from the file.
     * If the file exists, it reads the file line by line and adds each line to the userAddedWords set.
     *
     * @throws IOException If an I/O error occurs
     */
    private void loadUserAddedWords() throws IOException {
        File userWordsFile = new File(userWordsFilePath);
        if (userWordsFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(userWordsFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    userAddedWords.add(line);
                }
            }
        }
    }


    /**
     * Adds a word to the dictionary.
     * It strips punctuation from the word before adding it.
     * If the word is not already in the dictionary, it is added and saved to the file.
     *
     * @param word The word to add
     * @throws IOException If an I/O error occurs
     */
    public void addWord(String word) throws IOException {
        // Strip punctuation from the beginning and end of the word
        String strippedWord = word.replaceAll("^[\\p{Punct}\\s]+|[\\p{Punct}\\s]+$", "");
        // Check if the word is already in the userAddedWords set
        if (!this.userAddedWords.contains(strippedWord)) {
            this.userAddedWords.add(strippedWord);
            saveUserAddedWord(strippedWord);
        }
    }

    /**
     * Saves a user-added word to the file.
     * It writes the word to the file followed by a newline.
     *
     * @param word The word to save
     * @throws IOException If an I/O error occurs
     */
    private void saveUserAddedWord(String word) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userWordsFilePath, true))) {
            writer.write(word);
            writer.newLine();
        }
    }

    /**
     * Returns the set of user-added words.
     *
     * @return The set of user-added words
     */
    public HashSet<String> getUserAddedWords() {
        return this.userAddedWords;
    }


    /**
     * Removes a word from the dictionary.
     * It strips punctuation from the word before removing it.
     * If the word is in the dictionary, it is removed and the file is updated.
     *
     * @param word The word to remove
     * @throws IOException If an I/O error occurs
     */
    public void removeWord(String word) throws IOException {
        // Strip punctuation from the beginning and end of the word
        String strippedWord = word.replaceAll("^[\\p{Punct}\\s]+|[\\p{Punct}\\s]+$", "");
        // Check if the word is in the userAddedWords set
        if (this.userAddedWords.contains(strippedWord)) {
            this.userAddedWords.remove(strippedWord);
            saveUserRemovedWord(strippedWord);
        }
    }

    /**
     * Saves a user-removed word to the file.
     * It reads all lines from the file, removes the word from the lines, and writes the lines back to the file.
     *
     * @param word The word to save
     * @throws IOException If an I/O error occurs
     */
    private void saveUserRemovedWord(String word) throws IOException {
        // Read all lines from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userWordsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Remove the word from the lines
        lines.remove(word);

        // Write the lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userWordsFilePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
