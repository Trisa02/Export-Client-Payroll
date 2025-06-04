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
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperAdiraFinance;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperHCI;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientHCI;
import com.tsa.spring.payroll.repository.MasterDivisiRepo;
import com.tsa.spring.payroll.repository.ReportClientHCIRepo;
import com.tsa.spring.payroll.specification.ReportClientSpecification;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReportClientHCIService {

    @Autowired
    private ReportClientHCIRepo reportClientHCIRepo;

    @Autowired
    private ReportClientSpecification reportClientSpecification;

    @Autowired
    private MasterDivisiRepo masterDivisiRepo;

    public String getFormattedWorkinPeriode(String bulan, String tahun,String divisi) {
        List<Object[]> list = reportClientHCIRepo.findWorkingPeriode(bulan, tahun);
        if (list == null || list.isEmpty()) {
            return "-";
        }
        Object[] workingPeriode = list.get(0);
        Optional<String>exportTypeDivisi = masterDivisiRepo.findExportTypeByNamaIgnoreCase(divisi);
        String exportType = exportTypeDivisi.orElse(divisi);
        return DateUtil.formatRangeFromObjectArray(workingPeriode,exportType);
    }

    public void exportReportClientHCI(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientHCI> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientHCI> dataClientHCI = reportClientHCIRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientHomeCreditIndonesia.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateHCI.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);
        //Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String searchEmployeeType = searchData.getSearchEmployeeType();
        String WorkingPeriode = getFormattedWorkinPeriode(searchBulan,searchTahun,searchDivisi);
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
        cellB4.setCellValue("Periode "+periode+ " (" +WorkingPeriode+")");

        Row rowO5 = sheet.getRow(4);
        if(rowO5 == null){
            rowO5 = sheet.createRow(4);
        }

        Cell cellO5 = rowO5.getCell(15);
        if(cellO5 == null){
            cellO5  = rowO5.createCell(15);
        }

        String labelPeriode;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
            int bulanInt = Integer.parseInt(searchBulan);
            String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
            labelPeriode = bulanNamaStr + " " + searchTahun;
        } else {
            labelPeriode = "Allowance";
        }

        cellO5.setCellValue(labelPeriode);



        ///Untuk Data
        Set<Integer> formulaColumns = new HashSet<>(Arrays.asList(
            17,32,34,35,36,38,39
        ));

        Set<Integer> nullColums = new HashSet<>(Arrays.asList(
            0,1,2,3,4,5,6,7,8,9,10,11,40,41
        ));
        Set<Integer> alignCenter = Set.of(1,2);
        Set<Integer> alignRight = Set.of(12,13,16);
        Set<Integer> moneyColumns = new HashSet<>();

        for(int i = 17; i <= 39; i++) moneyColumns.add(i);
        moneyColumns.add(14);
        moneyColumns.add(15);
        int startRowIndex = 7;

        int lastRow = startRowIndex + dataClientHCI.size();
        Row sumRow = sheet.createRow(lastRow);
        Set<Integer> formulaSumColumn = new HashSet<>();
        for(int i = 13; i <= 39; i++)formulaSumColumn.add(i);

        for(int i=0; i< dataClientHCI.size(); i++){

            ReportClientHCI data = dataClientHCI.get(i);
            Row row = sheet.getRow(startRowIndex + i);
            if(row == null){
                row = sheet.createRow(startRowIndex + i);
            }

            Map<Integer, String> cellData = new HashMap<>();
            cellData.put(1, String.valueOf(i + 1));
            cellData.put(2, data.getNikKtp());
            cellData.put(3, data.getNik());
            cellData.put(4, data.getNama());
            cellData.put(5, data.getUnitname());
            cellData.put(6, data.getNPWP());
            cellData.put(7, data.getLabor());
            cellData.put(8, data.getPosition());
            cellData.put(9, data.getBranch());
            cellData.put(10, data.getJoinDate());
            cellData.put(11, data.getNoRekening());
            cellData.put(12, data.getWD());
            cellData.put(13, data.getWDActive());
            cellData.put(14, data.getLastSalary());
            cellData.put(15, data.getAllowance());
            cellData.put(16, data.getAbsen());
            cellData.put(18, data.getTunjanganJabatan());
            cellData.put(19, data.getTunjanganTransportasi());
            cellData.put(20, data.getTunjanganKomunikasi());
            cellData.put(21, data.getTunjanganKehadiran());
            cellData.put(22, data.getTunjanganAkomodasi());
            cellData.put(23, data.getTunjanganUangMakan());
            cellData.put(24, data.getTunjanganKerja());
            cellData.put(25, data.getTunjanganKerajinanl());
            cellData.put(26, data.getAdjusment());
            cellData.put(27, data.getPublicHoliday());
            cellData.put(28, data.getTunjanganTraining());
            cellData.put(29, data.getLembur());
            cellData.put(30, data.getInsentif());
            cellData.put(31, data.getRecognitionFee());
            cellData.put(33, data.getAsuransi());
            cellData.put(37, data.getPotongan());
            cellData.put(40, data.getStatus());
            cellData.put(41, data.getResignDate());

            for (int colIndex = 1; colIndex <= 41; colIndex++) {

                Cell cell = row.getCell(colIndex);
                if (cell == null) {
                    cell = row.createCell(colIndex);
                }

                if (formulaColumns.contains(colIndex)) {
                    String formula = ExcelFormulaHelperHCI.generateFormula(colIndex, startRowIndex + i + 1);
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
                } 
                else if (alignRight.contains(colIndex)) {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KANAN));
                }
                else if(alignCenter.contains(colIndex)){
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_TENGAH));
                }
                else {
                    cell.setCellStyle(style.get(ExcelStyleHelper.STYLE_KIRI));
                }
            }
            

        }

        ///Untuk SumTotal

        for(int colIndex = 1; colIndex <= 41; colIndex++){
    
            Row headerRow = sheet.getRow(2);
            if (headerRow == null) {
                headerRow = sheet.createRow(2);
            }

            Cell headerCell = headerRow.getCell(colIndex);
            if (headerCell == null) {
                headerCell = headerRow.createCell(colIndex);
            }
            Cell sumCell = sumRow.createCell(colIndex);

            if(colIndex == 4){

                sumCell.setCellValue("Total");
            
            }
            if(formulaSumColumn.contains(colIndex)){

                String formula = ExcelFormulaHelperAdiraFinance.generateTotalFormula(colIndex, startRowIndex + 1, lastRow);
                sumCell.setCellFormula(formula);

            }
            CellStyle sumStyle;
            if(formulaSumColumn.contains(colIndex)){
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
