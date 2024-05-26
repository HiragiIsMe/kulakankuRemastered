package utils;


import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hirag
 */
public class QuantityCellEditor extends AbstractCellEditor implements TableCellEditor{
    private PanelActionQuantity action;
    private TableActionEventQuantity event;

    public QuantityCellEditor(TableActionEventQuantity event) {
        this.event = event;
    }

    public Object getCellEditorValue() {
        return action.getQuantity();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        action = new PanelActionQuantity();
        action.initEvent(event, row);
        action.setQuantity((Integer) value);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
}
