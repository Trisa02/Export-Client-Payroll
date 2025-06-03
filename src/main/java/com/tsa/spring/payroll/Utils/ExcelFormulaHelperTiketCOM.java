package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelFormulaHelperTiketCOM {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();

    static{
        formulaColumnsMap.put(19,Arrays.asList(17,18));
        formulaColumnsMap.put(22,Arrays.asList(8,15,16,19));
        formulaColumnsMap.put(23,Arrays.asList(8,9));
        formulaColumnsMap.put(24,Arrays.asList(8,9));
        formulaColumnsMap.put(25,Arrays.asList(40));
        formulaColumnsMap.put(28,Arrays.asList(22,23,24,25,26,27));
        formulaColumnsMap.put(29,Arrays.asList(8,9));
        formulaColumnsMap.put(30,Arrays.asList(8,9));
        formulaColumnsMap.put(31,Arrays.asList(8,9));
        formulaColumnsMap.put(32,Arrays.asList(8,9));
        formulaColumnsMap.put(33,Arrays.asList(40));
        formulaColumnsMap.put(34,Arrays.asList(22,29,33,27));
        formulaColumnsMap.put(35,Arrays.asList(34));
        formulaColumnsMap.put(36,Arrays.asList(35));
        formulaColumnsMap.put(37,Arrays.asList(35));
        formulaColumnsMap.put(38,Arrays.asList(34,36,37));

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

        if (colIndex == 19 && cols.size() == 2) {
            return String.format("%s*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }
        
        else if(colIndex == 22 && cols.size() == 4){
            return String.format("SUM(%s:%s,%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber);
        }

        else if(colIndex == 23 && cols.size() == 2){
            return String.format("SUM($%s:$%s)*2%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if (colIndex == 24 && cols.size() == 2) {
            String col1 = getExcelColumnName(cols.get(0)) + excelRowNumber;
            String col2 = getExcelColumnName(cols.get(1)) + excelRowNumber;
            return String.format("IF((%s+%s)<$Y$3,((%s+%s)*1%%),$Y$4)", col1, col2, col1, col2);
        }

        else if (colIndex == 25 && cols.size() == 1) {
            String col1 = getExcelColumnName(cols.get(0)) + excelRowNumber;
            return String.format("IF(($%s)<$Z$3,(($%s)*1%%),$Z$4)", col1,  col1);
        }

        else if (colIndex == 28 && cols.size() == 6) {
            return String.format("%s-(%s+%s+%s)-(%s+%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber,
                getExcelColumnName(cols.get(4)) + excelRowNumber,
                getExcelColumnName(cols.get(5)) + excelRowNumber);
        }

        else if(colIndex == 29 && cols.size() == 2){
            return String.format("SUM($%s:$%s)*3.7%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 30 && cols.size() == 2){
            return String.format("SUM($%s:$%s)*0.24%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 31 && cols.size() == 2){
            return String.format("SUM($%s:$%s)*0.3%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if (colIndex == 32 && cols.size() == 2) {
            String col1 = getExcelColumnName(cols.get(0)) + excelRowNumber;
            String col2 = getExcelColumnName(cols.get(1)) + excelRowNumber;
            return String.format("IF((%s+%s)<$Y$3,((%s+%s)*2%%),$Y$4)", col1, col2, col1, col2);
        }

        else if (colIndex == 33 && cols.size() == 1) {
            String col1 = getExcelColumnName(cols.get(0)) + excelRowNumber;
            return String.format("IF(($%s)<$Z$3,(($%s)*4%%),$Z$4)", col1,  col1);
        }

        else if(colIndex == 34 && cols.size() == 4){
            return String.format("%s+SUM(%s:%s)-%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber);
        }

        else if(colIndex == 35 && cols.size() == 1){
            return String.format("%s*8%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 36 && cols.size() == 1){
            return String.format("12%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 37 && cols.size() == 1){
            return String.format("2%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 38 && cols.size() == 3){
            return String.format("SUM(%s:%s)-%s",
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

    public static String generateCountFormula(int colIndex, int startRow, int endRow){
        String columnName = getExcelColumnName(colIndex);
        return String.format("COUNTA(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }
}
