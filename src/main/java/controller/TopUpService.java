/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.*;
import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class TopUpService {
    private Connection connection;
    
    public TopUpService(Connection connection) {
        this.connection = connection;
    }
    
    public boolean executeTopUp(String strNominal) {
        try {
            if (strNominal == null || strNominal.trim().isEmpty()) {
                return false;
            }
            BigDecimal nominal = new BigDecimal(strNominal.trim());
            if (nominal.compareTo(BigDecimal.ZERO) <= 0) {
                return false;
            }
            return prosesTopUp(Session.userId, nominal);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean prosesTopUp(int pelangganId, BigDecimal nominal) {
        if (nominal == null
                || nominal.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        String cariId = "SELECT IFNULL(MAX(transaksi_id), 0) + 1 FROM transaksi FOR UPDATE";
        String insertTransaksi = "INSERT INTO transaksi (transaksi_id, jenis_transaksi, harga, biaya_lainnya, jumlah_transaksi, "
                + "metode_pembayaran, status_transaksi, waktu_transaksi, user_id) "
                + "VALUES (?, 'Top Up', ?, 0.00, ?, 'E-Wallet', 'Berhasil', CURDATE(), ?)";
        String insertTopUp = "INSERT INTO top_up (transaksi_id, saldo_masuk, saldo_keluar, status_top_up, pelanggan_id) "
                + "VALUES (?, ?, 0.00, 'Berhasil', ?)";
        String updatePelanggan = "UPDATE pelanggan SET saldo_wallet = saldo_wallet + ? WHERE user_id = ?";
        
        try {
            connection.setAutoCommit(false);
            int transaksiId;
            try (PreparedStatement ps = connection.prepareStatement(cariId);
                    ResultSet rs = ps.executeQuery()) {
                rs.next();
                transaksiId = rs.getInt(1);
            }
            try (PreparedStatement stmtT = connection.prepareStatement(insertTransaksi)) {
                stmtT.setInt(1, transaksiId);
                stmtT.setBigDecimal(2, nominal);
                stmtT.setBigDecimal(3, nominal);
                stmtT.setInt(4, pelangganId);
                stmtT.executeUpdate();
            }
            try (PreparedStatement stmtU = connection.prepareStatement(insertTopUp)) {
                stmtU.setInt(1, transaksiId);
                stmtU.setBigDecimal(2, nominal);
                stmtU.setInt(3, pelangganId);
                stmtU.executeUpdate();
            }
            try (PreparedStatement stmtP = connection.prepareStatement(updatePelanggan)) {
                stmtP.setBigDecimal(1, nominal);
                stmtP.setInt(2, pelangganId);
                stmtP.executeUpdate();
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
