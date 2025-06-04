package com.tsa.spring.payroll.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.spring.payroll.dto.SearchData;
import com.tsa.spring.payroll.repository.*;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportClientService {

    ///Repo
    @Autowired
    private MasterDivisiRepo masterDivisiRepo;

    ////Service
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

    public void exportreport(HttpServletResponse response, SearchData searchData) throws Exception{
        String inputDivisi = searchData.getSearchDivisi();

        Optional<String> exportTypeOpt = masterDivisiRepo.findExportTypeByNamaIgnoreCase(inputDivisi);

        if(exportTypeOpt.isPresent()) {
            String exportType = exportTypeOpt.get();
        
            switch(exportType.toLowerCase()) {
                case "mitra_oddity":
                    reportClientMitraOddityService.exportClientMitraOddity(response, searchData);
                    break;
                case "tigersnus":
                    reportClientTigerSnusService.exportClientTigerSnus(response, searchData);
                    break;
                case "adira_finance":
                    reportClientAdiraFinanceService.exportAdiraFinance(response, searchData);
                    break;
                case "afi":
                    reportClientAFIService.exportReportClientAFI(response, searchData);
                    break;
                case "msi":
                        reportClientAFIService.exportReportClientAFI(response, searchData);
                        break;
                case "agriaku_digital_indonesia":
                    reportClientAgriakuService.exportReportClientAgriaku(response, searchData);
                    break;
                case "benih_berkah_berseri":
                    reportClientBenihBerkahService.exportReportClientBenihBerkah(response, searchData);
                    break;
                case "bank_kb_bukopin":
                    reportClientBukopinService.exportClientBukopin(response, searchData);
                    break;
                case "hci":
                    reportClientHCIService.exportReportClientHCI(response, searchData);
                    break;
                case "tiketcom":
                    reportClientTiketComService.exportClientTiketCom(response, searchData);
                    break;
                case "bank_dbs_indonesia":
                    reportClientDSAService.exportClientDSA(response, searchData);
                    break;
                default:
                    throw new IllegalArgumentException("Export type tidak dikenali: " + exportType);
            }
            return;
        } else {
            // Handle jika divisi tidak ditemukan
            throw new IllegalArgumentException("Divisi tidak ditemukan: " + inputDivisi);
        }

    //     if (reportClientAFIRepo.findByDivisionContainingIgnoreCase(searchData.getSearchDivisi())
    //         .stream()
    //         .map(ReportClientAFI::getDivision)
    //         .anyMatch(inputDivisi::equalsIgnoreCase)) {
    //         reportClientAFIService.exportReportClientAFI(response, searchData);
    //         return;
    //     }

    //     if(reportClientAgriakuRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientAgriaku::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientAgriakuService.exportReportClientAgriaku(response, searchData);
    //             return;
    //         }

    //     if(reportClientBenihBerkahRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientBenihBerkah::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientBenihBerkahService.exportReportClientBenihBerkah(response, searchData);
    //             return;
    //         }

    //     if(reportClientBukopinRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientBukopin::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientBukopinService.exportClientBukopin(response, searchData);
    //             return;
    //         }
    //     if(reportClientTiketComRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientTiketCOM::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientTiketComService.exportClientTiketCom(response, searchData);
    //             return;
    //         }

    //     if(reportClientHCIRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientHCI::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientHCIService.exportReportClientHCI(response, searchData);
    //             return;
    //         }

    //     if(reportClientTigerSnusRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientTigerSnus::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientTigerSnusService.exportClientTigerSnus(response, searchData);
    //             return;
    //         }

    //     if(reportClientMitraOddityRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientMitraOddity::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientMitraOddityService.exportClientMitraOddity(response, searchData);
    //             return;
    //         }

    //    if(reportClientDSARepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientDSA::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientDSAService.exportClientDSA(response, searchData);
    //             return;
    //         }
        
    //     if(reportClientAdiraFinanceRepo.findFirstByOrderByNoAsc()
    //         .map(ReportClientAdiraFinance::getDivision)
    //         .filter(inputDivisi::equalsIgnoreCase).isPresent()){
    //             reportClientAdiraFinanceService.exportAdiraFinance(response, searchData);
    //             return;
    //         }
    }
        
    
}
