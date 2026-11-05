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
        this.tingkatAkses = tingkatAkses;
    }
    public void setBagianDivisi(String bagianDivisi) {
        this.bagianDivisi = bagianDivisi;
    }
}