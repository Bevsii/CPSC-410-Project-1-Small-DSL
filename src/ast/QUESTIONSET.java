package ast;

import libs.Node;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class QUESTIONSET extends Statement {
    String name;
    List<String> vars = new ArrayList<>();
    List<EQUATION> equations = new ArrayList<>();


    @Override
    public void parse(){
        tokenizer.getAndCheckNext("MAKESET");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("[");
        while (!tokenizer.checkToken("]")){
            if(!tokenizer.checkToken(",")){
                // If not an EQUATION (AKA unnamed) prompt/answer tuple,
                if(!tokenizer.checkToken("{")) {
                    // Add var name to list
                    vars.add(tokenizer.getNext());
                }
                else{   // Is an EQUATION
                    EQUATION eq = new EQUATION();
                    eq.parse();
                    equations.add(eq);
                }
            }
            else {
                tokenizer.getNext(); // Skip the commas
            }
        }
        tokenizer.getAndCheckNext("]");
    }

    @Override
    public String evaluate(){
        String set = "";
        for(String v : vars){
            set += Main.symbolTable.get(v) + "\n";
        }
        for(EQUATION eq : equations){
            set += eq.evaluate() + "\n";
        }

        System.out.println("Setting "+name+" to the questions/question set: "+ set);
        Main.symbolTable.put(name,set);
        return null;
    }
}
