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

    // Metode pembayaran
    public static final String CASH = "Cash";
    public static final String E_WALLET = "E-Wallet";
    // RutePay = saldo_wallet milik pelanggan
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
        if (transaksiId < 0) {
            throw new IllegalArgumentException("ID transaksi tidak boleh negatif");
        }
        this.transaksiId = transaksiId;
    }
    
    public void setJenisTransaksi(String jenisTransaksi) {
        if (jenisTransaksi == null) {
            throw new IllegalArgumentException("Jenis transaksi tidak boleh kosong");
        }
        if (jenisTransaksi.equalsIgnoreCase(JENIS_TOP_UP)) {
            this.jenisTransaksi = JENIS_TOP_UP;
        } else if (jenisTransaksi.equalsIgnoreCase(JENIS_PEMBAYARAN)) {
            this.jenisTransaksi = JENIS_PEMBAYARAN;
        } else {
            throw new IllegalArgumentException("Jenis transaksi harus " + JENIS_TOP_UP
                    + " atau " + JENIS_PEMBAYARAN);
        }
    }
    
    public void setHarga(double harga) {
        if (Double.isNaN(harga) || Double.isInfinite(harga) || harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        this.harga = harga;
    }
    
    public void setBiayaLainnya(double biayaLainnya) {
        if (Double.isNaN(biayaLainnya) || Double.isInfinite(biayaLainnya) || biayaLainnya < 0) {
            throw new IllegalArgumentException("Biaya lainnya tidak boleh negatif");
        }
        this.biayaLainnya = biayaLainnya;
    }
    
    public void setJumlahTransaksi(double jumlahTransaksi) {
        if (Double.isNaN(jumlahTransaksi) || Double.isInfinite(jumlahTransaksi) || jumlahTransaksi <= 0) {
            throw new IllegalArgumentException("Jumlah transaksi harus lebih dari 0");
        }
        this.jumlahTransaksi = jumlahTransaksi;
    }
    
    public void setMetodePembayaran(String metodePembayaran) {
        if (metodePembayaran == null) {
            throw new IllegalArgumentException("Metode pembayaran tidak boleh kosong");
        }
        if (metodePembayaran.equalsIgnoreCase(CASH)) {
            this.metodePembayaran = CASH;
        } else if (metodePembayaran.equalsIgnoreCase(E_WALLET)) {
            this.metodePembayaran = E_WALLET;
        } else if (metodePembayaran.equalsIgnoreCase(SALDO)) {
            this.metodePembayaran = SALDO;
        } else {
            throw new IllegalArgumentException("Metode pembayaran harus " + CASH + ", "
                    + E_WALLET + ", atau " + SALDO);
        }
    }
    
    public void setStatusTransaksi(String statusTransaksi) {
        if (statusTransaksi == null) {
            throw new IllegalArgumentException("Status transaksi tidak boleh kosong");
        }
        if (statusTransaksi.equalsIgnoreCase(BERHASIL)) {
            this.statusTransaksi = BERHASIL;
        } else if (statusTransaksi.equalsIgnoreCase(GAGAL)) {
            this.statusTransaksi = GAGAL;
        } else {
            throw new IllegalArgumentException("Status transaksi harus " + BERHASIL + " atau " + GAGAL);
        }
    }
    
    public void setWaktuTransaksi(Date waktuTransaksi) {
        if (waktuTransaksi == null) {
            throw new IllegalArgumentException("Waktu transaksi tidak boleh kosong");
        }
        this.waktuTransaksi = waktuTransaksi;
    }
    
    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ID user harus lebih dari 0");
        }
        this.userId = userId;
    }
    
    public void setPesananId(Integer pesananId) {
        if (pesananId != null && pesananId <= 0) {
            throw new IllegalArgumentException("ID pesanan harus lebih dari 0");
        }
        this.pesananId = pesananId;
    }
}
