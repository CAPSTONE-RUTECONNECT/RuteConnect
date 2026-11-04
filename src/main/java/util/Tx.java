/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import config.Koneksi;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Tx {
    public interface Kerja { boolean jalankan() throws Exception; }

    public static boolean jalankan(Kerja kerja) {
        Connection c = Koneksi.getConnection();
        if (c == null) return false;
        try {
            c.setAutoCommit(false);
            if (kerja.jalankan()) {
                c.commit(); return true;
            }
            c.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                c.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
