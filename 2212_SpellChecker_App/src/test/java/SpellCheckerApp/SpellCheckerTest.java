package SpellCheckerApp;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

/**
 * This class is responsible for unit testing the SpellChecker class in the SpellCheckerApp package.
 * It ensures that all the methods and functionalities of the SpellChecker class work correctly individually.
 * @author Ameen
 */
class SpellCheckerTest {
    // Instance of the SpellChecker to be tested
    private SpellChecker spellChecker;
    // Document instance to be used in the tests
    private Document document;
    // Instance of the UserDictionary to be used in the tests
    private UserDictionary userDictionary;
    // File instance to be used in the tests
    private File testFile;

    /**
     * This method sets up the test environment before each test.
     * It creates a new instance of UserDictionary, Document, and SpellChecker, and writes content to the test file.
     *
     * @throws IOException If an I/O error occurs while setting up the test environment
     */
    @BeforeEach
    void setUp() throws IOException {
        // Create a new UserDictionary instance with the name "dict.txt"
        userDictionary = new UserDictionary("dict.txt");

        // Create a new file named "testfile.txt"
        testFile = new File("testfile.txt");

        // Write content to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        String testDocContent = "hello hEllo. boy. bOy. prce Hello prce\ntest.";
        writer.write(testDocContent);
        writer.close();

        // Create a new Document object for each test
        document = new Document(testFile.getAbsolutePath());

        // Open the document file
        document.openFile();

        // Create a new SpellChecker object for each test
        spellChecker = new SpellChecker(document, userDictionary);
    }

    /**
     * This method cleans up the test environment after each test.
     * It deletes the test file.
     */
    @AfterEach
    void tearDown() {
        // Delete the file after the tests are done
        testFile.delete();
    }

    /**
     * This method tests the generateErrors method of the SpellChecker class.
     * It checks if the list of errors contains the expected words.
     */
    @Test
    public void testErrorList() {
        // Generate errors in the document
        spellChecker.generateErrors();
        // Get the list of errors from the spellChecker
        List<Error> errors = spellChecker.getErrors();
        // Create a list of expected error words
        List<String> expectedErrorWords = new ArrayList<>();
        for (String word : document.iterateWords()) {
            if (!word.equals("<NEWLINE>") && !word.equals("<START>")) {
                expectedErrorWords.add(word);
            }
        }
        // Create a list of actual error words
        List<String> actualErrorWords = new ArrayList<>();
        for (Error error : errors) {
            actualErrorWords.add(error.getErrorWord());
        }
        // Check if the list of actual error words is the same as the list of expected error words
        assertEquals(expectedErrorWords, actualErrorWords);
    }

    /**
     * This method tests the generateErrors method of the SpellChecker class.
     * It checks if the list of suggestions for each error is the same as the expected list of suggestions.
     */
    @Test
    public void testErrorSuggestionsForEachWord() {
        // Generate errors in the document
        spellChecker.generateErrors();
        // Get the list of errors from the spellChecker
        List<Error> errors = spellChecker.getErrors();

        // Define the expected suggestions for each word in testDocContent
        List<List<String>> expectedSuggestionsForEachWord = Arrays.asList(
                Arrays.asList("Hello"),  // Expected suggestions for "hello"
                Arrays.asList("hello."),  // Expected suggestions for "hEllo."
                Arrays.asList("Boy."),      // Expected suggestions for "boy."
                Arrays.asList("Boy."),      // Expected suggestions for "bOy."
                Arrays.asList("Pre", "Prc", "Perce", "Price", "Prec", "Pr ce", "Pr-ce", "Prc e", "Prc-e"),  // Expected suggestions for "prce"
                Arrays.asList("hello"),  // Expected suggestions for "Hello"
                Arrays.asList("pre", "prc", "perce", "price", "prec", "pr ce", "pr-ce", "prc e", "prc-e"),  // Expected suggestions for "prce"
                Arrays.asList("Test.")  // Expected suggestions for "prce"
        );

        // Check the actual suggestions for each error
        for (int i = 0; i < errors.size(); i++) {
            List<String> expectedSuggestions = expectedSuggestionsForEachWord.get(i);
            List<String> actualSuggestions = errors.get(i).getSuggestions();
            assertEquals(expectedSuggestions, actualSuggestions);
        }
    }

    /**
     * This method tests the applySuggestion method of the SpellChecker class.
     * It checks if the error is no longer in the errors list after applying a suggestion,
     * and if the word at the error's location in the document is now the suggestion.
     */
    @Test
    public void testApplySuggestion() {
        // Generate errors for the document
        spellChecker.generateErrors();

        // Get the list of errors
        List<Error> errors = spellChecker.getErrors();

        // Check that the errors list is not empty
        assertFalse(errors.isEmpty());

        // Get the first error
        Error error = errors.get(0);

        // Get suggestions for the error
        List<String> suggestions = error.getSuggestions();

        // Check that the suggestions list is not empty
        assertFalse(suggestions.isEmpty());

        // Apply the first suggestion to the error
        String suggestion = suggestions.get(0);
        spellChecker.applySuggestion(error, suggestion);

        // Check that the error is no longer in the errors list
        assertFalse(spellChecker.getErrors().contains(error));

        // Check that the word at the error's location in the document is now the suggestion
        assertEquals(suggestion, document.iterateWords().get(error.getLocation()));
    }

    /**
     * This method tests the applySuggestion method of the SpellChecker class for all errors.
     * It checks if the list of errors is empty after applying suggestions to all errors.
     */
    @Test
    public void testApplyAllSuggestions() {
        // Generate errors for the document
        spellChecker.generateErrors();

        // Get a copy of the list of errors
        List<Error> errors = new ArrayList<>(spellChecker.getErrors());

        // Apply the first suggestion to each error
        for (Error error : errors) {
            // Get suggestions for the error
            List<String> suggestions = error.getSuggestions();

            // Check that the suggestions list is not empty
            assertFalse(suggestions.isEmpty());

            // Apply the first suggestion to the error
            String suggestion = suggestions.get(0);
            spellChecker.applySuggestion(error, suggestion);

            // Check that the word at the error's location in the document is now the suggestion
            assertEquals(suggestion, document.iterateWords().get(error.getLocation()));
        }

        // Generate errors for the document again
        spellChecker.generateErrors();

        // Get the list of errors
        errors = spellChecker.getErrors();

        // Check that the errors list is now empty
        assertTrue(errors.isEmpty());

    }

    /**
     * This method tests the checkWord method of the SpellChecker class.
     * Add assertions based on the expected behavior of your checkWord method.
     */
    @Test
    public void testCheckWord() {
        spellChecker.checkWord("word", 0);
        // Add assertions here based on the expected behavior of your checkWord method
    }

    /**
     * This method tests the createError method of the SpellChecker class.
     * It checks if the list of errors contains the expected error word after calling the checkWord method.
     */
    @Test
    public void testCreateError() {
        spellChecker.checkWord("errorWord", 0);
        List<Error> errors = spellChecker.getErrors();
        assertFalse(errors.isEmpty());
        assertEquals("errorWord", errors.get(0).getErrorWord());
        // Add more assertions here based on the expected behavior of your createError method
    }

    /**
     * This method tests the getErrors method of the SpellChecker class.
     * It checks if the list of errors contains the expected error word after calling the checkWord method.
     */
    @Test
    public void testGetErrors() {
        List<Error> errors = spellChecker.getErrors();
        assertTrue(errors.isEmpty());
        spellChecker.checkWord("errorWord", 0);
        errors = spellChecker.getErrors();
        assertFalse(errors.isEmpty());
        assertEquals("errorWord", errors.get(0).getErrorWord());
    }

}
