/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Driver;
import util.Tx;

/**
 *
 * @author HP
 */
public class DriverDao extends BaseDao<Driver> {
    private static final String SELECT =
        "SELECT u.user_id, u.nama, u.no_telepon, u.email, u.password, u.role, "
        + "d.merk_kendaraan, d.no_plat_kendaraan, d.jenis_kendaraan, d.status_aktif "
        + "FROM user u JOIN driver d ON u.user_id = d.user_id";
    @Override
    protected Driver mapRow(ResultSet rs) throws SQLException {
        return new Driver(rs.getInt("user_id"), rs.getString("nama"), rs.getString("no_telepon"),
                rs.getString("email"), rs.getString("password"), rs.getString("role"),
                rs.getString("merk_kendaraan"), rs.getString("no_plat_kendaraan"),
                rs.getString("jenis_kendaraan"), rs.getString("status_aktif"));
    }
    @Override
    public boolean insert(Driver d) {
        final int id = nextId("user", "user_id");
        if (id < 0) return false;
        d.setUserId(id);
        return Tx.jalankan(() ->
            exec("INSERT INTO user(user_id, nama, no_telepon, email, password, role) VALUES (?,?,?,?,?,'Driver')",
                    id, d.getNama(), d.getNoTelepon(), d.getEmail(), d.getPassword())
            && exec("INSERT INTO driver(user_id, merk_kendaraan, no_plat_kendaraan, jenis_kendaraan, status_aktif) VALUES (?,?,?,?,?)",
                    id, d.getMerkKendaraan(), d.getNoPlatKendaraan(), d.getJenisKendaraan(), d.getStatusAktif()));
    }
    @Override
    public boolean update(Driver d) {
        return Tx.jalankan(() ->
            exec("UPDATE user SET nama=?, no_telepon=?, email=?, password=? WHERE user_id=?",
                    d.getNama(), d.getNoTelepon(), d.getEmail(), d.getPassword(), d.getUserId())
            && exec("UPDATE driver SET merk_kendaraan=?, no_plat_kendaraan=?, jenis_kendaraan=?, status_aktif=? WHERE user_id=?",
                    d.getMerkKendaraan(), d.getNoPlatKendaraan(), d.getJenisKendaraan(), d.getStatusAktif(), d.getUserId()));
    }
    @Override
    public boolean delete(int id) {
        return Tx.jalankan(() ->
            exec("DELETE FROM driver WHERE user_id = ?", id) && exec("DELETE FROM user WHERE user_id = ?", id));
    }
    @Override
    public Driver findById(int id) {
        return queryOne(SELECT + " WHERE u.user_id = ?", id);
    }
    @Override
    public List<Driver> findAll() {
        return query(SELECT + " ORDER BY u.user_id");
    }
    public boolean updateStatus(int userId, String statusAktif) {
        return exec("UPDATE driver SET status_aktif = ? WHERE user_id = ?", statusAktif, userId);
    }
}