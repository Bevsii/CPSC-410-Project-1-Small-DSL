package ast;

import libs.Tokenizer;
import ui.Main;

import java.util.ArrayList;
import java.util.List;

public class CONTENT extends STATEMENT {
    public String prompt;
    public String answer = null;
    public List<String> choices = new ArrayList<String>();
    public String phrasevar = null;

    @Override
    public void parse(){
        Tokenizer tokenizer = Tokenizer.getTokenizer();
        // {
        tokenizer.getAndCheckNext("\\{");
        // "Equation" comes next
        prompt = tokenizer.getNext();
        // Separator comma
        tokenizer.getAndCheckNext(",");
        // "Solution" to the "Equation"
        answer = tokenizer.getNext();
        // Is the question multiple choice? (i.e. is there an array of answers directly after the one ANSWER?
        if (tokenizer.checkToken("\\{")){             // If multiple choice
            tokenizer.getAndCheckNext("\\{");         // Skip bracket
            while (!tokenizer.checkToken("\\}")){
                if(!tokenizer.checkToken(",")){
                    choices.add(tokenizer.getNext());
                }
                else{
                    tokenizer.getNext();                    // Skip comma separator
                }
            }
            tokenizer.getAndCheckNext("\\}");         // Skip closing bracket
        }
        // Optional separator comma for PHRASE var name
        if(tokenizer.checkToken(",")){
            tokenizer.getAndCheckNext(",");
            phrasevar = tokenizer.getNext();
        }
        // End of equation,answer,phrase container
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public String evaluate(){
/*      ||| UNNEEDED EVALUATION |||
        String question;
        String phrase;
        if(phrasevar != null){
            phrase = Main.symbolTable.get(phrasevar) + ": ";
            question = phrase + prompt;
            if(!choices.isEmpty() && answer == null){
                question += "\n";
                for (String ans : choices){
                    question += ans + "\n";
                }
            }
        }
        else {
            question = prompt;
        }*/
        return null;
    }

    public void setPrompt(String str){
        this.prompt = str;
    }

    public void setAnswer(String ans){
        this.answer = ans;
    }

    public void setChoices(List<String> choiceList){
        this.choices = choiceList;
    }

    public void setPhrasevar (String pvar){
        this.phrasevar = pvar;
    }

    public String getPrompt(){
        if (null == prompt){
            prompt ="";
        }
        return this.prompt;
    }

    public String getAnswer(){
        return this.answer;
    }

    public List<String> getChoices(){
        return this.choices;
    }

    public String getPhrase (){
        if (null == phrasevar){
            return "";
        }
        return (String) Main.symbolTable.get(this.phrasevar);
    }
}
