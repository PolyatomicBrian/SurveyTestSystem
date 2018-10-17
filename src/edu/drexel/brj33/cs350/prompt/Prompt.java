package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.io.OutputFormatter;

public class Prompt {

    private String promptText;

    public Prompt(){
        this.promptText = "";
    }

    public Prompt(String promptText){
        this.promptText = promptText;
    }

    public String getPromptText() {
        return this.promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public void formatForOutput(OutputFormatter of){
        of.writeHeader(this.getPromptText());
    }
}
