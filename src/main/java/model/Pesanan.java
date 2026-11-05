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
public abstract class Pesanan {
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
    private String metodePembayaran;
    
    //Hasil JOIN
    private String namaLayanan;
    private String namaPelanggan;
    private String namaDriver;
    
    public Pesanan() {
        
    }
    
    // Diisi oleh subclass: Transportasi atau Dokumen.
    public abstract String getJenisPesanan();
    
    public boolean bisaDibatalkan() {
        return MENUNGGU.equals(statusPesanan) || DITERIMA.equals(statusPesanan) || DIPROSES.equals(statusPesanan);
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
    public String getNamaLayanan() {
        return namaLayanan;
    }
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public String getNamaDriver() {
        return namaDriver;
    }
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
    public void setPesananId(int pesananId) {
        if (pesananId < 0) {
            throw new IllegalArgumentException("ID pesanan tidak boleh negatif");
        }
        this.pesananId = pesananId;
    }
    public void setLokasiJemput(String lokasiJemput) {
        if (lokasiJemput == null || lokasiJemput.trim().length() < 5) {
            throw new IllegalArgumentException("Lokasi jemput minimal 5 karakter dan tidak boleh kosong");
        }
        this.lokasiJemput = lokasiJemput.trim();
    }
    public void setLokasiTujuan(String lokasiTujuan) {
        if (lokasiTujuan == null || lokasiTujuan.trim().length() < 5) {
            throw new IllegalArgumentException("Lokasi tujuan minimal 5 karakter dan tidak boleh kosong");
        }
        this.lokasiTujuan = lokasiTujuan.trim();
    }
    public void setJarakKm(double jarakKm) {
        if (Double.isNaN(jarakKm) || Double.isInfinite(jarakKm) || jarakKm < 0) {
            throw new IllegalArgumentException("Jarak tidak boleh negatif");
        }
        this.jarakKm = jarakKm;
    }
    public void setWaktuPenjemputan(Timestamp waktuPenjemputan) {
        if (waktuPenjemputan == null) {
            throw new IllegalArgumentException("Waktu penjemputan tidak boleh kosong");
        }
        this.waktuPenjemputan = waktuPenjemputan;
    }
    public void setCatatan(String catatan) {
        if (catatan != null && catatan.trim().length() > 255) {
            throw new IllegalArgumentException("Catatan maksimal 255 karakter");
        }
        this.catatan = (catatan == null) ? null : catatan.trim();
    }
    public void setTotalBiaya(double totalBiaya) {
        if (Double.isNaN(totalBiaya) || Double.isInfinite(totalBiaya) || totalBiaya < 0) {
            throw new IllegalArgumentException("Total biaya tidak boleh negatif");
        }
        this.totalBiaya = totalBiaya;
    }
    public void setWaktuPesanan(Date waktuPesanan) {
        if (waktuPesanan == null) {
            throw new IllegalArgumentException("Waktu pesanan tidak boleh kosong");
        }
        this.waktuPesanan = waktuPesanan;
    }
    public void setStatusPesanan(String statusPesanan) {
        if (statusPesanan == null
                || !(statusPesanan.equalsIgnoreCase("menunggu")
                || statusPesanan.equalsIgnoreCase("diproses")
                || statusPesanan.equalsIgnoreCase("selesai")
                || statusPesanan.equalsIgnoreCase("dibatalkan"))) {
        throw new IllegalArgumentException("Status pesanan harus menunggu, diproses, selesai, atau dibatalkan");
    }
    this.statusPesanan = statusPesanan.toLowerCase();
    }
    public void setAlasanBatal(String alasanBatal) {
        if (alasanBatal != null && alasanBatal.trim().length() > 255) {
            throw new IllegalArgumentException("Alasan batal maksimal 255 karakter");
        }
        this.alasanBatal = (alasanBatal == null) ? null : alasanBatal.trim();
    }
    public void setPelangganId(int pelangganId) {
        if (pelangganId <= 0) {
            throw new IllegalArgumentException("ID pelanggan harus lebih dari 0");
        }
        this.pelangganId = pelangganId;
    }
    public void setDriverId(Integer driverId) {
        if (driverId != null && driverId <= 0) {
            throw new IllegalArgumentException("ID driver harus lebih dari 0");
        }
        this.driverId = driverId;
    }
    public void setLayananId(int layananId) {
        if (layananId <= 0) {
            throw new IllegalArgumentException("ID layanan harus lebih dari 0");
        }
        this.layananId = layananId;
    }
    public void setTransaksiId(int transaksiId) {
        if (transaksiId < 0) {
            throw new IllegalArgumentException("ID transaksi tidak boleh negatif");
        }
        this.transaksiId = transaksiId;
    }
    public void setNamaLayanan(String namaLayanan) {
        if (namaLayanan == null || namaLayanan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama layanan tidak boleh kosong");
        }
        this.namaLayanan = namaLayanan.trim();
    }
    public void setNamaPelanggan(String namaPelanggan) {
        if (namaPelanggan == null || !namaPelanggan.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama pelanggan hanya boleh huruf dan spasi");
        }
        this.namaPelanggan = namaPelanggan.trim();
    }
    public void setNamaDriver(String namaDriver) {
        if (namaDriver != null && !namaDriver.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama driver hanya boleh huruf dan spasi");
        }
        this.namaDriver = (namaDriver == null) ? null : namaDriver.trim();
    }
    public void setMetodePembayaran(String metodePembayaran) {
        if (metodePembayaran == null
                || !(metodePembayaran.equalsIgnoreCase("tunai")
                || metodePembayaran.equalsIgnoreCase("wallet"))) {
            throw new IllegalArgumentException("Metode pembayaran harus tunai atau wallet");
        }
        this.metodePembayaran = metodePembayaran.toLowerCase();
    }
}