package SpellCheckerApp;

/**
 * The Metrics class is responsible for tracking various metrics related to the spell checking process.
 * It keeps track of character count, line count, word count, error count, correction count, manual correction count,
 * accepted suggestion count, and word deletion count. These metrics provide a comprehensive overview of the spell checking process,
 * allowing for detailed analysis and potential improvements in future iterations.
 *
 * @author Ameen
 */
public class Metrics {
    private int character_count, lines_count, words_count, errors_count, corrections_count, manual_corrections_count, accepted_suggestions_count, word_deletions_count, misspellings_count, miscapitalizations_count, double_words_count;

<<<<<<< HEAD
   /**
    * Getter method to returns the number of characters last tracked.
    *
    * @return character_count the number of characters last tracked.
    */
   public int getCharacterCount() {
	   return this.character_count;
   }
   
   /**
    * Getter method to returns the number of lines last tracked.
    *
    * @return lines_count the number of lines last tracked.
    */
   public int getLineCount() {
	   return this.lines_count;
   }
   
   /**
    * Getter method to returns the number of words last tracked.
    *
    * @return words_count the number of words within the document last tracked.
    */
   public int getWordCount() {
	   return this.words_count;
   }
   
   /**
    * Getter method to returns the number of errors last tracked.
    *
    * @return errors_count the number of errors within the document last tracked.
    */
   public int getErrorCount() {
	   return this.errors_count;
   }
   
   /**
    * Getter method to returns the number of correction applied last tracked (includes manual and accepted suggestions).
    *
    * @return corrections_count the number of corrections applied within the document last tracked.
    */
   public int getCorrectionCount() {
	   return this.corrections_count;
   }
   
   /**
    * Getter method to returns the number of manual corrections applied last tracked.
    *
    * @return manual_corrections_count the number of manual corrections applied last tracked.
    */
   public int getManualCorrectionsCount() {
	   return this.manual_corrections_count;
   }
   
   /**
    * Getter method to returns the number of accepted suggestions last tracked.
    * This is different from corrections count as this does not include manual corrections.
    * @return accepted_suggestions_count the number of accepted suggestion within the document last tracked.
    */
   public int getAcceptedSuggestionsCount() {
	   return this.accepted_suggestions_count;
   }
   
   /**
    * Getter method to returns the number of words deleted last tracked.
    *
    * @return word_deletions_count the number of deleted words within the document last tracked.
    */
   public int getWordDeletionsCount() {
	   return this.word_deletions_count;
   }
   
   
   //---------------Setter Methods --------------------//

   /**
    * Sets the number of characters in the document.
    * NOTE: Usually used to increment the count. For example x.setCharacterCount(x.getCharacterCount()+1)
    * @param ccount the number of characters to be set.
    */
   public void setCharacterCount(int ccount) {
	   this.character_count=ccount;
   }
   
   /**
    * Sets the number of lines in the document.
    * NOTE: Usually used to increment the line count. For example x.setLineCount(x.getLineCount()+1)
    * @param linecount the number of lines to be set.
    */
   public void setLineCount(int linecount) {
	   this.lines_count=linecount;
   }
   
   /**
    * Sets the number of words in the document.
    * NOTE: Usually used to increment the word count. For example x.setWordCount(x.getWordCount()+1)
    * @param wcount the number of words to be set.
    */
   public void setWordCount(int wcount) {
	   this.words_count=wcount;
   }
   
   /**
    * Sets the number of errors in the document.
    * NOTE: Usually used to increment the line count. For example x.setErrorCount(x.getErrorCount()+1)
    * @param ecount the number of errors to be set.
    */
   public void setErrorCount(int ecount) {
	   this.errors_count=ecount;
   }
   
   /**
    * Sets the number of corrections in the document. Includes both manual and suggestions corrections
    * NOTE: Usually used to increment the line count. For example x.setCorrectionCount(x.getCorrectionCount()+1)
    * @param corcount the number of corrections to be set.
    */
   public void setCorrectionCount(int corcount) {
	   this.corrections_count=corcount;
   }
   
   /**
    * Sets the number of manual corrections applied to the document.
    * NOTE: Usually used to increment the manual correction count. For example x.setManualCorrectionCount(x.getManualCorrectionsCount()+1)
    * @param mcount the number of manual corrections to be set.
    */
   public void setManualCorrectionsCount(int mcount) {
	   this.manual_corrections_count=mcount;
   }
   
   /**
    * Sets the number of accepted suggestions applied to the document.
    * NOTE: Usually used to increment the accepted suggestions count. For example x.setAcceptedSuggestionsCount(x.getAcceptedSuggestionsCount()+1)
    * @param acount the number of accepted suggestions to be set.
    */
   public void setAcceptedSuggestionsCount(int acount) {
	   this.accepted_suggestions_count=acount;
   }
   
   /**
    * Sets the number of deleted words applied to the document.
    * NOTE: Usually used to increment the accepted suggestions count. For example x.setWordDeletionsCount(x.getWordDeletionsCount()+1)
    * @param wcount the number of deleted words to be set.
    */
   public void setWordDeletionsCount(int wcount) {
	   this.word_deletions_count=wcount;
   }


   public int getMisspellingsCount(){
       return misspellings_count;
   }

   public int getMiscapitalizationsCount(){
       return miscapitalizations_count;
   }

   public int getDoubleWordsCount(){
       return double_words_count;
   }

   public void setMisspellingsCount(int misspellingsCount){
       this.misspellings_count = misspellingsCount;
    }

    public void setMiscapitalizationsCount(int miscapitalizationCount){
        this.miscapitalizations_count = miscapitalizationCount;
    }

    public void setDoubleWordsCount(int doubleWordCount){
       this.double_words_count = doubleWordCount;
=======
    public Metrics() {
        //initializes all variables
        //character count represents the total characters within the current document
        this.character_count = 0;
        //line count represents the number of lines within the current document
        this.lines_count = 0;
        //word count represents the number of words within the current document
        this.words_count = 0;
        //error count represents the number of errors (double word, spelling, and proper name) within the current document.
        this.errors_count = 0;
        //corrections count represents the number of corrections applied to the original document to get it the current document
        this.corrections_count = 0;
        //manual corrections count represents the number of manual corrections insertions the user has committed
        this.manual_corrections_count = 0;
        //Accepted suggestions count represents the number of suggestions the user has accepted to tuen the original document into the current "modified" document
        this.accepted_suggestions_count = 0;
        //word deletions count represents the number of deleted words in the orignal document to transform it into the current "modified" document
        this.word_deletions_count = 0;
    }


    /**
     * Getter method to returns the number of characters last tracked.
     *
     * @return character_count the number of characters last tracked.
     */
    public int getCharacterCount() {
        return this.character_count;
    }

    /**
     * Getter method to returns the number of lines last tracked.
     *
     * @return lines_count the number of lines last tracked.
     */
    public int getLineCount() {
        return this.lines_count;
    }

    /**
     * Getter method to returns the number of words last tracked.
     *
     * @return words_count the number of words within the document last tracked.
     */
    public int getWordCount() {
        return this.words_count;
    }

    /**
     * Getter method to returns the number of errors last tracked.
     *
     * @return errors_count the number of errors within the document last tracked.
     */
    public int getErrorCount() {
        return this.errors_count;
    }

    /**
     * Getter method to returns the number of correction applied last tracked (includes manual and accepted suggestions).
     *
     * @return corrections_count the number of corrections applied within the document last tracked.
     */
    public int getCorrectionCount() {
        return this.corrections_count;
    }

    /**
     * Getter method to returns the number of manual corrections applied last tracked.
     *
     * @return manual_corrections_count the number of manual corrections applied last tracked.
     */
    public int getManualCorrectionsCount() {
        return this.manual_corrections_count;
    }

    /**
     * Getter method to returns the number of accepted suggestions last tracked.
     * This is different from corrections count as this does not include manual corrections.
     *
     * @return accepted_suggestions_count the number of accepted suggestion within the document last tracked.
     */
    public int getAcceptedSuggestionsCount() {
        return this.accepted_suggestions_count;
    }

    /**
     * Getter method to returns the number of words deleted last tracked.
     *
     * @return word_deletions_count the number of deleted words within the document last tracked.
     */
    public int getWordDeletionsCount() {
        return this.word_deletions_count;
    }

    /**
     * Returns the count of misspellings.
     *
     * @return The count of misspellings
     */
    public int getMisspellingsCount() {
        return misspellings_count;
    }

    /**
     * Returns the count of miscapitalizations.
     *
     * @return The count of miscapitalizations
     */
    public int getMiscapitalizationsCount() {
        return miscapitalizations_count;
    }

    /**
     * Returns the count of double words.
     *
     * @return The count of double words
     */
    public int getDoubleWordsCount() {
        return double_words_count;
    }


    //---------------Setter Methods --------------------//

    /**
     * Sets the number of characters in the document.
     * NOTE: Usually used to increment the count. For example x.setCharacterCount(x.getCharacterCount()+1)
     *
     * @param ccount the number of characters to be set.
     */
    public void setCharacterCount(int ccount) {
        this.character_count = ccount;
    }

    /**
     * Sets the number of lines in the document.
     * NOTE: Usually used to increment the line count. For example x.setLineCount(x.getLineCount()+1)
     *
     * @param linecount the number of lines to be set.
     */
    public void setLineCount(int linecount) {
        this.lines_count = linecount;
    }

    /**
     * Sets the number of words in the document.
     * NOTE: Usually used to increment the word count. For example x.setWordCount(x.getWordCount()+1)
     *
     * @param wcount the number of words to be set.
     */
    public void setWordCount(int wcount) {
        this.words_count = wcount;
    }

    /**
     * Sets the number of errors in the document.
     * NOTE: Usually used to increment the line count. For example x.setErrorCount(x.getErrorCount()+1)
     *
     * @param ecount the number of errors to be set.
     */
    public void setErrorCount(int ecount) {
        this.errors_count = ecount;
    }

    /**
     * Sets the number of corrections in the document. Includes both manual and suggestions corrections
     * NOTE: Usually used to increment the line count. For example x.setCorrectionCount(x.getCorrectionCount()+1)
     *
     * @param corcount the number of corrections to be set.
     */
    public void setCorrectionCount(int corcount) {
        this.corrections_count = corcount;
    }

    /**
     * Sets the number of manual corrections applied to the document.
     * NOTE: Usually used to increment the manual correction count. For example x.setManualCorrectionCount(x.getManualCorrectionsCount()+1)
     *
     * @param mcount the number of manual corrections to be set.
     */
    public void setManualCorrectionsCount(int mcount) {
        this.manual_corrections_count = mcount;
    }

    /**
     * Sets the number of accepted suggestions applied to the document.
     * NOTE: Usually used to increment the accepted suggestions count. For example x.setAcceptedSuggestionsCount(x.getAcceptedSuggestionsCount()+1)
     *
     * @param acount the number of accepted suggestions to be set.
     */
    public void setAcceptedSuggestionsCount(int acount) {
        this.accepted_suggestions_count = acount;
    }

    /**
     * Sets the number of deleted words applied to the document.
     * NOTE: Usually used to increment the accepted suggestions count. For example x.setWordDeletionsCount(x.getWordDeletionsCount()+1)
     *
     * @param wcount the number of deleted words to be set.
     */
    public void setWordDeletionsCount(int wcount) {
        this.word_deletions_count = wcount;
    }

    /**
     * Sets the count of misspellings.
     *
     * @param misspellingsCount The count of misspellings
     */
    public void setMisspellingsCount(int misspellingsCount) {
        this.misspellings_count = misspellingsCount;
    }

    /**
     * Sets the count of miscapitalizations.
     *
     * @param miscapitalizationCount The count of miscapitalizations
     */
    public void setMiscapitalizationsCount(int miscapitalizationCount) {
        this.miscapitalizations_count = miscapitalizationCount;
    }

    /**
     * Sets the count of double words.
     *
     * @param doubleWordCount The count of double words
     */
    public void setDoubleWordsCount(int doubleWordCount) {
        this.double_words_count = doubleWordCount;
>>>>>>> af88943cd2e558eec5a63bf432ac1b25155a8d41
    }


}
