/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashSet;
import java.util.Set;
import model.Pelanggan;
import model.Pesanan;
import model.User;

/**
 *
 * @author HP
 */
public class Session {
    public static User user;
    public static int userId;
    public static String nama, email, role;
    public static double saldo;

    public static Pesanan draft;
    public static String asal;
    public static String kembaliKe;
    public static String ringkasanJudul;
    public static String ringkasanSub;
    public static int ringkasanMenit;
    public static final Set<Integer> ditolak = new HashSet<>();

    public static void set(User u) {
        user = u;
        userId = u.getUserId();
        nama = u.getNama();
        email = u.getEmail();
        role = u.getRole();
        if (u instanceof Pelanggan) {
            saldo = ((Pelanggan) u).getSaldoWallet();
        } else {
            saldo = 0.0;
        }
    }
    
    public static void clear() {
        user = null;
        userId = 0;
        nama = null;
        email = null;
        role = null;
        saldo = 0.0;

        draft = null;
        asal = null;
        kembaliKe = null;
        ringkasanJudul = null;
        ringkasanSub = null;
        ringkasanMenit = 0;
        ditolak.clear();
    }
    
    public static void refreshSaldo() {
        if (userId <= 0) return;

        String sql = "SELECT saldo_wallet FROM pelanggan WHERE user_id = ?";
        try (java.sql.Connection c = config.Koneksi.getConnection();
             java.sql.PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getDouble("saldo_wallet");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
