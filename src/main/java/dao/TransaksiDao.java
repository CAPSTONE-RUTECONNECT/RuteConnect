/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Transaksi;

/**
 *
 * @author HP
 */
public class TransaksiDao extends BaseDao<Transaksi> {
    @Override
    protected Transaksi mapRow(ResultSet rs) throws SQLException {
        Transaksi t = new Transaksi();
        t.setTransaksiId(rs.getInt("transaksi_id"));
        t.setJenisTransaksi(rs.getString("jenis_transaksi"));
        t.setHarga(rs.getDouble("harga"));
        t.setBiayaLainnya(rs.getDouble("biaya_lainnya"));
        t.setJumlahTransaksi(rs.getDouble("jumlah_transaksi"));
        t.setMetodePembayaran(rs.getString("metode_pembayaran"));
        t.setStatusTransaksi(rs.getString("status_transaksi"));
        t.setWaktuTransaksi(rs.getDate("waktu_transaksi"));
        t.setUserId(rs.getInt("user_id"));
        int ps = rs.getInt("pesanan_id");
        t.setPesananId(rs.wasNull() ? null : ps);
        return t;
    }
    @Override
    public boolean insert(Transaksi t) {
        int id = nextId("transaksi", "transaksi_id");
        t.setTransaksiId(id);
        return exec("INSERT INTO transaksi(transaksi_id, jenis_transaksi, harga, biaya_lainnya, jumlah_transaksi, "
                + "metode_pembayaran, status_transaksi, waktu_transaksi, user_id, pesanan_id) VALUES (?,?,?,?,?,?,?,CURDATE(),?,?)",
                id, t.getJenisTransaksi(), t.getHarga(), t.getBiayaLainnya(), t.getJumlahTransaksi(),
                t.getMetodePembayaran(), t.getStatusTransaksi(), t.getUserId(), t.getPesananId());
    }
    @Override
    public boolean update(Transaksi t) {
        return exec("UPDATE transaksi SET metode_pembayaran=?, status_transaksi=? WHERE transaksi_id=?",
                t.getMetodePembayaran(), t.getStatusTransaksi(), t.getTransaksiId());
    }
    @Override
    public boolean delete(int id) {
        return exec("DELETE FROM transaksi WHERE transaksi_id = ?", id);
    }
    @Override
    public Transaksi findById(int id) {
        return queryOne("SELECT * FROM transaksi WHERE transaksi_id = ?", id);
    }
    @Override
    public List<Transaksi> findAll() {
        return query("SELECT * FROM transaksi ORDER BY transaksi_id DESC");
    }
    public boolean hubungkanPesanan(int transaksiId, int pesananId) {
        return exec("UPDATE transaksi SET pesanan_id = ? WHERE transaksi_id = ?", pesananId, transaksiId);
    }
    public boolean insertTopUp(int transaksiId, double saldoMasuk, String status, int pelangganId) {
        return exec("INSERT INTO top_up(transaksi_id, saldo_masuk, saldo_keluar, status_top_up, pelanggan_id) VALUES (?,?,0,?,?)",
                transaksiId, saldoMasuk, status, pelangganId);
    }
    public List<Transaksi> findByTanggal(java.sql.Date tanggal) {
        return query("SELECT * FROM transaksi WHERE waktu_transaksi = ? ORDER BY transaksi_id", tanggal);
    }
    public List<Transaksi> findByBulan(int tahun, int bulan) {
        return query("SELECT * FROM transaksi WHERE YEAR(waktu_transaksi) = ? AND MONTH(waktu_transaksi) = ? ORDER BY transaksi_id", tahun, bulan);
    }
    // total semua transaksi yang berhasil
    public double totalBerhasil() {
        return scalar("SELECT IFNULL(SUM(jumlah_transaksi), 0) FROM transaksi WHERE status_transaksi = 'Berhasil'");
    }

    // total transaksi berhasil per metode: "E-Wallet", "Cash", atau "Saldo"
    public double totalByMetode(String metode) {
        return scalar("SELECT IFNULL(SUM(jumlah_transaksi), 0) FROM transaksi "
                    + "WHERE status_transaksi = 'Berhasil' AND metode_pembayaran = ?", metode);
    }

    // baris: {id transaksi, tanggal, pelanggan, jenis, metode, jumlah, status}
    public List<Object[]> ringkasanTransaksi() {
        List<Object[]> hasil = new java.util.ArrayList<>();
        String sql = "SELECT t.transaksi_id, t.waktu_transaksi, u.nama, t.jenis_transaksi, "
                   + "t.metode_pembayaran, t.jumlah_transaksi, t.status_transaksi "
                   + "FROM transaksi t JOIN user u ON t.user_id = u.user_id "
                   + "ORDER BY t.transaksi_id DESC";
        try (java.sql.PreparedStatement ps = conn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String metode = rs.getString(5);
                if ("Saldo".equalsIgnoreCase(metode)) metode = "RutePay";
                else if ("Cash".equalsIgnoreCase(metode)) metode = "Tunai";
                hasil.add(new Object[]{
                    "#" + rs.getInt(1),
                    rs.getDate(2),
                    rs.getString(3),
                    rs.getString(4),
                    metode != null ? metode : "-",
                    controller.Fmt.rupiah(rs.getDouble(6)),
                    rs.getString(7)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasil;
    }
}
