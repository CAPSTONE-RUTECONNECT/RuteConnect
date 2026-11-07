/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Admin extends User {
    private String tingkatAkses;
    private String bagianDivisi;
    
    public Admin() {
        
    }
    
    @Override
    public String getJenisUser() {
        return "Admin";
    }
    
    public Admin(int userId, String nama, String noTelepon, String email, String password, String role, String tingkatAkses, String bagianDivisi) {
        super(userId, nama, noTelepon, email, password, role);
        this.tingkatAkses = tingkatAkses;
        this.bagianDivisi = bagianDivisi;
    }
    
    public String getTingkatAkses() {
        return tingkatAkses;
    }
    public String getBagianDivisi() {
        return bagianDivisi;
    }
    public void setTingkatAkses(String tingkatAkses) {
        if (tingkatAkses == null || !tingkatAkses.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Tingkat akses hanya boleh huruf dan spasi");
        }
        this.tingkatAkses = tingkatAkses.trim();
    }
    public void setBagianDivisi(String bagianDivisi) {
        if (bagianDivisi == null || !bagianDivisi.trim().matches("[a-zA-Z &]+")) {
            throw new IllegalArgumentException("Bagian divisi hanya boleh huruf, spasi, dan &");
        }
        bagianDivisi = bagianDivisi.trim();
        if (bagianDivisi.length() < 3) {
            throw new IllegalArgumentException("Bagian divisi minimal 3 karakter");
        }
        if (bagianDivisi.length() > 50) {
            throw new IllegalArgumentException("Bagian divisi maksimal 50 karakter");
        }
        this.bagianDivisi = bagianDivisi;
    }
}
