package SpellCheckerApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * This class is responsible for testing the integration of the SpellCheckerApplication class in the SpellCheckerApp package.
 * It ensures that all the methods and functionalities of the SpellCheckerApplication class work correctly together.
 * @author Ameen
 */
public class SpellCheckerApplicationIntegrationTest {
    // Instance of the SpellCheckerApplication to be tested
    private SpellCheckerApplication spellCheckerApplication;
    // Document instance to be used in the tests
    private Document document;

    /**
     * This method sets up the test environment before each test.
     * It creates a new instance of SpellCheckerApplication and a Document.
     *
     * @throws IOException If an I/O error occurs while setting up the test environment
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a new instance of SpellCheckerApplication
        spellCheckerApplication = new SpellCheckerApplication();
        // Create a new Document instance with the name "testDoc.txt"
        document = new Document("testDoc.txt");
    }

    /**
     * This method tests the workflow of the SpellCheckerApplication class.
     * It checks if the methods of the SpellCheckerApplication class work correctly together.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testSpellCheckerApplicationWorkflow() throws IOException {
        // Start a new session with the document
        spellCheckerApplication.startSession(document);
        // Check if the document in the spellCheckerApplication is the same as the one we started the session with
        assertEquals(document, spellCheckerApplication.getDocument());

        // Recalculate the errors in the document
        spellCheckerApplication.recalculateErrors();
        // Check if the errors in the document are not null after recalculation
        assertNotNull(spellCheckerApplication.getErrors());

        // Update the metrics of the spellCheckerApplication
        spellCheckerApplication.updateMetrics();
        // Get the metrics from the spellCheckerApplication
        Metrics metrics = spellCheckerApplication.getMetrics();
        // Check if the metrics are not null
        assertNotNull(metrics);

        // Create a new Error instance
        Error error = new Error("errorWord", 0, spellCheckerApplication.getUserDictionary(), document, 0x00);
        // Suggestion for the error
        String suggestion = "suggestion";
        // Apply the suggestion to the error
        spellCheckerApplication.applySuggestion(error, suggestion);
        // Check if the correction count in the metrics has increased by 1
        assertEquals(1, metrics.getCorrectionCount());

        // Ignore the word for the session
        spellCheckerApplication.ignoreWordForSession(error);
        // Check if the errors in the document no longer contain the ignored word
        assertFalse(spellCheckerApplication.getErrors().contains(error));
    }
}
