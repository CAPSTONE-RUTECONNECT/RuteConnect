/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Pelanggan extends User {
    private String alamatFavorit;
    private double saldoWallet;
    
    public Pelanggan() {
    
    }
    
    @Override
    public String getJenisUser() {
        return "Pelanggan";
    }
    
    public Pelanggan(int userId, String nama, String noTelepon, String email, String password, String role, String alamatFavorit, double saldoWallet) {
        super(userId, nama, noTelepon, email, password, role);
        this.alamatFavorit = alamatFavorit;
        this.saldoWallet = saldoWallet;
    }
    
    public String getAlamatFavorit() {
        return alamatFavorit;
    }
    public double getSaldoWallet() {
        return saldoWallet;
    }
    public void setAlamatFavorit(String alamatFavorit) {
        if (alamatFavorit == null || alamatFavorit.trim().isEmpty()) {
            this.alamatFavorit = null;
            return;
        }
        alamatFavorit = alamatFavorit.trim();
        if (alamatFavorit.length() < 5) {
            throw new IllegalArgumentException("Alamat favorit minimal 5 karakter");
        }
        if (alamatFavorit.length() > 250) {
            throw new IllegalArgumentException("Alamat favorit maksimal 250 karakter");
        }
        this.alamatFavorit = alamatFavorit;
    }
    public void setSaldoWallet(double saldoWallet) {
        if (Double.isNaN(saldoWallet) || Double.isInfinite(saldoWallet) || saldoWallet < 0) {
            throw new IllegalArgumentException("Saldo wallet tidak boleh negatif");
        }
        this.saldoWallet = saldoWallet;
    }
}
