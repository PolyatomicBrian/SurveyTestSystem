package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;

public class MultipleChoiceQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public MultipleChoiceQuestion(){
        this.prompt = new ColumnOptionsPrompt(1);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        boolean contains = this.prompt.getColumn(0).contains(resp.getResponse());
        if (!contains){
            throw new Exception("Selection not listed.");
        }
    }

    @Override
    protected Response formatResponse(Response resp) {
        return resp;
    }

    @Override
    protected Prompt getPrompt() {
        return this.prompt;
    }
}
