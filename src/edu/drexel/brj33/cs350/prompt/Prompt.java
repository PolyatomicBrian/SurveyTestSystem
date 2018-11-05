package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.service.IOService;

import java.io.Serializable;

/**
 * A Prompt is just a sentence-based question (no options). An example of usage is in a True/False question:
 *    True or False: This is a sentence-based question.
 */
public class Prompt implements Serializable {

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

    public void display(IOService ioService){
        // Print out this prompt.
        ioService.writeTitle(this.getPromptText());
    }

    public void setup(IOService is){
        // Get input from user about what the prompt should be.
        String p = is.getStringFromUser("Enter a prompt for the question.");
        this.promptText = p;
    }

    public void edit(IOService ioService){
        /**
         * TODO, next homework.
         */
    }
}
