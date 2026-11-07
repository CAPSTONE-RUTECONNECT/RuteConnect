/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class TopUp {
    private int topUpId;
    private double saldoMasuk;
    private double saldoKeluar;
    private String statusTopUp;
    private int userId;
    private int transaksiId;

    public int getTopUpId() {
        return topUpId;
    }
    public double getSaldoMasuk() {
        return saldoMasuk;
    }
    public double getSaldoKeluar() {
        return saldoKeluar;
    }
    public String getStatusTopUp() {
        return statusTopUp;
    }
    public int getUserId() {
        return userId;
    }
    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTopUpId(int topUpId) {
        if (topUpId < 0) {
            throw new IllegalArgumentException("ID top up tidak boleh negatif");
        }
        this.topUpId = topUpId;
    }
    public void setSaldoMasuk(double saldoMasuk) {
        if (Double.isNaN(saldoMasuk) || Double.isInfinite(saldoMasuk) || saldoMasuk < 0) {
            throw new IllegalArgumentException("Saldo masuk tidak boleh negatif");
        }
        this.saldoMasuk = saldoMasuk;
    }
    public void setSaldoKeluar(double saldoKeluar) {
        if (Double.isNaN(saldoKeluar) || Double.isInfinite(saldoKeluar) || saldoKeluar < 0) {
            throw new IllegalArgumentException("Saldo keluar tidak boleh negatif");
        }
        this.saldoKeluar = saldoKeluar;
    }
    public void setStatusTopUp(String statusTopUp) {
        if (statusTopUp == null) {
            throw new IllegalArgumentException("Status top up tidak boleh kosong");
        }
        String s = statusTopUp.trim();
        if (s.equalsIgnoreCase("pending")) {
            this.statusTopUp = "Pending";
        } else if (s.equalsIgnoreCase("berhasil")) {
            this.statusTopUp = "Berhasil";
        } else if (s.equalsIgnoreCase("gagal")) {
            this.statusTopUp = "Gagal";
        } else {
            throw new IllegalArgumentException("Status top up harus pending, berhasil, atau gagal");
        }
    }
    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("ID user harus lebih dari 0");
        }
        this.userId = userId;
    }
    public void setTransaksiId(int transaksiId) {
        if (transaksiId <= 0) {
            throw new IllegalArgumentException("ID transaksi harus lebih dari 0");
        }
        this.transaksiId = transaksiId;
    }
}