package ast;


public class Get extends Statement {
    private String name;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("get");
        name = tokenizer.getNext();
    }

    @Override
    public String evaluate() {
        System.out.println("Getting "+name+" from Symbol Table");
        return String.valueOf(Program.symbolTable.get(name));
    }
}
