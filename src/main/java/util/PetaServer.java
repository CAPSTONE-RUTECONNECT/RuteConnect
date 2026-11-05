/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;

/**
 *
 * @author HP
 */
public class PetaServer {
    // Data yang dikirim peta setelah rute ketemu.
    public record Hasil(double jemputLat, double jemputLng,
                        double tujuanLat, double tujuanLng,
                        double km, int menit,
                        String namaJemput, String namaTujuan) {}

    private static final int PORT = 8765;
    private HttpServer server;
    private final Consumer<Hasil> onHasil;

    // onHasil dipanggil di thread Swing, jadi aman dipakai untuk mengisi komponen GUI.
    public PetaServer(Consumer<Hasil> onHasil) {
        this.onHasil = onHasil;
    }

    public void mulai() throws IOException {
        if (server != null) return;
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", PORT), 0);

        // kirim index.html ke browser
        server.createContext("/", ex -> {
            try (InputStream in = PetaServer.class.getResourceAsStream("/main/resources/index.html")) {
                if (in == null) {
                    ex.sendResponseHeaders(404, -1);
                    return;
                }
                byte[] data = in.readAllBytes();
                ex.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                ex.sendResponseHeaders(200, data.length);
                ex.getResponseBody().write(data);
            } finally {
                ex.close();
            }
        });
        
        // terima hasil rute dari browser
        server.createContext("/hasil", ex -> {
            try {
                String body = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String[] p = body.split("\\|");   // jemputLat|jemputLng|tujuanLat|tujuanLng|km|menit|namaJemput|namaTujuan
                if (p.length == 8) {
                    Hasil h = new Hasil(
                        Double.parseDouble(p[0]), Double.parseDouble(p[1]),
                        Double.parseDouble(p[2]), Double.parseDouble(p[3]),
                        Double.parseDouble(p[4]), Integer.parseInt(p[5].trim()),
                        p[6], p[7]);
                    SwingUtilities.invokeLater(() -> onHasil.accept(h));
                }
                ex.sendResponseHeaders(200, -1);
            } catch (NumberFormatException e) {
                ex.sendResponseHeaders(400, -1);
            } finally {
                ex.close();
            }
        });
        
        server.start();
    }

    public void bukaDiBrowser() throws Exception {
        Desktop.getDesktop().browse(URI.create("http://localhost:" + PORT + "/"));
    }

    public void berhenti() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }
}
