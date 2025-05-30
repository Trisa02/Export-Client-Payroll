package com.tsa.spring.payroll.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ReportExcelController {
    @GetMapping("/export-excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=gaji.xlsx");

        // Load template dari resource
        ClassPathResource templateResource = new ClassPathResource("templates/excel/TemplateBerkahBenihBerseri.xlsx");
        InputStream inputStream = templateResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

    // ========== Buat style border ==========
    CellStyle borderStyle = workbook.createCellStyle();
    borderStyle.setBorderTop(BorderStyle.THIN);
    borderStyle.setBorderBottom(BorderStyle.THIN);
    borderStyle.setBorderLeft(BorderStyle.THIN);
    borderStyle.setBorderRight(BorderStyle.THIN);

    // ========== Data Baris ==========
    int jumlahBarisData = 10;
    int startRowIndex = 1; // Mulai dari row ke-2 karena row ke-1 dipakai header

    for (int i = 0; i < jumlahBarisData; i++) {
        Row row = sheet.createRow(startRowIndex + i);

        // Data tiap kolom (index 0 = gaji pokok, 1 = tunjangan, 2 = total gaji (formula))
        double gajiPokok = 10000 + ((i + 1) * 1000);
        double tunjangan = 5000 + ((i + 1) * 500);

        Object[] data = { gajiPokok, tunjangan, null }; // null nanti diganti formula

        for (int col = 0; col < data.length; col++) {
            Cell cell = row.createCell(col);

            if (col == 2) { // kolom ke-2 isi formula
                cell.setCellFormula(String.format("A%d+B%d", startRowIndex + i + 1, startRowIndex + i + 1));
            } else {
                if (data[col] instanceof Double) {
                    cell.setCellValue((Double) data[col]);
                } else if (data[col] instanceof String) {
                    cell.setCellValue((String) data[col]);
                }
            }
            cell.setCellStyle(borderStyle);
        }
    }

    // ========== Total Keseluruhan ==========
    int totalRowIndex = startRowIndex + jumlahBarisData;
    Row totalRow = sheet.createRow(totalRowIndex);

    Cell totalLabelCell = totalRow.createCell(1); // kolom ke-2, buat tulisan "Total Keseluruhan"
    totalLabelCell.setCellValue("Total Keseluruhan");
    totalLabelCell.setCellStyle(borderStyle);

    Cell totalCell = totalRow.createCell(2); // kolom ke-3, total sum
    totalCell.setCellFormula(String.format("SUM(C2:C%d)", totalRowIndex));
    totalCell.setCellStyle(borderStyle);

    // ========== Done ==========
    workbook.write(response.getOutputStream());
    workbook.close();
    inputStream.close();
    }
}
