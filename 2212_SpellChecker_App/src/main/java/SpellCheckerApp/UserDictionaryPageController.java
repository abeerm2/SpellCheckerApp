package SpellCheckerApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controls the UserDictionaryPage of the SpellCheckerApplication.
 * It handles the adding and removing of words from the user dictionary.
 * @author Ameen
 */
public class UserDictionaryPageController {

    @FXML
    private TextField wordTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<String> wordListView;

    private Parent root;

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
     * Initializes the controller.
     * It populates the ListView with the user-added words and sets a custom cell factory for the ListView.
     */
    public void initialize() {
        // Populate the ListView with the user added words
        wordListView.getItems().addAll(spellCheckerApplication.getUserDictionary().getUserAddedWords());

        // Set a custom cell factory for the ListView
        wordListView.setCellFactory(lv -> new ListCell<String>() {
            // Create the Button
            Button btn = new Button("Remove");

            @Override
            public void updateItem(String word, boolean empty) {
                super.updateItem(word, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(word);
                    setGraphic(btn);
                    // Set the action for the Button
                    btn.setOnAction(e -> {
                        try {
                            // Remove the word from UserDictionary
                            spellCheckerApplication.getUserDictionary().removeWord(word);

                            // Remove the word from the ListView
                            wordListView.getItems().remove(word);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            // Handle the exception
                        }
                    });
                }
            }
        });
    }


    /**
     * Handles the adding of a word to the user dictionary.
     * It gets the word from the TextField, and if the word is not already in the ListView, it adds the word to the UserDictionary and the ListView.
     */
    @FXML
    public void handleAdd() {
        String word = wordTextField.getText();
        try {
            // Check if the word is already in the ListView
            if (!wordListView.getItems().contains(word)) {
                // Add the word to UserDictionary
                spellCheckerApplication.getUserDictionary().addWord(word);

                // Add the word to the ListView
                wordListView.getItems().add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }


    /**
     * Handles the canceling of the operation.
     * It closes the window.
     */
    @FXML
    public void handleCancel() {
        // Close the window
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
