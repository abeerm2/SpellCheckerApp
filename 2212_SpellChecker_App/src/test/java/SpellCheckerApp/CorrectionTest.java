package SpellCheckerApp;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;
/**
 * This class tests the Correction class in the SpellCheckerApp package.
 * @author Ameen
 */
class CorrectionTest {
    private Correction correction;

    /**
     * Sets up the test environment before each test.
     * It creates a new UserDictionary, Document, Error, and Correction.
     *
     * @throws IOException If an I/O error occurs
     */
    @BeforeEach
    void setUp() throws IOException {
        // Create a new Dictionary object for each test
        UserDictionary userDictionary = new UserDictionary("dict.txt");


        Document document = new Document("testDoc.txt");

        // Create a new Error object for each test
        Error error = new Error("golen", 1, userDictionary, document,0x02);

        // Create a new Correction object for each test
        correction = new Correction(error, userDictionary);

    }

    /**
     * Tests the generateSuggestions method of the Correction class.
     * It checks if the method generates the correct suggestions for a given error.
     */
    @Test
    void testGenerateSuggestions() {
        // Generate suggestions for the error
        List<String> suggestions = correction.generateSuggestions();

        // Check if the correct suggestions are generated
        assertTrue(suggestions.contains("goen"));
        assertTrue(suggestions.contains("gol en"));
        assertTrue(suggestions.contains("gol-en"));

        // Check if incorrect suggestions are not generated
        assertFalse(suggestions.contains("banana"));
        assertFalse(suggestions.contains("apple"));
    }
}
