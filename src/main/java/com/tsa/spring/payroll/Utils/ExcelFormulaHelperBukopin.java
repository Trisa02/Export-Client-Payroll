package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellReference;

public class ExcelFormulaHelperBukopin {

    private static final Map<Integer, List<Integer>> formulaColumnsMap =new HashMap<>();

    static{
        formulaColumnsMap.put(0 ,Arrays.asList(0,0));
        formulaColumnsMap.put(13 ,Arrays.asList(12,11,10));
        formulaColumnsMap.put(21 ,Arrays.asList(13,20));
        formulaColumnsMap.put(22 ,Arrays.asList(13));
        formulaColumnsMap.put(23 ,Arrays.asList(13));
        formulaColumnsMap.put(24 ,Arrays.asList(13));
        formulaColumnsMap.put(25 ,Arrays.asList(13));
        formulaColumnsMap.put(26 ,Arrays.asList(13));
        formulaColumnsMap.put(34 ,Arrays.asList(21,33));
        formulaColumnsMap.put(35 ,Arrays.asList(34));
        formulaColumnsMap.put(36 ,Arrays.asList(35));
        formulaColumnsMap.put(37 ,Arrays.asList(34,36));
        formulaColumnsMap.put(38 ,Arrays.asList(35));
        formulaColumnsMap.put(39 ,Arrays.asList(37,38));
    }

    public static String getExcelColumnName(int columnNumber) {
        StringBuilder columnName = new StringBuilder();
        columnNumber++; // Excel index dimulai dari 1, jadi tambah 1 dulu
        while (columnNumber > 0) {
            columnNumber--;
            columnName.insert(0, (char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return columnName.toString();
    }

    public static String generateFormula(int colIndex, int excelRowNumber) {

        List<Integer> cols = formulaColumnsMap.get(colIndex);
        if(cols == null || cols.isEmpty()) return "0";

        if(colIndex == 0 && cols.size() == 2){
            return String.format("ROW($%s)-ROW($%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(0)) + "$6");
        }

        else if(colIndex == 13 && cols.size() == 3){
            return String.format("IFERROR(ROUND($%s/$%s*$%s,0),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 21 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 22 && cols.size() == 1){
            return String.format("ROUND($%s*3.7%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 23 && cols.size() == 1){
            return String.format("ROUND($%s*0.24%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 24 && cols.size() == 1){
            return String.format("ROUND($%s*0.3%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 25 && cols.size() == 1){
            return String.format("ROUND($%s*2%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 26 && cols.size() == 1){
            return String.format("ROUND(600000*4%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 34 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 35 && cols.size() == 1){
            return String.format("%s*10%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 36 && cols.size() == 1){
            return String.format("%s*11%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 37 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 38 && cols.size() == 1){
            return String.format("%s*2%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 39 && cols.size() == 2){
            return String.format("%s-%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }





        return "0";  
    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }


    public static String formulaInvoice(String type, int colIndex, int startRow, int endRow){

        String colInvoice = CellReference.convertNumToColString(colIndex);

        switch (type) {
            case "C4":
                return String.format("Bukopin!%s%d", colInvoice, endRow);

            case "E4":
                return String.format("Bukopin!%s%d", colInvoice, endRow);

            case "F4":
                return "E4+C4";

            case "C7":
                return "SUM(C4)";

            case "E7":
                return "SUM(E4)";
            
            case "F7":
                return "SUM(F4)";
                
            case "F8":
                return "ROUND(E7*11%,0)";

            case "F9":
                return "ROUND(E7*2%,0)";

            case "F10":
                return "ROUND(SUM(F7:F8)-F9,0)";

            case "F11":
                return "F10";
            default:
                throw new IllegalArgumentException("Tipe rumus tidak dikenali: " + type);
        }
    }


}
