package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_tiketcom")
public class ReportClientTiketCOM {

    @Id
    @Column(name = "NO")
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

    @Column(name = "work_location")
    private String WorkLocation;

    @Column(name = "status_ptkp")
    private String StatusPTKP;

    @Column(name = "working_start_periode")
    private String Start;

    @Column(name = "working_end_periode")
    private String End;

    @Column(name = "basic_salary")
    private String BasicSalary;

    @Column(name = "rapel_basic_salary")
    private String RapelBasicSalary;

    @Column(name = "skill_allowance")
    private String SklillAllowance;

    @Column(name = "position_allowance")
    private String PositionAllowance;

    @Column(name = "grading_allowance")
    private String GradingAllowance;

    @Column(name = "montly_allowance")
    private String MonthlyAllowance;

    @Column(name = "montly_allowance_base_achevement")
    private String MonthlyAllowanceBase;

    @Column(name = "rapel_allowance")
    private String RapelAllowance;

    @Column(name = "amount")
    private String AmmountOvertime;

    @Column(name = "total_shift_attendance")
    private String TotalShift;

    @Column(name = "rate_shift")
    private String Rate;

    @Column(name = "amount_shift")
    private String Ammount;

    @Column(name = "total_meals_attendance")
    private String TotalAddance;

    @Column(name = "amount_meals")
    private String AmmountMeals;

    @Column(name = "total_salary")
    private String TotalSalary;

    @Column(name =  "jht_employee")
    private String JhtkKaryawan;

    @Column(name =  "jip_employee")
    private String JpKaryawan;

    @Column(name =  "kesehatan_employee")
    private String BpjsKesehatanKaryawan;

    @Column(name =  "pph_21")
    private String Pph21;

    @Column(name =  "deduction")
    private String Deduction;

     @Column(name =  "thp")
    private String THP;

    @Column(name =  "jht_perusahaan")
    private String JhtPerusahaan;

    @Column(name =  "jkk_perusahaaan")
    private String JkkPerusahaan;

    @Column(name =  "jkm_perusahaan")
    private String JkmPerusahaan;

    @Column(name =  "pensiun_perusahaan")
    private String Pensiun;

    @Column(name =  "kesehatan_perusahaan")
    private String BpjsKesehatanPerusahaan;

     @Column(name =  "gross_salary")
    private String GrossSalary;

    @Column(name =  "management_fee")
    private String ManagementFee;

    @Column(name =  "ppn_11")
    private String PPN;

    @Column(name =  "pph_23")
    private String PPH23;

    @Column(name =  "salary_total_invoice")
    private String TotalInvoice;

    @Column(name =  "ket")
    private String Ket;

    @Column(name =  "dub")
    private String DasarUpahBpjs;

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

    public String getWorkLocation() {
        return WorkLocation;
    }

    public void setWorkLocation(String workLocation) {
        WorkLocation = workLocation;
    }

    public String getStatusPTKP() {
        return StatusPTKP;
    }

    public void setStatusPTKP(String statusPTKP) {
        StatusPTKP = statusPTKP;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    public String getRapelBasicSalary() {
        return RapelBasicSalary;
    }

    public void setRapelBasicSalary(String rapelBasicSalary) {
        RapelBasicSalary = rapelBasicSalary;
    }

    public String getSklillAllowance() {
        return SklillAllowance;
    }

    public void setSklillAllowance(String sklillAllowance) {
        SklillAllowance = sklillAllowance;
    }

    public String getPositionAllowance() {
        return PositionAllowance;
    }

    public void setPositionAllowance(String positionAllowance) {
        PositionAllowance = positionAllowance;
    }

    public String getGradingAllowance() {
        return GradingAllowance;
    }

    public void setGradingAllowance(String gradingAllowance) {
        GradingAllowance = gradingAllowance;
    }

    public String getMonthlyAllowance() {
        return MonthlyAllowance;
    }

    public void setMonthlyAllowance(String monthlyAllowance) {
        MonthlyAllowance = monthlyAllowance;
    }

    public String getMonthlyAllowanceBase() {
        return MonthlyAllowanceBase;
    }

    public void setMonthlyAllowanceBase(String monthlyAllowanceBase) {
        MonthlyAllowanceBase = monthlyAllowanceBase;
    }

    public String getRapelAllowance() {
        return RapelAllowance;
    }

    public void setRapelAllowance(String rapelAllowance) {
        RapelAllowance = rapelAllowance;
    }

    public String getAmmountOvertime() {
        return AmmountOvertime;
    }

    public void setAmmountOvertime(String ammountOvertime) {
        AmmountOvertime = ammountOvertime;
    }

    public String getTotalShift() {
        return TotalShift;
    }

    public void setTotalShift(String totalShift) {
        TotalShift = totalShift;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getAmmount() {
        return Ammount;
    }

    public void setAmmount(String ammount) {
        Ammount = ammount;
    }

    public String getTotalAddance() {
        return TotalAddance;
    }

    public void setTotalAddance(String totalAddance) {
        TotalAddance = totalAddance;
    }

    public String getAmmountMeals() {
        return AmmountMeals;
    }

    public void setAmmountMeals(String ammountMeals) {
        AmmountMeals = ammountMeals;
    }

    public String getTotalSalary() {
        return TotalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        TotalSalary = totalSalary;
    }

    public String getJhtkKaryawan() {
        return JhtkKaryawan;
    }

    public void setJhtkKaryawan(String jhtkKaryawan) {
        JhtkKaryawan = jhtkKaryawan;
    }

    public String getJpKaryawan() {
        return JpKaryawan;
    }

    public void setJpKaryawan(String jpKaryawan) {
        JpKaryawan = jpKaryawan;
    }

    public String getBpjsKesehatanKaryawan() {
        return BpjsKesehatanKaryawan;
    }

    public void setBpjsKesehatanKaryawan(String bpjsKesehatanKaryawan) {
        BpjsKesehatanKaryawan = bpjsKesehatanKaryawan;
    }

    public String getPph21() {
        return Pph21;
    }

    public void setPph21(String pph21) {
        Pph21 = pph21;
    }

    public String getDeduction() {
        return Deduction;
    }

    public void setDeduction(String deduction) {
        Deduction = deduction;
    }

    public String getTHP() {
        return THP;
    }

    public void setTHP(String tHP) {
        THP = tHP;
    }

    public String getJhtPerusahaan() {
        return JhtPerusahaan;
    }

    public void setJhtPerusahaan(String jhtPerusahaan) {
        JhtPerusahaan = jhtPerusahaan;
    }

    public String getJkkPerusahaan() {
        return JkkPerusahaan;
    }

    public void setJkkPerusahaan(String jkkPerusahaan) {
        JkkPerusahaan = jkkPerusahaan;
    }

    public String getJkmPerusahaan() {
        return JkmPerusahaan;
    }

    public void setJkmPerusahaan(String jkmPerusahaan) {
        JkmPerusahaan = jkmPerusahaan;
    }

    public String getPensiun() {
        return Pensiun;
    }

    public void setPensiun(String pensiun) {
        Pensiun = pensiun;
    }

    public String getBpjsKesehatanPerusahaan() {
        return BpjsKesehatanPerusahaan;
    }

    public void setBpjsKesehatanPerusahaan(String bpjsKesehatanPerusahaan) {
        BpjsKesehatanPerusahaan = bpjsKesehatanPerusahaan;
    }

    public String getGrossSalary() {
        return GrossSalary;
    }

    public void setGrossSalary(String grossSalary) {
        GrossSalary = grossSalary;
    }

    public String getManagementFee() {
        return ManagementFee;
    }

    public void setManagementFee(String managementFee) {
        ManagementFee = managementFee;
    }

    public String getPPN() {
        return PPN;
    }

    public void setPPN(String pPN) {
        PPN = pPN;
    }

    public String getPPH23() {
        return PPH23;
    }

    public void setPPH23(String pPH23) {
        PPH23 = pPH23;
    }

    public String getTotalInvoice() {
        return TotalInvoice;
    }

    public void setTotalInvoice(String totalInvoice) {
        TotalInvoice = totalInvoice;
    }

    public String getKet() {
        return Ket;
    }

    public void setKet(String ket) {
        Ket = ket;
    }

    public String getDasarUpahBpjs() {
        return DasarUpahBpjs;
    }

    public void setDasarUpahBpjs(String dasarUpahBpjs) {
        DasarUpahBpjs = dasarUpahBpjs;
    }

    

    















}
