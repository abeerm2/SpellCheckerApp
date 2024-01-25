package SpellCheckerApp;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;

import javafx.stage.Stage;
import javafx.scene.Scene;


/**
 * This class controls the home page of the SpellCheckerApplication.
 * It handles the opening of files and the user dictionary, and the exiting of the program.
 * @author Ameen
 */
public class HomePageController {

    @FXML
    public Button openFileButton;
    @FXML
    public Text titleText;
    @FXML
    public Text nameText;
    @FXML
    public Button buttonMain;
    @FXML
    public AnchorPane mainPage;
    @FXML
    public Button exitButton;

    private SpellCheckerApplication spellCheckerApplication;


    /**
     * Sets the SpellCheckerApplication for this controller.
     *
     * @param spellCheckerApplication The SpellCheckerApplication to set
     */
    public void setSpellCheckerApplication(SpellCheckerApplication spellCheckerApplication) {
        this.spellCheckerApplication = spellCheckerApplication;
    }


    /**
     * Handles the opening of a file.
     * It opens a FileChooser for the user to select a file, then starts a new session with the selected file.
     * If a file is selected, it also opens the ErrorPageController in a new window.
     */
    @FXML
    public void handleOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Document document = new Document(selectedFile.getPath());
            try {
                spellCheckerApplication.startSession(document);

                // Open the ErrorPageController in a new window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ErrorPage.fxml"));

                // Set a controller factory
                loader.setControllerFactory(c -> {
                    ErrorPageController controller = new ErrorPageController();
                    controller.setSpellCheckerApplication(spellCheckerApplication);
                    return controller;
                });

                // Load the FXML file
                Parent root = loader.load();

                // Create a new Stage
                Stage errorPageStage = new Stage();

                // Create a new Scene with the root from the FXML file
                Scene errorPageScene = new Scene(root);

                // Set the Scene on the Stage
                errorPageStage.setScene(errorPageScene);

                // Show the Stage
                errorPageStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
    }




    /**
     * Opens the UserDictionaryPageController in a new window.
     */
    @FXML
    public void openUserDict() {
        try {
            // Create a new FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserDictPage.fxml"));

            // Set a controller factory
            loader.setControllerFactory(c -> {
                UserDictionaryPageController controller = new UserDictionaryPageController();
                controller.setSpellCheckerApplication(spellCheckerApplication);
                return controller;
            });

            // Load the FXML file
            Parent root = loader.load();

            // Create a new Stage
            Stage userDictionaryStage = new Stage();

            // Create a new Scene with the root from the FXML file
            Scene userDictionaryScene = new Scene(root);

            // Set the Scene on the Stage
            userDictionaryStage.setScene(userDictionaryScene);

            // Show the Stage
            userDictionaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }


    /**
     * Exits the program.
     */
    @FXML
    public void exitProgram() {
        Platform.exit();
    }

}