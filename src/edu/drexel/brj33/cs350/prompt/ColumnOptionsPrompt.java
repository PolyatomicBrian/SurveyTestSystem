package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.io.OutputFormatter;

import java.util.List;

public class ColumnOptionsPrompt extends Prompt {
    private List<String>[] optionsColumns;
    private int numColumns;

    public ColumnOptionsPrompt(String promptText, List<String> ... optionsColumns){
        super(promptText);
        numColumns = optionsColumns.length;
        this.optionsColumns = optionsColumns;
    }

    public int getNumColumns(){
        return numColumns;
    }

    public List<String> getColumn(int i){
        return optionsColumns[i];
    }

    public void formatForOutput(OutputFormatter of){
        super.formatForOutput(of);
        of.writeContent("ello");
    }

}
