/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Driver extends User {
    private String merkKendaraan;
    protected String noPlatKendaraan;
    private String jenisKendaraan;
    private String statusAktif;
    
    public Driver() {
        
    }
    
    @Override
    public String getJenisUser() {
        return "Driver";
    }

    public Driver(int userId, String nama, String noTelepon, String email, String password, String role, String merkKendaraan, String noPlatKendaraan, String jenisKendaraan, String statusAktif) {
        super(userId, nama, noTelepon, email, password, role);
        this.merkKendaraan = merkKendaraan;
        this.noPlatKendaraan = noPlatKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.statusAktif = statusAktif;
    }
    
    public String getMerkKendaraan() {
        return merkKendaraan;
    }
    public String getNoPlatKendaraan() {
        return noPlatKendaraan;
    }
    public String getJenisKendaraan() {
        return jenisKendaraan;
    }
    public String getStatusAktif() {
        return statusAktif;
    }
    public void setMerkKendaraan(String merkKendaraan) {
        if (merkKendaraan == null || !merkKendaraan.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Merk kendaraan hanya boleh huruf dan spasi");
        }
        this.merkKendaraan = merkKendaraan.trim();
    }
    public void setNoPlatKendaraan(String noPlatKendaraan) {
        if (noPlatKendaraan == null || !noPlatKendaraan.trim().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9 ]+$")) {
            throw new IllegalArgumentException("No plat harus mengandung huruf dan angka");
        }
        this.noPlatKendaraan = noPlatKendaraan.trim().toUpperCase();
    }
    public void setJenisKendaraan(String jenisKendaraan) {
        if (jenisKendaraan == null) {
            throw new IllegalArgumentException("Jenis kendaraan tidak boleh kosong");
        }
        String j = jenisKendaraan.trim();
        if (j.equalsIgnoreCase("motor")) {
            this.jenisKendaraan = "Motor";
        } else if (j.equalsIgnoreCase("mobil")) {
            this.jenisKendaraan = "Mobil";
        } else {
            throw new IllegalArgumentException("Jenis kendaraan harus motor atau mobil");
        }
    }
    public void setStatusAktif(String statusAktif) {
        if (statusAktif == null) {
            throw new IllegalArgumentException("Status tidak boleh kosong");
        }
        String s = statusAktif.trim();
        if (s.equalsIgnoreCase("aktif")) {
            this.statusAktif = "Aktif";
        } else if (s.equalsIgnoreCase("non-aktif")) {
            this.statusAktif = "Non-Aktif";
        } else {
            throw new IllegalArgumentException("Status harus aktif atau non-aktif");
        }
    }
    public boolean isAktif() {
        return "Aktif".equalsIgnoreCase(statusAktif);
    }
}
