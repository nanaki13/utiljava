/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jonathan
 */
public class GroupCellRenderer extends DefaultTableCellRenderer {
    private List<GroupeMouvement> l ;
    public GroupCellRenderer(List<GroupeMouvement> l) {
        this.l = l;
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        GroupeMouvement groupeMouvement = (GroupeMouvement) value;
        setText(groupeMouvement.getName());
      
 
        return this;
    }
}

