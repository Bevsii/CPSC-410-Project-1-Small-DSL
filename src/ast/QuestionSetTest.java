package ast;

import java.util.ArrayList;

/**
 * Created by rorycourt on 2018-10-14.
 */
public class QuestionSetTest {

    CONTENT content = new CONTENT();
    CONTENT content2 = new CONTENT();
    QUESTION question = new QUESTION();
    QUESTION question2 = new QUESTION();
    QUESTIONSET qSet = new QUESTIONSET();

    public void setupContent(){
        content.prompt = "4*3+1";
        content.answer = "13";

        ArrayList<String> newChoices = new ArrayList<String>();
        newChoices.add("13");
        newChoices.add("12");

        newChoices.add("16");
        content.choices = newChoices;
        content.phrasevar = "Solve the following equation";
    }

    public void setContent2(){
        content2.prompt = "12/6+4";
        content2.answer = "6";
    }

    public void setQuestion(){
        question.name = "Q1";
        question.content = content;
    }

    public void setQuestion2 (){
        question2.name = "Q2";
        question2.content = content2
    }

    public void setQuestionSet(){
        qSet.questions.add(question);
        qSet.questions.add(question2);
    }
}
