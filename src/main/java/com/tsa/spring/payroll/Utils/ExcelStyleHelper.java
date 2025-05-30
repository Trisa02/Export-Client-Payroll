package com.tsa.spring.payroll.Utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;

public class ExcelStyleHelper {

    public static final String STYLE_KIRI = "kiri";
    public static final String STYLE_TENGAH = "tengah";
    public static final String STYLE_KANAN = "kanan";
    public static final String STYLE_UANG = "uang";
    public static final String STYLE_PERSEN = "persen";
    public static final String STYLE_MERGE_CENTER = "merge";
    public static final String STYLE_BOLD = "bold";
    public static final String STYLE_TANGGAL_BOLD = "tanggal_bold";
    public static final String STYLE_TANGGAL= "tanggal";
    public static final String STYLE_HEADER = "header";


    public static Map<String, CellStyle> createStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new HashMap<>();

        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 9);

        XSSFFont boldFont = (XSSFFont) workbook.createFont();
        boldFont.setBold(true);


        DataFormat dataFormat = workbook.createDataFormat();
        short uangFormat = dataFormat.getFormat("#,##0;(#,##0);\"-\"");
        short persenFormat = dataFormat.getFormat("0.00%");

        CellStyle styleKiri = workbook.createCellStyle();
        styleKiri.setAlignment(HorizontalAlignment.LEFT);
        styleKiri.setVerticalAlignment(VerticalAlignment.CENTER);
        styleKiri.setFont(font);
        setBorder(styleKiri);
        styles.put(STYLE_KIRI, styleKiri);

        CellStyle styleTengah = workbook.createCellStyle();
        styleTengah.setAlignment(HorizontalAlignment.CENTER);
        styleTengah.setVerticalAlignment(VerticalAlignment.CENTER);
        styleTengah.setFont(font);
        setBorder(styleTengah);
        styles.put(STYLE_TENGAH, styleTengah);

        CellStyle styleKanan = workbook.createCellStyle();
        styleKanan.setAlignment(HorizontalAlignment.RIGHT);
        styleKanan.setVerticalAlignment(VerticalAlignment.CENTER);
        styleKanan.setFont(font);
        setBorder(styleKanan);
        styles.put(STYLE_KANAN, styleKanan);

        CellStyle styleUang = workbook.createCellStyle();
        styleUang.setAlignment(HorizontalAlignment.RIGHT);
        styleUang.setVerticalAlignment(VerticalAlignment.CENTER);
        styleUang.setFont(font);
        styleUang.setDataFormat(uangFormat);
        setBorder(styleUang);
        styles.put(STYLE_UANG, styleUang);

        CellStyle stylePersen = workbook.createCellStyle();
        stylePersen.setAlignment(HorizontalAlignment.RIGHT);
        stylePersen.setVerticalAlignment(VerticalAlignment.CENTER);
        stylePersen.setFont(font);
        stylePersen.setDataFormat(persenFormat);
        setBorder(stylePersen);
        styles.put(STYLE_PERSEN, stylePersen);

        CellStyle styleMergeLeft = workbook.createCellStyle();
        styleMergeLeft.setAlignment(HorizontalAlignment.LEFT);    
        styleMergeLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleMergeLeft.setFont(font);
        setBorder(styleMergeLeft);
        styles.put(STYLE_MERGE_CENTER, styleMergeLeft);

        CellStyle styleBold = workbook.createCellStyle();
        styleBold.setAlignment(HorizontalAlignment.CENTER);
        styleBold.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBold.setFont(createFont(workbook, true)); 
        setBorder(styleBold);
        styles.put(STYLE_BOLD, styleBold);


        // Style Tanggal dengan Bold
        XSSFCellStyle styleTanggalBold = (XSSFCellStyle) workbook.createCellStyle();
        styleTanggalBold.setAlignment(HorizontalAlignment.CENTER);
        styleTanggalBold.setVerticalAlignment(VerticalAlignment.CENTER);
        styleTanggalBold.setFont(boldFont);
        XSSFCreationHelper createHelper = (XSSFCreationHelper) workbook.getCreationHelper();
        styleTanggalBold.setDataFormat(createHelper.createDataFormat().getFormat("MMM-yyyy"));
        styles.put(STYLE_TANGGAL_BOLD, styleTanggalBold);

        // Style Tanggal tanpa Bold
        XSSFCellStyle styleTanggalNormal = (XSSFCellStyle) workbook.createCellStyle();
        styleTanggalNormal.setAlignment(HorizontalAlignment.LEFT);
        styleTanggalNormal.setVerticalAlignment(VerticalAlignment.CENTER);
        styleTanggalNormal.setDataFormat(createHelper.createDataFormat().getFormat("MMM-yyyy"));
        styles.put(STYLE_TANGGAL, styleTanggalNormal);

        // Contoh penggunaan kondisi
       
        CellStyle styleHeader = createColoredStyle(workbook, new byte[] {(byte) 146, (byte) 208, (byte) 80});
        styles.put(STYLE_HEADER, styleHeader);

        return styles;
    }

    private static Font createFont(Workbook workbook, boolean isBold) {
        Font font = workbook.createFont();
        font.setBold(isBold);  // Jika isBold true, maka font bold
        return font;
    }

     private static CellStyle createColoredStyle(Workbook workbook, byte[] rgb) {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        XSSFColor color = new XSSFColor(rgb, null);
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);   // Rata Tengah
        style.setVerticalAlignment(VerticalAlignment.CENTER);  // Rata Tengah Vertikal
        style.setFont(createFont(workbook, true));  // Font Tebal (Bold)
        setBorder(style);
        return style;
    }

    private static void setBorder(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

    
    public static CellStyle applyBoldToStyle(Workbook workbook, CellStyle baseStyle) {
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(baseStyle);

        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        newStyle.setFont(boldFont);

        return newStyle;
    }

    public static CellStyle applyColorToStyle(Workbook workbook, CellStyle baseStyle, byte[] rgb) {
        XSSFCellStyle newStyle = (XSSFCellStyle) workbook.createCellStyle();
        newStyle.cloneStyleFrom(baseStyle);
        
        XSSFColor color = new XSSFColor(rgb, null);
        newStyle.setFillForegroundColor(color);
        newStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        return newStyle;
    }

    public static CellStyle applyFontColorToStyle(Workbook workbook, CellStyle baseStyle, short color, boolean bold,short fontSize) {
        CellStyle newStyle = workbook.createCellStyle();
        newStyle.cloneStyleFrom(baseStyle);

        Font font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setColor(color);  // Mengatur warna font
        if (bold) {
            font.setBold(true);  // Mengatur font menjadi bold
        }
        newStyle.setFont(font);

        return newStyle;
    }

    

    

}
