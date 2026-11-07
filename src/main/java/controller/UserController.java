/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class UserController {
    public List<Object[]> cariUser(String keyword, String role) throws SQLException {
        List<Object[]> hasil = new ArrayList<>();

        String kw = (keyword == null ? "" : keyword.trim());
        String like = "%" + kw + "%";

        String sql = "SELECT u.user_id, u.nama, u.email, u.no_telepon, u.role, "
                   + "CASE WHEN u.role = 'Driver' THEN d.status_aktif ELSE 'Aktif' END AS status "
                   + "FROM `user` u LEFT JOIN driver d ON d.user_id = u.user_id "
                   + "WHERE (CAST(u.user_id AS CHAR) = ? OR u.nama LIKE ? OR u.no_telepon LIKE ? OR u.email LIKE ?) "
                   + "AND (? = 'Semua Role' OR u.role = ?) "
                   + "ORDER BY u.user_id";

        Connection conn = Koneksi.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kw);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ps.setString(5, role);
            ps.setString(6, role);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new Object[]{
                        rs.getInt("user_id"),
                        rs.getString("nama"),
                        rs.getString("email"),
                        rs.getString("no_telepon"),
                        rs.getString("status"),
                        rs.getString("role")
                    });
                }
            }
        }
        return hasil;
    }

    public void tambahUser(String nama, String telp, String email, String password,
            String role, String... extra) throws SQLException {

        Connection conn = Koneksi.getConnection();
        conn.setAutoCommit(false);

        try {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT 1 FROM `user` WHERE email = ?")) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) throw new SQLException("Email sudah terdaftar.");
                }
            }

            int id;
            try (Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT IFNULL(MAX(user_id), 0) + 1 FROM `user`")) {
                rs.next();
                id = rs.getInt(1);
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO `user`(user_id, nama, no_telepon, email, password, role) VALUES (?,?,?,?,?,?)")) {
                ps.setInt(1, id);
                ps.setString(2, nama);
                ps.setString(3, telp);
                ps.setString(4, email);
                ps.setString(5, password);
                ps.setString(6, role);
                ps.executeUpdate();
            }

            switch (role) {
                case "Pelanggan" -> {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO pelanggan(user_id, alamat_favorit, saldo_wallet) VALUES (?,?,0)")) {
                        ps.setInt(1, id);
                        ps.setString(2, extra[0]);
                        ps.executeUpdate();
                    }
                }
                case "Driver" -> {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO driver(user_id, merk_kendaraan, no_plat_kendaraan, jenis_kendaraan, status_aktif) VALUES (?,?,?,?,?)")) {
                        ps.setInt(1, id);
                        ps.setString(2, extra[0]);
                        ps.setString(3, extra[1]);
                        ps.setString(4, extra[2]);
                        ps.setString(5, extra[3]);
                        ps.executeUpdate();
                    }
                }
                case "Admin" -> {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO admin(user_id, tingkat_akses, bagian_divisi) VALUES (?,?,?)")) {
                        ps.setInt(1, id);
                        ps.setString(2, extra[0]);
                        ps.setString(3, extra[1]);
                        ps.executeUpdate();
                    }
                }
                default -> throw new SQLException("Role tidak dikenal: " + role);
            }

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void hapusUser(int id) throws SQLException {
        Connection conn = Koneksi.getConnection();
        conn.setAutoCommit(false);

        try {
            for (String t : new String[]{"admin", "driver", "pelanggan"}) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM " + t + " WHERE user_id = ?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM `user` WHERE user_id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public Object[] ambilUser(int id) throws SQLException {
        String sql = "SELECT u.nama, u.no_telepon, u.email, u.role, d.status_aktif "
                   + "FROM `user` u LEFT JOIN driver d ON d.user_id = u.user_id "
                   + "WHERE u.user_id = ?";

        Connection conn = Koneksi.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                        rs.getString("nama"),
                        rs.getString("no_telepon"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("status_aktif")
                    };
                }
            }
        }
        return null;
    }

    public void updateUser(int id, String nama, String telp, String email, String statusDriver) throws SQLException {
        Connection conn = Koneksi.getConnection();
        conn.setAutoCommit(false);

        try {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT 1 FROM `user` WHERE email = ? AND user_id <> ?")) {
                ps.setString(1, email);
                ps.setInt(2, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) throw new SQLException("Email sudah dipakai user lain.");
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE `user` SET nama = ?, no_telepon = ?, email = ? WHERE user_id = ?")) {
                ps.setString(1, nama);
                ps.setString(2, telp);
                ps.setString(3, email);
                ps.setInt(4, id);
                ps.executeUpdate();
            }

            if (statusDriver != null) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "UPDATE driver SET status_aktif = ? WHERE user_id = ?")) {
                    ps.setString(1, statusDriver);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }
            }

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
