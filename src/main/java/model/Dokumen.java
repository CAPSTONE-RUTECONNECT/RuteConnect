/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Dokumen extends Pesanan {
    //Tabel dokumen
    private String namaPenerima;
    private String noTeleponPenerima;
    private String detailDokumen;
    
    public Dokumen() {
        
    }
    
    @Override
    public String getJenisPesanan() {
        return JENIS_DOKUMEN;
    }
    
    public String getNamaPenerima() {
        return namaPenerima;
    }
    public String getNoTeleponPenerima() {
        return noTeleponPenerima;
    }
    public String getDetailDokumen() {
        return detailDokumen;
    }
    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }
    public void setNoTeleponPenerima(String noTeleponPenerima) {
        this.noTeleponPenerima = noTeleponPenerima;
    }
    public void setDetailDokumen(String detailDokumen) {
        this.detailDokumen = detailDokumen;
    }
}