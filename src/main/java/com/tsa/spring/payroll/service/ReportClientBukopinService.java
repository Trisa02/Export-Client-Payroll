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
        // Sheet sheet = workbook.getSheetAt(0);
        Sheet invoiceSheet = workbook.getSheet("Invoice");
        Sheet bukopinSheet = workbook.getSheet("Bukopin");

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        ///Data Untuk Sheet Bukopin
        //Untuk Data Header Sheet Bukopin
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();
        String searchEmployeeType = searchData.getSearchEmployeeType();
        String WorkingPeriode = getFormattedWorkinPeriode(searchBulan,searchTahun,searchDivisi);
        //String periode = DateUtil.dataPeriode(searchBulan, searchTahun,searchDivisi);

        Row rowB3 = bukopinSheet.getRow(2);
        if(rowB3 == null){
            rowB3 = bukopinSheet.createRow(2);
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

        Row rowB4 = bukopinSheet.getRow(3);
        if(rowB4 == null){
            rowB4 = bukopinSheet.createRow(3);
        }

        Cell cellB4 = rowB4.getCell(0);
        if(cellB4 == null){
            cellB4 = rowB4.createCell(0);
        }
        cellB4.setCellValue("Periode "+ WorkingPeriode);

        //Untuk Sheet Bukpin Data Perulangan
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

        //Untuk Data Sum Sheet Bukopin
        int lastRow = startRowIndex + dataReportClients.size();
        Row sumRow = bukopinSheet.createRow(lastRow);
        int sumRowIndex = lastRow  + 1;

        Cell sumLabelCell = sumRow.createCell(2);
        sumLabelCell.setCellValue("Total");
        Set<Integer> formulaSumColumns = new HashSet<>();
        for(int i = 12; i <= 39; i++) formulaSumColumns.add(i);

        //Perulangan Data Sheet Bukopin
        for(int i = 0; i < dataReportClients.size(); i++){
            ReportClientBukopin data = dataReportClients.get(i);
            Row row = bukopinSheet.getRow(startRowIndex + i);
            if(row == null){
                row = bukopinSheet.createRow(startRowIndex + i);
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

        //Untuk Formula Header Sheet Bukopin
        for(int colIndex = 0; colIndex <=46; colIndex++){
            Row headerRow = bukopinSheet.getRow(2);
            if (headerRow == null) {
                headerRow = bukopinSheet.createRow(2);
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


        ///Sheet Invoice
        Row rowInvoiceA2 = invoiceSheet.getRow(1);
        if(rowInvoiceA2 == null){
            rowInvoiceA2 = invoiceSheet.createRow(1);
        }

        Cell cellInvoiceA2 = rowInvoiceA2.getCell(0);
        if(cellInvoiceA2 == null){
            cellInvoiceA2 = rowInvoiceA2.createCell(0);
        }
        cellInvoiceA2.setCellValue("Periode "+ WorkingPeriode);

        Row rowInvoiceB4 = invoiceSheet.getRow(3);
        if(rowInvoiceB4 == null) rowInvoiceB4 = invoiceSheet.createRow(3);

        Cell cellInvoiceB4 = rowInvoiceB4.createCell(1);
        String labelPeriode;
        if (searchBulan != null && !searchBulan.isEmpty() &&
            searchTahun != null && !searchTahun.isEmpty()) {
            
            int bulanInt = Integer.parseInt(searchBulan);
            String bulanNamaStr = DateUtil.bulanNama[bulanInt - 1]; 
            labelPeriode = bulanNamaStr + " " + searchTahun;
        } else {
            labelPeriode = "Allowance";
        }
        cellInvoiceB4.setCellValue("Biaya Jasa Bank Bukopin Periode "+labelPeriode);
        cellInvoiceB4.setCellStyle(style.get(ExcelStyleHelper.STYLE_BOLD));

        Row rowInvoiceC4 = invoiceSheet.getRow(3);
        if (rowInvoiceC4 == null) rowInvoiceC4 = invoiceSheet.createRow(3);

        Cell cellInvoiceC4 = rowInvoiceC4.createCell(2);
        cellInvoiceC4.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("C4", 34, startRowIndex, sumRowIndex));
        cellInvoiceC4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceE4 = invoiceSheet.getRow(3);
        if (rowInvoiceE4 == null) rowInvoiceE4 = invoiceSheet.createRow(3);

        Cell cellInvoiceE4 = rowInvoiceE4.createCell(4);
        cellInvoiceE4.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("E4", 35, startRowIndex, sumRowIndex));
        cellInvoiceE4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF4 = invoiceSheet.getRow(3);
        if (rowInvoiceF4 == null) rowInvoiceF4 = invoiceSheet.createRow(3);

        Cell cellInvoiceF4 = rowInvoiceF4.createCell(5);
        cellInvoiceF4.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F4", 35, startRowIndex, lastRow));
        cellInvoiceF4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceC7 = invoiceSheet.getRow(6);
        if (rowInvoiceC7 == null) rowInvoiceC7 = invoiceSheet.createRow(6);

        Cell cellInvoiceC7 = rowInvoiceC7.createCell(2);
        cellInvoiceC7.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("C7", 35, startRowIndex, lastRow));
        cellInvoiceC7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceE7 = invoiceSheet.getRow(6);
        if (rowInvoiceE7 == null) rowInvoiceE7 = invoiceSheet.createRow(6);

        Cell cellInvoiceE7 = rowInvoiceE7.createCell(4);
        cellInvoiceE7.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("E7", 35, startRowIndex, lastRow));
        cellInvoiceE7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF7 = invoiceSheet.getRow(6);
        if (rowInvoiceF7 == null) rowInvoiceF7 = invoiceSheet.createRow(6);

        Cell cellInvoiceF7 = rowInvoiceF7.createCell(5);
        cellInvoiceF7.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F7", 35, startRowIndex, lastRow));
        cellInvoiceF7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF8 = invoiceSheet.getRow(7);
        if (rowInvoiceF8 == null) rowInvoiceF8 = invoiceSheet.createRow(7);

        Cell cellInvoiceF8 = rowInvoiceF8.createCell(5);
        cellInvoiceF8.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F8", 35, startRowIndex, lastRow));
        cellInvoiceF8.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF9 = invoiceSheet.getRow(8);
        if (rowInvoiceF9 == null) rowInvoiceF9 = invoiceSheet.createRow(8);

        Cell cellInvoiceF9 = rowInvoiceF9.createCell(5);
        cellInvoiceF9.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F9", 35, startRowIndex, lastRow));
        cellInvoiceF9.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF10 = invoiceSheet.getRow(9);
        if (rowInvoiceF10 == null) rowInvoiceF10 = invoiceSheet.createRow(9);

        Cell cellInvoiceF10 = rowInvoiceF10.createCell(5);
        cellInvoiceF10.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F10", 35, startRowIndex, lastRow));
        cellInvoiceF10.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF11 = invoiceSheet.getRow(10);
        if (rowInvoiceF11 == null) rowInvoiceF11 = invoiceSheet.createRow(10);

        Cell cellInvoiceF11 = rowInvoiceF11.createCell(5);
        cellInvoiceF11.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F11", 35, startRowIndex, lastRow));
        CellStyle sumStyle = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RP);
        byte[] yellowRgb = new byte[] {(byte) 8, (byte) 116, (byte) 196};
        sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
        cellInvoiceF11.setCellStyle(sumStyle);

        Row rowInvoiceA13 = invoiceSheet.getRow(12);
        if(rowInvoiceA13 == null) rowInvoiceA13 = invoiceSheet.createRow(12);

        Cell cellInoviceA13 = rowInvoiceA13.createCell(0);
        cellInoviceA13.setCellValue(DateUtil.getTanggalNow());
        cellInoviceA13.setCellStyle(style.get(ExcelStyleHelper.STYLE_BOLD));


        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();
    }

    
}
