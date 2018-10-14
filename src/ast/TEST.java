package ast;

import libs.Node;
import ui.Main;

import java.util.List;

public class TEST extends Statement {
    List<String> vars;  // vars can contain TEST vars AND QUESTIONSET vars.
    String name;

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("MAKETEST");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("{");
        while(!tokenizer.checkToken("}")){
            QUESTIONSET qs = null;
            if(!tokenizer.checkToken(",")){
                vars.add(tokenizer.getNext());
            }
            else {
                tokenizer.getNext(); // Skip the commas
            }
        }
    }

    @Override
    public String evaluate(){
        String test = "";
        for(String var : vars){
            test += Main.symbolTable.get(var) + "\n";
        }
        Main.symbolTable.put(name, test);
        return null;
    }
}
