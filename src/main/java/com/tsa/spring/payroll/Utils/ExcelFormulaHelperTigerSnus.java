package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFormulaHelperTigerSnus {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();

    static{
        formulaColumnsMap.put(16,Arrays.asList(13,15,14));
        formulaColumnsMap.put(21,Arrays.asList(16,20));
        formulaColumnsMap.put(26,Arrays.asList(16,17,18,22,23,24,25));
        formulaColumnsMap.put(28,Arrays.asList(26,27));
        formulaColumnsMap.put(29,Arrays.asList(28,29));
        formulaColumnsMap.put(30,Arrays.asList(28,29));
        formulaColumnsMap.put(31,Arrays.asList(16,31));
        formulaColumnsMap.put(32,Arrays.asList(16,32));
        formulaColumnsMap.put(35,Arrays.asList(21));
        formulaColumnsMap.put(36,Arrays.asList(21,35,34));
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

    public static String generateFormula(int colIndex, int excelRowNumber){
        
        List<Integer> cols = formulaColumnsMap.get(colIndex);
        if(cols == null || cols.isEmpty()) return "0";


        if (colIndex == 16 && cols.size()==3) {
           return String.format("IFERROR(ROUND(%s*(%s/%s),0),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 21 && cols.size() == 2){
            return String.format("ROUND(SUM(%s:%s),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }
        else if(colIndex == 26 && cols.size() == 7){
             return String.format("%s+%s+%s+%s+%s+%s+%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber,
                getExcelColumnName(cols.get(4)) + excelRowNumber,
                getExcelColumnName(cols.get(5)) + excelRowNumber,
                getExcelColumnName(cols.get(6)) + excelRowNumber);
        }

        else if(colIndex == 28){
             return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));
        }

        else if(colIndex == 29 && cols.size() == 2){
            return String.format("$%s*$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) +  "$6");   
        }

        else if(colIndex == 30){
             return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));
        }

        else if(colIndex == 31 && cols.size() == 2){
            return String.format("ROUND($%s*$%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) +  "$6");   
        }

        else if(colIndex == 32 && cols.size() == 2){
            return String.format("ROUND($%s*$%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) +  "$6");   
        }

        else if(colIndex == 35 && cols.size() == 1){
            return String.format("-ROUND(($%S/2)*5%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);   
        }

        else if(colIndex == 36){
             return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));
        }

        return "0";


    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }
    
}
