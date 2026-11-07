/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author HP
 */
public class TarifHelper {
    public static final double BATAS_KM = 100.0;
    
        public enum Paket {
        // nama, jenis, kelas, kapasitas, tarif/km, tarif minimum, kecepatan (km/jam), buffer (menit), faktor jarak
        MOTOR_BIASA  ("Motor Biasa",   "motor", "Biasa",   1, 2500,  8000, 30, 3, 0.95),
        MOTOR_COMFORT("Motor Comfort", "motor", "Comfort", 1, 3500, 12000, 27, 3, 0.97),
        MOBIL_4_SEAT ("Mobil 4 Seat",  "mobil", "4 Seat",  4, 5000, 15000, 25, 5, 1.00),
        MOBIL_6_SEAT ("Mobil 6 Seat",  "mobil", "6 Seat",  6, 6500, 20000, 23, 5, 1.03),
        MOBIL_8_SEAT ("Mobil 8 Seat",  "mobil", "8 Seat",  8, 8000, 25000, 21, 5, 1.05);

        private final String nama;
        private final String jenisKendaraan;
        private final String kelas;
        private final int kapasitas;
        private final double tarifPerKm;
        private final double tarifMinimum;
        private final double kecepatanKmJam;
        private final int bufferMenit;
        private final double faktorJarak;

        Paket(String nama, String jenisKendaraan, String kelas, int kapasitas,
              double tarifPerKm, double tarifMinimum, double kecepatanKmJam,
              int bufferMenit, double faktorJarak) {
            this.nama = nama;
            this.jenisKendaraan = jenisKendaraan;
            this.kelas = kelas;
            this.kapasitas = kapasitas;
            this.tarifPerKm = tarifPerKm;
            this.tarifMinimum = tarifMinimum;
            this.kecepatanKmJam = kecepatanKmJam;
            this.bufferMenit = bufferMenit;
            this.faktorJarak = faktorJarak;
        }

        public String getNama() {
            return nama;
        }
        public String getJenisKendaraan() {
            return jenisKendaraan;
        }
        public String getKelas() {
            return kelas;
        }
        public int getKapasitas() {
            return kapasitas;
        }
        public double getTarifPerKm() {
            return tarifPerKm;
        }
        public double getTarifMinimum() {
            return tarifMinimum;
        }
        public double getKecepatanKmJam() {
            return kecepatanKmJam;
        }
        public int getBufferMenit() {
            return bufferMenit;
        }
        public double getFaktorJarak() {
            return faktorJarak;
        }

        @Override
        public String toString() {
            return nama;
        }
    }
        
        public enum PaketDokumen {
    //            nama,            kelas,    tarif/km, tarif min, km/jam, buffer(menit), faktor jarak
    REGULER("Dokumen Reguler", "Reguler", 3000, 10000, 25, 30, 1.00),
    KILAT("Dokumen Hemat", "Hemat", 5500, 20000, 40, 5, 0.95);

    private final String nama, kelas;
    private final double tarifPerKm, tarifMinimum, kecepatanKmJam, faktorJarak;
    private final int bufferMenit;

    PaketDokumen(String nama, String kelas, double tarifPerKm, double tarifMinimum,
                 double kecepatanKmJam, int bufferMenit, double faktorJarak) {
        this.nama = nama; this.kelas = kelas;
        this.tarifPerKm = tarifPerKm; this.tarifMinimum = tarifMinimum;
        this.kecepatanKmJam = kecepatanKmJam; this.bufferMenit = bufferMenit;
        this.faktorJarak = faktorJarak;
    }
    public String getNama() {
        return nama;
    }
    public String getKelas() {
        return kelas;
    }
    public double getTarifPerKm() {
        return tarifPerKm;
    }
    public double getTarifMinimum() {
        return tarifMinimum;
    }
    public double getKecepatanKmJam() {
        return kecepatanKmJam;
    }
    public int getBufferMenit() {
        return bufferMenit;
    }
    public double getFaktorJarak() {
        return faktorJarak;
    }
    @Override public String toString() {
        return nama;
    }
}

    //Jarak (km) untuk paket ini, dihitung dari jarak rute peta x faktor paket. Dibulatkan 1 desimal.
    public static double hitungKm(Paket paket, double kmPeta) {
        if (paket == null || kmPeta < 0) return -1;
        return Math.round(kmPeta * paket.getFaktorJarak() * 10) / 10.0;
    }

    //Harga = tarif/km x km paket, minimal tarif minimum, dibulatkan ke atas per Rp 500.
    public static double hitungHarga(Paket paket, double kmPeta) {
        double km = hitungKm(paket, kmPeta);
        if (km < 0) return -1;
        double harga = Math.max(paket.getTarifMinimum(), paket.getTarifPerKm() * km);
        return Math.ceil(harga / 500.0) * 500.0;
    }

    //Estimasi waktu (menit) = km paket / kecepatan paket + buffer.
    public static int hitungMenit(Paket paket, double kmPeta) {
        double km = hitungKm(paket, kmPeta);
        if (km < 0) return -1;
        return (int) Math.ceil((km / paket.getKecepatanKmJam()) * 60) + paket.getBufferMenit();
    }
    
    public static double hitungKm(PaketDokumen p, double kmPeta) {
        if (p == null || kmPeta < 0) return -1;
        return Math.round(kmPeta * p.getFaktorJarak() * 10) / 10.0;
    }

    public static double hitungHarga(PaketDokumen p, double kmPeta) {
        double km = hitungKm(p, kmPeta);
        if (km < 0) return -1;
        double harga = Math.max(p.getTarifMinimum(), p.getTarifPerKm() * km);
        return Math.ceil(harga / 500.0) * 500.0;
    }

    public static int hitungMenit(PaketDokumen p, double kmPeta) {
        double km = hitungKm(p, kmPeta);
        if (km < 0) return -1;
        return (int) Math.ceil((km / p.getKecepatanKmJam()) * 60) + p.getBufferMenit();
    }

    public static boolean dalamBatas(double kmPeta) {
        return kmPeta >= 0 && kmPeta <= BATAS_KM;
    }

    public static Paket[] daftarPaket(String jenisKendaraan) {
        return java.util.Arrays.stream(Paket.values())
                .filter(p -> p.getJenisKendaraan().equalsIgnoreCase(jenisKendaraan))
                .toArray(Paket[]::new);
    }

    public static Paket cari(String jenisKendaraan, String kelas) {
        if (jenisKendaraan == null || kelas == null) return null;
        for (Paket p : Paket.values()) {
            if (p.getJenisKendaraan().equalsIgnoreCase(jenisKendaraan.trim())
                    && p.getKelas().equalsIgnoreCase(kelas.trim())) {
                return p;
            }
        }
        return null;
    }
}
