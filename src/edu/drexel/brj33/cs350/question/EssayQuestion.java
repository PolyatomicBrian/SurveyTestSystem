package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

public class EssayQuestion extends Question {

    private Prompt prompt;

    public EssayQuestion(){
        this.prompt = new Prompt();
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {

    }

    @Override
    protected Response formatResponse(Response resp) {
        return null;
    }

    @Override
    protected Prompt getPrompt() {
        return this.prompt;
    }
}
