package edu.drexel.brj33.cs350.io;

import edu.drexel.brj33.cs350.prompt.ColumnOptionsPrompt;
import edu.drexel.brj33.cs350.prompt.Prompt;
import edu.drexel.brj33.cs350.question.Question;

import java.io.InputStream;
import java.io.OutputStream;

public class IOHandler {

    private InputStream is;
    private OutputFormatter of;

    public IOHandler(OutputStream os, InputStream is){
        this.os = os;
        this.of = new OutputFormatter(os);
        this.is = is;
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
