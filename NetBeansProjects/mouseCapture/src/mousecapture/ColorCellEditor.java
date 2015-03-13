/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author jonathan
 */

public class ColorCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private Color couleur;
    private JButton bouton;
    private JColorChooser colorChooser;
    private JDialog dialog;
 
    public ColorCellEditor() {
        super();
 
        bouton = new JButton();
        bouton.setActionCommand("change");
        bouton.addActionListener(this);
        bouton.setBorderPainted(false);
 
        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(bouton, "Pick a Color", true, colorChooser, this, null);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("change".equals(e.getActionCommand())) {
            bouton.setBackground(couleur);
            colorChooser.setColor(couleur);
            dialog.setVisible(true);
 
            fireEditingStopped();
        } else {
            couleur = colorChooser.getColor();
        }
    }
 
    @Override
    public Object getCellEditorValue() {
        return couleur;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        couleur = (Color)value;
 
        return bouton;
    }
}

