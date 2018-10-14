package ast;

import libs.Node;
import ui.Main;

public class EQUATION extends Node {
    String prompt;
    String answer;
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
        answer = tokenizer.getNext();
        // Optional separator comma for PHRASE var name
        if(tokenizer.checkToken(",")){
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
        }
        else {
            question = prompt;
        }
        return question;
    }
}
