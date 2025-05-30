package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFormulaHelperHCI {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();

    static{

        formulaColumnsMap.put(17,Arrays.asList(16,15));
        formulaColumnsMap.put(32,Arrays.asList(17,31,14,14));
        formulaColumnsMap.put(34,Arrays.asList(32,33));
        formulaColumnsMap.put(35,Arrays.asList(34,35));
        formulaColumnsMap.put(36,Arrays.asList(34,35));
        formulaColumnsMap.put(38,Arrays.asList(32));
        formulaColumnsMap.put(39,Arrays.asList(32,38,37));

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

        if(colIndex == 17 && cols.size() == 2){
            return String.format("ROUND($%s*$%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber);   
        }

        else if(colIndex == 32 && cols.size() == 4){
            return String.format("SUM(%s:%s)+SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,  
                getExcelColumnName(cols.get(2)) + excelRowNumber,  
                getExcelColumnName(cols.get(3)) + excelRowNumber); 
        }

        else if(colIndex == 34 && cols.size() == 2){
            return String.format("ROUND($%s+$%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber);   
        }

       
        else if(colIndex == 35 && cols.size() == 2){
            return String.format("$%s+$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) +  "$6");   
        }

        else if(colIndex == 36 && cols.size() == 2){
            return String.format("$%s+$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber);   
        
        }

        else if(colIndex == 38 && cols.size() == 1){
            return String.format("ROUND(($%s/2)*5%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);   
        }

        else if(colIndex == 39 && cols.size() == 3){
            return String.format("ROUND(%s-%s-%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);   
        }



        return "0";
    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    } 
}
