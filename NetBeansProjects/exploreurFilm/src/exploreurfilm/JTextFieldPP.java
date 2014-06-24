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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

/**
 *
 * @author jonathan
 * @param <T>
 */
public class JTextFieldPP<T> extends JTextField implements KeyListener, MouseListener, ActionListener {

    private final Collection<T> objects;
    private final ChooserText<T> chooser;
    private Comparator<T> comparator;
    private StringMaker<T> stringMaker;
    private String lastEntry = null;
    private Collection<T> lastObjects;
    private JScrollPane jScrollPane = new JScrollPane();
    private T selected;
    private JPopupMenu jPopupMenu;
    private boolean noWait = true;
    private final JList<T> jl = new JList<T>();
    private final Object lock = new Object();

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
        if (this.isFocusOwner()) {
            if (noWait) {
                noWait = false;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (lock) {
                            try {
                                lock.wait(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JTextFieldPP.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            noWait = true;
                            String text = getText();

                            chooser.setText(text);
                            Collection<T> forChoice;
                            if (lastEntry != null && !lastEntry.isEmpty() && text.contains(lastEntry)) {
                                forChoice = lastObjects;
                                System.out.println("sur les derniers");
                            } else {
                                System.out.println("sur tout");
                                forChoice = objects;
                            }
                            lastEntry = text;
                            List<T> choosen = Chooser.chooseAndOrder(forChoice, chooser, comparator);
                            lastObjects = choosen;
//                            jPopupMenu.removeAll();

                            final Vector<T> vDeT = new Vector<>(choosen);
//                                final JList<T> jl = new JList<>(vDeT);
                            AbstractListModel<T> defaultListModel = new AbstractListModel<T>() {

                                @Override
                                public int getSize() {
                                    return vDeT.size();
                                }

                                @Override
                                public T getElementAt(int index) {
                                    return vDeT.elementAt(index);
                                }
                            };
                            jl.setModel(defaultListModel);

                            ListCellRenderer<T> listCellRenderer = new ListCellRenderer<>(stringMaker);
                            jl.setCellRenderer(listCellRenderer);
                            jl.addMouseMotionListener(listCellRenderer.getHandler(jl));
                            jl.addListSelectionListener(new ListSelectionListener() {

                                @Override
                                public void valueChanged(ListSelectionEvent e) {
                                    JTextFieldPP.this.setText(stringMaker.buildString(jl.getSelectedValue()));
                                    jPopupMenu.setVisible(false);
                                }
                            });
                            if (/*vDeT.size()<10*/false) {
                                jPopupMenu.add(jl);
                            } else {

//                                    jScrollPane = new JScrollPane(jl);
//                                    jl.validate();
                                System.out.println(jl.getSize());
                                System.out.println(jl.getPreferredSize());
                                int height = jl.getPreferredSize().height;
                                Dimension dScrollPane;
                                if (height <= 100) {
                                    dScrollPane = new Dimension(jl.getPreferredSize().width + 20, jl.getPreferredSize().height + 5);
                                } else {
                                    dScrollPane = new Dimension(jl.getPreferredSize().width + 20, 100);
                                }

                                jScrollPane.setPreferredSize(dScrollPane);
                                jPopupMenu.add(jScrollPane);
                            }

//                            }
                            if (!choosen.isEmpty()) {
                                jPopupMenu.pack();
//                            jPopupMenu.revalidate();
                                jPopupMenu.repaint();
                                jPopupMenu.show(JTextFieldPP.this, 0, getSize().height);

                            } else {
                                jPopupMenu.setVisible(false);
                            }

                        }
                    }
                });
                thread.start();

            } else {

            }

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
//        jPopupMenu.grabFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void init() {
        addKeyListener(this);
        jPopupMenu = new JPopupMenu();
        jScrollPane.setViewportView(jl);
        jPopupMenu.add(jScrollPane);
        jPopupMenu.setFocusable(false);
//        jl.setFocusable(false);
//        jScrollPane.setFocusable(false);
        this.setComponentPopupMenu(jPopupMenu);
        jPopupMenu.addMouseListener(this);
//        this.add(jComboBox);

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
