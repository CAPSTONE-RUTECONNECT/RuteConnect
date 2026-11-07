/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import java.io.IOException;
import controller.PetaServer;
import util.TarifHelper;

/**
 *
 * @author HP
 */
public class Maps extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Maps.class.getName());
    private PetaServer peta;
    private javax.swing.JPanel selectedArmadaPanel;
    private double jarakRuteKm = 0;
    private boolean ruteSudahDipilih = false;
    private String namaJemput = "", namaTujuan = "";

    public Maps() {
        initComponents();
        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        javax.swing.JSpinner.DateEditor editor = new javax.swing.JSpinner.DateEditor(jSpinner1, "HH:mm");
        jSpinner1.setEditor(editor);
        getContentPane().setPreferredSize(new java.awt.Dimension(390, 844));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        
        setupArmadaPanel(panelMotorBiasa);
        setupArmadaPanel(panelMotorCom);
        setupArmadaPanel(panelMobil4);
        setupArmadaPanel(panelMobil6);
        setupArmadaPanel(panelMobil8);
        
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

            // 1. Motor Comfort
            updateArmadaData(
                TarifHelper.Paket.MOTOR_COMFORT, 
                kmPeta, 
                lblKmMotorCom, 
                lblMenitMotorCom, 
                lblTarifMotorCom
            );

            // 2. Motor Biasa
            updateArmadaData(
                TarifHelper.Paket.MOTOR_BIASA, 
                kmPeta, 
                lblKmMotorBiasa, 
                lblMenitMotorBiasa, 
                lblTarifMotorBiasa
            );

            // 3. Mobil 4 Seat (RuteCar)
            updateArmadaData(
                TarifHelper.Paket.MOBIL_4_SEAT, 
                kmPeta, 
                lblKmMobil4, 
                lblMenitMobil4, 
                lblTarifMobil4
            );

            // 4. Mobil 6 Seat (RuteCar Comfort)
            updateArmadaData(
                TarifHelper.Paket.MOBIL_6_SEAT, 
                kmPeta, 
                lblKmMobil6, 
                lblMenitMobil6, 
                lblTarifMobil6
            );

            // 5. Mobil 8 Seat (RuteCar XL)
            updateArmadaData(
                TarifHelper.Paket.MOBIL_8_SEAT, 
                kmPeta, 
                lblKmMobil8, 
                lblMobilMenit8, 
                lblTarifMobil8
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
    
    private String namaLayananDb(TarifHelper.Paket p) {
        return switch (p) {
            case MOTOR_BIASA -> "RuteBike Regular";
            case MOTOR_COMFORT -> "RuteBike Comfort";
            case MOBIL_4_SEAT -> "RuteCar 4 Penumpang";
            case MOBIL_6_SEAT -> "RuteCar Comfort 6 Penumpang";
            case MOBIL_8_SEAT -> "RuteCar 8 Penumpang";
            default -> "";
        };
    }

    // Fungsi untuk mereset warna semua panel armada ke normal
    private void resetAllArmadaPanels() {
        java.awt.Color normalBg = new java.awt.Color(242, 243, 255);
        
        panelMotorBiasa.setBackground(normalBg);
        panelMotorCom.setBackground(normalBg);
        panelMobil4.setBackground(normalBg);
        panelMobil6.setBackground(normalBg);
        panelMobil8.setBackground(normalBg);
        selectedArmadaPanel = null;
    }
    
    private void munculkanJendela() {
        // kalau sedang di-minimize, kembalikan dulu ke ukuran normal
        if ((getExtendedState() & java.awt.Frame.ICONIFIED) != 0) {
            setExtendedState(java.awt.Frame.NORMAL);
        }
        // trik always-on-top: dipaksa ke depan, lalu dilepas lagi
        setAlwaysOnTop(true);
        toFront();
        
        // Perbaikan agar JScrollPane tetap bisa digulir setelah pop-up/fokus masuk
        java.awt.EventQueue.invokeLater(() -> {
            jScrollPane1.getVerticalScrollBar().setValue(0); // Reset posisi scroll ke atas (opsional)
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
    
    private void updateArmadaData(TarifHelper.Paket paket, double kmPeta, javax.swing.JLabel lblKm, javax.swing.JLabel lblMenit, javax.swing.JLabel lblTarif) {
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
        javax.swing.JLabel[] labelKmList = {lblKmMotorCom, lblKmMotorBiasa, lblKmMobil4, lblKmMobil6, lblKmMobil8};
        javax.swing.JLabel[] labelMenitList = {lblMenitMotorCom, lblMenitMotorBiasa, lblMenitMobil4, lblMenitMobil6, lblMobilMenit8};
        javax.swing.JLabel[] labelTarifList = {lblTarifMotorCom, lblTarifMotorBiasa, lblTarifMobil4, lblTarifMobil6, lblTarifMobil8};

        for (int i = 0; i < labelKmList.length; i++) {
            if (labelKmList[i] != null) labelKmList[i].setText("0 Km");
            if (labelMenitList[i] != null) labelMenitList[i].setText("0 Menit");
            if (labelTarifList[i] != null) labelTarifList[i].setText("Rp");
        }
    }
    
    private TarifHelper.Paket paketTerpilih() {
        if (selectedArmadaPanel == panelMotorBiasa)
            return TarifHelper.Paket.MOTOR_BIASA;
        if (selectedArmadaPanel == panelMotorCom)
            return TarifHelper.Paket.MOTOR_COMFORT;
        if (selectedArmadaPanel == panelMobil4)
            return TarifHelper.Paket.MOBIL_4_SEAT;
        if (selectedArmadaPanel == panelMobil6)
            return TarifHelper.Paket.MOBIL_6_SEAT;
        if (selectedArmadaPanel == panelMobil8)
            return TarifHelper.Paket.MOBIL_8_SEAT;
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnPeta = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblJemput = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTujuan = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();
        lblJarak = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelMotorCom = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblTarifMotorCom = new javax.swing.JLabel();
        lblMenitMotorCom = new javax.swing.JLabel();
        lblKmMotorCom = new javax.swing.JLabel();
        panelMotorBiasa = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblTarifMotorBiasa = new javax.swing.JLabel();
        lblMenitMotorBiasa = new javax.swing.JLabel();
        lblKmMotorBiasa = new javax.swing.JLabel();
        panelMobil4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblTarifMobil4 = new javax.swing.JLabel();
        lblMenitMobil4 = new javax.swing.JLabel();
        lblKmMobil4 = new javax.swing.JLabel();
        panelMobil6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblMenitMobil6 = new javax.swing.JLabel();
        lblTarifMobil6 = new javax.swing.JLabel();
        lblKmMobil6 = new javax.swing.JLabel();
        panelMobil8 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblMobilMenit8 = new javax.swing.JLabel();
        lblTarifMobil8 = new javax.swing.JLabel();
        lblKmMobil8 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtCatatan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnPembayaran = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pemesanan Transportasi");
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(390, 844));

        pnlPesan.setBackground(new java.awt.Color(14, 165, 233));
        pnlPesan.setPreferredSize(new java.awt.Dimension(390, 1400));
        pnlPesan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay+OverlayBlur.png"))); // NOI18N
        jLabel7.setToolTipText("");
        pnlPesan.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 90, 90));

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
        jLabel8.setText("Pemesanan Transportasi");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(90, 20, 180, 30);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(4).png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(320, 0, 50, 50);

        pnlPesan.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 50));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Margin.png"))); // NOI18N
        pnlPesan.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 30));

        jLabel11.setForeground(new java.awt.Color(226, 231, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Pilih armada dan tentukan perjalanan nyamanmu hari ini");
        pnlPesan.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 320, 30));

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
        jLabel13.setText("TITIK JEMPUT");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(60, 10, 120, 16);

        lblJemput.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJemput.setForeground(new java.awt.Color(0, 0, 0));
        lblJemput.setText("Pilih Titik Jemput");
        jPanel3.add(lblJemput);
        lblJemput.setBounds(60, 30, 270, 50);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(2).png"))); // NOI18N
        jPanel3.add(jLabel15);
        jLabel15.setBounds(10, 110, 32, 32);

        jLabel16.setForeground(new java.awt.Color(63, 72, 80));
        jLabel16.setText("TITIK TUJUAN");
        jPanel3.add(jLabel16);
        jLabel16.setBounds(60, 100, 100, 16);

        lblTujuan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTujuan.setForeground(new java.awt.Color(0, 0, 0));
        lblTujuan.setText("Pilih Titik Tujuan");
        jPanel3.add(lblTujuan);
        lblTujuan.setBounds(60, 120, 270, 50);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Margin(1).png"))); // NOI18N
        jPanel3.add(jLabel18);
        jLabel18.setBounds(10, 50, 340, 50);

        jLabel38.setForeground(new java.awt.Color(62, 72, 80));
        jLabel38.setText("Estimasi Tiba:");
        jPanel3.add(jLabel38);
        jLabel38.setBounds(10, 190, 120, 16);

        lblWaktu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblWaktu.setForeground(new java.awt.Color(0, 97, 148));
        lblWaktu.setText("0 Menit");
        jPanel3.add(lblWaktu);
        lblWaktu.setBounds(190, 190, 100, 16);

        lblJarak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJarak.setForeground(new java.awt.Color(0, 97, 148));
        lblJarak.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblJarak.setText("(0 Km)");
        jPanel3.add(lblJarak);
        lblJarak.setBounds(280, 190, 60, 16);

        btnReset.setBackground(new java.awt.Color(2, 132, 199));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset Lokasi");
        btnReset.addActionListener(this::btnResetActionPerformed);
        jPanel3.add(btnReset);
        btnReset.setBounds(10, 220, 120, 33);
        jPanel3.add(jSpinner1);
        jSpinner1.setBounds(220, 240, 120, 26);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pilih Waktu:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(150, 240, 70, 30);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 280, 350, 290);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pilih Armada");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(20, 570, 140, 20);

        panelMotorCom.setBackground(new java.awt.Color(242, 243, 255));
        panelMotorCom.setLayout(null);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(8).png"))); // NOI18N
        panelMotorCom.add(jLabel17);
        jLabel17.setBounds(10, 8, 58, 60);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(3).png"))); // NOI18N
        jLabel19.setText("RuteRide Comfort");
        panelMotorCom.add(jLabel19);
        jLabel19.setBounds(80, 10, 240, 20);

        lblTarifMotorCom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifMotorCom.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifMotorCom.setText("Rp 0");
        panelMotorCom.add(lblTarifMotorCom);
        lblTarifMotorCom.setBounds(250, 30, 80, 30);

        lblMenitMotorCom.setForeground(new java.awt.Color(63, 72, 80));
        lblMenitMotorCom.setText("0 Menit");
        panelMotorCom.add(lblMenitMotorCom);
        lblMenitMotorCom.setBounds(90, 30, 60, 40);

        lblKmMotorCom.setForeground(new java.awt.Color(63, 72, 80));
        lblKmMotorCom.setText("0 Km");
        panelMotorCom.add(lblKmMotorCom);
        lblKmMotorCom.setBounds(150, 30, 70, 40);

        jPanel2.add(panelMotorCom);
        panelMotorCom.setBounds(10, 680, 350, 70);

        panelMotorBiasa.setBackground(new java.awt.Color(242, 243, 255));
        panelMotorBiasa.setLayout(null);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(8).png"))); // NOI18N
        panelMotorBiasa.add(jLabel22);
        jLabel22.setBounds(10, 10, 58, 60);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(4).png"))); // NOI18N
        jLabel23.setText("RuteRide Reguler");
        panelMotorBiasa.add(jLabel23);
        jLabel23.setBounds(80, 10, 230, 20);

        lblTarifMotorBiasa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifMotorBiasa.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifMotorBiasa.setText("Rp 0");
        panelMotorBiasa.add(lblTarifMotorBiasa);
        lblTarifMotorBiasa.setBounds(253, 20, 90, 30);

        lblMenitMotorBiasa.setForeground(new java.awt.Color(63, 72, 80));
        lblMenitMotorBiasa.setText("0 Menit");
        panelMotorBiasa.add(lblMenitMotorBiasa);
        lblMenitMotorBiasa.setBounds(90, 30, 70, 40);

        lblKmMotorBiasa.setForeground(new java.awt.Color(63, 72, 80));
        lblKmMotorBiasa.setText("0 Km");
        panelMotorBiasa.add(lblKmMotorBiasa);
        lblKmMotorBiasa.setBounds(160, 30, 70, 40);

        jPanel2.add(panelMotorBiasa);
        panelMotorBiasa.setBounds(10, 600, 350, 70);

        panelMobil4.setBackground(new java.awt.Color(242, 243, 255));
        panelMobil4.setLayout(null);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(9).png"))); // NOI18N
        panelMobil4.add(jLabel26);
        jLabel26.setBounds(10, 10, 58, 60);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(5).png"))); // NOI18N
        jLabel27.setText("RuteCar");
        panelMobil4.add(jLabel27);
        jLabel27.setBounds(80, 10, 170, 20);

        lblTarifMobil4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifMobil4.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifMobil4.setText("Rp 0");
        panelMobil4.add(lblTarifMobil4);
        lblTarifMobil4.setBounds(253, 20, 90, 30);

        lblMenitMobil4.setForeground(new java.awt.Color(63, 72, 80));
        lblMenitMobil4.setText("0 Menit");
        panelMobil4.add(lblMenitMobil4);
        lblMenitMobil4.setBounds(90, 36, 70, 30);

        lblKmMobil4.setForeground(new java.awt.Color(63, 72, 80));
        lblKmMobil4.setText("0 Km");
        panelMobil4.add(lblKmMobil4);
        lblKmMobil4.setBounds(160, 30, 70, 40);

        jPanel2.add(panelMobil4);
        panelMobil4.setBounds(10, 760, 350, 70);

        panelMobil6.setBackground(new java.awt.Color(242, 243, 255));
        panelMobil6.setLayout(null);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(11).png"))); // NOI18N
        panelMobil6.add(jLabel30);
        jLabel30.setBounds(10, 10, 58, 60);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(6).png"))); // NOI18N
        jLabel31.setText("RuteCar Comfort");
        panelMobil6.add(jLabel31);
        jLabel31.setBounds(80, 10, 230, 20);

        lblMenitMobil6.setForeground(new java.awt.Color(63, 72, 80));
        lblMenitMobil6.setText("0 Menit");
        panelMobil6.add(lblMenitMobil6);
        lblMenitMobil6.setBounds(90, 30, 70, 40);

        lblTarifMobil6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifMobil6.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifMobil6.setText("Rp 0");
        panelMobil6.add(lblTarifMobil6);
        lblTarifMobil6.setBounds(250, 30, 90, 30);

        lblKmMobil6.setForeground(new java.awt.Color(63, 72, 80));
        lblKmMobil6.setText("0 Km");
        panelMobil6.add(lblKmMobil6);
        lblKmMobil6.setBounds(160, 30, 70, 40);

        jPanel2.add(panelMobil6);
        panelMobil6.setBounds(10, 840, 350, 70);

        panelMobil8.setBackground(new java.awt.Color(242, 243, 255));
        panelMobil8.setLayout(null);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(11).png"))); // NOI18N
        jLabel34.setToolTipText("");
        panelMobil8.add(jLabel34);
        jLabel34.setBounds(10, 10, 58, 60);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay(7).png"))); // NOI18N
        jLabel35.setText("RuteCar XL");
        panelMobil8.add(jLabel35);
        jLabel35.setBounds(80, 10, 190, 20);

        lblMobilMenit8.setForeground(new java.awt.Color(63, 72, 80));
        lblMobilMenit8.setText("0 Menit");
        panelMobil8.add(lblMobilMenit8);
        lblMobilMenit8.setBounds(90, 30, 70, 40);

        lblTarifMobil8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarifMobil8.setForeground(new java.awt.Color(0, 0, 0));
        lblTarifMobil8.setText("Rp 0");
        panelMobil8.add(lblTarifMobil8);
        lblTarifMobil8.setBounds(250, 20, 90, 30);

        lblKmMobil8.setForeground(new java.awt.Color(63, 72, 80));
        lblKmMobil8.setText("0 Km");
        panelMobil8.add(lblKmMobil8);
        lblKmMobil8.setBounds(160, 36, 80, 30);

        jPanel2.add(panelMobil8);
        panelMobil8.setBounds(10, 920, 350, 70);

        lblStatus.setForeground(new java.awt.Color(0, 0, 0));
        lblStatus.setText("Klik peta untuk memilih lokasi jemput dan tujuan.");
        lblStatus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(242, 243, 255), 4, true));
        jPanel2.add(lblStatus);
        lblStatus.setBounds(10, 220, 350, 40);

        txtCatatan.setBackground(new java.awt.Color(242, 243, 255));
        txtCatatan.setForeground(new java.awt.Color(0, 0, 0));
        txtCatatan.addActionListener(this::txtCatatanActionPerformed);
        jPanel2.add(txtCatatan);
        txtCatatan.setBounds(70, 1040, 280, 50);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Container(6).png"))); // NOI18N
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 1040, 350, 48);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Catatan Untuk Driver (Opsional)");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(40, 1020, 320, 16);

        btnPembayaran.setBackground(new java.awt.Color(14, 165, 233));
        btnPembayaran.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPembayaran.setForeground(new java.awt.Color(255, 255, 255));
        btnPembayaran.setText("Lanjut Ke Menu Pembayaran →");
        btnPembayaran.addActionListener(this::btnPembayaranActionPerformed);
        jPanel2.add(btnPembayaran);
        btnPembayaran.setBounds(30, 1110, 330, 50);

        pnlPesan.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 390, 1350));

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
        if (!ruteSudahDipilih) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Silakan tentukan titik jemput dan titik tujuan terlebih dahulu!",
                "Rute Belum Dipilih", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedArmadaPanel == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Silakan pilih salah satu armada terlebih dahulu!",
                "Armada Belum Dipilih", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        TarifHelper.Paket paket = paketTerpilih();
        double total = TarifHelper.hitungHarga(paket, jarakRuteKm);

        model.Layanan layanan = null;
        for (model.Layanan l : new dao.LayananDao().findAll()) {
            if (l.getNamaLayanan().equalsIgnoreCase(namaLayananDb(paket))) {
                layanan = l; break;
            }
        }
        if (layanan == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Layanan untuk armada ini belum ada di database.");
            return;
        }

        try {
            java.util.Date waktuPilih = (java.util.Date) jSpinner1.getValue();
            java.sql.Timestamp waktuPenjemputan = new java.sql.Timestamp(waktuPilih.getTime());
            model.Transportasi t = new model.Transportasi();
            t.setLokasiJemput(namaJemput);
            t.setLokasiTujuan(namaTujuan);
            t.setJarakKm(jarakRuteKm);
            t.setWaktuPenjemputan(waktuPenjemputan);
            t.setCatatan(txtCatatan.getText());
            t.setTotalBiaya(total);
            t.setPelangganId(controller.Session.userId);
            t.setLayananId(layanan.getLayananId());
            t.setNamaLayanan(layanan.getNamaLayanan());
            t.setJumlahPenumpang(1);
            controller.Session.draft = t;

            // data untuk jPanel3 di halaman pembayaran
            controller.Session.ringkasanJudul = layanan.getNamaLayanan();
            controller.Session.ringkasanSub = "Transportasi " + layanan.getJenisKendaraan();
            controller.Session.ringkasanMenit = TarifHelper.hitungMenit(paket, jarakRuteKm);
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        peta.berhenti(); // lepas port 8765
        java.awt.EventQueue.invokeLater(() -> new StatusPembayaran(total).setVisible(true));
        this.dispose();
    }//GEN-LAST:event_btnPembayaranActionPerformed

    private void txtCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatatanActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Maps().setVisible(true));
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
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel lblKmMobil4;
    private javax.swing.JLabel lblKmMobil6;
    private javax.swing.JLabel lblKmMobil8;
    private javax.swing.JLabel lblKmMotorBiasa;
    private javax.swing.JLabel lblKmMotorCom;
    private javax.swing.JLabel lblMenitMobil4;
    private javax.swing.JLabel lblMenitMobil6;
    private javax.swing.JLabel lblMenitMotorBiasa;
    private javax.swing.JLabel lblMenitMotorCom;
    private javax.swing.JLabel lblMobilMenit8;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTarifMobil4;
    private javax.swing.JLabel lblTarifMobil6;
    private javax.swing.JLabel lblTarifMobil8;
    private javax.swing.JLabel lblTarifMotorBiasa;
    private javax.swing.JLabel lblTarifMotorCom;
    private javax.swing.JLabel lblTujuan;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JPanel panelMobil4;
    private javax.swing.JPanel panelMobil6;
    private javax.swing.JPanel panelMobil8;
    private javax.swing.JPanel panelMotorBiasa;
    private javax.swing.JPanel panelMotorCom;
    private javax.swing.JPanel pnlPesan;
    private javax.swing.JTextField txtCatatan;
    // End of variables declaration//GEN-END:variables
}
