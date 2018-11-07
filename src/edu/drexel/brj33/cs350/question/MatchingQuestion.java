package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

import java.util.HashSet;
import java.util.Set;

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
        // Ensure inputted response is valid.
        String[] splitResp = resp.getResponse().toUpperCase().split(" ");
        Set<String> dedupedOptions = new HashSet<>();
        int numOpts = this.prompt.getColumn(0).size();
        // Ensure all options were matched.
        if (splitResp.length != numOpts){
            throw new Exception("All options must be matched (delimited by spaces)!");
        }
        for (String s : splitResp){
            // Ensure two characters were entered per delimitation.
            if (s.length() != 2){
                throw new Exception("Enter a space-delimited answer!");
            }
            // Get each character of delimitation.
            String opt1 = s.substring(0,1);
            String opt2 = s.substring(1,2);
            // Determine if char then num, or num then char.
            String index1 = resolveOption(opt1, numOpts);
            String index2 = resolveOption(opt2, numOpts);
            // Add to set.
            dedupedOptions.add(index1);
            dedupedOptions.add(index2);

        }
        if (dedupedOptions.size() != numOpts * 2){
            throw new Exception("All options must be matched exactly once!");
        }
    }

    private String resolveOption(String opt, int maxOpt) throws Exception{
        try {
            // Ensure valid integer selection.
            int i = Integer.parseInt(opt);
            if (i >= 1 && i <= maxOpt){
                return opt;
            } else {
                throw new Exception("Selection not listed!");
            }
        }catch(Exception e){
            // Ensure valid character selection.
            int c = (int) opt.charAt(0) - 65;
            if(c >= 0 && c < maxOpt) {
                return opt;
            } else {
                throw new Exception("Selection not listed!");
            }
        }
    }

    @Override
    protected Response formatResponse(Response resp) {
        // Matching Questions do not need to be formatted to adhere to how we display content.
        return resp;
    }

    @Override
    public Prompt getPrompt() {
        return this.prompt;
    }

    @Override
    public String getQuestionTypeDisplayName() {
        return "Matching Question";
    }
}
