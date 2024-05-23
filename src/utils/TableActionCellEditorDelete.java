/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import main.Pegawai.Pegawai;

/**
 *
 * @author hirag
 */
public class TableActionCellEditorDelete extends AbstractCellEditor implements TableCellEditor{
    
   private TableActionEventDelete event;
   
   public TableActionCellEditorDelete(TableActionEventDelete event){
       this.event = event;
   }
    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionDelete action = new PanelActionDelete();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
}
