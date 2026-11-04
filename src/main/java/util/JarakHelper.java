/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author HP
 */
public class JarakHelper {
    public static final double BATAS_KM = 30.0; // batas area layanan
    private static final Map<String, double[]> LOKASI = new LinkedHashMap<>();
    static {
        LOKASI.put("Universitas Mulawarman", new double[]{-0.4682, 117.1565});
        LOKASI.put("BigMall Samarinda", new double[]{-0.5040, 117.1290});
        LOKASI.put("Mall Lembuswana", new double[]{-0.4990, 117.1480});
        LOKASI.put("Samarinda Central Plaza", new double[]{-0.5026, 117.1488});
        LOKASI.put("Pasar Pagi", new double[]{-0.5016, 117.1537});
        LOKASI.put("Pelabuhan Samarinda", new double[]{-0.5105, 117.1570});
        LOKASI.put("Kantor Gubernur Kaltim", new double[]{-0.4938, 117.1420});
        LOKASI.put("RSUD AWS", new double[]{-0.4748, 117.1431});
        LOKASI.put("Terminal Sungai Kunjang", new double[]{-0.5490, 117.0940});
        LOKASI.put("Bandara APT Pranoto", new double[]{-0.3724, 117.2573});
        LOKASI.put("Jl. M. Yamin", new double[]{-0.4650, 117.1500});
        LOKASI.put("Jl. Pramuka", new double[]{-0.4580, 117.1450});
        LOKASI.put("Jl. Awang Long", new double[]{-0.4700, 117.1300});
        LOKASI.put("Jl. Juanda", new double[]{-0.4960, 117.1400});
        LOKASI.put("Jl. Antasari", new double[]{-0.5000, 117.1500});
        LOKASI.put("Jl. Cipto Mangunkusumo", new double[]{-0.4870, 117.1450});
        LOKASI.put("Jl. Ahmad Yani", new double[]{-0.4780, 117.1500});
        LOKASI.put("Kota Bangun (di luar area)", new double[]{-0.1700, 116.5500});
    }
    public static String[] daftarLokasi() {
        return LOKASI.keySet().toArray(new String[0]);
    }
    /** Jarak (km) rumus Haversine x faktor jalan 1.3. Return -1 jika lokasi tidak ada di daftar. */
    public static double hitungKm(String a, String b) {
        double[] p = cari(a), q = cari(b);
        if (p == null || q == null) return -1;
        double dLat = Math.toRadians(q[0] - p[0]), dLon = Math.toRadians(q[1] - p[1]);
        double h = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(p[0])) * Math.cos(Math.toRadians(q[0])) * Math.pow(Math.sin(dLon / 2), 2);
        double km = 2 * 6371 * Math.asin(Math.sqrt(h)) * 1.3;
        return Math.max(1.0, Math.round(km * 10) / 10.0);
    }
    private static double[] cari(String nama) {
        if (nama == null) return null;
        for (Map.Entry<String, double[]> e : LOKASI.entrySet())
            if (e.getKey().equalsIgnoreCase(nama.trim())) return e.getValue();
        return null;
    }
    public static boolean dalamBatas(double km) {
        return km >= 0 && km <= BATAS_KM;
    }
}
