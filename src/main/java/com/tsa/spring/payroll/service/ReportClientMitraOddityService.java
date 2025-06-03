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
import com.tsa.spring.payroll.Utils.ExcelHelperMitraOddity;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientMitraOddity;
import com.tsa.spring.payroll.repository.ReportClientMitraOddityRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientMitraOddityService {

    @Autowired 
    private ReportClientMitraOddityRepo reportClientMitraOddityRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public void exportClientMitraOddity(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientMitraOddity> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientMitraOddity> dataClientMitraOddities = reportClientMitraOddityRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClient.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateMitraOdity.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String searchEmployeeType = searchData.getSearchEmployeeType();
        String periode = DateUtil.dataPeriode(searchBulan, searchTahun,searchDivisi);

        

        Row rowB3 = sheet.getRow(2);
        if(rowB3 == null){
            rowB3 = sheet.createRow(2);
        }

        Cell cellB3 = rowB3.getCell(1);
        if(cellB3 == null){
            cellB3 = rowB3.createCell(1);
        }

        if(searchEmployeeType != null && !searchEmployeeType.isEmpty()){
            cellB3.setCellValue(searchEmployeeType+" "+searchDivisi);
        }
        else{
            cellB3.setCellValue(searchDivisi);
        }


        Row rowB4 = sheet.getRow(3);
        if(rowB4 == null){
            rowB4 = sheet.createRow(3);
        }

        Cell cellB4 = rowB4.getCell(1);
        if(cellB4 == null){
            cellB4 = rowB4.createCell(1);
        }
        cellB4.setCellValue("Periode "+ periode);

        Row rowO5 = sheet.getRow(4);
        if(rowO5 == null){
            rowO5 = sheet.createRow(4);
        }

        Cell cellO5 = rowO5.getCell(14);
        if(cellO5 == null){
            cellO5  = rowO5.createCell(14);
        }

        cellO5.setCellValue(periode);

        Row rowQ5 = sheet.getRow(4);
        if(rowQ5 == null){
            rowQ5 = sheet.createRow(4);
        }

        Cell cellQ5 = rowQ5.getCell(16);
        if(cellQ5 == null){
            cellQ5 = rowQ5.createCell(16);
        }

        if (searchBulan != null && !searchBulan.isEmpty()) {
            int bulanInt = Integer.parseInt(searchBulan); 
            if (bulanInt >= 1 && bulanInt <= 12) {
                cellQ5.setCellValue("Allowance "+DateUtil.bulanNama[bulanInt - 1]); 
            } else {
                cellQ5.setCellValue("Allowance"); 
            }
        } else {
            cellQ5.setCellValue("Allowance"); 
        }




        //Untuk Data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
            16,21,26,28,29,30,31,32,35,36
        ));

        Set<Integer> nullColums = new HashSet<>(Arrays.asList(
            1,2,3,4,5,6,7,8,10,11,37,38
        ));
        Set<Integer> alignCenter = Set.of(1);
        Set<Integer> alignRight = Set.of(15);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 16; i <= 36; i++) moneyColumns.add(i);
        for(int i = 12; i <= 14; i++) moneyColumns.add(i);
        moneyColumns.add(9);

        int startRowIndex = 6;

        for(int i =0; i< dataClientMitraOddities.size(); i++){

            ReportClientMitraOddity data = dataClientMitraOddities.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer,String> cellData = new HashMap<>();
            cellData.put(1, String.valueOf(i + 1));
            cellData.put(2, data.getNik());
            cellData.put(3, data.getNama());
            cellData.put(4, data.getPosition());
            cellData.put(5, data.getNpwp());
            cellData.put(6, data.getBranch());
            cellData.put(7, data.getJoinDate());
            cellData.put(8, data.getNoRekening());
            cellData.put(9, data.getAllowance1());
            cellData.put(10, data.getWdSebelumnya());
            cellData.put(11, data.getWdActiveSebelumnya());
            cellData.put(12, data.getAllowance2());
            cellData.put(13, data.getAllowance3());
            cellData.put(14, data.getGajiperhari());
            cellData.put(15, data.getWdActive());
            cellData.put(16, data.getTunjanganTransportasi());
            cellData.put(17, data.getInsentif());
            cellData.put(18, data.getPremiTerdaftar());
            cellData.put(19, data.getPremiTerdaftar());
            cellData.put(20, data.getPotongan());
            cellData.put(22, data.getJkk());
            cellData.put(23, data.getJkm());
            cellData.put(24, data.getJht());
            cellData.put(25, data.getAsuransiKesehatan());
            cellData.put(27, data.getPotongan());
            cellData.put(33, data.getBpjs());
            cellData.put(34, data.getPotongan());
            cellData.put(37, data.getStatus());
            cellData.put(38, data.getResignDate());


            for (int colIndex = 1; colIndex <= 38; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (formulaColumns.contains(colIndex)) {
                    String formula = ExcelHelperMitraOddity.generateFormula(colIndex, startRowIndex + i + 1);
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

                CellStyle cellStyle = style.get(ExcelStyleHelper.STYLE_KIRI);

                if (moneyColumns.contains(colIndex)) {
                    cellStyle = style.get(ExcelStyleHelper.STYLE_UANG);
                }
                if(alignCenter.contains(colIndex)){
                    cellStyle = style.get(ExcelStyleHelper.STYLE_TENGAH);
                }
                if(alignRight.contains(colIndex)){
                    cellStyle = style.get(ExcelStyleHelper.STYLE_KANAN);
                }

                cell.setCellStyle(cellStyle);
                // else {
                //     cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                // }
            }
        }

         ///Sum Total
        int lastRow = startRowIndex + dataClientMitraOddities.size();
        Row sumRow = sheet.createRow(lastRow);
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 15; i <= 36; i++) formulaSumColumns.add(i);

        for(int colIndex = 1; colIndex <= 38; colIndex++){
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
                sumCell.setCellValue("Total");
            }
            if(formulaSumColumns.contains(colIndex)){
                String formula = ExcelHelperMitraOddity.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
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
            byte[] yellowRgb = new byte[] {(byte) 198, (byte) 224, (byte) 180};  
            sumStyle = ExcelStyleHelper.applyFontColorToStyle(workbook, sumStyle, IndexedColors.BLACK.getIndex(),true,(short) 9);
            sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
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
