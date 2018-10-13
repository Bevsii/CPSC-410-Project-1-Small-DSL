package ast;


public class Set extends Statement {
    private String name;
    private String value;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("set");
        name = tokenizer.getNext();
        value = tokenizer.getNext();
    }

    @Override
    public String evaluate()  {
        System.out.println("Setting "+name+" to "+value);
        Program.symbolTable.put(name,value);
        return null;
    }
}
