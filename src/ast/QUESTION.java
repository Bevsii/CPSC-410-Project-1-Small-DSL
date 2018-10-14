package ast;

import ui.Main;

public class QUESTION extends Statement {
    String name;

    EQUATION eq;

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("MAKEQUESTION");
        // Store question Name
        name = tokenizer.getNext();
        // parse EQUATION to store PROMPT, ANSWER, and maybe PHRASE
        eq = new EQUATION();
        eq.parse();

    }

    @Override
    public String evaluate(){
        String question = eq.evaluate();
        System.out.println("Setting "+name+" to the question "+ question);
        Main.symbolTable.put(name, question);
        // i.e. Q1 = "Solve the following equation: 4*3+1"
        return null;
    }
}