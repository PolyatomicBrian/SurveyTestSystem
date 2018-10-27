package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.question.Question;
import edu.drexel.brj33.cs350.response.Response;
import edu.drexel.brj33.cs350.service.IOService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Test extends Survey {
    private List<Set<Response>> correctAnswers;

    public Test(){
        super();
        correctAnswers = new ArrayList<Set<Response>>();
    }

    public void addQuestion(Question q, IOService ioService){
        super.addQuestion(q, ioService);
        ioService.writeTitle("Enter the correct answer for this question.");
        // Have admin "take" the question by adding a correct answer.
        // This allows admin to verify their correct answer is a valid response
        // for that question type.
        q.take(ioService);
        this.correctAnswers.add(q.getResponses());
        // Clear admin's answer from question's responses.
        q.clearResponses();
    }

    public void display(IOService ioService){
        super.display(ioService);
        for (Set<Response> r : correctAnswers){
            ioService.writeTitle(r.toString());
        }
    }

    public void grade(IOService ioService){

    }
}
