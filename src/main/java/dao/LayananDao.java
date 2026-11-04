/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Layanan;

/**
 *
 * @author HP
 */
public class LayananDao extends BaseDao<Layanan> {
    @Override
    protected Layanan mapRow(ResultSet rs) throws SQLException {
        return new Layanan(rs.getInt("layanan_id"), rs.getString("nama_layanan"), rs.getString("jenis_kendaraan"),
                rs.getString("kelas_layanan"), rs.getDouble("tarif_per_km"));
    }
    @Override
    public boolean insert(Layanan l) {
        int id = nextId("layanan", "layanan_id");
        l.setLayananId(id);
        return exec("INSERT INTO layanan(layanan_id, nama_layanan, jenis_kendaraan, kelas_layanan, tarif_per_km) VALUES (?,?,?,?,?)",
                id, l.getNamaLayanan(), l.getJenisKendaraan(), l.getKelasLayanan(), l.getTarifPerKm());
    }
    @Override
    public boolean update(Layanan l) {
        return exec("UPDATE layanan SET nama_layanan=?, jenis_kendaraan=?, kelas_layanan=?, tarif_per_km=? WHERE layanan_id=?",
                l.getNamaLayanan(), l.getJenisKendaraan(), l.getKelasLayanan(), l.getTarifPerKm(), l.getLayananId());
    }
    @Override
    public boolean delete(int id) {
        return exec("DELETE FROM layanan WHERE layanan_id = ?", id);
    }
    @Override
    public Layanan findById(int id) {
        return queryOne("SELECT * FROM layanan WHERE layanan_id = ?", id);
    }
    @Override
    public List<Layanan> findAll() {
        return query("SELECT * FROM layanan ORDER BY layanan_id");
    }
    public List<Layanan> findTransportasi(String jenisKendaraan) {
        return query("SELECT * FROM layanan WHERE jenis_kendaraan = ? AND nama_layanan NOT LIKE '%Dokumen%' ORDER BY layanan_id", jenisKendaraan);
    }
    public List<Layanan> findDokumen() {
        return query("SELECT * FROM layanan WHERE nama_layanan LIKE '%Dokumen%' ORDER BY layanan_id");
    }
}