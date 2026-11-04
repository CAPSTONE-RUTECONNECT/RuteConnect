/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author HP
 */
public class Hasil {
    private final boolean sukses;
    private final String pesan;
    private Hasil(boolean sukses, String pesan) {
        this.sukses = sukses; this.pesan = pesan;
    }
    public static Hasil ok(String pesan) {
        return new Hasil(true, pesan);
    }
    public static Hasil gagal(String pesan) {
        return new Hasil(false, pesan);
    }
    public boolean isSukses() {
        return sukses;
    }
    public String getPesan() {
        return pesan;
    }
}
