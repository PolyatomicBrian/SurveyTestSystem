package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

public class RankingQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public RankingQuestion(){
        this.prompt = new ColumnOptionsPrompt(1);
    }

    public void setup(IOService ioService){
        this.getPrompt().setup(ioService);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {

    }

    @Override
    protected Response formatResponse(Response resp) {
        // Ranking Questions do not need to be formatted to adhere to how we display content.
        return resp;
    }

    @Override
    protected Prompt getPrompt() {
        return this.prompt;
    }

}
