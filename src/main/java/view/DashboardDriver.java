/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import java.awt.CardLayout;
import config.Koneksi;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author HP
 */
public class DashboardDriver extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardDriver.class.getName());
    CardLayout cardLayout;
    private int driverId = 2;          // sementara hardcode, nanti ambil dari login
    private int pesananAktifId = -1;
    private String statusAktif;
    
    private javax.swing.Timer timerMasuk;
    private Kartu kDokumen, kRide, kCar;

    // Menyimpan label & tombol milik satu card supaya diisi dengan 1 method yang sama
    private static class Kartu {
        final String jenisPesanan, jenisKendaraan, nama, teksTab;
        final JButton tab, tolak, terima;
        final JLabel header, judul, harga, metode, ket, kiriJudul, kananJudul, kiriNilai, kananNilai;
        model.Pesanan data;   // pesanan yang sedang tampil di card (null = kosong)

        Kartu(String jenisPesanan, String jenisKendaraan, String nama, JButton tab,
              JLabel header, JLabel judul, JLabel harga, JLabel metode, JLabel ket,
              JLabel kiriJudul, JLabel kananJudul, JLabel kiriNilai, JLabel kananNilai,
              JButton tolak, JButton terima) {
            this.jenisPesanan = jenisPesanan;
            this.jenisKendaraan = jenisKendaraan;
            this.nama = nama;
            this.tab = tab;
            this.teksTab = tab.getText();
            this.header = header;
            this.judul = judul;
            this.harga = harga;
            this.metode = metode;
            this.ket = ket;
            this.kiriJudul = kiriJudul;
            this.kananJudul = kananJudul;
            this.kiriNilai = kiriNilai;
            this.kananNilai = kananNilai;
            this.tolak = tolak;
            this.terima = terima;
        }
    }

    private void setupTabelOrder() {
        DefaultTableModel m = (DefaultTableModel) tblOrder.getModel();
        m.setRowCount(3);
        m.setColumnCount(3);

        tblOrder.setRowHeight(44);                       // muat 2 baris teks (judul + keterangan kecil)
        tblOrder.setTableHeader(null);                   // buang header
        jScrollPane1.setColumnHeaderView(null);          // header "Title 1/2/3" tidak ikut tampil
        tblOrder.setFillsViewportHeight(true);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        tblOrder.setIntercellSpacing(new Dimension(0, 0));
        tblOrder.getColumnModel().getColumn(0).setMaxWidth(40);
        tblOrder.getColumnModel().getColumn(2).setMaxWidth(90);

        tblOrder.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int row, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, false, false, row, col);
                l.setHorizontalAlignment(col == 0 ? CENTER : (col == 2 ? RIGHT : LEFT));
                l.setBackground(row == 1 ? new Color(224, 242, 254) : Color.WHITE);
                l.setForeground(row == 0 ? new Color(5, 150, 105)
                              : row == 1 ? new Color(2, 132, 199)
                              : new Color(100, 116, 139));
                return l;
            }
        });
    }
    
    private String teksTombol(String s) {
        if (model.Pesanan.DITERIMA.equals(s)) {
            return "Menuju Lokasi Jemput";
        }
        if (model.Pesanan.DIPROSES.equals(s)) {
            return "Sudah Dijemput";
        }
        return "Selesaikan Pesanan";
    }
    
    private void loadOrderBerjalan() {
        String sql = "SELECT p.pesanan_id, p.lokasi_jemput, p.lokasi_tujuan, p.waktu_penjemputan, "
                   + "p.status_pesanan, d.nama_penerima "
                   + "FROM pesanan p LEFT JOIN dokumen d ON d.pesanan_id = p.pesanan_id "
                   + "WHERE p.driver_id = ? AND p.status_pesanan IN ('Diterima','Diproses','Dalam Perjalanan') "
                   + "ORDER BY p.pesanan_id LIMIT 1";
        DefaultTableModel m = (DefaultTableModel) tblOrder.getModel();

        Connection c = Koneksi.getConnection();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Tidak bisa terhubung ke database.");
            return;
        }
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pesananAktifId = rs.getInt("pesanan_id");
                    statusAktif = rs.getString("status_pesanan");
                    btnSelesai.setText(teksTombol(statusAktif));
                    btnSelesai.setSelected(false);
                    lblJudul.setText("Order Berjalan #" + pesananAktifId);

                    String jam = new SimpleDateFormat("HH:mm 'WIB'")
                                    .format(rs.getTimestamp("waktu_penjemputan"));
                    String penerima = rs.getString("nama_penerima");

                    m.setValueAt("✔", 0, 0);
                    m.setValueAt("<html><b>Diterima oleh Driver</b><br><small>"
                            + rs.getString("lokasi_jemput") + "</small>", 0, 1);
                    m.setValueAt(jam, 0, 2);

                    m.setValueAt("➜", 1, 0);
                    m.setValueAt("<html><b>Antar ke Tujuan</b><br><small>"
                            + (penerima != null ? "Penerima: " + penerima : rs.getString("lokasi_tujuan"))
                            + "</small>", 1, 1);
                    m.setValueAt(rs.getString("status_pesanan"), 1, 2);

                    m.setValueAt("●", 2, 0);
                    m.setValueAt("<html><b>Pesanan Selesai</b><br><small>Konfirmasi serah terima</small>", 2, 1);
                    m.setValueAt("", 2, 2);

                    btnBatalkan.setEnabled(true);
                    btnSelesai.setEnabled(true);
                } else {
                    pesananAktifId = -1;
                    lblJudul.setText("Tidak ada order berjalan");
                    statusAktif = null;
                    btnSelesai.setText("Selesaikan Pesanan");
                    btnSelesai.setSelected(false);
                    
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++) m.setValueAt("", i, j);
                    btnBatalkan.setEnabled(false);
                    btnSelesai.setEnabled(false);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal load order: " + ex.getMessage());
        }
    }
    
    private void muatProfilDriver() {
        model.Driver d = new dao.DriverDao().findById(driverId);
        if (d == null) {
            lblNamaDriver.setText("Driver tidak ditemukan");
            lblMerkKendaraan.setText("-");
            lblPlat.setText("-");
            return;
        }
        lblNamaDriver.setText(d.getNama());
        lblMerkKendaraan.setText(d.getMerkKendaraan());
        lblPlat.setText(d.getNoPlatKendaraan());
    }
    
    private void siapkanKartu() {
        kDokumen = new Kartu("Dokumen", "Motor", "Pesanan Dokumen", btnSatu,
                jLabel10, jLabel11, jLabel12, jLabel13, jLabel18,
                jLabel14, jLabel15, jLabel16, jLabel17, btnTolakDokumen, btnTerimaDokumen);
        kRide = new Kartu("Transportasi", "Motor", "Pesanan RuteRide", btnDua,
                jLabel19, jLabel20, jLabel21, jLabel22, jLabel27,
                jLabel23, jLabel24, jLabel25, jLabel26, btnTolakRide, btnTerimaRide);
        kCar = new Kartu("Transportasi", "Mobil", "Pesanan RuteCar", btnTiga,
                jLabel28, jLabel29, jLabel30, jLabel31, jLabel36,
                jLabel32, jLabel33, jLabel34, jLabel35, btnTolakCar, btnTerimaCar);
        for (Kartu k : new Kartu[]{kDokumen, kRide, kCar}) {
            Dimension kolomKiri = new Dimension(217, 20);
            for (JLabel l : new JLabel[]{k.ket, k.judul}) {
                l.setPreferredSize(kolomKiri);
                l.setMinimumSize(kolomKiri);
                l.setMaximumSize(kolomKiri);
            }
        }
    }

    // Ambil pesanan "Menunggu" dari database lalu isi ketiga card
    private void muatPesananMasuk() {
        if (kDokumen == null) return;
        model.Driver d = new dao.DriverDao().findById(driverId);
        String jenisDriver = (d == null || d.getJenisKendaraan() == null) ? "" : d.getJenisKendaraan();
        boolean aktif = d != null && d.isAktif();
        for (Kartu k : new Kartu[]{kDokumen, kRide, kCar}) {
            isiKartu(k, jenisDriver, aktif);
        }
    }

    private void isiKartu(Kartu k, String jenisDriver, boolean aktif) {
        k.data = null;
        String info = "Pesanan baru akan muncul di sini";

        if (!aktif) {
            info = "Status Non-Aktif, tidak bisa menerima pesanan";
        } else if (!k.jenisKendaraan.equalsIgnoreCase(jenisDriver)) {
            info = "Khusus driver " + k.jenisKendaraan.toLowerCase();
        } else {
            for (model.Pesanan x : new dao.PesananDao().findTersedia(k.jenisPesanan, k.jenisKendaraan)) {
                if (!controller.Session.ditolak.contains(x.getPesananId())) {
                    k.data = x;
                    break;
                }
            }
        }

        k.kiriJudul.setText("Waktu Jemput");
        k.kananJudul.setText("Total Rute");
        model.Pesanan pm = k.data;

        if (pm == null) {
            k.header.setText("Tidak ada pesanan masuk");
            k.judul.setText("-");
            k.harga.setText("Rp 0");
            k.metode.setText("-");
            k.ket.setText(info);
            k.ket.setToolTipText(null);
            k.kiriNilai.setText("-");
            k.kananNilai.setText("-");
        } else {
            k.header.setText(k.nama + " Masuk!");
            k.judul.setText(pm.getNamaLayanan());
            k.harga.setText(controller.Fmt.rupiah(pm.getTotalBiaya()));
            k.metode.setText(tampilMetode(pm.getMetodePembayaran()));
            k.ket.setText(pm.getLokasiJemput() + " → " + pm.getLokasiTujuan());
            k.ket.setToolTipText(ringkasanPesanan(pm));
            k.kiriNilai.setText(new SimpleDateFormat("HH:mm 'WIB'").format(pm.getWaktuPenjemputan()));
            k.kananNilai.setText((Math.round(pm.getJarakKm() * 10) / 10.0) + " Km");
        }

        k.terima.setEnabled(pm != null);
        k.tolak.setEnabled(pm != null);
        k.tab.setText(pm != null ? k.teksTab + " ●" : k.teksTab);   // tanda ada pesanan di tab
    }

    private String tampilMetode(String m) {
        if (m == null) return "-";
        if (m.equalsIgnoreCase("Saldo")) return "RutePay";
        if (m.equalsIgnoreCase("Cash")) return "Tunai";
        return m;
    }

    private String esc(String s) {
        return s == null ? "" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    // Detail lengkap pesanan (dipakai untuk tooltip & dialog konfirmasi)
    private String ringkasanPesanan(model.Pesanan p) {
        StringBuilder sb = new StringBuilder("<html><body style='width:280px'>");
        sb.append("<b>").append(esc(p.getNamaLayanan())).append("</b> (#").append(p.getPesananId()).append(")<br><br>");
        sb.append("Pelanggan: ").append(esc(p.getNamaPelanggan())).append("<br>");
        sb.append("Jemput: ").append(esc(p.getLokasiJemput())).append("<br>");
        sb.append("Tujuan: ").append(esc(p.getLokasiTujuan())).append("<br>");
        sb.append("Jarak: ").append(Math.round(p.getJarakKm() * 10) / 10.0).append(" Km<br>");
        sb.append("Tarif: ").append(controller.Fmt.rupiah(p.getTotalBiaya()))
          .append(" (").append(tampilMetode(p.getMetodePembayaran())).append(")<br>");
        if (p instanceof model.Transportasi tr) {
            sb.append("Penumpang: ").append(tr.getJumlahPenumpang()).append("<br>");
        } else if (p instanceof model.Dokumen dk) {
            sb.append("Penerima: ").append(esc(dk.getNamaPenerima())).append(" (")
              .append(esc(dk.getNoTeleponPenerima())).append(")<br>");
            sb.append("Dokumen: ").append(esc(dk.getDetailDokumen())).append("<br>");
        }
        if (p.getCatatan() != null && !p.getCatatan().isEmpty()) {
            sb.append("Catatan: ").append(esc(p.getCatatan())).append("<br>");
        }
        return sb.append("</body></html>").toString();
    }

    private void terimaKartu(Kartu k) {
        model.Pesanan p = k.data;
        if (p == null) return;
        int pilih = JOptionPane.showConfirmDialog(this, ringkasanPesanan(p),
                "Terima pesanan ini?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pilih != JOptionPane.YES_OPTION) return;

        service.Hasil h = new service.PesananService().terima(p.getPesananId(), driverId);
        JOptionPane.showMessageDialog(this, h.getPesan());
        muatPesananMasuk();
        loadOrderBerjalan();
    }

    // Tolak = sembunyikan pesanan ini dari layar driver ini saja, pesanan tetap Menunggu untuk driver lain
    private void tolakKartu(Kartu k) {
        if (k.data == null) return;
        controller.Session.ditolak.add(k.data.getPesananId());
        muatPesananMasuk();
    }

    @Override
    public void dispose() {
        if (timerMasuk != null) timerMasuk.stop();
        super.dispose();
    }
    
    private boolean updateStatus(String status, String alasan) {
        if (pesananAktifId == -1) {
            return false;
        }

        String sql = "UPDATE pesanan "
                   + "SET status_pesanan = ?, alasan_batal = ? "
                   + "WHERE pesanan_id = ? AND driver_id = ?";

        Connection c = Koneksi.getConnection();

        if (c == null) {
            JOptionPane.showMessageDialog(this,
                    "Tidak bisa terhubung ke database.");
            return false;
        }

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, alasan);
            ps.setInt(3, pesananAktifId);
            ps.setInt(4, driverId);

            int hasil = ps.executeUpdate();

            if (hasil > 0) {
                loadOrderBerjalan();
                muatPesananMasuk();
                return true;
            }

            JOptionPane.showMessageDialog(this,
                    "Pesanan gagal diperbarui atau sudah tidak tersedia.");
            return false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal update: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * Creates new form DashboardDriver
     */
    public DashboardDriver() {
        initComponents();
        setLocationRelativeTo(null);
        cardLayout = (CardLayout) jPanel6.getLayout();

        if (controller.Session.userId > 0) {
            driverId = controller.Session.userId;
        }
        muatProfilDriver();

        panelOrder.setBounds(10, 525, 370, 280);   // posisi di jPanel1
        setupTabelOrder();
        siapkanKartu();
        muatPesananMasuk();
        loadOrderBerjalan();

        btnSatu.addActionListener(e -> { cardLayout.show(jPanel6, "card1"); muatPesananMasuk(); });
        btnDua.addActionListener(e  -> { cardLayout.show(jPanel6, "card2"); muatPesananMasuk(); });
        btnTiga.addActionListener(e -> { cardLayout.show(jPanel6, "card3"); muatPesananMasuk(); });

        // Cek pesanan masuk dan status order berjalan setiap 5 detik
        timerMasuk = new javax.swing.Timer(5000, e -> {
            muatPesananMasuk();
            loadOrderBerjalan();
        });
        timerMasuk.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNamaDriver = new javax.swing.JLabel();
        lblMerkKendaraan = new javax.swing.JLabel();
        lblPlat = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cardSatu = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnTolakDokumen = new javax.swing.JButton();
        btnTerimaDokumen = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cardDua = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnTolakRide = new javax.swing.JButton();
        btnTerimaRide = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        cardTiga = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnTolakCar = new javax.swing.JButton();
        btnTerimaCar = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        btnDua = new javax.swing.JButton();
        btnTiga = new javax.swing.JButton();
        btnSatu = new javax.swing.JButton();
        panelOrder = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnSelesai = new javax.swing.JToggleButton();
        btnBatalkan = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Driver");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(2, 132, 199));
        jPanel2.setPreferredSize(new java.awt.Dimension(390, 50));
        jPanel2.setLayout(null);

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setForeground(new java.awt.Color(0, 0, 0));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(10).png"))); // NOI18N
        btnLogout.setText("Keluar");
        btnLogout.setBorder(null);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        jPanel2.add(btnLogout);
        btnLogout.setBounds(10, 10, 70, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/logo ruteconnect logo kecil.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(90, 0, 40, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RuteConnect");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(130, 0, 140, 30);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard Mitra Pengemudi");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(130, 20, 200, 30);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background (6).png"))); // NOI18N
        jPanel2.add(jLabel4);
        jLabel4.setBounds(320, 0, 50, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 390, 50);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background+Shadow.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(16, 61, 59, 58);

        lblNamaDriver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNamaDriver.setForeground(new java.awt.Color(0, 0, 0));
        lblNamaDriver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/CENTANG BIRU.png"))); // NOI18N
        lblNamaDriver.setText("Abdul Qodir");
        jPanel1.add(lblNamaDriver);
        lblNamaDriver.setBounds(81, 70, 157, 20);

        lblMerkKendaraan.setForeground(new java.awt.Color(0, 0, 0));
        lblMerkKendaraan.setText("Honda Vario");
        jPanel1.add(lblMerkKendaraan);
        lblMerkKendaraan.setBounds(81, 96, 147, 16);

        lblPlat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPlat.setForeground(new java.awt.Color(0, 0, 0));
        lblPlat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPlat.setText("KT 1234 AB");
        jPanel1.add(lblPlat);
        lblPlat.setBounds(254, 96, 107, 16);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background+Border.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(16, 137, 355, 52);

        jPanel6.setLayout(new java.awt.CardLayout());

        cardSatu.setBackground(new java.awt.Color(14, 165, 233));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pesanan Dokumen Masuk!");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kirim Dokumen");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(252, 211, 77));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Rp 0");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Tunai");

        jPanel3.setBackground(new java.awt.Color(11, 137, 195));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Jarak Jemput");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Total Rute");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("0 Km (0 Menit)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("0 Km (0 Menit)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnTolakDokumen.setBackground(new java.awt.Color(11, 137, 195));
        btnTolakDokumen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTolakDokumen.setForeground(new java.awt.Color(255, 255, 255));
        btnTolakDokumen.setText("Tolak");
        btnTolakDokumen.addActionListener(this::btnTolakDokumenActionPerformed);

        btnTerimaDokumen.setBackground(new java.awt.Color(255, 255, 255));
        btnTerimaDokumen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTerimaDokumen.setForeground(new java.awt.Color(14, 165, 233));
        btnTerimaDokumen.setText("Terima Pesanan →");
        btnTerimaDokumen.addActionListener(this::btnTerimaDokumenActionPerformed);

        jLabel18.setForeground(new java.awt.Color(224, 242, 254));
        jLabel18.setText("Keterangan");

        javax.swing.GroupLayout cardSatuLayout = new javax.swing.GroupLayout(cardSatu);
        cardSatu.setLayout(cardSatuLayout);
        cardSatuLayout.setHorizontalGroup(
            cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSatuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardSatuLayout.createSequentialGroup()
                        .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cardSatuLayout.createSequentialGroup()
                        .addComponent(btnTolakDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTerimaDokumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardSatuLayout.setVerticalGroup(
            cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSatuLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardSatuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTolakDokumen, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnTerimaDokumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel6.add(cardSatu, "card1");

        cardDua.setBackground(new java.awt.Color(14, 165, 233));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Pesanan RuteRide Masuk!");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Pesanan RuteRide");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(252, 211, 77));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Rp 0");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Tunai");

        jPanel4.setBackground(new java.awt.Color(11, 137, 195));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Jarak Jemput");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Total Rute");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("0 Km (0 Menit)");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("0 Km (0 Menit)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnTolakRide.setBackground(new java.awt.Color(11, 137, 195));
        btnTolakRide.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTolakRide.setForeground(new java.awt.Color(255, 255, 255));
        btnTolakRide.setText("Tolak");
        btnTolakRide.addActionListener(this::btnTolakRideActionPerformed);

        btnTerimaRide.setBackground(new java.awt.Color(255, 255, 255));
        btnTerimaRide.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTerimaRide.setForeground(new java.awt.Color(14, 165, 233));
        btnTerimaRide.setText("Terima Pesanan →");
        btnTerimaRide.addActionListener(this::btnTerimaRideActionPerformed);

        jLabel27.setForeground(new java.awt.Color(224, 242, 254));
        jLabel27.setText("Keterangan Lokasi");

        javax.swing.GroupLayout cardDuaLayout = new javax.swing.GroupLayout(cardDua);
        cardDua.setLayout(cardDuaLayout);
        cardDuaLayout.setHorizontalGroup(
            cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDuaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardDuaLayout.createSequentialGroup()
                        .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cardDuaLayout.createSequentialGroup()
                        .addComponent(btnTolakRide, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTerimaRide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardDuaLayout.setVerticalGroup(
            cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDuaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardDuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTolakRide, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnTerimaRide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel6.add(cardDua, "card2");

        cardTiga.setBackground(new java.awt.Color(14, 165, 233));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Pesanan RuteCar Masuk!");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Pesanan RuteCar");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(252, 211, 77));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Rp 0");

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Tunai");

        jPanel5.setBackground(new java.awt.Color(11, 137, 195));

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Jarak Jemput");

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Total Rute");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("0 Km (0 Menit)");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("0 Km (0 Menit)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnTolakCar.setBackground(new java.awt.Color(11, 137, 195));
        btnTolakCar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTolakCar.setForeground(new java.awt.Color(255, 255, 255));
        btnTolakCar.setText("Tolak");
        btnTolakCar.addActionListener(this::btnTolakCarActionPerformed);

        btnTerimaCar.setBackground(new java.awt.Color(255, 255, 255));
        btnTerimaCar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTerimaCar.setForeground(new java.awt.Color(14, 165, 233));
        btnTerimaCar.setText("Terima Pesanan →");
        btnTerimaCar.addActionListener(this::btnTerimaCarActionPerformed);

        jLabel36.setForeground(new java.awt.Color(224, 242, 254));
        jLabel36.setText("Keterangan Lokasi");

        javax.swing.GroupLayout cardTigaLayout = new javax.swing.GroupLayout(cardTiga);
        cardTiga.setLayout(cardTigaLayout);
        cardTigaLayout.setHorizontalGroup(
            cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTigaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardTigaLayout.createSequentialGroup()
                        .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cardTigaLayout.createSequentialGroup()
                        .addComponent(btnTolakCar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTerimaCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardTigaLayout.setVerticalGroup(
            cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTigaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardTigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTolakCar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnTerimaCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel6.add(cardTiga, "card3");

        jPanel1.add(jPanel6);
        jPanel6.setBounds(10, 260, 370, 255);

        btnDua.setBackground(new java.awt.Color(2, 132, 199));
        btnDua.setForeground(new java.awt.Color(255, 255, 255));
        btnDua.setText("RuteRide");
        jPanel1.add(btnDua);
        btnDua.setBounds(30, 210, 100, 40);

        btnTiga.setBackground(new java.awt.Color(2, 132, 199));
        btnTiga.setForeground(new java.awt.Color(255, 255, 255));
        btnTiga.setText("RuteCar");
        jPanel1.add(btnTiga);
        btnTiga.setBounds(140, 210, 100, 40);

        btnSatu.setBackground(new java.awt.Color(2, 132, 199));
        btnSatu.setForeground(new java.awt.Color(255, 255, 255));
        btnSatu.setText("Kirim Dokumen");
        jPanel1.add(btnSatu);
        btnSatu.setBounds(254, 210, 116, 40);

        panelOrder.setBackground(new java.awt.Color(242, 243, 255));
        panelOrder.setLayout(new java.awt.BorderLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(0, 0, 0));
        lblJudul.setText("Order Berjalan #");
        panelOrder.add(lblJudul, java.awt.BorderLayout.PAGE_START);

        tblOrder.setBackground(new java.awt.Color(255, 255, 255));
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tblOrder.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tblOrder);

        panelOrder.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(242, 243, 255));

        btnSelesai.setBackground(new java.awt.Color(14, 165, 233));
        btnSelesai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSelesai.setForeground(new java.awt.Color(255, 255, 255));
        btnSelesai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(12).png"))); // NOI18N
        btnSelesai.setText("Selesaikan Pesanan");
        btnSelesai.addActionListener(this::btnSelesaiActionPerformed);

        btnBatalkan.setBackground(new java.awt.Color(255, 255, 255));
        btnBatalkan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBatalkan.setForeground(new java.awt.Color(14, 165, 233));
        btnBatalkan.setText("Batalkan");
        btnBatalkan.addActionListener(this::btnBatalkanActionPerformed);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBatalkan, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatalkan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelOrder.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(panelOrder);
        panelOrder.setBounds(0, 530, 390, 390);

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTerimaDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerimaDokumenActionPerformed
        // TODO add your handling code here:
        terimaKartu(kDokumen);
    }//GEN-LAST:event_btnTerimaDokumenActionPerformed

    private void btnTerimaRideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerimaRideActionPerformed
        // TODO add your handling code here:
        terimaKartu(kRide);
    }//GEN-LAST:event_btnTerimaRideActionPerformed

    private void btnTerimaCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerimaCarActionPerformed
        // TODO add your handling code here:
        terimaKartu(kCar);
    }//GEN-LAST:event_btnTerimaCarActionPerformed

    private void btnTolakCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTolakCarActionPerformed
        // TODO add your handling code here:
        tolakKartu(kCar);
    }//GEN-LAST:event_btnTolakCarActionPerformed

    private void btnTolakRideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTolakRideActionPerformed
        // TODO add your handling code here:
        tolakKartu(kRide);
    }//GEN-LAST:event_btnTolakRideActionPerformed

    private void btnTolakDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTolakDokumenActionPerformed
        // TODO add your handling code here:
        tolakKartu(kDokumen);
    }//GEN-LAST:event_btnTolakDokumenActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int pilihan = JOptionPane.showConfirmDialog(
            this, 
            "Apakah Anda yakin ingin keluar?", 
            "Konfirmasi Logout", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (pilihan == JOptionPane.YES_OPTION) {
            controller.AuthService.logout();
            java.awt.EventQueue.invokeLater(() -> new view.FrameMenu().setVisible(true));
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:
        btnSelesai.setSelected(false);
        if (pesananAktifId == -1) {
            JOptionPane.showMessageDialog(this, "Tidak ada pesanan yang sedang berjalan.");
            return;
        }
        String next = service.PesananService.berikutnya(statusAktif);
        if (next == null) {
            JOptionPane.showMessageDialog(this, "Status tidak bisa diubah lagi.");
            return;
        }
        String tanya = model.Pesanan.SELESAI.equals(next)
                ? "Apakah Anda yakin pesanan ini sudah selesai diantar?"
                : "Ubah status pesanan menjadi \"" + next + "\"?";
        int pilih = JOptionPane.showConfirmDialog(this, tanya, "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pilih != JOptionPane.YES_OPTION) return;

        service.Hasil h = new service.PesananService().ubahStatus(pesananAktifId, driverId);
        JOptionPane.showMessageDialog(this, h.getPesan());
        loadOrderBerjalan();
        muatPesananMasuk();
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void btnBatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanActionPerformed
        // TODO add your handling code here:
        if (pesananAktifId == -1) {
            JOptionPane.showMessageDialog(this,
                    "Tidak ada pesanan yang sedang berjalan.");
            return;
        }

        // Daftar alasan pembatalan
        String[] daftarAlasan = {
            "Pelanggan tidak dapat dihubungi / tidak berada di lokasi",
            "Pelanggan meminta pesanan dibatalkan",
            "Alamat penjemputan terlalu jauh / tidak sesuai",
            "Kendala kendaraan / pribadi",
            "Muatan / penumpang melebihi kapasitas"
        };

        // Dropdown pilihan alasan
        JComboBox<String> cmbAlasan = new JComboBox<>(daftarAlasan);
        cmbAlasan.setSelectedIndex(-1);

        JPanel panel = new JPanel(new BorderLayout(5, 10));
        panel.add(new JLabel("Pilih alasan pembatalan pesanan:"), BorderLayout.NORTH);
        panel.add(cmbAlasan, BorderLayout.CENTER);

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Batalkan Pesanan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // Jika driver menekan Cancel atau menutup dialog
        if (pilihan != JOptionPane.OK_OPTION) {
            return;
        }

        String alasan = (String) cmbAlasan.getSelectedItem();

        // Pastikan driver memilih alasan
        if (alasan == null) {
            JOptionPane.showMessageDialog(this,
                    "Silakan pilih alasan pembatalan terlebih dahulu.");
            return;
        }

        // Konfirmasi pembatalan
        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Alasan pembatalan:\n" + alasan
                        + "\n\nApakah Anda yakin ingin membatalkan pesanan ini?",
                "Konfirmasi Pembatalan",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            service.Hasil h = new service.PesananService()
                    .batalkanOlehDriver(pesananAktifId, driverId, alasan);
            JOptionPane.showMessageDialog(this, h.getPesan());
            loadOrderBerjalan();
            muatPesananMasuk();
        }
    }//GEN-LAST:event_btnBatalkanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DashboardDriver().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBatalkan;
    private javax.swing.JButton btnDua;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSatu;
    private javax.swing.JToggleButton btnSelesai;
    private javax.swing.JButton btnTerimaCar;
    private javax.swing.JButton btnTerimaDokumen;
    private javax.swing.JButton btnTerimaRide;
    private javax.swing.JButton btnTiga;
    private javax.swing.JButton btnTolakCar;
    private javax.swing.JButton btnTolakDokumen;
    private javax.swing.JButton btnTolakRide;
    private javax.swing.JPanel cardDua;
    private javax.swing.JPanel cardSatu;
    private javax.swing.JPanel cardTiga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblMerkKendaraan;
    private javax.swing.JLabel lblNamaDriver;
    private javax.swing.JLabel lblPlat;
    private javax.swing.JPanel panelOrder;
    private javax.swing.JTable tblOrder;
    // End of variables declaration//GEN-END:variables
}
