package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFormulaHelperDSA {

    private static final Map<Integer, List<Integer>> formulaColumnsMap =new HashMap<>();

    static{
        formulaColumnsMap.put(14, Arrays.asList(12,11,11));
        formulaColumnsMap.put(16, Arrays.asList(11,11));
        formulaColumnsMap.put(17, Arrays.asList(14,15,16,13));
        formulaColumnsMap.put(18, Arrays.asList(17));
        formulaColumnsMap.put(19, Arrays.asList(18));
        formulaColumnsMap.put(20, Arrays.asList(18));
        formulaColumnsMap.put(21, Arrays.asList(19));
        formulaColumnsMap.put(22, Arrays.asList(19));
        formulaColumnsMap.put(23, Arrays.asList(17,19,21,22));
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
            return String.format("%s/$%s*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) +   "$1",
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }

        else if(colIndex == 16 && cols.size() == 2){
            return String.format("200000/$%s*%s",
                getExcelColumnName(cols.get(0)) +   "$1",
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 17 && cols.size() == 4){
            return String.format("SUM(%s,%s,%s,%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber);
        }

        else if(colIndex == 18 && cols.size() == 1){
            return String.format("4.75%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 19 && cols.size() == 1){
            return String.format("90%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 20 && cols.size() == 1){
            return String.format("10%%*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 21 && cols.size() == 1){
            return String.format("%s*11%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 22 && cols.size() == 1){
            return String.format("(-2%%)*%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }

        else if(colIndex == 23){
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));  // Gabungkan dengan '+'
        }

        return "0";


    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }

}
