package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_adirafinance")
public class ReportClientAdiraFinance {

    @Id
    @Column(name = "no")
    private Long no;

    @Column(name = "nik")
    private String nik;

    @Column(name = "nik_adira")
    private String nikAdira;

    @Column(name = "nama")
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

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "end_contract")
    private String endContract;

    @Column(name = "periode_payroll")
    private String periodeInvoice;

    @Column(name = "resign_date")
    private String resignDate;

    @Column(name = "alasan_resign")
    private String alasanResign;

    @Column(name = "basic_salary")
    private String basicSalary;

    @Column(name = "total_works")
    private String totalWorks;

    @Column(name = "absen")
    private String absen;

    @Column(name = "tunjangan_jabatan")
    private String tunjanganJabatan;

    @Column(name = "jht_employee")
    private String jhtKaryawan;

    @Column(name = "jip_employee")
    private String jipKaryawan;

    @Column(name = "bpjs_employee")
    private String bpjsKaryawan;

    @Column(name = "bpjs_perusahaan")
    private String bpjsPerusahaan;



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

    public String getNikAdira() {
        return nikAdira;
    }

    public void setNikAdira(String nikAdira) {
        this.nikAdira = nikAdira;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getEndContract() {
        return endContract;
    }

    public void setEndContract(String endContract) {
        this.endContract = endContract;
    }

    public String getPeriodeInvoice() {
        return periodeInvoice;
    }

    public void setPeriodeInvoice(String periodeInvoice) {
        this.periodeInvoice = periodeInvoice;
    }

    public String getResignDate() {
        return resignDate;
    }

    public void setResignDate(String resignDate) {
        this.resignDate = resignDate;
    }

    public String getAlasanResign() {
        return alasanResign;
    }

    public void setAlasanResign(String alasanResign) {
        this.alasanResign = alasanResign;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getTotalWorks() {
        return totalWorks;
    }

    public void setTotalWorks(String totalWorks) {
        this.totalWorks = totalWorks;
    }

    public String getAbsen() {
        return absen;
    }

    public void setAbsen(String absen) {
        this.absen = absen;
    }

    public String getTunjanganJabatan() {
        return tunjanganJabatan;
    }

    public void setTunjanganJabatan(String tunjanganJabatan) {
        this.tunjanganJabatan = tunjanganJabatan;
    }

    public String getJhtKaryawan() {
        return jhtKaryawan;
    }

    public void setJhtKaryawan(String jhtKaryawan) {
        this.jhtKaryawan = jhtKaryawan;
    }

    public String getJipKaryawan() {
        return jipKaryawan;
    }

    public void setJipKaryawan(String jipKaryawan) {
        this.jipKaryawan = jipKaryawan;
    }

    public String getBpjsKaryawan() {
        return bpjsKaryawan;
    }

    public void setBpjsKaryawan(String bpjsKaryawan) {
        this.bpjsKaryawan = bpjsKaryawan;
    }

    public String getBpjsPerusahaan() {
        return bpjsPerusahaan;
    }

    public void setBpjsPerusahaan(String bpjsPerusahaan) {
        this.bpjsPerusahaan = bpjsPerusahaan;
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
