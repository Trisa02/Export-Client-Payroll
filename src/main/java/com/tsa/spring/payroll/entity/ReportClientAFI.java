package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_afi")
public class ReportClientAFI {

    @Id
    @Column(name = "no")
    private Long no;

    @Column(name = "employee_id")
    private String nik;

    @Column(name = "employee_name")
    private String nama;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name ="periode_start")
    private LocalDate periodeStart;

    @Column(name ="periode_end")
    private LocalDate periodeEnd;

    @Column(name = "company")
    private String Company;

    @Column(name = "country")
    private String Country;

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

    @Column(name = "sub_business_line")
    private String SubBussines;

    @Column(name = "main_department")
    private String MainDepartment;

    @Column(name = "sub_department")
    private String SubDepartmen;

    @Column(name = "co_manager_1")
    private String CoManager;

    @Column(name = "join_date")
    private String JoinDate;

    @Column(name = "resign_date")
    private String ResignDate;

    @Column(name = "contract_start_date")
    private String ContractStart;

    @Column(name = "contract_end_date")
    private String ContractEnd;

    @Column(name = "remaining")
    private String AnnualLeave;

    @Column(name = "basic_salary")
    private String BasicSalary;

    @Column(name = "methode_pajak")
    private String NetGross;

    @Column(name = "marital_status")
    private String MaritalStatus;

    @Column(name = "npwp")
    private String NPWP;

    @Column(name = "ewd")
    private String EWD;

    @Column(name = "5ewd")
    private String WD5;

    @Column(name = "6ewd")
    private String WD6;

     @Column(name = "regular_tax")
    private String RegularTax;

    @Column(name = "irregular_tax")
    private String IrregularTax;

    @Column(name = "tj_lain")
    private String TunjanganLain;

    @Column(name = "overtime")
    private String Overtime;

    @Column(name = "rapelan")
    private String Rapelan;

    @Column(name = "ojt")
    private String OJT;

    @Column(name = "thr")
    private String THR;

    @Column(name = "bonus")
    private String Bonus;

    @Column(name = "commission")
    private String Commision;

    @Column(name = "insentif")
    private String Isentif;

    @Column(name = "uang_kompensasi")
    private String UangKompensasi;

    @Column(name = "bpjs_kesehatan_allowance")
    private String BpjsKesehataAllowance;

    @Column(name = "bpjs_adjustment_allowance")
    private String BpjsAdjusment;

    @Column(name = "bpjs_tenagakerjaan_allowance")
    private String BpjsTenagaKerjaAllowance;

    @Column(name = "potongan_absen")
    private String PotonganAbsen;

    @Column(name = "less_payment")
    private String LessPayment;

    @Column(name = "potongan_lainnya")
    private String PotonganLainnya;

    @Column(name = "regular_income_tax")
    private String RegulerIncome;

    @Column(name = "irregular_income_tax")
    private String IrregularIncome;

    @Column(name = "payable_tax")
    private String PayableTax;

    @Column(name = "last_compensation_payment")
    private String DatePayment;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
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

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public String getSubBussines() {
        return SubBussines;
    }

    public void setSubBussines(String subBussines) {
        SubBussines = subBussines;
    }

    public String getMainDepartment() {
        return MainDepartment;
    }

    public void setMainDepartment(String mainDepartment) {
        MainDepartment = mainDepartment;
    }

    public String getSubDepartmen() {
        return SubDepartmen;
    }

    public void setSubDepartmen(String subDepartmen) {
        SubDepartmen = subDepartmen;
    }

    public String getCoManager() {
        return CoManager;
    }

    public void setCoManager(String coManager) {
        CoManager = coManager;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getResignDate() {
        return ResignDate;
    }

    public void setResignDate(String resignDate) {
        ResignDate = resignDate;
    }

    public String getContractStart() {
        return ContractStart;
    }

    public void setContractStart(String contractStart) {
        ContractStart = contractStart;
    }

    public String getContractEnd() {
        return ContractEnd;
    }

    public void setContractEnd(String contractEnd) {
        ContractEnd = contractEnd;
    }

    public String getAnnualLeave() {
        return AnnualLeave;
    }

    public void setAnnualLeave(String annualLeave) {
        AnnualLeave = annualLeave;
    }

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    public String getNetGross() {
        return NetGross;
    }

    public void setNetGross(String netGross) {
        NetGross = netGross;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getNPWP() {
        return NPWP;
    }

    public void setNPWP(String nPWP) {
        NPWP = nPWP;
    }

    public String getEWD() {
        return EWD;
    }

    public void setEWD(String eWD) {
        EWD = eWD;
    }

    public String getWD5() {
        return WD5;
    }

    public void setWD5(String wD5) {
        WD5 = wD5;
    }

    public String getWD6() {
        return WD6;
    }

    public void setWD6(String wD6) {
        WD6 = wD6;
    }

    public String getRegularTax() {
        return RegularTax;
    }

    public void setRegularTax(String regularTax) {
        RegularTax = regularTax;
    }

    public String getIrregularTax() {
        return IrregularTax;
    }

    public void setIrregularTax(String irregularTax) {
        IrregularTax = irregularTax;
    }

    public String getTunjanganLain() {
        return TunjanganLain;
    }

    public void setTunjanganLain(String tunjanganLain) {
        TunjanganLain = tunjanganLain;
    }

    public String getOvertime() {
        return Overtime;
    }

    public void setOvertime(String overtime) {
        Overtime = overtime;
    }

    public String getRapelan() {
        return Rapelan;
    }

    public void setRapelan(String rapelan) {
        Rapelan = rapelan;
    }

    public String getOJT() {
        return OJT;
    }

    public void setOJT(String oJT) {
        OJT = oJT;
    }

    public String getTHR() {
        return THR;
    }

    public void setTHR(String tHR) {
        THR = tHR;
    }

    public String getBonus() {
        return Bonus;
    }

    public void setBonus(String bonus) {
        Bonus = bonus;
    }

    public String getCommision() {
        return Commision;
    }

    public void setCommision(String commision) {
        Commision = commision;
    }

    public String getIsentif() {
        return Isentif;
    }

    public void setIsentif(String isentif) {
        Isentif = isentif;
    }

    public String getUangKompensasi() {
        return UangKompensasi;
    }

    public void setUangKompensasi(String uangKompensasi) {
        UangKompensasi = uangKompensasi;
    }

    public String getBpjsKesehataAllowance() {
        return BpjsKesehataAllowance;
    }

    public void setBpjsKesehataAllowance(String bpjsKesehataAllowance) {
        BpjsKesehataAllowance = bpjsKesehataAllowance;
    }

    public String getBpjsAdjusment() {
        return BpjsAdjusment;
    }

    public void setBpjsAdjusment(String bpjsAdjusment) {
        BpjsAdjusment = bpjsAdjusment;
    }

    public String getBpjsTenagaKerjaAllowance() {
        return BpjsTenagaKerjaAllowance;
    }

    public void setBpjsTenagaKerjaAllowance(String bpjsTenagaKerjaAllowance) {
        BpjsTenagaKerjaAllowance = bpjsTenagaKerjaAllowance;
    }

    public String getPotonganAbsen() {
        return PotonganAbsen;
    }

    public void setPotonganAbsen(String potonganAbsen) {
        PotonganAbsen = potonganAbsen;
    }

    public String getLessPayment() {
        return LessPayment;
    }

    public void setLessPayment(String lessPayment) {
        LessPayment = lessPayment;
    }

    public String getPotonganLainnya() {
        return PotonganLainnya;
    }

    public void setPotonganLainnya(String potonganLainnya) {
        PotonganLainnya = potonganLainnya;
    }

    public String getRegulerIncome() {
        return RegulerIncome;
    }

    public void setRegulerIncome(String regulerIncome) {
        RegulerIncome = regulerIncome;
    }

    public String getIrregularIncome() {
        return IrregularIncome;
    }

    public void setIrregularIncome(String irregularIncome) {
        IrregularIncome = irregularIncome;
    }

    public String getPayableTax() {
        return PayableTax;
    }

    public void setPayableTax(String payableTax) {
        PayableTax = payableTax;
    }

    public String getDatePayment() {
        return DatePayment;
    }

    public void setDatePayment(String datePayment) {
        DatePayment = datePayment;
    }

    

    

   

    




    
}
