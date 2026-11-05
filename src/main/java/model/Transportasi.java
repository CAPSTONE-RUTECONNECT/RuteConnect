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
        this.jumlahPenumpang = jumlahPenumpang;
    }
    public void setStatusPenjemputan(String statusPenjemputan) {
        this.statusPenjemputan = statusPenjemputan;
    }

}
