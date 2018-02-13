package com.alex.smartHome.src.model.reports;

import data.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Created by cosma on 06.05.2017.
 */
public class PdfReport implements Report {
    private String content;

    public void write(String place){
        int rowCnt = 40;
        getContent(place);
        String[] rs = content.split("\n");
        try{
            PDDocument doc = new PDDocument();
            PDFont font = PDType1Font.TIMES_ROMAN;
            //while(rowCnt>0) {

                PDPage page = new PDPage();
                doc.addPage(page);

                PDPageContentStream content = new PDPageContentStream(doc, page);

                content.beginText();
                content.setFont(font, 12);
                content.newLineAtOffset(80, 700);
                content.setLeading(14.5f);

                for(int i=0;i<rs.length;i++){
                    if(rowCnt!=0) {
                        if (rs[i].length() != 0) {
                            if (rs[i].length() > 5)
                                content.showText(rs[i] + ", ");
                            else {
                                content.showText(rs[i]);
                                content.newLine();
                                rowCnt--;
                            }
                        }
                    }
                    else
                    {
                        content.endText();
                        content.close();
                        rowCnt = 40;

                        page = new PDPage();
                        doc.addPage(page);
                        content = new PDPageContentStream(doc, page);
                        content.beginText();
                        content.setFont(font, 12);
                        content.newLineAtOffset(80, 700);
                        content.setLeading(14.5f);
                        if (rs[i].length() != 0) {
                            if (rs[i].length() > 5)
                                content.showText(rs[i] + ", ");
                            else {
                                content.showText(rs[i]);
                                content.newLine();
                                rowCnt--;
                            }
                        }
                    }
                }

                content.endText();
                content.close();
            //}
            doc.save("reports/report.pdf");
            doc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getContent(String place) {
        content = (new Logger().getInfo(place));
    }
}
