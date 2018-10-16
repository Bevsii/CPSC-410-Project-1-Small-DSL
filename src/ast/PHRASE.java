package ast;

import libs.Tokenizer;
import ui.Main;

public class PHRASE extends STATEMENT {
    String name;
    String phrase;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        //MAKEPHRASE
        tokenizer.getAndCheckNext("MAKEPHRASE");
        // Phrase Name
        name = tokenizer.getNext();
        // Open '
        tokenizer.getAndCheckNext("\\{");
        // Phrase content
        phrase = tokenizer.getNext();
        // Closing '
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public String evaluate(){
        System.out.println("Setting "+name+" to the String "+phrase);
        Main.symbolTable.put(name, phrase);
        return null;
    }
}
