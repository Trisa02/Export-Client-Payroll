package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "report_client_mitraoddity")
public class ReportClientMitraOddity {

     @Id
    @Column(name = "no")
    private Long no;

    @Column(name = "nik")
    private String nik;

    @Column(name = "employee_name")
    private String nama;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name ="periode_start")
    private LocalDate periodeStart;

    @Column(name ="periode_end")
    private LocalDate periodeEnd;

    @Column(name = "npwp")
    private String npwp;

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

    @Column(name = "norek")
    private String noRekening;

    @Column(name = "allowance1")
    private String allowance1;

    @Column(name = "wd_sebelumnya")
    private String wdSebelumnya;

    @Column(name = "wd_aktif_sebelumnya")
    private String wdActiveSebelumnya;

    @Column(name = "allowance2")
    private String allowance2;

    @Column(name = "basic_salary")
    private String allowance3;

    @Column(name = "gajiperhari")
    private String gajiperhari;

    @Column(name = "absen")
    private String wdActive;

    @Column(name = "tunjangan_transportasi")
    private String tunjanganTransportasi;

    @Column(name = "insentif")
    private String insentif;

    @Column(name = "premi_terdaftar")
    private String premiTerdaftar;

    @Column(name = "potongan")
    private String potongan;

    @Column(name = "jkk_perusahaan")
    private String jkk;

     @Column(name = "jkm_perusahaan")
    private String jkm;

    @Column(name = "jamsostek_perusahaan")
    private String jht;

    @Column(name = "premi_asuransi")
    private String asuransiKesehatan;

    @Column(name = "bpjs_employee")
    private String bpjs;

    @Column(name = "status_employee")
    private String status;

    @Column(name = "resign_date")
    private String resignDate;

    

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

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
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

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getAllowance1() {
        return allowance1;
    }

    public void setAllowance1(String allowance1) {
        this.allowance1 = allowance1;
    }

    public String getWdSebelumnya() {
        return wdSebelumnya;
    }

    public void setWdSebelumnya(String wdSebelumnya) {
        this.wdSebelumnya = wdSebelumnya;
    }

    public String getWdActiveSebelumnya() {
        return wdActiveSebelumnya;
    }

    public void setWdActiveSebelumnya(String wdActiveSebelumnya) {
        this.wdActiveSebelumnya = wdActiveSebelumnya;
    }

    public String getAllowance2() {
        return allowance2;
    }

    public void setAllowance2(String allowance2) {
        this.allowance2 = allowance2;
    }

    public String getAllowance3() {
        return allowance3;
    }

    public void setAllowance3(String allowance3) {
        this.allowance3 = allowance3;
    }

    public String getGajiperhari() {
        return gajiperhari;
    }

    public void setGajiperhari(String gajiperhari) {
        this.gajiperhari = gajiperhari;
    }

    public String getWdActive() {
        return wdActive;
    }

    public void setWdActive(String wdActive) {
        this.wdActive = wdActive;
    }

    public String getTunjanganTransportasi() {
        return tunjanganTransportasi;
    }

    public void setTunjanganTransportasi(String tunjanganTransportasi) {
        this.tunjanganTransportasi = tunjanganTransportasi;
    }

    public String getInsentif() {
        return insentif;
    }

    public void setInsentif(String insentif) {
        this.insentif = insentif;
    }

    public String getPremiTerdaftar() {
        return premiTerdaftar;
    }

    public void setPremiTerdaftar(String premiTerdaftar) {
        this.premiTerdaftar = premiTerdaftar;
    }

    public String getPotongan() {
        return potongan;
    }

    public void setPotongan(String potongan) {
        this.potongan = potongan;
    }

    public String getJkk() {
        return jkk;
    }

    public void setJkk(String jkk) {
        this.jkk = jkk;
    }

    public String getJkm() {
        return jkm;
    }

    public void setJkm(String jkm) {
        this.jkm = jkm;
    }

    public String getJht() {
        return jht;
    }

    public void setJht(String jht) {
        this.jht = jht;
    }

    public String getAsuransiKesehatan() {
        return asuransiKesehatan;
    }

    public void setAsuransiKesehatan(String asuransiKesehatan) {
        this.asuransiKesehatan = asuransiKesehatan;
    }

    public String getBpjs() {
        return bpjs;
    }

    public void setBpjs(String bpjs) {
        this.bpjs = bpjs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResignDate() {
        return resignDate;
    }

    public void setResignDate(String resignDate) {
        this.resignDate = resignDate;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
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
