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

import com.tsa.spring.payroll.Utils.ExcelFormulaHelperAdiraFinance;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperAfi;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientAFI;
import com.tsa.spring.payroll.repository.ReportClientAFIRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientAFIService {

    @Autowired 
    private ReportClientAFIRepo reportClientAFIRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public void exportReportClientAFI(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientAFI> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientAFI> dataClientAFI = reportClientAFIRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClient.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateAFI.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
            22,37,38,39,41,42,43,44,45,46,52,53
        ));

        Set<Integer> nullColums = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17,54,55,56
        ));
        Set<Integer> alignRight = Set.of(15,19,20,21,54,55,56);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 22; i <= 53; i++) moneyColumns.add(i);
        moneyColumns.add(15);
    
        int startRowIndex = 3;

        int lastRow = startRowIndex + dataClientAFI.size();
        Row sumRow = sheet.createRow(lastRow);
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 22; i <= 53; i++)formulaSumColumns.add(i);

        for(int i =0; i < dataClientAFI.size(); i++){

            ReportClientAFI data = dataClientAFI.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(0, String.valueOf(data.getNik()));
            cellData.put(1,data.getNama());
            cellData.put(2,data.getCompany());
            cellData.put(3,data.getCountry());
            cellData.put(4,data.getBranch());
            cellData.put(5,data.getBranch());
            cellData.put(6,data.getSubBussines());
            cellData.put(7,data.getMainDepartment());
            cellData.put(8,data.getSubDepartmen());
            cellData.put(9,data.getPosition());
            cellData.put(10,data.getCoManager());
            cellData.put(11,data.getJoinDate());
            cellData.put(12,data.getResignDate());
            cellData.put(13,data.getContractEnd());
            cellData.put(14,data.getAnnualLeave());
            cellData.put(15,data.getBasicSalary());
            cellData.put(16,data.getNetGross());
            cellData.put(17,data.getMaritalStatus());
            cellData.put(18,data.getNPWP());
            cellData.put(19,data.getEWD());
            cellData.put(20,data.getWD5());
            cellData.put(21,data.getWD6());
            cellData.put(23,data.getRegularTax());
            cellData.put(24,data.getIrregularTax());
            cellData.put(25,data.getTunjanganLain());
            cellData.put(26,data.getOvertime());
            cellData.put(27,data.getRapelan());
            cellData.put(28,data.getOJT());
            cellData.put(29,data.getTHR());
            cellData.put(30,data.getBonus());
            cellData.put(31,data.getCommision());
            cellData.put(32,data.getIsentif());
            cellData.put(33,data.getUangKompensasi());
            cellData.put(34,data.getBpjsKesehataAllowance());
            cellData.put(35,data.getBpjsAdjusment());
            cellData.put(36,data.getBpjsTenagaKerjaAllowance());
            cellData.put(40,data.getPotonganAbsen());
            cellData.put(47,data.getLessPayment());
            cellData.put(48,data.getPotonganLainnya());
            cellData.put(49,data.getRegulerIncome());
            cellData.put(50,data.getIrregularIncome());
            cellData.put(51,data.getPayableTax());
            cellData.put(54,data.getContractStart());
            cellData.put(55,data.getContractEnd());
            cellData.put(56,data.getDatePayment());


            for (int colIndex = 0; colIndex <= 56; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (formulaColumns.contains(colIndex)) {
                    String formula = ExcelFormulaHelperAfi.generateFormula(colIndex, startRowIndex + i + 1);
                    cell.setCellFormula(formula);
                } else {
                    String value = cellData.getOrDefault(colIndex, "0");

                    if (value == null || value.trim().isEmpty()) {
                        if(nullColums.contains(colIndex)){
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
                }else {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                }
            }


        }

         for(int colIndex = 0; colIndex <= 56; colIndex++){
            Row headerRow = sheet.getRow(2);
            if (headerRow == null) {
                headerRow = sheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);
            
            if(colIndex == 0){
                sumCell.setCellValue("Total");
                sumCell.setCellStyle(style.get(ExcelStyleHelper.STYLE_MERGE_CENTER));  // Pakai style merge kiri

                sheet.addMergedRegion(new CellRangeAddress(lastRow, lastRow, 0, 21));
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
                sumStyle = style.get(ExcelStyleHelper.STYLE_KANAN);
            }

            sumStyle = ExcelStyleHelper.applyBoldToStyle(workbook, sumStyle);
            if (colIndex >= 0 && colIndex <= 21) {
                byte[] yellowRgb = new byte[] {(byte) 255, (byte) 255, (byte) 0};  
                sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
            }
            if (colIndex >= 22 && colIndex <= 53) {
                byte[] yellowRgb = new byte[] {(byte) 146, (byte) 208, (byte) 80};  
                sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
            }
            sumCell.setCellStyle(sumStyle);

        }

        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();

    }

    

}


