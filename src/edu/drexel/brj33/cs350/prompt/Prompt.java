package edu.drexel.brj33.cs350.prompt;

import edu.drexel.brj33.cs350.service.IOService;

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

    public void display(IOService ioService){
        ioService.writeTitle(this.getPromptText());
    }

    public void setup(IOService is){
        String p = is.getStringFromUser("Enter a prompt for the question.");
        this.promptText = p;
    }

    public void edit(IOService ioService){

    }
}
