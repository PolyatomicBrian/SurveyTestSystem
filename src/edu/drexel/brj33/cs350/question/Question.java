package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

import java.io.Serializable;

public abstract class Question implements Serializable {

    public void submitResponse(Response resp) throws Exception {
        // Called by ConsoleHandler after user responds to a question.
        // Throws an Exception when a response is Invalid.
    }

    protected abstract boolean isValidResponse(Response resp);

    public abstract Prompt getPrompt();


}
