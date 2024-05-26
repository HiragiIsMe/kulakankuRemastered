/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import main.Pegawai.Pegawai;
import Utils.User;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import main.Barang.Barang;
import utils.PanelGradientHorizontal;

/**
 *
 * @author hirag
 */
public class MainAdmin extends javax.swing.JFrame {

    /**
     * Creates new form MainAdmin
     */
    public MainAdmin() {
        initComponents();
        this.dispose();
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        DashClick();
    }
    
    void DashClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/dashboardClicked.png");
        labelDash.setIcon(icon);
        labelHead.setText("Dashboard");
        labelBody.setText("Semua Informasi Umum Ditampilkan Disini");
        
        MainPanel.removeAll();
        Dashboard form = new Dashboard();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }
    void DashDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/dashboard.png");
        labelDash.setIcon(icon);
    }
    void PegClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/employeeClicked.png");
        labelPeg.setIcon(icon);
        labelHead.setText("Pegawai");
        labelBody.setText("Semua Informasi Pegawai Diatur Dan Ditampilkan Disini");
    }
    void PegDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/employee.png");
        labelPeg.setIcon(icon);
    }
    void BarClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/packagingClicked.png");
        labelBar.setIcon(icon);
        labelHead.setText("Barang");
        labelBody.setText("Semua Informasi Barang Diatur Dan Ditampilkan Disini");
    }
    void BarDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/packaging.png");
        labelBar.setIcon(icon);
    }
     void KulakClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/add-to-cartClicked.png");
        labelKulak.setIcon(icon);
        labelHead.setText("Kulakan");
        labelBody.setText("Semua Kegiatan Kulakan Diatur Disini");
    }
    void KulakDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/add-to-cart.png");
        labelKulak.setIcon(icon);
    }
     void ReturClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/return-boxClicked.png");
        labelRetur.setIcon(icon);
        labelHead.setText("Retur Barang");
        labelBody.setText("Semua Kegiatan Retur Barang Diatur Disini");
    }
    void ReturDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/return-box.png");
        labelRetur.setIcon(icon);
    }
     void RiwKulakClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/order-historyClicked.png");
        labelRiwKul.setIcon(icon);
        labelHead.setText("Riwayat Kulakan");
        labelBody.setText("Semua Informasi Riwayat Kulakan Ditampilkan Disini");
    }
    void RiwKulakDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/order-history.png");
        labelRiwKul.setIcon(icon);
    }
     void RiwTranClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/tradeClicked.png");
        labelRiwTran.setIcon(icon);
        labelHead.setText("Riwayat Transaksi");
        labelBody.setText("Semua Informasi Riwayat Transaksi Ditampilkan Disini");
    }
    void RiwTranDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/trade.png");
        labelRiwTran.setIcon(icon);
    }
    void RiwReturClick(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/taxClicked.png");
        labelRiwRetur.setIcon(icon);
        labelHead.setText("Riwayat Retur");
        labelBody.setText("Semua Informasi Riwayat Retur Ditampilkan Disini");
    }
    void RiwReturDefault(){
        ImageIcon icon = new ImageIcon("C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/assets/tax.png");
        labelRiwRetur.setIcon(icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelDash = new javax.swing.JLabel();
        labelPeg = new javax.swing.JLabel();
        labelBar = new javax.swing.JLabel();
        labelKulak = new javax.swing.JLabel();
        labelRiwKul = new javax.swing.JLabel();
        labelRiwTran = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelRiwTran1 = new javax.swing.JLabel();
        labelRetur = new javax.swing.JLabel();
        labelRiwRetur = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelHead = new javax.swing.JLabel();
        labelBody = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(238, 247, 255));

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

        jPanel2 = new utils.RoundedPanelGradient(new Color(127, 39, 255), new Color(28, 22, 120), 20);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-app.png"))); // NOI18N

        labelDash.setBackground(new java.awt.Color(255, 255, 255));
        labelDash.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelDash.setForeground(new java.awt.Color(238, 247, 255));
        labelDash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dashboard.png"))); // NOI18N
        labelDash.setText("  Dashboard");
        labelDash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDashMouseClicked(evt);
            }
        });

        labelPeg.setBackground(new java.awt.Color(255, 255, 255));
        labelPeg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelPeg.setForeground(new java.awt.Color(238, 247, 255));
        labelPeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/employee.png"))); // NOI18N
        labelPeg.setText("  Pegawai");
        labelPeg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPegMouseClicked(evt);
            }
        });

        labelBar.setBackground(new java.awt.Color(255, 255, 255));
        labelBar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelBar.setForeground(new java.awt.Color(238, 247, 255));
        labelBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/packaging.png"))); // NOI18N
        labelBar.setText("  Barang");
        labelBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBarMouseClicked(evt);
            }
        });

        labelKulak.setBackground(new java.awt.Color(255, 255, 255));
        labelKulak.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelKulak.setForeground(new java.awt.Color(238, 247, 255));
        labelKulak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/add-to-cart.png"))); // NOI18N
        labelKulak.setText("  Kulakan");
        labelKulak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelKulakMouseClicked(evt);
            }
        });

        labelRiwKul.setBackground(new java.awt.Color(255, 255, 255));
        labelRiwKul.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRiwKul.setForeground(new java.awt.Color(238, 247, 255));
        labelRiwKul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/order-history.png"))); // NOI18N
        labelRiwKul.setText("  Riwayat Kulakan");
        labelRiwKul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRiwKulMouseClicked(evt);
            }
        });

        labelRiwTran.setBackground(new java.awt.Color(255, 255, 255));
        labelRiwTran.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRiwTran.setForeground(new java.awt.Color(238, 247, 255));
        labelRiwTran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/trade.png"))); // NOI18N
        labelRiwTran.setText("  Riwayat Transaksi");
        labelRiwTran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRiwTranMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/line.png"))); // NOI18N

        labelRiwTran1.setBackground(new java.awt.Color(255, 255, 255));
        labelRiwTran1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRiwTran1.setForeground(new java.awt.Color(238, 247, 255));
        labelRiwTran1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout.png"))); // NOI18N
        labelRiwTran1.setText("  Logout");
        labelRiwTran1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRiwTran1MouseClicked(evt);
            }
        });

        labelRetur.setBackground(new java.awt.Color(255, 255, 255));
        labelRetur.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRetur.setForeground(new java.awt.Color(238, 247, 255));
        labelRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/return-box.png"))); // NOI18N
        labelRetur.setText("  Retur Barang");
        labelRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelReturMouseClicked(evt);
            }
        });

        labelRiwRetur.setBackground(new java.awt.Color(255, 255, 255));
        labelRiwRetur.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelRiwRetur.setForeground(new java.awt.Color(238, 247, 255));
        labelRiwRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tax.png"))); // NOI18N
        labelRiwRetur.setText("  Riwayat Retur");
        labelRiwRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelRiwReturMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRiwRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRiwTran, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelDash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelKulak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelRiwKul, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(labelRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelRiwTran1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(labelDash, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPeg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelBar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelKulak, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRiwKul, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRiwTran, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRiwRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelRiwTran1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel3.setBackground(new java.awt.Color(238, 247, 255));

        labelHead.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        labelHead.setForeground(new java.awt.Color(127, 39, 255));
        labelHead.setText("Dashboard");

        labelBody.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        labelBody.setForeground(new java.awt.Color(127, 39, 255));
        labelBody.setText("Semua Informasi Umum Ditampilkan Disini");

        jPanel4 = new PanelGradientHorizontal(new Color(127, 39, 255), new Color(28, 22, 120));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 247, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bussiness-man.png"))); // NOI18N
        jLabel2.setText(" Admin");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 54, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 54, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBody, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHead, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelHead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBody)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.setBackground(new java.awt.Color(238, 247, 255));
        MainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void labelDashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDashMouseClicked
        DashClick();
        
        PegDefault();
        BarDefault();
        KulakDefault();
        RiwKulakDefault();
        RiwTranDefault();
        ReturDefault();
        RiwReturDefault();
    }//GEN-LAST:event_labelDashMouseClicked

    private void labelPegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPegMouseClicked
        PegClick();
        
        DashDefault();
        BarDefault();
        KulakDefault();
        RiwKulakDefault();
        RiwTranDefault();
        ReturDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        Pegawai form = new Pegawai();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelPegMouseClicked

    private void labelBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBarMouseClicked
        BarClick();

        DashDefault();
        PegDefault();
        KulakDefault();
        RiwKulakDefault();
        RiwTranDefault();
        ReturDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        Barang form = new Barang();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelBarMouseClicked

    private void labelKulakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelKulakMouseClicked
        KulakClick();
        
        DashDefault();
        PegDefault();
        BarDefault();
        RiwKulakDefault();
        RiwTranDefault();
        ReturDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        Kulakan form = new Kulakan();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelKulakMouseClicked

    private void labelRiwKulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRiwKulMouseClicked
        RiwKulakClick();
        
        DashDefault();
        PegDefault();
        BarDefault();
        KulakDefault();
        RiwTranDefault();
        ReturDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        RiwayatKulakan form = new RiwayatKulakan();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelRiwKulMouseClicked

    private void labelRiwTranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRiwTranMouseClicked
        RiwTranClick();
        
        DashDefault();
        PegDefault();
        BarDefault();
        KulakDefault();
        RiwKulakDefault();
        ReturDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        RiwayatTransaksi form = new RiwayatTransaksi();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelRiwTranMouseClicked

    private void labelRiwTran1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRiwTran1MouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            User.setId(0);
            User.setNama("");
            this.dispose();
            MainLogin form = new MainLogin();
            form.show();
        }
    }//GEN-LAST:event_labelRiwTran1MouseClicked

    private void labelReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelReturMouseClicked
        ReturClick();
        
        DashDefault();
        PegDefault();
        BarDefault();
        KulakDefault();
        RiwTranDefault();
        RiwKulakDefault();
        RiwReturDefault();
        
        MainPanel.removeAll();
        Retur form = new Retur();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelReturMouseClicked

    private void labelRiwReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelRiwReturMouseClicked
        RiwReturClick();
        
        DashDefault();
        PegDefault();
        BarDefault();
        KulakDefault();
        RiwTranDefault();
        RiwKulakDefault();
        ReturDefault();
        
        MainPanel.removeAll();
        RiwayatRetur form = new RiwayatRetur();
        MainPanel.add(form);
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_labelRiwReturMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelBar;
    private javax.swing.JLabel labelBody;
    private javax.swing.JLabel labelDash;
    private javax.swing.JLabel labelHead;
    private javax.swing.JLabel labelKulak;
    private javax.swing.JLabel labelPeg;
    private javax.swing.JLabel labelRetur;
    private javax.swing.JLabel labelRiwKul;
    private javax.swing.JLabel labelRiwRetur;
    private javax.swing.JLabel labelRiwTran;
    private javax.swing.JLabel labelRiwTran1;
    // End of variables declaration//GEN-END:variables
}
