package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_agriaku")
public class ReportClientAgriaku {

    @Id
    @Column(name = "no")
    private Long no;

    @Column(name = "id")
    private String Id;

    @Column(name = "nik_dika")
    private String nik;

    @Column(name = "company")
    private String Company;

    @Column(name = "name_karyawan")
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

    @Column(name = "sub_department")
    private String SubDepartment;

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
    private String WokrDays2;

    @Column(name = "work_days_active")
    private String WorkDaysActive;

    @Column(name = "presence")
    private String Presence;

    @Column(name = "sick")
    private String Sick;
    
    @Column(name = "annual_leave")
    private String AnnualLeave;

    @Column(name = "alpa")
    private String Alpha;

    @Column(name = "y")
    private String columnY;

    @Column(name = "z")
    private String columnZ;

    @Column(name = "aa")
    private String columnAA;

    @Column(name = "ab")
    private String columnAB;

    @Column(name = "ac")
    private String columnAC;

    @Column(name = "ad")
    private String columnAD;

    @Column(name = "ae")
    private String columnAE;

    @Column(name = "af")
    private String columnAF;

    @Column(name = "ag")
    private String columnAG;

    @Column(name = "ah")
    private String columnAH;

    @Column(name = "basic_salary")
    private String BasicSalary;

    @Column(name = "paid_salary")
    private String PaidSalary;

    @Column(name = "rapelan")
    private String Rapelan;

    @Column(name = "ao")
    private String ColumnAO;

    @Column(name = "biaya_akomodasi")
    private String BiayaAkomodasi;

    @Column(name = "sewa_laptop")
    private String SewaLaptop;

    @Column(name = "transportasi")
    private String Transportasi;

    @Column(name = "insentif_sales")
    private String InsentifSales;

    @Column(name = "insentif_sales_offcycles")
    private String InsentifSalesOffcycles;

    @Column(name = "lembur_yang_sudah_dibayarkan")
    private String LemburyangDibayarkan;

    @Column(name = "lembur")
    private String Lembur;

    @Column(name = "kompensasi_pkwt")
    private String Kompensasi;

    @Column(name = "potongan_bpjs_perusahaan")
    private String PotonganBpjsPerusahaan;

    @Column(name = "pph_21")
    private String Pph21;

    @Column(name = "bpjs_kesehatan_perusahaan")
    private String BpjsKesehatanPerusahaan;

    @Column(name = "id_card")
    private String IdCard;

    @Column(name = "account")
    private String Account;

    @Column(name = "bank")
    private String Bank;

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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
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

    public String getSubDepartment() {
        return SubDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        SubDepartment = subDepartment;
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

    public String getWokrDays2() {
        return WokrDays2;
    }

    public void setWokrDays2(String wokrDays2) {
        WokrDays2 = wokrDays2;
    }

    public String getWorkDaysActive() {
        return WorkDaysActive;
    }

    public void setWorkDaysActive(String workDaysActive) {
        WorkDaysActive = workDaysActive;
    }

    public String getPresence() {
        return Presence;
    }

    public void setPresence(String presence) {
        Presence = presence;
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

    public String getAlpha() {
        return Alpha;
    }

    public void setAlpha(String alpha) {
        Alpha = alpha;
    }

    public String getColumnY() {
        return columnY;
    }

    public void setColumnY(String columnY) {
        this.columnY = columnY;
    }

    public String getColumnZ() {
        return columnZ;
    }

    public void setColumnZ(String columnZ) {
        this.columnZ = columnZ;
    }

    public String getColumnAA() {
        return columnAA;
    }

    public void setColumnAA(String columnAA) {
        this.columnAA = columnAA;
    }

    public String getColumnAB() {
        return columnAB;
    }

    public void setColumnAB(String columnAB) {
        this.columnAB = columnAB;
    }

    public String getColumnAC() {
        return columnAC;
    }

    public void setColumnAC(String columnAC) {
        this.columnAC = columnAC;
    }

    public String getColumnAD() {
        return columnAD;
    }

    public void setColumnAD(String columnAD) {
        this.columnAD = columnAD;
    }

    public String getColumnAE() {
        return columnAE;
    }

    public void setColumnAE(String columnAE) {
        this.columnAE = columnAE;
    }

    public String getColumnAF() {
        return columnAF;
    }

    public void setColumnAF(String columnAF) {
        this.columnAF = columnAF;
    }

    public String getColumnAG() {
        return columnAG;
    }

    public void setColumnAG(String columnAG) {
        this.columnAG = columnAG;
    }

    public String getColumnAH() {
        return columnAH;
    }

    public void setColumnAH(String columnAH) {
        this.columnAH = columnAH;
    }

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    public String getPaidSalary() {
        return PaidSalary;
    }

    public void setPaidSalary(String paidSalary) {
        PaidSalary = paidSalary;
    }

    public String getRapelan() {
        return Rapelan;
    }

    public void setRapelan(String rapelan) {
        Rapelan = rapelan;
    }

    public String getColumnAO() {
        return ColumnAO;
    }

    public void setColumnAO(String columnAO) {
        ColumnAO = columnAO;
    }

    public String getBiayaAkomodasi() {
        return BiayaAkomodasi;
    }

    public void setBiayaAkomodasi(String biayaAkomodasi) {
        BiayaAkomodasi = biayaAkomodasi;
    }

    public String getSewaLaptop() {
        return SewaLaptop;
    }

    public void setSewaLaptop(String sewaLaptop) {
        SewaLaptop = sewaLaptop;
    }

    public String getTransportasi() {
        return Transportasi;
    }

    public void setTransportasi(String transportasi) {
        Transportasi = transportasi;
    }

    public String getInsentifSales() {
        return InsentifSales;
    }

    public void setInsentifSales(String insentifSales) {
        InsentifSales = insentifSales;
    }

    public String getInsentifSalesOffcycles() {
        return InsentifSalesOffcycles;
    }

    public void setInsentifSalesOffcycles(String insentifSalesOffcycles) {
        InsentifSalesOffcycles = insentifSalesOffcycles;
    }

    public String getLemburyangDibayarkan() {
        return LemburyangDibayarkan;
    }

    public void setLemburyangDibayarkan(String lemburyangDibayarkan) {
        LemburyangDibayarkan = lemburyangDibayarkan;
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

    public String getPotonganBpjsPerusahaan() {
        return PotonganBpjsPerusahaan;
    }

    public void setPotonganBpjsPerusahaan(String potonganBpjsPerusahaan) {
        PotonganBpjsPerusahaan = potonganBpjsPerusahaan;
    }

    public String getPph21() {
        return Pph21;
    }

    public void setPph21(String pph21) {
        Pph21 = pph21;
    }

    public String getBpjsKesehatanPerusahaan() {
        return BpjsKesehatanPerusahaan;
    }

    public void setBpjsKesehatanPerusahaan(String bpjsKesehatanPerusahaan) {
        BpjsKesehatanPerusahaan = bpjsKesehatanPerusahaan;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
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
