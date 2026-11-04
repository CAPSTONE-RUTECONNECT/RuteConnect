/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.*;
import config.Koneksi;

/**
 *
 * @author HP
 */
public class AuthService {
    public enum LoginResult {
        BERHASIL, INPUT_KOSONG, SALAH, ERROR
    }
    public enum RegisterResult {
        BERHASIL, DATA_KOSONG, FORMAT_SALAH, EMAIL_TERDAFTAR, ERROR
    }
    
    public static LoginResult login(String email, String password) {
        if (Validator.isKosong(email, password)) {
            return LoginResult.INPUT_KOSONG;
        }
        String sql = "SELECT user_id, nama, email, role FROM user WHERE email = ? AND password = ?";
        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email.trim());
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Session.userId = rs.getInt("user_id");
                    Session.nama = rs.getString("nama");
                    Session.email = rs.getString("email");
                    Session.role = rs.getString("role");
                    return LoginResult.BERHASIL;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return LoginResult.ERROR;
        }
        return LoginResult.SALAH;
    }
    
    public static RegisterResult register(String nama, String email, String telp, String password) {
        if (Validator.isKosong(nama, email, telp, password)) {
            return RegisterResult.DATA_KOSONG;
        }
        if (!Validator.emailValid(email.trim()) || !Validator.passwordValid(password) || !Validator.teleponValid(telp.trim())) {
            return RegisterResult.FORMAT_SALAH;
        }
        try (Connection c = Koneksi.getConnection()) {
            c.setAutoCommit(false);
            try {
                try (PreparedStatement cek = c.prepareStatement(
                "SELECT 1 FROM user WHERE email = ?")) {
                    cek.setString(1, email.trim());
                    if (cek.executeQuery().next()) {
                        c.rollback();
                        return RegisterResult.EMAIL_TERDAFTAR;
                    }
                }
                int newId;
                try (PreparedStatement mx = c.prepareStatement (
                "SELECT IFNULL(MAX(user_id), 0) + 1 FROM user FOR UPDATE");
                        ResultSet rs = mx.executeQuery()) {
                    rs.next();
                    newId = rs.getInt(1);
                }
                try (PreparedStatement ins = c.prepareStatement(
                "INSERT INTO user(user_id, nama, no_telepon, email, password, role)"
                + "VALUES (?, ?, ?, ?, ?, 'Pelanggan')")) {
                    ins.setInt(1, newId);
                    ins.setString(2, nama.trim());
                    ins.setString(3, telp.trim());
                    ins.setString(4, email.trim());
                    ins.setString(5, password);
                    ins.executeUpdate();
                }
                try (PreparedStatement pel = c.prepareStatement(
                "INSERT INTO pelanggan(user_id, saldo_wallet) VALUES (?, 0)")) {
                    pel.setInt(1, newId);
                    pel.executeUpdate();
                }
                c.commit();
                return RegisterResult.BERHASIL;
            } catch (SQLException e) {
                c.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return RegisterResult.ERROR;
        }
    }
        
    public static void logout() {
        Session.clear();
    }
}
