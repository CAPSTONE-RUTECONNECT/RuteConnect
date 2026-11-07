/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import config.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public abstract class BaseDao<T> implements CrudDao<T> {
    protected Connection conn() {
        return Koneksi.getConnection();
    }
    protected abstract T mapRow(ResultSet rs) throws SQLException;

    protected List<T> query(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            bind(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    protected T queryOne(String sql, Object... params) {
        List<T> l = query(sql, params);
        return l.isEmpty() ? null : l.get(0);
    }
    protected boolean exec(String sql, Object... params) {
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            bind(ps, params);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    protected int nextId(String tabel, String kolom) {
        String sql = "SELECT IFNULL(MAX(" + kolom + "), 0) + 1 FROM " + tabel;
        try (Statement st = conn().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    protected double scalar(String sql, Object... params) {
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            bind(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    private void bind(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) ps.setObject(i + 1, params[i]);
    }
}