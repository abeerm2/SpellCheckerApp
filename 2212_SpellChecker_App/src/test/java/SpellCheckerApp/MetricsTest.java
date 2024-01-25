package SpellCheckerApp;

<<<<<<< HEAD
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MetricsTest {
    private Metrics metrics;

    @BeforeEach
    void setUp() {
        metrics = new Metrics();
    }

    @Test
    void testMisspellingsCount() {
        assertEquals(0, metrics.getMisspellingsCount());
        metrics.setMisspellingsCount(5);
        assertEquals(5, metrics.getMisspellingsCount());
    }

    @Test
    void testMiscapitalizationsCount() {
        assertEquals(0, metrics.getMiscapitalizationsCount());
        metrics.setMiscapitalizationsCount(3);
        assertEquals(3, metrics.getMiscapitalizationsCount());
    }

    @Test
    void testDoubleWordsCount() {
        assertEquals(0, metrics.getDoubleWordsCount());
        metrics.setDoubleWordsCount(2);
        assertEquals(2, metrics.getDoubleWordsCount());
    }

    @Test
    void testCharacterCount() {
        assertEquals(0, metrics.getCharacterCount());
        metrics.setCharacterCount(5);
        assertEquals(5, metrics.getCharacterCount());
    }

    @Test
    void testLineCount() {
        assertEquals(0, metrics.getLineCount());
        metrics.setLineCount(3);
        assertEquals(3, metrics.getLineCount());
    }

    @Test
    void testWordCount() {
        assertEquals(0, metrics.getWordCount());
        metrics.setWordCount(2);
        assertEquals(2, metrics.getWordCount());
    }

    @Test
    void testErrorCount() {
        assertEquals(0, metrics.getErrorCount());
        metrics.setErrorCount(4);
        assertEquals(4, metrics.getErrorCount());
    }

    @Test
    void testCorrectionCount() {
        assertEquals(0, metrics.getCorrectionCount());
        metrics.setCorrectionCount(6);
        assertEquals(6, metrics.getCorrectionCount());
    }

    @Test
    void testManualCorrectionsCount() {
        assertEquals(0, metrics.getManualCorrectionsCount());
        metrics.setManualCorrectionsCount(7);
        assertEquals(7, metrics.getManualCorrectionsCount());
    }

    @Test
    void testAcceptedSuggestionsCount() {
        assertEquals(0, metrics.getAcceptedSuggestionsCount());
        metrics.setAcceptedSuggestionsCount(8);
        assertEquals(8, metrics.getAcceptedSuggestionsCount());
    }

    @Test
    void testWordDeletionsCount() {
        assertEquals(0, metrics.getWordDeletionsCount());
        metrics.setWordDeletionsCount(9);
        assertEquals(9, metrics.getWordDeletionsCount());
    }

}
=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Metrics class in the SpellCheckerApp package.
 * @author Sajeiv
 */
class MetricsTest {

    private Metrics metrics;
    @BeforeEach
    void setUp() throws IOException {
        // Create a new Metrics object for each test
        metrics = new Metrics();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // UNIT TEST CASES FOR ALL METRICS CLASS METHODS
    //---------------------------------------------------------------------------------------------------------------------------------------------
    @Test
        /* Junit Test for both Character Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testCharacterCount() {

        // Test to see if CharacterCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getCharacterCount(), 0);

        metrics.setCharacterCount(12);
        assertEquals(metrics.getCharacterCount(), 12);

        metrics.setCharacterCount(30);
        assertEquals(metrics.getCharacterCount(), 30);
    }

    @Test
        /* Junit Test for both Word Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testWordCount() {

        // Test to see if WordCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getWordCount(), 0);

        metrics.setWordCount(88);
        assertEquals(metrics.getWordCount(), 88);

        metrics.setWordCount(33);
        assertEquals(metrics.getWordCount(), 33);
    }

    @Test
        /* Junit Test for both Character Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testLineCount() {

        // Test to see if LineCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getLineCount(), 0);

        metrics.setLineCount(100);
        assertEquals(metrics.getLineCount(), 100);

        metrics.setLineCount(250);
        assertEquals(metrics.getLineCount(), 250);
    }

    @Test
        /* Junit Test for both Error Count setter and getter methods
        *  Both methods must set and get the same expected value in order to pass the test
        *  This way both methods are simultaneously tested in this Unit Test*/
    void testErrorCount() {

        // Test to see if ErrorCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getErrorCount(), 0);

        metrics.setErrorCount(10);
        assertEquals(metrics.getErrorCount(), 10);

        metrics.setErrorCount(78);
        assertEquals(metrics.getErrorCount(), 78);
    }

    @Test
        /* Junit Test for both Correction Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testCorrectionCount() {

        // Test to see if CorrectionCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getCorrectionCount(), 0);

        metrics.setCorrectionCount(23);
        assertEquals(metrics.getCorrectionCount(), 23);

        metrics.setCorrectionCount(44);
        assertEquals(metrics.getCorrectionCount(), 44);
    }

    @Test
        /* Junit Test for both Manual Correction Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testManualCorrectionsCount() {

        // Test to see if ManualCorrectionCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getManualCorrectionsCount(), 0);

        metrics.setManualCorrectionsCount(400);
        assertEquals(metrics.getManualCorrectionsCount(), 400);

        metrics.setManualCorrectionsCount(505);
        assertEquals(metrics.getManualCorrectionsCount(), 505);
    }

    @Test
        /* Junit Test for both Accepted Suggestions Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testAcceptedSuggestionsCount() {

        // Test to see if AcceptedSuggestionsCount is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getAcceptedSuggestionsCount(), 0);

        metrics.setAcceptedSuggestionsCount(233);
        assertEquals(metrics.getAcceptedSuggestionsCount(), 233);

        metrics.setAcceptedSuggestionsCount(15);
        assertEquals(metrics.getAcceptedSuggestionsCount(), 15);
    }

    @Test
        /* Junit Test for both Words Deletions Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testWordDeletionsCount() {

        // Test to see if Words Deletions Count is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getWordDeletionsCount(), 0);

        metrics.setWordDeletionsCount(999);
        assertEquals(metrics.getWordDeletionsCount(), 999);

        metrics.setWordDeletionsCount(35);
        assertEquals(metrics.getWordDeletionsCount(), 35);
    }

    @Test
        /* Junit Test for both Misspelling Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testMisspellingsCount() {

        // Test to see if Misspellings Count is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getMisspellingsCount(), 0);

        metrics.setMisspellingsCount(156);
        assertEquals(metrics.getMisspellingsCount(), 156);

        metrics.setMisspellingsCount(1);;
        assertEquals(metrics.getMisspellingsCount(), 1);
    }

    @Test
        /* Junit Test for both Miscapitalizations Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testMiscapitalizationsCount() {

        // Test to see if Miscapitalizations Count is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getMiscapitalizationsCount(), 0);

        metrics.setMiscapitalizationsCount(156);
        assertEquals(metrics.getMiscapitalizationsCount(), 156);

        metrics.setMiscapitalizationsCount(8);;
        assertEquals(metrics.getMiscapitalizationsCount(), 8);
    }

    @Test
        /* Junit Test for both Double Words Count setter and getter methods
         *  Both methods must set and get the same expected value in order to pass the test
         *  This way both methods are simultaneously tested in this Unit Test*/
    void testDoubleWordsCount() {

        // Test to see if Double Words Count is defaulted to 0, assuming nothing is set.
        assertEquals(metrics.getDoubleWordsCount(), 0);

        metrics.setDoubleWordsCount(97);
        assertEquals(metrics.getDoubleWordsCount(), 97);

        metrics.setDoubleWordsCount(9);;
        assertEquals(metrics.getDoubleWordsCount(), 9);
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    // INTEGRATIONS TEST CASES FOR ALL METRICS CLASS METHODS
    //---------------------------------------------------------------------------------------------------------------------------------------------

    @Test
        /* Tests to see if the setter and getter method for error count will accept and retrieve
        the accumulation of different values from getter methods for Miscapitalizations, Mispellings, and double words*/
    void testErrorCountAfterACombinationOfErrorSets(){
        metrics.setMiscapitalizationsCount(156);
        metrics.setMisspellingsCount(156);
        metrics.setDoubleWordsCount(97);

        //should be 156 + 156 + 97
        metrics.setErrorCount((metrics.getMiscapitalizationsCount() + metrics.getMisspellingsCount() + metrics.getDoubleWordsCount()));
        assertEquals(metrics.getErrorCount(), (156 + 156 + 97));

    }
}

>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
