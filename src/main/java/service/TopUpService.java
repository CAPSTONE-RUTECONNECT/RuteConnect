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
public class TopUpService {
    private Connection connection;
    
    public TopUpService(Connection connection) {
        this.connection = connection;
    }
    
    boolean prosesTopUp(int pelangganId, BigDecimal nominal) {
        String insertTransaksi = "INSERT INTO transaksi (jenis_transaksi, harga, biaya_lainnya, jumlah_transaksi, " +
                                 "metode_pembayaran, status_transaksi, waktu_transaksi, user_id) " +
                                 "VALUES ('Top Up', ?, 0.00, ?, 'E-Wallet', 'Berhasil', CURDATE(), ?)";
        String insertTopUp = "INSERT INTO top_up (transaksi_id, saldo_masuk, saldo_keluar, status_top_up, pelanggan_id) " +
                             "VALUES (?, ?, 0.00, 'Berhasil', ?)";
        String updatePelanggan = "UPDATE pelanggan SET saldo_wallet = saldo_wallet + ? WHERE user_id = ?";

        try {
            connection.setAutoCommit(false);
            long transaksiId = -1;
            try (PreparedStatement stmtT = connection.prepareStatement(insertTransaksi, Statement.RETURN_GENERATED_KEYS)) {
                stmtT.setBigDecimal(1, nominal);
                stmtT.setBigDecimal(2, nominal);
                stmtT.setInt(3, pelangganId);
                stmtT.executeUpdate();
                try (ResultSet rs = stmtT.getGeneratedKeys()) {
                    if (rs.next()) {
                        transaksiId = rs.getLong(1);
                    }
                }
            }
            try (PreparedStatement stmtU = connection.prepareStatement(insertTopUp)) {
                stmtU.setLong(1, transaksiId);
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
