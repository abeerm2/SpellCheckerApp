package SpellCheckerApp;


import java.util.ArrayList;
import java.util.List;

/**
 * The Correction class represents a correction for a given error.
 * It contains a list of suggestions for the error, which are generated using a user dictionary.
 *
 * @author Ameen
 */
public class Correction {
    private Error error; // The error that this correction belongs to
    private List<String> suggestions; // The list of suggestions for the error
    private UserDictionary userDictionary; // The dictionary used to generate suggestions

    /**
     * Constructor for the Correction class.
     *
     * @param error The error that this correction belongs to
     * @param userDictionary The user dictionary that is used to generate suggestions from
     */
    public Correction(Error error, UserDictionary userDictionary) {
        this.error = error;
        this.userDictionary = userDictionary;
        this.suggestions = new ArrayList<>();
    }

    /**
     * Generates a list of suggestions for the error.
     *
     * @return The list of suggestions
     */
    public List<String> generateSuggestions() {

        String errorWord = error.getErrorWord();


        switch (error.getErrorCode()) {

            case 0x00: {
                //This is a double word
                suggestions.add("");
                break;
            }
            case 0x13: {
                //This is a valid word but needs to have its first letter capitalized.
                suggestions.add(Character.toUpperCase(errorWord.charAt(0)) + errorWord.substring(1));
                break;

            }
            case 0x11: {
                //This is a miscapitalized word that needs its first letter capitalized.
                for (int i = 0; i < (1 << errorWord.length()); i++) {
                    StringBuilder word = new StringBuilder(errorWord);
                    for (int j = 0; j < errorWord.length(); j++) {
                        if (((i >> j) & 1) == 1) {
                            word.setCharAt(j, Character.toUpperCase(errorWord.charAt(j)));
                        } else {
                            word.setCharAt(j, Character.toLowerCase(errorWord.charAt(j)));
                        }
                    }
                    // If the capitalization exists in the dictionary and is not already in the suggestions, add it to the suggestions
                    if (userDictionary.doesWordExist(word.toString()) && !suggestions.contains(word.toString())) {
                        // Capitalize the first letter of the word
                        word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
                        if (!suggestions.contains(word.toString())) {
                            suggestions.add(word.toString());
                        }
                    }
                }
                break;

            }
            case 0x12: {
                // Generate suggestions by deleting each letter
                for (int i = 0; i < errorWord.length(); i++) {
                    String suggestion = errorWord.substring(0, i) + errorWord.substring(i + 1);
                    if (userDictionary.doesWordExist(suggestion)) {
                        suggestion = Character.toUpperCase(suggestion.charAt(0)) + suggestion.substring(1);
                        if (!suggestions.contains(suggestion)) {
                            suggestions.add(suggestion);
                        }
                    }
                }

                // Generate suggestions by inserting each possible letter into each possible position
                for (int i = 0; i <= errorWord.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String suggestion = errorWord.substring(0, i) + c + errorWord.substring(i);
                        if (userDictionary.doesWordExist(suggestion)) {
                            suggestion = Character.toUpperCase(suggestion.charAt(0)) + suggestion.substring(1);
                            if (!suggestions.contains(suggestion)) {
                                suggestions.add(suggestion);
                            }
                        }
                    }
                }

                // Generate suggestions by swapping each pair of consecutive letters
                for (int i = 0; i < errorWord.length() - 1; i++) {
                    String suggestion = errorWord.substring(0, i) + errorWord.charAt(i + 1) + errorWord.charAt(i) + errorWord.substring(i + 2);
                    if (userDictionary.doesWordExist(suggestion)) {
                        suggestion = Character.toUpperCase(suggestion.charAt(0)) + suggestion.substring(1);
                        if (!suggestions.contains(suggestion)) {
                            suggestions.add(suggestion);
                        }
                    }
                }

                // Generate suggestions by inserting a space or hyphen at each possible interior position
                for (int i = 1; i < errorWord.length(); i++) {
                    String suggestion1 = errorWord.substring(0, i) + " " + errorWord.substring(i);
                    String suggestion2 = errorWord.substring(0, i) + "-" + errorWord.substring(i);
                    if (userDictionary.doesWordExist(suggestion1.split(" ")[0]) && userDictionary.doesWordExist(suggestion1.split(" ")[1])) {
                        suggestion1 = Character.toUpperCase(suggestion1.charAt(0)) + suggestion1.substring(1);
                        if (!suggestions.contains(suggestion1)) {
                            suggestions.add(suggestion1);
                        }
                    }
                    if (userDictionary.doesWordExist(suggestion2.split("-")[0]) && userDictionary.doesWordExist(suggestion2.split("-")[1])) {
                        suggestion2 = Character.toUpperCase(suggestion2.charAt(0)) + suggestion2.substring(1);
                        if (!suggestions.contains(suggestion2)) {
                            suggestions.add(suggestion2);
                        }
                    }
                }

                break;

            }

            case 0x01: {
                for (int i = 0; i < (1 << errorWord.length()); i++) {
                    StringBuilder word = new StringBuilder(errorWord);
                    for (int j = 0; j < errorWord.length(); j++) {
                        if (((i >> j) & 1) == 1) {
                            word.setCharAt(j, Character.toUpperCase(errorWord.charAt(j)));
                        } else {
                            word.setCharAt(j, Character.toLowerCase(errorWord.charAt(j)));
                        }
                    }
                    // If the capitalization exists in the dictionary and is not already in the suggestions, add it to the suggestions
                    if (userDictionary.doesWordExist(word.toString()) && !suggestions.contains(word.toString())) {
                        if (!suggestions.contains(word.toString())) {
                            suggestions.add(word.toString());
                        }
                    }
                }

                break;

            }

            case 0x02: {
                for (int i = 0; i < errorWord.length(); i++) {
                    String suggestion = errorWord.substring(0, i) + errorWord.substring(i + 1);
                    if (userDictionary.doesWordExist(suggestion)) {
                        if (!suggestions.contains(suggestion)) {
                            suggestions.add(suggestion);
                        }
                    }
                }

                // Generate suggestions by inserting each possible letter into each possible position
                for (int i = 0; i <= errorWord.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String suggestion = errorWord.substring(0, i) + c + errorWord.substring(i);
                        if (userDictionary.doesWordExist(suggestion)) {
                            if (!suggestions.contains(suggestion)) {
                                suggestions.add(suggestion);
                            }
                        }
                    }
                }

                // Generate suggestions by swapping each pair of consecutive letters
                for (int i = 0; i < errorWord.length() - 1; i++) {
                    String suggestion = errorWord.substring(0, i) + errorWord.charAt(i + 1) + errorWord.charAt(i) + errorWord.substring(i + 2);
                    if (userDictionary.doesWordExist(suggestion)) {
                        if (!suggestions.contains(suggestion)) {
                            suggestions.add(suggestion);
                        }
                    }
                }

                // Generate suggestions by inserting a space or hyphen at each possible interior position
                for (int i = 1; i < errorWord.length(); i++) {
                    String suggestion1 = errorWord.substring(0, i) + " " + errorWord.substring(i);
                    String suggestion2 = errorWord.substring(0, i) + "-" + errorWord.substring(i);
                    if (userDictionary.doesWordExist(suggestion1.split(" ")[0]) && userDictionary.doesWordExist(suggestion1.split(" ")[1])) {
                        if (!suggestions.contains(suggestion1)) {
                            suggestions.add(suggestion1);
                        }
                    }
                    if (userDictionary.doesWordExist(suggestion2.split("-")[0]) && userDictionary.doesWordExist(suggestion2.split("-")[1])) {
                        if (!suggestions.contains(suggestion2)) {
                            suggestions.add(suggestion2);
                        }
                    }
                }

                break;

            }
        }


        return suggestions;
    }


    public List<String> getSuggestions() {
        return this.suggestions;
    }

}
