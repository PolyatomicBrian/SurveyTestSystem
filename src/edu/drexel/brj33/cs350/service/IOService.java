package edu.drexel.brj33.cs350.service;

import java.io.*;
import java.util.List;

public class IOService {

    private OutputStream os;
    private BufferedReader br;

    public IOService(OutputStream os, InputStream is){
        this.os = os;
        this.br = new BufferedReader(new InputStreamReader(is));

    }

    public void writeTitle(String header){
        // Used to display some form of header.
        // In our Console implementation, this has no substance.
        // If we need to implement this in some other medium,
        // we can easily adapt this.
        writeContent(header);
    }

    public void writeContent(String line){
        // Used to display content.
        writeContent(line, true);
    }

    public void writeContent(String line, boolean newLine){
        // Will add a newline if specified, and then display the content.
        try {
            if (newLine) {
                line += "\n";
            }
            os.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeIndentedContent(String line){
        writeContent("\t" + line);
    }

    public void writeSeparator() {
        writeContent("--------------------------------------------------------------------", true);
    }

    public String getStringFromUser(String description){
        if (description == null){
            description = "> ";
        }else{
            description += "\n> ";
        }
        this.writeContent(description, false);
        String s = null;
        while (s == null){
            try {
                // Prompt user for some String.
                String userInput = br.readLine();
                // Ensure input received is not empty.
                if (!userInput.trim().isEmpty()) {
                    s = userInput;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return s;
    }

    public int getNumberFromUser(String description) {
        if (description == null){
            description = "> ";
        }else{
            description += "\n> ";
        }
        Integer userNumber = null;
        while (userNumber == null) {
            try {
                // Prompt user for some number.
                this.writeContent(description, false);
                String userInput = br.readLine();
                userNumber = Integer.valueOf(userInput);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return userNumber;
    }

    public int getChoiceFromUser(List<? extends Object> choices){
        // Prompt user for a choice, where a "choice" corresponds to an object
        // contained within the List argument.
        Integer ret = null;
        if (choices.isEmpty()){
            throw new RuntimeException("Choices for user to select from is empty!");
        }
        // Loop until valid selection made.
        while (ret == null) {
            for (int i = 0; i < choices.size(); i++) {
                this.writeContent((i + 1) + ") " + choices.get(i).toString());
            }
            int selection = this.getNumberFromUser("Enter your selection.");
            if (selection <= choices.size() && selection > 0) {
                ret = selection - 1;
            }else{
                this.writeContent("Invalid selection, try again.");
            }
        }
        return ret;
    }

}
