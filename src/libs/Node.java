package libs;

public abstract class Node {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();

    abstract public void parse();
    abstract public String evaluate();


}
