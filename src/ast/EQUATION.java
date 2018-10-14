package ast;

import libs.Node;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class EQUATION extends Node {
    String prompt;
    String answer = null;
    List<String> answers = new ArrayList<>();
    String phrasevar = null;

    @Override
    public void parse(){
        // {
        tokenizer.getAndCheckNext("{");
        // "Equation" comes next
        prompt = tokenizer.getNext();
        // Separator comma
        tokenizer.getAndCheckNext(",");
        // "Solution" to the "Equation"
        if (tokenizer.checkToken("{")){             // If multiple choice
            tokenizer.getAndCheckNext("{");
            while (!tokenizer.checkToken("}")){
                if(!tokenizer.checkToken(",")){
                    answers.add(tokenizer.getNext());
                }
                else{
                    tokenizer.getNext();                    // Skip comma separator
                }
            }
            tokenizer.getAndCheckNext("}");
        }
        else {                                              // Else it's short answer
            answer = tokenizer.getNext();
        }
        // Optional separator comma for PHRASE var name
        if(tokenizer.checkToken(",")){
            tokenizer.getAndCheckNext(",");
            phrasevar = tokenizer.getNext();
        }
        // End of equation,answer,phrase container
        tokenizer.getAndCheckNext("}");
    }

    @Override
    public String evaluate(){
        String question;
        // TODO: !!!GIVE PHRASE A DEFAULT VALUE AS INTENDED!!!
        String phrase;
        if(phrasevar != null){
            phrase = Main.symbolTable.get(phrasevar) + ": ";
            question = phrase + prompt;
            if(!answers.isEmpty() && answer == null){
                question += "\n";
                for (String ans : answers){
                    question += ans + "\n";
                }
            }
        }
        else {
            question = prompt;
        }
        return question;
    }
}
