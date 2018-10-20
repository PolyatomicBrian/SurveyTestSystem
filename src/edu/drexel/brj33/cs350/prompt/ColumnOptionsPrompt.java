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
    }

    public int getNumColumns(){
        return numColumns;
    }

    public List<String> getColumn(int i){
        return optionsColumns.get(i);
    }

    public void display(IOService ioService){
        super.display(ioService);
    }

    public void setup(IOService ioService){
        super.setup(ioService);
        ioService.getNumberFromUser("Enter number of options.");
    }

}
