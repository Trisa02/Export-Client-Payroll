package com.tsa.spring.payroll.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.service.*;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ReportPayrollClientController {

   
    @Autowired
    private ReportClientService reportClientService;

    @PostMapping("/export-client/export")
    public void exportReportClient(@RequestBody SearchData searchData, HttpServletResponse response) throws Exception {
        
        reportClientService.exportreport(response, searchData);
        
      
    }   

    @GetMapping("/export-new")
    public void exportClientNew(
        @RequestParam(required = false) String searchDivisi,
        @RequestParam(required = false) String searchUnit,
        @RequestParam(required = false) String searchPosition,
        @RequestParam(required = false) String searchBranch,
        @RequestParam(required = false) String searchBulan,
        @RequestParam(required = false) String searchTahun,
        @RequestParam(required = false) String searchEmployeeType,
        @RequestParam(required = false) String searchKeyword,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchPeriodeStart,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate searchPeriodeEnd,
        HttpServletResponse response) throws Exception {

    SearchData searchData = new SearchData();
    searchData.setSearchDivisi(searchDivisi);
    searchData.setSearchUnit(searchUnit);
    searchData.setSearchPosisi(searchPosition);
    searchData.setSearchBranch(searchBranch);
    searchData.setSearchBulan(searchBulan);
    searchData.setSearchTahun(searchTahun);
    searchData.setSearchEmployeeType(searchEmployeeType);
    searchData.setSearchKeyword(searchKeyword);
    searchData.setSearchPeriodeStart(searchPeriodeStart);
    searchData.setSearchPeriodeEnd(searchPeriodeEnd);

    reportClientService.exportreport(response, searchData);
}


}
