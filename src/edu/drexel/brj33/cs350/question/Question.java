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

    protected int numResponses = 1;

    public Question(){
        this.responses = new HashSet<Response>();
    }

    public Set<Response> getResponses(){
        return this.responses;
    }

    public void clearResponses(){
        this.responses = new HashSet<>();
    }

    public void submitResponse(Response resp) throws Exception {
        // Called after user responds to a question.
        // Throws an Exception when a response is Invalid.
        validateResponse(resp);
        this.responses.add(formatResponse(resp));
    }

    public void setup(IOService ioService){
        // When creating a new question, set a new prompt and the number of responses.
        this.getPrompt().setup(ioService);
        this.numResponses = ioService.getNumberFromUser("Enter number of allowed responses.");
    }

    public void display(IOService ioService){
        // Display the prompt of the question.
        this.getPrompt().display(ioService);
    }

    protected abstract void validateResponse(Response resp) throws Exception;

    protected abstract Response formatResponse(Response resp);

    public abstract Prompt getPrompt();

    public abstract String getQuestionTypeDisplayName();

    protected int getNumberResponses(){
        return this.numResponses;
    }

    public void take(IOService ioService){
        // Method is called whenever a user is answering a question.
        // Display the question.
        this.display(ioService);
        // While we don't have the expected number of responses...
        while(this.responses.size() < this.getNumberResponses()){
            try {
                // Get a response.
                String userInputResponse = ioService.getStringFromUser(null);
                // Submit the response.
                this.submitResponse(new Response(userInputResponse));
            } catch (Exception e) {
                // Display an appropriate error message.
                ioService.writeContent(e.getMessage());
            }
        }
    }

    public void edit(IOService ioService){
        this.getPrompt().edit(ioService);
    }
}
