package com.tsa.spring.payroll.dto;

import java.time.LocalDate;

public class SearchData {

    private String searchDivisi;
    private String searchUnit;
    private String searchPosisi;
    private String serachEmployeeType;
    private String searchBranch;
    private String searchBulan;
    private String searchTahun;
    private String searchKeyword;
    private String searchEmployeeType;
    private LocalDate searchPeriodeStart;
    private LocalDate searchPeriodeEnd;

    public String getSearchDivisi() {
        return isEmpty(searchDivisi);
    }
    public void setSearchDivisi(String searchDivisi) {
        this.searchDivisi = searchDivisi;
    }
    
    public String getSearchUnit() {
        return isEmpty(searchUnit);
    }
    public void setSearchUnit(String searchUnit) {
        this.searchUnit = searchUnit;
    }
    public String getSearchPosisi() {
        return isEmpty(searchPosisi);
    }
    public void setSearchPosisi(String searchPosisi) {
        this.searchPosisi = searchPosisi;
    }
    public String getSerachEmployeeType() {
        return isEmpty(serachEmployeeType);
    }
    public void setSerachEmployeeType(String serachEmployeeType) {
        this.serachEmployeeType = serachEmployeeType;
    }
    public String getSearchBranch() {
        return isEmpty(searchBranch);
    }
    public void setSearchBranch(String searchBranch) {
        this.searchBranch = searchBranch;
    }
    public String getSearchBulan() {
        return isEmpty(searchBulan);
    }
    public void setSearchBulan(String searchBulan) {
        this.searchBulan = searchBulan;
    }
    public String getSearchTahun() {
        return isEmpty(searchTahun);
    }
    public void setSearchTahun(String searchTahun) {
        this.searchTahun = searchTahun;
    }

    public String getSearchKeyword() {
        return isEmpty(searchKeyword);
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchEmployeeType() {
        return isEmpty(searchEmployeeType);
    }
    public void setSearchEmployeeType(String searchEmployeeType) {
        this.searchEmployeeType = searchEmployeeType;
    }
    
    public LocalDate getSearchPeriodeStart() {
        return searchPeriodeStart;
    }
    public void setSearchPeriodeStart(LocalDate searchPeriodeStart) {
        this.searchPeriodeStart = searchPeriodeStart;
    }
    public LocalDate getSearchPeriodeEnd() {
        return searchPeriodeEnd;
    }
    public void setSearchPeriodeEnd(LocalDate searchPeriodeEnd) {
        this.searchPeriodeEnd = searchPeriodeEnd;
    }
    private String isEmpty(String val){
        return(val == null || val.trim().isEmpty()) ? null : val.trim();
    }
    
    

    

    
}
