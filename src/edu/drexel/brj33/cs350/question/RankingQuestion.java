package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

public class RankingQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public RankingQuestion(){
        this.prompt = new ColumnOptionsPrompt(1);
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
