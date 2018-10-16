package ast;

import libs.Node;
import libs.Tokenizer;
import ui.Main;

public class OUTPUT extends Statement {
    String test;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        tokenizer.getAndCheckNext("MAKEOUTPUT");
        test = tokenizer.getNext();
    }

    @Override
    public String evaluate(){
        //TODO: Convert to PDF
        Main.symbolTable.get(test);
        return null;
    }
}
