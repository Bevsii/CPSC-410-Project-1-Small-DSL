import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.cos.ICOSVisitor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;

public class PDFConverter {
    //File Data
    private String fileName = "TestPDF.pdf";
    private PDDocument document;

    // Margins
    private int marginTop = 30;
    private int marginLeft = 30;

    // Fonts
    PDFont titleFont = PDType1Font.HELVETICA_BOLD;
    int titleFontSize = 26;
    PDFont textFont = PDType1Font.TIMES_BOLD;
    int textFontSize = 16;



    public PDFConverter(){
        document = new PDDocument();
    }

    public void createPDF() {
        try {
            createTitlePage("Title font");

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
        content.newLineAtOffset(0,-40);
        content.showText("Student ID: _________________");
        content.newLineAtOffset(0,-40);
        content.showText("Grade: _________________");
        content.endText();

        content.close();
    }
}
