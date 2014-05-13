/*
 * Copyright (C) 2014 jonathan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package exploreurfilm;

import com.jonathan.lib.collections.Chooser;
import com.jonathan.lib.collections.ChooserText;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SingleSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.Document;

/**
 *
 * @author jonathan
 */
public class JTextFieldPP<T> extends JTextField implements KeyListener, MouseListener, ActionListener{
     private final Collection<T> objects;  
     private ChooserText<T> chooser;
     private Comparator<T> comparator;
     private StringMaker<T> stringMaker;
     
     private T selected;
    private JPopupMenu jPopupMenu;

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    

    public JTextFieldPP(Collection<T> objects, ChooserText<T> chooser) {
        this.objects = objects;
        this.chooser = chooser;
        init();
    }

    public JTextFieldPP(Collection<T> objects, ChooserText<T> chooser, String text) {
        super(text);
        this.objects = objects;
        this.chooser = chooser;
        init();
        
    }

    public JTextFieldPP(Collection<T> objects, ChooserText<T> chooser, int columns) {
        super(columns);
        this.objects = objects;
        this.chooser = chooser;
        init();
    }

    public JTextFieldPP(Collection<T> objects, ChooserText<T> chooser, String text, int columns) {
        super(text, columns);
        this.objects = objects;
        this.chooser = chooser;
        init();
    }

    public JTextFieldPP(Collection<T> objects, ChooserText<T> chooser, Document doc, String text, int columns) {
        super(doc, text, columns);
        this.objects = objects;
        this.chooser = chooser;
        init();
    }
     
     

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(this.isFocusOwner()){
            String text = this.getText();
            chooser.setText(text);
            List<T> choosen = Chooser.chooseAndOrder(objects, chooser, comparator);

            jPopupMenu.removeAll();


            for(T t : choosen){
                JMenuItem jMenuItem;
                jMenuItem = new JMenuItemObject<T>(t, stringMaker);
                
                jMenuItem.addActionListener(this);
                jPopupMenu.add(jMenuItem);
            }
            if(!choosen.isEmpty()){
                jPopupMenu.show(this, 0, this.getSize().height);
                this.grabFocus();
            }             
            else{
                jPopupMenu.setVisible(false);
            }
            jPopupMenu.pack();
        }
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        jPopupMenu.grabFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    private void init() {
        addKeyListener(this);
        jPopupMenu = new JPopupMenu();
        this.setComponentPopupMenu(jPopupMenu);
        jPopupMenu.addMouseListener(this);
       
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItemObject<T> jMenuItemObject = (JMenuItemObject<T>) e.getSource();
        this.setText((jMenuItemObject).getText());
        selected = jMenuItemObject.getObj();
    }

    public T getSelected() {
        return selected;
    }

    public void setStringMaker(StringMaker<T> stringMaker) {
        this.stringMaker = stringMaker;
    }
    
    
    
    
     
     
}
