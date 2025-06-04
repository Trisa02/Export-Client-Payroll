package com.tsa.spring.payroll.service;


import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperBukopin;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientBukopin;
import com.tsa.spring.payroll.repository.MasterDivisiRepo;
import com.tsa.spring.payroll.repository.ReportClientBukopinRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientBukopinService {

    @Autowired
    private  ReportClientBukopinRepo reportClientBukopinRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    @Autowired
    private MasterDivisiRepo masterDivisiRepo;

    public String getFormattedWorkinPeriode(String bulan, String tahun,String divisi) {
        List<Object[]> list = reportClientBukopinRepo.findWorkingPeriode(bulan, tahun);
        if (list == null || list.isEmpty()) {
            return "-";
        }
        Object[] workingPeriode = list.get(0);
        Optional<String>exportTypeDivisi = masterDivisiRepo.findExportTypeByNamaIgnoreCase(divisi);
        String exportType = exportTypeDivisi.orElse(divisi);
        return DateUtil.formatRangeFromObjectArray(workingPeriode,exportType);
    }

    public void exportClientBukopin(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientBukopin> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientBukopin> dataReportClients = reportClientBukopinRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientBankKBBukopin.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TamplateKBBukopin.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Data Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String searchEmployeeType = searchData.getSearchEmployeeType();
        String WorkingPeriode = getFormattedWorkinPeriode(searchBulan,searchTahun,searchDivisi);
        //String periode = DateUtil.dataPeriode(searchBulan, searchTahun,searchDivisi);

        Row rowB3 = sheet.getRow(2);
        if(rowB3 == null){
            rowB3 = sheet.createRow(2);
        }

        Cell cellB3 = rowB3.getCell(0);
        if(cellB3 == null){
            cellB3 = rowB3.createCell(0);
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

        Cell cellB4 = rowB4.getCell(0);
        if(cellB4 == null){
            cellB4 = rowB4.createCell(0);
        }
        cellB4.setCellValue("Periode "+ WorkingPeriode);



        //Untuk Data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
            0,13,21,22,23,24,25,26,34,35,36,37,38,39
        ));

        Set<Integer> nullColumns = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,8,9,40,41,42,43,44,45,46
        ));

        Set<Integer> alignCenter = Set.of(0,9,12,42,45,46);
        Set<Integer> alignRight = Set.of(10,11,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40);
        Set<Integer> moneyColumns = new HashSet<>();
        for(int i = 10; i <= 10; i++) moneyColumns.add(i);
        for(int i = 13; i <= 39 ; i++) moneyColumns.add(i);

        int startRowIndex = 6;

        //Untuk Data Sum
        int lastRow = startRowIndex + dataReportClients.size();
        Row sumRow = sheet.createRow(lastRow);
        Cell sumLabelCell = sumRow.createCell(2);
        sumLabelCell.setCellValue("Total");
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 12; i <= 39; i++) formulaSumColumns.add(i);

        //Perulangan Data
        for(int i = 0; i < dataReportClients.size(); i++){
            ReportClientBukopin data = dataReportClients.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(1, String.valueOf(data.getNik()));
            cellData.put(2, data.getNama());
            cellData.put(3,data.getCabang());
            cellData.put(4,data.getKantor());
            cellData.put(5,data.getDepartment());
            cellData.put(6,data.getJabatan());
            cellData.put(7,data.getFungsi());
            cellData.put(8,data.getVendor());
            cellData.put(9,data.getTanggalJatuhTempo());
            cellData.put(10,data.getGaji());
            cellData.put(11,data.getWd());
            cellData.put(12,data.getWdAktif());
            cellData.put(14,data.getTunjanganDasar());
            cellData.put(15,data.getUMT());
            cellData.put(16,data.getLembur());
            cellData.put(17,data.getInsentif());
            cellData.put(18,data.getBensin());
            cellData.put(19,data.getTunjanganKesehatan());
            cellData.put(20,data.getTunjanganLain());
            cellData.put(27,data.getCadanganTHR());
            cellData.put(28,data.getCadanganBPJS());
            cellData.put(29,data.getCadanganBonus());
            cellData.put(30,data.getSeragam());
            cellData.put(31,data.getCadanganBandik());
            cellData.put(32,data.getCadanganLain());
            cellData.put(33,data.getKompensasi());
            cellData.put(40,data.getPeriodePayroll());
            cellData.put(41,data.getPIC());
            cellData.put(42,data.getJoinDate());
            cellData.put(43,data.getWorkLocation());
            cellData.put(44,data.getDataOJK());
            cellData.put(45,data.getJenisKelamin());
            cellData.put(46,data.getPendidikanTerakhir());
         

            for(int colIndex = 0; colIndex <= 46; colIndex++){
                Cell cell = row.getCell(colIndex);
                if(cell == null){
                    cell = row.createCell(colIndex);
                }

                if(formulaColumns.contains(colIndex)){
                    String formula = ExcelFormulaHelperBukopin.generateFormula(colIndex,startRowIndex + i + 1);
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

        for(int colIndex = 0; colIndex <=46; colIndex++){
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

                String formula = ExcelFormulaHelperBukopin.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
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
            byte[] yellowRgb = new byte[] {(byte) 146, (byte) 208, (byte) 80};  
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
