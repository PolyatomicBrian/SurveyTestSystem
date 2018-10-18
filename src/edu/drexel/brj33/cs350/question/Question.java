package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Question implements Serializable {

    // Using a Set to prevent duplicates and allow for unordered elements.
    protected Set<Response> responses;

    private int numResponses = 0;

    public Question(){
        this.responses = new HashSet<Response>();
    }

    public void submitResponse(Response resp) throws Exception {
        // Called by ConsoleHandler after user responds to a question.
        // Throws an Exception when a response is Invalid.
        validateResponse(resp);
        this.responses.add(formatResponse(resp));
    }

    protected abstract void validateResponse(Response resp) throws Exception;

    protected abstract Response formatResponse(Response resp);

    public abstract Prompt getPrompt();

    public int getNumberResponses(){
        return this.numResponses;
    }

    public void setNumberResponses(int num){
        this.numResponses = num;
    }
}
