package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

public class MatchingQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public MatchingQuestion(){
        this.prompt = new ColumnOptionsPrompt(2);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        // Todo, make sure we throw an exception.
        this.prompt.getColumn(0).contains(resp.getResponse());
    }

    @Override
    protected Response formatResponse(Response resp) {
        return null;
    }

    @Override
    public Prompt getPrompt() {
        return this.prompt;
    }
}
