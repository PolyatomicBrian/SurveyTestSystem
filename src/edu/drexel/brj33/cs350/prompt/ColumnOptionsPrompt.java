package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.service.IOService;

import java.util.ArrayList;
import java.util.List;

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
            for (int j = 0; j < this.numColumns; j++){
                // Add options of row i together, separated by a tab.
                line += this.optionsColumns.get(j).get(i) + "\t";
            }
            // Output String containing options of row i.
            ioService.writeContent(line);
        }
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

}
