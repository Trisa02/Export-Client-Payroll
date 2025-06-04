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

import com.tsa.spring.payroll.Utils.DateUtil;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperBenihBerkah;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientBenihBerkah;
import com.tsa.spring.payroll.repository.ReportClientBenihBerkahRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientBenihBerkahService {

    @Autowired
    private ReportClientBenihBerkahRepo reportClientRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    public void exportReportClientBenihBerkah(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientBenihBerkah> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientBenihBerkah> dataReportClients = reportClientRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientBenihBerkahBerseri.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateBerkahBenihBerseri.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        Row row15 = sheet.getRow(0);
        if(row15 == null){
            row15 = sheet.createRow(0);
        }
        Cell cell15 = row15.getCell(15);
        if(cell15 == null){
            cell15 = row15.createCell(15);
        }
        String labelWorkDaysP;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
                int bulanInt = Integer.parseInt(searchBulan);
                int tahunInt = Integer.parseInt(searchTahun);

                bulanInt -= 1;
                if (bulanInt == 0) {
                    bulanInt = 12;
                    tahunInt -= 1;
                }
            
                String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
                labelWorkDaysP = bulanNamaStr + " " + tahunInt;
        } else {
            labelWorkDaysP = "Work Days";
        }
        cell15.setCellValue(labelWorkDaysP);

        Row row16 = sheet.getRow(0);
        if(row16 == null){
            row16 = sheet.createRow(0);
        }
        Cell cell16 = row16.getCell(16);
        if(cell16 == null){
            cell16 = row16.createCell(16);
        }
        String labelWorkDaysQ;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
            int bulanInt = Integer.parseInt(searchBulan);
            String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
            labelWorkDaysQ = bulanNamaStr + " " + searchTahun;
        } else {
            labelWorkDaysQ = "Work Days";
        }
        cell16.setCellValue(labelWorkDaysQ);

        //Untuk Data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
            18, 19,35, 37, 39, 43, 44, 45, 55, 56, 57, 60, 61, 62, 63, 66, 67, 68, 69, 70
        ));

        Set<Integer> nullColums = new HashSet<>(Arrays.asList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,65, 71, 72, 73, 74, 75, 76, 77
        ));

        Set<Integer> alignCenter = Set.of(0,15, 16, 17, 18, 19, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33);
        Set<Integer> alignRight = Set.of(12, 13, 77);
        Set<Integer>percenStyle = Set.of(19);
        Set<Integer> moneyColumns = new HashSet<>();
        for (int i = 34; i <= 64; i++) moneyColumns.add(i);
        for (int i = 66; i <= 70; i++) moneyColumns.add(i);

        int startRowIndex = 1;

        int lastRow = startRowIndex + dataReportClients .size();
        Row sumRow = sheet.createRow(lastRow);

        Set<Integer>formulaSumColumns = new HashSet<>();
        for(int i = 20; i <= 70; i++) formulaSumColumns.add(i);


        for(int i =0; i < dataReportClients.size(); i++){
            ReportClientBenihBerkah data = dataReportClients.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(0, String.valueOf(i + 1));
            cellData.put(1, data.getID());
            cellData.put(2,data.getNik());
            cellData.put(3, data.getCompany());
            cellData.put(4, data.getNama());
            cellData.put(5,data.getPosition());
            cellData.put(6,data.getDepartment());
            cellData.put(7, data.getBranch());
            cellData.put(8,data.getServiceLocation());
            cellData.put(9,data.getRegion());
            cellData.put(10,data.getFunction());
            cellData.put(11,data.getMmr());
            cellData.put(12,data.getJoinDate());
            cellData.put(13,data.getEndContract());
            cellData.put(14,data.getMaritalStatus());
            cellData.put(15,data.getWorkDays1());
            cellData.put(16,data.getWorkDays2());
            cellData.put(17,data.getWorkDaysAktif());
            cellData.put(20,data.getPresence());
            cellData.put(21,data.getAlpha());
            cellData.put(22,data.getSick());
            cellData.put(23,data.getAnnualLeave());
            cellData.put(24,data.getColumnY());
            cellData.put(25,data.getPresenceZ());
            cellData.put(26,data.getSickAA());
            cellData.put(27,data.getAnnualLeaveAB());
            cellData.put(28,data.getAlphaAC());
            cellData.put(29,data.getColumnAD());
            cellData.put(31,data.getPresenceAE());
            cellData.put(32,data.getSickAF());
            cellData.put(33,data.getAnnualLeaveAG());
            cellData.put(34,data.getBasicSalary());
            //cellData.put(35,data.getBasicSalary());
            cellData.put(36,data.getRapel());
            cellData.put(38,data.getUangMasaKontrak());
            cellData.put(40,data.getTunjanganOperasional());
            cellData.put(41,data.getTunjanganParkirBensin());
            cellData.put(42,data.getTunjanganSewaServiceMotor());
            cellData.put(46,data.getTunjanganAkomodasi());
            cellData.put(47,data.getTunjanganSewaLapotop());
            cellData.put(48,data.getTunjanganTransportasi1());
            cellData.put(49,data.getInsentifSalesOffcycleDibayarkan());
            cellData.put(50,data.getInsentifSusulan());
            cellData.put(51,data.getInsentif());
            cellData.put(52,data.getLemburDibayarkan());
            cellData.put(53,data.getLembur());
            cellData.put(54,data.getKompensasi());
            cellData.put(58,data.getPotonganBPJSTKJP());
            cellData.put(59,data.getPph21());
            cellData.put(64,data.getPotonganBPJSKesehatan());
            cellData.put(65,data.getIDCard());
            cellData.put(71,data.getAccount());
            cellData.put(72,data.getBank());
            cellData.put(73,data.getCabangBank());
            cellData.put(74,data.getNameBank());
            cellData.put(75,data.getNpwp());
            cellData.put(76,data.getStatus());
            cellData.put(77,data.getResignDate());

            for (int colIndex = 0; colIndex <= 77; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (formulaColumns.contains(colIndex)) {
                    String formula = ExcelFormulaHelperBenihBerkah.generateFormula(colIndex, startRowIndex + i + 1);
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

                if (percenStyle.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_PERSEN));
                } else if (alignCenter.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_TENGAH));
                } else if (moneyColumns.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG));
                } else if (alignRight.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KANAN));
                } else {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                }
            }

        }

        for(int colIndex = 0; colIndex <= 77; colIndex++){
            Row headerRow = sheet.getRow(2);
            if (headerRow == null) {
                headerRow = sheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);

            if(colIndex == 19){
                sumCell.setCellValue("Total");
            }
            if(formulaSumColumns.contains(colIndex)){

                String formula = ExcelFormulaHelperBenihBerkah.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
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
            if (colIndex >= 20 && colIndex <= 70) {
                byte[] yellowRgb = new byte[] {(byte) 255, (byte) 255, (byte) 0};  
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
