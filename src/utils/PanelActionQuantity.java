/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hirag
 */
public class PanelActionQuantity extends javax.swing.JPanel {

    /**
     * Creates new form PanelActionQuantity1
     */
     public PanelActionQuantity() {
        initComponents();
    }

    public void initEvent(TableActionEventQuantity event, int row) {
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onAdd(row, PanelActionQuantity.this);
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onSubtract(row, PanelActionQuantity.this);
            }
        });
    }

    public void setQuantity(int quantity) {
        lblQuantity.setText(String.valueOf(quantity));
    }

    public int getQuantity() {
        return Integer.parseInt(lblQuantity.getText());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minus = new javax.swing.JButton();
        lblQuantity = new javax.swing.JLabel();
        plus = new javax.swing.JButton();

        minus.setBackground(new java.awt.Color(255, 255, 255));
        minus.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        minus.setForeground(new java.awt.Color(51, 51, 51));
        minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/minus (1).png"))); // NOI18N
        minus.setPreferredSize(new java.awt.Dimension(30, 29));
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minusMouseExited(evt);
            }
        });
        minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minusMouseExited(evt);
            }
        });
        minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusActionPerformed(evt);
            }
        });

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuantity.setText("0");
        lblQuantity.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        plus.setBackground(new java.awt.Color(255, 255, 255));
        plus.setFont(new java.awt.Font("Segoe UI Semibold", 1, 10)); // NOI18N
        plus.setForeground(new java.awt.Color(51, 51, 51));
        plus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/plus.png"))); // NOI18N
        plus.setPreferredSize(new java.awt.Dimension(30, 30));
        plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plusMouseExited(evt);
            }
        });
        plus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                plusMouseExited(evt);
            }
        });
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void minusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusMouseEntered
        minus.setBackground(Color.WHITE);
    }//GEN-LAST:event_minusMouseEntered

    private void minusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusMouseExited
         minus.setBackground(Color.WHITE);
    }//GEN-LAST:event_minusMouseExited

    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_minusActionPerformed

    private void plusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusMouseEntered
        plus.setBackground(Color.WHITE);
    }//GEN-LAST:event_plusMouseEntered

    private void plusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusMouseExited
        plus.setBackground(Color.WHITE);
    }//GEN-LAST:event_plusMouseExited

    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JButton minus;
    private javax.swing.JButton plus;
    // End of variables declaration//GEN-END:variables
}
