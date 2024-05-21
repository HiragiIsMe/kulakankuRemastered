/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main.Pegawai;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utils.CustomButton;
import utils.TableActionCellEditor;
import utils.TableActionCellRenderer;
import utils.dbConnection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import utils.TableActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.PasswordHash;
/**
 *
 * @author hirag
 */
public class Pegawai extends javax.swing.JPanel {
    public static int Act;
    public Pegawai() {
        initComponents();
        loadTable();
    }
    void loadTable(){
        TableActionEvent evt = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Act = 2;
                PegawaiForm.row = row;
                DoOutputForm();
            }

            @Override
            public void onDelete(int row) {
                System.out.println("row " + row);
            }
        };
        TableColumnModel columnModel = MainTable.getColumnModel();
        columnModel.getColumn(5).setCellRenderer(new TableActionCellRenderer());
        columnModel.getColumn(5).setCellEditor(new TableActionCellEditor(evt)); 
        
        paintTableHeader();
        hideTableColumn();
        loadTableData();
    }
    void paintTableHeader(){
        DefaultTableCellRenderer headRender = new DefaultTableCellRenderer();
        headRender.setForeground(new Color(238,247,255));
        headRender.setBackground(new Color(127,39,255));
        MainTable.getTableHeader().setDefaultRenderer(headRender);
    }
    void hideTableColumn(){
        TableColumn idColumn = MainTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setWidth(0);
        idColumn.setPreferredWidth(0);
    }
    void loadTableData(){
        DefaultTableModel mdl = (DefaultTableModel) MainTable.getModel();
        mdl.setRowCount(0);
        
        try{
            ResultSet rs = dbConnection.getData("select * from users");
            while(rs.next()){
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String username = rs.getString("username");
                String rfid = rs.getString("rfid_code");
                String role;
                if(rs.getString("role").equals("1")){
                    role = "Admin";
                }else{
                    role = "Kasir";
                }
                mdl.addRow(new Object[]{id, nama, username, rfid, role});
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    public String[] ThrowData(){
        DefaultTableModel mdl = (DefaultTableModel) MainTable.getModel();
        String id = mdl.getValueAt(MainTable.getSelectedRow(), 0).toString();
        String nama = mdl.getValueAt(MainTable.getSelectedRow(), 1).toString();
        String username = mdl.getValueAt(MainTable.getSelectedRow(), 2).toString();
        String rfid = mdl.getValueAt(MainTable.getSelectedRow(), 3).toString();
        String role;
        if(mdl.getValueAt(MainTable.getSelectedRow(), 4).toString().equals("Admin")){
            role = "1";
        }else{
            role = "2";
        }
        String[] data = {id, nama, username, rfid, role};
        return data;
    }
    public void DoOutputForm(){
        PegawaiForm form = new PegawaiForm(this);
        form.setVisible(true);
    }
    void ClearSelect(int row){
        MainTable.clearSelection();
        MainTable.setRowSelectionInterval(row, row);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(238, 247, 255));

        jButton1 = new CustomButton("Tambah Data");
        jButton1.setBackground(new java.awt.Color(127, 39, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(238, 247, 255));
        jButton1.setText("Tambah Data");
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

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Username", "Kode RFID", "Role", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MainTable.setRowHeight(40);
        MainTable.setSelectionBackground(new java.awt.Color(142, 143, 250));
        jScrollPane1.setViewportView(MainTable);
        if (MainTable.getColumnModel().getColumnCount() > 0) {
            MainTable.getColumnModel().getColumn(0).setResizable(false);
            MainTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Act = 1;
        PegawaiForm form = new PegawaiForm(this);
        form.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
