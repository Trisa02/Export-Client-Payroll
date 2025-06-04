package com.tsa.spring.payroll.Utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DateUtil {
   
    public static final String[] bulanNama = {
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    public static String dataTanggal(String searchBulan, String searchTahun) {
        if ((searchBulan == null || searchBulan.isEmpty()) && (searchTahun == null || searchTahun.isEmpty())) {
            return null;
        } else if (searchBulan != null && !searchBulan.isEmpty() && (searchTahun == null || searchTahun.isEmpty())) {
            int bulanInt = Integer.parseInt(searchBulan);  
        
            return bulanNama[bulanInt - 1];
        } else if (searchTahun != null && !searchTahun.isEmpty() && (searchBulan == null || searchBulan.isEmpty())) {
            return String.format("%d", Integer.parseInt(searchTahun));
        } else {
            int bulanInt = Integer.parseInt(searchBulan);
            int tahunInt = Integer.parseInt(searchTahun);
            return String.format("%d-%02d-01", tahunInt, bulanInt);
        }
    }

    public static int getDaysInMonth(String searchBulan, String searchTahun) {
        try {
            if ((searchBulan == null || searchBulan.isEmpty()) && (searchTahun == null || searchTahun.isEmpty())) {
                return 30;  
            }

            int bulan = Integer.parseInt(searchBulan);
            int tahun = Integer.parseInt(searchTahun);

        
            LocalDate date = LocalDate.of(tahun, bulan, 1);
            return date.lengthOfMonth();
        } catch (NumberFormatException | DateTimeException e) {
            return 0;  
        }
    }

    public static String dataPeriode(String searchBulan, String searchTahun,  String searchDivisi) {
        if ((searchBulan == null || searchBulan.isEmpty()) && (searchTahun == null || searchTahun.isEmpty())) {
            return "";
        }
    
        int bulanInt = 0;
        String bulanNamaStr = "";
        if (searchBulan != null && !searchBulan.isEmpty()) {
            bulanInt = Integer.parseInt(searchBulan);
            bulanNamaStr = bulanNama[bulanInt - 1];
        }
    
       
    
        if (bulanNamaStr != null && !bulanNamaStr.isEmpty() && (searchTahun == null || searchTahun.isEmpty())) {
            return bulanNamaStr;
        } else if (searchTahun != null && !searchTahun.isEmpty() && (searchBulan == null || searchBulan.isEmpty())) {
            return searchTahun;
        } else {
            return String.format("%s %s", bulanNamaStr, searchTahun);
        }
    }

   
    
    public static String formatRangeFromObjectArray(Object[] row, String divisi) {
            
        if (row == null || row.length < 2) return "-";
    
        LocalDate start = ((java.sql.Date) row[0]).toLocalDate();
        LocalDate end = ((java.sql.Date) row[1]).toLocalDate();
    
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d", new Locale("id", "ID")); // tanpa 0 di depan
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id", "ID"));
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));
        DateTimeFormatter dayMonthFormatter = DateTimeFormatter.ofPattern("d MMMM", new Locale("id", "ID")); 
    
        if ("tiketcom".equalsIgnoreCase(divisi)) {
            return dayFormatter.format(start) + "-" + dayFormatter.format(end) + " " + monthYearFormatter.format(end);
        } else if ("bank_kb_bukopin".equalsIgnoreCase(divisi)) {
            return dayMonthFormatter.format(start) + " - " + fullFormatter.format(end);
        } else if ("hci".equalsIgnoreCase(divisi)) {
            return dayFormatter.format(start) + " - " + dayFormatter.format(end) + " " + monthYearFormatter.format(end);
        }
    
        // Default jika divisi tidak cocok
         return dayFormatter.format(start) + "-" + dayFormatter.format(end) + " " + monthYearFormatter.format(end);
        
    }
    
}

    





