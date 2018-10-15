package libs;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {


    private static String program;
    private static List<String> literals;
    private String[] tokens;
    private int currentToken;
    private static Tokenizer theTokenizer;

    private Tokenizer(String inputText, List<String> literalsList){
        if (inputText.length() < 1) {
            System.out.println("Empty Input");
            return;
        }
        literals = literalsList;
        program = inputText;
        tokenize();
    }

    private void tokenize (){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");
        tokenizedProgram = tokenizedProgram.replaceAll("([0-9]+)","_$1_");
        System.out.println(program);

        for (String s : literals){
            tokenizedProgram = tokenizedProgram.replace(s,"_"+s+"_");
            System.out.println(tokenizedProgram);
        }
        tokenizedProgram = tokenizedProgram.replaceAll("_ ","_");
        tokenizedProgram = tokenizedProgram.replaceAll(" _","_");
        System.out.println(tokenizedProgram);
        String [] temparray=tokenizedProgram.split("[_]+");
        tokens = new String[temparray.length-1];

        System.arraycopy(temparray,1,tokens,0,temparray.length-1);
        System.out.println(Arrays.asList(tokens));
    }

    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    public String getNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }


    public boolean checkToken(String regexp){
        String s = checkNext();
        System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) {
            System.out.println("FAILED!!!!");
            System.exit(0);
        }
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }

    public boolean moreTokens(){
        return currentToken<tokens.length;
    }

    public static void makeTokenizer(String inputText, List<String> literals){
        if (theTokenizer==null){
            theTokenizer = new Tokenizer(inputText,literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}
