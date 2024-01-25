package SpellCheckerApp;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the MainApplication class in the SpellCheckerApp package.
 * It extends ApplicationTest from the TestFX framework for JavaFX testing.
 * @author Ameens
 */
public class MainApplicationTest extends ApplicationTest {

    private MainApplication mainApplication;

    /**
     * Starts the application for testing.
     * It creates a new MainApplication and starts it.
     *
     * @param stage The primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        mainApplication = new MainApplication();
        try {
            mainApplication.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the test environment before each test.
     * It creates a new MainApplication.
     *
     * @throws Exception If an error occurs
     */
    @BeforeEach
    public void setUp() throws Exception {
        mainApplication = new MainApplication();
    }

    /**
     * Tests the start method of the MainApplication class.
     * It checks if the MainApplication is not null after starting.
     */
    @Test
    public void testStart() {
        assertNotNull(mainApplication);
    }
}
