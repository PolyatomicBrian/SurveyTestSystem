package edu.drexel.brj33.cs350.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputService {

    private BufferedReader br;
    private OutputService os;

    public InputService(InputStream is, OutputService os) {
        this.br = new BufferedReader(new InputStreamReader(is));
        this.os = os;
    }

    public String getStringFromUser(String description){
        this.os.writeContent(description);
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
        this.os.writeContent(description);
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


}
