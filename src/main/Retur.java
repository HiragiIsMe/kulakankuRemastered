/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utils.CustomButton;
import utils.RoundedTextField;
import utils.TableActionCellEditorDelete;
import utils.TableActionCellRendererDelete;
import utils.TableActionEventDelete;
import utils.dbConnection;

/**
 *
 * @author hirag
 */
public class Retur extends javax.swing.JPanel {
    private List<Integer> idKul = new ArrayList<>();
    private List<Integer> idDetKul = new ArrayList<>();
    private List<Integer> idBrg = new ArrayList<>();
    private List<Integer> idKulDipilih = new ArrayList<>();
    private List<Integer> stk_sisa = new ArrayList<>();
    public Retur() {
        initComponents();
        loadTable();
        DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
        tbl.setRowCount(0);
        AutoCompleteDecorator.decorate(kulRet);
        loadKulakan();
        barRet.setSelected(true);
        returPil.setText("Jumlah Barang Diganti");
    }
    void loadTable(){
        TableActionEventDelete evt = new TableActionEventDelete(){
            @Override
            public void onDelete(int row) {
                
                if(MainTable.isEditing()){
                    MainTable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
                idKulDipilih.remove(row);
                tbl.removeRow(row);
            }
            
        };
        TableColumnModel columnModel = MainTable.getColumnModel();
        columnModel.getColumn(8).setCellRenderer(new TableActionCellRendererDelete());
        columnModel.getColumn(8).setCellEditor(new TableActionCellEditorDelete(evt));
        
        paintTableHeader();
        hideTableColumn();
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
        
        TableColumn idColumn1 = MainTable.getColumnModel().getColumn(1);
        idColumn1.setMinWidth(0);
        idColumn1.setMaxWidth(0);
        idColumn1.setWidth(0);
        idColumn1.setPreferredWidth(0);
        
         TableColumn idColumn2 = MainTable.getColumnModel().getColumn(7);
        idColumn2.setMinWidth(0);
        idColumn2.setMaxWidth(0);
        idColumn2.setWidth(0);
        idColumn2.setPreferredWidth(0);
    }
    void loadKulakan(){
        DefaultComboBoxModel mdl = new DefaultComboBoxModel();
        try{
            String query = "select * from kulaan";
            ResultSet rs = dbConnection.getData(query);
            while(rs.next()){
                idKul.add(rs.getInt("id"));
                mdl.addElement(rs.getString("judul_kulakan") + " (" + rs.getString("tanggal") + ")");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
        kulRet.setModel(mdl);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        kulRet = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        barangRet = new javax.swing.JComboBox<>();
        barRet = new javax.swing.JRadioButton();
        uangRet = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        stokRet = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        alasanRet = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        StokUang = new javax.swing.JTextField();
        returPil = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id_detail", "id_barang", "Nama Barang", "Stok Di Retur", "Stok Diganti", "Uang Diganti", "Alasan", "idKul", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
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

        jPanel2.setBackground(new java.awt.Color(238, 247, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 39, 255), 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 39, 255));
        jLabel1.setText("Waktu Kulakan");

        kulRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kulRetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(kulRet, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kulRet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(238, 247, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 39, 255), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 39, 255));
        jLabel2.setText("Barang Di Retur");
        jLabel2.setToolTipText("");

        buttonGroup1.add(barRet);
        barRet.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        barRet.setForeground(new java.awt.Color(127, 39, 255));
        barRet.setText("Retur Barang Diganti");
        barRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barRetActionPerformed(evt);
            }
        });

        buttonGroup1.add(uangRet);
        uangRet.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        uangRet.setForeground(new java.awt.Color(127, 39, 255));
        uangRet.setText("Retur Uang Diganti");
        uangRet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uangRetActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 39, 255));
        jLabel3.setText("Tipe Retur");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 39, 255));
        jLabel4.setText("Jumlah Diretur");
        jLabel4.setToolTipText("");

        stokRet = new RoundedTextField(10, 15, 2);
        stokRet.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        stokRet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stokRetKeyTyped(evt);
            }
        });

        alasanRet.setColumns(20);
        alasanRet.setRows(5);
        jScrollPane2.setViewportView(alasanRet);

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 39, 255));
        jLabel5.setText("Alasan Retur");
        jLabel5.setToolTipText("");

        jButton2 = new CustomButton("Tambah Data");
        jButton2.setBackground(new java.awt.Color(127, 39, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(238, 247, 255));
        jButton2.setText("Tambah");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        StokUang = new RoundedTextField(10, 15, 2);
        StokUang.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        StokUang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                StokUangKeyTyped(evt);
            }
        });

        returPil.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        returPil.setForeground(new java.awt.Color(127, 39, 255));
        returPil.setText("Stok Uang");
        returPil.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(barangRet, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(barRet)
                    .addComponent(uangRet))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stokRet, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StokUang, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(returPil))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(barangRet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stokRet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(returPil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(barRet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uangRet))
                            .addComponent(StokUang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(238, 247, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 39, 255), 2));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 39, 255));
        jLabel6.setText("Catatan");

        catatan.setColumns(20);
        catatan.setRows(5);
        jScrollPane3.setViewportView(catatan);

        jButton3 = new CustomButton("Tambah Data");
        jButton3.setBackground(new java.awt.Color(127, 39, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(238, 247, 255));
        jButton3.setText("Submit");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 166, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
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

    private void stokRetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stokRetKeyTyped
        char input = evt.getKeyChar();

        if(!Character.isDigit(input)){
            evt.consume();
        }
    }//GEN-LAST:event_stokRetKeyTyped

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton2MouseExited
    boolean valAdd(){
        if(stokRet.getText().equals("") || alasanRet.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Semua Form Data Retur Barang Wajib Diisi");
            return false;
        }
        return true;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if(valAdd()){
            if(stk_sisa.get(barangRet.getSelectedIndex()) > Integer.valueOf(stokRet.getText())){
                idKulDipilih.add(idKul.get(kulRet.getSelectedIndex()));
                DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
                int idKulakan = idDetKul.get(barangRet.getSelectedIndex());
                int idBarang = idBrg.get(barangRet.getSelectedIndex());
                String namaBarang = barangRet.getSelectedItem().toString();
                String stokDiretur = stokRet.getText();
                String stkung = StokUang.getText();
                String alasan = alasanRet.getText();

                if(barRet.isSelected()){
                    tbl.addRow(new Object[]{idKulakan, idBarang, namaBarang, stokDiretur, stkung, "0",alasan, idKul.get(kulRet.getSelectedIndex())});
                }else{
                    tbl.addRow(new Object[]{idKulakan, idBarang, namaBarang, stokDiretur, "0",stkung,alasan, idKul.get(kulRet.getSelectedIndex())});
                }
                stokRet.setText("");
                StokUang.setText("");
                alasanRet.setText("");
                if(barRet.isSelected()){
                    barRet.setSelected(false);
                }else{
                    uangRet.setSelected(false);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Barang Kulakan Yang DiRetur Tidak Mencukupi Dengan Sisa Stok Yang Belum Terjual, Sisa Stok:" + stk_sisa.get(barangRet.getSelectedIndex()).toString());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(MainTable.getRowCount() > 0){
                        DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
            try{
                for(int i = 0; i < idKulDipilih.size(); i++){
                LocalDateTime currentDate = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String tgl = currentDate.format(formatter);
                
                String kulIn = "insert into retur_kulaan(id_kulaan,tanggal,catatan) values("+ idKulDipilih.get(i) +",'"+ tgl +"', '"+ alasanRet.getText() +"')";
                Statement stkulIn = dbConnection.getConn().createStatement();
                stkulIn.executeUpdate(kulIn);
                stkulIn.close();
                
                try{
                    String getId = "select id from retur_kulaan order by id desc limit 1";
                    ResultSet rs = dbConnection.getData(getId);
                    rs.next();
                    int idRetKul = rs.getInt("id");
                    
                    for(int j = 0; j < MainTable.getRowCount(); j++){
                        if(tbl.getValueAt(j, 7) == idKulDipilih.get(i)){
                            String detKulIn = "insert into detail_retur_kulaan(id_retur_kulaan, id_barang, qty, stok_diganti, uang_diganti, alasan) values("+ idRetKul +", "+ tbl.getValueAt(j, 1) +", "+ tbl.getValueAt(j, 3) +", '"+ tbl.getValueAt(j, 4)+"', '"+ tbl.getValueAt(j, 5) +"', '"+ tbl.getValueAt(j, 6) +"')";
                            Statement stDetIn = dbConnection.getConn().createStatement();
                            stDetIn.executeUpdate(detKulIn);
                            stDetIn.close();
                            
                            if(tbl.getValueAt(j, 4).toString() == "0"){
                                String QupDetKul = "update detail_kulaan set stokDidapat = stokDidapat - "+ tbl.getValueAt(j, 3)+", stok_tersisa = stok_tersisa - "+ tbl.getValueAt(j, 3)+",hargaKulaan = hargaKulaan + '"+ tbl.getValueAt(j, 5) +"' where id = "+ tbl.getValueAt(j, 0) +"";
                                Statement upDetKul = dbConnection.getConn().createStatement();
                                upDetKul.executeUpdate(QupDetKul);
                                upDetKul.close();
                            }else{
                                String QupDetKul = "update detail_kulaan set stokDidapat = stokDidapat - "+ tbl.getValueAt(j, 3)+", stok_tersisa = stok_tersisa - "+ tbl.getValueAt(j, 3)+" where id = "+ tbl.getValueAt(j, 0) +"";
                                Statement upDetKul = dbConnection.getConn().createStatement();
                                upDetKul.executeUpdate(QupDetKul);
                                upDetKul.close();
                                
                                String QupDetKul1 = "update detail_kulaan set stokDidapat = stokDidapat + " + tbl.getValueAt(j, 4) + ", stok_tersisa = stok_tersisa + "+ tbl.getValueAt(j, 4)+" where id = "+ tbl.getValueAt(j, 0) +"";
                                Statement upDetKul1 = dbConnection.getConn().createStatement();
                                upDetKul1.executeUpdate(QupDetKul1);
                                upDetKul1.close();
                            }
                            
                        }
                    }
                tbl.setRowCount(0);
                idKulDipilih.clear();
                catatan.setText("");
                JOptionPane.showMessageDialog(this, "Retur Barang Berhasil");
                }catch(Exception e){
                    System.out.println(e.toString());
                }
            }
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void StokUangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StokUangKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_StokUangKeyTyped

    private void barRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barRetActionPerformed
        returPil.setText("Jumlah Stok Diganti");
    }//GEN-LAST:event_barRetActionPerformed

    private void uangRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uangRetActionPerformed
        returPil.setText("Jumlah Uang Diganti");
    }//GEN-LAST:event_uangRetActionPerformed

    private void kulRetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kulRetActionPerformed
       idBrg.clear();
       idDetKul.clear();
       stk_sisa.clear();
        DefaultComboBoxModel mdl = new DefaultComboBoxModel();
        try{
            String query = "select * from detail_kulaan join barang on barang.id = detail_kulaan.id_barang where stok_tersisa > 0 and id_kulaan="+ idKul.get(kulRet.getSelectedIndex())+"";
            ResultSet rs = dbConnection.getData(query);
            while(rs.next()){
                idDetKul.add(rs.getInt("id"));
                idBrg.add(rs.getInt("id_barang"));
                stk_sisa.add(rs.getInt("stok_tersisa"));
                mdl.addElement(rs.getString("nama") + " (" + rs.getString("kode") + ")");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
        barangRet.setModel(mdl);
    }//GEN-LAST:event_kulRetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainTable;
    private javax.swing.JTextField StokUang;
    private javax.swing.JTextArea alasanRet;
    private javax.swing.JRadioButton barRet;
    private javax.swing.JComboBox<String> barangRet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea catatan;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> kulRet;
    private javax.swing.JLabel returPil;
    private javax.swing.JTextField stokRet;
    private javax.swing.JRadioButton uangRet;
    // End of variables declaration//GEN-END:variables
}
