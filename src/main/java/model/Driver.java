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
        this.merkKendaraan = merkKendaraan;
    }
    public void setNoPlatKendaraan(String noPlatKendaraan) {
        this.noPlatKendaraan = noPlatKendaraan;
    }
    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }
    public void setStatusAktif(String statusAktif) {
        this.statusAktif = statusAktif;
    }
    public boolean isAktif() {
        return "Aktif".equalsIgnoreCase(statusAktif);
    }
}
