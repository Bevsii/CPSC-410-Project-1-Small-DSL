package ast;

import libs.Tokenizer;
import ui.Main;

public class QUESTION extends STATEMENT {
    String name;

    CONTENT content;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        tokenizer.getAndCheckNext("MAKEQUESTION");
        // Store question Name
        name = tokenizer.getNext();
        // parse CONTENT to store PROMPT, ANSWER, and maybe PHRASE
        content = new CONTENT();
        content.parse();

    }

    @Override
    public String evaluate(){
        // String questionContent = content.evaluate();
        // System.out.println("Setting "+name+" to the question "+ questionContent);
        Main.symbolTable.put(name, this);
        // i.e. Q1 contains its name, Q1, and more importantly, the CONTENT with PROMPT, ANSWER, CHOICES, and PHRASE (as variable name)
        return null;
    }

    public CONTENT getContent(){
        return content;
    }

    public String getName(){
        return name;
    }
}