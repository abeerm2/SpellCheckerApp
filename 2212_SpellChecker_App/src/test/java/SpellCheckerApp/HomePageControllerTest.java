package SpellCheckerApp;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.*;
import javafx.application.Application.*;
import javafx.application.Platform.*;


/**
 * This class tests the HomePageController class in the SpellCheckerApp package.
 * @author Sajeiv
 */
class HomePageControllerTest {

    private HomePageController homePageController;
    @BeforeEach
    void setUp() throws IOException {
        // Create a new HomePageController object for each test
        homePageController = new HomePageController();
    }

    @Test
    void isHomePageRunning() {

    }
}