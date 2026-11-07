/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import java.io.IOException;
import controller.PetaServer;
import util.TarifHelper;
import util.Validator;

/**
 *
 * @author HP
 */
public class PengirimanDokumenForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PengirimanDokumenForm.class.getName());
    private PetaServer peta;
    private javax.swing.JPanel selectedArmadaPanel;
    private double jarakRuteKm = 0;
    private boolean ruteSudahDipilih = false;
    private String namaJemput = "", namaTujuan = "";

    public PengirimanDokumenForm() {
        initComponents();
        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        javax.swing.JSpinner.DateEditor editor = new javax.swing.JSpinner.DateEditor(jSpinner1, "HH:mm");
        jSpinner1.setEditor(editor);
        txtPengirim.setText(controller.Session.nama == null ? "" : controller.Session.nama);
        txtPengirim.setEditable(false);
        getContentPane().setPreferredSize(new java.awt.Dimension(390, 844));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        
        setupArmadaPanel(panelReguler);
        
        // yang terjadi saat peta mengirim hasil rute
        peta = new PetaServer(h -> {
            ruteSudahDipilih = true;
            namaJemput = h.namaJemput();
            namaTujuan = h.namaTujuan();
            jarakRuteKm = h.km();
            lblJemput.setText("<html>" + h.namaJemput() + "</html>");
            lblTujuan.setText("<html>" + h.namaTujuan() + "</html>");
            lblJarak.setText("(" + h.km() + " Km)");
            lblWaktu.setText(h.menit() + " Menit");
            lblStatus.setText("<html>Rute berhasil diterima dari peta.</html>");

            double kmPeta = h.km();
 
            updateArmadaData(TarifHelper.PaketDokumen.REGULER,
                kmPeta,
                lblKmReguler,
                lblMenitReguler,
                lblTarifReguler
            );

            munculkanJendela();
        }, () -> {
            ruteSudahDipilih = false;
            jarakRuteKm = 0;
            selectedArmadaPanel = null;
            resetAllArmadaPanels();
            kosongkanTampilan();
            resetDataArmada(); 
        });

        try {
            peta.mulai();
        } catch (IOException e) {
            lblStatus.setText("Server peta gagal jalan: " + e.getMessage());
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                peta.berhenti();
            }
        });
    }
    
    private void setupArmadaPanel(javax.swing.JPanel panel) {
        java.awt.Color normalBg = new java.awt.Color(242, 243, 255);
        java.awt.Color hoverColor = new java.awt.Color(230, 238, 255); // Biru muda lembut saat di-hover
        java.awt.Color selectColor = new java.awt.Color(210, 225, 255); // Biru terang saat dipilih

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (panel != selectedArmadaPanel) {
                    panel.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (panel != selectedArmadaPanel) {
                    panel.setBackground(normalBg);
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetAllArmadaPanels();
                selectedArmadaPanel = panel;
                panel.setBackground(selectColor);
            }
        });
    }
    
    private String namaLayananDb(TarifHelper.PaketDokumen p) {
        return p == TarifHelper.PaketDokumen.KILAT ? "RuteExpress Dokumen Kilat" : "RuteExpress Dokumen";
}

    // Fungsi untuk mereset warna semua panel armada ke normal
    private void resetAllArmadaPanels() {
        java.awt.Color normalBg = new java.awt.Color(242, 243, 255);

        panelReguler.setBackground(normalBg);
        selectedArmadaPanel = null;
    }
    
    private void munculkanJendela() {
        if ((getExtendedState() & java.awt.Frame.ICONIFIED) != 0) {
            setExtendedState(java.awt.Frame.NORMAL);
        }
        setAlwaysOnTop(true);
        toFront();
        
        java.awt.EventQueue.invokeLater(() -> {
            jScrollPane1.getVerticalScrollBar().setValue(0);
            jScrollPane1.requestFocusInWindow();
            pnlPesan.revalidate();
            pnlPesan.repaint();
        });
        
        setAlwaysOnTop(false);
    }
    
    //Mengosongkan semua kolom di form ini.
    private void kosongkanTampilan() {
        lblJemput.setText("Pilih Titik Jemput");
        lblTujuan.setText("Pilih Titik Tujuan");
        lblJarak.setText("(0 Km)");
        lblWaktu.setText("0 Menit");
        lblStatus.setText("<html>Klik peta untuk memilih lokasi jemput dan tujuan.</html>");
    }
    
    private void updateArmadaData(TarifHelper.PaketDokumen paket, double kmPeta, javax.swing.JLabel lblKm, javax.swing.JLabel lblMenit, javax.swing.JLabel lblTarif){
        if (TarifHelper.dalamBatas(kmPeta)) {
            double kmAktif = TarifHelper.hitungKm(paket, kmPeta);
            int menitAktif = TarifHelper.hitungMenit(paket, kmPeta);
            double hargaAktif = TarifHelper.hitungHarga(paket, kmPeta);

            lblKm.setText(kmAktif + " Km");
            lblMenit.setText(menitAktif + " Menit");
            lblTarif.setText("Rp " + String.format("%,.0f", hargaAktif).replace(',', '.'));
        } else {
            lblKm.setText("-");
            lblMenit.setText("-");
            lblTarif.setText("Diluar Batas");
        }
    }

    // Method untuk mereset tampilan label armada saat tombol reset ditekan
    private void resetDataArmada() {
        lblKmReguler.setText("0 Km");
        lblMenitReguler.setText("0 Menit");
        lblTarifReguler.setText("Rp");
    }
    
    private TarifHelper.PaketDokumen paketTerpilih() {
        if (selectedArmadaPanel == panelReguler) return TarifHelper.PaketDokumen.REGULER;
        return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPesan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnKembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnPeta = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblJemput = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTujuan = new javax.swing.JLabel();
        txtPengirim = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();
        lblJarak = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        txtPenerima = new javax.swing.JTextField();
        txtTelpPenerima = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelReguler = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblMenitReguler = new javax.swing.JLabel();
        lblTarifReguler = new javax.swing.JLabel();
        lblKmReguler = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnPembayaran = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtDetail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pemesanan Transportasi");
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(390, 844));

        pnlPesan.setBackground(new java.awt.Color(14, 165, 233));
        pnlPesan.setPreferredSize(new java.awt.Dimension(390, 1400));
        pnlPesan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Express Courier Showcase Card.png"))); // NOI18N
        jLabel7.setToolTipText("");
        pnlPesan.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 380, 90));

        jPanel1.setBackground(new java.awt.Color(2, 132, 199));
        jPanel1.setLayout(null);

        btnKembali.setBackground(new java.awt.Color(60, 175, 229));
        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Container(1).png"))); // NOI18N
        btnKembali.setBorder(null);
        btnKembali.addActionListener(this::btnKembaliActionPerformed);
        jPanel1.add(btnKembali);
        btnKembali.setBounds(10, 10, 30, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/logo ruteconnect logo kecil.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 0, 40, 50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RuteConnect");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(90, 0, 110, 30);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pengiriman Dokumen");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(90, 20, 180, 30);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(4).png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(320, 0, 50, 50);

        pnlPesan.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnPeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Screenshot 2026-11-05 084140.png"))); // NOI18N
        btnPeta.setToolTipText("");
        btnPeta.setBorder(null);
        btnPeta.addActionListener(this::btnPetaActionPerformed);
        jPanel2.add(btnPeta);
        btnPeta.setBounds(10, 20, 350, 180);

        jPanel3.setBackground(new java.awt.Color(242, 243, 255));
        jPanel3.setLayout(null);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(1).png"))); // NOI18N
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 10, 32, 32);

        jLabel13.setForeground(new java.awt.Color(63, 72, 80));
        jLabel13.setText("TITIK JEMPUT (Pengirim)");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(60, 10, 160, 16);

        lblJemput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJemput.setForeground(new java.awt.Color(0, 0, 0));
        lblJemput.setText("Pilih Titik Jemput");
        jPanel3.add(lblJemput);
        lblJemput.setBounds(60, 30, 270, 50);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(2).png"))); // NOI18N
        jPanel3.add(jLabel15);
        jLabel15.setBounds(10, 160, 32, 32);

        jLabel16.setForeground(new java.awt.Color(63, 72, 80));
        jLabel16.setText("TITIK TUJUAN (Penerima)");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(60, 150, 160, 16);

        lblTujuan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTujuan.setForeground(new java.awt.Color(0, 0, 0));
        lblTujuan.setText("Pilih Titik Tujuan");
        jPanel3.add(lblTujuan);
        lblTujuan.setBounds(60, 170, 270, 50);

        txtPengirim.setBackground(new java.awt.Color(255, 255, 255));
        txtPengirim.setForeground(new java.awt.Color(0, 0, 0));
        txtPengirim.addActionListener(this::txtPengirimActionPerformed);
        jPanel3.add(txtPengirim);
        txtPengirim.setBounds(60, 80, 280, 30);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Margin(1).png"))); // NOI18N
        jPanel3.add(jLabel18);
        jLabel18.setBounds(10, 50, 340, 32);

        jLabel38.setForeground(new java.awt.Color(62, 72, 80));
        jLabel38.setText("Estimasi Tiba:");
        jPanel3.add(jLabel38);
        jLabel38.setBounds(20, 310, 120, 16);

        lblWaktu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblWaktu.setForeground(new java.awt.Color(0, 97, 148));
        lblWaktu.setText("0 Menit");
        jPanel3.add(lblWaktu);
        lblWaktu.setBounds(190, 320, 100, 16);

        lblJarak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJarak.setForeground(new java.awt.Color(0, 97, 148));
        lblJarak.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblJarak.setText("(0 Km)");
        jPanel3.add(lblJarak);
        lblJarak.setBounds(280, 320, 60, 16);

        btnReset.setBackground(new java.awt.Color(2, 132, 199));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset Lokasi");
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel3.add(btnReset);
        btnReset.setBounds(10, 330, 120, 33);

        txtPenerima.setBackground(new java.awt.Color(255, 255, 255));
        txtPenerima.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtPenerima);
        txtPenerima.setBounds(60, 220, 280, 30);

        txtTelpPenerima.setBackground(new java.awt.Color(255, 255, 255));
        txtTelpPenerima.setForeground(new java.awt.Color(0, 0, 0));
        txtTelpPenerima.addActionListener(this::txtTelpPenerimaActionPerformed);
        jPanel3.add(txtTelpPenerima);
        txtTelpPenerima.setBounds(60, 260, 280, 26);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(20, 230, 34, 16);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Telp:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 270, 37, 16);
        jPanel3.add(jSpinner1);
        jSpinner1.setBounds(220, 370, 120, 26);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(20, 90, 43, 16);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pilih Waktu:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(150, 370, 70, 30);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 280, 350, 410);

        panelReguler.setBackground(new java.awt.Color(242, 243, 255));
        panelReguler.setLayout(null);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(13).png"))); // NOI18N
        panelReguler.add(jLabel30);
        jLabel30.setBounds(10, 10, 37, 60);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Reguler");
        panelReguler.add(jLabel31);
        jLabel31.setBounds(80, 10, 230, 20);

        lblMenitReguler.setForeground(new java.awt.Color(63, 72, 80));
        lblMenitReguler.setText("0 Menit");
        panelReguler.add(lblMenitReguler);
        lblMenitReguler.setBounds(90, 30, 70, 40);

        lblTarifReguler.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifReguler.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifReguler.setText("Rp 0");
        panelReguler.add(lblTarifReguler);
        lblTarifReguler.setBounds(250, 30, 90, 30);

        lblKmReguler.setForeground(new java.awt.Color(63, 72, 80));
        lblKmReguler.setText("0 Km");
        panelReguler.add(lblKmReguler);
        lblKmReguler.setBounds(160, 30, 70, 40);

        jPanel2.add(panelReguler);
        panelReguler.setBounds(10, 810, 350, 70);

        lblStatus.setForeground(new java.awt.Color(0, 0, 0));
        lblStatus.setText("Klik peta untuk memilih lokasi jemput dan tujuan.");
        lblStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(242, 243, 255), 4, true));
        jPanel2.add(lblStatus);
        lblStatus.setBounds(10, 220, 350, 40);

        btnPembayaran.setBackground(new java.awt.Color(14, 165, 233));
        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(255, 255, 255));
        btnPembayaran.setText("Lanjut Ke Menu Pembayaran →");
        btnPembayaran.addActionListener(this::btnPembayaranActionPerformed);
        jPanel2.add(btnPembayaran);
        btnPembayaran.setBounds(20, 900, 330, 50);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Detail Dokumen");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 700, 150, 16);

        txtDetail.setBackground(new java.awt.Color(242, 243, 255));
        txtDetail.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtDetail);
        txtDetail.setBounds(50, 720, 290, 50);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Tingkat Kecepatan");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(20, 780, 110, 16);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Container(6).png"))); // NOI18N
        jPanel2.add(jLabel14);
        jLabel14.setBounds(10, 720, 350, 48);

        pnlPesan.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 390, 1380));

        jScrollPane1.setViewportView(pnlPesan);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        ruteSudahDipilih = false;
        namaJemput = "";
        namaTujuan = "";
        selectedArmadaPanel = null;
        resetAllArmadaPanels();
        kosongkanTampilan();
        resetDataArmada();
        peta.resetPeta();
        jarakRuteKm = 0;
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetaActionPerformed
        // TODO add your handling code here:
        try {
            peta.bukaDiBrowser();
            lblStatus.setText("Peta dibuka di browser. Pilih lokasi lalu kembali ke sini.");
        } catch (Exception e) {
            lblStatus.setText("Gagal membuka browser: " + e.getMessage());
        }
    }//GEN-LAST:event_btnPetaActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        peta.berhenti(); // lepas port 8765
        java.awt.EventQueue.invokeLater(() -> new MenuPelangganForm().setVisible(true));
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembayaranActionPerformed
        // TODO add your handling code here:
        // 1. Validasi pemilihan rute
        if (!ruteSudahDipilih) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Silakan tentukan titik jemput dan titik tujuan terlebih dahulu!",
                "Rute Belum Dipilih",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Validasi pemilihan layanan
        if (selectedArmadaPanel == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Silakan pilih layanan Reguler terlebih dahulu!",
                "Layanan Belum Dipilih",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Validasi nama penerima
        String namaPenerima = txtPenerima.getText().trim();

        if (namaPenerima.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Nama penerima wajib diisi!",
                "Input Belum Lengkap",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            txtPenerima.requestFocus();
            return;
        }

        if (!Validator.namaValid(namaPenerima)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Nama penerima harus terdiri dari 3-50 karakter dan hanya boleh berisi huruf.",
                "Nama Penerima Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            txtPenerima.requestFocus();
            return;
        }

        // 4. Validasi nomor telepon penerima
        String noTelp = txtTelpPenerima.getText().trim();

        if (noTelp.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Nomor telepon penerima wajib diisi!",
                "Input Belum Lengkap",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            txtTelpPenerima.requestFocus();
            return;
        }

        if (!Validator.teleponValid(noTelp)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Nomor telepon tidak valid!\n"
                + "Gunakan format 08xxxxxxxxxx (10-15 digit).",
                "Format Nomor Telepon Salah",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            txtTelpPenerima.requestFocus();
            return;
        }

        // 5. Validasi jarak rute
        if (!TarifHelper.dalamBatas(jarakRuteKm)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Jarak di luar batas layanan (maks "
                + (int) TarifHelper.BATAS_KM + " km).",
                "Jarak Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 6. Mengambil paket dan menghitung total biaya
        TarifHelper.PaketDokumen paket = paketTerpilih();

        if (paket == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Layanan belum dipilih!",
                "Peringatan",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        double total = TarifHelper.hitungHarga(paket, jarakRuteKm);

        // 7. Mencari layanan di database
        model.Layanan layanan = null;

        for (model.Layanan l : new dao.LayananDao().findAll()) {
            if (l.getNamaLayanan().equalsIgnoreCase(namaLayananDb(paket))) {
                layanan = l;
                break;
            }
        }

        if (layanan == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Layanan dokumen belum ada di database!");
            return;
        }

        // 8. Menyimpan data pesanan ke draft
        try {
            model.Dokumen d = new model.Dokumen();
            d.setLokasiJemput(namaJemput);
            d.setLokasiTujuan(namaTujuan);
            d.setJarakKm(jarakRuteKm);
            java.util.Date waktuPilih = (java.util.Date) jSpinner1.getValue();
            d.setWaktuPenjemputan(new java.sql.Timestamp(waktuPilih.getTime()));
            d.setCatatan("Pengirim: " + txtPengirim.getText().trim());
            d.setTotalBiaya(total);
            d.setPelangganId(controller.Session.userId);
            d.setLayananId(layanan.getLayananId());
            d.setNamaLayanan(layanan.getNamaLayanan());
            d.setNamaPenerima(namaPenerima);
            d.setNoTeleponPenerima(noTelp);
            d.setDetailDokumen(txtDetail.getText().trim());
            controller.Session.draft = d;
            
            // Data ringkasan untuk halaman pembayaran
            controller.Session.ringkasanJudul = layanan.getNamaLayanan();
            controller.Session.ringkasanSub = "Pengiriman Dokumen - " + paket.getKelas();
            controller.Session.ringkasanMenit = TarifHelper.hitungMenit(paket, jarakRuteKm);
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Gagal Membuat Pesanan",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 9. Beralih ke halaman pembayaran
        peta.berhenti();

        java.awt.EventQueue.invokeLater(() ->
            new StatusPembayaran(total).setVisible(true)
        );

        this.dispose();
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void txtTelpPenerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpPenerimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpPenerimaActionPerformed

    private void txtPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPengirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPengirimActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new PengirimanDokumenForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPembayaran;
    private javax.swing.JButton btnPeta;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblJarak;
    private javax.swing.JLabel lblJemput;
    private javax.swing.JLabel lblKmReguler;
    private javax.swing.JLabel lblMenitReguler;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTarifReguler;
    private javax.swing.JLabel lblTujuan;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JPanel panelReguler;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JTextField txtDetail;
    private javax.swing.JTextField txtPenerima;
    private javax.swing.JTextField txtPengirim;
    private javax.swing.JTextField txtTelpPenerima;
    // End of variables declaration//GEN-END:variables
}
