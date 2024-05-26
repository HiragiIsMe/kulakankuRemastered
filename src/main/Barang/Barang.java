/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main.Barang;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import utils.CustomButton;
import utils.TableActionCellEditor;
import utils.TableActionCellRenderer;
import utils.TableActionEvent;
import utils.dbConnection;

/**
 *
 * @author hirag
 */
public class Barang extends javax.swing.JPanel {
    public static int Act;
    public Barang() {
        initComponents();
        loadTable();
    }
    void loadTable(){
        TableActionEvent evt = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Act = 2;
                BarangForm.row = row;
                DoOutputForm();
            }

            @Override
            public void onDelete(int row) {
                if(MainTable.isEditing()){
                    MainTable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel mdl = (DefaultTableModel) MainTable.getModel();
                int result = JOptionPane.showConfirmDialog(null, "Yakin Ingin Menghapus " + mdl.getValueAt(row, 2) + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    String sql = "DELETE FROM barang WHERE id = ?";
                    try (PreparedStatement stmt = dbConnection.getConn().prepareStatement(sql)) {
                        stmt.setInt(1, Integer.valueOf(mdl.getValueAt(row, 0).toString()));
                        stmt.executeUpdate();
                        loadTableData();
                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                }
            }
        };
        TableColumnModel columnModel = MainTable.getColumnModel();
        columnModel.getColumn(7).setCellRenderer(new TableActionCellRenderer());
        columnModel.getColumn(7).setCellEditor(new TableActionCellEditor(evt)); 
        
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
        
        TableColumn idColumn1 = MainTable.getColumnModel().getColumn(1);
        idColumn1.setMinWidth(0);
        idColumn1.setMaxWidth(0);
        idColumn1.setWidth(0);
        idColumn1.setPreferredWidth(0);
    }
    void loadTableData(){
        DefaultTableModel mdl = (DefaultTableModel) MainTable.getModel();
        mdl.setRowCount(0);
        
        try{
            ResultSet rs = dbConnection.getData("select * from barang join unit on unit.id = barang.id_unit");
            while(rs.next()){
                int id = rs.getInt("id");
                int id_unit =  rs.getInt("id_unit");
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                String harga = rs.getString("harga");
                String unit = rs.getString("unit");
                String sisa_stok = rs.getString("total_stok");
                mdl.addRow(new Object[]{id, id_unit, kode, nama, harga, unit, sisa_stok});
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }public String[] ThrowData(){
        DefaultTableModel mdl = (DefaultTableModel) MainTable.getModel();
        String id = mdl.getValueAt(MainTable.getSelectedRow(), 0).toString();
        String kode = mdl.getValueAt(MainTable.getSelectedRow(), 2).toString();
        String nama = mdl.getValueAt(MainTable.getSelectedRow(), 3).toString();
        String harga = mdl.getValueAt(MainTable.getSelectedRow(), 4).toString();
        String unit = mdl.getValueAt(MainTable.getSelectedRow(), 5).toString();
        String[] data = {id, kode, nama, harga, unit};
        return data;
    }
    public void DoOutputForm(){
        BarangForm form = new BarangForm(this);
        form.setVisible(true);
    }
    void ClearSelect(int row){
        MainTable.setRowSelectionInterval(row, row);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(238, 247, 255));

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

        jPanel2.setBackground(new java.awt.Color(238, 247, 255));

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "id_unit", "Kode", "Nama", "Harga", "Unit", "Total Stok", "Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
        );

        jButton2 = new CustomButton("Tambah Data");
        jButton2.setBackground(new java.awt.Color(127, 39, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(238, 247, 255));
        jButton2.setText("Print Barcode");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Act = 1;
        BarangForm form = new BarangForm(this);
        form.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(144,117,245));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(127,39,255));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println(MainTable.getRowCount());
        try{
            Document document = new Document(PageSize.A4);
            File file = File.createTempFile("pdf", ".pdf");
            FileOutputStream stream = new FileOutputStream(file);
            
            PdfWriter writer = PdfWriter.getInstance(document, stream);
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            document.open();
            
            DefaultTableModel data = (DefaultTableModel) MainTable.getModel();
            for(int i = 0; i < MainTable.getRowCount(); i++){
                String kode = data.getValueAt(i, 2).toString();
                
                Barcode128 barcode = new Barcode128();
                barcode.setCode(kode);
                com.itextpdf.text.Image image = barcode.createImageWithBarcode(writer.getDirectContent(), null, null);
                image.scaleToFit(150, 50);
                
                PdfPCell cell = new PdfPCell();
                cell.setBorder(0);
                cell.setPadding(20);
                cell.addElement(image);

                table.addCell(cell);
                
                if (i == MainTable.getRowCount() - 1 && MainTable.getRowCount() % 2 != 0) {
                    PdfPCell emptyCell = new PdfPCell();
                    emptyCell.setBorder(0); 
                    table.addCell(emptyCell);
                }
            }
            
            document.add(table);
            document.close();
            stream.close();
            Desktop.getDesktop().open(file);
       }catch(Exception e){
           System.out.println(e.toString());
       }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MainTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
