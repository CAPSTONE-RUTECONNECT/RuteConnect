/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Transaksi {
    public static final String JENIS_TOP_UP = "Top Up";
    public static final String JENIS_PEMBAYARAN = "Pembayaran Pesanan";
    
    public static final String BERHASIL = "Berhasil";
    public static final String GAGAL = "Gagal";
    
    //Metode pembayaran
    public static final String CASH = "Cash";
    public static final String E_WALLET = "E-Wallet";
    public static final String SALDO = "Saldo";
    
    private int transaksiId;
    private String jenisTransaksi;
    private double harga;
    private double biayaLainnya;
    private double jumlahTransaksi;
    private String metodePembayaran;
    private String statusTransaksi;
    private Date waktuTransaksi;
    private int userId;
    private Integer pesananId;
    
    public Transaksi() {
        
    }
    
    public int getTransaksiId() {
        return transaksiId;
    }
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }
    public double getHarga() {
        return harga;
    }
    public double getBiayaLainnya() {
        return biayaLainnya;
    }
    public double getJumlahTransaksi() {
        return jumlahTransaksi;
    }
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
    public String getStatusTransaksi() {
        return statusTransaksi;
    }
    public Date getWaktuTransaksi() {
        return waktuTransaksi;
    }
    public int getUserId() {
        return userId;
    }
    public Integer getPesananId() {
        return pesananId;
    }
    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }
    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public void setBiayaLainnya(double biayaLainnya) {
        this.biayaLainnya = biayaLainnya;
    }
    public void setJumlahTransaksi(double jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
    public void setWaktuTransaksi(Date waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setPesananId(Integer pesananId) {
        this.pesananId = pesananId;
    }
}
