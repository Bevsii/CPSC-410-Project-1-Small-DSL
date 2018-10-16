package ast;

import libs.Node;
import libs.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node {
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
        Tokenizer tokenizer = Tokenizer.getTokenizer();

        while (tokenizer.moreTokens()) {
            //STATEMENT s = STATEMENT.getSubStatement();
            STATEMENT s = null;
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
        for (STATEMENT s : statements){
            s.evaluate();
        }
        return null;
    }
}
