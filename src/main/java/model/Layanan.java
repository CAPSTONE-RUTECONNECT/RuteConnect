/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Layanan {
    private int layananId;
    private String namaLayanan;
    private String jenisKendaraan;
    private String kelasLayanan;
    private double tarifPerKm;
    
    public Layanan() {
        
    }
    
    public Layanan(int layananId, String namaLayanan, String jenisKendaraan, String kelasLayanan, double tarifPerKm) {
        this.layananId = layananId;
        this.namaLayanan = namaLayanan;
        this.jenisKendaraan = jenisKendaraan;
        this.kelasLayanan = kelasLayanan;
        this.tarifPerKm = tarifPerKm;
    }
    
    public double hitungTarif(double jarakKm) {
        return tarifPerKm * jarakKm;
    }
    
    public int getLayananId() {
        return layananId;
    }
    public String getNamaLayanan() {
        return namaLayanan;
    }
    public String getJenisKendaraan() {
        return jenisKendaraan;
    }
    public String getKelasLayanan() {
        return kelasLayanan;
    }
    public double getTarifPerKm() {
        return tarifPerKm;
    }
    public void setLayananId(int layananId) {
        this.layananId = layananId;
    }
    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }
    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }
    public void setKelasLayanan(String kelasLayanan) {
        this.kelasLayanan = kelasLayanan;
    }
    public void setTarifPerKm(double tarifPerKm) {
        this.tarifPerKm = tarifPerKm;
    }
    
    @Override
    public String toString() {
        return namaLayanan;
    }
}