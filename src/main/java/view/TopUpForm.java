/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import controller.TopUpService;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class TopUpForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TopUpForm.class.getName());

    /**
     * Creates new form FrameTopUp
     */
    public TopUpForm() {
        initComponents();
        setLocationRelativeTo(null);
        
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 1230));
        getContentPane().removeAll();
        
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16); // Biar smooth scroll-nya
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jScrollPane1);
        
        revalidate();
        repaint();
        
        controller.Session.refreshSaldo();
        lblSaldo.setText(controller.Fmt.rupiah(controller.Session.saldo));
        
        javax.swing.ButtonGroup metodeGroup = new javax.swing.ButtonGroup();
        metodeGroup.add(radioBtnEwallet);
        metodeGroup.add(radioBtnBank);
        metodeGroup.add(radioBtnMinimarket);
        
        setupPaymentPanel(panelEwallet, radioBtnEwallet);
        setupPaymentPanel(panelBank, radioBtnBank);
        setupPaymentPanel(panelMinimarket, radioBtnMinimarket);
        
        setupNominalPanel(panel20, lbl20, "20000");
        setupNominalPanel(panel50, lbl50, "50000");
        setupNominalPanel(panel100, lbl100, "100000");
        setupNominalPanel(panel200, lbl200, "200000");
        setupNominalPanel(panel500, lbl500, "500000");
    }
    
    private void setupPaymentPanel(javax.swing.JPanel panel, javax.swing.JRadioButton radioButton) {
        java.awt.Color normalColor = panel.getBackground();
        java.awt.Color hoverColor = new java.awt.Color(230, 238, 255); // Biru muda lembut saat di-hover
        java.awt.Color selectColor = new java.awt.Color(210, 225, 255); // Biru terang saat dipilih

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!radioButton.isSelected()) {
                    panel.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!radioButton.isSelected()) {
                    panel.setBackground(normalColor);
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton.setSelected(true);
                updatePaymentSelection(panel, radioButton);
            }
        });

        // Kalau radio button-nya diklik langsung
        radioButton.addActionListener(e -> updatePaymentSelection(panel, radioButton));
    }

    // Fungsi untuk mereset warna semua panel pembayaran
    private void updatePaymentSelection(javax.swing.JPanel activePanel, javax.swing.JRadioButton activeRadio) {
        java.awt.Color normalColor = new java.awt.Color(242, 243, 255);
        java.awt.Color selectColor = new java.awt.Color(210, 225, 255);

        panelEwallet.setBackground(normalColor);
        panelBank.setBackground(normalColor);
        panelMinimarket.setBackground(normalColor);

        activePanel.setBackground(selectColor);
    }
    
    // Fungsi bantuan khusus untuk panel pilihan nominal
    private void setupNominalPanel(javax.swing.JPanel panel, javax.swing.JLabel label, String nominalValue) {
        java.awt.Color normalBg = new java.awt.Color(242, 243, 255);
        java.awt.Color normalFg = new java.awt.Color(0, 0, 0); 
        java.awt.Color hoverColor = new java.awt.Color(230, 238, 255); 
        java.awt.Color selectBg = new java.awt.Color(2, 132, 199); // #0284C7
        java.awt.Color selectFg = java.awt.Color.WHITE; 

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!txtNominal.getText().equals(nominalValue)) {
                    panel.setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!txtNominal.getText().equals(nominalValue)) {
                    panel.setBackground(normalBg);
                }
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNominal.setText(nominalValue);
                resetAllNominalPanels();
                panel.setBackground(selectBg);
                label.setForeground(selectFg);
            }
        });
        
        txtNominal.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { checkManualInput(); }
            
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { checkManualInput(); }
            
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { checkManualInput(); }
            
            private void checkManualInput() {
                if (!txtNominal.getText().equals(nominalValue)) {
                    if (panel.getBackground().equals(selectBg)) {
                        panel.setBackground(normalBg);
                        label.setForeground(normalFg);
                    }
                }
            }
        });
    }

    // Fungsi untuk mereset semua panel nominal
    private void resetAllNominalPanels() {
        java.awt.Color normalBg = new java.awt.Color(242, 243, 255);
        java.awt.Color normalFg = new java.awt.Color(0, 0, 0); 

        panel20.setBackground(normalBg); lbl20.setForeground(normalFg);
        panel50.setBackground(normalBg); lbl50.setForeground(normalFg);
        panel100.setBackground(normalBg); lbl100.setForeground(normalFg);
        panel200.setBackground(normalBg); lbl200.setForeground(normalFg);
        panel500.setBackground(normalBg); lbl500.setForeground(normalFg);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panel20 = new javax.swing.JPanel();
        lbl20 = new javax.swing.JLabel();
        panel50 = new javax.swing.JPanel();
        lbl50 = new javax.swing.JLabel();
        panel100 = new javax.swing.JPanel();
        lbl100 = new javax.swing.JLabel();
        panel200 = new javax.swing.JPanel();
        lbl200 = new javax.swing.JLabel();
        panel500 = new javax.swing.JPanel();
        lbl500 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNominal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelEwallet = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        radioBtnEwallet = new javax.swing.JRadioButton();
        panelBank = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        radioBtnBank = new javax.swing.JRadioButton();
        panelMinimarket = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        radioBtnMinimarket = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        passwordCheckBox = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        btnTopUp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Top Up");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(14, 165, 233));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 1230));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Mini Brand App Symbol.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Isi Saldo RutePay");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 158, -1));

        jLabel3.setForeground(new java.awt.Color(226, 231, 255));
        jLabel3.setText("Dompet digital instan untuk perjalanan & kiriman aman");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jPanel2.setBackground(new java.awt.Color(2, 132, 199));
        jPanel2.setLayout(null);

        jButton1.setBackground(new java.awt.Color(60, 175, 229));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Container(1).png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jPanel2.add(jButton1);
        jButton1.setBounds(10, 10, 30, 30);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/logo ruteconnect logo kecil.png"))); // NOI18N
        jPanel2.add(jLabel4);
        jLabel4.setBounds(50, 0, 40, 50);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Top Up Saldo");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(90, 0, 91, 50);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(4).png"))); // NOI18N
        jPanel2.add(jLabel6);
        jLabel6.setBounds(330, 0, 50, 50);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(242, 243, 255));
        jPanel4.setLayout(null);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Saldo Anda Saat Ini");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(70, 10, 158, 16);

        lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(0, 0, 0));
        lblSaldo.setText("Rp");
        jPanel4.add(lblSaldo);
        lblSaldo.setBounds(70, 30, 260, 25);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Overlay.png"))); // NOI18N
        jPanel4.add(jLabel9);
        jLabel9.setBounds(10, 10, 60, 50);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(20, 20, 350, 70);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pilih Nominal Top Up");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(30, 110, 180, 20);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Bebas biaya admin");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(250, 114, 120, 16);

        panel20.setBackground(new java.awt.Color(242, 243, 255));
        panel20.setLayout(null);

        lbl20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl20.setForeground(new java.awt.Color(0, 0, 0));
        lbl20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl20.setText("Rp 20.000");
        panel20.add(lbl20);
        lbl20.setBounds(20, 0, 100, 50);

        jPanel3.add(panel20);
        panel20.setBounds(30, 160, 150, 50);

        panel50.setBackground(new java.awt.Color(242, 243, 255));
        panel50.setLayout(null);

        lbl50.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl50.setForeground(new java.awt.Color(0, 0, 0));
        lbl50.setText("Rp 50.000");
        panel50.add(lbl50);
        lbl50.setBounds(30, 0, 90, 50);

        jPanel3.add(panel50);
        panel50.setBounds(210, 160, 150, 50);

        panel100.setBackground(new java.awt.Color(242, 243, 255));
        panel100.setLayout(null);

        lbl100.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl100.setForeground(new java.awt.Color(0, 0, 0));
        lbl100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl100.setText("Rp 100.000");
        panel100.add(lbl100);
        lbl100.setBounds(20, 0, 110, 50);

        jPanel3.add(panel100);
        panel100.setBounds(30, 230, 150, 50);

        panel200.setBackground(new java.awt.Color(242, 243, 255));
        panel200.setLayout(null);

        lbl200.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl200.setForeground(new java.awt.Color(0, 0, 0));
        lbl200.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl200.setText("Rp 200.000");
        panel200.add(lbl200);
        lbl200.setBounds(20, 0, 110, 50);

        jPanel3.add(panel200);
        panel200.setBounds(210, 230, 150, 50);

        panel500.setBackground(new java.awt.Color(242, 243, 255));
        panel500.setLayout(null);

        lbl500.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl500.setForeground(new java.awt.Color(0, 0, 0));
        lbl500.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl500.setText("Rp 500.000");
        panel500.add(lbl500);
        lbl500.setBounds(90, 0, 140, 50);

        jPanel3.add(panel500);
        panel500.setBounds(30, 300, 330, 50);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Atau Masukkan Nominal Sendiri");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(30, 370, 240, 16);

        txtNominal.setBackground(new java.awt.Color(242, 243, 255));
        txtNominal.setForeground(new java.awt.Color(0, 0, 0));
        txtNominal.addActionListener(this::txtNominalActionPerformed);
        jPanel3.add(txtNominal);
        txtNominal.setBounds(70, 390, 280, 50);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Container(3).png"))); // NOI18N
        jPanel3.add(jLabel12);
        jLabel12.setBounds(10, 390, 370, 52);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Metode Pembayaran");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(30, 460, 170, 20);

        panelEwallet.setBackground(new java.awt.Color(242, 243, 255));
        panelEwallet.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Dompet Digital (E-Wallet)");
        panelEwallet.add(jLabel16);
        jLabel16.setBounds(60, 10, 200, 20);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background+Shadow_1.png"))); // NOI18N
        panelEwallet.add(jLabel14);
        jLabel14.setBounds(10, 10, 50, 44);

        jLabel15.setForeground(new java.awt.Color(63, 72, 80));
        jLabel15.setText("BCA, Mandiri, GoPay, ShopeePay, OVO");
        panelEwallet.add(jLabel15);
        jLabel15.setBounds(60, 30, 220, 16);
        panelEwallet.add(radioBtnEwallet);
        radioBtnEwallet.setBounds(300, 20, 20, 20);

        jPanel3.add(panelEwallet);
        panelEwallet.setBounds(30, 500, 330, 60);

        panelBank.setBackground(new java.awt.Color(242, 243, 255));
        panelBank.setLayout(null);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(6).png"))); // NOI18N
        panelBank.add(jLabel17);
        jLabel17.setBounds(10, 10, 50, 40);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Virtual Account Bank");
        panelBank.add(jLabel18);
        jLabel18.setBounds(60, 10, 170, 20);

        jLabel19.setForeground(new java.awt.Color(63, 72, 80));
        jLabel19.setText("BCA, Mandiri, BRI, BNI");
        panelBank.add(jLabel19);
        jLabel19.setBounds(60, 30, 160, 16);
        panelBank.add(radioBtnBank);
        radioBtnBank.setBounds(300, 20, 20, 20);

        jPanel3.add(panelBank);
        panelBank.setBounds(30, 580, 330, 60);

        panelMinimarket.setBackground(new java.awt.Color(242, 243, 255));
        panelMinimarket.setLayout(null);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Minimarket Terdekat");
        panelMinimarket.add(jLabel22);
        jLabel22.setBounds(60, 10, 170, 20);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Background(7).png"))); // NOI18N
        panelMinimarket.add(jLabel20);
        jLabel20.setBounds(10, 10, 50, 40);

        jLabel21.setForeground(new java.awt.Color(63, 72, 80));
        jLabel21.setText("Indomaret & Alfamart");
        panelMinimarket.add(jLabel21);
        jLabel21.setBounds(60, 30, 170, 16);
        panelMinimarket.add(radioBtnMinimarket);
        radioBtnMinimarket.setBounds(300, 20, 20, 20);

        jPanel3.add(panelMinimarket);
        panelMinimarket.setBounds(30, 660, 330, 60);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Icon(3).png"))); // NOI18N
        jLabel23.setText("Verifikasi Keamanan");
        jPanel3.add(jLabel23);
        jLabel23.setBounds(20, 750, 180, 20);

        txtPassword.setBackground(new java.awt.Color(242, 243, 255));
        txtPassword.addActionListener(this::txtPasswordActionPerformed);
        jPanel3.add(txtPassword);
        txtPassword.setBounds(64, 780, 290, 50);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/gambar/Password Input Pill styled identically to reference.png"))); // NOI18N
        jPanel3.add(jLabel24);
        jLabel24.setBounds(20, 780, 350, 50);

        passwordCheckBox.setForeground(new java.awt.Color(148, 163, 184));
        passwordCheckBox.setText("Tampilkan Password");
        passwordCheckBox.addActionListener(this::passwordCheckBoxActionPerformed);
        jPanel3.add(passwordCheckBox);
        passwordCheckBox.setBounds(30, 840, 230, 20);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("<html>Masukkan kata sandi login RuteConnect Anda untuk<br>memvalidasi transaksi pengisian saldo.</html>");
        jPanel3.add(jLabel25);
        jLabel25.setBounds(30, 870, 330, 32);

        btnTopUp.setBackground(new java.awt.Color(14, 165, 233));
        btnTopUp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTopUp.setForeground(new java.awt.Color(255, 255, 255));
        btnTopUp.setText("Top Up Sekarang →");
        btnTopUp.addActionListener(this::btnTopUpActionPerformed);
        jPanel3.add(btnTopUp);
        btnTopUp.setBounds(30, 920, 330, 50);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 390, 1030));

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void passwordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        if (passwordCheckBox.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('\u2022'); 
        }
    }                                                

    private void txtNominalActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void btnTopUpActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try {
            // 1. Validasi pilihan metode pembayaran (salah satu RadioButton harus dipilih)
            if (!radioBtnEwallet.isSelected() && !radioBtnBank.isSelected() && !radioBtnMinimarket.isSelected()) {
                JOptionPane.showMessageDialog(this, "Silakan pilih salah satu metode pembayaran terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Validasi input password tidak boleh kosong
            String passwordInput = new String(txtPassword.getPassword());
            if (passwordInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Silakan masukkan password verifikasi keamanan Anda!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Connection conn = config.Koneksi.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi database gagal!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Verifikasi password user yang sedang login menggunakan UserDao
            dao.UserDao userDao = new dao.UserDao();
            boolean passwordValid = userDao.cekPassword(controller.Session.userId, passwordInput);
            if (!passwordValid) {
                JOptionPane.showMessageDialog(this, "Password salah! Periksa kembali password Anda.", "Verifikasi Gagal", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Proses Top Up jika validasi aman
            TopUpService service = new TopUpService(conn);
            boolean sukses = service.executeTopUp(txtNominal.getText());

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Top Up Berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                java.awt.EventQueue.invokeLater(() -> new MenuPelangganForm().setVisible(true));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Top Up Gagal diproses. Periksa kembali nominal Anda.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

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
        java.awt.EventQueue.invokeLater(() -> new TopUpForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTopUp;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl100;
    private javax.swing.JLabel lbl20;
    private javax.swing.JLabel lbl200;
    private javax.swing.JLabel lbl50;
    private javax.swing.JLabel lbl500;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JPanel panel100;
    private javax.swing.JPanel panel20;
    private javax.swing.JPanel panel200;
    private javax.swing.JPanel panel50;
    private javax.swing.JPanel panel500;
    private javax.swing.JPanel panelBank;
    private javax.swing.JPanel panelEwallet;
    private javax.swing.JPanel panelMinimarket;
    private javax.swing.JCheckBox passwordCheckBox;
    private javax.swing.JRadioButton radioBtnBank;
    private javax.swing.JRadioButton radioBtnEwallet;
    private javax.swing.JRadioButton radioBtnMinimarket;
    private javax.swing.JTextField txtNominal;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
