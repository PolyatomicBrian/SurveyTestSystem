package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.question.*;
import edu.drexel.brj33.cs350.service.IOService;
import edu.drexel.brj33.cs350.service.SerializingInterface;

import java.util.ArrayList;
import java.util.List;

public class Survey implements SerializingInterface {
    private List<Question> questions;
    private String surveyName;
    public static final String fileExtension = "survey";

    public Survey(){
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setup(IOService ioService){
        String surveyName = ioService.getStringFromUser("Enter a name:");
        this.setSurveyName(surveyName);

        Menu m = new Menu(this);
        // Use Lambda expressions, which will be treated as a MenuAction type.
        m.addMenuOptionAction("Add a new T/F question", ()->{
            addQuestion(new TrueFalseQuestion(), ioService);
        });
        m.addMenuOptionAction("Add a new multiple choice question", ()->{
            addQuestion(new MultipleChoiceQuestion(), ioService);
        });
        m.addMenuOptionAction("Add a new short answer question", ()->{
            addQuestion(new ShortAnswerQuestion(), ioService);
        });
        m.addMenuOptionAction("Add a new essay question", ()->{
            addQuestion(new EssayQuestion(), ioService);
        });
        m.addMenuOptionAction("Add a new ranking question", ()->{
            addQuestion(new RankingQuestion(), ioService);
        });
        m.addMenuOptionAction("Add a new matching question", ()->{
            addQuestion(new MatchingQuestion(), ioService);
        });
        m.handleMenu(ioService);
    }

    public String getSurveyName(){
        return this.surveyName;
    }

    public void setSurveyName(String surveyName){
        this.surveyName = surveyName;
    }

    public String getFileName(){
        return getSurveyName() + "." + fileExtension;
    }

    public void display(IOService ioService) {
        for (Question q : this.questions){
            q.display(ioService);
        }
    }

    public void take(IOService ioService){
        for (Question q : this.questions){
            q.take(ioService);
        }
    }

    public void edit(IOService ioService){

    }

    public void addQuestion(Question q, IOService ioService){
        this.questions.add(q);
        q.setup(ioService);
    }

    public Survey deepCopy(Survey s){
        // Returns a deep copy of the survey.
        // To be used to serialize a survey containing responses.
        return null;
    }

    public String toString(){
        return this.getFileName();
    }

}
