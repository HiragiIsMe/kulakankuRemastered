/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import utils.CustomButton;
import utils.RoundedTextField;
import utils.TableActionCellEditor;
import utils.TableActionCellEditorDelete;
import utils.TableActionCellRenderer;
import utils.TableActionCellRendererDelete;
import utils.TableActionEventDelete;
import utils.dbConnection;

/**
 *
 * @author hirag
 */
public class Kulakan extends javax.swing.JPanel {
    
    private List<Integer> idBrg = new ArrayList<>();
    public Kulakan() {
        initComponents();
        
        loadTable();
        loadBarang();
        DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
        tbl.setRowCount(0);
    }
    void loadTable(){
        TableActionEventDelete evt = new TableActionEventDelete(){
            @Override
            public void onDelete(int row) {
                if(MainTable.isEditing()){
                    MainTable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
                tbl.removeRow(row);
            }
            
        };
        TableColumnModel columnModel = MainTable.getColumnModel();
        columnModel.getColumn(5).setCellRenderer(new TableActionCellRendererDelete());
        columnModel.getColumn(5).setCellEditor(new TableActionCellEditorDelete(evt));
        
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        barangKul = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hargaKul = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        expKul = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        stokKul = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        judulKul = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        catatan = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        totalKul = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_barang", "Barang", "Harga ", "Stok Didapat", "Tanggal Kadaluarsa", "Action"
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

        jPanel2.setBackground(new java.awt.Color(238, 247, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(127, 39, 255), 2, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 39, 255));
        jLabel1.setText("Nama Barang");

        barangKul.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 39, 255));
        jLabel2.setText("Stok Didapat");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 39, 255));
        jLabel3.setText("Harga Kulakan");

        hargaKul = new RoundedTextField(10, 15, 2);
        hargaKul.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        hargaKul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaKulKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 39, 255));
        jLabel4.setText("Tanggal Kadaluarsa");

        jButton1 = new CustomButton("Tambah Data");
        jButton1.setBackground(new java.awt.Color(127, 39, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(238, 247, 255));
        jButton1.setText("Tambah");
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

        stokKul = new RoundedTextField(10, 15, 2);
        stokKul.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        stokKul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                stokKulKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(barangKul, 0, 185, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(stokKul))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(hargaKul)
                    .addComponent(jLabel3)
                    .addComponent(expKul, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(barangKul, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hargaKul, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stokKul, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expKul, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(238, 247, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(127, 39, 255), 2));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 39, 255));
        jLabel5.setText("Judul Kulakan");

        judulKul = new RoundedTextField(10, 15, 2);

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 39, 255));
        jLabel6.setText("Catatan");

        catatan.setColumns(20);
        catatan.setRows(5);
        jScrollPane2.setViewportView(catatan);

        jButton1 = new CustomButton("Tambah Data");
        jButton2.setBackground(new java.awt.Color(127, 39, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(238, 247, 255));
        jButton2.setText("Submit");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
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

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 39, 255));
        jLabel7.setText("Total");

        totalKul = new RoundedTextField(10, 15, 2);
        totalKul.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(judulKul, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(totalKul, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(judulKul, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(1, 1, 1)
                                .addComponent(totalKul, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    void loadBarang(){
        DefaultComboBoxModel mdl = new DefaultComboBoxModel();
        try{
            String query = "select * from barang";
            ResultSet rs = dbConnection.getData(query);
            while(rs.next()){
                idBrg.add(rs.getInt("id"));
                mdl.addElement(rs.getString("nama") + " (" + rs.getString("kode") + ")");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
        barangKul.setModel(mdl);
    }
    void Calculate(){
        DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
        int har = 0;
        for(int i = 0; i < tbl.getRowCount(); i++){
            har += Integer.parseInt(tbl.getValueAt(i, 2).toString());
            totalKul.setText(String.valueOf(har));
        }
    }
    
    boolean valAdd(){
        if(hargaKul.getText().equals("") || stokKul.getText().equals("") || expKul.getDate() == null){
            JOptionPane.showMessageDialog(this, "Semua Form Data Barang Kulakan Wajib Diisi");
            return false;
        }
        
        if(Integer.valueOf(stokKul.getText()) < 1 || Integer.valueOf(hargaKul.getText()) < 1){
            JOptionPane.showMessageDialog(this, "Stok Dan Harga Kulakan Tidak Boleh 0");
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tglExp = expKul.getDate();
        Calendar currentDate = Calendar.getInstance();
        Date tglNow = currentDate.getTime();
        if(tglExp.before(tglNow)){
            JOptionPane.showMessageDialog(this, "Tanggal Kadaluarsa Tidak Boleh Kurang Dari Tanggal Saat Ini");
            return false;
        }
        
        DefaultTableModel tbl = (DefaultTableModel) MainTable.getModel();
        for(int i = 0; i < MainTable.getRowCount(); i++){
            if(tbl.getValueAt(i, 0) ==idBrg.get(barangKul.getSelectedIndex())){
                tbl.setValueAt(hargaKul.getText(), i, 2);
                tbl.setValueAt(stokKul.getText(), i, 3);
                SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
                Date tgl = expKul.getDate();
                String ex = sdff.format(tgl);
                tbl.setValueAt(ex, i, 4);
                return false;
            }
        }
        return true;
    }
    void clear(){
        hargaKul.setText("");
        stokKul.setText("");
    }
    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton2MouseExited
    boolean valSubmit(){
        if(judulKul.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Judul Kulakan Tidak Boleh Kosong");
            return false;
        }
        return true;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(MainTable.getRowCount() > 0 && valSubmit()){
           try{
                LocalDateTime currentDate = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String tgl = currentDate.format(formatter);
                String judul = judulKul.getText();
                int tl = Integer.parseInt(totalKul.getText());
                String ct = catatan.getText();
                String kulIn = "insert into kulaan(judul_kulakan,tanggal,total,catatan) values('"+ judul +"','"+ tgl +"', "+ tl +", '"+ ct +"')";
                Statement stkulIn = dbConnection.getConn().createStatement();
                stkulIn.executeUpdate(kulIn);
                stkulIn.close();
                
                try{
                    String getId = "select id from kulaan order by id desc limit 1";
                    ResultSet rs = dbConnection.getData(getId);
                    rs.next();
                    int idKul = rs.getInt("id");

                    DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
                    for(int i = 0; i < MainTable.getRowCount(); i++){
                        String detKulIn = "insert into detail_kulaan(id_barang, hargaKulaan, stokDidapat, stok_tersisa,tgl_exp, id_kulaan) values("+ tbl.getValueAt(i, 0) +", "+ tbl.getValueAt(i, 2) +", "+ tbl.getValueAt(i, 3) +",  "+ tbl.getValueAt(i, 3) +",'"+ tbl.getValueAt(i, 4)+"', "+ idKul +")";
                        Statement stDetIn = dbConnection.getConn().createStatement();
                        stDetIn.executeUpdate(detKulIn);
                        stDetIn.close();

                    }

                    JOptionPane.showMessageDialog(this, "Kulakan Berhasil Didata");
                    tbl.setRowCount(0);
                    judulKul.setText("");
                    totalKul.setText("");
                    catatan.setText("");
                }catch(Exception e){
                    System.out.println(e.toString());
                }
           }catch(Exception e){
               System.out.println(e.toString());
           }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(valAdd()){
            DefaultTableModel tbl = (DefaultTableModel)MainTable.getModel();
            int idBarang = idBrg.get(barangKul.getSelectedIndex());
            String bar = barangKul.getSelectedItem().toString();
            String har = hargaKul.getText();
            String stk = stokKul.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tgl = expKul.getDate();
            String ex = sdf.format(tgl);
            tbl.addRow(new Object[]{idBarang,bar,har,stk,ex});
            clear();
            Calculate();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton1MouseEntered

    private void hargaKulKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaKulKeyTyped
        char input = evt.getKeyChar();

        if(!Character.isDigit(input)){
            evt.consume();
        }
    }//GEN-LAST:event_hargaKulKeyTyped

    private void stokKulKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stokKulKeyTyped
        char input = evt.getKeyChar();

        if(!Character.isDigit(input)){
            evt.consume();
        }
    }//GEN-LAST:event_stokKulKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainTable;
    private javax.swing.JComboBox<String> barangKul;
    private javax.swing.JTextArea catatan;
    private com.toedter.calendar.JDateChooser expKul;
    private javax.swing.JTextField hargaKul;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField judulKul;
    private javax.swing.JTextField stokKul;
    private javax.swing.JTextField totalKul;
    // End of variables declaration//GEN-END:variables
}
