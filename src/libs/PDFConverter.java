package libs;

import ast.QUESTION;
import ast.QUESTIONSET;
import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.ICOSVisitor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;
import java.util.ArrayList;

public class PDFConverter {
    //File Data
    private String fileName = "Output.pdf";
    private PDDocument document;

    // Margins
    private int marginTop = 30;
    private int marginLeft = 30;
    private int questionSpace = 400;

    // Fonts
    PDFont titleFont = PDType1Font.HELVETICA_BOLD;
    int titleFontSize = 26;
    PDFont textFont = PDType1Font.TIMES_ROMAN;
    int textFontSize = 16;

    //TEMP
    private ArrayList<String> questionList;


    public PDFConverter() {
        document = new PDDocument();

        //temp
        questionList = new ArrayList<String>();
        questionList.add("Question1?");
        questionList.add("Question1?Question1?Question1?Question1?Question1?Question1?");
        questionList.add("Question1?  Question1?");
        questionList.add("Question1? _____________ Question1?Question1?");
        questionList.add("What is the meaning of life?");
        questionList.add("The name of the type of study cognitive psychologists use is ________");
        questionList.add("Why are you like this?");
    }

    public void createTestPDF(QUESTIONSET questionSet) {

        try {
            createTitlePage("Test");

            populateQuestions(questionSet);

            document.save(fileName);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void createTitlePage(String title) throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream content = new PDPageContentStream(document, page);

        float titleWidth = titleFont.getStringWidth(title) / 1000 * titleFontSize;
        float titleHeight = titleFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleFontSize;

        content.beginText();
        content.setFont(titleFont, titleFontSize);
        content.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - marginTop - titleHeight);
        content.showText(title);
        content.endText();

        content.beginText();
        content.setFont(textFont, textFontSize);
        content.newLineAtOffset(marginLeft, page.getMediaBox().getHeight() - marginTop - 100);
        content.showText("Name: _________________");
        content.newLineAtOffset(0, -40);
        content.showText("Student ID: _________________");
        content.newLineAtOffset(0, -40);
        content.showText("Signature: _________________");
        content.endText();

        content.close();
    }

    private void populateQuestions(QUESTIONSET questionSet) throws IOException {
        int questionPageCount = 0;
        PDPage currentPage = null;
        PDPageContentStream content = null;
        boolean wasLastQuestionMultipleChoice = false;

        for (QUESTION question : questionSet.getQuestions()) {

            boolean isFirstQuestionOnPage = false;

            if (questionPageCount % 3 == 0) {
                isFirstQuestionOnPage = true;
                wasLastQuestionMultipleChoice = false;

                // Close the content of the previous page
                if (questionPageCount != 0 && null != content) {
                    System.out.println("Bevlog: Not first!");
                    content.endText();
                    content.close();
                }
                System.out.println("Bevlog: New Page!");
                currentPage = new PDPage();
                document.addPage(currentPage);

                content = new PDPageContentStream(document, currentPage);
                content.beginText();
                content.setFont(textFont, textFontSize);
            }

            System.out.println("Bevlog: Question number " + questionPageCount);

            if (isFirstQuestionOnPage)
                content.newLineAtOffset(marginLeft, currentPage.getMediaBox().getHeight() - marginTop - 100);
            else if (wasLastQuestionMultipleChoice) {
                content.newLineAtOffset(-50, -30);
                wasLastQuestionMultipleChoice = false;
            } else {
                content.newLineAtOffset(0, -200);
            }

            content.showText(questionPageCount + 1 + ". " + question.getContent().getPhrase() + " " + question.getContent().getPrompt());

            if (question.getContent().getChoices().size() > 0) {
                content.newLineAtOffset(50, -50);

                int i = 1;
                for (String choice : question.getContent().getChoices()) {
                    content.showText(getCharForNumber(i) + ". " + choice);
                    content.newLineAtOffset(0, -30);
                    i++;
                }
                wasLastQuestionMultipleChoice = true;
            }

            questionPageCount++;
        }
        content.close();
    }

    public void createAnswerKeyPDF(QUESTIONSET questions) {
        try {
            PDDocument answerKeyDocument = new PDDocument();


            PDPage page = new PDPage();
            answerKeyDocument.addPage(page);
            PDPageContentStream content = new PDPageContentStream(answerKeyDocument, page);

            float titleWidth = titleFont.getStringWidth("Answer Key") / 1000 * titleFontSize;
            float titleHeight = titleFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleFontSize;

            content.beginText();
            content.setFont(titleFont, titleFontSize);
            content.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - marginTop - titleHeight);
            content.showText("Answer Key");
            content.endText();

            content.beginText();
            content.setFont(textFont, textFontSize);
            content.newLineAtOffset(marginLeft, page.getMediaBox().getHeight() - marginTop - 100);
            int i = 1;
            for (QUESTION question : questions.getQuestions()) {
                content.showText(i + ": " + question.getContent().getAnswer());
                content.newLineAtOffset(0, -textFontSize * 2);
                i++;
            }
            content.endText();
            content.close();

            answerKeyDocument.save("AnswerKey.pdf");
            answerKeyDocument.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }
}