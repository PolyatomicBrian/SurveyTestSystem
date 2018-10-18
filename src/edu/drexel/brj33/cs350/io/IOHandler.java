package edu.drexel.brj33.cs350.io;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.question.Question;

import java.io.InputStream;
import java.io.OutputStream;

public class IOHandler {

    private InputService is;
    private OutputService os;

    public IOHandler(OutputStream os, InputStream is){
        this.os = new OutputService(os);
        this.is = new InputService(is, this.os);
    }

    public void setUpQuestion(Question q){
        int num = is.getNumberFromUser("Enter number of responses.");
        q.setNumberResponses(num);
        q.getPrompt().configureWithInput(this.is);

    }


    public void displayQuestion(Question q){
        this.displayPrompt(q.getPrompt());
    }

    protected void displayPrompt(Prompt p){


        if(p instanceof ColumnOptionsPrompt){

        }

        //p.formatForOutput();
    }
}
