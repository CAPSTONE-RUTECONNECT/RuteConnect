/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import util.JarakHelper;

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
    
    public double hitungTarif(String lokasiJemput, String lokasiTujuan) {
        double km = JarakHelper.hitungKm(lokasiJemput, lokasiTujuan);
        return km < 0 ? -1 : hitungTarif(km);
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
        if (layananId < 0) {
            throw new IllegalArgumentException("ID layanan tidak boleh negatif");
        }
        this.layananId = layananId;
    }
    public void setNamaLayanan(String namaLayanan) {
        if (namaLayanan == null || namaLayanan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama layanan tidak boleh kosong");
        }
        this.namaLayanan = namaLayanan.trim();
    }
    public void setJenisKendaraan(String jenisKendaraan) {
        if (jenisKendaraan == null
                || !(jenisKendaraan.equalsIgnoreCase("motor")
                || jenisKendaraan.equalsIgnoreCase("mobil"))) {
            throw new IllegalArgumentException("Jenis kendaraan harus motor atau mobil");
        }
        this.jenisKendaraan = jenisKendaraan.toLowerCase();
    }
    public void setKelasLayanan(String kelasLayanan) {
        if (kelasLayanan == null
                || !(kelasLayanan.equalsIgnoreCase("ekonomi")
                || kelasLayanan.equalsIgnoreCase("reguler")
                || kelasLayanan.equalsIgnoreCase("premium"))) {
            throw new IllegalArgumentException("Kelas layanan harus ekonomi, reguler, atau premium");
        }
        this.kelasLayanan = kelasLayanan.toLowerCase();
    }
    public void setTarifPerKm(double tarifPerKm) {
        if (Double.isNaN(tarifPerKm) || Double.isInfinite(tarifPerKm) || tarifPerKm <= 0) {
            throw new IllegalArgumentException("Tarif per km harus lebih dari 0");
        }
        this.tarifPerKm = tarifPerKm;
    }
    
    @Override
    public String toString() {
        return namaLayanan;
    }
}