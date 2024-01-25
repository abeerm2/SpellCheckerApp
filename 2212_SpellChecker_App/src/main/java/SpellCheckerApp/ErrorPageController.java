package SpellCheckerApp;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is responsible for controlling the ErrorPage.
 * @author Ameen
 */
public class ErrorPageController {

    @FXML
    private FlowPane documentText; // The FlowPane that contains the document text

    @FXML
    private Button buttonMain; // The main button on the error page

    @FXML
    private Button errorPageButtons; // The buttons on the error page

    @FXML
    private VBox suggestionsBox; // The VBox that contains the suggestions

    @FXML
    private HBox metricsBox; // The HBox that contains the metrics

    @FXML
    private Button helpButton; // The help button on the error page

    private SpellCheckerApplication spellCheckerApplication; // The SpellCheckerApplication instance

    /**
     * Sets the SpellCheckerApplication instance.
     *
     * @param spellCheckerApplication The SpellCheckerApplication instance
     */
    public void setSpellCheckerApplication(SpellCheckerApplication spellCheckerApplication) {
        this.spellCheckerApplication = spellCheckerApplication;
    }

    /**
     * Creates a styled button with the specified text.
     *
     * @param text The text to display on the button
     * @return The created button
     */
    private Button createStyledButton(String text) {
        Button button = new Button(text); // Create a new button with the specified text
        button.setMaxWidth(Double.MAX_VALUE); // Set the button to fill the width of the VBox
        button.setPrefHeight(40); // Increase the size of the button
        VBox.setMargin(button, new javafx.geometry.Insets(10, 0, 10, 0)); // Add margin above and below the button
        return button;
    }

    /**
     * Initializes the ErrorPageController.
     */
    public void initialize() {

        // Clear the documentText FlowPane
        documentText.getChildren().clear();

        // Get the Document from the SpellCheckerApplication
        Document document = spellCheckerApplication.getDocument();

        // Get the list of words from the Document
        List<String> words = document.iterateWords();

        // Get the list of errors from the SpellCheckerApplication
        List<Error> errors = spellCheckerApplication.getErrors();

        spellCheckerApplication.updateMetrics();

        // Skip the first word
        for (int i = 1; i < words.size(); i++) {
            String word = words.get(i);
            // If the word is "<NEWLINE>", add a new line to the FlowPane
            if ("<NEWLINE>".equals(word)) {
                documentText.getChildren().add(new Label("\n"));
            } else if (!word.isEmpty()) {
                // Check if the word is an error word at the current location
                int finalI = i;
                Optional<Error> optionalError = errors.stream()
                        .filter(error -> finalI == error.getLocation() && word.equals(error.getErrorWord()))
                        .findFirst();
                if (optionalError.isPresent()) {
                    // If it's an error word, create a Hyperlink instead of a Label
                    Hyperlink hyperlink = new Hyperlink(word);
                    hyperlink.setStyle("-fx-background-color: #F08080; -fx-padding: 5; -fx-text-fill: black;");
                    // Set an action to display the suggestions when the hyperlink is clicked
                    hyperlink.setOnAction(event -> {
                        Error error = optionalError.get();
                        List<String> suggestions = error.getSuggestions();
                        // Clear the suggestionsBox
                        suggestionsBox.getChildren().clear();
                        // Create a button for each suggestion
                        for (String suggestion : suggestions) {
                            Button button = createStyledButton(suggestion);
                            // Set an action to apply the suggestion and rerender the document when the button is clicked
                            button.setOnAction(suggestionEvent -> {
                                spellCheckerApplication.applySuggestion(error, suggestion);
                                spellCheckerApplication.getMetrics().setAcceptedSuggestionsCount(spellCheckerApplication.getMetrics().getAcceptedSuggestionsCount() + 1);
                                // Rerender the document
                                initialize();
                                suggestionsBox.getChildren().clear();
                            });
                            suggestionsBox.getChildren().add(button);
                        }

                        // Add buttons for the other options
                        Button ignoreOnceButton = createStyledButton("Ignore Error Once");
                        ignoreOnceButton.setOnAction(event1 -> {
                            errors.remove(error);
                            // Rerender the document
                            initialize();
                            suggestionsBox.getChildren().clear();

                        });
                        suggestionsBox.getChildren().add(ignoreOnceButton);

                        Button ignoreForSessionButton = createStyledButton("Ignore Error For Session");
                        ignoreForSessionButton.setOnAction(event2 -> {
                            spellCheckerApplication.ignoreWordForSession(error);
                            // Rerender the document
                            initialize();
                            suggestionsBox.getChildren().clear();

                        });
                        suggestionsBox.getChildren().add(ignoreForSessionButton);

                        Button deleteWordButton = createStyledButton("Delete Word");
                        deleteWordButton.setOnAction(event3 -> {
                            spellCheckerApplication.applySuggestion(error, "");
                            spellCheckerApplication.getMetrics().setWordDeletionsCount(spellCheckerApplication.getMetrics().getWordDeletionsCount() + 1);
                            // Rerender the document
                            initialize();
                            suggestionsBox.getChildren().clear();

                        });
                        suggestionsBox.getChildren().add(deleteWordButton);

                        Button addToDictionaryButton = createStyledButton("Add Word to User Dictionary");
                        addToDictionaryButton.setOnAction(event4 -> {
                            try {
                                spellCheckerApplication.getUserDictionary().addWord(error.getErrorWord());
                                spellCheckerApplication.recalculateErrors();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            // Rerender the document
                            initialize();
                            suggestionsBox.getChildren().clear();

                        });
                        suggestionsBox.getChildren().add(addToDictionaryButton);

                        // Add a TextField for the user to input a custom suggestion
                        TextField customSuggestionField = new TextField();
                        customSuggestionField.setPromptText("Enter a Custom Suggestion");
                        suggestionsBox.getChildren().add(customSuggestionField);

                        // Add a Button to apply the custom suggestion
                        Button applyCustomSuggestionButton = createStyledButton("Apply Custom suggestion");
                        applyCustomSuggestionButton.setOnAction(event5 -> {
                            String customSuggestion = customSuggestionField.getText();
                            spellCheckerApplication.applySuggestion(error, customSuggestion);
                            spellCheckerApplication.getMetrics().setManualCorrectionsCount(spellCheckerApplication.getMetrics().getManualCorrectionsCount() + 1);
                            // Rerender the document
                            initialize();
                            suggestionsBox.getChildren().clear();
                        });
                        suggestionsBox.getChildren().add(applyCustomSuggestionButton);
                    });


                    documentText.getChildren().add(hyperlink);
                } else {
                    // If it's not an error word, create a Label
                    Label label = new Label(word);
                    label.setStyle("-fx-background-color: lightgray; -fx-padding: 5;");
                    documentText.getChildren().add(label);
                }
            }
        }


        // In the initialize function:
        metricsBox.getChildren().clear();

        // Get the Metrics from the SpellCheckerApplication
        Metrics metrics = spellCheckerApplication.getMetrics();

        //Create labels to be put in the HBox for the metrics.
        Label characterCountLabel = new Label("Characters:\n " + metrics.getCharacterCount());
        characterCountLabel.setAlignment(Pos.CENTER);
        characterCountLabel.setPadding(new Insets(6));

        Label linesCountLabel = new Label("Lines:\n " + metrics.getLineCount());
        linesCountLabel.setAlignment(Pos.CENTER);
        linesCountLabel.setPadding(new Insets(6));

        Label wordsCountLabel = new Label("Words:\n " + metrics.getWordCount());
        wordsCountLabel.setAlignment(Pos.CENTER);
        wordsCountLabel.setPadding(new Insets(6));

        Label errorsCountLabel = new Label("Errors:\n " + metrics.getErrorCount());
        errorsCountLabel.setAlignment(Pos.CENTER);
        errorsCountLabel.setPadding(new Insets(6));

        Label misspellingLabel = new Label("Misspellings:\n " + metrics.getMisspellingsCount());
        misspellingLabel.setAlignment(Pos.CENTER);
        misspellingLabel.setPadding(new Insets(6));

        Label miscapitalizationLabel = new Label("Miscapitalizations:\n " + metrics.getMiscapitalizationsCount());
        miscapitalizationLabel.setAlignment(Pos.CENTER);
        miscapitalizationLabel.setPadding(new Insets(6));

        Label doubleWordLabel = new Label("Double Words:\n " + metrics.getDoubleWordsCount());
        doubleWordLabel.setAlignment(Pos.CENTER);
        doubleWordLabel.setPadding(new Insets(6));

        Label wordDeletionsCountLabel = new Label("Word Deletions:\n " + metrics.getWordDeletionsCount());
        wordDeletionsCountLabel.setAlignment(Pos.CENTER);
        wordDeletionsCountLabel.setPadding(new Insets(6));

        Label manualCorrectionsLabel = new Label("Manual Corrections:\n " + metrics.getManualCorrectionsCount());
        manualCorrectionsLabel.setAlignment(Pos.CENTER);
        manualCorrectionsLabel.setPadding(new Insets(6));

        Label acceptedSuggestionsLabel = new Label("Accepted Suggestions:\n " + metrics.getAcceptedSuggestionsCount());
        acceptedSuggestionsLabel.setAlignment(Pos.CENTER);
        acceptedSuggestionsLabel.setPadding(new Insets(6));


        // Add the Labels to the metricsBox
        metricsBox.getChildren().addAll(characterCountLabel, linesCountLabel, wordsCountLabel, errorsCountLabel, misspellingLabel, miscapitalizationLabel, doubleWordLabel, wordDeletionsCountLabel, manualCorrectionsLabel, acceptedSuggestionsLabel);

    }


    /**
     * Goes back to the previous page.
     */
    @FXML
    public void goBack() {
        Document doc = spellCheckerApplication.getDocument(); // Get the current document
        if (!doc.getUnsavedChanges()) {
            ((Stage) errorPageButtons.getScene().getWindow()).close(); // Close the window if there are no unsaved changes
        } else {
            Alert unsavedChanges = new Alert(AlertType.CONFIRMATION); // Create a new confirmation alert for unsaved changes
            unsavedChanges.setTitle("UNSAVED CHANGES"); // Set the title of the alert
            unsavedChanges.setHeaderText("Warning: Unsaved Changes"); // Set the header text of the alert
            unsavedChanges.setContentText("Do you want to continue without saving?"); // Set the content text of the alert
            unsavedChanges.getButtonTypes().setAll(javafx.scene.control.ButtonType.YES, javafx.scene.control.ButtonType.NO); // Set the button types of the alert
            javafx.scene.control.ButtonType YorN = unsavedChanges.showAndWait().orElse(javafx.scene.control.ButtonType.NO); // Show the alert and wait for the user's response
            if (YorN == javafx.scene.control.ButtonType.YES) {
                ((Stage) errorPageButtons.getScene().getWindow()).close(); // Close the window if the user chose to continue without saving
            }
        }
    }

    /**
     * Opens the user dictionary page.
     */
    @FXML
    public void openUserDict() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserDictPage.fxml")); // Create a new FXMLLoader
            loader.setControllerFactory(c -> {
                UserDictionaryPageController controller = new UserDictionaryPageController(); // Create a new UserDictionaryPageController
                controller.setSpellCheckerApplication(spellCheckerApplication); // Set the SpellCheckerApplication instance on the controller
                return controller;
            });
            Parent root = loader.load(); // Load the FXML file
            Stage userDictionaryStage = new Stage(); // Create a new Stage
            Scene userDictionaryScene = new Scene(root); // Create a new Scene with the root from the FXML file
            userDictionaryStage.setScene(userDictionaryScene); // Set the Scene on the Stage
            userDictionaryStage.setOnHidden(e -> {
                spellCheckerApplication.recalculateErrors(); // Recalculate the errors when the userDictionaryStage is closed
                initialize(); // Re-initialize the ErrorPageController
            });
            userDictionaryStage.show(); // Show the Stage
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace of the exception
            // Handle the exception
        }
    }


    /**
     * Exports the current document to a file.
     */
    @FXML
    public void handleExport() {
        Document docc = spellCheckerApplication.getDocument(); // Get the current document

        if (docc != null) { // Check if the document is not null
            FileChooser fileExport = new FileChooser(); // Create a new FileChooser
            fileExport.setTitle("Export File"); // Set the title of the FileChooser
            fileExport.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", ".txt")); // Add an extension filter for text files

            // Set the initial file name based on the original document's path
            fileExport.setInitialFileName(docc.getFilePath());
            File toSave = fileExport.showSaveDialog(new Stage()); // Show the save dialog and get the selected file
            if (toSave != null) { // Check if a file was selected
                try {
                    docc.saveFile(toSave); // Save the document to the selected file
                } catch (IOException e) { // Catch any IOExceptions
                    System.out.println("Error saving file"); // Print an error message
                }
            } else {
                System.out.println("Cancelled fail operation"); // Print a message if the operation was cancelled
            }
        }
    }

    /**
     * Handles the help button click event.
     *
     * @param event The ActionEvent
     */
    @FXML
    public void handleHelp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create a new information alert
        alert.setTitle("Help"); // Set the title of the alert
        alert.setHeaderText(null); // Set the header text of the alert to null
        alert.setContentText("Words in red are errors. Click on them to correct them. You can apply a suggestion by " +
                "clicking on the suggested words on the right. If the error doesnt have any suggested words, none " +
                "will be shown. Every error will also have options to ignore the error, ignore the error for the " +
                "session, add to user dictionary, and apply manual correction. The user can also add and remove " +
                "words from the user dictionary by clicking on the \"Edit User Dictionary\" button found at the " +
                "bottom right of the page."); // Set the content text of the alert

        alert.showAndWait(); // Show the alert and wait for the user's response
    }


}
