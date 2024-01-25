package SpellCheckerApp;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.HashSet;

/**
 * This class tests the Dictionary class in the SpellCheckerApp package.
 * @author Ameen
 */
class DictionaryTest {
    private Dictionary dictionary;

    /**
     * Sets up the test environment before each test.
     * It creates a new Dictionary from a file named "dict.txt".
     *
     * @throws IOException If an I/O error occurs
     */
    @BeforeEach
    void setUp() throws IOException {
        // Use a dictionary file named "dict.txt" in the current working directory
        // Create a new Dictionary object for each test
        dictionary = new Dictionary("dict.txt");
    }

    /**
     * Tests the getDict method of the Dictionary class.
     * It checks if the method returns a non-null dictionary.
     */
    @Test
    public void testGetDict() {
        HashSet<String> dict = dictionary.getDict();
        assertNotNull(dict);
    }

    /**
     * Tests the doesWordExist method of the Dictionary class.
     * It checks if the method correctly identifies existing and non-existing words.
     */
    @Test
    void testDoesWordExist() {
        assertTrue(dictionary.doesWordExist("\"apple\""));
        assertTrue(dictionary.doesWordExist(",,.\"',zoopharmacological..,,..,,"));
        assertTrue(dictionary.doesWordExist("millisecond"));
        assertTrue(dictionary.doesWordExist("pseudoscholastically"));


        assertFalse(dictionary.doesWordExist("asdiahgsd"));
        assertFalse(dictionary.doesWordExist("aisdhaisfugyhasf"));
        assertFalse(dictionary.doesWordExist("NOTAREALWORD"));
    }
}
