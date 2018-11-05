package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

public class ShortAnswerQuestion extends Question {

    private Prompt prompt;

    public ShortAnswerQuestion(){
        this.prompt = new Prompt();
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        // Ensure inputted response is valid.
        if (resp == null || resp.getResponse().isEmpty()){
            throw new Exception("Invalid response!");
        }
    }

    @Override
    protected Response formatResponse(Response resp) {
        // Short Answer Questions do not need to be formatted to adhere to how we display content.
        return resp;
    }

    @Override
    protected Prompt getPrompt() {
        return this.prompt;
    }
}
