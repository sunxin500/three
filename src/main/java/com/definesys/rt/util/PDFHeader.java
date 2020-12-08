package com.definesys.rt.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class PDFHeader extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(1);
        try {
            table.setTotalWidth(530);
            Image image = Image.getInstance("C:\\Users\\Hasee\\Desktop\\sanling.png");
            image.setAlignment(Image.ALIGN_LEFT);
            image.setWidthPercentage(20);
            image.scalePercent(40); //依照比例缩放
            PdfPCell cell = new PdfPCell();
            cell.setBorder(0);
            cell.setPaddingTop(-20f);
            cell.addElement(image);
            cell.setBorderWidthBottom(1);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 30, 806, writer.getDirectContent());
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
