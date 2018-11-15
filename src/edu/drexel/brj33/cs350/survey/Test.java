package edu.drexel.brj33.cs350.survey;

import edu.drexel.brj33.cs350.menu.Menu;
import edu.drexel.brj33.cs350.question.EssayQuestion;
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
        if (!(q instanceof EssayQuestion)) {
            ioService.writeTitle("Enter the correct answer for this question.");
            // Have admin "take" the question by adding a correct answer.
            // This allows admin to verify their correct answer is a valid response
            // for that question type.
            q.take(ioService);
            this.correctAnswers.add(q.getResponses());
            // Clear admin's answer from question's responses.
            q.clearResponses();
        }else{
            // Essay questions don't have correct answers, so add "null" instead.
            // Having a null value as a correct answer will make grading easier.
            this.correctAnswers.add(null);
        }
    }

    public void display(IOService ioService){
        ioService.writeSeparator();
        for (int i = 0; i < getQuestions().size(); i ++) {
            ioService.writeContent("Question " + (i+1) + " of " + getQuestions().size(), true);
            getQuestions().get(i).display(ioService);
            if (correctAnswers.get(i) != null) {
                ioService.writeContent("Correct Answer: " + correctAnswers.get(i).toString());
            }
            ioService.writeSeparator();
        }
    }

    public void edit(IOService ioService){
        // Display new menu, asking user if they want to edit questions or answers.
        Menu m = new Menu(null);
        // Edit questions, do so the way Survey would edit them.
        m.addMenuOptionAction("Edit Questions.", ()->{
            super.edit(ioService);
        });
        // Edit answers, specific to Test.
        m.addMenuOptionAction("Edit Answer Keys.", ()->{
            List<String> availableQuestions = new ArrayList<>();
            // Display questions that user can edit.
            for (int i = 0; i < this.getQuestions().size(); i++){
                Question q = this.getQuestions().get(i);
                String displayString = "Question " + (i+1);
                displayString += " (" + q.getQuestionTypeDisplayName() + "): ";
                displayString += q.getPrompt().getPromptText();
                availableQuestions.add(displayString);
            }
            // Ask user which question's answer they plan on changing.
            ioService.writeContent("Select question to edit.");
            int selected_index = ioService.getChoiceFromUser(availableQuestions);
            Question q = this.getQuestions().get(selected_index);
            // Have admin "take" the question, ie answer it with a correct answer.
            q.take(ioService);
            // Overwrite existing answer with new answer.
            this.correctAnswers.set(selected_index, q.getResponses());
            // Clear all previous answers from question's responses.
            // We do so b/c what if someone changed "I love dogs" to "I hate dogs"?
            q.clearResponses();
        });
        m.handleMenu(ioService);
    }

    public void grade(IOService ioService){
        int tallyCorrect = 0;
        int totalGradedQuestions = 0;
        for (int i = 0; i < this.getQuestions().size(); i++){
            Question q = this.getQuestions().get(i);
            Set<Response> qResp = q.getResponses();
            Set<Response> correctAns = this.correctAnswers.get(i);
            // Essay Questions have null correctAns, since essay questions don't have any correct answers.
            if (correctAns != null) {
                totalGradedQuestions++;
                ioService.writeTitle("Question " + (i+1) + ": " + q.getPrompt().getPromptText());
                ioService.writeSeparator();
                if (correctAns.containsAll(qResp)) {
                    ioService.writeContent("You got this right!");
                    tallyCorrect++;
                } else {
                    ioService.writeContent("You got this wrong.");
                }
                ioService.writeIndentedContent("Correct = " + correctAns.toString());
                ioService.writeIndentedContent("You said = " + qResp.toString());
            }
        }
        ioService.writeContent("You scored " + tallyCorrect*10 + "/" + totalGradedQuestions*10 + "!");
        ioService.writeSeparator();
    }
}
