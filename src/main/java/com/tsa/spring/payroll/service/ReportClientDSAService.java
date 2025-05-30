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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.Utils.DateUtil;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperDSA;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientDSA;
import com.tsa.spring.payroll.repository.ReportClientDSARepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientDSAService {

    @Autowired
    private ReportClientDSARepo reportClientDSARepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public void exportClientDSA(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientDSA> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientDSA> dataClientDSA = reportClientDSARepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClient.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateDSA.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);
        
        ///Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String tanggalToDisplay = DateUtil.dataTanggal(searchBulan, searchTahun);

        Row rowB1 = sheet.getRow(0);
        if(rowB1 == null){
            rowB1 = sheet.createRow(0);
        }

        Cell cellB1 = rowB1.getCell(1);
        if(cellB1 == null){
            cellB1 = rowB1.createCell(1);
        }

        if (tanggalToDisplay != null){
            try {
                if (tanggalToDisplay.matches("\\d{4}-\\d{2}-\\d{2}")) {  
                    java.util.Date utilDate = java.sql.Date.valueOf(tanggalToDisplay);  
                    cellB1.setCellValue(utilDate);  
                } else {  // Jika bukan format tanggal, tampilkan sebagai teks
                    cellB1.setCellValue(tanggalToDisplay);  
                }
                cellB1.setCellStyle(style.get(ExcelStyleHelper.STYLE_TANGGAL_BOLD));
            } catch (IllegalArgumentException e) {
                cellB1.setCellValue("Invalid Date");
            }
        } else {
            cellB1.setCellValue("");
        }

        int daysInMonth = DateUtil.getDaysInMonth(searchBulan, searchTahun);

        Row rowL1 = sheet.getRow(0);
        if (rowL1 == null) {
            rowL1 = sheet.createRow(0);
        }
        Cell cellL1 = rowL1.getCell(11);
        if (cellL1 == null) {
            cellL1 = rowL1.createCell(11);
        }
        cellL1.setCellValue(daysInMonth);
        //cellL1.setCellStyle(style.get(ExcelStyleHelper.STYLE_TENGAH));

        ///Untuk Data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
           14,16,17,18,19,20,21,22,23
        ));

        Set<Integer> nullColumns = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,8,9,10,24,25,26
        ));

        Set<Integer> alignCenter = Set.of(0,11);
        Set<Integer> alignRight = Set.of(7,8);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 12; i <= 23; i++) moneyColumns.add(i);
        
        int startRowIndex = 2;

        int lastRow = startRowIndex + dataClientDSA.size();
        Row sumRow = sheet.createRow(lastRow);
        Set<Integer> formulaSumColumns = new HashSet<>(Arrays.asList(
            12,13,14,15,16,17,18,19,20,21,22,23
        ));

        for(int i = 0; i < dataClientDSA.size(); i++){

            ReportClientDSA data = dataClientDSA.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(0, String.valueOf(i + 1));
            cellData.put(1,data.getSalesCode());
            cellData.put(2,data.getNama());
            cellData.put(3,data.getPosition());
            cellData.put(4,data.getTitle());
            cellData.put(5,data.getNamaTl());
            cellData.put(6,data.getNamaManager());
            cellData.put(7,data.getJoinDate());
            cellData.put(8,data.getResignDate());
            cellData.put(9,data.getBranch());
            cellData.put(10,data.getLevel());
            cellData.put(11,data.getAbsen());
            cellData.put(12,data.getBasicSalary());
            cellData.put(13,data.getRapelan());
            cellData.put(15,data.getUangKesehatan());
            cellData.put(24,data.getWajibRek());
            cellData.put(25,data.getNorek());
            cellData.put(26,data.getRemarks());

            for(int colIndex = 0; colIndex <= 26; colIndex++){
                Cell cell = row.getCell(colIndex);
                if(cell == null){
                    cell = row.createCell(colIndex);
                }

                if(formulaColumns.contains(colIndex)){
                    String formula = ExcelFormulaHelperDSA.generateFormula(colIndex,startRowIndex + i + 1);
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
        }

        ///Untuk SumTotal

        for(int colIndex = 11; colIndex <= 23; colIndex++){
            Row headerRow = sheet.getRow(2);
            if (headerRow == null) {
                headerRow = sheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);

            if(colIndex == 11){
                sumCell.setCellValue("Grand Total");
            }
            if(formulaSumColumns.contains(colIndex)){
                String formula = ExcelFormulaHelperDSA.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
                sumCell.setCellFormula(formula);
            }

            CellStyle sumStyle;
             if(formulaSumColumns.contains(colIndex)){
                sumStyle = style.get(ExcelStyleHelper.STYLE_UANG);
            }
            else{
                sumStyle = style.get(ExcelStyleHelper.STYLE_TENGAH);
            }

            sumStyle = ExcelStyleHelper.applyBoldToStyle(workbook, sumStyle);
            byte[] yellowRgb = new byte[] {(byte) 0, (byte) 32, (byte) 96};  
            sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
            sumStyle = ExcelStyleHelper.applyFontColorToStyle(workbook, sumStyle, IndexedColors.WHITE.getIndex(),true,(short) 15);
            sumCell.setCellStyle(sumStyle);

            

            
        }

        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();

        // String downloadsFolder = System.getProperty("user.home") + File.separator + "Downloads";
        // String fileName = "ReportClient.xlsx";
        // String filePath = downloadsFolder + File.separator + fileName;
        // int counter = 1;


        // while (new File(filePath).exists()) {
        //     String newFileName = "ReportClient" + counter + ".xlsx";
        //     filePath = downloadsFolder + File.separator + newFileName;
        //     counter++;
        // }

        // try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
        //     workbook.write(fileOut);
        //     response.getWriter().println("File saved to: ReportClient.xlsx");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
        //     workbook.close();
        // }
    }

}
