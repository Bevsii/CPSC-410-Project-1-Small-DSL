package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program extends Node{
    private List<Statement> statements = new ArrayList<>();
    static Map<String,Object> symbolTable = new HashMap<>();

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            Statement s = Statement.getSubStatement();
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
