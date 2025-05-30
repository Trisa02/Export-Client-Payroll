package com.tsa.spring.payroll.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_client_bukopin")
public class ReportClientBukopin {

    @Id
    @Column(name = "No")
    private Long no;

    @Column(name = "nip")
    private String nik;

    @Column(name = "name")
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

    @Column(name = "cabang")
    private String Cabang;

    @Column(name = "kantor")
    private String Kantor;

    @Column(name = "department")
    private String Department;

    @Column(name = "jabatan")
    private String Jabatan;

    @Column(name = "fungsi")
    private String Fungsi;

    @Column(name = "vendor")
    private String Vendor;

    @Column(name = "tanggal_jatuh_tempo")
    private String TanggalJatuhTempo;

    @Column(name = "gaji")
    private String Gaji;

    @Column(name = "wd")
    private String Wd;

    @Column(name = "wd_active")
    private String WdAktif;

    @Column(name = "salary")
    private String Salary;

    @Column(name = "tunj_dasar")
    private String TunjanganDasar;

    @Column(name = "umt")
    private String UMT;

    @Column(name = "lembur")
    private String Lembur;

    @Column(name = "insentif")
    private String Insentif;

    @Column(name = "bensin")
    private String Bensin;

    @Column(name = "t_kesehatan")
    private String TunjanganKesehatan;

    @Column(name = "t_lain")
    private String TunjanganLain;


    @Column(name = "thp")
    private String THP;

    @Column(name = "jamsostek")
    private String Jamsostek;

    @Column(name = "jkk")
    private String JKK;

    @Column(name = "jkm")
    private String JKM;

    @Column(name = "jip")
    private String JIP;

    @Column(name = "bpjs")
    private String BpjsKesehatan;

    @Column(name = "cadangan_thr")
    private String CadanganTHR;

    @Column(name = "cadangan_bpjs")
    private String CadanganBPJS;

    @Column(name = "cadangan_bonus")
    private String CadanganBonus;

    @Column(name = "seragam")
    private String Seragam;

    @Column(name = "cadangan_bandik")
    private String CadanganBandik;

    @Column(name = "cadangan_lain")
    private String CadanganLain;

    @Column(name = "kompensasi")
    private String Kompensasi;

    @Column(name = "total_btk")
    private String TotalBTK;

    @Column(name = "managemen_fee")
    private String ManagementFee;

    @Column(name = "ppn")
    private String PPN;

    @Column(name = "total_tagihan")
    private String TotalTagihan;

    @Column(name = "pph_23")
    private String PotonganPPh23;

    @Column(name = "pembayaran_vendor")
    private String PembayaranVendor;

    @Column(name = "periode_payroll")
    private String periodePayroll;

    @Column(name = "pic")
    private String PIC;

    @Column(name = "join_date")
    private String JoinDate;

    @Column(name = "work_location")
    private String WorkLocation;

    @Column(name = "data_ojk")
    private String DataOJK;

    @Column(name = "jenis_kelamin")
    private String JenisKelamin;

    @Column(name = "pendidikan_terakhir")
    private String PendidikanTerakhir;

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

    public String getCabang() {
        return Cabang;
    }

    public void setCabang(String cabang) {
        Cabang = cabang;
    }

    public String getKantor() {
        return Kantor;
    }

    public void setKantor(String kantor) {
        Kantor = kantor;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getJabatan() {
        return Jabatan;
    }

    public void setJabatan(String jabatan) {
        Jabatan = jabatan;
    }

    public String getFungsi() {
        return Fungsi;
    }

    public void setFungsi(String fungsi) {
        Fungsi = fungsi;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getTanggalJatuhTempo() {
        return TanggalJatuhTempo;
    }

    public void setTanggalJatuhTempo(String tanggalJatuhTempo) {
        TanggalJatuhTempo = tanggalJatuhTempo;
    }

    public String getGaji() {
        return Gaji;
    }

    public void setGaji(String gaji) {
        Gaji = gaji;
    }

    public String getWd() {
        return Wd;
    }

    public void setWd(String wd) {
        Wd = wd;
    }

    public String getWdAktif() {
        return WdAktif;
    }

    public void setWdAktif(String wdAktif) {
        WdAktif = wdAktif;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getTunjanganDasar() {
        return TunjanganDasar;
    }

    public void setTunjanganDasar(String tunjanganDasar) {
        TunjanganDasar = tunjanganDasar;
    }

    public String getUMT() {
        return UMT;
    }

    public void setUMT(String uMT) {
        UMT = uMT;
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

    public String getBensin() {
        return Bensin;
    }

    public void setBensin(String bensin) {
        Bensin = bensin;
    }

    public String getTunjanganKesehatan() {
        return TunjanganKesehatan;
    }

    public void setTunjanganKesehatan(String tunjanganKesehatan) {
        TunjanganKesehatan = tunjanganKesehatan;
    }

    public String getTunjanganLain() {
        return TunjanganLain;
    }

    public void setTunjanganLain(String tunjanganLain) {
        TunjanganLain = tunjanganLain;
    }

    public String getTHP() {
        return THP;
    }

    public void setTHP(String tHP) {
        THP = tHP;
    }

    public String getJamsostek() {
        return Jamsostek;
    }

    public void setJamsostek(String jamsostek) {
        Jamsostek = jamsostek;
    }

    public String getJKK() {
        return JKK;
    }

    public void setJKK(String jKK) {
        JKK = jKK;
    }

    public String getJKM() {
        return JKM;
    }

    public void setJKM(String jKM) {
        JKM = jKM;
    }

    public String getJIP() {
        return JIP;
    }

    public void setJIP(String jIP) {
        JIP = jIP;
    }

    public String getBpjsKesehatan() {
        return BpjsKesehatan;
    }

    public void setBpjsKesehatan(String bpjsKesehatan) {
        BpjsKesehatan = bpjsKesehatan;
    }

    public String getCadanganTHR() {
        return CadanganTHR;
    }

    public void setCadanganTHR(String cadanganTHR) {
        CadanganTHR = cadanganTHR;
    }

    public String getCadanganBPJS() {
        return CadanganBPJS;
    }

    public void setCadanganBPJS(String cadanganBPJS) {
        CadanganBPJS = cadanganBPJS;
    }

    public String getCadanganBonus() {
        return CadanganBonus;
    }

    public void setCadanganBonus(String cadanganBonus) {
        CadanganBonus = cadanganBonus;
    }

    public String getSeragam() {
        return Seragam;
    }

    public void setSeragam(String seragam) {
        Seragam = seragam;
    }

    public String getCadanganBandik() {
        return CadanganBandik;
    }

    public void setCadanganBandik(String cadanganBandik) {
        CadanganBandik = cadanganBandik;
    }

    public String getCadanganLain() {
        return CadanganLain;
    }

    public void setCadanganLain(String cadanganLain) {
        CadanganLain = cadanganLain;
    }

    public String getKompensasi() {
        return Kompensasi;
    }

    public void setKompensasi(String kompensasi) {
        Kompensasi = kompensasi;
    }

    public String getTotalBTK() {
        return TotalBTK;
    }

    public void setTotalBTK(String totalBTK) {
        TotalBTK = totalBTK;
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

    public String getTotalTagihan() {
        return TotalTagihan;
    }

    public void setTotalTagihan(String totalTagihan) {
        TotalTagihan = totalTagihan;
    }

    public String getPotonganPPh23() {
        return PotonganPPh23;
    }

    public void setPotonganPPh23(String potonganPPh23) {
        PotonganPPh23 = potonganPPh23;
    }

    public String getPembayaranVendor() {
        return PembayaranVendor;
    }

    public void setPembayaranVendor(String pembayaranVendor) {
        PembayaranVendor = pembayaranVendor;
    }

    

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String pIC) {
        PIC = pIC;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getWorkLocation() {
        return WorkLocation;
    }

    public void setWorkLocation(String workLocation) {
        WorkLocation = workLocation;
    }

    public String getDataOJK() {
        return DataOJK;
    }

    public void setDataOJK(String dataOJK) {
        DataOJK = dataOJK;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getPendidikanTerakhir() {
        return PendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        PendidikanTerakhir = pendidikanTerakhir;
    }

    public String getPeriodePayroll() {
        return periodePayroll;
    }

    public void setPeriodePayroll(String periodePayroll) {
        this.periodePayroll = periodePayroll;
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
