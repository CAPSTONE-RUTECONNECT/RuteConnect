/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Pesanan;
import util.Tx;

/**
 *
 * @author HP
 */
public class PesananDao extends BaseDao<Pesanan> {
    private static final String SELECT =
        "SELECT p.*, l.nama_layanan, up.nama AS nama_pelanggan, ud.nama AS nama_driver, "
        + "t.metode_pembayaran, tr.jumlah_penumpang, tr.status_penjemputan, "
        + "dk.nama_penerima, dk.no_telepon_penerima, dk.detail_dokumen "
        + "FROM pesanan p "
        + "JOIN layanan l ON p.layanan_id = l.layanan_id "
        + "JOIN user up ON p.pelanggan_id = up.user_id "
        + "LEFT JOIN user ud ON p.driver_id = ud.user_id "
        + "LEFT JOIN transaksi t ON p.transaksi_id = t.transaksi_id "
        + "LEFT JOIN transportasi tr ON tr.pesanan_id = p.pesanan_id "
        + "LEFT JOIN dokumen dk ON dk.pesanan_id = p.pesanan_id ";

    @Override
    protected Pesanan mapRow(ResultSet rs) throws SQLException {
        Pesanan p = new Pesanan();
        p.setPesananId(rs.getInt("pesanan_id"));
        p.setLokasiJemput(rs.getString("lokasi_jemput"));
        p.setLokasiTujuan(rs.getString("lokasi_tujuan"));
        p.setJarakKm(rs.getDouble("jarak_km"));
        p.setWaktuPenjemputan(rs.getTimestamp("waktu_penjemputan"));
        p.setCatatan(rs.getString("catatan"));
        p.setTotalBiaya(rs.getDouble("total_biaya"));
        p.setWaktuPesanan(rs.getDate("waktu_pesanan"));
        p.setStatusPesanan(rs.getString("status_pesanan"));
        p.setAlasanBatal(rs.getString("alasan_batal"));
        p.setPelangganId(rs.getInt("pelanggan_id"));
        int d = rs.getInt("driver_id");
        p.setDriverId(rs.wasNull() ? null : d);
        p.setLayananId(rs.getInt("layanan_id"));
        p.setTransaksiId(rs.getInt("transaksi_id"));
        p.setJenisPesanan(rs.getString("jenis_pesanan"));
        p.setNamaLayanan(rs.getString("nama_layanan"));
        p.setNamaPelanggan(rs.getString("nama_pelanggan"));
        p.setNamaDriver(rs.getString("nama_driver"));
        p.setMetodePembayaran(rs.getString("metode_pembayaran"));
        p.setJumlahPenumpang(rs.getInt("jumlah_penumpang"));
        p.setStatusPenjemputan(rs.getString("status_penjemputan"));
        p.setNamaPenerima(rs.getString("nama_penerima"));
        p.setNoTeleponPenerima(rs.getString("no_telepon_penerima"));
        p.setDetailDokumen(rs.getString("detail_dokumen"));
        return p;
    }

    /** Menyimpan baris pesanan + baris turunannya (transportasi / dokumen). Panggil di dalam Tx. */
    @Override
    public boolean insert(Pesanan p) {
        int id = nextId("pesanan", "pesanan_id");
        if (id < 0) return false;
        p.setPesananId(id);
        boolean ok = exec("INSERT INTO pesanan(pesanan_id, lokasi_jemput, lokasi_tujuan, jarak_km, waktu_penjemputan, catatan, "
                + "total_biaya, waktu_pesanan, status_pesanan, pelanggan_id, driver_id, layanan_id, transaksi_id, jenis_pesanan) "
                + "VALUES (?,?,?,?,?,?,?,CURDATE(),'Menunggu',?,NULL,?,?,?)",
                id, p.getLokasiJemput(), p.getLokasiTujuan(), p.getJarakKm(), p.getWaktuPenjemputan(), p.getCatatan(),
                p.getTotalBiaya(), p.getPelangganId(), p.getLayananId(), p.getTransaksiId(), p.getJenisPesanan());
        if (!ok) return false;
        if (p.isDokumen()) {
            return exec("INSERT INTO dokumen(pesanan_id, nama_penerima, no_telepon_penerima, detail_dokumen) VALUES (?,?,?,?)",
                    id, p.getNamaPenerima(), p.getNoTeleponPenerima(), p.getDetailDokumen());
        }
        return exec("INSERT INTO transportasi(pesanan_id, jumlah_penumpang, status_penjemputan) VALUES (?,?,'Menunggu')",
                id, p.getJumlahPenumpang());
    }

    @Override
    public boolean update(Pesanan p) {
        return exec("UPDATE pesanan SET status_pesanan=?, driver_id=?, alasan_batal=? WHERE pesanan_id=?",
                p.getStatusPesanan(), p.getDriverId(), p.getAlasanBatal(), p.getPesananId());
    }

    @Override
    public boolean delete(int id) {
        return Tx.jalankan(() -> {
            exec("DELETE FROM transportasi WHERE pesanan_id = ?", id);
            exec("DELETE FROM dokumen WHERE pesanan_id = ?", id);
            return exec("DELETE FROM pesanan WHERE pesanan_id = ?", id);
        });
    }

    @Override
    public Pesanan findById(int id) {
        return queryOne(SELECT + "WHERE p.pesanan_id = ?", id);
    }
    @Override
    public List<Pesanan> findAll() {
        return query(SELECT + "ORDER BY p.pesanan_id DESC");
    }
    // ---------- query khusus ----------
    public List<Pesanan> findByPelanggan(int pelangganId, String filter) {
        String w = "WHERE p.pelanggan_id = ? ";
        if ("Aktif".equals(filter)) w += "AND p.status_pesanan IN ('Menunggu','Diterima','Diproses','Dalam Perjalanan') ";
        else if ("Selesai".equals(filter)) w += "AND p.status_pesanan = 'Selesai' ";
        else if ("Dibatalkan".equals(filter)) w += "AND p.status_pesanan = 'Dibatalkan' ";
        return query(SELECT + w + "ORDER BY p.pesanan_id DESC", pelangganId);
    }
    /** Pesanan yang masih menunggu driver, sesuai jenis layanan & kendaraan driver. */
    public List<Pesanan> findTersedia(String jenisPesanan, String jenisKendaraan) {
        return query(SELECT + "WHERE p.status_pesanan = 'Menunggu' AND p.driver_id IS NULL "
                + "AND p.jenis_pesanan = ? AND l.jenis_kendaraan = ? ORDER BY p.pesanan_id", jenisPesanan, jenisKendaraan);
    }
    public List<Pesanan> findByDriver(int driverId, boolean aktif) {
        String st = aktif ? "('Diterima','Diproses','Dalam Perjalanan')" : "('Selesai','Dibatalkan')";
        return query(SELECT + "WHERE p.driver_id = ? AND p.status_pesanan IN " + st + " ORDER BY p.pesanan_id DESC", driverId);
    }
    /** Driver menerima pesanan (hanya berhasil jika belum diambil driver lain). */
    public boolean terima(int pesananId, int driverId) {
        return exec("UPDATE pesanan SET status_pesanan='Diterima', driver_id=? "
                + "WHERE pesanan_id=? AND status_pesanan='Menunggu' AND driver_id IS NULL", driverId, pesananId);
    }
    public boolean updateStatus(int id, String status) {
        boolean ok = exec("UPDATE pesanan SET status_pesanan = ? WHERE pesanan_id = ?", status, id);
        if (ok && Pesanan.DALAM_PERJALANAN.equals(status))
            exec("UPDATE transportasi SET status_penjemputan='Dijemput' WHERE pesanan_id = ?", id);
        if (ok && Pesanan.SELESAI.equals(status))
            exec("UPDATE transportasi SET status_penjemputan='Selesai' WHERE pesanan_id = ?", id);
        return ok;
    }

    public boolean batalkan(int id, String alasan) {
        return exec("UPDATE pesanan SET status_pesanan = 'Dibatalkan', alasan_batal = ? WHERE pesanan_id = ?", alasan, id);
    }
}