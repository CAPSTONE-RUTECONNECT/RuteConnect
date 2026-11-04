/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class Pesanan {
    public static final String MENUNGGU = "Menunggu";
    public static final String DITERIMA = "Diterima";
    public static final String DIPROSES = "Diproses";
    public static final String DALAM_PERJALANAN = "Dalam Perjalanan";
    public static final String SELESAI = "Selesai";
    public static final String DIBATALKAN = "Dibatalkan";
    
    public static final String JENIS_TRANSPORTASI = "Transportasi";
    public static final String JENIS_DOKUMEN = "Dokumen";
    
    //Tabel pesanan
    private int pesananId;
    private String lokasiJemput;
    private String lokasiTujuan;
    private double jarakKm;
    private Timestamp waktuPenjemputan;
    private String catatan;
    private double totalBiaya;
    private Date waktuPesanan;
    private String statusPesanan;
    private String alasanBatal;
    private int pelangganId;
    private Integer driverId;
    private int layananId;
    private int transaksiId;
    private String jenisPesanan;
    private String metodePembayaran;
    
    //Tabel transportasi
    private int jumlahPenumpang;
    private String statusPenjemputan;
    
    //Tabel dokumen
    private String namaPenerima;
    private String noTeleponPenerima;
    private String detailDokumen;
    
    //Hasil JOIN
    private String namaLayanan;
    private String namaPelanggan;
    private String namaDriver;
    
    public Pesanan() {
        
    }
    
    public boolean bisaDibatalkan() {
        return MENUNGGU.equals(statusPesanan) || DITERIMA.equals(statusPesanan) || DIPROSES.equals(statusPesanan);
    }
    
    public boolean isDokumen() {
        return JENIS_DOKUMEN.equals(jenisPesanan);
    }
    
    public int getPesananId() {
        return pesananId;
    }
    public String getLokasiJemput() {
        return lokasiJemput;
    }
    public String getLokasiTujuan() {
        return lokasiTujuan;
    }
    public double getJarakKm() {
        return jarakKm;
    }
    public Timestamp getWaktuPenjemputan() {
        return waktuPenjemputan;
    }
    public String getCatatan() {
        return catatan;
    }
    public double getTotalBiaya() {
        return totalBiaya;
    }
    public Date getWaktuPesanan() {
        return waktuPesanan;
    }
    public String getStatusPesanan() {
        return statusPesanan;
    }
    public String getAlasanBatal() {
        return alasanBatal;
    }
    public Integer getDriverId() {
        return driverId;
    }
    public int getPelangganId() {
        return pelangganId;
    }
    public int getLayananId() {
        return layananId;
    }
    public int getTransaksiId() {
        return transaksiId;
    }
    public int getJumlahPenumpang() {
        return jumlahPenumpang;
    }
    public String getStatusPenjemputan() {
        return statusPenjemputan;
    }
    public String getNamaPenerima() {
        return namaPenerima;
    }
    public String getNoTeleponPenerima() {
        return noTeleponPenerima;
    }
    public String getDetailDokumen() {
        return detailDokumen;
    }
    public String getNamaLayanan() {
        return namaLayanan;
    }
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public String getNamaDriver() {
        return namaDriver;
    }
    public String getJenisPesanan() {
        return jenisPesanan;
    }
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
    public void setPesananId(int pesananId) {
        this.pesananId = pesananId;
    }
    public void setLokasiJemput(String lokasiJemput) {
        this.lokasiJemput = lokasiJemput;
    }
    public void setLokasiTujuan(String lokasiTujuan) {
        this.lokasiTujuan = lokasiTujuan;
    }
    public void setJarakKm(double jarakKm) {
        this.jarakKm = jarakKm;
    }
    public void setWaktuPenjemputan(Timestamp waktuPenjemputan) {
        this.waktuPenjemputan = waktuPenjemputan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }
    public void setWaktuPesanan(Date waktuPesanan) {
        this.waktuPesanan = waktuPesanan;
    }
    public void setStatusPesanan(String statusPesanan) {
        this.statusPesanan = statusPesanan;
    }
    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
    }
    public void setPelangganId(int pelangganId) {
        this.pelangganId = pelangganId;
    }
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    public void setLayananId(int layananId) {
        this.layananId = layananId;
    }
    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }
    public void setJumlahPenumpang(int jumlahPenumpang) {
        this.jumlahPenumpang = jumlahPenumpang;
    }
    public void setStatusPenjemputan(String statusPenjemputan) {
        this.statusPenjemputan = statusPenjemputan;
    }
    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }
    public void setNoTeleponPenerima(String noTeleponPenerima) {
        this.noTeleponPenerima = noTeleponPenerima;
    }
    public void setDetailDokumen(String detailDokumen) {
        this.detailDokumen = detailDokumen;
    }
    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    public void setNamaDriver(String namaDriver) {
        this.namaDriver = namaDriver;
    }
    public void setJenisPesanan(String jenisPesanan) {
        this.jenisPesanan = jenisPesanan;
    }
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
