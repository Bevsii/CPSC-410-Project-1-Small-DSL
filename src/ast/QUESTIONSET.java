package ast;

import libs.Tokenizer;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class QUESTIONSET extends Statement {
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
                // If not an CONTENT (AKA unnamed) prompt/answer tuple,
                if(!tokenizer.checkToken("\\{")) {
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
        tokenizer.getAndCheckNext("\\]");
    }

    @Override
    public String evaluate(){
        //use questions array instead of String set
        String set = "";
        for(String v : vars){
            //TODO check if Main.symbolTable.get(v) is a QUESTIONSET
            //  if so, add all questions in QUESTIONSET to this.questions
            //check if Main.symbolTable.get(v) is a QUESTION
            //  if it is, add it directly to this.questions
            set += Main.symbolTable.get(v) + "\n";
        }
        for(CONTENT ct : content){
            //TODO make a question with an arbitrary name and add it to this.questions
            set += ct.evaluate() + "\n";
        }

        System.out.println("Setting "+name+" to the questions/question set: "+ set);
        //TODO actually want to store this into symbol table
        Main.symbolTable.put(name,set);
        return null;
    }

    public List<QUESTION> getQuestions(){
        return questions;
    }
}
