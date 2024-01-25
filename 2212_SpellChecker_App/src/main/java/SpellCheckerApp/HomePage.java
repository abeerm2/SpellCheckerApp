package SpellCheckerApp;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomePage extends Application {

    public AnchorPane mainPage;
    public Text nameText;

    public Text titleText;
    public Button fileUpload;
    // Event handler for the openFileButton
    @FXML
    public Button openFileButton;  // This is the Button with fx:id="openFileButton" in the FXML
    private SpellCheckerApplication spellCheckerApplication;
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleOpenFile() throws IOException {

        ListView<HBox> listView = new ListView<>();
        HBox metricsBox = new HBox();
        metricsBox.setSpacing(10);
        // Logic for handling the "Open File" button click
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (isValidFile(selectedFile)) {
            String filePath = selectedFile.getAbsolutePath();

            Document document = new Document(filePath);
            UserDictionary userDictionary = null;
            try {
                userDictionary = new UserDictionary("dict.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            SpellChecker spellChecker = new SpellChecker(document, userDictionary);

            spellCheckerApplication = new SpellCheckerApplication(spellChecker, userDictionary, document);
            try {
                spellCheckerApplication.startSession();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            spellCheckerApplication.updateMetrics();

            // Display words in the document
            List<String> wordsList = document.iterateWords();
            List<Error> errors = spellCheckerApplication.getErrors();


            for (int i = 0; i < wordsList.size(); i++) {
                String word = wordsList.get(i);

                if (!word.equals("<START>") && !word.equals("<NEWLINE>")) {
                    Label label = new Label(word);
                    label.setFont(new Font(20)); // Set the font size to 20

                    HBox hbox = new HBox();
                    hbox.getChildren().add(label);

                    for (Error error : errors) {
                        if (error.getLocation() == i) {
                            label.setTextFill(Color.RED);
                            Button button = new Button("Fix");
                            button.setOnAction(event -> {
                                // TODO
                            });
                            hbox.getChildren().add(button);
                            break;
                        }
                    }

                    listView.getItems().add(hbox);
                }

            }


            // Display metrics
            Metrics metrics = spellCheckerApplication.getMetrics();
            String[] metricsLabels = {
                    "Characters: \n" + metrics.getCharacterCount(),
                    "Lines: \n" + metrics.getLineCount(),
                    "Words: \n" + metrics.getWordCount(),
                    "Errors: \n" + metrics.getErrorCount(),
                    "Corrections: \n" + metrics.getCorrectionCount(),
                    "Manual Corrections: \n" + metrics.getManualCorrectionsCount(),
                    "Accepted Suggestions: \n" + metrics.getAcceptedSuggestionsCount(),
                    "Word Deletions: \n" + metrics.getWordDeletionsCount(),
                    "Misspellings: \n" + metrics.getMisspellingsCount(),
                    "Miscapitalizations: \n" + metrics.getMiscapitalizationsCount(),
                    "Double Words: \n" + metrics.getDoubleWordsCount()
            };
            metricsBox.getChildren().clear();
            for (String metricsLabel : metricsLabels) {
                Label label = new Label(metricsLabel);
                metricsBox.getChildren().add(label);
            }

            UserInterface.showErrorPage(listView, metricsBox);
            FXMLLoader errorPageLoader = new FXMLLoader(getClass().getResource("/ErrorPage.fxml"));


            ErrorPage errorPageController = errorPageLoader.getController();
            errorPageController.setDocument(document);


        }
        else{
            showAlert("File Selection", "No file selected or the dialog was canceled.");
        }





    }
    private boolean isValidFile(File file) {
        // Example: Check if the file meets certain criteria
        // You can implement your own validation logic based on your requirements
        return file.getName().toLowerCase().endsWith(".txt");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void openUserDict() throws IOException {

        UserInterface.showUserDictPage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserDictPage.fxml"));
//        Scene scene = new Scene(loader.load());
//
//        // Pass the primaryStage reference to the UserDictPage controller
//        UserDictPage userDictPageController = loader.getController();
//        userDictPageController.setPrimaryStage(primaryStage);
    }
    @Override
    public void start(Stage stage) throws Exception {

    }
    @FXML
    private void exitProgram() throws IOException {
        if (primaryStage != null) {
            primaryStage.close();
        }
    }

}
