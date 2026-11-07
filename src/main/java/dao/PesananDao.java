/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Dokumen;
import model.Pesanan;
import model.Transportasi;
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
        // pilih subclass sesuai kolom jenis_pesanan
        Pesanan p;
        if (Pesanan.JENIS_DOKUMEN.equals(rs.getString("jenis_pesanan"))) {
            Dokumen dok = new Dokumen();
            dok.setNamaPenerima(rs.getString("nama_penerima"));
            dok.setNoTeleponPenerima(rs.getString("no_telepon_penerima"));
            dok.setDetailDokumen(rs.getString("detail_dokumen"));
            p = dok;
        } else {
            Transportasi trans = new Transportasi();
            trans.setJumlahPenumpang(rs.getInt("jumlah_penumpang"));
            trans.setStatusPenjemputan(rs.getString("status_penjemputan"));
            p = trans;
        }
        // data umum (sama untuk semua jenis pesanan)
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
        p.setNamaLayanan(rs.getString("nama_layanan"));
        p.setNamaPelanggan(rs.getString("nama_pelanggan"));
        p.setNamaDriver(rs.getString("nama_driver"));
        p.setMetodePembayaran(rs.getString("metode_pembayaran"));
        return p;
    }
 
    //Menyimpan baris pesanan + baris turunannya (transportasi / dokumen). Panggil di dalam Tx.
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
        if (p instanceof Dokumen dok) {
            return exec("INSERT INTO dokumen(pesanan_id, nama_penerima, no_telepon_penerima, detail_dokumen) VALUES (?,?,?,?)",
                    id, dok.getNamaPenerima(), dok.getNoTeleponPenerima(), dok.getDetailDokumen());
        }
        Transportasi trans = (Transportasi) p;
        return exec("INSERT INTO transportasi(pesanan_id, jumlah_penumpang, status_penjemputan) VALUES (?,?,'Menunggu')",
                id, trans.getJumlahPenumpang());
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
    
    public List<Pesanan> findByPelanggan(int pelangganId, String filter) {
        String w = "WHERE p.pelanggan_id = ? ";
        w += switch (filter) {
            case "Aktif"      -> "AND p.status_pesanan IN ('Menunggu','Diterima','Diproses','Dalam Perjalanan') ";
            case "Selesai"    -> "AND p.status_pesanan = 'Selesai' ";
            case "Dibatalkan" -> "AND p.status_pesanan = 'Dibatalkan' ";
            case null, default -> "";   // filter lain atau null: tampilkan semua
        };
        return query(SELECT + w + "ORDER BY p.pesanan_id DESC", pelangganId);
    }
    
    // Pesanan yang masih menunggu driver, sesuai jenis layanan & kendaraan driver. 
    public List<Pesanan> findTersedia(String jenisPesanan, String jenisKendaraan) {
        return query(SELECT + "WHERE p.status_pesanan = 'Menunggu' AND p.driver_id IS NULL "
                + "AND p.jenis_pesanan = ? AND l.jenis_kendaraan = ? ORDER BY p.pesanan_id", jenisPesanan, jenisKendaraan);
    }
    public List<Pesanan> findByDriver(int driverId, boolean aktif) {
        String st = aktif ? "('Diterima','Diproses','Dalam Perjalanan')" : "('Selesai','Dibatalkan')";
        return query(SELECT + "WHERE p.driver_id = ? AND p.status_pesanan IN " + st + " ORDER BY p.pesanan_id DESC", driverId);
    }
    
    //Driver menerima pesanan (hanya berhasil jika belum diambil driver lain).
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
    
    public int hitungPesananHariIni() {
        return (int) scalar("SELECT COUNT(*) FROM pesanan WHERE waktu_pesanan = CURDATE()");
    }

    // pendapatan = total biaya pesanan yang sudah Selesai
    public double totalPendapatan() {
        return scalar("SELECT IFNULL(SUM(total_biaya), 0) FROM pesanan WHERE status_pesanan = 'Selesai'");
    }

    // baris: {id, pelanggan, driver, layanan, metode, rute, status}
    public List<Object[]> ringkasanDashboard() {
        List<Object[]> hasil = new java.util.ArrayList<>();
        String sql = "SELECT p.pesanan_id, up.nama, ud.nama, l.nama_layanan, t.metode_pembayaran, "
                + "p.lokasi_jemput, p.lokasi_tujuan, p.status_pesanan "
                + "FROM pesanan p "
                + "JOIN layanan l ON p.layanan_id = l.layanan_id "
                + "JOIN user up ON p.pelanggan_id = up.user_id "
                + "LEFT JOIN user ud ON p.driver_id = ud.user_id "
                + "LEFT JOIN transaksi t ON t.pesanan_id = p.pesanan_id "
                + "AND t.jenis_transaksi = 'Pembayaran Pesanan' "
                + "ORDER BY p.pesanan_id DESC";
        
        try (PreparedStatement ps = conn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String metode = rs.getString(5);
                if ("Saldo".equalsIgnoreCase(metode)) metode = "RutePay";
                else if ("Cash".equalsIgnoreCase(metode)) metode = "Tunai";
                hasil.add(new Object[]{
                    "#" + rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3) != null ? rs.getString(3) : "-",
                    rs.getString(4),
                    metode != null ? metode : "-",
                    rs.getString(6) + " → " + rs.getString(7),
                    rs.getString(8)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasil;
    }
    
    public int hitungByStatus(String status) {
        return (int) scalar("SELECT COUNT(*) FROM pesanan WHERE status_pesanan = ?", status);
    }

    // total semua pesanan masuk
    public int hitungSemuaPesanan() {
        return (int) scalar("SELECT COUNT(*) FROM pesanan");
    }
    
    public boolean adaPesananBerlangsung(int pelangganId) {
        return scalar("SELECT COUNT(*) FROM pesanan WHERE pelanggan_id = ? "
                + "AND status_pesanan IN ('Menunggu','Diterima','Diproses','Dalam Perjalanan')",
                pelangganId) > 0;
    }

    public List<Object[]> cariPesananAdmin(String keyword, String kategori, String status) {
        List<Object[]> hasil = new java.util.ArrayList<>();
        String kw = (keyword == null ? "" : keyword.trim());
        String like = "%" + kw + "%";

        StringBuilder sql = new StringBuilder(
            "SELECT p.pesanan_id, up.nama, ud.nama, l.nama_layanan, p.total_biaya, "
            + "p.lokasi_jemput, p.lokasi_tujuan, p.status_pesanan "
            + "FROM pesanan p "
            + "JOIN layanan l ON p.layanan_id = l.layanan_id "
            + "JOIN user up ON p.pelanggan_id = up.user_id "
            + "LEFT JOIN user ud ON p.driver_id = ud.user_id "
            + "WHERE (CAST(p.pesanan_id AS CHAR) = ? OR up.nama LIKE ? OR ud.nama LIKE ? "
            + "OR l.nama_layanan LIKE ? OR p.lokasi_jemput LIKE ? OR p.lokasi_tujuan LIKE ?) ");

        // filter kategori layanan
        switch (kategori) {
            case "RuteRide" -> sql.append("AND p.jenis_pesanan = 'Transportasi' AND l.jenis_kendaraan = 'Motor' ");
            case "RuteCar" -> sql.append("AND p.jenis_pesanan = 'Transportasi' AND l.jenis_kendaraan = 'Mobil' ");
            case "Dokumen" -> sql.append("AND p.jenis_pesanan = 'Dokumen' ");
            default -> {}
        }

        // filter status
        boolean pakaiStatus = true;
        switch (status) {
            case "Menunggu" -> sql.append("AND p.status_pesanan = 'Menunggu' ");
            case "Dalam Perjalanan" -> sql.append("AND p.status_pesanan = 'Dalam Perjalanan' ");
            case "Selesai" -> sql.append("AND p.status_pesanan = 'Selesai' ");
            case "Batal" -> sql.append("AND p.status_pesanan = 'Dibatalkan' ");
            default -> pakaiStatus = false;
        }

        sql.append("ORDER BY p.pesanan_id DESC");

        try (PreparedStatement ps = conn().prepareStatement(sql.toString())) {
            ps.setString(1, kw);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ps.setString(5, like);
            ps.setString(6, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new Object[]{
                        "#" + rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3) != null ? rs.getString(3) : "-",
                        rs.getString(4),
                        controller.Fmt.rupiah(rs.getDouble(5)),
                        rs.getString(6) + " → " + rs.getString(7),
                        rs.getString(8)
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasil;
    }
}
