package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.question.*;
import edu.drexel.brj33.cs350.service.IOService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Survey implements Serializable {
    private List<Question> questions;
    private String surveyName;

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

        /*
         * Use Lambda expressions, which will be treated as a MenuAction type.
         * These Lambda expressions will be called when the corresponding menu
         * option is selected.
         */
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

    public void display(IOService ioService) {
        ioService.writeSeparator();
        for (int i = 0; i < getQuestions().size(); i ++) {
            ioService.writeContent("Question " + (i+1) + " of " + getQuestions().size(), true);
            getQuestions().get(i).display(ioService);
            ioService.writeSeparator();
        }
    }

    public void take(IOService ioService){
        for (Question q : this.questions){
            q.take(ioService);
        }
    }

    public void edit(IOService ioService){
        List<String> availableQuestions = new ArrayList<>();
        for (int i = 0; i < this.questions.size(); i++){
            Question q = this.questions.get(i);
            String displayString = "Question " + (i+1);
            displayString += " (" + q.getQuestionTypeDisplayName() + "): ";
            displayString += q.getPrompt().getPromptText();
            availableQuestions.add(displayString);
        }
        ioService.writeContent("Select question to edit.");
        int selected_index = ioService.getChoiceFromUser(availableQuestions);
        this.questions.get(selected_index).edit(ioService);
    }

    public void addQuestion(Question q, IOService ioService){
        this.questions.add(q);
        q.setup(ioService);
    }

    public String toString(){
        return this.getSurveyName();
    }

}
