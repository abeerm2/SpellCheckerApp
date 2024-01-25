package SpellCheckerApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the main entry point for the SpellCheckerApplication.
 * It loads the HomePage.fxml file and sets the SpellCheckerApplication for the HomePageController.
 * @author Ameen
 */
public class MainApplication extends Application {

    private SpellCheckerApplication spellCheckerApplication;

    /**
     * Starts the application.
     * It creates a new SpellCheckerApplication, loads the HomePage.fxml file, and sets the SpellCheckerApplication for the HomePageController.
     *
     * @param primaryStage The primary stage for this application
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a new SpellCheckerApplication
        spellCheckerApplication = new SpellCheckerApplication();

        // Load the HomePage.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomePage.fxml"));
        Parent root = loader.load();

        // Get the controller that was created when the FXML file was loaded
        HomePageController controller = loader.getController();
        // Set the SpellCheckerApplication for the controller
        controller.setSpellCheckerApplication(spellCheckerApplication);

        // Set the title of the primary stage
        primaryStage.setTitle("SpellChecker");
        // Set the scene of the primary stage
        primaryStage.setScene(new Scene(root));
        // Show the primary stage
        primaryStage.show();
    }

    /**
     * The main method that launches the application.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
