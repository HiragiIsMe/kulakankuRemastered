package utils;


import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hirag
 */
public class QuantityCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        PanelActionQuantity action = new PanelActionQuantity();
        action.setQuantity((Integer) value);
        if (isSelected) {
            action.setBackground(table.getSelectionBackground());
        } else {
            action.setBackground(row % 2 == 0 ? Color.WHITE : table.getBackground());
        }
        return action;
    }
}
