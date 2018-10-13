package libs;

import java.io.PrintWriter;

public abstract class Node {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;

    abstract public void parse();
    abstract public String evaluate();


}
