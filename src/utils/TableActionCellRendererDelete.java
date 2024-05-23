/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import main.Pegawai.Pegawai;

/**
 *
 * @author hirag
 */
public class TableActionCellRendererDelete extends DefaultTableCellRenderer {
    
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean bin, boolean bin1, int row, int column){
        Component com = super.getTableCellRendererComponent(jTable, o, bin1, bin1, row, column);
        PanelActionDelete action = new PanelActionDelete();
        if(bin == false && row % 2 == 0){
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }
}
