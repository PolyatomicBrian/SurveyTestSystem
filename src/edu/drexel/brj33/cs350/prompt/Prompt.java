package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.io.InputService;
import edu.drexel.brj33.cs350.io.OutputService;

public class Prompt {

    private String promptText;

    public Prompt(){
        this.promptText = "";
    }

    public String getPromptText() {
        return this.promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public void formatForOutput(OutputService of){
        of.writeTitle(this.getPromptText());
    }

    public void configureWithInput(InputService is){
        String p = is.getStringFromUser("Enter a prompt for the question.");
        this.promptText = p;
    }
}
