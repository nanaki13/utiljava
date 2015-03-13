/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import com.jonathan.factory.Provider;
import com.jonathan.lib.graphique.GenericListModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author jonathan
 */
class GroupeMouvementCellEditor extends AbstractCellEditor implements TableCellEditor {

    private List<GroupeMouvement> groupeMouvements;
    private JButton jButtonCreer;
    private final JComboBox<GroupeMouvement> jComboBox;
    private AbstractAction creerGroupe;
    private Provider<GroupeMouvement> provider;
    public GroupeMouvementCellEditor(List<GroupeMouvement> groupeMouvements,Provider<GroupeMouvement> provider) {
        super();
        this.groupeMouvements = groupeMouvements;
        jComboBox = new JComboBox<>(new GenericListModel<>(groupeMouvements));
        creerGroupe = new AbstractAction("creer") {     
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = JOptionPane.showInputDialog("nom du groupe : ");
                GroupeMouvement newInstance = provider.provide(groupName);
                if(newInstance!=null){
                     jComboBox.addItem(newInstance);
                }
                GroupeMouvementCellEditor.this.fireEditingStopped();
                
               
            }
        };
        jButtonCreer = new JButton(creerGroupe);
        this.provider = provider;
    }

    @Override
    public Object getCellEditorValue() {
        if(groupeMouvements.isEmpty()){
            return null;
        }
        return jComboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        System.out.println(String.format("%s,%s,%s,%s", value,  isSelected,  row,  column));
        if(groupeMouvements.isEmpty()){
            return jButtonCreer;
        }
        return jComboBox;
    }
    
}
