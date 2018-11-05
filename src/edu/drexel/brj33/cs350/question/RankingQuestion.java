package edu.drexel.brj33.cs350.question;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

import java.util.HashSet;
import java.util.Set;

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
        // Ensure inputted response is valid.
        String[] splitResp = resp.getResponse().toUpperCase().split(" ");
        // Sets prevent duplicates, so we'll make sure no dupes are used.
        Set<String> dedupedOptions = new HashSet<>();
        int numOpts = this.prompt.getColumn(0).size();
        // Ensure all options were matched.
        if (splitResp.length != numOpts){
            throw new Exception("All options must be ranked (delimited by spaces)!");
        }
        for (String s : splitResp){
            // Ensure character was entered per delimitation.
            if (s.length() != 1){
                throw new Exception("Enter a space-delimited answer!");
            }
            // Get the character of delimitation.
            String opt1 = s.substring(0,1);
            // Ensure the character is one that's actually listed.
            int c = (int) opt1.charAt(0) - 65;
            if(!(c >= 0 && c < numOpts)) {
                throw new Exception("Selection not listed!");
            }
            // Add to set.
            dedupedOptions.add(opt1);
        }
        // Ensure the set is the appropriate size, ie no dupes were ever added,
        // meaning the user did not enter any dupes.
        if (dedupedOptions.size() != numOpts){
            throw new Exception("All options must be matched exactly once!");
        }
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
