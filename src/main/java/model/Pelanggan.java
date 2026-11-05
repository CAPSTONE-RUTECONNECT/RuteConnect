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
        this.alamatFavorit = alamatFavorit;
    }
    public void setSaldoWallet(double saldoWallet) {
        this.saldoWallet = saldoWallet;
    }
}
