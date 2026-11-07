/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.*;
import model.*;
import util.Tx;

/**
 *
 * @author HP
 */
public class PesananService {
    private final PesananDao pesananDao = new PesananDao();
    private final TransaksiDao transaksiDao = new TransaksiDao();
    private final PelangganDao pelangganDao = new PelangganDao();
    private final DriverDao driverDao = new DriverDao();
    
    private static boolean isWallet(String metode) {
        return Transaksi.SALDO.equalsIgnoreCase(metode);
    }
    
    public Hasil buatPesanan(Pesanan p, String metode) {
        if (pesananDao.adaPesananBerlangsung(p.getPelangganId()))
            return Hasil.gagal("Masih ada pesanan yang sedang berlangsung.");

        final boolean wallet = isWallet(metode);
        final double total = p.getTotalBiaya();
        if (wallet && pelangganDao.getSaldo(p.getPelangganId()) < total)
            return Hasil.gagal("Saldo tidak cukup");
        
        final Transaksi t = new Transaksi();
        t.setJenisTransaksi(Transaksi.JENIS_PEMBAYARAN);
        t.setHarga(total);
        t.setBiayaLainnya(0);
        t.setJumlahTransaksi(total);
        t.setMetodePembayaran(metode);
        t.setStatusTransaksi(Transaksi.BERHASIL);
        t.setUserId(p.getPelangganId());
        boolean ok = Tx.jalankan(() -> {
            if (wallet && !pelangganDao.ubahSaldo(p.getPelangganId(), -total))
                return false;
            if (!transaksiDao.insert(t))
                return false;
            p.setTransaksiId(t.getTransaksiId());
            if (!pesananDao.insert(p))
                return false;
            return transaksiDao.hubungkanPesanan(t.getTransaksiId(), p.getPesananId());
        });
        return ok ? Hasil.ok("Pesanan berhasil dibuat (status: Menunggu driver).") : Hasil.gagal("Pesanan gagal disimpan.");
    }

    public Hasil batalkanOlehCustomer(int pesananId, String alasan) {
        Pesanan p = pesananDao.findById(pesananId);
        if (p == null)
            return Hasil.gagal("Pesanan tidak ditemukan.");
        if (!p.bisaDibatalkan())
            return Hasil.gagal("Pesanan Tidak Dapat Dibatalkan.");
        String a = (alasan == null || alasan.trim().isEmpty()) ? "Dibatalkan oleh pelanggan" : alasan.trim();
        return batalkan(p, a);
    }

    private Hasil batalkan(Pesanan p, String alasan) {
        final boolean refund = isWallet(p.getMetodePembayaran());
        boolean ok = Tx.jalankan(() -> {
            // 1. Batalkan pesanan
            boolean suksesPesanan = pesananDao.batalkan(p.getPesananId(), alasan);
            
            // 2. Jika menggunakan wallet, lakukan refund saldo
            boolean suksesRefund = !refund || pelangganDao.ubahSaldo(p.getPelangganId(), p.getTotalBiaya());
            
            // 3. Ubah status transaksi terkait menjadi 'Gagal' agar tidak masuk hitungan laporan sukses Admin
            boolean suksesTransaksi = transaksiDao.updateStatusTransaksiByPesanan(p.getPesananId(), "Gagal");
            
            return suksesPesanan && suksesRefund && suksesTransaksi;
        });
        
        if (!ok)
            return Hasil.gagal("Gagal membatalkan pesanan.");
        return Hasil.ok(refund ? "Pesanan dibatalkan. Saldo E-Wallet dikembalikan penuh."
                               : "Pesanan dibatalkan. Pelanggan tidak perlu melakukan pembayaran.");
    }
    
    //bgian driver
    public Hasil terima(int pesananId, int driverId) {
        Driver d = driverDao.findById(driverId);
        if (d == null || !d.isAktif())
            return Hasil.gagal("Status driver Non-Aktif, tidak bisa menerima pesanan.");
        return pesananDao.terima(pesananId, driverId)
                ? Hasil.ok("Pesanan diterima.")
                : Hasil.gagal("Pesanan sudah diambil driver lain atau tidak tersedia.");
    }
    public static String berikutnya(String status) {
        if (Pesanan.DITERIMA.equals(status))
            return Pesanan.DIPROSES;
        if (Pesanan.DIPROSES.equals(status))
            return Pesanan.DALAM_PERJALANAN;
        if (Pesanan.DALAM_PERJALANAN.equals(status))
            return Pesanan.SELESAI;
        return null;
    }
    public Hasil ubahStatus(int pesananId, int driverId) {
        Pesanan p = pesananDao.findById(pesananId);
        if (p == null || p.getDriverId() == null || p.getDriverId() != driverId)
            return Hasil.gagal("Pesanan bukan milik driver ini.");
        String next = berikutnya(p.getStatusPesanan());
        if (next == null)
            return Hasil.gagal("Status tidak bisa diubah lagi.");
        return pesananDao.updateStatus(pesananId, next)
                ? Hasil.ok("Status pesanan sekarang: " + next) : Hasil.gagal("Gagal mengubah status.");
    }
    
    public Hasil batalkanOlehDriver(int pesananId, int driverId) {
        return batalkanOlehDriver(pesananId, driverId, "Dibatalkan oleh driver");
    }
    //overload
    public Hasil batalkanOlehDriver(int pesananId, int driverId, String alasan) {
        Pesanan p = pesananDao.findById(pesananId);
        if (p == null || p.getDriverId() == null || p.getDriverId() != driverId)
            return Hasil.gagal("Pesanan bukan milik driver ini.");
        String s = p.getStatusPesanan();
        if (!(Pesanan.DITERIMA.equals(s) || Pesanan.DIPROSES.equals(s) || Pesanan.DALAM_PERJALANAN.equals(s)))
            return Hasil.gagal("Pesanan tidak dapat dibatalkan pada status " + s);
        return batalkan(p, alasan);
    }
}
