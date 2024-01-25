package SpellCheckerApp;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

/**
 * This class tests the Test class in the SpellCheckerApp package.
 * @author Ameen
 */
public class ErrorTest {
    private Error error;
    private UserDictionary userDictionary;
    private Document document;

    /**
     * Sets up the test environment before each test.
     * It creates a new UserDictionary, Document, and Error.
     *
     * @throws IOException If an I/O error occurs
     */
    @BeforeEach
    public void setUp() throws IOException {
        userDictionary = new UserDictionary("dict.txt");
        document = new Document("");
        error = new Error("errorWord", 1, userDictionary, document, 1);
    }

    /**
     * Tests the getErrorWord method of the Error class.
     * It checks if the method correctly returns the error word.
     */
    @Test
    public void testGetErrorWord() {
        assertEquals("errorWord", error.getErrorWord());
    }

    /**
     * Tests the setErrorWord method of the Error class.
     * It checks if the method correctly sets the error word.
     */
    @Test
    public void testSetErrorWord() {
        error.setErrorWord("newErrorWord");
        assertEquals("newErrorWord", error.getErrorWord());
    }

    /**
     * Tests the getLocation method of the Error class.
     * It checks if the method correctly returns the location of the error.
     */
    @Test
    public void testGetLocation() {
        assertEquals(1, error.getLocation());
    }

    /**
     * Tests the setLocation method of the Error class.
     * It checks if the method correctly sets the location of the error.
     */
    @Test
    public void testSetLocation() {
        error.setLocation(2);
        assertEquals(2, error.getLocation());
    }

    /**
     * Tests the getSuggestions method of the Error class.
     * It checks if the method correctly returns the list of suggestions.
     */
    @Test
    public void testGetSuggestions() {
        assertTrue(error.getSuggestions().isEmpty());
    }

    /**
     * Tests the setSuggestions method of the Error class.
     * It checks if the method correctly sets the list of suggestions.
     */
    @Test
    public void testSetSuggestions() {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("suggestion1");
        suggestions.add("suggestion2");
        error.setSuggestions(suggestions);
        assertEquals(suggestions, error.getSuggestions());
    }

    /**
     * Tests the getErrorCode method of the Error class.
     * It checks if the method correctly returns the error code.
     */
    @Test
    public void testGetErrorCode() {
        assertEquals(1, error.getErrorCode());
    }

    /**
     * Tests the setErrorCode method of the Error class.
     * It checks if the method correctly sets the error code.
     */
    @Test
    public void testSetErrorCode() {
        error.setErrorCode(2);
        assertEquals(2, error.getErrorCode());
    }
}