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
    
    // Mutasi saldo RutePay milik 1 pelanggan, terbaru di atas
    // baris: {judul, keterangan, metode, tanggal, jumlah, masuk(true = saldo bertambah)}
    public List<Object[]> riwayatSaldo(int userId) {
        List<Object[]> hasil = new java.util.ArrayList<>();
        String sql = "SELECT t.transaksi_id, t.waktu_transaksi, t.jenis_transaksi, t.metode_pembayaran, "
                   + "t.jumlah_transaksi, t.pesanan_id, p.status_pesanan, l.nama_layanan "
                   + "FROM transaksi t "
                   + "LEFT JOIN pesanan p ON p.pesanan_id = t.pesanan_id "
                   + "LEFT JOIN layanan l ON l.layanan_id = p.layanan_id "
                   + "WHERE t.user_id = ? AND t.status_transaksi = 'Berhasil' "
                   + "AND (t.jenis_transaksi = 'Top Up' OR t.metode_pembayaran = 'Saldo') "
                   + "ORDER BY t.transaksi_id DESC";
        try (java.sql.PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    java.sql.Date tanggal = rs.getDate(2);
                    String jenis = rs.getString(3);
                    String metode = rs.getString(4);
                    double jumlah = rs.getDouble(5);
                    int idPesanan = rs.getInt(6);
                    boolean adaPesanan = !rs.wasNull();
                    String statusPesanan = rs.getString(7);
                    String layanan = rs.getString(8);

                    if ("Top Up".equalsIgnoreCase(jenis)) {
                        hasil.add(new Object[]{"Top Up Saldo", "Isi ulang saldo", metode, tanggal, jumlah, true});
                    } else {
                        String ket = (layanan != null ? layanan : "Pesanan") + (adaPesanan ? " #" + idPesanan : "");
                        if ("Dibatalkan".equalsIgnoreCase(statusPesanan)) {
                            hasil.add(new Object[]{"Refund Pesanan", ket, "RutePay", tanggal, jumlah, true});
                        }
                        hasil.add(new Object[]{"Pembayaran Pesanan", ket, "RutePay", tanggal, jumlah, false});
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasil;
    }

    // jumlah pesanan yang pembayarannya berhasil pada tahun & bulan tertentu
    public int hitungPesananBerhasil(int tahun, int bulan) {
        return (int) scalar("SELECT COUNT(*) FROM transaksi WHERE status_transaksi = 'Berhasil' "
                + "AND jenis_transaksi = 'Pembayaran Pesanan' "
                + "AND YEAR(waktu_transaksi) = ? AND MONTH(waktu_transaksi) = ?", tahun, bulan);
    }

    // baris: {bulan, id pesanan, tanggal, pelanggan, metode, jumlah, status}
    // status: "Semua Status" / "Berhasil" / "Gagal"
    // metode: "Semua Metode" / "E-Wallet" / "Cash" (juga menerima "RutePay" & "Tunai")
    // tahun/bulan = 0 berarti tidak difilter
    public List<Object[]> cariLaporan(String keyword, String status, String metode, int tahun, int bulan) {
        List<Object[]> hasil = new java.util.ArrayList<>();
        String kw = (keyword == null ? "" : keyword.trim());
        List<Object> p = new java.util.ArrayList<>();

        StringBuilder sql = new StringBuilder(
            "SELECT t.waktu_transaksi, t.pesanan_id, u.nama, t.metode_pembayaran, "
            + "t.jumlah_transaksi, t.status_transaksi, IFNULL(p.jenis_pesanan, 'Top Up') "
            + "FROM transaksi t JOIN user u ON t.user_id = u.user_id "
            + "LEFT JOIN pesanan p ON t.pesanan_id = p.pesanan_id "
            + "WHERE (CAST(t.transaksi_id AS CHAR) = ? OR CAST(t.pesanan_id AS CHAR) = ? OR u.nama LIKE ?) ");
        p.add(kw); p.add(kw); p.add("%" + kw + "%");

        if ("Berhasil".equals(status) || "Gagal".equals(status)) {
            sql.append("AND t.status_transaksi = ? ");
            p.add(status);
        }
        if ("E-Wallet".equals(metode)) {
            sql.append("AND t.metode_pembayaran = 'E-Wallet' ");
        } else if ("Cash".equals(metode) || "Tunai".equals(metode)) {
            sql.append("AND t.metode_pembayaran = 'Cash' ");
        } else if ("RutePay".equals(metode) || "Saldo".equals(metode)) {
            sql.append("AND t.metode_pembayaran = 'Saldo' ");
        }
        if (tahun > 0) { sql.append("AND YEAR(t.waktu_transaksi) = ? "); p.add(tahun); }
        if (bulan > 0) { sql.append("AND MONTH(t.waktu_transaksi) = ? "); p.add(bulan); }
        sql.append("ORDER BY t.transaksi_id DESC");

        try (java.sql.PreparedStatement ps = conn().prepareStatement(sql.toString())) {
            for (int i = 0; i < p.size(); i++) ps.setObject(i + 1, p.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    java.time.LocalDate d = rs.getDate(1).toLocalDate();
                    String namaBulan = d.getMonth().getDisplayName(
                            java.time.format.TextStyle.FULL, new java.util.Locale("id", "ID")) + " " + d.getYear();
                    int idPesanan = rs.getInt(2);
                    boolean adaPesanan = !rs.wasNull();
                    String metodeTampil = rs.getString(4);
                    if ("Saldo".equalsIgnoreCase(metodeTampil)) metodeTampil = "RutePay";
                    else if ("Cash".equalsIgnoreCase(metodeTampil)) metodeTampil = "Tunai";
                    hasil.add(new Object[]{
                        namaBulan,
                        adaPesanan ? "#" + idPesanan : "-",
                        rs.getString(7),          // Jenis: Top Up / Transportasi / Dokumen
                        rs.getDate(1),
                        rs.getString(3),
                        metodeTampil,
                        controller.Fmt.rupiah(rs.getDouble(5)),
                        rs.getString(6)
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasil;
    }
}
