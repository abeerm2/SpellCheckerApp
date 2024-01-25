package SpellCheckerApp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an error object in the spell checker app.
 * It contains the error word, its location, a list of suggestions, and an error code.
 * @author Theo Cho
 */
public class Error {
    // The word that contains the error
    private String errorWord;
    // The location of the error in the document
    private int location;
    // The list of suggestions for the error
    private List<String> suggestions;
    // The type of error
    private int errorCode;

    /**
     * Constructor for the Error class.
     * Initializes the error word, its location, an empty list of suggestions, and the error code.
     *
     * @param errorWord      The word that contains the error
     * @param location       The location of the error in the document
     * @param userDictionary The dictionary used to check the error word
     * @param document       The document where the error is located
     * @param errorCode      The type of error.
     *                       0x00: 2nd double word. Suggestions is to delete it.
     *                       0x01: Miscapitalization, don't need to capitalize first letter.
     *                       0x02: Misspelling and don't need to capitalize first letter.
     *                       0x11: Miscapitalization, need to capitalize first letter.
     *                       0x12: Misspelling and need to capitalize first letter.
     *                       0x13: Valid word, first letter needs to be capitalized though.
     */
    public Error(String errorWord, int location, UserDictionary userDictionary, Document document, int errorCode) {
        this.errorWord = errorWord;
        this.location = location;
        this.suggestions = new ArrayList<>();
        this.errorCode = errorCode;
    }

    /**
     * Returns the word that contains the error.
     *
     * @return The word that contains the error
     */
    public String getErrorWord() {
        return errorWord;
    }

    /**
     * Sets the word that contains the error.
     *
     * @param errorWord The word that contains the error
     */
    public void setErrorWord(String errorWord) {
        this.errorWord = errorWord;
    }

    /**
     * Returns the location of the error in the document.
     *
     * @return The location of the error
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the location of the error in the document.
     *
     * @param location The location of the error
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Returns the list of suggestions for the error.
     *
     * @return The list of suggestions
     */
    public List<String> getSuggestions() {
        return suggestions;
    }

    /**
     * Sets the list of suggestions for the error.
     *
     * @param suggestions The list of suggestions
     */
    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * Sets the type of error.
     *
     * @param errorCode The type of error.
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Returns the type of error.
     *
     * @return The type of error
     */
    public int getErrorCode() {
        return this.errorCode;
    }
}
