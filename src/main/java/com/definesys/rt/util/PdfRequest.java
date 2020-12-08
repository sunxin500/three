package com.definesys.rt.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//简单的pdfdemo
public class PdfRequest {

    public static File createPDF(String start, String end, String today, String name, String num, String desc) {
        File file = null;
        try{
            //1.新建document对象
            Document document = new Document(PageSize.A4);

            //2.建立一个书写器Writer 与document对象关联
            file = new File("PDFDemo.pdf");
//            file.createNewFile();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

            pdfWriter.setPageEvent(new Watermark("HELLO ITEXTPDF"));   //水印
//            pdfWriter.setPageEvent(new MyHeaderFooter());   //页眉/页脚
            pdfWriter.setPageEvent(new PDFHeader());    //页眉/页脚

            //3.打开文档
            document.open();
            document.addTitle("Title@XX-Java"); //标题
            document.addAuthor("Author@xinxin"); //作者
            document.addSubject("Subject@iText pdf sample"); //主题
            document.addKeywords("Keywords@iTextpdf"); //关键字
            document.addCreator("Creator@umiz`s");// 创建者

            //4.向文档添加内容
            Document pdf = new PdfRequest().generatePDF(document, start, end, today, name, num, desc);

            System.out.println("完成创建文档");
            //5.关闭文档
            document.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    //定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;

    //最大宽度
    private static int maxWidth = 520;

    //静态代码块
    static {
        try {
            //不同字体（这里定义位同一种字体，包含不同字号，不同style）
            BaseFont font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(font, 16, Font.BOLD);
            headfont = new Font(font,10,Font.BOLD);
            keyfont = new Font(font,8,Font.BOLD);
            textfont = new Font(font,10,Font.NORMAL);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }


    //生成PDF文件
    public Document generatePDF(Document document,String start,String end,String today,String name,String num,String desc) throws Exception {

//        paragraph.setIndentationLeft(12); //设置左缩进
//        paragraph.setIndentationRight(12); //设置右缩进
//        paragraph.setFirstLineIndent(24); //设置首行缩进
//        paragraph.setLeading(20f); //行间距
//        paragraph.setSpacingBefore(5f); //设置段落上空白
//        paragraph.setSpacingAfter(10f); //设置段落下空白
        //段落1
        Paragraph paragraph1 = new Paragraph("· 上海三菱电梯系统开发人天报告", titlefont);
        paragraph1.setAlignment(0); //设置文字居中 0 靠左 1 居中  2 靠右
        paragraph1.setSpacingAfter(10f); //设置段落下空白

        //段落2
        Paragraph paragraph2 = new Paragraph("开发期间："+ start+ " 到 "+ end, headfont);
        paragraph2.setAlignment(0);

        //段落3
        Paragraph paragraph3 = new Paragraph("报告人员："+ "张少伟", headfont);
        paragraph3.setAlignment(0);

        //段落4
        Paragraph paragraph4 = new Paragraph("报告时间："+ today, headfont);
        paragraph4.setAlignment(0);

        //段落5
        Paragraph paragraph5 = new Paragraph("开发工程师："+ name, headfont);
        paragraph5.setAlignment(0);

        //段落6
        Paragraph paragraph6 = new Paragraph("工作总天数："+ num+ " 天", headfont);
        paragraph6.setAlignment(0);

        //段落7
        Paragraph paragraph7 = new Paragraph("工作内容：", headfont);
        paragraph7.setAlignment(0);
        paragraph7.setSpacingBefore(10f); //设置段落上空白
        paragraph7.setSpacingAfter(5f); //设置段落下空白

        //段落8
//        Paragraph paragraph8 = new Paragraph(text,textfont);
//        paragraph8.setAlignment(0);
//        paragraph8.setIndentationLeft(5); //设置左缩进
//        paragraph8.setLeading(20f); //行间距


//        //添加图片
//        Image image = Image.getInstance("C:\\Users\\Hasee\\Desktop\\sanling.png");
//        image.setAlignment(Image.ALIGN_LEFT);
//        image.scalePercent(40); //依照比例缩放

        //直线1
        Paragraph p1 = new Paragraph();
        LineSeparator lineSeparator1 = new LineSeparator(); //实线
        lineSeparator1.setLineWidth(5.0f); //宽度
        Chunk chunk1 = new Chunk(lineSeparator1);
        chunk1.setLineHeight(10.0f);//高度
        p1.add(chunk1);

        //直线2
        Paragraph p2 = new Paragraph();
        LineSeparator lineSeparator2 = new LineSeparator(); //实线
        lineSeparator2.setLineWidth(2.0f); //宽度
        Chunk chunk2 = new Chunk(lineSeparator2);
        chunk2.setLineHeight(10.0f);//高度
        p2.add(chunk2);

        //直线3
        Paragraph p3 = new Paragraph();
        LineSeparator lineSeparator3 = new LineSeparator(); //实线
        lineSeparator3.setLineWidth(2.0f); //宽度
        Chunk chunk3 = new Chunk(lineSeparator3);
        chunk3.setLineHeight(30.0f);//高度
        p3.add(chunk3);

        //点线
//        Paragraph p = new Paragraph();
//        p.add(new Chunk(new DottedLineSeparator()));

//        //超连接
//        Anchor anchor = new Anchor("baidu");
//        anchor.setReference("www.baidu.com");
//
//        //定位
//        Anchor gotop = new Anchor("goto");
//        gotop.setReference("#top");

        //表格
        PdfPTable table = createTable(2,Element.ALIGN_LEFT);
        table.addCell(createCell("=双方签字确认=",headfont,Element.ALIGN_LEFT, 6,false));
        table.addCell(createCell("上海三菱电梯有限公司", keyfont, Element.ALIGN_LEFT));
        table.addCell(createCell("上海得帆信息技术有限公司", keyfont, Element.ALIGN_LEFT));
        table.addCell(createCell("签字代表：", keyfont, Element.ALIGN_LEFT));
        table.addCell(createCell("签字代表：", keyfont, Element.ALIGN_LEFT));
        table.addCell(createCell("日期：", keyfont, Element.ALIGN_LEFT));
        table.addCell(createCell("日期：", keyfont, Element.ALIGN_LEFT));

        document.add(p1);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(p2);
        document.add(paragraph5);
        document.add(paragraph6);
        document.add(paragraph7);

        //添加工作内容
        List<String> list = StringUtil.splitString(desc,"\n");
        for (int i = 0; i < list.size(); i++) {
            Paragraph paragraph = new Paragraph(list.get(i), textfont);
            paragraph.setAlignment(0);
            paragraph.setIndentationLeft(5); //设置左缩进
            paragraph.setLeading(20f); //行间距
            document.add(paragraph);
        }
        document.add(p3);
        document.add(table);

        return document;
    }



    /**--------------------------------------------创建表格单元格的方法-----------------------------------------------------*/

    /**
     * 创建单元格（指定字体）
     * @param value
     * @param font
     * @return
     */
    public PdfPCell createCell(String value,Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
     * 创建单元格（指定字体，水平。。。）
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(8.0f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
     * 创建单元格（指定字体，水平居。。。 单元格跨x 列合并）
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align,int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        return cell;
    }

    /**
     * 创建单元格（指定字体，水平居。。。单元格跨x列合并，设置单元格内的边距）
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @param boderFlag
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value,font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPadding(15.0f);
            cell.setPaddingBottom(8.0f);
        }else if (boderFlag) {
            cell.setBorder(0);
            cell.setPadding(0.0f);
            cell.setPaddingBottom(15.0f);
        }
        return cell;
    }


    public PdfPCell createCell(String value,Font font,int align,float[] borderWidth, float[] paddingSize,boolean flag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        cell.setBorderWidthLeft(borderWidth[0]);
        cell.setBorderWidthRight(borderWidth[1]);
        cell.setBorderWidthTop(borderWidth[2]);
        cell.setBorderWidthBottom(borderWidth[3]);
        cell.setPaddingTop(paddingSize[0]);
        cell.setPaddingBottom(paddingSize[1]);
        if (flag) {
            cell.setColspan(2);
        }
        return cell;
    }

/**------------------------------------创建单元格的方法end-------------------------------------------------------*/


/**----------------------------------------创建表格的方法start--------------------------------------------------------*/

    /**
     * 创建默认宽，指定列数，水平（居中，左，右）的表格
     * @param colNumber
     * @param align
     * @return
     */
    public PdfPTable createTable(int colNumber, int align) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(align);
            table.getDefaultCell().setBorder(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 创建指定列宽，列数的表格
     * @param widths
     * @return
     */
    public PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 创建空白的表格
     * @return
     */
    public PdfPTable createTable() {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("",keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }
/**-----------------------------------------------创建表格的方法end----------------------------------------------*/


    public static void main(String[] args) throws Exception{

    }


}
