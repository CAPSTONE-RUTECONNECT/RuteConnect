/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.*;

/**
 *
 * @author HP
 */
public class UserDao extends BaseDao<User> {
    @Override
    protected User mapRow(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        User u = "Admin".equals(role) ? new Admin() : "Driver".equals(role) ? new Driver() : new Pelanggan();
        u.setUserId(rs.getInt("user_id"));
        u.setNama(rs.getString("nama"));
        u.setNoTelepon(rs.getString("no_telepon"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRole(role);
        return u;
    }
    public User login(String email, String password) {
        User dasar = queryOne("SELECT * FROM user WHERE email = ? AND BINARY password = ?", email, password);
        if (dasar == null) return null;
        User lengkap;
        switch (dasar.getRole()) {
            case "Pelanggan": lengkap = new PelangganDao().findById(dasar.getUserId());
            break;
            case "Driver":    lengkap = new DriverDao().findById(dasar.getUserId());
            break;
            default:          lengkap = muatAdmin(dasar);
        }
        return lengkap != null ? lengkap : dasar;
    }
    private Admin muatAdmin(User d) {
        Admin a = new Admin(d.getUserId(), d.getNama(), d.getNoTelepon(), d.getEmail(), d.getPassword(), d.getRole(), "", "");
        try (PreparedStatement ps = conn().prepareStatement("SELECT tingkat_akses, bagian_divisi FROM admin WHERE user_id = ?")) {
            ps.setInt(1, d.getUserId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a.setTingkatAkses(rs.getString(1)); a.setBagianDivisi(rs.getString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    public boolean emailTerdaftar(String email, int kecualiUserId) {
        return queryOne("SELECT * FROM user WHERE email = ? AND user_id <> ?", email, kecualiUserId) != null;
    }
    public boolean cekPassword(int userId, String password) {
        return queryOne("SELECT * FROM user WHERE user_id = ? AND BINARY password = ?", userId, password) != null;
    }
    @Override
    public boolean insert(User u) {
        int id = nextId("user", "user_id");
        u.setUserId(id);
        return exec("INSERT INTO user(user_id, nama, no_telepon, email, password, role) VALUES (?,?,?,?,?,?)",
                id, u.getNama(), u.getNoTelepon(), u.getEmail(), u.getPassword(), u.getRole());
    }
    @Override
    public boolean update(User u) {
        return exec("UPDATE user SET nama=?, no_telepon=?, email=?, password=? WHERE user_id=?",
                u.getNama(), u.getNoTelepon(), u.getEmail(), u.getPassword(), u.getUserId());
    }
    @Override public boolean delete(int id) {
        return exec("DELETE FROM user WHERE user_id = ?", id);
    }
    @Override public User findById(int id) {
        return queryOne("SELECT * FROM user WHERE user_id = ?", id);
    }
    @Override public List<User> findAll() {
        return query("SELECT * FROM user ORDER BY user_id");
    }
}
