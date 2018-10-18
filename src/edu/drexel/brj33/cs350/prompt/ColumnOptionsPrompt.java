package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.io.InputService;
import edu.drexel.brj33.cs350.io.OutputService;

import java.util.ArrayList;
import java.util.List;

public class ColumnOptionsPrompt extends Prompt {
    private List<List<String>> optionsColumns;
    private int numColumns;

    public ColumnOptionsPrompt(int numColumns){
        this.numColumns = numColumns;
        this.optionsColumns = new ArrayList<List<String>>();
    }

    public int getNumColumns(){
        return numColumns;
    }

    public List<String> getColumn(int i){
        return optionsColumns.get(i);
    }

    public void formatForOutput(OutputService of){
        super.formatForOutput(of);
        of.writeContent("ello");
    }

    public void configureWithInput(InputService is){
        super.configureWithInput(is);
        is.getNumberFromUser("Enter number of options.");
    }

}
