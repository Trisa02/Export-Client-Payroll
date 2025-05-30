package com.tsa.spring.payroll.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFormulaHelperAfi {

    private static final Map<Integer, List<Integer>> formulaColumnsMap = new HashMap<>();
    
    static{

        formulaColumnsMap.put(22,Arrays.asList(19,15,15,19,20,21));
        formulaColumnsMap.put(37,Arrays.asList(37));
        formulaColumnsMap.put(38,Arrays.asList(22,27,38));
        formulaColumnsMap.put(39,Arrays.asList(22,27,39));
        formulaColumnsMap.put(41,Arrays.asList(22,40));
        formulaColumnsMap.put(42,Arrays.asList(22,27,42));
        formulaColumnsMap.put(43,Arrays.asList(22,27,43));
        formulaColumnsMap.put(44,Arrays.asList(22,27,44,22,27,44));
        formulaColumnsMap.put(45,Arrays.asList(22,27,45,22,27,45));
        formulaColumnsMap.put(46,Arrays.asList(46));
        formulaColumnsMap.put(52,Arrays.asList(41,37,38,39,42,44,46,48,49,50,51,47));
        formulaColumnsMap.put(53,Arrays.asList(41,43,45));
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

        if(colIndex == 22 && cols.size() == 6){
            return String.format("IF(%s=0,%s,%s*%s/(%s+%s))",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,  
                getExcelColumnName(cols.get(2)) + excelRowNumber,  
                getExcelColumnName(cols.get(3)) + excelRowNumber,  
                getExcelColumnName(cols.get(4)) + excelRowNumber,  
                getExcelColumnName(cols.get(5)) + excelRowNumber);   
        }
        else if (colIndex == 37 && cols.size() == 1) {
            String colRef = getExcelColumnName(cols.get(0)) + "$2";
            return String.format(
                "ROUND(IF((5128960)*$%s<202695,202695,IF((5128960)*$%s>480000,480000,(5128960)*$%s)),0)",
                colRef, colRef, colRef
            );
        }

        else if(colIndex == 38 && cols.size() == 3){
            return String.format("(%s+%s)*$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + "$2");
        }

        else if(colIndex == 39 && cols.size() == 3){
            return String.format("(%s+%s)*$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + "$2");
        }

        else if(colIndex == 41 && cols.size() == 2){
            return String.format("SUM(%s:%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber);
        }

        else if(colIndex == 42 && cols.size() == 3){
            return String.format("(%s+%s)*$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + "$2");
        }

        else if(colIndex == 43 && cols.size() == 3){
            return String.format("(%s+%s)*$%s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,
                getExcelColumnName(cols.get(2)) + "$2");
        }

        else if (colIndex == 44 && cols.size() == 6) {
            return String.format(
                "IF(((%s+%s)*$%s)>100423,100423,(%s+%s)*$%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber, 
                getExcelColumnName(cols.get(1)) + excelRowNumber, 
                getExcelColumnName(cols.get(2)) + "$2",           
                getExcelColumnName(cols.get(3)) + excelRowNumber, 
                getExcelColumnName(cols.get(4)) + excelRowNumber, 
                getExcelColumnName(cols.get(5)) + "$2"            
            );
        }

        else if (colIndex == 45 && cols.size() == 6) {
            return String.format(
                "IF(((%s+%s)*$%s)>200846,200846,(%s+%s)*$%s)",
                getExcelColumnName(cols.get(0)) + excelRowNumber, 
                getExcelColumnName(cols.get(1)) + excelRowNumber, 
                getExcelColumnName(cols.get(2)) + "$2",           
                getExcelColumnName(cols.get(3)) + excelRowNumber, 
                getExcelColumnName(cols.get(4)) + excelRowNumber, 
                getExcelColumnName(cols.get(5)) + "$2"            
            );
        }

        else if (colIndex == 46 && cols.size() == 1) {
            return String.format(
                "ROUND(IF((5128960)*$%s<50674,50674,IF((5128960)*$%s>120000,120000,(5128960)*$%s)),0)",
                getExcelColumnName(cols.get(0)) + "$2",
                getExcelColumnName(cols.get(0)) + "$2",
                getExcelColumnName(cols.get(0)) + "$2"
            );
        }

        else if (colIndex == 52 && cols.size() == 12) {
            return String.format("(%s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s) + %s",
                getExcelColumnName(cols.get(0)) + excelRowNumber,
                getExcelColumnName(cols.get(1)) + excelRowNumber,  
                getExcelColumnName(cols.get(2)) + excelRowNumber,  
                getExcelColumnName(cols.get(3)) + excelRowNumber,  
                getExcelColumnName(cols.get(4)) + excelRowNumber,  
                getExcelColumnName(cols.get(5)) + excelRowNumber,  
                getExcelColumnName(cols.get(6)) + excelRowNumber,  
                getExcelColumnName(cols.get(7)) + excelRowNumber,  
                getExcelColumnName(cols.get(8)) + excelRowNumber, 
                getExcelColumnName(cols.get(9)) + excelRowNumber,  
                getExcelColumnName(cols.get(10)) + excelRowNumber, 
                getExcelColumnName(cols.get(11)) + excelRowNumber 
            );
        }

        else if (colIndex == 53 && cols.size() == 3) {
            return String.format("%s + %s + %s ",
                getExcelColumnName(cols.get(0)) + excelRowNumber,  
                getExcelColumnName(cols.get(1)) + excelRowNumber,  
                getExcelColumnName(cols.get(2)) + excelRowNumber 
            );
        }

        return "0";
    }

    public static String generateTotalFormula(int colIndex, int startRow, int endRow){

        String columnName = getExcelColumnName(colIndex);
        return String.format("SUM(%s%d:%s%d)", columnName, startRow, columnName, endRow);
    }

}
