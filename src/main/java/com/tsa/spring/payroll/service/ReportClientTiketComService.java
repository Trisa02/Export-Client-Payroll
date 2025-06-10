package com.tsa.spring.payroll.service;


import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.Utils.DateUtil;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperTiketCOM;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientTiketCOM;
import com.tsa.spring.payroll.repository.MasterDivisiRepo;
import com.tsa.spring.payroll.repository.ReportClientTiketComRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientTiketComService {

    @Autowired
    private ReportClientTiketComRepo reportClientTiketComRepo;

    @Autowired
    private MasterDivisiRepo masterDivisiRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public String getFormattedWorkinPeriode(String bulan, String tahun,String divisi) {
        List<Object[]> list = reportClientTiketComRepo.findWorkingPeriode(bulan, tahun);
        if (list == null || list.isEmpty()) {
            return "-";
        }
        Object[] workingPeriode = list.get(0);
        Optional<String>exportTypeDivisi = masterDivisiRepo.findExportTypeByNamaIgnoreCase(divisi);
        String exportType = exportTypeDivisi.orElse(divisi);
        return DateUtil.formatRangeFromObjectArray(workingPeriode,exportType);
    }
    
    
    public void exportClientTiketCom(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientTiketCOM> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientTiketCOM> dataClientTiketCOM = reportClientTiketComRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientTIKETCOM.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateTiket.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

        //Sheet sheet = workbook.getSheetAt(0);
        Sheet invoiceSheet = workbook.getSheet("INVOICE");
        Sheet tiketSheet = workbook.getSheet("CALCULATION");

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Data Header

        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String WorkingPeriode = getFormattedWorkinPeriode(searchBulan,searchTahun,searchDivisi);
        
        Row row3 = tiketSheet.getRow(3);
        if(row3 == null){
            row3 = tiketSheet.createRow(3);
        }
        Cell cellc = row3.getCell(2);
        if(cellc == null){
            cellc = row3.createCell(2);
        }
        cellc.setCellValue(WorkingPeriode);

        Row row4 = tiketSheet.getRow(4);
        if(row4 == null){
            row4 = tiketSheet.createRow(4);
        }
        Cell cell2 = row4.getCell(2);
        if(cell2 == null){
            cell2 = row4.createCell(2);
        }

        String labelPeriode;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
            int bulanInt = Integer.parseInt(searchBulan);
            String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
            labelPeriode = bulanNamaStr + " " + searchTahun;
        } else {
            labelPeriode = "";
        }

        cell2.setCellValue(labelPeriode);

        Row row9 = tiketSheet.getRow(8);
        if(row9  == null){
            row9  = tiketSheet.createRow(8);
        }
        Cell cell42 = row9 .getCell(42);
        if(cell42 == null){
            cell42 = row9.createCell(42);
        }
        String labelbulan;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
            int bulanInt = Integer.parseInt(searchBulan);
            String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
            labelbulan = bulanNamaStr + " " + searchTahun;
        } else {
            labelbulan= "";
        }
        cell42.setCellValue(labelbulan);

        int sumRowIndex = 6;
        int startRowIndex = 10;
        int lastRow = startRowIndex + dataClientTiketCOM.size();
        Row sumRow = tiketSheet.createRow(sumRowIndex);
        // Cell countC7 = sumRow.createCell(2);
        // countC7.setCellValue("Total");
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 8; i <= 16; i++)formulaSumColumns.add(i);
        for(int i =19; i <= 38; i++)formulaSumColumns.add(i);

        for(int colIndex = 2; colIndex <= 40; colIndex++){
            Row headerRow = tiketSheet.getRow(2);
            if (headerRow == null) {
                headerRow = tiketSheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);

            if(colIndex == 2){
                String formula = ExcelFormulaHelperTiketCOM.generateCountFormula(colIndex, startRowIndex + 1, lastRow);
                sumCell.setCellFormula(formula);
                
            }
            if(formulaSumColumns.contains(colIndex)){
                String formula = ExcelFormulaHelperTiketCOM.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
                sumCell.setCellFormula(formula);
            }


            if (colIndex == 2 || (colIndex >= 8 && colIndex <= 16) || (colIndex >= 19 && colIndex <= 38)) {
                CellStyle sumStyle;
                if (formulaSumColumns.contains(colIndex)) {
                    sumStyle = style.get(ExcelStyleHelper.STYLE_UANG);
                } else {
                    sumStyle = style.get(ExcelStyleHelper.STYLE_TENGAH);
                }
        
                CellStyle newStyle = workbook.createCellStyle();
                newStyle.cloneStyleFrom(sumStyle);

                newStyle.setBorderTop(BorderStyle.NONE);
                newStyle.setBorderBottom(BorderStyle.NONE);
                newStyle.setBorderLeft(BorderStyle.NONE);
                newStyle.setBorderRight(BorderStyle.NONE);

                newStyle = ExcelStyleHelper.applyBoldToStyle(workbook, newStyle);
                byte[] yellowRgb = new byte[] { (byte) 255, (byte) 252, (byte) 4 };
                newStyle = ExcelStyleHelper.applyColorToStyle(workbook, newStyle, yellowRgb);
                
                sumCell.setCellStyle(newStyle);
            }
        }

        Set<Integer> formulaColums = new HashSet<>(Arrays.asList(
            19,22,23,24,25,28,29,30,31,32,33,34,35,36,37,38
        ));

        Set<Integer> nullColumns = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,39,40
        ));

        Set<Integer> alignCenter = Set.of(0,20,21);
        Set<Integer> alignRight = Set.of(8,9,10,11,12,13,14,15,16,17,18,19,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,40);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 8; i<= 19; i++)moneyColumns.add(i);
        for(int i = 22; i<= 38; i++)moneyColumns.add(i);
        moneyColumns.add(40);

       

        for(int i = 0; i < dataClientTiketCOM.size(); i++){

            ReportClientTiketCOM data=dataClientTiketCOM.get(i);
            Row row = tiketSheet.getRow(startRowIndex + i);
            if(row == null){
                row = tiketSheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(0, String.valueOf(i + 1));
            cellData.put(1, data.getNik());
            cellData.put(2,data.getNama());
            cellData.put(3,data.getPosition());
            cellData.put(4,data.getWorkLocation());
            cellData.put(5,data.getStatusPTKP());
            cellData.put(6,data.getStart());
            cellData.put(7,data.getEnd());
            cellData.put(8,data.getBasicSalary());
            cellData.put(9,data.getRapelBasicSalary());
            cellData.put(10,data.getSklillAllowance());
            cellData.put(11,data.getPositionAllowance());
            cellData.put(12,data.getGradingAllowance());
            cellData.put(13,data.getMonthlyAllowance());
            cellData.put(14,data.getMonthlyAllowanceBase());
            cellData.put(15,data.getRapelAllowance());
            cellData.put(16,data.getAmmountOvertime());
            cellData.put(17,data.getTotalShift());
            cellData.put(18,data.getRate());
            //cellData.put(19,data.getAmmount());
            cellData.put(20,data.getTotalAddance());
            cellData.put(21,data.getAmmountMeals());
            cellData.put(26,data.getPph21());
            cellData.put(27,data.getDeduction());
            cellData.put(39,data.getKet());
            cellData.put(40,data.getDasarUpahBpjs());

            for(int colIndex = 0; colIndex <= 40; colIndex++){
                Cell cell = row.getCell(colIndex);
                if(cell == null){
                    cell = row.createCell(colIndex);
                }

                if(formulaColums.contains(colIndex)){
                    String formula = ExcelFormulaHelperTiketCOM.generateFormula(colIndex,startRowIndex + i + 1);
                    cell.setCellFormula(formula);
                }else {
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

                if (alignCenter.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_TENGAH));
                } else if (moneyColumns.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));
                } else if (alignRight.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KANAN));
                }else {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                }

            }

            int colAR = 43; 

            Map<Integer, String> cellMappings = Map.of(
                9, "AI7",
                10, "AJ7",
                11, "AK7",
                12, "AL7",
                13, "C7"
            );

            for (Map.Entry<Integer, String> entry : cellMappings.entrySet()) {
                int rowIndex = entry.getKey();
                String formula = entry.getValue();

                Row rowar = tiketSheet.getRow(rowIndex);
                if (rowar == null) {
                    rowar = tiketSheet.createRow(rowIndex);
                }

                Cell cell = rowar.createCell(colAR); 
                cell.setCellFormula(formula);
                CellStyle sumStyle;
                sumStyle=style.get(ExcelStyleHelper.STYLE_UANG);
                cell.setCellStyle(sumStyle);

            }

            int rowYz = 3; 

            Row rowYz4 = tiketSheet.getRow(rowYz);
            if (rowYz4 == null) {
                rowYz4 = tiketSheet.createRow(rowYz);
            }

            // Kolom Y = index ke-24
            Cell cellY4 = rowYz4.createCell(24); 
            cellY4.setCellFormula("Y3*1%");
            cellY4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));

            // Kolom Z = index ke-25
            Cell cellZ4 = rowYz4.createCell(25); 
            cellZ4.setCellFormula("Z3*1%");
            cellZ4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));
        }

        ///Untuk Invoice
        Row rowInvoiceI21 = invoiceSheet.getRow(20);
        if(rowInvoiceI21 == null) rowInvoiceI21 = invoiceSheet.createRow(20);
        Cell cellInvoiceI21 = rowInvoiceI21.createCell(8);
        cellInvoiceI21.setCellValue(DateUtil.getTanggalNowBy());
     

        Row rowInvoiceG25 = invoiceSheet.getRow(24);
        if(rowInvoiceG25 == null) rowInvoiceG25 = invoiceSheet.createRow(24);

        Cell cellInvoiceG25 = rowInvoiceG25.createCell(6);
        cellInvoiceG25.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("G25", startRowIndex, 14));
        cellInvoiceG25.setCellStyle(style.get(ExcelStyleHelper.STYLE_KANAN_INVOICE));

        Row rowInvoiceI25 = invoiceSheet.getRow(24);
        if(rowInvoiceI25 == null) rowInvoiceI25 = invoiceSheet.createRow(24);
        Cell cellIncoiveI25 = rowInvoiceI25.createCell(8);
        cellIncoiveI25.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I25", startRowIndex,10));
        cellIncoiveI25.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH));

        Row rowInvoiceI28 = invoiceSheet.getRow(27);
        if(rowInvoiceI28 == null) rowInvoiceI28 = invoiceSheet.createRow(27);
        Cell cellIncoiveI28 = rowInvoiceI28.createCell(8);
        cellIncoiveI28.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I28", startRowIndex,10));
        cellIncoiveI28.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH));

        Row rowInvoiceI34 = invoiceSheet.getRow(33);
        if(rowInvoiceI34 == null) rowInvoiceI34 = invoiceSheet.createRow(33);
        Cell cellInvoiceI34 = rowInvoiceI34.createCell(8);
        cellInvoiceI34.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I34", startRowIndex, lastRow));
        CellStyle sumSytleI34 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH_BOLD);
        byte[] yellowRgb = new byte[] {(byte) 200, (byte) 196, (byte) 196};
        sumSytleI34 = ExcelStyleHelper.applyColorToStyle(workbook, sumSytleI34, yellowRgb);
        cellInvoiceI34.setCellStyle(sumSytleI34);

        Row rowInvoiceI35 = invoiceSheet.getRow(34);
        if(rowInvoiceI35 == null) rowInvoiceI35 = invoiceSheet.createRow(34);
        Cell cellInvoiceI35 = rowInvoiceI35.createCell(8);
        cellInvoiceI35.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I35", startRowIndex, lastRow));
        CellStyle sumStyleI35 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH_BOLD);
        sumStyleI35 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyleI35, yellowRgb);
        cellInvoiceI35.setCellStyle(sumStyleI35);

        Row rowInvoiceI36 = invoiceSheet.getRow(35);
        if(rowInvoiceI36 == null) rowInvoiceI36 = invoiceSheet.createRow(35);
        Cell cellInvoiceI36 = rowInvoiceI36.createCell(8);
        cellInvoiceI36.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I36", startRowIndex, lastRow));
        CellStyle sumStyleI36 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH_BOLD);
        sumStyleI36 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyleI36, yellowRgb);
        cellInvoiceI36.setCellStyle(sumStyleI36);

        Row rowInvoiceI37 = invoiceSheet.getRow(36);
        if(rowInvoiceI37 == null) rowInvoiceI37 = invoiceSheet.createRow(36);
        Cell cellInvoiceI37 = rowInvoiceI37.createCell(8);
        cellInvoiceI37.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I37", startRowIndex, lastRow));
        CellStyle sumStyleI37 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH_BOLD);
        sumStyleI37 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyleI37, yellowRgb);
        cellInvoiceI37.setCellStyle(sumStyleI37);

        Row rowInvoiceI38 = invoiceSheet.getRow(37);
        if(rowInvoiceI38 == null) rowInvoiceI38 = invoiceSheet.createRow(37);
        Cell cellInvoiceI38 = rowInvoiceI38.createCell(8);
        cellInvoiceI38.setCellFormula(ExcelFormulaHelperTiketCOM.formulaInvoice("I36", startRowIndex, lastRow));
        CellStyle sumStyleI38 = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RUPIAH_BOLD);
        sumStyleI38 = ExcelStyleHelper.applyColorToStyle(workbook, sumStyleI38, yellowRgb);
        cellInvoiceI38.setCellStyle(sumStyleI38);

        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();
    }

    
}
