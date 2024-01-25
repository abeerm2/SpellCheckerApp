package SpellCheckerApp;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * The SpellChecker class is responsible for checking the spelling of a document against a user dictionary.
 * It maintains a list of errors found in the document. The class provides methods to clear errors and generate a new list of errors.
 * This class is a key component of the spell checking process, enabling the identification and correction of spelling errors.
 *
 * @author Ameen
 */
public class SpellChecker {
    // The document to be checked
    private Document document;
    // The dictionary used for checking
    private UserDictionary userDictionary;
    // The list of errors found in the document
    private List<Error> errors;

    /**
     * Constructor for the SpellChecker class.
     * Initializes the document, user dictionary, and an empty list of errors.
     *
     * @param document       The document to be checked
     * @param userDictionary The dictionary used for checking
     */
    public SpellChecker(Document document, UserDictionary userDictionary) {
        this.document = document;
        this.userDictionary = userDictionary;
        this.errors = new ArrayList<>();
    }

    /**
     * Clears the list of errors.
     */
    public void clearErrors() {
        this.errors.clear();
    }

    /**
     * Generates a list of errors in the document.
     * It iterates over the words in the document and checks each word that is not a special character or a number.
     */
    public void generateErrors() {
        // Get the list of words in the document
        List<String> words = document.iterateWords();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
<<<<<<< HEAD
            if (!word.equals("<NEWLINE>") && !word.equals("<START>") && !word.matches("\\d+") && !userDictionary.getUserAddedWords().contains(word)) {
=======
            // Check if the word is not a special character or a number
            if (!word.equals("<NEWLINE>") && !word.equals("<START>") && !word.matches("\\d+") && !word.equals("")) {
                // Check the word for errors
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
                checkWord(word, i);
            }
        }
    }


    /**
     * Checks a word for errors.
     * It checks if the word is a double word, a miscapitalization, or a misspelling.
     * If an error is found, it creates an error with the appropriate error code.
     *
     * @param word  The word to check
     * @param index The index of the word in the document
     */
    public void checkWord(String word, int index) {

        // Error code table:
        // 0x00: 2nd double word. Suggestions is to delete it.
        // 0x01: Miscapitalization, don't need to capitalize first letter.
        // 0x02: Misspelling and don't need to capitalize first letter.
        // 0x11: Miscapitalization, need to capitalize first letter.
        // 0x12: Misspelling and need to capitalize first letter.
        // 0x13: Valid word, first letter needs to be capitalized though.


        // Get the context of the word
        List<String> contextWords = document.getContext(index);
        String prevWord = contextWords.get(0);

        String firstLetterLowerCased = Character.toLowerCase(word.charAt(0)) + word.substring(1);

        //Double word
        if (word.equalsIgnoreCase(prevWord) && !prevWord.endsWith(".")) {
            createError(word, index, 0x00);
            return;
        }

        //Word in User Dictionary
        String strippedWord = word.replaceAll("\\p{Punct}", "");

        HashSet<String> userAddedWords = userDictionary.getUserAddedWords();

        if (userAddedWords.contains(strippedWord)) {
            return;
        }

        //valid word following a period.
<<<<<<< HEAD
        boolean b = prevWord.endsWith(".") || prevWord.equals("<START>") || prevWord.equals("<NEWLINE>");

        if (b && Character.isUpperCase(word.charAt(0)) && (userDictionary.doesWordExist(word) || userDictionary.doesWordExist(firstLetterLowerCased))) {
=======
        boolean upperCaseNext = prevWord.endsWith(".") || prevWord.endsWith("?") || prevWord.endsWith("!") || prevWord.equals("<START>") || prevWord.equals("<NEWLINE>");


        if (upperCaseNext &&
                Character.isUpperCase(word.charAt(0)) &&
                (userDictionary.doesWordExist(word) || userDictionary.doesWordExist(firstLetterLowerCased))) {
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
            return;
        }

        //valid word not following a period.
<<<<<<< HEAD
        if (!b && userDictionary.doesWordExist(word)) {
=======
        if (!upperCaseNext && userDictionary.doesWordExist(word)) {
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41

            return;
        }


        // Check for capitalization after periods and whitespaces.
<<<<<<< HEAD
        if (b && !Character.isUpperCase(word.charAt(0))) {
=======
        if (upperCaseNext && !Character.isUpperCase(word.charAt(0))) {
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41

            //This is a valid word but needs to have its first letter capitalized.
            if (userDictionary.doesWordExist(word)) {
                createError(word, index, 0x13);
                return;
            }
            //This is a miscapitalized word and needs to have its first letter capitalized.
            for (int i = 0; i < (1 << word.length()); i++) {
                StringBuilder wordCheck = new StringBuilder(word);
                for (int j = 0; j < word.length(); j++) {
                    if (((i >> j) & 1) == 1) {
                        wordCheck.setCharAt(j, Character.toUpperCase(word.charAt(j)));
                    } else {
                        wordCheck.setCharAt(j, Character.toLowerCase(word.charAt(j)));
                    }
                }
                if (userDictionary.doesWordExist(wordCheck.toString())) {
                    createError(word, index, 0x11);
                    return;
                }
            }
            //This is a misspelled word and needs to have its first letter capitalized.
            createError(word, index, 0x12);
            return;
        }

        //This is a miscapitalized word and DOES NOT need to have its first letter capitalized.
        for (int i = 0; i < (1 << word.length()); i++) {
            StringBuilder wordCheck = new StringBuilder(word);
            for (int j = 0; j < word.length(); j++) {
                if (((i >> j) & 1) == 1) {
                    wordCheck.setCharAt(j, Character.toUpperCase(word.charAt(j)));
                } else {
                    wordCheck.setCharAt(j, Character.toLowerCase(word.charAt(j)));
                }
            }
            if (userDictionary.doesWordExist(wordCheck.toString())) {
                createError(word, index, 0x01);
                return;
            }
        }

        //This is a misspelled word and DOES NOT need to have its first letter capitalized.
        createError(word, index, 0x02);
        return;


    }

    /**
     * Creates an error with the specified word, index, and error code.
     * It also creates a new Correction for the error and generates suggestions.
     *
     * @param word      The word that contains the error
     * @param index     The index of the word in the document
     * @param errorCode The type of error
     */
    private void createError(String word, int index, int errorCode) {
        // Create a new Error and add it to the list
        Error error = new Error(word, index, userDictionary, document, errorCode);
        errors.add(error);

        // Create a new Correction for the error and generate suggestions
        Correction correction = new Correction(error, userDictionary);
        List<String> suggestions = correction.generateSuggestions();

        // Add the suggestions to the error
        error.setSuggestions(suggestions);
    }

    /**
     * Returns the list of errors found in the document.
     *
     * @return The list of errors
     */
    public List<Error> getErrors() {
        return errors;
    }

<<<<<<< HEAD

=======
    /**
     * Applies a suggestion to an error.
     * It replaces the error word in the document with the suggestion and removes the error from the errors list.
     *
     * @param error      The error to apply the suggestion to
     * @param suggestion The suggestion to apply
     */
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
    public void applySuggestion(Error error, String suggestion) {
        // Replace the error word in the document with the suggestion
        document.replaceWord(error.getLocation(), suggestion);

        // Remove the error from the errors list
        errors.remove(error);
    }

}
