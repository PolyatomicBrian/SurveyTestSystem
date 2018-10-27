package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.question.Question;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

import java.util.List;
import java.util.Set;

public class Test extends Survey {
    private List<Set<Response>> correctAnswers;

    public void addQuestion(Question q, IOService ioService){
        super.addQuestion(q, ioService);
        // Todo require user to add Correct Answer as well.
    }

    public void grade(IOService ioService){

    }
}
