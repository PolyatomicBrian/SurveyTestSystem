package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

public class TrueFalseQuestion extends Question {

    private Prompt prompt;

    public TrueFalseQuestion(){
        this.prompt = new Prompt();
    }

    public void setup(IOService ioService){
        this.getPrompt().setup(ioService);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {

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
