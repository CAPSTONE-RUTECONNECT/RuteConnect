/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author HP
 */
public class Validator {
    public static boolean isKosong(String... fields) {
        for (String f : fields) {
            if (f == null || f.trim().isEmpty()) return true;
        }
        return false;
    }
    public static boolean emailValid(String email) {
        return email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$");
    }
    public static boolean passwordValid(String pass) {
        return pass.length() >= 8 && pass.matches(".*[A-Za-z].*") && pass.matches(".*\\d.*");
    }
    public static boolean teleponValid(String t) {
        return t != null && t.trim().matches("^08\\d{8,13}$");
    }
}
