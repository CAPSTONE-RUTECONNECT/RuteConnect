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
public class OrderService {
    private Connection connection;
    
    public OrderService(Connection connection) {
        this.connection = connection;
    }
    
    public BigDecimal hitungTarif(int layananId, double jarakKm) throws SQLException {
        String query = "SELECT tarif_per_km FROM layanan WHERE layanan_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, layananId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal tarifPerKm = rs.getBigDecimal("tarif_per_km");
                    return tarifPerKm.multiply(BigDecimal.valueOf(jarakKm));
                }
            }
        }
        throw new IllegalArgumentException("Layanan dengan ID " + layananId + " tidak ditemukan.");
    }
    
    public boolean simpanPesananTransportasi(int pelangganId, int layananId, String lokasiJemput, String lokasiTujuan, double jarakKm, BigDecimal totalBiaya, int jumlahPenumpang, String catatan, int transaksiId) {
        String insertPesanan = "INSERT INTO pesanan (lokasi_jemput, lokasi_tujuan, jarak_km, total_biaya, " +
                               "waktu_pesanan, status_pesanan, pelanggan_id, layanan_id, transaksi_id, jenis_pesanan, catatan) " +
                               "VALUES (?, ?, ?, ?, CURDATE(), 'Menunggu', ?, ?, ?, 'Transportasi', ?)";
        String insertTransportasi = "INSERT INTO transportasi (pesanan_id, jumlah_penumpang, status_penjemputan) VALUES (?, ?, 'Menunggu')";
        try {
            connection.setAutoCommit(false);

            long pesananId = -1;
            try (PreparedStatement stmtPesanan = connection.prepareStatement(insertPesanan, Statement.RETURN_GENERATED_KEYS)) {
                stmtPesanan.setString(1, lokasiJemput);
                stmtPesanan.setString(2, lokasiTujuan);
                stmtPesanan.setDouble(3, jarakKm);
                stmtPesanan.setBigDecimal(4, totalBiaya);
                stmtPesanan.setInt(5, pelangganId);
                stmtPesanan.setInt(6, layananId);
                stmtPesanan.setInt(7, transaksiId);
                stmtPesanan.setString(8, catatan);
                stmtPesanan.executeUpdate();

                try (ResultSet rs = stmtPesanan.getGeneratedKeys()) {
                    if (rs.next()) {
                        pesananId = rs.getLong(1);
                    }
                }
            }
            try (PreparedStatement stmtTrans = connection.prepareStatement(insertTransportasi)) {
                stmtTrans.setLong(1, pesananId);
                stmtTrans.setInt(2, jumlahPenumpang);
                stmtTrans.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) {
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
    
    public boolean batalkanPesanan(int pesananId, String metodePembayaran, int pelangganId, BigDecimal totalBiaya, String alasanBatal) {
        String updatePesanan = "UPDATE pesanan SET status_pesanan = 'Dibatalkan', alasan_batal = ? WHERE pesanan_id = ?";
        String refundWallet = "UPDATE pelanggan SET saldo_wallet = saldo_wallet + ? WHERE user_id = ?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmtP = connection.prepareStatement(updatePesanan)) {
                stmtP.setString(1, alasanBatal);
                stmtP.setInt(2, pesananId);
                stmtP.executeUpdate();
            }
            if ("E-Wallet".equalsIgnoreCase(metodePembayaran) || "Saldo".equalsIgnoreCase(metodePembayaran)) {
                try (PreparedStatement stmtW = connection.prepareStatement(refundWallet)) {
                    stmtW.setBigDecimal(1, totalBiaya);
                    stmtW.setInt(2, pelangganId);
                    stmtW.executeUpdate();
                }
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
