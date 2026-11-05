/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Transportasi extends Pesanan {
    //Tabel transportasi
    private int jumlahPenumpang;
    private String statusPenjemputan;
    
    public Transportasi() {
        
    }
    
    @Override
    public String getJenisPesanan() {
        return JENIS_TRANSPORTASI;
    }
    
    public int getJumlahPenumpang() {
        return jumlahPenumpang;
    }
    public String getStatusPenjemputan() {
        return statusPenjemputan;
    }
    public void setJumlahPenumpang(int jumlahPenumpang) {
        if (jumlahPenumpang < 1 || jumlahPenumpang > 8) {
            throw new IllegalArgumentException("Jumlah penumpang harus antara 1 sampai 8");
        }
        this.jumlahPenumpang = jumlahPenumpang;
    }
    public void setStatusPenjemputan(String statusPenjemputan) {
        if (statusPenjemputan == null
                || !(statusPenjemputan.equalsIgnoreCase("menunggu")
                || statusPenjemputan.equalsIgnoreCase("sudah dijemput"))) {
            throw new IllegalArgumentException("Status penjemputan harus menunggu, dalam perjalanan, atau sudah dijemput");
        }
        this.statusPenjemputan = statusPenjemputan.toLowerCase();
    }
}