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

    }

    public void writeContent(String line){
        try {
            line += "\n";
            os.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringFromUser(String description){
        this.writeContent(description);
        String s = null;
        while (s == null){
            try {
                String userInput = br.readLine();
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
        this.writeContent(description);
        Integer userNumber = null;
        while (userNumber == null) {
            try {
                String userInput = br.readLine();
                userNumber = Integer.valueOf(userInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userNumber;
    }

    public int getChoiceFromUser(List<? extends Object> choices){
        Integer ret = null;
        while (ret == null) {
            for (int i = 0; i < choices.size(); i++) {
                this.writeContent((i + 1) + ") " + choices.get(i).toString());
            }
            int selection = this.getNumberFromUser("Enter your selection: ");
            if (selection <= choices.size() && selection > 0) {
                ret = selection - 1;
            }else{
                this.writeContent("Invalid selection, try again.");
            }
        }
        return ret;
    }

}
