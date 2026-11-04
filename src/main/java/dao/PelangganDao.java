/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Pelanggan;
import util.Tx;

/**
 *
 * @author HP
 */
public class PelangganDao extends BaseDao<Pelanggan> {
    private static final String SELECT =
        "SELECT u.user_id, u.nama, u.no_telepon, u.email, u.password, u.role, p.alamat_favorit, p.saldo_wallet "
        + "FROM user u JOIN pelanggan p ON u.user_id = p.user_id";
    @Override
    protected Pelanggan mapRow(ResultSet rs) throws SQLException {
        return new Pelanggan(rs.getInt("user_id"), rs.getString("nama"), rs.getString("no_telepon"),
                rs.getString("email"), rs.getString("password"), rs.getString("role"),
                rs.getString("alamat_favorit"), rs.getDouble("saldo_wallet"));
    }
    @Override public boolean insert(Pelanggan p) {
        final int id = nextId("user", "user_id");
        if (id < 0) return false;
        p.setUserId(id);
        return Tx.jalankan(() ->
            exec("INSERT INTO user(user_id, nama, no_telepon, email, password, role) VALUES (?,?,?,?,?,'Pelanggan')",
                    id, p.getNama(), p.getNoTelepon(), p.getEmail(), p.getPassword())
            && exec("INSERT INTO pelanggan(user_id, alamat_favorit, saldo_wallet) VALUES (?,?,?)",
                    id, p.getAlamatFavorit(), p.getSaldoWallet()));
    }
    @Override public boolean update(Pelanggan p) {
        return Tx.jalankan(() ->
            exec("UPDATE user SET nama=?, no_telepon=?, email=?, password=? WHERE user_id=?",
                    p.getNama(), p.getNoTelepon(), p.getEmail(), p.getPassword(), p.getUserId())
            && exec("UPDATE pelanggan SET alamat_favorit=?, saldo_wallet=? WHERE user_id=?",
                    p.getAlamatFavorit(), p.getSaldoWallet(), p.getUserId()));
    }
    @Override
    public boolean delete(int id) {
        return Tx.jalankan(() ->
            exec("DELETE FROM pelanggan WHERE user_id = ?", id) && exec("DELETE FROM user WHERE user_id = ?", id));
    }
    @Override
    public Pelanggan findById(int id) {
        return queryOne(SELECT + " WHERE u.user_id = ?", id);
    }
    @Override
    public List<Pelanggan> findAll() {
        return query(SELECT + " ORDER BY u.user_id");
    }
    public double getSaldo(int userId) {
        Pelanggan p = findById(userId);
        return p == null ? 0 : p.getSaldoWallet();
    }
    public boolean ubahSaldo(int userId, double delta) {
        return exec("UPDATE pelanggan SET saldo_wallet = saldo_wallet + ? WHERE user_id = ?", delta, userId);
    }
}