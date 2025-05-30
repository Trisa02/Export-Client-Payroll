package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_dsa")
public class ReportClientDSA {

    @Id
    @Column(name = "no")
    private Long no;

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

    @Column(name = "sales_code")
    private String salesCode;

    @Column(name = "nama")
    private String nama;

    @Column(name = "nik")
    private String nik;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name ="periode_start")
    private LocalDate periodeStart;

    @Column(name ="periode_end")
    private LocalDate periodeEnd;

    @Column(name = "title")
    private String title;

    @Column(name = "nama_tl")
    private String namaTl;

    @Column(name = "nama_manager")
    private String namaManager;

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "resign_date")
    private String resignDate;

    @Column(name = "level")
    private String level;

    @Column(name = "total_works")
    private String totalWorks;

    @Column(name = "absen")
    private String absen;

    @Column(name = "basic_salary")
    private String basicSalary;

    @Column(name = "rapelan")
    private String rapelan;

    @Column(name = "uang_kesehatan")
    private String uangKesehatan;

    @Column(name = "wajib_rek")
    private String wajibRek;

    @Column(name = "norek")
    private String norek;

    @Column(name = "remarks")
    private String remarks;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
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

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNamaTl() {
        return namaTl;
    }

    public void setNamaTl(String namaTl) {
        this.namaTl = namaTl;
    }

    public String getNamaManager() {
        return namaManager;
    }

    public void setNamaManager(String namaManager) {
        this.namaManager = namaManager;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getResignDate() {
        return resignDate;
    }

    public void setResignDate(String resignDate) {
        this.resignDate = resignDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getRapelan() {
        return rapelan;
    }

    public void setRapelan(String rapelan) {
        this.rapelan = rapelan;
    }

    public String getUangKesehatan() {
        return uangKesehatan;
    }

    public void setUangKesehatan(String uangKesehatan) {
        this.uangKesehatan = uangKesehatan;
    }

    public String getWajibRek() {
        return wajibRek;
    }

    public void setWajibRek(String wajibRek) {
        this.wajibRek = wajibRek;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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
