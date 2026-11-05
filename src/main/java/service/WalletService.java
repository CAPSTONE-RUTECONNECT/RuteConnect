/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.PelangganDao;
import dao.TransaksiDao;
import model.Transaksi;
import util.Fmt;
import util.Tx;

/**
 *
 * @author HP
 */
public class WalletService {
    public static final double MIN = 10000, MAKS = 2000000;
    private final TransaksiDao transaksiDao = new TransaksiDao();
    private final PelangganDao pelangganDao = new PelangganDao();
    
    public Hasil topUp(int userId, double nominal) {
        if (nominal < MIN || nominal > MAKS)
            return Hasil.gagal("Nominal tidak valid atau melebihi batas (min " + Fmt.rupiah(MIN) + ", maks " + Fmt.rupiah(MAKS) + ").");
        final Transaksi t = new Transaksi();
        t.setJenisTransaksi(Transaksi.JENIS_TOP_UP);
        t.setHarga(nominal);
        t.setBiayaLainnya(0);
        t.setJumlahTransaksi(nominal);
        t.setMetodePembayaran(Transaksi.E_WALLET);
        t.setStatusTransaksi(Transaksi.BERHASIL);
        t.setUserId(userId);
        t.setPesananId(null);
        boolean ok = Tx.jalankan(() ->
            transaksiDao.insert(t)
            && transaksiDao.insertTopUp(t.getTransaksiId(), nominal, Transaksi.BERHASIL, userId)
            && pelangganDao.ubahSaldo(userId, nominal));
        return ok ? Hasil.ok("Top up berhasil. Saldo ditambahkan " + Fmt.rupiah(nominal)) : Hasil.gagal("Top up gagal.");
    }
}
