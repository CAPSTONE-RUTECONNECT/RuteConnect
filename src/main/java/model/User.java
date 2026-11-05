/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public abstract class User {
    private int userId;
    private String nama;
    private String noTelepon;
    private String email;
    private String password;
    private String role;
    
    public User(){
        
    }
    
    public abstract String getJenisUser();
    
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
        if (nama == null || !nama.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama hanya boleh huruf dan spasi");
        }
        this.nama = nama.trim();
    }
    public void setNoTelepon(String noTelepon) {
        if (noTelepon == null || !noTelepon.matches("\\d{1,13}")) {
            throw new IllegalArgumentException("No telepon hanya angka, maksimal 13 digit");
        }
        this.noTelepon = noTelepon;
    }
    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("Format email tidak valid");
        }
        this.email = email;
    }
    public void setPassword(String password) {
        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            throw new IllegalArgumentException("Password harus mengandung huruf dan angka");
        }
        this.password = password;
    }
    public void setRole(String role) {
        if (role == null || !(role.equalsIgnoreCase("pelanggan") || role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("driver"))) {
            throw new IllegalArgumentException("Role harus pelanggan, admin, atau driver");
        }
        this.role = role.toLowerCase();
    }
}
