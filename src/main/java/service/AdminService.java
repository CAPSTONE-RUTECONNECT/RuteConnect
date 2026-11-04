/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.sql.*;
import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class AdminService {
    private Connection connection;
    
    public AdminService(Connection connection) {
        this.connection = connection;
    }
    
    public boolean tambahLayanan(String namaLayanan, String jenisKendaraan, String kelasLayanan, BigDecimal tarifPerKm) {
        String query = "INSERT INTO layanan (nama_layanan, jenis_kendaraan, kelas_layanan, tarif_per_km) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, namaLayanan);
            stmt.setString(2, jenisKendaraan);
            stmt.setString(3, kelasLayanan);
            stmt.setBigDecimal(4, tarifPerKm);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateLayanan(int layananId, String namaLayanan, BigDecimal tarifPerKm) {
        String query = "UPDATE layanan SET nama_layanan = ?, tarif_per_km = ? WHERE layanan_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, namaLayanan);
            stmt.setBigDecimal(2, tarifPerKm);
            stmt.setInt(3, layananId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean hapusLayanan(int layananId) {
        String query = "DELETE FROM layanan WHERE layanan_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, layananId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateStatusDriver(int userId, String statusAktif) {
        String query = "UPDATE driver SET status_aktif = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, statusAktif);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean hapusUserDriver(int userId) {
        // Karena ada relasi foreign key, hapus dari tabel driver dulu baru ke user
        String deleteDriver = "DELETE FROM driver WHERE user_id = ?";
        String deleteUser = "DELETE FROM user WHERE user_id = ?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmtD = connection.prepareStatement(deleteDriver)) {
                stmtD.setInt(1, userId);
                stmtD.executeUpdate();
            }
            try (PreparedStatement stmtU = connection.prepareStatement(deleteUser)) {
                stmtU.setInt(1, userId);
                stmtU.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
