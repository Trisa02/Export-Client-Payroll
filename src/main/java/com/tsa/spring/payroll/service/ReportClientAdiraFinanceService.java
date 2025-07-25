package com.tsa.spring.payroll.service;


import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.Utils.DateUtil;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperAdiraFinance;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientAdiraFinance;
import com.tsa.spring.payroll.repository.ReportClientAdiraFinanceRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientAdiraFinanceService {

    @Autowired
    private ReportClientAdiraFinanceRepo reportClientAdiraFinanceRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public void exportAdiraFinance(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientAdiraFinance> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientAdiraFinance> dataclientAdiraFinance = reportClientAdiraFinanceRepo.findAll(spec);
        

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientAdiraFinance.xlsx");


        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateAdiraFinance.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        //Sheet sheet = workbook.getSheetAt(0);
        Sheet invoiceSheet = workbook.getSheet("INVOICE");
        Sheet adiraSheet = workbook.getSheet("Adira Finance");

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        ///Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String tanggalToDisplay = DateUtil.dataTanggal(searchBulan, searchTahun);
        String periode = DateUtil.dataPeriode(searchBulan, searchTahun,searchDivisi);
        
        Row rowC2 = adiraSheet.getRow(1);
        if(rowC2 == null){
            rowC2= adiraSheet.createRow(1);
        }

        Cell cellC2 = rowC2.getCell(2);
        if(cellC2 == null){
            cellC2 = rowC2.createCell(2);
        }

        if (tanggalToDisplay != null) {
            try {
                if (tanggalToDisplay.matches("\\d{4}-\\d{2}-\\d{2}")) {  
                    java.util.Date utilDate = java.sql.Date.valueOf(tanggalToDisplay);  
                    cellC2.setCellValue(utilDate);  
                } else {  // Jika bukan format tanggal, tampilkan sebagai teks
                    cellC2.setCellValue(tanggalToDisplay);  
                }
                cellC2.setCellStyle(style.get(ExcelStyleHelper.STYLE_TANGGAL));
            } catch (IllegalArgumentException e) {
                cellC2.setCellValue("Invalid Date");
            }
        } else {
            cellC2.setCellValue("");
        }
        

        Row rowK4 = adiraSheet.getRow(3); 
        if (rowK4 == null) {
            rowK4 = adiraSheet.createRow(3);
        }

        Cell cellK4 = rowK4.getCell(10);  
        if (cellK4 == null) {
            cellK4 = rowK4.createCell(10);
        }

        String dasarGapok = "DASAR GAPOK";
        if (searchTahun != null && !searchTahun.isEmpty()) {
            try {
                int tahunSebelumnya = Integer.parseInt(searchTahun) - 1;
                dasarGapok += " " + tahunSebelumnya;  
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        cellK4.setCellValue(dasarGapok);  
        cellK4.setCellStyle(style.get(ExcelStyleHelper.STYLE_HEADER));

        
        //Untuk data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
           14,15,17,19,22,23,24,25,26
        ));

        Set<Integer> nullColumns = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,8,9
        ));

        Set<Integer> alignCenter = Set.of(0,1,2,5,6,7,8);
        Set<Integer> alignRight = Set.of(12,13);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 14; i <= 26; i++) moneyColumns.add(i);
        moneyColumns.add(10);
        moneyColumns.add(11);
       
        int startRowIndex = 5;

        for(int i = 0; i < dataclientAdiraFinance.size(); i ++){

            ReportClientAdiraFinance data = dataclientAdiraFinance.get(i);
            Row row = adiraSheet.getRow(startRowIndex + i);
            if(row == null){
                row = adiraSheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(0, String.valueOf(i + 1));
            cellData.put(1,data.getNik());
            cellData.put(2,data.getNikAdira());
            cellData.put(3,data.getNama());
            cellData.put(4,data.getPosition());
            cellData.put(5,data.getJoinDate());
            cellData.put(6,data.getEndContract());
            cellData.put(7,data.getPeriodeInvoice());
            cellData.put(8,data.getResignDate());
            cellData.put(9,data.getAlasanResign());
            cellData.put(10,data.getBasicSalary());
            cellData.put(11,data.getTunjanganJabatan());
            cellData.put(12,data.getTotalWorks());
            cellData.put(13,data.getAbsen());
            cellData.put(16,data.getJhtKaryawan());
            cellData.put(18,data.getJipKaryawan());
            cellData.put(20,data.getBpjsKaryawan());
            cellData.put(21,data.getBpjsPerusahaan());

            for(int colIndex = 0; colIndex <= 26; colIndex++){

                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (formulaColumns.contains(colIndex)) {
                    String formula = ExcelFormulaHelperAdiraFinance.generateFormula(colIndex, startRowIndex + i + 1);
                    cell.setCellFormula(formula);
                } else {
                    String value = cellData.getOrDefault(colIndex, "0");

                    if (value == null || value.trim().isEmpty()) {
                        if(nullColumns.contains(colIndex)){
                            cell.setBlank();
                        }
                        else{
                            cell.setCellValue(0);
                        }
                        
                    } else {
                        try {
                            double numericValue = Double.parseDouble(value);
                            if (numericValue == (int) numericValue) {
                                cell.setCellValue((int) numericValue);
                            } else {
                                cell.setCellValue(numericValue);
                            }
                        } catch (NumberFormatException e) {
                            cell.setCellValue(value);
                        }
                    }
                }

                if (moneyColumns.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));
                } else if (alignRight.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KANAN));
                }else if (alignCenter.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_TENGAH));
                }
                else {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                }
            }
        

        }

        ///Sum Total
        int lastRow = startRowIndex + dataclientAdiraFinance.size();
        Row sumRow = adiraSheet.createRow(lastRow);
        int sumRowIndex = lastRow  + 1;
        
        Set<Integer> formulaSumColumns = new HashSet<>(Arrays.asList(
            10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26
        ));

        for(int colIndex = 0; colIndex <= 26; colIndex++){

            Row headerRow = adiraSheet.getRow(2);
            if (headerRow == null) {
                headerRow = adiraSheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);
            
            if(colIndex == 0){
                sumCell.setCellValue("Total");
                sumCell.setCellStyle(style.get(ExcelStyleHelper.STYLE_MERGE_CENTER));  // Pakai style merge kiri

                adiraSheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 5));
            }
            if(formulaSumColumns.contains(colIndex)){

                String formula = ExcelFormulaHelperAdiraFinance.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
                sumCell.setCellFormula(formula);

            }
                
            CellStyle sumStyle;
            if(formulaSumColumns.contains(colIndex)){
                sumStyle = style.get(ExcelStyleHelper.STYLE_UANG);
            }
            else{
                sumStyle = style.get(ExcelStyleHelper.STYLE_KIRI);
            }

            sumStyle = ExcelStyleHelper.applyBoldToStyle(workbook, sumStyle);
            byte[] yellowRgb = new byte[] {(byte) 146, (byte) 208, (byte) 80};  
            sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
            
            sumCell.setCellStyle(sumStyle);


           
        }

        //Untuk Invoice
        Row rowInvoiceA8 = invoiceSheet.getRow(7);
        if(rowInvoiceA8 == null) rowInvoiceA8 = invoiceSheet.createRow(7);
        Cell cellInvoiceA8 = rowInvoiceA8.getCell(0);
        cellInvoiceA8.setCellValue("Periode "+ periode);

        Row rowInvoiceA13 = invoiceSheet.getRow(13);
        if(rowInvoiceA13 == null) rowInvoiceA13 = invoiceSheet.createRow(13);

        Cell cellInvoiceA13 = rowInvoiceA13.createCell(0);
        cellInvoiceA13.setCellValue(DateUtil.getTanggalNow());
        cellInvoiceA13.setCellStyle(style.get(ExcelStyleHelper.STYLE_BOLD));

        Row rowInvoice11 = invoiceSheet.getRow(10);
        if (rowInvoice11 == null) {
            rowInvoice11 = invoiceSheet.createRow(10);
        }

        Cell cellInvoiceC11 = rowInvoice11.createCell(2);
        cellInvoiceC11.setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("C11", startRowIndex, sumRowIndex));
        cellInvoiceC11.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));

        Cell cellInvoiceD11 = rowInvoice11.createCell(3);
        cellInvoiceD11.setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("D11", startRowIndex, sumRowIndex));
        cellInvoiceD11.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));

        Cell cellInvoiceE11 = rowInvoice11.createCell(4);
        cellInvoiceE11.setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("E11", startRowIndex, sumRowIndex));
        cellInvoiceE11.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));

        Row rowInvoice12 = invoiceSheet.getRow(11);
        if (rowInvoice12 == null) {
            rowInvoice12 = invoiceSheet.createRow(11);
        }

        Cell cellInvoiceC12   = rowInvoice12.createCell(2);
        cellInvoiceC12 .setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("C12", startRowIndex, sumRowIndex));
        CellStyle sumStyle = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RP);
        byte[] yellowRgb = new byte[] {(byte) 184, (byte) 196, (byte) 228};
        sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
        cellInvoiceC12.setCellStyle(sumStyle);


        Cell cellInvoiceD12   =rowInvoice12.createCell(3);
        cellInvoiceD12.setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("D12", startRowIndex, sumRowIndex));
        CellStyle sumStyleD12 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RP);
        sumStyleD12 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
        cellInvoiceD12.setCellStyle(sumStyleD12);


        Cell cellInvoiceE12   = rowInvoice12.createCell(4);
        cellInvoiceE12.setCellFormula(ExcelFormulaHelperAdiraFinance.formulaInvoice("E12", startRowIndex, sumRowIndex));
        CellStyle sumStyleE12 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RP);
        sumStyleE12 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
        cellInvoiceE12.setCellStyle(sumStyleE12);






        //workbook.setForceFormulaRecalculation(true);
        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();




    }

    
    
}
