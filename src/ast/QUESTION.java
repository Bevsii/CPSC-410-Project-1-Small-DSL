package ast;

import ui.Main;

public class QUESTION extends Statement {
    String name;

    CONTENT content;

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("MAKEQUESTION");
        // Store question Name
        name = tokenizer.getNext();
        // parse CONTENT to store PROMPT, ANSWER, and maybe PHRASE
        content = new CONTENT();
        content.parse();

    }

    @Override
    public String evaluate(){
        String question = content.evaluate();
        System.out.println("Setting "+name+" to the question "+ question);
        Main.symbolTable.put(name, question);
        // i.e. Q1 = "Solve the following equation: 4*3+1"
        return null;
    }

    public CONTENT getContent(){
        return content;
    }

    public String getName(){
        return name;
    }
}