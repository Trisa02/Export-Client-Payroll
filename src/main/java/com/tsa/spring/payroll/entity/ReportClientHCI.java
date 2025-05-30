package com.tsa.spring.payroll.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_hci")
public class ReportClientHCI {

    @Id
    @Column(name = "no")
    private Long no;

    @Column(name = "nik_ktp")
    private String nikKtp;

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

    @Column(name = "npwp")
    private String NPWP;

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

    @Column(name = "labor")
    private String Labor;

    @Column(name = "join_date")
    private String JoinDate;

    @Column(name = "Norek")
    private String NoRekening;

    @Column(name = "wd_sebelumnya")
    private String WD;

    @Column(name = "wd_aktif_sebelumnya")
    private String WDActive;

    @Column(name = "salary_sebelumnya")
    private String LastSalary;

    @Column(name = "allowance")
    private String Allowance;

    @Column(name = "absen")
    private String Absen;

    @Column(name = "basic_salary")
    private String BasicSalary;

    @Column(name = "tunjangan_jabatan")
    private String TunjanganJabatan;

    @Column(name = "tunjangan_transportasi")
    private String TunjanganTransportasi;

    @Column(name = "tunjangan_komunikasi")
    private String TunjanganKomunikasi;

    @Column(name = "tunjangan_kehadiran")
    private String TunjanganKehadiran;

    @Column(name = "tunjangan_akomodasi")
    private String TunjanganAkomodasi;

    @Column(name = "tunjangan_makan")
    private String TunjanganUangMakan;

    @Column(name = "tunjangan_kerja")
    private String TunjanganKerja;

    @Column(name = "tunjangan_kerajinan")
    private String TunjanganKerajinanl;

    @Column(name = "Adjusment")
    private String Adjusment;

    @Column(name = "public_holiday")
    private String PublicHoliday;

    @Column(name = "tunjangan_training")
    private String TunjanganTraining;

    @Column(name = "lembur")
    private String Lembur;

    @Column(name = "insentif")
    private String Insentif;

    @Column(name = "recognition_fee")
    private String RecognitionFee;

    @Column(name = "premi_asuransi")
    private String Asuransi;

    @Column(name = "potongan_lain")
    private String Potongan;

    @Column(name = "status_employee")
    private String Status;

    @Column(name = "resign_date")
    private String ResignDate;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getNikKtp() {
        return nikKtp;
    }

    public void setNikKtp(String nikKtp) {
        this.nikKtp = nikKtp;
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

    public String getNPWP() {
        return NPWP;
    }

    public void setNPWP(String nPWP) {
        NPWP = nPWP;
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

    public String getLabor() {
        return Labor;
    }

    public void setLabor(String labor) {
        Labor = labor;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getNoRekening() {
        return NoRekening;
    }

    public void setNoRekening(String noRekening) {
        NoRekening = noRekening;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String wD) {
        WD = wD;
    }

    public String getWDActive() {
        return WDActive;
    }

    public void setWDActive(String wDActive) {
        WDActive = wDActive;
    }

    public String getLastSalary() {
        return LastSalary;
    }

    public void setLastSalary(String lastSalary) {
        LastSalary = lastSalary;
    }

    public String getAllowance() {
        return Allowance;
    }

    public void setAllowance(String allowance) {
        Allowance = allowance;
    }

    public String getAbsen() {
        return Absen;
    }

    public void setAbsen(String absen) {
        Absen = absen;
    }

    public String getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        BasicSalary = basicSalary;
    }

    public String getTunjanganJabatan() {
        return TunjanganJabatan;
    }

    public void setTunjanganJabatan(String tunjanganJabatan) {
        TunjanganJabatan = tunjanganJabatan;
    }

    public String getTunjanganTransportasi() {
        return TunjanganTransportasi;
    }

    public void setTunjanganTransportasi(String tunjanganTransportasi) {
        TunjanganTransportasi = tunjanganTransportasi;
    }

    public String getTunjanganKomunikasi() {
        return TunjanganKomunikasi;
    }

    public void setTunjanganKomunikasi(String tunjanganKomunikasi) {
        TunjanganKomunikasi = tunjanganKomunikasi;
    }

    public String getTunjanganKehadiran() {
        return TunjanganKehadiran;
    }

    public void setTunjanganKehadiran(String tunjanganKehadiran) {
        TunjanganKehadiran = tunjanganKehadiran;
    }

    public String getTunjanganAkomodasi() {
        return TunjanganAkomodasi;
    }

    public void setTunjanganAkomodasi(String tunjanganAkomodasi) {
        TunjanganAkomodasi = tunjanganAkomodasi;
    }

    public String getTunjanganUangMakan() {
        return TunjanganUangMakan;
    }

    public void setTunjanganUangMakan(String tunjanganUangMakan) {
        TunjanganUangMakan = tunjanganUangMakan;
    }

    public String getTunjanganKerja() {
        return TunjanganKerja;
    }

    public void setTunjanganKerja(String tunjanganKerja) {
        TunjanganKerja = tunjanganKerja;
    }

    public String getTunjanganKerajinanl() {
        return TunjanganKerajinanl;
    }

    public void setTunjanganKerajinanl(String tunjanganKerajinanl) {
        TunjanganKerajinanl = tunjanganKerajinanl;
    }

    public String getAdjusment() {
        return Adjusment;
    }

    public void setAdjusment(String adjusment) {
        Adjusment = adjusment;
    }

    public String getPublicHoliday() {
        return PublicHoliday;
    }

    public void setPublicHoliday(String publicHoliday) {
        PublicHoliday = publicHoliday;
    }

    public String getTunjanganTraining() {
        return TunjanganTraining;
    }

    public void setTunjanganTraining(String tunjanganTraining) {
        TunjanganTraining = tunjanganTraining;
    }

    public String getLembur() {
        return Lembur;
    }

    public void setLembur(String lembur) {
        Lembur = lembur;
    }

    public String getInsentif() {
        return Insentif;
    }

    public void setInsentif(String insentif) {
        Insentif = insentif;
    }

    public String getRecognitionFee() {
        return RecognitionFee;
    }

    public void setRecognitionFee(String recognitionFee) {
        RecognitionFee = recognitionFee;
    }

    public String getAsuransi() {
        return Asuransi;
    }

    public void setAsuransi(String asuransi) {
        Asuransi = asuransi;
    }

    public String getPotongan() {
        return Potongan;
    }

    public void setPotongan(String potongan) {
        Potongan = potongan;
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

   
    

    





}
