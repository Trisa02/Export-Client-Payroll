package com.tsa.spring.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.entity.ReportClientAFI;
import com.tsa.spring.payroll.entity.ReportClientAdiraFinance;
import com.tsa.spring.payroll.entity.ReportClientAgriaku;
import com.tsa.spring.payroll.entity.ReportClientBenihBerkah;
import com.tsa.spring.payroll.entity.ReportClientBukopin;
import com.tsa.spring.payroll.entity.ReportClientDSA;
import com.tsa.spring.payroll.entity.ReportClientHCI;
import com.tsa.spring.payroll.entity.ReportClientMitraOddity;
import com.tsa.spring.payroll.entity.ReportClientTigerSnus;
import com.tsa.spring.payroll.entity.ReportClientTiketCOM;
import com.tsa.spring.payroll.repository.*;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportClientService {

    ///Service
    @Autowired
    private ReportClientAFIRepo reportClientAFIRepo;

    @Autowired
    private ReportClientAgriakuRepo reportClientAgriakuRepo;

    @Autowired
    private ReportClientBenihBerkahRepo reportClientBenihBerkahRepo;

    @Autowired
    private ReportClientBukopinRepo reportClientBukopinRepo;

    @Autowired
    private ReportClientTiketComRepo reportClientTiketComRepo;

    @Autowired
    private ReportClientHCIRepo reportClientHCIRepo;

    @Autowired
    private ReportClientTigerSnusRepo reportClientTigerSnusRepo;

    @Autowired
    private ReportClientMitraOddityRepo reportClientMitraOddityRepo;

    @Autowired
    private ReportClientDSARepo reportClientDSARepo;

    @Autowired
    private ReportClientAdiraFinanceRepo reportClientAdiraFinanceRepo;

    ////Repo
    @Autowired
    private ReportClientAFIService reportClientAFIService;

    @Autowired 
    private ReportClientAgriakuService reportClientAgriakuService;

    @Autowired 
    private ReportClientBenihBerkahService reportClientBenihBerkahService;
    
    @Autowired
    private ReportClientBukopinService reportClientBukopinService;

    @Autowired
    private ReportClientTiketComService reportClientTiketComService;

    @Autowired
    private ReportClientHCIService reportClientHCIService;

    @Autowired
    private ReportClientTigerSnusService reportClientTigerSnusService;

    @Autowired
    private ReportClientMitraOddityService reportClientMitraOddityService;

    @Autowired
    private ReportClientDSAService reportClientDSAService;

    @Autowired
    private ReportClientAdiraFinanceService reportClientAdiraFinanceService;

    ReportClientService(ReportClientTigerSnusRepo reportClientTigerSnusRepo) {
        this.reportClientTigerSnusRepo = reportClientTigerSnusRepo;
    }

    public void exportreport(HttpServletResponse response, SearchData searchData) throws Exception{
        String inputDivisi = searchData.getSearchDivisi();

        if (reportClientAFIRepo.findByDivisionContainingIgnoreCase(searchData.getSearchDivisi())
            .stream()
            .map(ReportClientAFI::getDivision)
            .anyMatch(inputDivisi::equalsIgnoreCase)) {
            reportClientAFIService.exportReportClientAFI(response, searchData);
            return;
        }

        if(reportClientAgriakuRepo.findFirstByOrderByNoAsc()
            .map(ReportClientAgriaku::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientAgriakuService.exportReportClientAgriaku(response, searchData);
                return;
            }

        if(reportClientBenihBerkahRepo.findFirstByOrderByNoAsc()
            .map(ReportClientBenihBerkah::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientBenihBerkahService.exportReportClientBenihBerkah(response, searchData);
                return;
            }

        if(reportClientBukopinRepo.findFirstByOrderByNoAsc()
            .map(ReportClientBukopin::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientBukopinService.exportClientBukopin(response, searchData);
                return;
            }
        if(reportClientTiketComRepo.findFirstByOrderByNoAsc()
            .map(ReportClientTiketCOM::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientTiketComService.exportClientTiketCom(response, searchData);
                return;
            }

        if(reportClientHCIRepo.findFirstByOrderByNoAsc()
            .map(ReportClientHCI::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientHCIService.exportReportClientHCI(response, searchData);
                return;
            }

        if(reportClientTigerSnusRepo.findFirstByOrderByNoAsc()
            .map(ReportClientTigerSnus::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientTigerSnusService.exportClientTigerSnus(response, searchData);
                return;
            }

        if(reportClientMitraOddityRepo.findFirstByOrderByNoAsc()
            .map(ReportClientMitraOddity::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientMitraOddityService.exportClientMitraOddity(response, searchData);
                return;
            }

       if(reportClientDSARepo.findFirstByOrderByNoAsc()
            .map(ReportClientDSA::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientDSAService.exportClientDSA(response, searchData);
                return;
            }
        
        if(reportClientAdiraFinanceRepo.findFirstByOrderByNoAsc()
            .map(ReportClientAdiraFinance::getDivision)
            .filter(inputDivisi::equalsIgnoreCase).isPresent()){
                reportClientAdiraFinanceService.exportAdiraFinance(response, searchData);
                return;
            }
    }
        
    
}
