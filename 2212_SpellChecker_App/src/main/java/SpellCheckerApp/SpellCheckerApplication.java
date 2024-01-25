package SpellCheckerApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The SpellCheckerApplication class is the main class for the spell checking application.
 * It manages the SpellChecker, UserDictionary, Metrics, and Document for the application.
 * The class provides methods to start a new session with a specified document, recalculate errors in the document, and update metrics for the application.
 * This class serves as the central hub for the spell checking process, coordinating the various components of the application.
 *
 * @author Ameen
 */
public class SpellCheckerApplication {
    // The SpellChecker for the application
    private SpellChecker spellChecker;
    // The UserDictionary for the application
    private UserDictionary userDictionary;
    // The Metrics for the application
    private Metrics metrics;
    // The Document to be checked
    private Document document;

<<<<<<< HEAD
    public SpellCheckerApplication(SpellChecker spellChecker, UserDictionary userDictionary, Document document) {
        this.spellChecker = spellChecker;
        this.userDictionary = userDictionary;
        this.document = document;
=======
    /**
     * Constructor for the SpellCheckerApplication class.
     * Initializes the UserDictionary and Metrics.
     *
     * @throws IOException If an I/O error occurs
     */
    public SpellCheckerApplication() throws IOException {
        this.userDictionary = new UserDictionary("dict.txt");
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
        this.metrics = new Metrics();
    }

    /**
     * Starts a new session with the specified document.
     * It opens the document, creates a new SpellChecker, and generates errors.
     *
     * @param document The document to start a session with
     * @throws IOException If an I/O error occurs
     */
    public void startSession(Document document) throws IOException {

        this.document = document;
        this.spellChecker = new SpellChecker(document, userDictionary);


        document.openFile();

        spellChecker = new SpellChecker(document, userDictionary);

        spellChecker.generateErrors();

    }

    /**
     * Recalculates the errors in the document.
     * It clears the current errors and generates new ones.
     */
    public void recalculateErrors(){
        spellChecker.clearErrors();
        spellChecker.generateErrors();
    }

    /**
     * Updates the metrics for the application.
     * It calculates the word count, character count, error count, double word count, miscapitalization count, misspelling count, and line count.
     */
    public void updateMetrics() {
        //Update metrics word count
        List<String> wordsList = document.iterateWords();
        int wordCount = 0;
        int totalCharacters = 0;
        for (int i = 0; i < wordsList.size(); i++) {
            String word = wordsList.get(i);
            if (!word.equals("<START>")) {
                if (!word.equals("<NEWLINE>")) {
                    wordCount++;
                    totalCharacters += word.length();
                    if (i != wordsList.size() - 1 && !wordsList.get(i + 1).equals("<NEWLINE>")) {
                        totalCharacters++; // Add 1 to character count for space after each word except the last one and <NEWLINE>
                    }
                } else {
                    totalCharacters++; // Add 1 to character count for "<NEWLINE>"
                }
            }
        }
        metrics.setWordCount(wordCount);
        metrics.setCharacterCount(totalCharacters);

        //Update Metrics error count.
        List<Error> errors = spellChecker.getErrors();
        metrics.setErrorCount(errors.size());


        //Initialize error type counts
        int doubleWordCount = 0;
        int miscapitalizationCount = 0;
        int misspellingCount = 0;

        //Iterate over errors and update error type counts
        for (Error error : errors) {
            int errorCode = error.getErrorCode();
            switch (errorCode) {
                case 0x00:
                    doubleWordCount++;
                    break;
                case 0x01:
                case 0x11:
                case 0x13:
                    miscapitalizationCount++;
                    break;
                case 0x02:
                    misspellingCount++;
                    break;
                case 0x12:
                    miscapitalizationCount++;
                    misspellingCount++;
                    break;
            }
        }

        //Update metrics with error type counts
        metrics.setDoubleWordsCount(doubleWordCount);
        metrics.setMiscapitalizationsCount(miscapitalizationCount);
        metrics.setMisspellingsCount(misspellingCount);

        int lineCount = 1;
        for (String word : wordsList) {
            if (word.equals("<NEWLINE>")) {
                lineCount++;
            }
        }
        // Set the line count in metrics
        metrics.setLineCount(lineCount);
    }


    /**
     * Returns the document being checked.
     *
     * @return The document being checked
     */
    public Document getDocument(){
        return this.document;
    }

    /**
     * Returns the user dictionary used for checking.
     *
     * @return The user dictionary
     */
    public UserDictionary getUserDictionary(){
        return this.userDictionary;
    }

    /**
     * Returns the list of errors found in the document.
     *
     * @return The list of errors
     */
    public List<Error> getErrors() {
        return spellChecker.getErrors();
    }

    /**
     * Returns the metrics for the application.
     *
     * @return The metrics
     */
    public Metrics getMetrics() {
        return metrics;
    }

    /**
     * Applies a suggestion to an error.
     * It replaces the error word in the document with the suggestion, increments the correction count, and updates the metrics.
     *
     * @param error      The error to apply the suggestion to
     * @param suggestion The suggestion to apply
     */
    public void applySuggestion(Error error, String suggestion) {
        spellChecker.applySuggestion(error, suggestion);
        metrics.setCorrectionCount(metrics.getCorrectionCount() + 1);
        updateMetrics();

    }

    /**
     * Ignores a word for the current session.
     * It removes all errors containing the word from the errors list and replaces the error word in the document with the word.
     *
     * @param error The error containing the word to ignore
     */
    public void ignoreWordForSession(Error error) {
        // Get the error word and strip punctuation from both ends
        String errorWord = error.getErrorWord().replaceAll("^[\\p{Punct}\\s]+|[\\p{Punct}\\s]+$", "");

        // Get the list of errors from the spell checker
        List<Error> errors = spellChecker.getErrors();

        // Create a list to hold the errors to remove
        List<Error> errorsToRemove = new ArrayList<>();

        // Iterate over the errors
        for (Error e : errors) {
            // Strip punctuation from both ends of the error word in the error list
            String strippedErrorWord = e.getErrorWord().replaceAll("^[\\p{Punct}\\s]+|[\\p{Punct}\\s]+$", "");
            // Check if the stripped error word is the same as the one to ignore
            if (strippedErrorWord.equals(errorWord)) {
                // Add the error to the list of errors to remove
                errorsToRemove.add(e);
            }
        }

        // Remove the errors from the errors list
        errors.removeAll(errorsToRemove);

        // Apply the suggestion for each error in the list of errors to remove
        for (Error e : errorsToRemove) {
            spellChecker.applySuggestion(e, errorWord);
        }
    }

<<<<<<< HEAD

    public void endSession() {
        // Discard the spell checker
        spellChecker = null;

        // Discard the user dictionary
        userDictionary = null;

        // Discard the document
        document = null;

        // Discard the metrics
        metrics = null;
    }


=======
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
}

