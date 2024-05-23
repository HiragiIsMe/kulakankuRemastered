/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import utils.CustomButton;
import utils.dbConnection;

/**
 *
 * @author hirag
 */
public class RiwayatRetur extends javax.swing.JPanel {

    private List<Integer> id = new ArrayList<>();
    public RiwayatRetur() {
        initComponents();
        paintTableHeader();
        loadTableMain("select retur_kulaan.*, kulaan.judul_kulakan, kulaan.tanggal as 'tgl' from retur_kulaan join kulaan on kulaan.id = retur_kulaan.id_kulaan");
    }
    void paintTableHeader(){
        DefaultTableCellRenderer headRender = new DefaultTableCellRenderer();
        headRender.setForeground(new Color(238,247,255));
        headRender.setBackground(new Color(127,39,255));
        MainTable.getTableHeader().setDefaultRenderer(headRender);
        MainSubTable.getTableHeader().setDefaultRenderer(headRender);
    }
    void loadTableMain(String query){
        DefaultTableModel tblMain = new DefaultTableModel();
        tblMain.addColumn("Nama Kulaan");
        tblMain.addColumn("Tanggal;");
        tblMain.addColumn("Catatan");
        
        try{
            ResultSet rs = dbConnection.getData(query);
            id.clear();
            while(rs.next()){
                id.add(rs.getInt("id"));
                tblMain.addRow(new Object[]{String.valueOf(rs.getString("judul_kulakan") + "(" + rs.getDate("tgl") +")"), String.valueOf(rs.getDate("tanggal")), rs.getString("catatan")});
            }
            MainTable.setModel(tblMain);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    void loadSubTable(){
        DefaultTableModel tblSub = new DefaultTableModel();
        tblSub.addColumn("Nama Barang");
        tblSub.addColumn("Stok Diretur");
        tblSub.addColumn("Stok Diganti");
        tblSub.addColumn("Uang Diganati");
        tblSub.addColumn("Alasan");
        
        String query = "select Barang.nama, detail_retur_kulaan.* from detail_retur_kulaan join barang on detail_retur_kulaan.id_barang = barang.id where id_retur_kulaan = "+ id.get(MainTable.getSelectedRow()) +"";
        try{
            ResultSet rs = dbConnection.getData(query);
            while(rs.next()){
                tblSub.addRow(new Object[]{rs.getString("nama"), String.valueOf(rs.getInt("qty")), String.valueOf(rs.getInt("stok_diganti")), String.valueOf(rs.getInt("uang_diganti")), rs.getString("alasan")});
            }
            MainSubTable.setModel(tblSub);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainSubTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        filter = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        labelHead = new javax.swing.JLabel();
        labelHead1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

        MainSubTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MainSubTable.setRowHeight(40);
        jScrollPane1.setViewportView(MainSubTable);

        jButton1 = new CustomButton("Tambah Data");
        jButton1.setBackground(new java.awt.Color(127, 39, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(238, 247, 255));
        jButton1.setText("Filter");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MainTable.setRowHeight(40);
        MainTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MainTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(MainTable);

        labelHead.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelHead.setForeground(new java.awt.Color(127, 39, 255));
        labelHead.setText("Retur");

        labelHead1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        labelHead1.setForeground(new java.awt.Color(127, 39, 255));
        labelHead1.setText("Detail Retur");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelHead, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelHead1)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(18, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(labelHead)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(labelHead1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(277, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tgl = filter.getDate();
        String ex = sdf.format(tgl);
        loadTableMain("select retur_kulaan.*, kulaan.judul_kulakan, kulaan.tanggal as 'tgl' from retur_kulaan join kulaan on kulaan.id = retur_kulaan.id_kulaan where retur_kulaan.tanggal='"+ ex +"'");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MainTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MainTableMouseClicked
        if(MainTable.getSelectedRow() >= 0){
            loadSubTable();
        }
    }//GEN-LAST:event_MainTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainSubTable;
    private javax.swing.JTable MainTable;
    private com.toedter.calendar.JDateChooser filter;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelHead;
    private javax.swing.JLabel labelHead1;
    // End of variables declaration//GEN-END:variables
}
