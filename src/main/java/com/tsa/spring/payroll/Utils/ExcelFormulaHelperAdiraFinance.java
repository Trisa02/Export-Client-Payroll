package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ExcelFormulaHelperAdiraFinance {

    private static final Map<Integer, List<Integer>> formulaColumnsMap =new HashMap<>();

    static{
        formulaColumnsMap.put(14,Arrays.asList(13,12,10));
        formulaColumnsMap.put(15,Arrays.asList(13,12,11));
        formulaColumnsMap.put(17,Arrays.asList(14));
        formulaColumnsMap.put(19,Arrays.asList(14,14,14));
        formulaColumnsMap.put(22,Arrays.asList(14,21));
        formulaColumnsMap.put(23,Arrays.asList(22));
        formulaColumnsMap.put(24,Arrays.asList(23));
        formulaColumnsMap.put(25,Arrays.asList(22,23,24));
        formulaColumnsMap.put(26,Arrays.asList(23,25));
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

        if(colIndex == 14 && cols.size() == 3){
            return String.format("(%s/%s)*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 15 && cols.size() == 3){
            return String.format("ROUND(%s/%s*%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 17 && cols.size() == 1){
            return String.format("4.24%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 19 && cols.size() == 3){
            return String.format("IF(%s<9559600, %s*2%%, IF(%s>=9559600, 9559600*2%%))",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber
            );
        }

        else if(colIndex == 22 && cols.size() == 2){
            return String.format("ROUND(SUM(%s:%s),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 23 && cols.size() == 1){
            return String.format("ROUND(%s*5%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 24 && cols.size() == 1){
            return String.format("11%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 25){
            return cols.stream()
                .map(c -> getExcelColumnName(c) + excelRowNumber)
                .collect(Collectors.joining("+"));  // Gabungkan dengan '+'
        }

        
        else if(colIndex == 26 && cols.size() == 2){
            return String.format("(-2%%*%s)+%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }



        return "0";
    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    } 

    public static String formulaInvoice(String type, int startRow,int endRow){

        String colW = "W";
        String colX = "X";

        switch (type) {
            case "C11":
                return String.format("'Adira Finance'!%s%d", colW, endRow);
                
            case "D11":
                return String.format("'Adira Finance'!%s%d", colX, endRow);

            case "E11":
                return "SUM(C11:D11)";

            case "C12":
                return "SUM(C11:C11,0)";
            
            case "D12":
                return "SUM(D11:D11,0)";

            case "E12":
                return "SUM(E11:E11,0)";
        
            default:
                throw new IllegalArgumentException("Tipe rumus tidak dikenali: " + type);
        }

    }

}
