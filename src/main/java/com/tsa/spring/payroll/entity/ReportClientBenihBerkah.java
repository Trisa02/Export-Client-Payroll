package com.tsa.spring.payroll.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_benih_berkah")
public class ReportClientBenihBerkah {

    @Id
    @Column(name = "no")
    private Long no;

    @Column(name ="id")
    private String ID;

    @Column(name = "nik_dika")
    private String nik;

    @Column(name="company")
    private String company;

    @Column(name ="name_karyawan")
    private String nama;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name ="periode_start")
    private LocalDate periodeStart;

    @Column(name ="periode_end")
    private LocalDate periodeEnd;

    @Column(name = "division")
    private String division;

    @Column(name = "unit_name")
    private String unitname;

    @Column(name = "position") 
    private String position;

    @Column(name = "branch")
    private String branch;

    @Column(name = "bulan")
    private String monthpayroll;

    @Column(name = "tahun")
    private String yearpayroll;

    @Column(name = "department")
    private String Department;

    @Column(name = "service_location")
    private String ServiceLocation;

    @Column(name = "region")
    private String Region;

    @Column(name = "function")
    private String Function;

    @Column(name = "mmr")
    private String Mmr;

    @Column(name = "join_date")
    private String JoinDate;

    @Column(name = "end_contract")
    private String EndContract;

    @Column(name = "marital_status")
    private String MaritalStatus;

    @Column(name = "work_days1")
    private String WorkDays1;

    @Column(name = "work_days2")
    private String WorkDays2;

    @Column(name = "work_daysaktif")
    private String WorkDaysAktif;

    @Column(name = "presence")
    private String Presence;

    @Column(name = "alpha")
    private String Alpha;

    @Column(name = "sick")
    private String Sick;

    @Column(name = "annual_leave")
    private String AnnualLeave;

    @Column(name = "column_y")
    private String ColumnY;

    @Column(name = "column_z")
    private String PresenceZ;

    @Column(name = "column_aa")
    private String SickAA;

    @Column(name = "column_ab")
    private String AnnualLeaveAB;

    @Column(name = "column_ac")
    private String AlphaAC;

    @Column(name = "column_ad")
    private String ColumnAD;

    @Column(name = "column_ae")
    private String PresenceAE;

    @Column(name = "column_af")
    private String SickAF;

    @Column(name = "column_ag")
    private String AnnualLeaveAG;

    @Column(name =  "column_ah")
    private String AlphaAH;

    @Column(name = "basic_salary")
    private String BasicSalary;

    @Column(name = "rapel")
    private String Rapel;

    @Column(name = "uang_masa_kontrak")
    private String UangMasaKontrak;

    @Column(name = "tunjangan_uang_makan")
    private String TunjanganUangMakan;

    @Column(name = "tunjangan_operasional")
    private String TunjanganOperasional;

    @Column(name = "tunjangan-parkir_bensin")
    private String TunjanganParkirBensin;

    @Column(name = "tunjangan_sewa_service_motor")
    private String TunjanganSewaServiceMotor;

    @Column(name = "tunjangan_akomodasi")
    private String TunjanganAkomodasi;

    @Column(name = "tunjangan_sewalaptop")
    private String TunjanganSewaLapotop;

    @Column(name = "tunjangan_transportasi1")
    private String TunjanganTransportasi1;

    @Column(name = "insentif_sales_offcycle_dbyrkn")
    private String InsentifSalesOffcycleDibayarkan;

    @Column(name ="insentif_susulan")
    private String InsentifSusulan;

    @Column(name ="insentif")
    private String Insentif;

    @Column(name = "lembur_dibayarkan")
    private String LemburDibayarkan;

    @Column(name = "lembur")
    private String Lembur;

    @Column(name ="kompensasi")
    private String Kompensasi;

    @Column(name = "potongan_bpjstkjp")
    private String PotonganBPJSTKJP;

    @Column(name = "potongan_bpjs_kesehatan")
    private String PotonganBPJSKesehatan;

    @Column(name = "pph21")
    private String Pph21;

    @Column(name = "bpjs_perusahaan")
    private String BPJSPerusahaan;

    @Column(name = "idcard")
    private String IDCard;

    @Column(name ="account")
    private String Account;

    @Column(name = "bank")
    private String Bank;

    @Column(name = "cabang_bank")
    private String CabangBank;

    @Column(name = "name_bank")
    private String NameBank;

    @Column(name = "npwp")
    private String Npwp;

    @Column(name = "status")
    private String Status;

    @Column(name = "resign_date")
    private String ResignDate;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMonthpayroll() {
        return monthpayroll;
    }

    public void setMonthpayroll(String monthpayroll) {
        this.monthpayroll = monthpayroll;
    }

    public String getYearpayroll() {
        return yearpayroll;
    }

    public void setYearpayroll(String yearpayroll) {
        this.yearpayroll = yearpayroll;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getServiceLocation() {
        return ServiceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        ServiceLocation = serviceLocation;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getFunction() {
        return Function;
    }

    public void setFunction(String function) {
        Function = function;
    }

    public String getMmr() {
        return Mmr;
    }

    public void setMmr(String mmr) {
        Mmr = mmr;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getEndContract() {
        return EndContract;
    }

    public void setEndContract(String endContract) {
        EndContract = endContract;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getWorkDays1() {
        return WorkDays1;
    }

    public void setWorkDays1(String workDays1) {
        WorkDays1 = workDays1;
    }

    public String getWorkDays2() {
        return WorkDays2;
    }

    public void setWorkDays2(String workDays2) {
        WorkDays2 = workDays2;
    }

    public String getWorkDaysAktif() {
        return WorkDaysAktif;
    }

    public void setWorkDaysAktif(String workDaysAktif) {
        WorkDaysAktif = workDaysAktif;
    }

    public String getPresence() {
        return Presence;
    }

    public void setPresence(String presence) {
        Presence = presence;
    }

    public String getAlpha() {
        return Alpha;
    }

    public void setAlpha(String alpha) {
        Alpha = alpha;
    }

    public String getSick() {
        return Sick;
    }

    public void setSick(String sick) {
        Sick = sick;
    }

    public String getAnnualLeave() {
        return AnnualLeave;
    }

    public void setAnnualLeave(String annualLeave) {
        AnnualLeave = annualLeave;
    }

    public String getColumnY() {
        return ColumnY;
    }

    public void setColumnY(String columnY) {
        ColumnY = columnY;
    }

    public String getPresenceZ() {
        return PresenceZ;
    }

    public void setPresenceZ(String presenceZ) {
        PresenceZ = presenceZ;
    }

    public String getSickAA() {
        return SickAA;
    }

    public void setSickAA(String sickAA) {
        SickAA = sickAA;
    }

    public String getAnnualLeaveAB() {
        return AnnualLeaveAB;
    }

    public void setAnnualLeaveAB(String annualLeaveAB) {
        AnnualLeaveAB = annualLeaveAB;
    }

    public String getAlphaAC() {
        return AlphaAC;
    }

    public void setAlphaAC(String alphaAC) {
        AlphaAC = alphaAC;
    }

    public String getColumnAD() {
        return ColumnAD;
    }

    public void setColumnAD(String columnAD) {
        ColumnAD = columnAD;
    }

    public String getPresenceAE() {
        return PresenceAE;
    }

    public void setPresenceAE(String presenceAE) {
        PresenceAE = presenceAE;
    }

    public String getSickAF() {
        return SickAF;
    }

    public void setSickAF(String sickAF) {
        SickAF = sickAF;
    }

    public String getAnnualLeaveAG() {
        return AnnualLeaveAG;
    }

    public void setAnnualLeaveAG(String annualLeaveAG) {
        AnnualLeaveAG = annualLeaveAG;
    }

    public String getAlphaAH() {
        return AlphaAH;
    }

    public void setAlphaAH(String alphaAH) {
        AlphaAH = alphaAH;
    }

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    public String getRapel() {
        return Rapel;
    }

    public void setRapel(String rapel) {
        Rapel = rapel;
    }

    public String getUangMasaKontrak() {
        return UangMasaKontrak;
    }

    public void setUangMasaKontrak(String uangMasaKontrak) {
        UangMasaKontrak = uangMasaKontrak;
    }

    public String getTunjanganUangMakan() {
        return TunjanganUangMakan;
    }

    public void setTunjanganUangMakan(String tunjanganUangMakan) {
        TunjanganUangMakan = tunjanganUangMakan;
    }

    public String getTunjanganOperasional() {
        return TunjanganOperasional;
    }

    public void setTunjanganOperasional(String tunjanganOperasional) {
        TunjanganOperasional = tunjanganOperasional;
    }

    public String getTunjanganParkirBensin() {
        return TunjanganParkirBensin;
    }

    public void setTunjanganParkirBensin(String tunjanganParkirBensin) {
        TunjanganParkirBensin = tunjanganParkirBensin;
    }

    public String getTunjanganSewaServiceMotor() {
        return TunjanganSewaServiceMotor;
    }

    public void setTunjanganSewaServiceMotor(String tunjanganSewaServiceMotor) {
        TunjanganSewaServiceMotor = tunjanganSewaServiceMotor;
    }

    public String getTunjanganAkomodasi() {
        return TunjanganAkomodasi;
    }

    public void setTunjanganAkomodasi(String tunjanganAkomodasi) {
        TunjanganAkomodasi = tunjanganAkomodasi;
    }

    public String getTunjanganSewaLapotop() {
        return TunjanganSewaLapotop;
    }

    public void setTunjanganSewaLapotop(String tunjanganSewaLapotop) {
        TunjanganSewaLapotop = tunjanganSewaLapotop;
    }

    public String getTunjanganTransportasi1() {
        return TunjanganTransportasi1;
    }

    public void setTunjanganTransportasi1(String tunjanganTransportasi1) {
        TunjanganTransportasi1 = tunjanganTransportasi1;
    }

    public String getInsentifSalesOffcycleDibayarkan() {
        return InsentifSalesOffcycleDibayarkan;
    }

    public void setInsentifSalesOffcycleDibayarkan(String insentifSalesOffcycleDibayarkan) {
        InsentifSalesOffcycleDibayarkan = insentifSalesOffcycleDibayarkan;
    }

    public String getInsentifSusulan() {
        return InsentifSusulan;
    }

    public void setInsentifSusulan(String insentifSusulan) {
        InsentifSusulan = insentifSusulan;
    }

    public String getInsentif() {
        return Insentif;
    }

    public void setInsentif(String insentif) {
        Insentif = insentif;
    }

    public String getLemburDibayarkan() {
        return LemburDibayarkan;
    }

    public void setLemburDibayarkan(String lemburDibayarkan) {
        LemburDibayarkan = lemburDibayarkan;
    }

    public String getLembur() {
        return Lembur;
    }

    public void setLembur(String lembur) {
        Lembur = lembur;
    }

    public String getKompensasi() {
        return Kompensasi;
    }

    public void setKompensasi(String kompensasi) {
        Kompensasi = kompensasi;
    }

    public String getPotonganBPJSTKJP() {
        return PotonganBPJSTKJP;
    }

    public void setPotonganBPJSTKJP(String potonganBPJSTKJP) {
        PotonganBPJSTKJP = potonganBPJSTKJP;
    }

    public String getPotonganBPJSKesehatan() {
        return PotonganBPJSKesehatan;
    }

    public void setPotonganBPJSKesehatan(String potonganBPJSKesehatan) {
        PotonganBPJSKesehatan = potonganBPJSKesehatan;
    }

    public String getPph21() {
        return Pph21;
    }

    public void setPph21(String pph21) {
        Pph21 = pph21;
    }

    public String getBPJSPerusahaan() {
        return BPJSPerusahaan;
    }

    public void setBPJSPerusahaan(String bPJSPerusahaan) {
        BPJSPerusahaan = bPJSPerusahaan;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String iDCard) {
        IDCard = iDCard;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getCabangBank() {
        return CabangBank;
    }

    public void setCabangBank(String cabangBank) {
        CabangBank = cabangBank;
    }

    public String getNameBank() {
        return NameBank;
    }

    public void setNameBank(String nameBank) {
        NameBank = nameBank;
    }

    public String getNpwp() {
        return Npwp;
    }

    public void setNpwp(String npwp) {
        Npwp = npwp;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResignDate() {
        return ResignDate;
    }

    public void setResignDate(String resignDate) {
        ResignDate = resignDate;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public LocalDate getPeriodeStart() {
        return periodeStart;
    }

    public void setPeriodeStart(LocalDate periodeStart) {
        this.periodeStart = periodeStart;
    }

    public LocalDate getPeriodeEnd() {
        return periodeEnd;
    }

    public void setPeriodeEnd(LocalDate periodeEnd) {
        this.periodeEnd = periodeEnd;
    }

    

    

   

    
    

    
    

    

    
    

    
}
