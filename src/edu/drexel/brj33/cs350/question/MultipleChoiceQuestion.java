package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

public class MultipleChoiceQuestion extends Question {

    private ColumnOptionsPrompt prompt;

    public MultipleChoiceQuestion(){
        this.prompt = new ColumnOptionsPrompt(1);
    }

    @Override
    public void setup(IOService ioService){
        // When creating a new question, set a new prompt and the number of responses.
        this.getPrompt().setup(ioService);
        this.numResponses = ioService.getNumberFromUser("Enter number of allowed responses.");
        // Ensure the inputted number is greater than 0, but less than or equal to the num of options.
        while (this.numResponses <= 0 || this.numResponses > this.prompt.getNumOptions()){
            this.numResponses = ioService.getNumberFromUser("Enter number of allowed responses.");
        }
    }

    @Override
    protected void validateResponse(Response resp) throws Exception {
        // Ensures the selected option is actually a listed option.
        if (resp.getResponse().length() != 1){
            throw new Exception("Selection not listed.");
        }
        // Also checks that the selected option is at least 0.
        boolean contains = this.prompt.getColumn(0).size() > strToResponseIndex(resp.getResponse()) &&
                           strToResponseIndex(resp.getResponse()) >= 0;
        if (!contains){
            throw new Exception("Selection not listed.");
        }
    }

    @Override
    protected Response formatResponse(Response resp) {
        // Multiple Choice Questions do not need to be formatted to adhere to how we display content.
        return resp;
    }

    @Override
    public Prompt getPrompt() {
        return this.prompt;
    }

    private int strToResponseIndex(String s){
        /* Get the index of a specified Menu label.
         * eg. A) Option one
         *    would have A correspond to 0.
         *    Then we get index 0 from our list.
         */
        s = s.toUpperCase();
        int index = s.charAt(0) - 65;
        return index;
    }

    @Override
    public String getQuestionTypeDisplayName() {
        return "Multiple Choice Question";
    }
}
