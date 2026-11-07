/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import controller.UserController;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import controller.TarifController;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author HP
 */
public class FrameAdmin extends javax.swing.JFrame {
    private static final java.awt.Color BIRU = new java.awt.Color(14, 165, 233);
    private static final java.awt.Color PUTIH  = new java.awt.Color(255, 255, 255);
    private javax.swing.JPanel[] menuPanels;
    private javax.swing.JLabel[] menuLabels;
    
    private UserController userController = new UserController();
    private TarifController tarifController = new TarifController();
    private String kategoriPesanan = "Semua Layanan";
    private final dao.PesananDao pesananDao = new dao.PesananDao();
    private final dao.TransaksiDao transaksiDao = new dao.TransaksiDao();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameAdmin.class.getName());

    /**
     * Creates new form FrameAdmin
     */
    public FrameAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        
        setupMenu();
        
        cardSub.removeAll();
        cardSub.setLayout(new java.awt.CardLayout());
        cardSub.add(cardUser, "cardUser");
        cardSub.add(cardTarif, "cardTarif"); // Pastikan ini terdaftar
        cardSub.setBounds(6, 250, 1040, 380);

        javax.swing.ButtonGroup bg = new javax.swing.ButtonGroup();
        bg.add(btnUser);
        bg.add(btnTarif);
        btnUser.setSelected(true);

        // tabel user: 6 kolom sesuai cariUser(), tidak bisa diedit
        jTable2.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nama", "Email", "No. Telp", "Status", "Role"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });

        // pencarian dan filter
        jComboBox1.addActionListener(e -> loadUserData());
        btnHapusUser.addActionListener(e -> hapusUserTerpilih());
        
        jTable3.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nama Layanan", "Jenis Kendaraan", "Kelas", "Tarif per Km (Rp)"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        
        jComboBox2.addActionListener(e -> loadTarifData());
        loadTarifData();
        loadUserData();
        
        jTable1.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Pesanan", "Pelanggan", "Driver", "Layanan", "Metode Pembayaran", "Rute Lintasan", "Status"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        loadDashboard();

        jTable4.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Pesanan", "Pelanggan", "Driver", "Layanan", "Total Biaya", "Rute Lintasan", "Status"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        loadPesananData();
        
        // tabel transaksi (admin), tidak bisa diedit
        jTable5.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Transaksi", "Tanggal", "Pelanggan", "Jenis", "Metode Pembayaran", "Jumlah", "Status"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        loadTransaksiData();
        
        // tabel laporan, tidak bisa diedit
        jTable6.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Bulan", "ID Pesanan", "Jenis", "Waktu Transaksi", "Pelanggan", "Metode Pembayaran", "Jumlah Transaksi", "Status"}) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });

        txtCariLaporan.setEditable(true);   // di kode generated, field ini diset editable(false)

        // default combo = tahun & bulan sekarang (diset SEBELUM listener dipasang)
        comboBoxTahun.setSelectedItem(String.valueOf(java.time.LocalDate.now().getYear()));
        comboBoxBulan.setSelectedItem(String.valueOf(java.time.LocalDate.now().getMonthValue()));

        comboBoxTahun.addActionListener(e -> loadLaporanData());
        comboBoxBulan.addActionListener(e -> loadLaporanData());
        comboBoxStatus.addActionListener(e -> loadLaporanData());
        comboBoxMetode.addActionListener(e -> loadLaporanData());
        txtCariLaporan.addActionListener(e -> loadLaporanData());
        btnRefreshLaporan.addActionListener(e -> loadLaporanData());
        loadLaporanData();
        
        // ==========================================
        // PASANG TOOLTIP KE SEMUA TABEL DI SINI
        // ==========================================
        // Untuk jTable1, jTable2, jTable4, jTable5, jTable6 (Tabel Biasa)
        for (javax.swing.JTable t : new javax.swing.JTable[]{jTable1, jTable2, jTable4, jTable5, jTable6}) {
            aktifkanTooltipSel(t);
        }

        // Khusus jTable3 (Karena ada format Rupiah khusus di kolom ke-4)
        aktifkanTooltipSelTarif(jTable3);

        javax.swing.ToolTipManager.sharedInstance().setInitialDelay(300);
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(15000);
    }
    
    private void loadDashboard() {
        dao.PesananDao pd = new dao.PesananDao();
        lblTotalPesanan.setText(String.valueOf(pd.hitungPesananHariIni()));
        lblTotalPendapatan.setText(controller.Fmt.rupiah(pd.totalPendapatan()));
        lblDriverAktif.setText(String.valueOf(new dao.DriverDao().hitungAktif()));

        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.setRowCount(0);
        for (Object[] r : pd.ringkasanDashboard()) m.addRow(r);
    }
    
    
    // Method untuk mengaktifkan tooltip di tabel biasa (jTable1, jTable2, jTable4, jTable5, jTable6)
    private void aktifkanTooltipSel(javax.swing.JTable tabel) {
        tabel.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable t, Object v,
                    boolean sel, boolean fokus, int r, int c) {
                super.getTableCellRendererComponent(t, v, sel, fokus, r, c);
                setToolTipText(buatTooltip(v));
                return this;
            }
        });
    }

    // Method khusus untuk jTable3 (supaya format Rupiah & tooltip-nya jalan barengan)
    private void aktifkanTooltipSelTarif(javax.swing.JTable tabel) {
        tabel.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override 
            protected void setValue(Object v) {
                String formatted = v instanceof Number
                    ? "Rp " + NumberFormat.getInstance(new java.util.Locale("id", "ID")).format(((Number) v).doubleValue())
                    : String.valueOf(v);
                setText(formatted);
                setHorizontalAlignment(RIGHT);
                setToolTipText(buatTooltip(v)); // Tooltip teks panjang tetap aktif di sini
            }
        });
    }

    // Method pembantu untuk format teks/HTML tooltip
    private String buatTooltip(Object v) {
        String teks = (v == null) ? "" : v.toString().trim();
        if (teks.isEmpty()) return null;
        if (teks.length() > 40) {
            String aman = teks.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
            return "<html><body style='width:320px'>" + aman + "</body></html>";
        }
        return teks;
    }
    
    private void loadPesananData() {
        // kartu ringkasan
        lblPesananMasuk.setText(String.valueOf(pesananDao.hitungSemuaPesanan()));
        lblDalamPerjalanan.setText(String.valueOf(pesananDao.hitungByStatus("Dalam Perjalanan")));
        lblDibatalkan.setText(String.valueOf(pesananDao.hitungByStatus("Dibatalkan")));

        // tabel
        DefaultTableModel m = (DefaultTableModel) jTable4.getModel();
        m.setRowCount(0);
        String status = String.valueOf(jComboBox3.getSelectedItem());
        for (Object[] r : pesananDao.cariPesananAdmin(txtCariPesanan.getText(), kategoriPesanan, status)) {
            m.addRow(r);
        }
    }
    
    private void loadTransaksiData() {
        // kartu ringkasan
        lblTransaksiBerhasil.setText(controller.Fmt.rupiah(transaksiDao.totalBerhasil()));
        lblPenerimaanEwallet.setText(controller.Fmt.rupiah(transaksiDao.totalByMetode("E-Wallet")));
        lblPenerimaanTunai.setText(controller.Fmt.rupiah(transaksiDao.totalByMetode("Cash")));

        // tabel
        DefaultTableModel m = (DefaultTableModel) jTable5.getModel();
        m.setRowCount(0);
        for (Object[] r : transaksiDao.ringkasanTransaksi()) m.addRow(r);
    }
    
    private void loadLaporanData() {
        int tahun = Integer.parseInt(String.valueOf(comboBoxTahun.getSelectedItem()));
        int bulan = Integer.parseInt(String.valueOf(comboBoxBulan.getSelectedItem()));

        // kartu mengikuti combo tahun & bulan
        lblPesananBerhasil.setText(String.valueOf(transaksiDao.hitungPesananBerhasil(tahun, bulan)));

        // tabel memuat semua data, disaring oleh pencarian + status + metode
        DefaultTableModel m = (DefaultTableModel) jTable6.getModel();
        m.setRowCount(0);
        for (Object[] r : transaksiDao.cariLaporan(
                txtCariLaporan.getText(),
                String.valueOf(comboBoxStatus.getSelectedItem()),
                String.valueOf(comboBoxMetode.getSelectedItem()),
                0, 0)) {          // ganti 0, 0 menjadi tahun, bulan kalau tabel juga mau ikut filter periode
            m.addRow(r);
        }
    }

        private void setKategoriPesanan(String kategori) {
            kategoriPesanan = kategori;
            loadPesananData();
        }
    
    private void loadTarifData() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            // Ambil data dari TarifController dan masukkan ke model tabel
            List<Object[]> listTarif = tarifController.cariLayanan(txtSearchTarif.getText(), (String) jComboBox2.getSelectedItem());
            for (Object[] row : listTarif) {
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data tarif: " + e.getMessage());
        }
    }

    private void tambahTarifBaru() {
        // Logika membuka form/dialog tambah tarif
        // Contoh: new DialogTambahTarif(this, true).setVisible(true);
        // loadTarifData(); // Refresh tabel setelah tambah
    }

    private void updateTarifTerpilih() {
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data tarif yang ingin diubah terlebih dahulu!");
            return;
        }
        // Logika update data berdasarkan ID baris yang dipilih
    }

    private void hapusTarifTerpilih() {
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data tarif yang ingin dihapus terlebih dahulu!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Panggil controller untuk hapus berdasarkan ID
            // misal: tarifController.hapus(...);
            loadTarifData();
        }
    }
    
    private void setupMenu() {
        // halaman utama: Dashboard dan Kelola Data berganti, tidak menumpuk lagi
        cardPanelUtama.removeAll();
        cardPanelUtama.setLayout(new java.awt.CardLayout());
        cardPanelUtama.add(cardDashboard, "dashboard");
        cardPanelUtama.add(cardKelola,    "kelola");
        cardPanelUtama.add(cardPesanan, "pesanan");
        cardPanelUtama.add(cardTransaksi, "transaksi");
        cardPanelUtama.add(cardLaporan, "laporan");

        menuPanels = new javax.swing.JPanel[]{panelDashboard, panelKelolaData, panelData, panelTransaksi, panelLaporan};
        menuLabels = new javax.swing.JLabel[]{lblDashboard, lblKelola, lblData, lblTransaksi, lblLaporan};

        for (int i = 0; i < menuPanels.length; i++) {
            final int idx = i;
            java.awt.event.MouseAdapter klik = new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent e) { pilihMenu(idx); }
            };
            menuPanels[i].addMouseListener(klik);
            menuLabels[i].addMouseListener(klik);   // label menutupi panel, jadi perlu listener juga
            java.awt.Cursor tangan = java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR);
            menuPanels[i].setCursor(tangan);
            menuLabels[i].setCursor(tangan);
        }
        pilihMenu(0);   // awalnya Dashboard aktif
    }
    
    private void pilihMenu(int aktif) {
        for (int i = 0; i < menuPanels.length; i++) {
            boolean on = (i == aktif);
            menuPanels[i].setBackground(on ? PUTIH : BIRU);
            menuLabels[i].setForeground(on ? BIRU : PUTIH);
        }
        
        java.awt.CardLayout cl = (java.awt.CardLayout) cardPanelUtama.getLayout();
        
        switch (aktif) {
        case 0 -> { cl.show(cardPanelUtama, "dashboard"); loadDashboard(); }
        case 1 -> { cl.show(cardPanelUtama, "kelola"); loadUserData(); loadTarifData(); }
        case 2 -> { cl.show(cardPanelUtama, "pesanan"); loadPesananData(); }
        case 3 -> { cl.show(cardPanelUtama, "transaksi"); loadTransaksiData(); }
        case 4 -> { cl.show(cardPanelUtama, "laporan"); loadLaporanData(); }
        default -> {} // Opsional: untuk mengantisipasi angka di luar 0-4
        }
    }
    
    private void loadUserData() {
        try {
            String role = String.valueOf(jComboBox1.getSelectedItem());
            List<Object[]> data = userController.cariUser(txtSearch.getText(), role);
            DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
            m.setRowCount(0);
            for (Object[] r : data) m.addRow(r);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat user:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusUserTerpilih() {
        int row = jTable2.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih user yang mau dihapus dulu.");
            return;
        }
        int id = Integer.parseInt(String.valueOf(jTable2.getValueAt(row, 0)));
        String nama = String.valueOf(jTable2.getValueAt(row, 1));
        int ok = JOptionPane.showConfirmDialog(this, "Hapus user \"" + nama + "\"?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;
        try {
            userController.hapusUser(id);
            loadUserData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus (mungkin user ini punya pesanan/transaksi):\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Dialog isian tarif. Mengembalikan {nama, jenis, kelas, tarif} atau null kalau dibatalkan.
    private String[] dialogTarif(String judul, String nama, String jenis, String kelas, String tarif) {
        JTextField fNama = new JTextField(nama, 20);
        JComboBox<String> fJenis = new JComboBox<>(new String[]{"Motor", "Mobil"});
        JComboBox<String> fKelas = new JComboBox<>(new String[]{"Biasa", "Comfort", "Reguler"});
        JTextField fTarif = new JTextField(tarif, 20);
        if (jenis != null) fJenis.setSelectedItem(jenis);
        if (kelas != null) fKelas.setSelectedItem(kelas);

        JPanel p = new JPanel(new java.awt.GridLayout(0, 2, 8, 8));
        p.add(new JLabel("Nama Layanan"));   p.add(fNama);
        p.add(new JLabel("Jenis Kendaraan")); p.add(fJenis);
        p.add(new JLabel("Kelas"));          p.add(fKelas);
        p.add(new JLabel("Tarif per Km (Rp)")); p.add(fTarif);

        while (true) {
            int ok = JOptionPane.showConfirmDialog(this, p, judul,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (ok != JOptionPane.OK_OPTION) return null;

            if (fNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama layanan wajib diisi.");
                continue;
            }
            try {
                BigDecimal t = new BigDecimal(fTarif.getText().trim());
                if (t.signum() <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tarif harus angka lebih dari 0 (contoh: 6500).");
                continue;
            }
            return new String[]{fNama.getText().trim(), (String) fJenis.getSelectedItem(),
                                (String) fKelas.getSelectedItem(), fTarif.getText().trim()};
        }
    }
    
    // Dialog tambah user (Pelanggan / Driver).
    // Mengembalikan {role, nama, telp, email, password, ...extra} atau null kalau dibatalkan.
    // extra Pelanggan = {alamat}; extra Driver = {merk, plat, jenis, "Aktif"}
    private String[] dialogTambahUser() {
        JComboBox<String> fRole = new JComboBox<>(new String[]{"Pelanggan", "Driver"});
        JTextField fNama = new JTextField(20);
        JTextField fEmail = new JTextField(20);
        JTextField fTelp = new JTextField(20);
        JPasswordField fPass = new JPasswordField(20);
        JCheckBox cekLihat = new JCheckBox("Tampilkan password");
        final char echoAwal = fPass.getEchoChar();
        cekLihat.addActionListener(e -> fPass.setEchoChar(cekLihat.isSelected() ? (char) 0 : echoAwal));

        JTextField fAlamat = new JTextField(20);
        JTextField fMerk = new JTextField(20);
        JTextField fPlat = new JTextField(20);
        JComboBox<String> fJenis = new JComboBox<>(new String[]{"Motor", "Mobil"});

        JPanel umum = new JPanel(new java.awt.GridLayout(0, 2, 8, 8));
        umum.add(new JLabel("Tambah sebagai")); umum.add(fRole);
        umum.add(new JLabel("Nama"));           umum.add(fNama);
        umum.add(new JLabel("Email"));          umum.add(fEmail);
        umum.add(new JLabel("No. Telepon"));    umum.add(fTelp);
        umum.add(new JLabel("Password"));       umum.add(fPass);
        umum.add(new JLabel(""));               umum.add(cekLihat);

        JPanel khusus = new JPanel(new java.awt.GridLayout(0, 2, 8, 8));
        JPanel p = new JPanel(new java.awt.BorderLayout(0, 8));
        p.add(umum, java.awt.BorderLayout.NORTH);
        p.add(khusus, java.awt.BorderLayout.CENTER);

        // isi bagian bawah sesuai role yang dipilih
        Runnable isiKhusus = () -> {
            khusus.removeAll();
            if ("Pelanggan".equals(fRole.getSelectedItem())) {
                khusus.add(new JLabel("Alamat Rumah"));    khusus.add(fAlamat);
            } else {
                khusus.add(new JLabel("Merk Kendaraan"));  khusus.add(fMerk);
                khusus.add(new JLabel("No. Plat"));        khusus.add(fPlat);
                khusus.add(new JLabel("Jenis Kendaraan")); khusus.add(fJenis);
            }
            khusus.revalidate();
            khusus.repaint();
            java.awt.Window w = SwingUtilities.getWindowAncestor(p);
            if (w != null) w.pack();   // ukuran popup menyesuaikan
        };
        fRole.addActionListener(e -> isiKhusus.run());
        isiKhusus.run();

        while (true) {
            int ok = JOptionPane.showConfirmDialog(this, p, "Tambah User Baru",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (ok != JOptionPane.OK_OPTION) return null;

            String role = (String) fRole.getSelectedItem();
            String nama = fNama.getText().trim();
            String email = fEmail.getText().trim();
            String telp = fTelp.getText().trim();
            String pass = new String(fPass.getPassword());

            if (util.Validator.isKosong(nama, email, telp, pass)) {
                JOptionPane.showMessageDialog(this, "Nama, email, no. telepon, dan password wajib diisi.");
                continue;
            }
            try {
                // aturan validasi memakai setter di model (sama dengan yang dipakai aplikasi)
                model.User u = "Driver".equals(role) ? new model.Driver() : new model.Pelanggan();
                u.setNama(nama);
                u.setNoTelepon(telp);
                u.setEmail(email);
                u.setPassword(pass);
                if (!util.Validator.teleponValid(telp)) {
                    throw new IllegalArgumentException("No. telepon harus diawali 08 dan 10-15 digit.");
                }
                if (!util.Validator.passwordValid(pass)) {
                    throw new IllegalArgumentException("Password minimal 8 karakter, harus ada huruf dan angka.");
                }

                String[] extra;
                if (u instanceof model.Pelanggan pel) {
                    if (fAlamat.getText().trim().isEmpty()) {
                        throw new IllegalArgumentException("Alamat rumah wajib diisi.");
                    }
                    pel.setAlamatFavorit(fAlamat.getText());
                    extra = new String[]{pel.getAlamatFavorit()};
                } else {
                    model.Driver drv = (model.Driver) u;
                    if (fMerk.getText().trim().length() > 50) {
                        throw new IllegalArgumentException("Merk kendaraan maksimal 50 karakter.");
                    }
                    if (fPlat.getText().trim().length() > 15) {
                        throw new IllegalArgumentException("No. plat maksimal 15 karakter.");
                    }
                    drv.setMerkKendaraan(fMerk.getText());
                    drv.setNoPlatKendaraan(fPlat.getText());
                    drv.setJenisKendaraan((String) fJenis.getSelectedItem());
                    extra = new String[]{drv.getMerkKendaraan(), drv.getNoPlatKendaraan(),
                                         drv.getJenisKendaraan(), "Aktif"};   // status otomatis Aktif
                }

                // email tidak boleh sudah terdaftar
                try {
                    for (Object[] r : userController.cariUser(email, "Semua Role")) {
                        if (email.equalsIgnoreCase(String.valueOf(r[2]))) {
                            throw new IllegalArgumentException("Email sudah terdaftar, pakai email lain.");
                        }
                    }
                } catch (SQLException ignore) {
                    // kalau pengecekan gagal, tambahUser() tetap menolak email ganda
                }

                String[] hasil = new String[5 + extra.length];
                hasil[0] = role;
                hasil[1] = u.getNama();
                hasil[2] = u.getNoTelepon();
                hasil[3] = u.getEmail();
                hasil[4] = u.getPassword();
                System.arraycopy(extra, 0, hasil, 5, extra.length);
                return hasil;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Data belum valid",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelDashboard = new javax.swing.JPanel();
        lblDashboard = new javax.swing.JLabel();
        panelKelolaData = new javax.swing.JPanel();
        lblKelola = new javax.swing.JLabel();
        panelData = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        panelTransaksi = new javax.swing.JPanel();
        lblTransaksi = new javax.swing.JLabel();
        panelLaporan = new javax.swing.JPanel();
        lblLaporan = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        cardPanelUtama = new javax.swing.JPanel();
        cardDashboard = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalPesanan = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalPendapatan = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblDriverAktif = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cardKelola = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnUser = new javax.swing.JToggleButton();
        btnTarif = new javax.swing.JToggleButton();
        cardSub = new javax.swing.JPanel();
        cardUser = new javax.swing.JPanel();
        btnTambahUser = new javax.swing.JButton();
        btnUpdateUser = new javax.swing.JButton();
        btnHapusUser = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cardTarif = new javax.swing.JPanel();
        btnTambahTarif = new javax.swing.JButton();
        btnUpdateTarif = new javax.swing.JButton();
        btnHapusTarif = new javax.swing.JButton();
        buttonRefreshTarif = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSearchTarif = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        cardPesanan = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblPesananMasuk = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblDalamPerjalanan = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblDibatalkan = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnSemuaLayanan = new javax.swing.JButton();
        btnRuteRide = new javax.swing.JButton();
        btnRuteCar = new javax.swing.JButton();
        btnDokumen = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        txtCariPesanan = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        btnRefreshPesanan = new javax.swing.JButton();
        cardTransaksi = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lblTransaksiBerhasil = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        lblPenerimaanEwallet = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        lblPenerimaanTunai = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        cardLaporan = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        lblPesananBerhasil = new javax.swing.JLabel();
        comboBoxTahun = new javax.swing.JComboBox<>();
        comboBoxBulan = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        txtCariLaporan = new javax.swing.JTextField();
        comboBoxStatus = new javax.swing.JComboBox<>();
        comboBoxMetode = new javax.swing.JComboBox<>();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        btnRefreshLaporan = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Admin");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1331, 860));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(14, 165, 233));
        jPanel1.setPreferredSize(new java.awt.Dimension(260, 860));
        jPanel1.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/logo ruteconnect.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 113, 110);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RuteConnect");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(110, 30, 140, 25);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Admin");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(110, 60, 140, 20);

        panelDashboard.setBackground(new java.awt.Color(14, 165, 233));

        lblDashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboard.setText("Dashboard");

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelDashboard);
        panelDashboard.setBounds(0, 120, 260, 60);

        panelKelolaData.setBackground(new java.awt.Color(14, 165, 233));

        lblKelola.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKelola.setForeground(new java.awt.Color(255, 255, 255));
        lblKelola.setText("Kelola Data");

        javax.swing.GroupLayout panelKelolaDataLayout = new javax.swing.GroupLayout(panelKelolaData);
        panelKelolaData.setLayout(panelKelolaDataLayout);
        panelKelolaDataLayout.setHorizontalGroup(
            panelKelolaDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKelolaDataLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblKelola, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        panelKelolaDataLayout.setVerticalGroup(
            panelKelolaDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKelolaDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKelola, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelKelolaData);
        panelKelolaData.setBounds(0, 180, 260, 60);

        panelData.setBackground(new java.awt.Color(14, 165, 233));

        lblData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setText("Data Pesanan");

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelData);
        panelData.setBounds(0, 240, 260, 60);

        panelTransaksi.setBackground(new java.awt.Color(14, 165, 233));

        lblTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        lblTransaksi.setText("Data Transaksi");

        javax.swing.GroupLayout panelTransaksiLayout = new javax.swing.GroupLayout(panelTransaksi);
        panelTransaksi.setLayout(panelTransaksiLayout);
        panelTransaksiLayout.setHorizontalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        panelTransaksiLayout.setVerticalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addComponent(lblTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelTransaksi);
        panelTransaksi.setBounds(0, 300, 260, 60);

        panelLaporan.setBackground(new java.awt.Color(14, 165, 233));

        lblLaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLaporan.setForeground(new java.awt.Color(255, 255, 255));
        lblLaporan.setText("Laporan");

        javax.swing.GroupLayout panelLaporanLayout = new javax.swing.GroupLayout(panelLaporan);
        panelLaporan.setLayout(panelLaporanLayout);
        panelLaporanLayout.setHorizontalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLaporanLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        panelLaporanLayout.setVerticalGroup(
            panelLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelLaporan);
        panelLaporan.setBounds(0, 360, 260, 60);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel11);
        jPanel11.setBounds(260, 0, 1070, 860);

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setForeground(new java.awt.Color(0, 0, 0));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(10).png"))); // NOI18N
        btnLogout.setText("Keluar");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        jPanel1.add(btnLogout);
        btnLogout.setBounds(20, 790, 110, 27);

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 860));

        cardPanelUtama.setBackground(new java.awt.Color(255, 153, 255));
        cardPanelUtama.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardPanelUtama.setLayout(new java.awt.CardLayout());

        cardDashboard.setBackground(new java.awt.Color(242, 243, 255));
        cardDashboard.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardDashboard.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(14, 165, 233));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Selamat Bertugas, Admin!");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pengelolaan terpadu master data Pelanggan (Customer), Mitra Driver, serta Konfigurasi Layanan & Tarif operasional RuteConnect.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        cardDashboard.add(jPanel3);
        jPanel3.setBounds(6, 6, 1060, 180);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Total Pesanan Hari Ini.png"))); // NOI18N
        jLabel6.setText("Total Pesanan Hari Ini");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(10, 0, 190, 50);

        lblTotalPesanan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalPesanan.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPesanan.setText("0");
        jPanel4.add(lblTotalPesanan);
        lblTotalPesanan.setBounds(10, 60, 200, 50);

        cardDashboard.add(jPanel4);
        jPanel4.setBounds(6, 200, 210, 150);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(12).png"))); // NOI18N
        jLabel7.setText("Total Pendapatan");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(10, 0, 190, 50);

        lblTotalPendapatan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalPendapatan.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPendapatan.setText("Rp 0");
        jPanel5.add(lblTotalPendapatan);
        lblTotalPendapatan.setBounds(10, 60, 200, 50);

        cardDashboard.add(jPanel5);
        jPanel5.setBounds(240, 200, 210, 150);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(8).png"))); // NOI18N
        jLabel8.setText("Driver Aktif");
        jPanel6.add(jLabel8);
        jLabel8.setBounds(10, 0, 200, 50);

        lblDriverAktif.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDriverAktif.setForeground(new java.awt.Color(0, 0, 0));
        lblDriverAktif.setText("0");
        jPanel6.add(lblDriverAktif);
        lblDriverAktif.setBounds(10, 60, 200, 50);

        cardDashboard.add(jPanel6);
        jPanel6.setBounds(470, 200, 210, 150);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pemantauan Operasional Terkini");
        cardDashboard.add(jLabel10);
        jLabel10.setBounds(10, 360, 330, 50);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Pelanggan", "Driver", "Layanan", "Metode Pembayaran", "Rute Lintasan", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        cardDashboard.add(jPanel7);
        jPanel7.setBounds(10, 410, 1050, 440);

        cardPanelUtama.add(cardDashboard, "card2");

        cardKelola.setBackground(new java.awt.Color(242, 243, 255));
        cardKelola.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardKelola.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(14, 165, 233));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kelola Data Master Sistem");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Pengelolaan terpadu master data Pelanggan (Customer), Mitra Driver, serta Konfigurasi Layanan & Tarif operasional RuteConnect.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        cardKelola.add(jPanel8);
        jPanel8.setBounds(6, 6, 1060, 180);

        btnUser.setBackground(new java.awt.Color(2, 132, 199));
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Data Customer.png"))); // NOI18N
        btnUser.setText("Data User");
        btnUser.addActionListener(this::btnUserActionPerformed);
        cardKelola.add(btnUser);
        btnUser.setBounds(10, 200, 140, 40);

        btnTarif.setBackground(new java.awt.Color(2, 132, 199));
        btnTarif.setForeground(new java.awt.Color(255, 255, 255));
        btnTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(8).png"))); // NOI18N
        btnTarif.setText("Tarif & Layanan");
        btnTarif.addActionListener(this::btnTarifActionPerformed);
        cardKelola.add(btnTarif);
        btnTarif.setBounds(160, 200, 140, 40);

        cardUser.setBackground(new java.awt.Color(255, 255, 255));
        cardUser.setLayout(null);

        btnTambahUser.setBackground(new java.awt.Color(2, 132, 199));
        btnTambahUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahUser.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(9).png"))); // NOI18N
        btnTambahUser.setText("Tambah Data Baru");
        btnTambahUser.addActionListener(this::btnTambahUserActionPerformed);
        cardUser.add(btnTambahUser);
        btnTambahUser.setBounds(10, 10, 190, 40);

        btnUpdateUser.setBackground(new java.awt.Color(242, 243, 255));
        btnUpdateUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateUser.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Update Pensil.png"))); // NOI18N
        btnUpdateUser.setText("Ubah Terpilih");
        btnUpdateUser.addActionListener(this::btnUpdateUserActionPerformed);
        cardUser.add(btnUpdateUser);
        btnUpdateUser.setBounds(220, 10, 160, 40);

        btnHapusUser.setBackground(new java.awt.Color(255, 218, 214));
        btnHapusUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusUser.setForeground(new java.awt.Color(147, 0, 10));
        btnHapusUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tong Sampah.png"))); // NOI18N
        btnHapusUser.setText("Hapus");
        cardUser.add(btnHapusUser);
        btnHapusUser.setBounds(403, 10, 140, 40);

        buttonRefresh.setBackground(new java.awt.Color(234, 237, 255));
        buttonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(13).png"))); // NOI18N
        buttonRefresh.addActionListener(this::buttonRefreshActionPerformed);
        cardUser.add(buttonRefresh);
        buttonRefresh.setBounds(550, 10, 40, 40);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tanda Search.png"))); // NOI18N
        cardUser.add(jLabel15);
        jLabel15.setBounds(620, 10, 20, 40);

        jLabel16.setForeground(new java.awt.Color(156, 163, 175));
        jLabel16.setText("Cari nama, ID, nomor...");
        cardUser.add(jLabel16);
        jLabel16.setBounds(670, 0, 140, 16);

        txtSearch.setBackground(new java.awt.Color(242, 243, 255));
        txtSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtSearch.addActionListener(this::txtSearchActionPerformed);
        cardUser.add(txtSearch);
        txtSearch.setBounds(660, 20, 200, 26);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Role", "Pelanggan", "Driver", "Admin" }));
        cardUser.add(jComboBox1);
        jComboBox1.setBounds(880, 20, 140, 26);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.setForeground(new java.awt.Color(0, 0, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Email", "No. Telp", "Role"
            }
        ));
        jScrollPane.setViewportView(jTable2);

        jPanel9.add(jScrollPane, java.awt.BorderLayout.CENTER);

        cardUser.add(jPanel9);
        jPanel9.setBounds(10, 70, 1020, 300);

        cardTarif.setBackground(new java.awt.Color(255, 255, 255));
        cardTarif.setLayout(null);

        btnTambahTarif.setBackground(new java.awt.Color(2, 132, 199));
        btnTambahTarif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambahTarif.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(9).png"))); // NOI18N
        btnTambahTarif.setText("Tambah Data Baru");
        btnTambahTarif.addActionListener(this::btnTambahTarifActionPerformed);
        cardTarif.add(btnTambahTarif);
        btnTambahTarif.setBounds(10, 10, 190, 40);

        btnUpdateTarif.setBackground(new java.awt.Color(242, 243, 255));
        btnUpdateTarif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateTarif.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdateTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Update Pensil.png"))); // NOI18N
        btnUpdateTarif.setText("Ubah Terpilih");
        btnUpdateTarif.addActionListener(this::btnUpdateTarifActionPerformed);
        cardTarif.add(btnUpdateTarif);
        btnUpdateTarif.setBounds(220, 10, 160, 40);

        btnHapusTarif.setBackground(new java.awt.Color(255, 218, 214));
        btnHapusTarif.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapusTarif.setForeground(new java.awt.Color(147, 0, 10));
        btnHapusTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tong Sampah.png"))); // NOI18N
        btnHapusTarif.setText("Hapus");
        btnHapusTarif.addActionListener(this::btnHapusTarifActionPerformed);
        cardTarif.add(btnHapusTarif);
        btnHapusTarif.setBounds(403, 10, 140, 40);

        buttonRefreshTarif.setBackground(new java.awt.Color(234, 237, 255));
        buttonRefreshTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(13).png"))); // NOI18N
        buttonRefreshTarif.addActionListener(this::buttonRefreshTarifActionPerformed);
        cardTarif.add(buttonRefreshTarif);
        buttonRefreshTarif.setBounds(550, 10, 40, 40);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tanda Search.png"))); // NOI18N
        cardTarif.add(jLabel17);
        jLabel17.setBounds(620, 10, 20, 40);

        jLabel18.setForeground(new java.awt.Color(156, 163, 175));
        jLabel18.setText("Cari nama, ID, jenis kendaraan..");
        cardTarif.add(jLabel18);
        jLabel18.setBounds(670, 0, 140, 16);

        txtSearchTarif.setBackground(new java.awt.Color(242, 243, 255));
        txtSearchTarif.setForeground(new java.awt.Color(0, 0, 0));
        txtSearchTarif.addActionListener(this::txtSearchTarifActionPerformed);
        cardTarif.add(txtSearchTarif);
        txtSearchTarif.setBounds(660, 20, 200, 26);

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Kendaraan", "Mobil", "Motor" }));
        cardTarif.add(jComboBox2);
        jComboBox2.setBounds(880, 20, 140, 26);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jTable3.setBackground(new java.awt.Color(255, 255, 255));
        jTable3.setForeground(new java.awt.Color(0, 0, 0));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Layanan", "Jenis Kendaraan", "Kelas", "Tarif per Km (Rp)"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel10.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        cardTarif.add(jPanel10);
        jPanel10.setBounds(10, 70, 1020, 300);

        javax.swing.GroupLayout cardSubLayout = new javax.swing.GroupLayout(cardSub);
        cardSub.setLayout(cardSubLayout);
        cardSubLayout.setHorizontalGroup(
            cardSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSubLayout.createSequentialGroup()
                .addGroup(cardSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardUser, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cardSubLayout.setVerticalGroup(
            cardSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardSubLayout.createSequentialGroup()
                .addComponent(cardTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cardUser, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cardKelola.add(cardSub);
        cardSub.setBounds(6, 250, 1040, 380);

        cardPanelUtama.add(cardKelola, "card3");

        cardPesanan.setBackground(new java.awt.Color(242, 243, 255));
        cardPesanan.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardPesanan.setLayout(null);

        jPanel12.setBackground(new java.awt.Color(14, 165, 233));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Selamat Bertugas, Admin!");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Pengelolaan terpadu master data Pelanggan (Customer), Mitra Driver, serta Konfigurasi Layanan & Tarif operasional RuteConnect.");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        cardPesanan.add(jPanel12);
        jPanel12.setBounds(6, 6, 1060, 180);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Total Pesanan Hari Ini.png"))); // NOI18N
        jLabel21.setText("Total Pesanan Masuk");
        jPanel13.add(jLabel21);
        jLabel21.setBounds(10, 0, 190, 50);

        lblPesananMasuk.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPesananMasuk.setForeground(new java.awt.Color(0, 0, 0));
        lblPesananMasuk.setText("0");
        jPanel13.add(lblPesananMasuk);
        lblPesananMasuk.setBounds(10, 60, 200, 50);

        cardPesanan.add(jPanel13);
        jPanel13.setBounds(6, 200, 210, 150);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(null);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/mobil.png"))); // NOI18N
        jLabel23.setText("Dalam Perjalanan");
        jPanel14.add(jLabel23);
        jLabel23.setBounds(10, 0, 190, 50);

        lblDalamPerjalanan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDalamPerjalanan.setForeground(new java.awt.Color(0, 0, 0));
        lblDalamPerjalanan.setText("0");
        jPanel14.add(lblDalamPerjalanan);
        lblDalamPerjalanan.setBounds(10, 60, 200, 50);

        cardPesanan.add(jPanel14);
        jPanel14.setBounds(240, 200, 210, 150);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(null);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/tanda silang merah.png"))); // NOI18N
        jLabel28.setText("Dibatalkan");
        jPanel17.add(jLabel28);
        jLabel28.setBounds(10, 0, 198, 49);

        lblDibatalkan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDibatalkan.setForeground(new java.awt.Color(0, 0, 0));
        lblDibatalkan.setText("0");
        jPanel17.add(lblDibatalkan);
        lblDibatalkan.setBounds(10, 60, 200, 52);

        cardPesanan.add(jPanel17);
        jPanel17.setBounds(470, 200, 210, 150);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(null);

        btnSemuaLayanan.setBackground(new java.awt.Color(242, 243, 255));
        btnSemuaLayanan.setForeground(new java.awt.Color(0, 0, 0));
        btnSemuaLayanan.setText("Semua Layanan");
        btnSemuaLayanan.addActionListener(this::btnSemuaLayananActionPerformed);
        jPanel16.add(btnSemuaLayanan);
        btnSemuaLayanan.setBounds(10, 10, 130, 40);

        btnRuteRide.setBackground(new java.awt.Color(242, 243, 255));
        btnRuteRide.setForeground(new java.awt.Color(0, 0, 0));
        btnRuteRide.setText("RuteRide");
        btnRuteRide.addActionListener(this::btnRuteRideActionPerformed);
        jPanel16.add(btnRuteRide);
        btnRuteRide.setBounds(150, 10, 130, 40);

        btnRuteCar.setBackground(new java.awt.Color(242, 243, 255));
        btnRuteCar.setForeground(new java.awt.Color(0, 0, 0));
        btnRuteCar.setText("RuteCar");
        btnRuteCar.addActionListener(this::btnRuteCarActionPerformed);
        jPanel16.add(btnRuteCar);
        btnRuteCar.setBounds(290, 10, 130, 40);

        btnDokumen.setBackground(new java.awt.Color(242, 243, 255));
        btnDokumen.setForeground(new java.awt.Color(0, 0, 0));
        btnDokumen.setText("Dokumen");
        btnDokumen.addActionListener(this::btnDokumenActionPerformed);
        jPanel16.add(btnDokumen);
        btnDokumen.setBounds(430, 10, 140, 40);

        jPanel23.setLayout(new java.awt.BorderLayout());

        jTable4.setBackground(new java.awt.Color(255, 255, 255));
        jTable4.setForeground(new java.awt.Color(0, 0, 0));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Pelanggan", "Driver", "Layanan", "Total Biaya", "Rute Lintasan", "Status"
            }
        ));
        jScrollPane2.setViewportView(jTable4);

        jPanel23.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel23);
        jPanel23.setBounds(10, 60, 1040, 420);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tanda Search.png"))); // NOI18N
        jPanel16.add(jLabel27);
        jLabel27.setBounds(650, 10, 30, 40);

        txtCariPesanan.setBackground(new java.awt.Color(242, 243, 255));
        txtCariPesanan.setForeground(new java.awt.Color(0, 0, 0));
        txtCariPesanan.addActionListener(this::txtCariPesananActionPerformed);
        jPanel16.add(txtCariPesanan);
        txtCariPesanan.setBounds(690, 10, 220, 40);

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Status", "Menunggu", "Dalam Perjalanan", "Selesai", "Batal" }));
        jComboBox3.addActionListener(this::jComboBox3ActionPerformed);
        jPanel16.add(jComboBox3);
        jComboBox3.setBounds(910, 20, 130, 26);

        btnRefreshPesanan.setBackground(new java.awt.Color(242, 243, 255));
        btnRefreshPesanan.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshPesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(13).png"))); // NOI18N
        btnRefreshPesanan.addActionListener(this::btnRefreshPesananActionPerformed);
        jPanel16.add(btnRefreshPesanan);
        btnRefreshPesanan.setBounds(580, 10, 40, 40);

        cardPesanan.add(jPanel16);
        jPanel16.setBounds(6, 370, 1060, 490);

        cardPanelUtama.add(cardPesanan, "card4");

        cardTransaksi.setBackground(new java.awt.Color(242, 243, 255));
        cardTransaksi.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardTransaksi.setLayout(null);

        jPanel18.setBackground(new java.awt.Color(14, 165, 233));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Selamat Bertugas, Admin!");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Pengelolaan terpadu master data Pelanggan (Customer), Mitra Driver, serta Konfigurasi Layanan & Tarif operasional RuteConnect.");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        cardTransaksi.add(jPanel18);
        jPanel18.setBounds(6, 6, 1060, 180);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(null);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Total Transaksi Berhasil.png"))); // NOI18N
        jLabel32.setText("Total Transaksi Berhasil");
        jPanel19.add(jLabel32);
        jLabel32.setBounds(10, 0, 190, 50);

        lblTransaksiBerhasil.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTransaksiBerhasil.setForeground(new java.awt.Color(0, 0, 0));
        lblTransaksiBerhasil.setText("Rp");
        jPanel19.add(lblTransaksiBerhasil);
        lblTransaksiBerhasil.setBounds(10, 60, 200, 50);

        cardTransaksi.add(jPanel19);
        jPanel19.setBounds(6, 200, 210, 150);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(null);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/E-Wallet.png"))); // NOI18N
        jLabel34.setText("Penerimaan E-Wallet");
        jPanel20.add(jLabel34);
        jLabel34.setBounds(10, 0, 190, 50);

        lblPenerimaanEwallet.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPenerimaanEwallet.setForeground(new java.awt.Color(0, 0, 0));
        lblPenerimaanEwallet.setText("Rp");
        jPanel20.add(lblPenerimaanEwallet);
        lblPenerimaanEwallet.setBounds(10, 60, 200, 50);

        cardTransaksi.add(jPanel20);
        jPanel20.setBounds(240, 200, 210, 150);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setLayout(null);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Total Pendapatan.png"))); // NOI18N
        jLabel36.setText("Penerimaan Tunai");
        jPanel21.add(jLabel36);
        jLabel36.setBounds(10, 0, 200, 50);

        lblPenerimaanTunai.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPenerimaanTunai.setForeground(new java.awt.Color(0, 0, 0));
        lblPenerimaanTunai.setText("Rp");
        jPanel21.add(lblPenerimaanTunai);
        lblPenerimaanTunai.setBounds(10, 60, 200, 50);

        cardTransaksi.add(jPanel21);
        jPanel21.setBounds(470, 200, 210, 150);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Pemantauan Operasional Terkini");
        cardTransaksi.add(jLabel38);
        jLabel38.setBounds(10, 360, 330, 50);

        jPanel22.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setBorder(null);

        jTable5.setBackground(new java.awt.Color(255, 255, 255));
        jTable5.setForeground(new java.awt.Color(0, 0, 0));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pesanan", "Pelanggan", "Driver", "Layanan", "Total Biaya", "Rute Lintasan", "Status"
            }
        ));
        jScrollPane4.setViewportView(jTable5);

        jPanel22.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        cardTransaksi.add(jPanel22);
        jPanel22.setBounds(10, 410, 1050, 440);

        cardPanelUtama.add(cardTransaksi, "card5");

        cardLaporan.setBackground(new java.awt.Color(242, 243, 255));
        cardLaporan.setPreferredSize(new java.awt.Dimension(1070, 860));
        cardLaporan.setLayout(null);

        jPanel24.setBackground(new java.awt.Color(14, 165, 233));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Selamat Bertugas, Admin!");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Pengelolaan terpadu master data Pelanggan (Customer), Mitra Driver, serta Konfigurasi Layanan & Tarif operasional RuteConnect.");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        cardLaporan.add(jPanel24);
        jPanel24.setBounds(6, 6, 1060, 180);

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setLayout(null);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Transaksi Berhasil.png"))); // NOI18N
        jLabel41.setText("Total Pesanan Berhasil");
        jPanel25.add(jLabel41);
        jLabel41.setBounds(10, 0, 190, 50);

        lblPesananBerhasil.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPesananBerhasil.setForeground(new java.awt.Color(0, 0, 0));
        lblPesananBerhasil.setText("0");
        jPanel25.add(lblPesananBerhasil);
        lblPesananBerhasil.setBounds(10, 60, 200, 50);

        cardLaporan.add(jPanel25);
        jPanel25.setBounds(6, 200, 210, 150);

        comboBoxTahun.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxTahun.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026" }));
        cardLaporan.add(comboBoxTahun);
        comboBoxTahun.setBounds(250, 240, 76, 26);

        comboBoxBulan.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxBulan.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cardLaporan.add(comboBoxBulan);
        comboBoxBulan.setBounds(350, 240, 76, 26);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Tahun");
        cardLaporan.add(jLabel43);
        jLabel43.setBounds(250, 220, 70, 20);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Bulan");
        cardLaporan.add(jLabel44);
        jLabel44.setBounds(350, 220, 70, 20);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setLayout(null);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Tanda Search.png"))); // NOI18N
        jPanel26.add(jLabel45);
        jLabel45.setBounds(10, 0, 30, 40);

        txtCariLaporan.setBackground(new java.awt.Color(242, 243, 255));
        txtCariLaporan.setForeground(new java.awt.Color(0, 0, 0));
        txtCariLaporan.addActionListener(this::txtCariLaporanActionPerformed);
        jPanel26.add(txtCariLaporan);
        txtCariLaporan.setBounds(50, 10, 180, 30);

        comboBoxStatus.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxStatus.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Status", "Berhasil", "Gagal" }));
        jPanel26.add(comboBoxStatus);
        comboBoxStatus.setBounds(250, 10, 130, 26);

        comboBoxMetode.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxMetode.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxMetode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Metode", "RutePay", "E-Wallet", "Cash" }));
        jPanel26.add(comboBoxMetode);
        comboBoxMetode.setBounds(400, 10, 130, 26);

        jPanel27.setLayout(new java.awt.BorderLayout());

        jTable6.setBackground(new java.awt.Color(255, 255, 255));
        jTable6.setForeground(new java.awt.Color(0, 0, 0));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Bulan", "ID Pesanan", "Jenis", "Waktu Transaksi", "Pelanggan", "Metode Pembayaran", "Jumlah Transaksi", "Status"
            }
        ));
        jScrollPane5.setViewportView(jTable6);

        jPanel27.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel26.add(jPanel27);
        jPanel27.setBounds(10, 60, 1030, 420);

        btnRefreshLaporan.setBackground(new java.awt.Color(242, 243, 255));
        btnRefreshLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(13).png"))); // NOI18N
        jPanel26.add(btnRefreshLaporan);
        btnRefreshLaporan.setBounds(550, 10, 40, 40);

        btnPdf.setBackground(new java.awt.Color(14, 165, 233));
        btnPdf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPdf.setForeground(new java.awt.Color(255, 255, 255));
        btnPdf.setText("Download PDF");
        btnPdf.addActionListener(this::btnPdfActionPerformed);
        jPanel26.add(btnPdf);
        btnPdf.setBounds(890, 10, 150, 40);

        cardLaporan.add(jPanel26);
        jPanel26.setBounds(0, 370, 1050, 490);

        cardPanelUtama.add(cardLaporan, "card6");

        jPanel2.add(cardPanelUtama, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1070, 860));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        // TODO add your handling code here:
        loadUserData();
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        loadUserData();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void buttonRefreshTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshTarifActionPerformed
        // TODO add your handling code here:
        loadTarifData();
    }//GEN-LAST:event_buttonRefreshTarifActionPerformed

    private void txtSearchTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchTarifActionPerformed
        // TODO add your handling code here:
        loadTarifData();
    }//GEN-LAST:event_txtSearchTarifActionPerformed

    private void btnTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifActionPerformed
        // TODO add your handling code here:
        java.awt.CardLayout cl = (java.awt.CardLayout) cardSub.getLayout();
        cl.show(cardSub, "cardTarif");

        // Pastikan tombol sinkron
        btnTarif.setSelected(true);
        btnUser.setSelected(false);
    }//GEN-LAST:event_btnTarifActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih user yang mau diubah dulu.");
            return;
        }
        int id = Integer.parseInt(String.valueOf(jTable2.getValueAt(row, 0)));

        try {
            Object[] u = userController.ambilUser(id);
            if (u == null) {
                JOptionPane.showMessageDialog(this, "Data user tidak ditemukan.");
                return;
            }
            String nama  = String.valueOf(u[0]);
            String telp  = u[1] == null ? "" : String.valueOf(u[1]);
            String email = String.valueOf(u[2]);
            String role  = String.valueOf(u[3]);

            UbahUserForm formEdit = new UbahUserForm();
            formEdit.set_data(id, nama, telp, email, role);
            formEdit.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override public void windowClosed(java.awt.event.WindowEvent e) { loadUserData(); }
            });
            formEdit.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        // TODO add your handling code here:
        java.awt.CardLayout cl = (java.awt.CardLayout) cardSub.getLayout();
        cl.show(cardSub, "cardUser");

        // Pastikan tombol sinkron
        btnUser.setSelected(true);
        btnTarif.setSelected(false);
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnTambahUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahUserActionPerformed
        // TODO add your handling code here:
        String[] d = dialogTambahUser();
        if (d == null) return;
        try {
            userController.tambahUser(d[1], d[2], d[3], d[4], d[0],
                    java.util.Arrays.copyOfRange(d, 5, d.length));
            loadUserData();
            JOptionPane.showMessageDialog(this, d[0] + " \"" + d[1] + "\" berhasil ditambahkan.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambah user:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTambahUserActionPerformed

    private void btnTambahTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahTarifActionPerformed
        // TODO add your handling code here:
        String[] d = dialogTarif("Tambah Layanan", "", null, null, "");
        if (d == null) return;
        try {
            tarifController.tambahLayanan(d[0], d[1], d[2], new BigDecimal(d[3]));
            loadTarifData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menambah:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTambahTarifActionPerformed

    private void btnUpdateTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTarifActionPerformed
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih layanan yang mau diubah dulu.");
            return;
        }
        int id = Integer.parseInt(String.valueOf(jTable3.getValueAt(row, 0)));
        String[] d = dialogTarif("Ubah Layanan #" + id,
                String.valueOf(jTable3.getValueAt(row, 1)),
                String.valueOf(jTable3.getValueAt(row, 2)),
                String.valueOf(jTable3.getValueAt(row, 3)),
                ((BigDecimal) jTable3.getValueAt(row, 4)).stripTrailingZeros().toPlainString());
        if (d == null) return;
        try {
            tarifController.updateLayanan(id, d[0], d[1], d[2], new BigDecimal(d[3]));
            loadTarifData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateTarifActionPerformed

    private void btnHapusTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusTarifActionPerformed
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Pilih layanan yang mau dihapus dulu.");
            return;
        }
        int id = Integer.parseInt(String.valueOf(jTable3.getValueAt(row, 0)));
        String nama = String.valueOf(jTable3.getValueAt(row, 1));
        if (JOptionPane.showConfirmDialog(this, "Hapus layanan \"" + nama + "\"?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            tarifController.hapusLayanan(id);
            loadTarifData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus (layanan ini kemungkinan sudah dipakai di pesanan):\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusTarifActionPerformed

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

    private void btnSemuaLayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemuaLayananActionPerformed
        // TODO add your handling code here:
        setKategoriPesanan("Semua Layanan");
    }//GEN-LAST:event_btnSemuaLayananActionPerformed

    private void btnRuteRideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuteRideActionPerformed
        // TODO add your handling code here:
        setKategoriPesanan("RuteRide");
    }//GEN-LAST:event_btnRuteRideActionPerformed

    private void btnRuteCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuteCarActionPerformed
        // TODO add your handling code here:
        setKategoriPesanan("RuteCar");
    }//GEN-LAST:event_btnRuteCarActionPerformed

    private void btnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokumenActionPerformed
        // TODO add your handling code here:
        setKategoriPesanan("Dokumen");
    }//GEN-LAST:event_btnDokumenActionPerformed

    private void btnRefreshPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPesananActionPerformed
        // TODO add your handling code here:
        loadPesananData();
    }//GEN-LAST:event_btnRefreshPesananActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        loadPesananData();
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void txtCariPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariPesananActionPerformed
        // TODO add your handling code here:
        loadPesananData();
    }//GEN-LAST:event_txtCariPesananActionPerformed

    private void btnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfActionPerformed
        // TODO add your handling code here:
        if (jTable6.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk dicetak.");
            return;
        }

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Simpan Laporan Transaksi");

        fc.setSelectedFile(new java.io.File(
            "Laporan_Transaksi_" + java.time.LocalDate.now() + ".pdf"
        ));

        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        java.io.File f = fc.getSelectedFile();

        if (!f.getName().toLowerCase().endsWith(".pdf")) {
            f = new java.io.File(
                f.getParentFile(),
                f.getName() + ".pdf"
            );
        }

        String sub = "Status: " + comboBoxStatus.getSelectedItem()
                + " | Metode: " + comboBoxMetode.getSelectedItem()
                + (txtCariLaporan.getText().isBlank()
                    ? ""
                    : " | Pencarian: " + txtCariLaporan.getText().trim())
                + " | Dicetak: " + java.time.LocalDate.now();

        try {
            // Membuat dan menyimpan PDF
            new service.PdfLaporanService().buat(
                jTable6.getModel(),
                f,
                "Laporan Transaksi RuteConnect",
                sub
            );

            // Pastikan file benar-benar sudah dibuat
            if (!f.exists() || f.length() == 0) {
                throw new Exception("File PDF gagal dibuat.");
            }

            JOptionPane.showMessageDialog(
                this,
                "PDF berhasil disimpan!\nLokasi: " + f.getAbsolutePath()
            );

            // Membuka PDF secara terpisah
            try {
                if (java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop.getDesktop().open(f);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                    this,
                    "PDF berhasil disimpan, tetapi gagal dibuka otomatis."
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                this,
                "Gagal membuat PDF:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnPdfActionPerformed

    private void txtCariLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLaporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FrameAdmin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDokumen;
    private javax.swing.JButton btnHapusTarif;
    private javax.swing.JButton btnHapusUser;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPdf;
    private javax.swing.JButton btnRefreshLaporan;
    private javax.swing.JButton btnRefreshPesanan;
    private javax.swing.JButton btnRuteCar;
    private javax.swing.JButton btnRuteRide;
    private javax.swing.JButton btnSemuaLayanan;
    private javax.swing.JButton btnTambahTarif;
    private javax.swing.JButton btnTambahUser;
    private javax.swing.JToggleButton btnTarif;
    private javax.swing.JButton btnUpdateTarif;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JToggleButton btnUser;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonRefreshTarif;
    private javax.swing.JPanel cardDashboard;
    private javax.swing.JPanel cardKelola;
    private javax.swing.JPanel cardLaporan;
    private javax.swing.JPanel cardPanelUtama;
    private javax.swing.JPanel cardPesanan;
    private javax.swing.JPanel cardSub;
    private javax.swing.JPanel cardTarif;
    private javax.swing.JPanel cardTransaksi;
    private javax.swing.JPanel cardUser;
    private javax.swing.JComboBox<String> comboBoxBulan;
    private javax.swing.JComboBox<String> comboBoxMetode;
    private javax.swing.JComboBox<String> comboBoxStatus;
    private javax.swing.JComboBox<String> comboBoxTahun;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JLabel lblDalamPerjalanan;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDibatalkan;
    private javax.swing.JLabel lblDriverAktif;
    private javax.swing.JLabel lblKelola;
    private javax.swing.JLabel lblLaporan;
    private javax.swing.JLabel lblPenerimaanEwallet;
    private javax.swing.JLabel lblPenerimaanTunai;
    private javax.swing.JLabel lblPesananBerhasil;
    private javax.swing.JLabel lblPesananMasuk;
    private javax.swing.JLabel lblTotalPendapatan;
    private javax.swing.JLabel lblTotalPesanan;
    private javax.swing.JLabel lblTransaksi;
    private javax.swing.JLabel lblTransaksiBerhasil;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelKelolaData;
    private javax.swing.JPanel panelLaporan;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JTextField txtCariLaporan;
    private javax.swing.JTextField txtCariPesanan;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchTarif;
    // End of variables declaration//GEN-END:variables
}
