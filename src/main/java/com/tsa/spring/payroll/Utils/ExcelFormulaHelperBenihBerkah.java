package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelFormulaHelperBenihBerkah {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();

    static{
        formulaColumnsMap.put(18, Arrays.asList(20,21));
        formulaColumnsMap.put(19, Arrays.asList(20,15));
        formulaColumnsMap.put(35,Arrays.asList(17,16,34));
        formulaColumnsMap.put(37,Arrays.asList(21,15,34));
        formulaColumnsMap.put(39,Arrays.asList(17,16,21,15));
        formulaColumnsMap.put(43,Arrays.asList(17,16,21,15));
        formulaColumnsMap.put(44,Arrays.asList(17,16,21,15));
        formulaColumnsMap.put(45,Arrays.asList(17,16,21,15));
        formulaColumnsMap.put(55,Arrays.asList(35,54));
        formulaColumnsMap.put(56,Arrays.asList(35));
        formulaColumnsMap.put(57,Arrays.asList(35));
        formulaColumnsMap.put(60,Arrays.asList(55,56,57,58,59,49,50,54,52));
        formulaColumnsMap.put(61,Arrays.asList(35));
        formulaColumnsMap.put(62,Arrays.asList(35));
        formulaColumnsMap.put(63,Arrays.asList(35));
        formulaColumnsMap.put(66,Arrays.asList(55,61,62,63,64));
        formulaColumnsMap.put(67,Arrays.asList(66,51,49,50,51,49,50));
        formulaColumnsMap.put(68,Arrays.asList(67));
        formulaColumnsMap.put(69,Arrays.asList(67));
        formulaColumnsMap.put(70,Arrays.asList(66,67,68,69));
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
        if (cols == null || cols.isEmpty()) return "0";  // Jika formula tidak ada, kembalikan 0

        // Jika formula berhubungan dengan beberapa kolom
        if (colIndex == 18) {
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));  // Gabungkan dengan '+'
        }

        // Jika formula hanya melibatkan 2 kolom
        else if (colIndex == 19 && cols.size() == 2) {
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
        else if((colIndex == 39 || colIndex == 43 || colIndex == 44) && cols.size() == 4){
            return String.format("IFERROR(($%s/$%s*500000)-($%s/$%s*500000),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }
        else if(colIndex == 45 && cols.size() == 4){
            return String.format("IFERROR(($%s/$%s*100000)-($%s/$%s*100000),0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber);
        }
        else if(colIndex == 55 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }
        else if(colIndex == 56 && cols.size() == 1){
            return String.format("ROUND($%s*2%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 57 && cols.size() == 1){
            return String.format("ROUND($%s*1%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 60){
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("-"));
        }
        else if(colIndex == 61 && cols.size() == 1){
            return String.format("ROUND($%s*3.7%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 62 && cols.size() == 1){
            return String.format("ROUND($%s*0.54%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 63 && cols.size() == 1){
            return String.format("ROUND($%s*2%%,0)",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 66){
            return cols.stream()
                    .map(c -> getExcelColumnName(c) + excelRowNumber)
                    .collect(Collectors.joining("+"));
        }
        else if(colIndex == 67 && cols.size() == 7){
            return String.format("(%s-%s-%s-%s)*7%%+(%s+%s+%s)*5%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + excelRowNumber,
                getExcelColumnName(cols.get(3)) + excelRowNumber,
                getExcelColumnName(cols.get(4)) + excelRowNumber,
                getExcelColumnName(cols.get(5)) + excelRowNumber,
                getExcelColumnName(cols.get(6)) + excelRowNumber);
        }
        else if(colIndex == 68 && cols.size() == 1){
            return String.format("%s*11%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 69 && cols.size() == 1){
            return String.format("%s*2%%",
                getExcelColumnName(cols.get(0)) + excelRowNumber);
        }
        else if(colIndex == 70 && cols.size() == 4){
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


