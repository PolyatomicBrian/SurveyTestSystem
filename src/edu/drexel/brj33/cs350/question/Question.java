package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.response.Response;

import java.io.Serializable;

public class Question implements Serializable {

    public void submitResponse(Response resp) throws Exception {
        // Called by ConsoleHandler after user responds to a question.
        // Throws an Exception when a response is Invalid.
    }

    private boolean isValidResponse(Response resp){
        return true;
    }

}
