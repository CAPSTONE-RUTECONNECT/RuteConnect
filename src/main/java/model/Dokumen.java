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
        if (namaPenerima == null || !namaPenerima.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama penerima hanya boleh huruf dan spasi");
        }
        this.namaPenerima = namaPenerima.trim();
    }
    public void setNoTeleponPenerima(String noTeleponPenerima) {
        if (noTeleponPenerima == null || !noTeleponPenerima.matches("\\d{1,15}")) {
            throw new IllegalArgumentException("No telepon penerima hanya angka, maksimal 15 digit");
        }
        this.noTeleponPenerima = noTeleponPenerima;
    }
    public void setDetailDokumen(String detailDokumen) {
        if (detailDokumen == null || detailDokumen.trim().isEmpty()) {
            throw new IllegalArgumentException("Detail dokumen tidak boleh kosong");
        }
        if (detailDokumen.trim().length() > 255) {
            throw new IllegalArgumentException("Detail dokumen maksimal 255 karakter");
        }
        this.detailDokumen = detailDokumen.trim();
    }
}