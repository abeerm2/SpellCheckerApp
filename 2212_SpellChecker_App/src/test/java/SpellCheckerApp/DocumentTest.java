package SpellCheckerApp;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

/**
 * This class tests the Document class in the SpellCheckerApp package.
 * @author Ameen
 */
class DocumentTest {
    private Document document;
    private String testFilePath = "testDoc2.txt";

    /**
     * Sets up the test environment before each test.
     * It creates a new Document from a file named "testDoc2.txt" and opens the file.
     *
     * @throws IOException If an I/O error occurs
     */
    @BeforeEach
    void setUp() throws IOException {
        // Create a new Document object for each test
        document = new Document(testFilePath);

        // Open and read the file
        document.openFile();
    }


    /**
     * Tests the openFile method of the Document class.
     * It checks if the method correctly opens the file and reads the words.
     */
    @Test
    void testOpenFile() {
        List<String> words = document.iterateWords();
        assertEquals(Arrays.asList("<START>","This", "is", "a", "test", "file.", "<NEWLINE>", "New", "line.", "<NEWLINE>", "<NEWLINE>", "<NEWLINE>", "Three", "lines", "later."), words);
    }

    /**
     * Tests the saveFile method of the Document class.
     * It checks if the method correctly saves the words to the file.
     *
     * @throws IOException If an I/O error occurs
     */
    @Test
    void testSaveFile() throws IOException {
        File testfile = new File(testFilePath); //NOT SURE IF THIS IS RIGHT
        document.saveFile(testfile); //NOT SURE IF THIS IS RIGHT
        BufferedReader reader = new BufferedReader(new FileReader(testFilePath));
        String line;
        List<String> words = new ArrayList<>();
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
        assertEquals(Arrays.asList("<START>","This", "is", "a", "test", "file.", "<NEWLINE>", "New", "line.", "<NEWLINE>", "<NEWLINE>", "<NEWLINE>", "Three", "lines", "later."), words);
        reader.close();
    }

    /**
     * Tests the saveFile method of the Document class.
     * It checks if the method correctly saves the words to a temporary file.
     *
     * @throws IOException If an I/O error occurs
     */
    @Test
    void testSaveFile2() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        // Write the expected contents to the temporary file
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        for (String word : Arrays.asList("<START>","This", "is", "a", "test", "file.", "<NEWLINE>", "New", "line.", "<NEWLINE>", "<NEWLINE>", "<NEWLINE>", "Three", "lines", "later.")) {
            if(!word.equals("<START>")){
                if (word.equals("<NEWLINE>")) {
                    // Write a newline character to the file
                    writer.write("\n");
                } else {
                    // Write the word followed by a space to the file
                    writer.write(word + " ");
                }
            }

        }
        writer.close();

        // Call the saveFile method on the document object
        document.saveFile(tempFile); //NOT SURE IF THIS IS RIGHT

        // Compare the contents of the test file and the temporary file
        BufferedReader testReader = new BufferedReader(new FileReader(testFilePath));
        BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
        String testLine;
        String tempLine;
        while ((testLine = testReader.readLine()) != null && (tempLine = tempReader.readLine()) != null) {
            assertEquals(tempLine, testLine);
        }
        // Check if both files have the same number of lines
        assertNull(testReader.readLine());
        assertNull(tempReader.readLine());

        testReader.close();
        tempReader.close();
    }

    /**
     * Tests the discardChanges method of the Document class.
     * It checks if the method correctly discards the changes and leaves the document empty.
     */
    @Test
    void testDiscardChanges() {
        document.discardChanges();
        List<String> words = document.iterateWords();
        assertTrue(words.isEmpty());
    }

    /**
     * Tests the getContext method of the Document class.
     * It checks if the method correctly returns the context of a word at a given index.
     */
    @Test
    void testGetContext() {
        List<String> context = document.getContext(2);
        assertEquals(Arrays.asList("This", "is", "a"), context);
    }

    /**
     * Tests the getContext method of the Document class.
     * It checks if the method correctly returns the context of a word at the start of the document.
     */
    @Test
    void testGetContext2() {
        List<String> context = document.getContext(0);
        assertEquals(Arrays.asList("", "<START>", "This"), context);
    }

    /**
     * Tests the replaceWord method of the Document class.
     * It checks if the method correctly replaces a word at a given index.
     */
    @Test
    void testReplaceWord() {
        List<String> words = document.iterateWords();
        assertEquals(Arrays.asList("<START>","This", "is", "a", "test", "file.", "<NEWLINE>", "New", "line.", "<NEWLINE>", "<NEWLINE>", "<NEWLINE>", "Three", "lines", "later."), words);
        document.replaceWord(2,"NOT");
        List<String> newWords = document.iterateWords();
        assertEquals(Arrays.asList("<START>","This", "NOT", "a", "test", "file.", "<NEWLINE>", "New", "line.", "<NEWLINE>", "<NEWLINE>", "<NEWLINE>", "Three", "lines", "later."), newWords);

    }

}
