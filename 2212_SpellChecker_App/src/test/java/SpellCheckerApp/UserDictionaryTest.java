package SpellCheckerApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

/**
 * This class is responsible for unit testing the UserDictionary class in the SpellCheckerApp package.
 * It ensures that all the methods and functionalities of the UserDictionary class work correctly individually.
 * @author Ameen
 */
public class UserDictionaryTest {
    // Instance of the UserDictionary to be tested
    private UserDictionary userDictionary;

    /**
     * This method sets up the test environment before each test.
     * It creates a new instance of UserDictionary.
     *
     * @throws IOException If an I/O error occurs while setting up the test environment
     */
    @BeforeEach
    public void setUp() throws IOException {
        // Create a new UserDictionary instance with the name "dict.txt"
        userDictionary = new UserDictionary("dict.txt"); // assuming UserDictionary takes a filename as a parameter
    }

    /**
     * This method tests the addWord method of the UserDictionary class.
     * It checks if the user-added words in the UserDictionary contain the new word after adding it.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testAddWord() throws IOException {
        // New word to be added to the UserDictionary
        String newWord = "newword";
        // Add the new word to the UserDictionary
        userDictionary.addWord(newWord);
        // Check if the user-added words in the UserDictionary contain the new word
        assertTrue(userDictionary.getUserAddedWords().contains(newWord));
    }

    /**
     * This method tests the addWord method of the UserDictionary class for multiple words.
     * It checks if the user-added words in the UserDictionary contain the new words after adding them.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testAddMultipleWords() throws IOException {
        // New words to be added to the UserDictionary
        String[] newWords = {"newword1", "newword2", "newword3"};
        // Add the new words to the UserDictionary
        for (String word : newWords) {
            userDictionary.addWord(word);
        }
        // Check if the user-added words in the UserDictionary contain the new words
        for (String word : newWords) {
            assertTrue(userDictionary.getUserAddedWords().contains(word));
        }
    }

    /**
     * This method tests the removeWord method of the UserDictionary class.
     * It checks if the user-added words in the UserDictionary do not contain the word after removing it.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testRemoveWord() throws IOException {
        // Word to be removed from the UserDictionary
        String wordToRemove = "testword123";
        // Add the word to the UserDictionary
        userDictionary.addWord(wordToRemove);
        // Check if the user-added words in the UserDictionary contain the word
        assertTrue(userDictionary.getUserAddedWords().contains(wordToRemove));
        // Remove the word from the UserDictionary
        userDictionary.removeWord(wordToRemove);
        // Check if the user-added words in the UserDictionary do not contain the word
        assertFalse(userDictionary.getUserAddedWords().contains(wordToRemove));
    }

    /**
     * This method tests the removeWord method of the UserDictionary class for a non-existing word.
     * It checks if the user-added words in the UserDictionary do not contain the word before and after trying to remove it.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testRemoveNonExistingWord() throws IOException {
        // Non-existing word to be removed from the UserDictionary
        String wordToRemove = "nonexistingword";
        // Check if the user-added words in the UserDictionary do not contain the word
        assertFalse(userDictionary.getUserAddedWords().contains(wordToRemove));
        // Try to remove the word from the UserDictionary
        userDictionary.removeWord(wordToRemove);
        // Check if the user-added words in the UserDictionary still do not contain the word
        assertFalse(userDictionary.getUserAddedWords().contains(wordToRemove));
    }

    /**
     * This method tests the doesWordExist method of the UserDictionary class.
     * It checks if the method correctly identifies whether a word exists in the UserDictionary.
     *
     * @throws IOException If an I/O error occurs during the test
     */
    @Test
    public void testDoesWordExist() throws IOException {
        // Existing word in the UserDictionary
        String existingWord = "apple";
        // Add the word to the UserDictionary
        userDictionary.addWord(existingWord);
        // Check if the word exists in the UserDictionary
        assertTrue(userDictionary.doesWordExist(existingWord));
        // Non-existing word in the UserDictionary
        String nonExistingWord = "NOTAREALWORD";
        // Check if the word does not exist in the UserDictionary
        assertFalse(userDictionary.doesWordExist(nonExistingWord));
    }
}
