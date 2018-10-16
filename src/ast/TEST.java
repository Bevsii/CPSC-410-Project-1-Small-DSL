package ast;

import libs.Tokenizer;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class TEST extends STATEMENT {
    private List<String> vars = new ArrayList<>();  // vars can contain TEST vars AND QUESTIONSET vars.
    private String name;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        tokenizer.getAndCheckNext("MAKETEST");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("\\{");
        while(!tokenizer.checkToken("\\}")){
            if(!tokenizer.checkToken(",")){
                vars.add(tokenizer.getNext());
            }
            else {
                tokenizer.getNext(); // Skip the commas
            }
        }
        tokenizer.getAndCheckNext("\\}");
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
