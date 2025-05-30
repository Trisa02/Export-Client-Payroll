package com.tsa.spring.payroll.Utils;

import java.time.DateTimeException;
import java.time.LocalDate;

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

    // public static String dataPeriode(String searchBulan, String searchTahun) {

    //     if ((searchBulan == null || searchBulan.isEmpty()) && (searchTahun == null || searchTahun.isEmpty())) {
    //         return "";
    //     } else if (searchBulan != null && !searchBulan.isEmpty() && (searchTahun == null || searchTahun.isEmpty())) {
    //         int bulanInt = Integer.parseInt(searchBulan);
    //         return bulanNama[bulanInt - 1];
    //     } else if (searchTahun != null && !searchTahun.isEmpty() && (searchBulan == null || searchBulan.isEmpty())) {
    //         return searchTahun;
    //     } else {
    //         int bulanInt = Integer.parseInt(searchBulan);
    //         return String.format("%s %s", bulanNama[bulanInt - 1], searchTahun);
    //     }
    // }

    public static String dataPeriode(String searchBulan, String searchTahun, LocalDate searchPeriodeStart, LocalDate searchPeriodeEnd, String searchDivisi) {
        if ((searchBulan == null || searchBulan.isEmpty()) && (searchTahun == null || searchTahun.isEmpty())) {
            return "";
        }
    
        int bulanInt = 0;
        String bulanNamaStr = "";
        if (searchBulan != null && !searchBulan.isEmpty()) {
            bulanInt = Integer.parseInt(searchBulan);
            bulanNamaStr = bulanNama[bulanInt - 1];
        }
    
        boolean isHCI = "Home Credit Indonesia".equalsIgnoreCase(searchDivisi);
        boolean isBkp = "Bank KB Bukopin".equalsIgnoreCase(searchDivisi);
        boolean hasFullDate = searchPeriodeStart != null && searchPeriodeEnd != null;
        int bulanStart = searchPeriodeStart.getMonthValue(); 
        int bulanEnd = searchPeriodeEnd.getMonthValue(); 
        String bulanStartStr = bulanNama[bulanStart - 1];
        String bulanEndStr = bulanNama[bulanEnd - 1];

        if (isHCI && hasFullDate && bulanNamaStr != null && !bulanNamaStr.isEmpty() && searchTahun != null && !searchTahun.isEmpty()) {
            return String.format("%s %s (%d-%d %s %s)",
                    bulanNamaStr,
                    searchTahun,
                    searchPeriodeStart.getDayOfMonth(),
                    searchPeriodeEnd.getDayOfMonth(),
                    bulanNamaStr,
                    searchTahun
            );
        }

        if(isBkp && hasFullDate && bulanNamaStr != null && !bulanNamaStr.isEmpty() && searchTahun != null && !searchTahun.isEmpty()){
            return String.format("%d %s - %d %s %s",
                searchPeriodeStart.getDayOfMonth(),
                bulanStartStr,
                searchPeriodeEnd.getDayOfMonth(),
                bulanEndStr,
                searchTahun
            );
        }
    
        if (bulanNamaStr != null && !bulanNamaStr.isEmpty() && (searchTahun == null || searchTahun.isEmpty())) {
            return bulanNamaStr;
        } else if (searchTahun != null && !searchTahun.isEmpty() && (searchBulan == null || searchBulan.isEmpty())) {
            return searchTahun;
        } else {
            return String.format("%s %s", bulanNamaStr, searchTahun);
        }
    }
    




}
