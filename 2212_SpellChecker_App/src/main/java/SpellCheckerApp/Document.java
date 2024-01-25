package SpellCheckerApp;


import java.io.*;
import java.util.*;

/**
 * This class represents a document that the spell checker will check.
 * @author Ameen
 */
public class Document {
    // The path to the file
    private String file_path;
    // The words in the document
    private List<String> words;
    // Flag for unsaved changes
    private boolean unsaved_changes = false;

    /**
     * Constructor for the Document class.
     * Initializes the file path and creates an empty list of words.
     *
     * @param file_p The path to the file
     */
    public Document(String file_p) {
        this.file_path = file_p;
        this.words = new ArrayList<>();
    }

    /**
     * Opens the file at the specified file path and reads the words into the words list.
     * Special characters are used to indicate the start of the document and new lines.
     *
     * @throws IOException If an I/O error occurs
     */
    public void openFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file_path));
        String line;
        // Add a special character to indicate the start of the document
        words.add("<START>");
        while ((line = reader.readLine()) != null) {
            // Check if the line is not empty before splitting it into words
            if (!line.isEmpty()) {
                // Split the line into words and add them to the words list
                Collections.addAll(words, line.split("\\s+"));
            }
            // Add a special character to indicate a new line
            words.add("<NEWLINE>");
        }
        // Remove the last "<NEWLINE>" added
        if (!words.isEmpty()) {
            words.remove(words.size() - 1);
        }
        reader.close();
    }

    /**
     * Saves the current content to the file at the specified file path.
     * Special characters are used to indicate new lines.
     *
     * @param toFinalWrite The file to which the document is written or saved back to
     * @throws IOException If an I/O error occurs
     */
    public void saveFile(File toFinalWrite) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFinalWrite));
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (!word.equals("")) {
                if (word.equals("<NEWLINE>")) {
                    // Write a newline character to the file
                    writer.write("\n");
                } else if (!word.equals("<START>") || i != 0) {
                    // Write the word followed by a space to the file, unless it's the <START> word at the beginning of the list
                    writer.write(word + " ");
                }
            }
        }
        // Reset the unsaved changes flag
        unsaved_changes = false;
        writer.close();
    }

    /**
     * Returns a list of words in the document.
     *
     * @return A list of words in the document
     */
    public List<String> iterateWords() {
        return words;
    }

    /**
     * Discards any changes that have not been saved.
     * It clears the words list and resets the unsaved changes flag.
     */
    public void discardChanges() {
        // Clear the words list
        words.clear();
        // Reset the unsaved changes flag
        unsaved_changes = false;
    }

    /**
     * Gets the context of a word in the document.
     * It returns the word itself along with the word before and after it.
     * If the word is at the beginning or end of the document, an empty string is added at the beginning or end, respectively.
     *
     * @param index The index of the word to get the context for
     * @return A list representing the context of the word
     */
    public List<String> getContext(int index) {

        // Get the words around the specified word to provide context
        int start = Math.max(0, index - 1);
        int end = Math.min(words.size(), index + 2);
        List<String> context = new ArrayList<>(words.subList(start, end));

        // If the first index element is requested, add an empty string at the beginning
        if (index == 0) {
            context.add(0, "");
        }

        // If the last index element is requested, add an empty string at the end
        if (index == words.size() - 1) {
            context.add("");
        }

        return context;
    }

    /**
     * Replaces the word at the specified index with a new word.
     * It also sets the unsaved changes flag to true.
     *
     * @param index   The index of the word to replace
     * @param newWord The new word to replace the old word with
     */
    public void replaceWord(int index, String newWord) {
        // Check if the index is valid
        if (index >= 0 && index < words.size()) {
            // Replace the word at the specified index with the new word
            words.set(index, newWord);
            // Set the unsaved changes flag to true
            unsaved_changes = true;
        } else {
            // Print an error message if the index is out of bounds
            System.out.println("Error: Index out of bounds!");
        }
    }

    /**
     * Returns the unsaved changes flag.
     *
     * @return The unsaved changes flag
     */
    public boolean getUnsavedChanges(){
        return unsaved_changes;
    }

    /**
     * Returns the file path of the document.
     *
     * @return The file path of the document
     */
    public String getFilePath() {
        return this.file_path;
    }
}
