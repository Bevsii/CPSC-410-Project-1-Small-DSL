package ast;

import libs.Tokenizer;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class QUESTIONSET extends STATEMENT {
    String name;
    List<String> vars = new ArrayList<>(); //vars are question names
    List<CONTENT> content = new ArrayList<>();
    List<QUESTION> questions = new ArrayList<QUESTION>();


    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        tokenizer.getAndCheckNext("MAKESET");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("\\[");
        while (!tokenizer.checkToken("\\]")){
            if(!tokenizer.checkToken(",")){
                // If not a CONTENT (AKA unnamed question)
                if(!tokenizer.checkToken("\\{")) {
                    // Add var name to list
                    vars.add(tokenizer.getNext());
                }
                else{   // Is a CONTENT
                    CONTENT contents = new CONTENT();
                    contents.parse();
                    this.content.add(contents);
                }
            }
            else {
                tokenizer.getNext(); // Skip the commas
            }
        }
        tokenizer.getAndCheckNext("\\]");
    }

    @Override
    public String evaluate(){
        //use questions array instead of String set
        String useless = "";
        for(String v : vars){
            // Check if Main.symbolTable.get(v) is a QUESTIONSET and add questions to this.questions
            Object questionOrSet = Main.symbolTable.get(v);
            if(questionOrSet instanceof QUESTIONSET) {
                QUESTIONSET set = (QUESTIONSET) questionOrSet;
                for (QUESTION q : set.getQuestions()){
                    this.questions.add(q);
                }
            }
            // If of QUESTION type, just add to this.questions
            else if (questionOrSet instanceof QUESTION) {
                QUESTION q = (QUESTION) questionOrSet;
                this.questions.add(q);
            }
        }
        for(CONTENT ct : content){
            // Make a question with an arbitrary name and add it to this.questions
            QUESTION q = new QUESTION();
            q.content = ct;
            this.questions.add(q);
        }
        Main.symbolTable.put(name,this);
        return null;
    }

    public List<QUESTION> getQuestions(){
        return questions;
    }
}
