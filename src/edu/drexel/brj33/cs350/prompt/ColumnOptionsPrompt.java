package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.service.IOService;

import java.util.ArrayList;
import java.util.List;

/**
 * A ColumnOptionsPrompt is a prompt that provides options with its prompt, rather than
 * just displaying a sentence-based question. An example of usage would be in a Multiple
 * Choice question, where a ColumnOptionsPrompt is used to create one column of options:
 *    This is the sentence-based prompt, below are my options
 *       A) First option
 *       B) Second option
 *       C) Third option
 */
public class ColumnOptionsPrompt extends Prompt {
    private List<List<String>> optionsColumns;
    private int numColumns;

    public ColumnOptionsPrompt(int numColumns){
        this.numColumns = numColumns;
        this.optionsColumns = new ArrayList<List<String>>();
        // Initialize nested Lists within optionsColumns.
        for (int i = 0; i < numColumns; i++){
            this.optionsColumns.add(new ArrayList<>());
        }
    }

    public int getNumColumns(){
        return numColumns;
    }

    public List<String> getColumn(int i){
        return optionsColumns.get(i);
    }

    public void display(IOService ioService){
        super.display(ioService);
        // Handle displaying of columns.
        int numOptions = this.optionsColumns.get(0).size();
        for (int i = 0; i < numOptions; i++){
            String line = "";
            int numLongestOptionChars = getNumLongestOptionChars(optionsColumns.get(0));
            int numLongestCharsWithPadding = numLongestOptionChars + 10;
            for (int j = 0; j < this.numColumns; j++){
                /* Formatting with "%1$-" will add padding to the right of
                 * our string this.optionsColumns.get(j).get(i). */
                line += getOptionCharacterLabel(i, j) + String.format("%1$-" +
                        numLongestCharsWithPadding + "s",
                        this.optionsColumns.get(j).get(i));
            }
            // Output String containing options of row i.
            ioService.writeIndentedContent(line);
        }
    }

    private static int getNumLongestOptionChars(List<String> opts) {
        int n = 0;
        // Iterate through all options.
        for (String opt : opts){
            // If opt is longer than n, set n to opt's length.
            if (opt.length() > n){
                n = opt.length();
            }
        }
        return n;
    }

    public void setup(IOService ioService){
        super.setup(ioService);
        int num = ioService.getNumberFromUser("Enter number of options.");
        // Iterate through all options in all columns...
        for (int i = 0; i < this.numColumns; i++){
            for (int j = 0; j < num; j++){
                String prompt = "Enter option " + (j+1);
                if (this.numColumns != 1){
                    prompt += " for column " + (i+1);
                }
                // Prompt user for option values.
                String opt = ioService.getStringFromUser(prompt);
                // Add user-specified option value to optionsColumns.
                this.optionsColumns.get(i).add(opt);
            }
        }
    }

    private static String getOptionCharacterLabel(int i, int j){
        // For the second column of matching, use numbers.
        if (j == 1){
            return i+1 + ") ";
        // Otherwise, use letters.
        }else {
            // Convert number to a char, where i=0=>A, i=1=>B, etc.
            char c = (char) (i + 65);
            String label = c + ") ";
            return label;
        }
    }

}
