/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main.Kasir;


import Utils.User;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.CallableStatement;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.CustomButton;
import utils.PanelActionQuantity;
import utils.QuantityCellEditor;
import utils.QuantityCellRenderer;
import utils.RoundedTextField;
import utils.RoundedPanelGradient;
import utils.TableActionEventQuantity;
import utils.dbConnection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.NomorTransaksi;
import utils.TableActionCellEditorDelete;
import utils.TableActionCellRendererDelete;
import utils.TableActionEventDelete;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
/**
 *
 * @author hirag
 */
public class MainKasir extends javax.swing.JFrame{

    boolean bayar = false;
    public final DefaultTableModel tbl;
    PanelActionQuantity action;

    public MainKasir() {
        initComponents();

        this.tbl = (DefaultTableModel) MainTable.getModel();
        this.dispose();
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        loadTable();
        totalBay.setHorizontalAlignment(SwingConstants.RIGHT);
        totalBel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalKem.setHorizontalAlignment(SwingConstants.RIGHT);
        noTran.setText(NomorTransaksi.generateUniqueTransactionNumber());
        user.setText(User.getNama());
    }

    void loadTable() {
        paintTableHeader();
        hideTableColumn();
        tbl.setRowCount(0);

        TableActionEventQuantity event = new TableActionEventQuantity() {
            @Override
            public void onAdd(int row, PanelActionQuantity actionPanel) {
                action = actionPanel;
                try {
                    PreparedStatement preparedStatement = dbConnection.getConn().prepareStatement("SELECT barang.harga, SUM(detail_kulaan.stok_tersisa) AS total_stok FROM barang JOIN detail_kulaan ON barang.id = detail_kulaan.id_barang WHERE barang.id = ? GROUP BY barang.nama;");
                    preparedStatement.setInt(1, Integer.valueOf(tbl.getValueAt(row, 0).toString()));
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();

                    int quantity = (int) tbl.getValueAt(row, 3);
                    if (rs.getInt("total_stok") > quantity) {
                        tbl.setValueAt(quantity + 1, row, 3);
                        tbl.setValueAt((quantity + 1) * rs.getInt("harga"), row, 4);
                        actionPanel.setQuantity(quantity + 1);
                    } else {
                        JOptionPane.showMessageDialog(actionPanel, "Stok Barang Tidak Memenuhi, Sisa Stok :" + rs.getInt("total_stok"));
                    }
                    Calculate();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            @Override
            public void onSubtract(int row, PanelActionQuantity actionPanel) {
                action = actionPanel;
                try {
                    PreparedStatement preparedStatement = dbConnection.getConn().prepareStatement("SELECT barang.harga FROM barang JOIN detail_kulaan ON barang.id = detail_kulaan.id_barang WHERE barang.id = ? GROUP BY barang.nama;");
                    preparedStatement.setInt(1, Integer.valueOf(tbl.getValueAt(row, 0).toString()));
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    int quantity = (int) tbl.getValueAt(row, 3);
                    if (quantity > 1) {
                        tbl.setValueAt(quantity - 1, row, 3);
                        tbl.setValueAt((quantity - 1) * rs.getInt("harga"), row, 4);
                        actionPanel.setQuantity(quantity - 1);
                    }
                    Calculate();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }

        };
        TableActionEventDelete evt = new TableActionEventDelete() {
            @Override
            public void onDelete(int row) {

                if (MainTable.isEditing()) {
                    MainTable.getCellEditor().stopCellEditing();
                }
                tbl.removeRow(row);
            }

        };

        MainTable.getColumnModel().getColumn(3).setCellRenderer(new QuantityCellRenderer());
        MainTable.getColumnModel().getColumn(3).setCellEditor(new QuantityCellEditor(event));
        MainTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRendererDelete());
        MainTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorDelete(evt));
    }

    void paintTableHeader() {
        DefaultTableCellRenderer headRender = new DefaultTableCellRenderer();
        headRender.setForeground(new Color(238, 247, 255));
        headRender.setBackground(new Color(127, 39, 255));
        MainTable.getTableHeader().setDefaultRenderer(headRender);
    }

    void hideTableColumn() {
        TableColumn idColumn = MainTable.getColumnModel().getColumn(0);
        idColumn.setMinWidth(0);
        idColumn.setMaxWidth(0);
        idColumn.setWidth(0);
        idColumn.setPreferredWidth(0);
    }

    void Calculate() {
        int total = 0;
        int qty = 0;
        for (int i = 0; i < MainTable.getRowCount(); i++) {
            total += Integer.valueOf(tbl.getValueAt(i, 4).toString());
            qty += Integer.valueOf(tbl.getValueAt(i, 3).toString());
        }
        lblQty.setText(String.valueOf(qty));
        lblTotal.setText(String.valueOf(total));
        totalBel.setText(String.valueOf(total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        lbl14 = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        kodeBrg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblQty = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        totalBel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        totalKem = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        totalBay = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        noTran = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        MainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_barang", "Nama Barang", "Harga", "Quantity", "Sub Total", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MainTable.setRowHeight(45);
        MainTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        MainTable.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(MainTable);

        jPanel5 = new utils.PanelGradient(new Color(127, 39, 255), new Color(28, 22, 120));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-app.png"))); // NOI18N

        user.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        user.setForeground(new java.awt.Color(238, 247, 255));
        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bussiness-man.png"))); // NOI18N
        user.setText("Kasir");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(user)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        lbl14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lbl14.setForeground(new java.awt.Color(127, 39, 255));
        lbl14.setText("Total :");
        lbl14.setToolTipText("");

        lblQuantity.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblQuantity.setForeground(new java.awt.Color(51, 51, 51));
        lblQuantity.setText("Quantity : ");
        lblQuantity.setToolTipText("");

        jPanel4 = new RoundedPanelGradient(new Color(199,167,174), new Color(199,167,174), 20);
        jPanel4.setBackground(new java.awt.Color(199, 167, 174));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(16, 16, 16))
        );

        jPanel7 = new RoundedPanelGradient(new Color(255, 255, 255), new Color(255, 255, 255), 20);
        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        kodeBrg = new RoundedTextField(10, 15, 2);
        kodeBrg.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        kodeBrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeBrgActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/barcode-scan (3).png"))); // NOI18N

        jButton2 = new CustomButton("Tambah Data");
        jButton2.setBackground(new java.awt.Color(127, 39, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(238, 247, 255));
        jButton2.setText("Cari Barang Manual");
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(kodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kodeBrg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblTotal.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(127, 39, 255));
        lblTotal.setText("0");
        lblTotal.setToolTipText("");

        lblQty.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblQty.setForeground(new java.awt.Color(51, 51, 51));
        lblQty.setText("0");
        lblQty.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblQuantity)
                                .addGap(2, 2, 2)
                                .addComponent(lblQty))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQuantity)
                            .addComponent(lblQty))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl14)
                            .addComponent(lblTotal)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/exit-full-screen.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 39, 255));
        jLabel8.setText("Pembayaran");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("No Transaksi :");
        jLabel9.setToolTipText("");

        jPanel6 = new RoundedPanelGradient(new Color(204,204,204), new Color(204,204,204), 20);
        jPanel6.setForeground(new java.awt.Color(204, 204, 204));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Total Belanja");

        totalBel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        totalBel.setForeground(new java.awt.Color(51, 51, 51));
        totalBel.setText("0");
        totalBel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Total Kembalian");

        totalKem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        totalKem.setForeground(new java.awt.Color(51, 51, 51));
        totalKem.setText("-");
        totalKem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Total Dibayar");

        totalBay.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        totalBay.setForeground(new java.awt.Color(51, 51, 51));
        totalBay.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalBel, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(totalKem, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(totalBay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(totalBel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(totalKem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(totalBay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jButton3 = new CustomButton("Tambah Data");
        jButton3.setBackground(new java.awt.Color(127, 39, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(238, 247, 255));
        jButton3.setText("2");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
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

        jButton4 = new CustomButton("Tambah Data");
        jButton4.setBackground(new java.awt.Color(127, 39, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(238, 247, 255));
        jButton4.setText("1");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5 = new CustomButton("Tambah Data");
        jButton5.setBackground(new java.awt.Color(127, 39, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(238, 247, 255));
        jButton5.setText("3");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6 = new CustomButton("Tambah Data");
        jButton6.setBackground(new java.awt.Color(127, 39, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(238, 247, 255));
        jButton6.setText("4");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7 = new CustomButton("Tambah Data");
        jButton7.setBackground(new java.awt.Color(127, 39, 255));
        jButton7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(238, 247, 255));
        jButton7.setText("5");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8 = new CustomButton("Tambah Data");
        jButton8.setBackground(new java.awt.Color(127, 39, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(238, 247, 255));
        jButton8.setText("6");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9 = new CustomButton("Tambah Data");
        jButton9.setBackground(new java.awt.Color(127, 39, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(238, 247, 255));
        jButton9.setText("7");
        jButton9.setToolTipText("");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10 = new CustomButton("Tambah Data");
        jButton10.setBackground(new java.awt.Color(127, 39, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(238, 247, 255));
        jButton10.setText("8");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11 = new CustomButton("Tambah Data");
        jButton11.setBackground(new java.awt.Color(127, 39, 255));
        jButton11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(238, 247, 255));
        jButton11.setText("9");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
        });
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12 = new CustomButton("Tambah Data");
        jButton12.setBackground(new java.awt.Color(127, 39, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(238, 247, 255));
        jButton12.setText("0");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13 = new CustomButton("Tambah Data");
        jButton13.setBackground(new java.awt.Color(127, 39, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton13.setForeground(new java.awt.Color(238, 247, 255));
        jButton13.setText("00");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
        });
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton13MouseExited(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14 = new CustomButton("Enter");
        jButton14.setBackground(new java.awt.Color(127, 39, 255));
        jButton14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(238, 247, 255));
        jButton14.setText("Enter");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
        });
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15 = new CustomButton("Tambah Data");
        jButton15.setBackground(new java.awt.Color(255, 102, 102));
        jButton15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(238, 247, 255));
        jButton15.setText("Cancel");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton15MouseExited(evt);
            }
        });
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton15MouseExited(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16 = new CustomButton("Tambah Data");
        jButton16.setBackground(new java.awt.Color(127, 39, 255));
        jButton16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jButton16.setForeground(new java.awt.Color(238, 247, 255));
        jButton16.setText("<");
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton16MouseExited(evt);
            }
        });
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton16MouseExited(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton19 = new CustomButton("Tambah Data");
        jButton19.setBackground(new java.awt.Color(102, 255, 102));
        jButton19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton19.setForeground(new java.awt.Color(51, 51, 51));
        jButton19.setText("Submit Transaksi");
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton19MouseExited(evt);
            }
        });
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton19MouseExited(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        noTran.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        noTran.setForeground(new java.awt.Color(51, 51, 51));
        noTran.setText("0");
        noTran.setToolTipText("");

        jButton20 = new CustomButton("Tambah Data");
        jButton20.setBackground(new java.awt.Color(102, 255, 102));
        jButton20.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(51, 51, 51));
        jButton20.setText("Report Hari Ini");
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton20MouseExited(evt);
            }
        });
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton20MouseExited(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(noTran))
                                    .addComponent(jButton20))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(noTran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
        System.out.println(evt.getKeyChar());
    }//GEN-LAST:event_jPanel3KeyPressed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddBarang form = new AddBarang(this);
        form.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Ingin Menutup Aplikasi?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String a = totalBay.getText();
        String b = jButton3.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        jButton4.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        jButton4.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton4MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String a = totalBay.getText();
        String b = jButton4.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String a = totalBay.getText();
        String b = jButton5.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String a = totalBay.getText();
        String b = jButton6.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String a = totalBay.getText();
        String b = jButton7.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        jButton8.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String a = totalBay.getText();
        String b = jButton8.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        jButton9.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        jButton9.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton9MouseExited

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String a = totalBay.getText();
        String b = jButton9.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        jButton10.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        jButton10.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String a = totalBay.getText();
        String b = jButton10.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
        jButton11.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseExited
        jButton11.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton11MouseExited

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String a = totalBay.getText();
        String b = jButton11.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        jButton12.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        jButton12.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton12MouseExited

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String a = totalBay.getText();
        String b = jButton12.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseEntered
        jButton13.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton13MouseEntered

    private void jButton13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseExited
        jButton13.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton13MouseExited

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String a = totalBay.getText();
        String b = jButton13.getText();
        String c = a + b;
        totalBay.setText(c);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseEntered
        jButton15.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton15MouseEntered

    private void jButton15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseExited
        jButton15.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_jButton15MouseExited

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        bayar = false;
        totalKem.setText("-");
        totalBay.setText("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseEntered
        jButton16.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton16MouseEntered

    private void jButton16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseExited
        jButton16.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton16MouseExited

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        totalBay.setText(remove(totalBay.getText()));
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        jButton19.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        jButton19.setBackground(new Color(102, 255, 102));
    }//GEN-LAST:event_jButton19MouseExited
    public void updateStock(int idBarang, int qty) {
        String sql = "{CALL update_stok_kulaan(?, ?)}";
        try (CallableStatement stmt = dbConnection.getConn().prepareCall(sql)) {
            stmt.setInt(1, idBarang);
            stmt.setInt(2, qty);
            stmt.execute();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
       
                    if (bayar && !totalBel.getText().equals("0")) {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDate.format(formatter);
            String inTran = "insert into transaksi(noTransaksi, id_user, total_item, total_harga, total_dibayar, total_kembalian, tanggal) values('" + noTran.getText() + "'," + User.id + ", " + Integer.parseInt(lblQty.getText()) + ", " + Integer.parseInt(totalBel.getText()) + ", " + Integer.parseInt(totalBay.getText()) + ", " + Integer.parseInt(totalKem.getText()) + ", '" + formattedDateTime + "')";
            try {
                Statement stInTran = dbConnection.getConn().createStatement();
                stInTran.executeUpdate(inTran);
                stInTran.close();

                String getId = "select id from transaksi order by id desc limit 1";
                ResultSet rs = dbConnection.getData(getId);
                rs.next();
                int idKul = rs.getInt("id");

                for (int i = 0; i < MainTable.getRowCount(); i++) {
                    String InDetTran = "insert into detail_transaksi(id_transaksi, id_barang, harga, qty, subtotal) values(" + idKul + ", " + tbl.getValueAt(i, 0) + ", " + tbl.getValueAt(i, 2) + ", " + tbl.getValueAt(i, 3) + ", " + tbl.getValueAt(i, 4) + ")";
                    Statement stInDetTran = dbConnection.getConn().createStatement();
                    stInDetTran.executeUpdate(InDetTran);
                    stInDetTran.close();

                    updateStock(Integer.valueOf(tbl.getValueAt(i, 0).toString()), Integer.valueOf(tbl.getValueAt(i, 3).toString()));
                }
                JOptionPane.showMessageDialog(this, "Transaksi Berhasil");
                GenerateStruk();
                totalBel.setText("0");
                totalBay.setText("");
                totalKem.setText("-");
                noTran.setText(NomorTransaksi.generateUniqueTransactionNumber());
                bayar = false;
                tbl.setRowCount(0);
                lblQty.setText("0");
                lblTotal.setText("0");
                action.setQuantity(1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan Selesaikan Proses Pembayaran Terlebih Dahulu");
        } 
    }//GEN-LAST:event_jButton19ActionPerformed
    void GenerateReport() {
        try {
            String sql = "SELECT t.id, t.id_user, u.nama as 'kasir', t.noTransaksi, b.nama as 'barang', dt.qty, dt.subtotal FROM transaksi t JOIN users u on t.id_user = u.id JOIN detail_transaksi dt ON t.id = dt.id_transaksi JOIN barang b ON dt.id_barang = b.id";

            Statement statement = dbConnection.getConn().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sales Report");

            int rowCount = 0;

            Row headerRow = sheet.createRow(rowCount++);
            headerRow.createCell(0).setCellValue("Nomor Transaksi");
            headerRow.createCell(1).setCellValue("Nama Kasir");
            headerRow.createCell(2).setCellValue("Nama Barang");
            headerRow.createCell(3).setCellValue("Quantity");
            headerRow.createCell(4).setCellValue("SubTotal");

            Map<String, Double> dailyTotalMap = new HashMap<>();

            while (resultSet.next()) {
                String nomorNota = resultSet.getString("noTransaksi");
                String namaKasir = resultSet.getString("kasir");
                String namaBarang = resultSet.getString("barang");
                double qty = resultSet.getDouble("qty");
                double subtotal = resultSet.getDouble("subtotal");

                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(nomorNota);
                row.createCell(1).setCellValue(namaKasir);
                row.createCell(2).setCellValue(namaBarang);
                row.createCell(3).setCellValue(qty);
                row.createCell(4).setCellValue(subtotal);

                dailyTotalMap.put("Total", dailyTotalMap.getOrDefault("Total", 0.0) + subtotal);
            }

            // Add total row for each day
            for (Map.Entry<String, Double> entry : dailyTotalMap.entrySet()) {
                Row totalRow = sheet.createRow(rowCount++);
                totalRow.createCell(3).setCellValue("Total ");
                totalRow.createCell(4).setCellValue(entry.getValue());
            }
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            String lokasi = "C:/Users/hirag/Documents/NetBeansProjects/KulaankuRemastered/src/Report/" + formattedDate + ".xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(lokasi)) {
                workbook.write(outputStream);
            }

            workbook.close();
            statement.close();

            System.out.println("Report generated successfully!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (!totalBay.getText().equals("")) {
            if (Integer.valueOf(totalBel.getText()) < Integer.valueOf(totalBay.getText())) {
                int kembalian = Integer.valueOf(totalBay.getText()) - Integer.valueOf(totalBel.getText());
                totalKem.setText(String.valueOf(kembalian));
                bayar = true;
            } else {
                JOptionPane.showMessageDialog(this, "Uang Yang Bibayarkan Tidak Mencukupi");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan Input Uang Tuk Dibayar");
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseExited
        jButton14.setBackground(new Color(127, 39, 255));
    }//GEN-LAST:event_jButton14MouseExited

    private void jButton14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseEntered
        jButton14.setBackground(new Color(144, 117, 245));
    }//GEN-LAST:event_jButton14MouseEntered
    public String remove(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    boolean getBarang() {
        try {
            PreparedStatement preparedStatement = dbConnection.getConn().prepareStatement("SELECT barang.id, barang.nama, barang.harga, SUM(detail_kulaan.stok_tersisa) AS total_stok FROM barang JOIN detail_kulaan ON barang.id = detail_kulaan.id_barang WHERE barang.kode = ? GROUP BY barang.nama;");
            preparedStatement.setString(1, kodeBrg.getText());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("total_stok");
                if (stok > 0) {
                    for (int i = 0; i < MainTable.getRowCount(); i++) {
                        if (Integer.valueOf(tbl.getValueAt(i, 0).toString()) == id) {
                            if (Integer.valueOf(tbl.getValueAt(i, 3).toString()) < stok) {
                                tbl.setValueAt(Integer.valueOf(tbl.getValueAt(i, 3).toString()) + 1, i, 3);
                                tbl.setValueAt(Integer.valueOf(tbl.getValueAt(i, 3).toString()) * harga, i, 4);
                                return false;
                            } else {
                                JOptionPane.showMessageDialog(this, "Stok Barang Tidak Memenuhi, Sisa Stok :" + stok);
                                return false;
                            }
                        }
                    }
                    tbl.addRow(new Object[]{id, nama, harga, 1, harga});
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Stok Barang Ini Telah Habis");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Kode Barang Tidak Ditemukan");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
        return true;
    }
    private void kodeBrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeBrgActionPerformed
        getBarang();
        kodeBrg.setText("");
        Calculate();
    }//GEN-LAST:event_kodeBrgActionPerformed
//    void GenerateStruk() {
//
//        float widthInMM = 58f;
//        float heightInMM = 210f;
//        float widthInPoints = widthInMM * 2.83465f;
//        float heightInPoints = heightInMM * 2.83465f;
//
//        Document document = new Document(new com.itextpdf.text.Rectangle(widthInPoints, heightInPoints));
//
//        try {
//            File tempFile = File.createTempFile("pdf", ".pdf");
//            FileOutputStream fos = new FileOutputStream(tempFile);
//
//            PdfWriter writer = PdfWriter.getInstance(document, fos);
//            document.open();
//            writer.setCompressionLevel(0);
//
//            com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);
//            com.itextpdf.text.Font monospacedFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
//
//            Paragraph title = new Paragraph("Kulaanku", boldFont);
//            title.setAlignment(Element.ALIGN_CENTER);// Menghilangkan spasi setelah judul
//            document.add(title);
//
//            Paragraph alamat = new Paragraph("Jl.Dimana Mana Nomor Random \n Jember, Jawa Timur", monospacedFont);
//            alamat.setAlignment(Element.ALIGN_CENTER);
//            document.add(alamat);
//
//            Paragraph garis1 = new Paragraph("============================", monospacedFont);
//            document.add(garis1);
//
//            Paragraph trans = new Paragraph("Nomor Transaksi : " + noTran.getText(), monospacedFont);
//            trans.setAlignment(Element.ALIGN_LEFT);
//            document.add(trans);
//
//            Paragraph kasir = new Paragraph("Kasir : " + User.getNama(), monospacedFont);
//            kasir.setAlignment(Element.ALIGN_LEFT);
//            document.add(kasir);
//
//            LocalDateTime currentDate = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            String formattedDateTime = currentDate.format(formatter);
//
//            Paragraph tgl = new Paragraph("Tanggal : " + formattedDateTime, monospacedFont);
//            tgl.setAlignment(Element.ALIGN_LEFT);
//            document.add(tgl);
//
//            Paragraph garis2 = new Paragraph("============================", monospacedFont);
//            document.add(garis2);
//
//            DefaultTableModel tbl = (DefaultTableModel) MainTable.getModel();
//            for (int i = 0; i < MainTable.getRowCount(); i++) {
//                Paragraph item = new Paragraph(tbl.getValueAt(i, 0).toString(), monospacedFont);
//                item.setSpacingAfter(0);
//                document.add(item);
//
//                Paragraph item1 = new Paragraph(tbl.getValueAt(i, 2).toString() + "     x     " + tbl.getValueAt(i, 1).toString() + "               " + tbl.getValueAt(i, 3).toString(), monospacedFont);
//                item1.setSpacingAfter(0);
//                item1.setAlignment(Element.ALIGN_RIGHT);
//                document.add(item1);
//
//            }
//
//            Paragraph garis3 = new Paragraph("============================", monospacedFont);
//            document.add(garis3);
//
//            Paragraph total = new Paragraph("Total: Rp " + totalBel.getText(), monospacedFont);
//            total.setAlignment(Element.ALIGN_RIGHT);
//            total.setSpacingAfter(0);
//            document.add(total);
//
//            Paragraph bayar = new Paragraph("Dibayar: Rp " + totalBay.getText(), monospacedFont);
//            bayar.setAlignment(Element.ALIGN_RIGHT);
//            bayar.setSpacingAfter(0);
//            document.add(bayar);
//
//            Paragraph kembalian = new Paragraph("Dibayar: Rp " + totalKem.getText(), monospacedFont);
//            kembalian.setAlignment(Element.ALIGN_RIGHT);
//            kembalian.setSpacingAfter(0);
//            document.add(kembalian);
//
//            Paragraph garis4 = new Paragraph("============================", monospacedFont);
//            document.add(garis4);
//
//            Paragraph footer = new Paragraph("TERIMA KASIH", monospacedFont);
//            footer.setAlignment(Element.ALIGN_CENTER);
//            document.add(footer);
//
//            document.close();
//            fos.close();
//            Desktop.getDesktop().open(tempFile);
//            //ThermalPrinter.printReceipt(tempFile);
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        }
//    }
    void GenerateStruk() throws PrinterException {
        float widthInMM = 58f;
        float heightInMM = 210f;
        float widthInPoints = widthInMM * 2.83465f;
        float heightInPoints = heightInMM * 2.83465f;

        Document document = new Document(new com.itextpdf.text.Rectangle(widthInPoints, heightInPoints), 10f, 10f, 10f, 10f);

        try {
            File tempFile = File.createTempFile("pdf", ".pdf");
            FileOutputStream fos = new FileOutputStream(tempFile);

            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            writer.setCompressionLevel(0);

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font monospacedFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, com.itextpdf.text.BaseColor.BLACK);

            // Add title "Kulaanku" centered
            Paragraph title = new Paragraph("Kulaanku", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(5);
            document.add(title);

            // Add address centered
            Paragraph alamat = new Paragraph("Jl.Dimana Mana Nomor Random \n Jember, Jawa Timur", monospacedFont);
            alamat.setAlignment(Element.ALIGN_CENTER);
            alamat.setSpacingAfter(5);
            document.add(alamat);

            // Add horizontal line
            Paragraph garis1 = new Paragraph("=========================", monospacedFont);
            garis1.setAlignment(Element.ALIGN_CENTER);
            garis1.setSpacingAfter(5);
            document.add(garis1);

            // Add transaction number aligned to the left
            Paragraph trans = new Paragraph("Nomor Transaksi: " + noTran.getText(), monospacedFont);
            trans.setAlignment(Element.ALIGN_LEFT);
            trans.setSpacingAfter(5);
            document.add(trans);

            // Add cashier name aligned to the left
            Paragraph kasir = new Paragraph("Kasir: " + User.getNama(), monospacedFont);
            kasir.setAlignment(Element.ALIGN_LEFT);
            kasir.setSpacingAfter(5);
            document.add(kasir);

            // Get current date and time
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDateTime = currentDate.format(formatter);

            // Add date aligned to the left
            Paragraph tgl = new Paragraph("Tanggal: " + formattedDateTime, monospacedFont);
            tgl.setAlignment(Element.ALIGN_LEFT);
            tgl.setSpacingAfter(5);
            document.add(tgl);

            // Add another horizontal line
            Paragraph garis2 = new Paragraph("=========================", monospacedFont);
            garis2.setAlignment(Element.ALIGN_CENTER);
            garis2.setSpacingAfter(5);
            document.add(garis2);

             for (int i = 0; i < MainTable.getRowCount(); i++) {
                Paragraph item1 = new Paragraph(tbl.getValueAt(i, 1).toString() + "   x   " + tbl.getValueAt(i, 3).toString() + "          " + tbl.getValueAt(i, 2).toString(), monospacedFont);
                item1.setAlignment(Element.ALIGN_RIGHT);
                item1.setSpacingAfter(0);
                document.add(item1);
            }

            // Add another horizontal line
            Paragraph garis3 = new Paragraph("=========================", monospacedFont);
            garis3.setAlignment(Element.ALIGN_CENTER);
            garis3.setSpacingAfter(5);
            document.add(garis3);

            // Add total amount aligned to the right
            Paragraph total = new Paragraph("Total: Rp " + totalBel.getText(), monospacedFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingAfter(5);
            document.add(total);

            // Add paid amount aligned to the right
            Paragraph bayar = new Paragraph("Dibayar: Rp " + totalKem.getText(), monospacedFont);
            bayar.setAlignment(Element.ALIGN_RIGHT);
            bayar.setSpacingAfter(5);
            document.add(bayar);

            // Add change amount aligned to the right
            Paragraph kembalian = new Paragraph("Kembalian: Rp " + totalBay.getText(), monospacedFont);
            kembalian.setAlignment(Element.ALIGN_RIGHT);
            kembalian.setSpacingAfter(5);
            document.add(kembalian);

            // Add another horizontal line
            Paragraph garis4 = new Paragraph("=========================", monospacedFont);
            garis4.setAlignment(Element.ALIGN_CENTER);
            garis4.setSpacingAfter(5);
            document.add(garis4);

            // Add "TERIMA KASIH" centered
            Paragraph footer = new Paragraph("TERIMA KASIH", monospacedFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingAfter(5);
            document.add(footer);

            document.close();
            fos.close();

            // Print the PDF
            ThermalPrinter.printReceipt(tempFile);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
       if(MainTable.getRowCount() > 0){
            int result = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Semua Baris Barang?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                tbl.setRowCount(0);
                lblQty.setText("0");
                lblTotal.setText("0");
            }else{
                JOptionPane.showMessageDialog(this, "Tidak Ada Data Untuk Dihapus");
            } 
       }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseEntered
        jButton20.setBackground(new Color(204, 255, 204));
    }//GEN-LAST:event_jButton20MouseEntered

    private void jButton20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20MouseExited

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        GenerateReport();
    }//GEN-LAST:event_jButton20ActionPerformed

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
            java.util.logging.Logger.getLogger(MainKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable MainTable;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kodeBrg;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lblQty;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel noTran;
    private javax.swing.JLabel totalBay;
    private javax.swing.JLabel totalBel;
    private javax.swing.JLabel totalKem;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
