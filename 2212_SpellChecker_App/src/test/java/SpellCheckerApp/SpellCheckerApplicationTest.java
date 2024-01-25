package SpellCheckerApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for unit testing the SpellCheckerApplication class in the SpellCheckerApp package.
 * It ensures that all the methods and functionalities of the SpellCheckerApplication class work correctly individually.
 * @author Ameen
 */
public class SpellCheckerApplicationTest {
    // Instance of the SpellCheckerApplication to be tested
    private SpellCheckerApplication spellCheckerApplication;
    // Document instance to be used in the tests
    private Document document;

    /**
     * This method sets up the test environment before each test.
     * It creates a new instance of SpellCheckerApplication and a Document, and starts a session with the document.
     *
     * @throws IOException If an I/O error occurs while setting up the test environment
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a new instance of SpellCheckerApplication
        spellCheckerApplication = new SpellCheckerApplication();
        // Create a new Document instance with the name "testDoc.txt"
        document = new Document("testDoc.txt");
        // Start a new session with the document
        spellCheckerApplication.startSession(document);
    }

    /**
     * This method tests the startSession method of the SpellCheckerApplication class.
     * It checks if the document in the spellCheckerApplication is the same as the one we started the session with.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testStartSession() throws IOException {
        // Create a new Document instance with the name "testDoc.txt"
        Document newDocument = new Document("testDoc.txt");
        // Start a new session with the new document
        spellCheckerApplication.startSession(newDocument);
        // Check if the document in the spellCheckerApplication is the same as the new document we started the session with
        assertEquals(newDocument, spellCheckerApplication.getDocument());
    }

    /**
     * This method tests the recalculateErrors method of the SpellCheckerApplication class.
     * It checks if the errors in the document are not null after recalculation.
     */
    @Test
    public void testRecalculateErrors() {
        // Recalculate the errors in the document
        spellCheckerApplication.recalculateErrors();
        // Check if the errors in the document are not null after recalculation
        assertNotNull(spellCheckerApplication.getErrors());
    }

    /**
     * This method tests the updateMetrics method of the SpellCheckerApplication class.
     * It checks if the metrics are not null after updating.
     */
    @Test
    public void testUpdateMetrics() {
        // Update the metrics of the spellCheckerApplication
        spellCheckerApplication.updateMetrics();
        // Get the metrics from the spellCheckerApplication
        Metrics metrics = spellCheckerApplication.getMetrics();
        // Check if the metrics are not null
        assertNotNull(metrics);
    }

    /**
     * This method tests the getDocument method of the SpellCheckerApplication class.
     * It checks if the document returned by the method is the same as the document we started the session with.
     */
    @Test
    public void testGetDocument() {
        // Check if the document returned by the getDocument method is the same as the document we started the session with
        assertEquals(document, spellCheckerApplication.getDocument());
    }

    /**
     * This method tests the getUserDictionary method of the SpellCheckerApplication class.
     * It checks if the user dictionary is not null.
     */
    @Test
    public void testGetUserDictionary() {
        // Check if the user dictionary is not null
        assertNotNull(spellCheckerApplication.getUserDictionary());
    }

    /**
     * This method tests the getErrors method of the SpellCheckerApplication class.
     * It checks if the list of errors is not null.
     */
    @Test
    public void testGetErrors() {
        // Get the list of errors from the spellCheckerApplication
        List<Error> errors = spellCheckerApplication.getErrors();
        // Check if the list of errors is not null
        assertNotNull(errors);
    }

    /**
     * This method tests the getMetrics method of the SpellCheckerApplication class.
     * It checks if the metrics are not null.
     */
    @Test
    public void testGetMetrics() {
        // Get the metrics from the spellCheckerApplication
        Metrics metrics = spellCheckerApplication.getMetrics();
        // Check if the metrics are not null
        assertNotNull(metrics);
    }

    /**
     * This method tests the applySuggestion method of the SpellCheckerApplication class.
     * It checks if the correction count in the metrics has increased by 1 after applying a suggestion.
     */
    @Test
    public void testApplySuggestion() {
        // Create a new Error instance
        Error error = new Error("errorWord", 0, spellCheckerApplication.getUserDictionary(), document, 0x00);
        // Suggestion for the error
        String suggestion = "suggestion";
        // Apply the suggestion to the error
        spellCheckerApplication.applySuggestion(error, suggestion);
        // Get the metrics from the spellCheckerApplication
        Metrics metrics = spellCheckerApplication.getMetrics();
        // Check if the correction count in the metrics has increased by 1
        assertEquals(1, metrics.getCorrectionCount());
    }

    /**
     * This method tests the ignoreWordForSession method of the SpellCheckerApplication class.
     * It checks if the list of errors no longer contains the ignored word.
     */
    @Test
    public void testIgnoreWordForSession() {
        // Create a new Error instance
        Error error = new Error("errorWord", 0, spellCheckerApplication.getUserDictionary(), document, 0x00);
        // Ignore the word for the session
        spellCheckerApplication.ignoreWordForSession(error);
        // Get the list of errors from the spellCheckerApplication
        List<Error> errors = spellCheckerApplication.getErrors();
        // Check if the list of errors no longer contains the ignored word
        assertFalse(errors.contains(error));
    }
}
