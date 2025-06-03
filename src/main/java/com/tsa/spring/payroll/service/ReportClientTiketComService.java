package com.tsa.spring.payroll.service;


import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.tsa.spring.payroll.Utils.DateUtil.MonthFormatedtoIndo;
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

    public String getFormattedWorkinPeriode(String bulan, String tahun,String divisi) {
        List<Object[]> list = reportClientTiketComRepo.findWorkingPeriode(bulan, tahun);
        if (list == null || list.isEmpty()) {
            return "-";
        }
        Object[] workingPeriode = list.get(0);
        return MonthFormatedtoIndo.formatRangeFromObjectArray(workingPeriode,divisi);
    }
    
    
    public void exportClientTiketCom(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientTiketCOM> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientTiketCOM> dataClientTiketCOM = reportClientTiketComRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClient.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateTiket.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Data Header

        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String WorkingPeriode = getFormattedWorkinPeriode(searchBulan,searchTahun,searchDivisi);
        
        Row row3 = sheet.getRow(3);
        if(row3 == null){
            row3 = sheet.createRow(3);
        }
        Cell cellc = row3.getCell(2);
        if(cellc == null){
            cellc = row3.createCell(2);
        }
        cellc.setCellValue(WorkingPeriode);

        Row row4 = sheet.getRow(4);
        if(row4 == null){
            row4 = sheet.createRow(4);
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

        Row row9 = sheet.getRow(8);
        if(row9  == null){
            row9  = sheet.createRow(8);
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
        Row sumRow = sheet.createRow(sumRowIndex);
        // Cell countC7 = sumRow.createCell(2);
        // countC7.setCellValue("Total");
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 8; i <= 16; i++)formulaSumColumns.add(i);
        for(int i =19; i <= 38; i++)formulaSumColumns.add(i);

        for(int colIndex = 2; colIndex <= 40; colIndex++){
            Row headerRow = sheet.getRow(2);
            if (headerRow == null) {
                headerRow = sheet.createRow(2);
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

                Row rowar = sheet.getRow(rowIndex);
                if (rowar == null) {
                    rowar = sheet.createRow(rowIndex);
                }

                Cell cell = rowar.createCell(colAR); 
                cell.setCellFormula(formula);
                CellStyle sumStyle;
                sumStyle=style.get(ExcelStyleHelper.STYLE_UANG);
                cell.setCellStyle(sumStyle);

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
