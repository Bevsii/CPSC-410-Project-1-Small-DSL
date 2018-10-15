package ast;

import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class QUESTIONSET extends Statement {
    String name;
    List<String> vars = new ArrayList<>();
    List<CONTENT> content = new ArrayList<>();
    List<QUESTION> questions = new ArrayList<QUESTION>();


    @Override
    public void parse(){
        tokenizer.getAndCheckNext("MAKESET");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("[");
        while (!tokenizer.checkToken("]")){
            if(!tokenizer.checkToken(",")){
                // If not an CONTENT (AKA unnamed) prompt/answer tuple,
                if(!tokenizer.checkToken("{")) {
                    // Add var name to list
                    vars.add(tokenizer.getNext());
                }
                else{   // Is an CONTENT
                    CONTENT contents = new CONTENT();
                    contents.parse();
                    this.content.add(contents);
                }
            }
            else {
                tokenizer.getNext(); // Skip the commas
            }
        }
        tokenizer.getAndCheckNext("]");
    }

    @Override
    public String evaluate(){
        String set = "";
        for(String v : vars){
            set += Main.symbolTable.get(v) + "\n";
        }
        for(CONTENT ct : content){
            set += ct.evaluate() + "\n";
        }

        System.out.println("Setting "+name+" to the questions/question set: "+ set);
        Main.symbolTable.put(name,set);
        return null;
    }

    public List<QUESTION> getQuestions(){
        return questions;
    }
}
