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
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperBenihBerkah;
import com.tsa.spring.payroll.Utils.ExcelFormulaHelperBukopin;
import com.tsa.spring.payroll.Utils.ExcelStyleHelper;
import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientBenihBerkah;
import com.tsa.spring.payroll.repository.MasterDivisiRepo;
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

       @Autowired
    private MasterDivisiRepo masterDivisiRepo;

    public String getFormattedWorkinPeriode(String bulan, String tahun,String divisi) {
        List<Object[]> list = reportClientRepo.findWorkingPeriode(bulan, tahun);
        if (list == null || list.isEmpty()) {
            return "-";
        }
        Object[] workingPeriode = list.get(0);
        Optional<String>exportTypeDivisi = masterDivisiRepo.findExportTypeByNamaIgnoreCase(divisi);
        String exportType = exportTypeDivisi.orElse(divisi);
        return DateUtil.formatRangeFromObjectArray(workingPeriode,exportType);
    }

    public void exportReportClientBenihBerkah(HttpServletResponse response, SearchData searchData)throws Exception{

        Specification<ReportClientBenihBerkah> spec = reportClientSpecification.searchReportClient(searchData);
        List<ReportClientBenihBerkah> dataReportClients = reportClientRepo.findAll(spec);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ReportClientBenihBerkahBerseri.xlsx");

        ClassPathResource temPathResource = new ClassPathResource("templates/excel/TemplateBerkahBenihBerseri.xlsx");
        InputStream inputStream = temPathResource.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);

         // Sheet sheet = workbook.getSheetAt(0);
         Sheet invoiceSheet = workbook.getSheet("Invoice");
         Sheet benihSheet = workbook.getSheet("Benih Berkah Berseri");

        Map<String, CellStyle> style = ExcelStyleHelper.createStyles(workbook);

        //Untuk Header
        String searchBulan = searchData.getSearchBulan();
        String searchTahun = searchData.getSearchTahun();
        String searchDivisi = searchData.getSearchDivisi();

        Row row15 = benihSheet.getRow(0);
        if(row15 == null){
            row15 = benihSheet.createRow(0);
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

        Row row16 = benihSheet.getRow(0);
        if(row16 == null){
            row16 = benihSheet.createRow(0);
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

        Set<Integer> formulaColumsbpjs = new HashSet<>(Arrays.asList(58,64));

        Set<Integer> nullColums = new HashSet<>(Arrays.asList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,65, 71, 72, 73, 74, 75, 76, 77
        ));

        Set<Integer> alignCenter = Set.of(0,15, 16, 17, 18, 19, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33);
        Set<Integer> alignRight = Set.of(2,12, 13, 77);
        Set<Integer>percenStyle = Set.of(19);
        Set<Integer> textColumns = Set.of( 71,75);
        Set<Integer> moneyColumns = new HashSet<>();
        for (int i = 34; i <= 64; i++) moneyColumns.add(i);
        for (int i = 66; i <= 70; i++) moneyColumns.add(i);

        int startRowIndex = 1;

        int lastRow = startRowIndex + dataReportClients .size();
        Row sumRow = benihSheet.createRow(lastRow);
        int sumRowIndex = lastRow  + 1;

        Set<Integer>formulaSumColumns = new HashSet<>();
        for(int i = 20; i <= 70; i++) formulaSumColumns.add(i);


        for(int i =0; i < dataReportClients.size(); i++){
            ReportClientBenihBerkah data = dataReportClients.get(i);
            Row row = benihSheet.getRow(startRowIndex + i);
            if(row == null){
                row = benihSheet.createRow(startRowIndex + i);
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
            //cellData.put(58,data.getPotonganBPJSTKJP());
            cellData.put(59,data.getPph21());
            //cellData.put(64,data.getPotonganBPJSKesehatan());
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
                } else if(formulaColumsbpjs.contains(colIndex)){
                    String formulabpjs = ExcelFormulaHelperBenihBerkah.formulabpjs(colIndex, data.getDub());
                    cell.setCellFormula(formulabpjs);
                }
                else {
                    String value = cellData.getOrDefault(colIndex, "0");

                    if (value == null || value.trim().isEmpty()) {
                        if(nullColums.contains(colIndex)){
                            cell.setBlank();
                        }
                        else{
                            cell.setCellValue(0);
                        }
                        
                    } else {
                        if(textColumns.contains(colIndex)){
                            cell.setCellValue(value);
                        }else{
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
            Row headerRow = benihSheet.getRow(2);
            if (headerRow == null) {
                headerRow = benihSheet.createRow(2);
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

        ///Untuk Sheet Invoice
        Row rowInvoiceA2 = invoiceSheet.getRow(1);
        if(rowInvoiceA2 == null) rowInvoiceA2 = invoiceSheet.createRow(1);
        Cell cellInvoiceA2 = rowInvoiceA2.getCell(0);
        String periodeInovoice = getFormattedWorkinPeriode(searchBulan, searchTahun, searchDivisi);
        cellInvoiceA2.setCellValue("Periode " + periodeInovoice);

        Row rowInvoiceA13 = invoiceSheet.getRow(12);
        if(rowInvoiceA13 == null) rowInvoiceA13 = invoiceSheet.createRow(12);

        Cell cellInoviceA13 = rowInvoiceA13.createCell(0);
        cellInoviceA13.setCellValue(DateUtil.getTanggalNow());
        cellInoviceA13.setCellStyle(style.get(ExcelStyleHelper.STYLE_BOLD));

        Row rowInvoiceC4 = invoiceSheet.getRow(3);
        if (rowInvoiceC4 == null) rowInvoiceC4 = invoiceSheet.createRow(3);

        Cell cellInvoiceC4 = rowInvoiceC4.createCell(2);
        cellInvoiceC4.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("C4", 66, startRowIndex, sumRowIndex));
        cellInvoiceC4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceC5 = invoiceSheet.getRow(4);
        if (rowInvoiceC5 == null) rowInvoiceC5 = invoiceSheet.createRow(4);

        Cell cellInvoiceC5 = rowInvoiceC5.createCell(2);
        cellInvoiceC5.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("C5", 66, startRowIndex, sumRowIndex));
        cellInvoiceC5.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceC7 = invoiceSheet.getRow(6);
        if (rowInvoiceC7 == null) rowInvoiceC7 = invoiceSheet.createRow(6);

        Cell cellInvoiceC7 = rowInvoiceC7.createCell(2);
        cellInvoiceC7.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("C7", 66, startRowIndex, sumRowIndex));
        cellInvoiceC7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceE4 = invoiceSheet.getRow(3);
        if (rowInvoiceE4 == null) rowInvoiceE4 = invoiceSheet.createRow(3);

        Cell cellInvoiceE4 = rowInvoiceE4.createCell(4);
        cellInvoiceE4.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("E4", 35, startRowIndex, sumRowIndex));
        cellInvoiceE4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceE5 = invoiceSheet.getRow(4);
        if (rowInvoiceE5 == null) rowInvoiceE5 = invoiceSheet.createRow(4);

        Cell cellInvoiceE5 = rowInvoiceE5.createCell(4);
        cellInvoiceE5.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("E5", 35, startRowIndex, sumRowIndex));
        cellInvoiceE5.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceE7 = invoiceSheet.getRow(6);
        if (rowInvoiceE7 == null) rowInvoiceE7 = invoiceSheet.createRow(6);

        Cell cellInvoiceE7 = rowInvoiceE7.createCell(4);
        cellInvoiceE7.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("E7", 35, startRowIndex, sumRowIndex));
        cellInvoiceE7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF4 = invoiceSheet.getRow(3);
        if (rowInvoiceF4 == null) rowInvoiceF4 = invoiceSheet.createRow(3);

        Cell cellInvoiceF4 = rowInvoiceF4.createCell(5);
        cellInvoiceF4.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F4", 35, startRowIndex, lastRow));
        cellInvoiceF4.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF5 = invoiceSheet.getRow(4);
        if (rowInvoiceF5 == null) rowInvoiceF5 = invoiceSheet.createRow(4);

        Cell cellInvoiceF5 = rowInvoiceF5.createCell(5);
        cellInvoiceF5.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F5", 35, startRowIndex, lastRow));
        cellInvoiceF5.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF7 = invoiceSheet.getRow(6);
        if (rowInvoiceF7 == null) rowInvoiceF7 = invoiceSheet.createRow(6);

        Cell cellInvoiceF7 = rowInvoiceF7.createCell(5);
        cellInvoiceF7.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F7", 35, startRowIndex, lastRow));
        cellInvoiceF7.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF8 = invoiceSheet.getRow(7);
        if (rowInvoiceF8 == null) rowInvoiceF8 = invoiceSheet.createRow(7);

        Cell cellInvoiceF8 = rowInvoiceF8.createCell(5);
        cellInvoiceF8.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F8", 35, startRowIndex, lastRow));
        cellInvoiceF8.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF9 = invoiceSheet.getRow(8);
        if (rowInvoiceF9 == null) rowInvoiceF9 = invoiceSheet.createRow(8);

        Cell cellInvoiceF9 = rowInvoiceF9.createCell(5);
        cellInvoiceF9.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F9", 35, startRowIndex, lastRow));
        cellInvoiceF9.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));

        Row rowInvoiceF10 = invoiceSheet.getRow(9);
        if (rowInvoiceF10 == null) rowInvoiceF10 = invoiceSheet.createRow(9);

        Cell cellInvoiceF10 = rowInvoiceF10.createCell(5);
        cellInvoiceF10.setCellFormula(ExcelFormulaHelperBenihBerkah.formulaInvoice("F10", 35, startRowIndex, lastRow));
        cellInvoiceF10.setCellStyle(style.get(ExcelStyleHelper.STYLE_UANG_INVOICE));


        Row rowInvoiceF11 = invoiceSheet.getRow(10);
        if (rowInvoiceF11 == null) rowInvoiceF11 = invoiceSheet.createRow(10);

        Cell cellInvoiceF11 = rowInvoiceF11.createCell(5);
        cellInvoiceF11.setCellFormula(ExcelFormulaHelperBukopin.formulaInvoice("F11", 35, startRowIndex, lastRow));
        CellStyle sumStyle = style.get(ExcelStyleHelper.STYLE_UANG_INVOICE_RP);
        byte[] yellowRgb = new byte[] {(byte) 8, (byte) 116, (byte) 196};
        sumStyle = ExcelStyleHelper.applyColorToStyle(workbook, sumStyle, yellowRgb);
        cellInvoiceF11.setCellStyle(sumStyle);





        
        workbook.setForceFormulaRecalculation(true);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
        inputStream.close();
    }

    
}
