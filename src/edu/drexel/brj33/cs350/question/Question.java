package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.service.IOService;
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
        // Called after user responds to a question.
        // Throws an Exception when a response is Invalid.
        validateResponse(resp);
        this.responses.add(formatResponse(resp));
    }

    public void setup(IOService ioService){
        this.getPrompt().setup(ioService);
    }

    public void display(IOService ioService){
        this.getPrompt().display(ioService);
    }

    protected abstract void validateResponse(Response resp) throws Exception;

    protected abstract Response formatResponse(Response resp);

    protected abstract Prompt getPrompt();

    protected int getNumberResponses(){
        return this.numResponses;
    }

    protected void setNumberResponses(int num){
        this.numResponses = num;
    }

    public void take(IOService ioService){
        this.display(ioService);
        {
            try {
                String userInputResponse = ioService.getStringFromUser("> ");
                this.submitResponse(new Response(userInputResponse));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void edit(IOService ioService){

    }
}
