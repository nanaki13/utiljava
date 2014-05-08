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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lib.string.StringTool;

/**
 *
 * @author jonathan
 */
public class ExploreurFilm {

    public static void main(String[] args) {
        ControleurInterface c = new Controleur();
        ExploreurFilm exploreurFilm = new ExploreurFilm(c);
    }
    private final JFrame jf;
    private final JMenuBar menuBar;
    private final JPanel vueG;
    private final JScrollPane scrolPaneG;
    private final JPanel vueD;
    public ExploreurFilm(final ControleurInterface c) {
        jf = new JFrame("test");
        // --------------- CRETAION MENU
        menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuEditer = new JMenu("Editer");
        JMenu menuFilm = new JMenu("Film");
        
        JMenuItem nouveauFilm = new JMenuItem("nouveau");
        JMenuItem importerItem = new JMenuItem("importer");
        ImporterAction importerAction = new ImporterAction(this);
        importerItem.addActionListener(importerAction);
        menuFilm.add(nouveauFilm);
        menuFilm.add(importerItem);
        menuEditer.add(menuFilm);
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        menuFichier.add(itemQuitter);
        itemQuitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c.exit();
            }
        });
        menuBar.add(menuFichier);
        menuBar.add(menuEditer);
        
        jf.setJMenuBar(menuBar);
        
        vueG = new JPanel();
        FlowLayout fl = (FlowLayout) vueG.getLayout();
        fl.setAlignment(FlowLayout.LEFT);
        vueD = new JPanel();
        
        scrolPaneG = new JScrollPane(vueG);
        JScrollPane scrolPaneD = new JScrollPane(vueD);
        // js.add
      
       // vueD.setPreferredSize(new Dimension(100, 100));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrolPaneG, scrolPaneD);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(100);

//Provide minimum sizes for the two components in the split pane
  //      Dimension minimumSize = new Dimension(200, 200);
//listScrollPane.setMinimumSize(minimumSize);
//pictureScrollPane.setMinimumSize(minimumSize);
        jf.setContentPane(splitPane);
      //  jf.setMinimumSize(minimumSize);
        //  jp.add(js);
        
        WindowListener w = new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                c.exit();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        };
        
        jf.addWindowListener(w);
        jf.pack();
        jf.setSize(new Dimension(300, 300));
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }

    void importPerformed(List<String> filmPathCol) {
        vueG.removeAll();
        vueG.setBackground(Color.BLUE);
        JPanel jp = new JPanel();
        jp.setBackground(Color.red);
        BoxLayout lm = new BoxLayout(jp, BoxLayout.Y_AXIS); 
        final JPanelNvFilm jpNvFilm = new JPanelNvFilm();
        
        jp.setLayout(lm);
        JList<String> jl = new JList(filmPathCol.toArray(new String[filmPathCol.size()]));
        final ListModel<String> model = jl.getModel();
        
        jl.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(e);
                System.out.println(model.getElementAt(e.getFirstIndex()));
                String elementAt = model.getElementAt(e.getFirstIndex());
                jpNvFilm.getTitreJt().setText(StringTool.take(elementAt, '/', '.'));
                vueD.removeAll();
                vueD.add(jpNvFilm.getJpanel());
                jf.revalidate();
                jf.repaint();
                jf.repaint();
            }
        });
        jl.setDragEnabled(true);
        jl.setCellRenderer(new StringCellRender());

        
        vueG.add(jl);
        scrolPaneG.validate();
       // jf.repaint();
       // jf.pack();
    }

}
