/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class Fmt {
    private static final NumberFormat NF = NumberFormat.getInstance(Locale.forLanguageTag("id-ID"));
    public static String rupiah(double v) {
        return "Rp " + NF.format(Math.round(v));
    }
}
