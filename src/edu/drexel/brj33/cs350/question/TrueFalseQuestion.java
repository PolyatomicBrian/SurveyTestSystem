package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

import java.util.ArrayList;
import java.util.List;

public class TrueFalseQuestion extends Question {

    private Prompt prompt;

    public TrueFalseQuestion(){
        this.prompt = new Prompt();
    }

    public void setup(IOService ioService){
        this.getPrompt().setup(ioService);
    }

    @Override
    public void display(IOService ioService){
        super.display(ioService);
        ioService.writeContent("T/F", true);
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        List<String> validResponses = new ArrayList<String>();
        validResponses.add("T");
        validResponses.add("F");
        validResponses.add("TRUE");
        validResponses.add("FALSE");
        // Make sure they enter some variant of True / False.
        if (!validResponses.contains(resp.getResponse().toUpperCase())){
            throw new Exception("Invalid response. Must be T or F.");
        }
    }

    @Override
    protected Response formatResponse(Response resp) {
        // True/False Questions do not need to be formatted to adhere to how we display content.
        return resp;
    }

    @Override
    protected Prompt getPrompt() {
        return this.prompt;
    }
}
