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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.Utils.ExcelFormulaHelperTiketCOM;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientTiketCOM;
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
    private ReportClientSpecification reportClientSpecification;

    public void exportClientTiketCom(HttpServletResponse response, SearchData searchDat)throws Exception{

        Specification<ReportClientTiketCOM> spec = reportClientSpecification.searchReportClient(searchDat);
        List<ReportClientTiketCOM> dataClientTiketCOM = reportClientTiketComRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClient.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateTiket.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

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

        int startRowIndex = 10;

        for(int i = 0; i < dataClientTiketCOM.size(); i++){

            ReportClientTiketCOM data=dataClientTiketCOM.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
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
