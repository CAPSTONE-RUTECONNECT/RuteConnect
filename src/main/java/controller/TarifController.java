/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class TarifController {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ruteconnect?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
            "root", "");
    }

    // baris: {id, nama_layanan, jenis_kendaraan, kelas_layanan, tarif_per_km}
    public List<Object[]> cariLayanan(String keyword, String jenis) throws SQLException {
        List<Object[]> hasil = new ArrayList<>();
        String kw = (keyword == null ? "" : keyword.trim());
        String like = "%" + kw + "%";

        String sql = "SELECT layanan_id, nama_layanan, jenis_kendaraan, kelas_layanan, tarif_per_km "
                   + "FROM layanan "
                   + "WHERE (CAST(layanan_id AS CHAR) = ? OR nama_layanan LIKE ? "
                   + "OR jenis_kendaraan LIKE ? OR kelas_layanan LIKE ?) "
                   + "AND (? = 'Semua Kendaraan' OR jenis_kendaraan = ?) "
                   + "ORDER BY layanan_id";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kw);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ps.setString(5, jenis);
            ps.setString(6, jenis);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new Object[]{
                        rs.getInt("layanan_id"),
                        rs.getString("nama_layanan"),
                        rs.getString("jenis_kendaraan"),
                        rs.getString("kelas_layanan"),
                        rs.getBigDecimal("tarif_per_km")
                    });
                }
            }
        }
        return hasil;
    }

    public void tambahLayanan(String nama, String jenis, String kelas, BigDecimal tarif) throws SQLException {
        try (Connection conn = getConnection()) {
            int id;
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT IFNULL(MAX(layanan_id), 0) + 1 FROM layanan")) {
                rs.next();
                id = rs.getInt(1);
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO layanan(layanan_id, nama_layanan, jenis_kendaraan, kelas_layanan, tarif_per_km) VALUES (?,?,?,?,?)")) {
                ps.setInt(1, id);
                ps.setString(2, nama);
                ps.setString(3, jenis);
                ps.setString(4, kelas);
                ps.setBigDecimal(5, tarif);
                ps.executeUpdate();
            }
        }
    }

    public void updateLayanan(int id, String nama, String jenis, String kelas, BigDecimal tarif) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "UPDATE layanan SET nama_layanan=?, jenis_kendaraan=?, kelas_layanan=?, tarif_per_km=? WHERE layanan_id=?")) {
            ps.setString(1, nama);
            ps.setString(2, jenis);
            ps.setString(3, kelas);
            ps.setBigDecimal(4, tarif);
            ps.setInt(5, id);
            ps.executeUpdate();
        }
    }

    public void hapusLayanan(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM layanan WHERE layanan_id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
