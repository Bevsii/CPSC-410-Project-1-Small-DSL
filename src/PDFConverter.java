import org.apache.pdfbox.cos.ICOSVisitor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

public class PDFConverter {
    private String fileName = "TestPDF.pdf";

    public PDFConverter(){

    }

    public void createPDF() {

        try {
            PDDocument document = new PDDocument();
            document.addPage(new PDPage());

            document.save(fileName);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
