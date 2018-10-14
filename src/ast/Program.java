package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program extends Node{
    private List<Statement> statements = new ArrayList<>();

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            //Statement s = Statement.getSubStatement();
            Statement s = null;
            if (tokenizer.checkToken("MAKEPHRASE")){
                s = new PHRASE();
            }
            else if (tokenizer.checkToken("MAKEQUESTION")){
                s = new QUESTION();
            }
            else if (tokenizer.checkToken("MAKESET")){
                s = new QUESTIONSET();
            }
            else if (tokenizer.checkToken("MAKETEST")){
                s = new TEST();
            }
            else if (tokenizer.checkToken("MAKEOUTPUT")){
                s = new OUTPUT();
            }
            s.parse();
            statements.add(s);
        }

    }

    @Override
    public String evaluate() {
        for (Statement s : statements){
            s.evaluate();
        }
        return null;
    }
}
