package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

public class MatchingQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public MatchingQuestion(){
        this.prompt = new ColumnOptionsPrompt(2);
    }

    public void setup(IOService ioService){
        this.getPrompt().setup(ioService);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        // Todo, make sure we throw an exception.
        this.prompt.getColumn(0).contains(resp.getResponse());
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
