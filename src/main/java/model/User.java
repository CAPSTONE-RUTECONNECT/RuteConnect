/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class User {
    private int userId;
    private String nama;
    private String noTelepon;
    private String email;
    private String password;
    private String role;
    
    public User(){
        
    }
    
    public User(int userId, String nama, String noTelepon, String email, String password, String role) {
        this.userId = userId;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public int getUserId() {
        return userId;
    }
    public String getNama() {
        return nama;
    }
    public String getNoTelepon() {
        return noTelepon;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
