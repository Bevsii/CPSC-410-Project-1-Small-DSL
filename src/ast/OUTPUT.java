package ast;

import libs.PDFConverter;
import libs.Tokenizer;
import ui.Main;

public class OUTPUT extends STATEMENT {
    String questionSetName;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        tokenizer.getAndCheckNext("MAKEOUTPUT");
        questionSetName = tokenizer.getNext();
    }

    @Override
    public String evaluate(){
        PDFConverter pdfConverter = new PDFConverter();
        QUESTIONSET questionSet = (QUESTIONSET) Main.symbolTable.get(questionSetName);
        pdfConverter.createPDF(questionSet);
        System.out.println("FINISHED!!!!");
        return null;
    }
}
