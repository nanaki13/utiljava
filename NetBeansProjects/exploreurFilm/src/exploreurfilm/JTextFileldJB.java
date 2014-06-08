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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author jonathan
 * @param <T>
 */
public class JTextFileldJB<T> extends JComboBox<T> implements KeyListener, MouseListener, ActionListener {
    
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(jp);
        JTextFileldJB<String> jt = new JTextFileldJB();
        jp.add(jt);
        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setVisible(true);
        
    }
    private Collection<T> objects;
    private ChooserText<T> chooser;
    private Comparator<T> comparator;
    private StringMaker<T> stringMaker;
    private JComboBox<T> jComboBox = new JComboBox<T>();
    private T selected;
//    private JPopupMenu jPopupMenu;
    private boolean noWait = true;
    private final Object lock = new Object();
    
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    public JTextFileldJB(Collection<T> objects, ComboBoxModel<T> aModel) {
        super(aModel);
        this.objects = objects;
        init();
    }
    
    public JTextFileldJB(Collection<T> objects) {
        super();
        this.objects = objects;
        this.
                init();
    }
    
    public JTextFileldJB() {
        super();
        init();
    }
    
    private void init() {
        setEditable(true);
        for (Component child : getComponents()) {
            if (child instanceof JButton) {
                this.remove(child);
//                    child.setVisible(false);
                break;
            }
        }
        revalidate();
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
