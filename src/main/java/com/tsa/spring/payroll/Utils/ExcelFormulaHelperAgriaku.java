package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFormulaHelperAgriaku {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();

    static{
        formulaColumnsMap.put(18, Arrays.asList(20,21,22,23));
        formulaColumnsMap.put(19,Arrays.asList(20,15));
        formulaColumnsMap.put(35,Arrays.asList(17,16,34));
        formulaColumnsMap.put(37,Arrays.asList(23,15,34));
        formulaColumnsMap.put(38,Arrays.asList(20));
        formulaColumnsMap.put(39,Arrays.asList(17,16));
        formulaColumnsMap.put(41,Arrays.asList(19));
        formulaColumnsMap.put(42,Arrays.asList(20));
        formulaColumnsMap.put(43,Arrays.asList(17,16));
        formulaColumnsMap.put(44, Arrays.asList(17,16));
        formulaColumnsMap.put(45,Arrays.asList(17,16));
        formulaColumnsMap.put(53,Arrays.asList(35,52));
        formulaColumnsMap.put(54,Arrays.asList(35));
        formulaColumnsMap.put(55,Arrays.asList(35));
        formulaColumnsMap.put(58, Arrays.asList(53,54,55,56,57,52,49,50));
        formulaColumnsMap.put(59,Arrays.asList(35));
        formulaColumnsMap.put(60,Arrays.asList(35));
        formulaColumnsMap.put(61,Arrays.asList(35));
        formulaColumnsMap.put(64,Arrays.asList(53,59,60,61,62));
        formulaColumnsMap.put(65, Arrays.asList(64,49,49));
        formulaColumnsMap.put(66, Arrays.asList(65));
        formulaColumnsMap.put(67, Arrays.asList(65));
        formulaColumnsMap.put(68,Arrays.asList(64,65,66,67));
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

        if (colIndex == 18) {
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));  // Gabungkan dengan '+'
        }

        else if(colIndex == 19 && cols.size() == 2){
            return String.format("IFERROR($%s/$%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if (colIndex == 35 && cols.size() == 3){
            return String.format("IFERROR(%s/%s*%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 37 && cols.size() == 3){
            return String.format("IFERROR(-%s/%s*%s,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 38 && cols.size() == 1){
            return String.format("IFERROR($%s*20000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 39 && cols.size() == 2){
            return String.format("IFERROR(%s/%s*300000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 41 && cols.size() == 1){
            return String.format("IF(%s>=80%%,250000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 42 && cols.size() == 1){
            return String.format("IFERROR($%s*25000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 43 && cols.size() == 2){
            return String.format("IFERROR(%s/%s*150000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 44 && cols.size() == 2){
            return String.format("IFERROR(%s/%s*500000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 45 && cols.size() == 2){
            return String.format("IFERROR(%s/%s*500000,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 53 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 54 && cols.size() == 1){
            return String.format("ROUND($%s*2%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 55 && cols.size() == 1){
            return String.format("ROUND($%s*1%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if (colIndex == 58) {
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("-"));  // Gabungkan dengan '-'
        }

        else if(colIndex == 59 && cols.size() == 1){
            return String.format("ROUND($%s*3.7%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 60 && cols.size() == 1){
            return String.format("ROUND($%s*0.54%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 61 && cols.size() == 1){
            return String.format("ROUND($%s*2%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if (colIndex == 64) {
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));  // Gabungkan dengan '-'
        }

        else if(colIndex == 65 && cols.size() == 3){
            return String.format("(%s-%s)*7%%+%s*5%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 66 && cols.size() == 1){
            return String.format("%s*11%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 67 && cols.size() == 1){
            return String.format("%s*2%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 68 && cols.size() == 4){
            return String.format("%s+%s+%s-%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber);
        }


        return "0";  
    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }



}

