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
//            if (tokenizer.checkToken("set")) {
//                s = new SET();
//            }
//            else if (tokenizer.checkToken("get")){
//                s = new USE();
//            }
//            else if (tokenizer.checkToken("new")){
//                s = new DEC();
//            }
//            else if (tokenizer.checkToken("print")){
//                s = new PRINT();
//            }
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
