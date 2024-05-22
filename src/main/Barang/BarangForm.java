/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.Barang;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import utils.CustomButton;
import utils.RoundedPanelBorder;
import utils.RoundedTextField;
import utils.dbConnection;
import java.sql.PreparedStatement;

/**
 *
 * @author hirag
 */
public class BarangForm extends javax.swing.JFrame {
    public static int row;
    private Barang form;
    ArrayList<Integer> id_unit = new ArrayList<>();
    public BarangForm(Barang form) {
        initComponents();
        this.form = form;
        this.dispose();
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(),10, 10));
        loadUnit();
        loadUpdate();
        
    }
    void loadUpdate(){
        if(Barang.Act == 2){
            kodeText.setText(form.ThrowData()[1]);
            namaText.setText(form.ThrowData()[2]);
            hargaText.setText(form.ThrowData()[3]);
            unitText.setSelectedItem(form.ThrowData()[4]);
            id_unit.get(unitText.getSelectedIndex());
        }
    }
     void loadUnit(){
        DefaultComboBoxModel data = new DefaultComboBoxModel<>();
        String query = "select * from unit";
        ResultSet rs = dbConnection.getData(query);
        try{
            while(rs.next()){
                id_unit.add(rs.getInt("id"));
                data.addElement(rs.getString("unit"));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        unitText.setModel(data);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kodeText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namaText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hargaText = new javax.swing.JTextField();
        unitText = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));
        jPanel1 = new RoundedPanelBorder(new Color(238,247,255), new Color(127,39,255), 30, 5);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 39, 255));
        jLabel1.setText("Data Barang");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 39, 255));
        jLabel2.setText("Kode");

        kodeText = new RoundedTextField(10, 15, 2);
        kodeText.setBackground(new java.awt.Color(238, 247, 255));
        kodeText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        kodeText.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 39, 255));
        jLabel3.setText("Nama");

        namaText = new RoundedTextField(10, 15, 2);
        namaText.setBackground(new java.awt.Color(238, 247, 255));
        namaText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        namaText.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 39, 255));
        jLabel4.setText("Harga");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 39, 255));
        jLabel6.setText("Unit");

        hargaText = new RoundedTextField(10, 15, 2);
        hargaText.setBackground(new java.awt.Color(238, 247, 255));
        hargaText.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        hargaText.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8));
        hargaText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaTextKeyTyped(evt);
            }
        });

        jButton1 = new CustomButton("Simpan");
        jButton1.setBackground(new java.awt.Color(127, 39, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(238, 247, 255));
        jButton1.setText("Simpan");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });

        jButton2 = new CustomButton("Login");
        jButton2.setBackground(new java.awt.Color(127, 39, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(238, 247, 255));
        jButton2.setText("Batal");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(kodeText)
                            .addComponent(jLabel4)
                            .addComponent(hargaText, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(303, 303, 303)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(namaText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)
                                .addComponent(unitText, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(185, 185, 185)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(221, 221, 221)
                            .addComponent(jLabel1))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hargaText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitText, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    boolean valIn(){
        try{
            if(kodeText.getText().equals("") || namaText.getText().equals("") || hargaText.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Semua Form Wajib Diisi");
            return false;
            }
            ResultSet kode = dbConnection.getData("select kode from barang where kode='"+ kodeText.getText() +"'");
            if(kode.next()){
                JOptionPane.showMessageDialog(this, "Kode Barang Telah Digunakan");
                return false;
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return true;
    }
    boolean valUp(){
        try{
            if(kodeText.getText().equals("") || namaText.getText().equals("") || hargaText.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Semua Form Wajib Diisi");
            return false;
            }
            if(!form.ThrowData()[1].equals(kodeText.getText())){
                ResultSet kode = dbConnection.getData("select kode from barang where kode='"+ kodeText.getText() +"'");
                if(kode.next()){
                    JOptionPane.showMessageDialog(this, "Kode Barang Telah Digunakan");
                    return false;
                }
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return true;
    }
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(Barang.Act == 1 && valIn()){
            String query = "INSERT INTO barang (kode, nama, id_unit, harga) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
                stmt.setString(1, kodeText.getText());
                stmt.setString(2, namaText.getText());
                stmt.setInt(3, id_unit.get(unitText.getSelectedIndex()));
                stmt.setInt(4, Integer.valueOf(hargaText.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                form.loadTableData();
                this.setVisible(false);
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
        if(Barang.Act == 2 && valUp()){
            String query = "UPDATE barang SET kode = ?, nama = ?, id_unit = ?, harga = ? WHERE id = ?";
            try (PreparedStatement stmt = dbConnection.getConn().prepareStatement(query)) {
                stmt.setString(1, kodeText.getText());
                stmt.setString(2, namaText.getText());
                stmt.setInt(3, id_unit.get(unitText.getSelectedIndex()));
                stmt.setInt(4, Integer.valueOf(hargaText.getText()));
                stmt.setInt(5, Integer.valueOf(form.ThrowData()[0]));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
                form.loadTableData();
                form.ClearSelect(row);
                this.setVisible(false);
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton2MouseExited

    private void hargaTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaTextKeyTyped
        char input = evt.getKeyChar();
        
        if(!Character.isDigit(input)){
            evt.consume();
        }
    }//GEN-LAST:event_hargaTextKeyTyped

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
            java.util.logging.Logger.getLogger(BarangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hargaText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField kodeText;
    private javax.swing.JTextField namaText;
    private javax.swing.JComboBox<String> unitText;
    // End of variables declaration//GEN-END:variables
}
