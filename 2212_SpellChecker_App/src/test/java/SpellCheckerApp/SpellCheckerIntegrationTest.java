package SpellCheckerApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for integration testing the SpellChecker class in the SpellCheckerApp package.
 * It ensures that the SpellChecker class works correctly with the UserDictionary and Document classes.
 * @author Ameen
 */
public class SpellCheckerIntegrationTest {
    // Instance of the SpellChecker to be tested
    private SpellChecker spellChecker;
    // Instance of the UserDictionary to be used in the tests
    private UserDictionary userDictionary;
    // Document instance to be used in the tests
    private Document document;

    /**
     * This method sets up the test environment before each test.
     * It creates a new instance of UserDictionary, Document, and SpellChecker.
     *
     * @throws IOException If an I/O error occurs while setting up the test environment
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a new UserDictionary instance with the name "dict.txt"
        userDictionary = new UserDictionary("dict.txt");
        // Create a new Document instance with the name "testDoc.txt"
        document = new Document("testDoc.txt");
        // Open the document file
        document.openFile();
        // Create a new SpellChecker instance with the document and user dictionary
        spellChecker = new SpellChecker(document, userDictionary);
    }

    /**
     * This method tests the SpellChecker class with the UserDictionary class.
     * It checks if the SpellChecker class correctly identifies errors based on the words in the UserDictionary.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testSpellCheckerWithUserDictionary() throws IOException {
        // New word to be added to the user dictionary
        String newWord = "farawey";
        // Add the new word to the user dictionary
        userDictionary.addWord(newWord);
        // Generate errors in the document
        spellChecker.generateErrors();
        // Check if the list of errors does not contain the new word
        assertFalse(spellChecker.getErrors().stream().anyMatch(error -> error.getErrorWord().equals(newWord)));

        // Clear the errors in the document
        spellChecker.clearErrors();
        // Remove the new word from the user dictionary
        userDictionary.removeWord(newWord);
        // Generate errors in the document again
        spellChecker.generateErrors();
        // Check if the list of errors now contains the new word
        assertTrue(spellChecker.getErrors().stream().anyMatch(error -> error.getErrorWord().equals(newWord)));
    }
}
