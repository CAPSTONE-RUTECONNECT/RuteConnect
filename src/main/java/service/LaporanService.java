/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.io.*;
import java.util.List;
import model.Transaksi;

/**
 *
 * @author HP
 */
public class LaporanService {
    public double totalPembayaran(List<Transaksi> data) {
        return total(data, Transaksi.JENIS_PEMBAYARAN);
    }
    public double totalTopUp(List<Transaksi> data) {
        return total(data, Transaksi.JENIS_TOP_UP);
    }
    private double total(List<Transaksi> data, String jenis) {
        double s = 0;
        for (Transaksi t : data)
            if (jenis.equals(t.getJenisTransaksi()) && Transaksi.BERHASIL.equals(t.getStatusTransaksi())) s += t.getJumlahTransaksi();
        return s;
    }
    public void eksporCsv(List<Transaksi> data, File file) throws IOException {
        try (PrintWriter w = new PrintWriter(new FileWriter(file))) {
            w.println("ID,Tanggal,User ID,Jenis,Harga,Biaya Lain,Jumlah,Metode,Status,Pesanan ID");
            for (Transaksi t : data) {
                w.println(t.getTransaksiId() + "," + t.getWaktuTransaksi() + "," + t.getUserId() + "," + t.getJenisTransaksi() + ","
                        + t.getHarga() + "," + t.getBiayaLainnya() + "," + t.getJumlahTransaksi() + ","
                        + t.getMetodePembayaran() + "," + t.getStatusTransaksi() + "," + (t.getPesananId() == null ? "" : t.getPesananId()));
            }
        }
    }
}
