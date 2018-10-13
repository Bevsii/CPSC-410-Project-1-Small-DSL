package ast;

import libs.Node;

public  abstract class Statement extends Node {
    public static Statement getSubStatement(){
        if (tokenizer.checkToken("set")) {
            return new Set();
        }
        if (tokenizer.checkToken("get")){
            return new Get();
        }
        else return null;
    }
}
