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
package exploreurfilm.vue;

import com.jonathan.lib.collections.Chooser;
import com.jonathan.lib.collections.ChooserText;
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
import com.jonathan.lib.string.StringTool;
import com.jonathan.metier.Film;
import exploreurfilm.Controleur;
import exploreurfilm.ImporterAction;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import storage.DataJsonException;

/**
 *
 * @author jonathan
 */
public class ExploreurFilm implements WindowListener , ActionListener {

    private final JFrame jf;
    private final JMenuBar menuBar;
    private final Form vueG;
//    private final JScrollPane scrolPaneG;
    private final JPanel vueD;
    private final Controleur controleur;
    private final VueFilm jpNvFilm;
//    private final VueFilm jpNvFilm;
    private List<String> filmPathCol;
//    private Controleur c;
    private final JMenuItem itemFilmChercher;

    public ExploreurFilm(final Controleur c) {
        this.controleur = c;
        jf = new JFrame("test");
        jpNvFilm = new VueFilm(controleur.getGenres(), controleur.getPays(), controleur);
        // --------------- CRETAION MENU
        menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuEditer = new JMenu("Editer");
        JMenu menuChercher = new JMenu("Chercher");
        JMenu menuFilmEditer = new JMenu("Film");
        itemFilmChercher = new JMenuItem("Film");
        itemFilmChercher.addActionListener(this);
        menuChercher.add(itemFilmChercher);
        JMenuItem nouveauFilm = new JMenuItem("nouveau");
        JMenuItem importerItem = new JMenuItem("importer");
        ImporterAction importerAction = new ImporterAction(this);
        importerItem.addActionListener(importerAction);
        menuFilmEditer.add(nouveauFilm);
        menuFilmEditer.add(importerItem);
        menuEditer.add(menuFilmEditer);
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
        menuBar.add(menuChercher);

        jf.setJMenuBar(menuBar);

        vueG = new Form();
        vueD = new JPanel();
        jf.setContentPane(vueG);

        jf.addWindowListener(this);
        jf.pack();
        jf.setSize(new Dimension(900, 600));
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }

    public void importPerformed(List<String> filmPathCol) {
        vueG.removeAll();
//        vueG.setBackground(Color.BLUE);
        this.filmPathCol = filmPathCol;
//        JPanel jp = new JPanel();
//        jp.setBackground(Color.red);
//        BoxLayout lm = new BoxLayout(jp, BoxLayout.Y_AXIS); 
//        jp.setLayout(lm);
        List<Film> films = controleur.getFilms();
        Iterator<String> iterator = filmPathCol.iterator();
        while (iterator.hasNext()) {
            String c = iterator.next();
            for (Film f : films) {
                if (f.getChemins() != null) {
                    if (f.getChemins().contains(c)) {
                        iterator.remove();
                    }
                }
            }

        }

        final JList<String> jl = new JList(filmPathCol.toArray(new String[filmPathCol.size()]));

//        jl.addListSelectionListener(new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                
////                System.out.println(e.getSource());
////                System.out.println("first indtex : "+e.getFirstIndex());
////                System.out.println("last : "+e.getLastIndex());
////                System.out.println(jl.getSelectedValue());
//                
//                String elementAt = jl.getSelectedValue();
//                jpNvFilm.getTitreJt().setText(StringTool.take(elementAt, '/', '.'));
//                jpNvFilm.addChemin(elementAt);
//                vueD.removeAll();
//                vueD.add(jpNvFilm.getJpanel());
//                vueD.revalidate();
//                vueD.repaint();
//            }
//        });
        jl.setDragEnabled(false);
        jl.setCellRenderer(new StringCellRender());
        jl.setVisibleRowCount(500);
        JScrollPane js = new JScrollPane(jl);
        Dimension s = jl.getPreferredSize();
        int i = js.getVerticalScrollBar().getPreferredSize().width;
        Dimension dimension = new Dimension(s.width + i + 3, 300);
        js.setPreferredSize(dimension);
        js.setMinimumSize(dimension);
        vueG.add(js);
        vueG.nextLine();
        JLabel jLabel = new JLabel("Sélectioner des chemins et cliquer sur ok");
//        jLabel.setFont(new Font(jLabel.getFont().getName(), Font.PLAIN, 10));
        vueG.addWithJPanel(jLabel);
        vueG.nextLine();
        JButton jButton = new JButton(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedValuesList = jl.getSelectedValuesList();
                if (!selectedValuesList.isEmpty()) {
                    jpNvFilm.remove();
                    jpNvFilm.addChemins(selectedValuesList);
//                    for (String s : selectedValuesList) {
//                        jpNvFilm.addChemin(s);
//                    }
                    jpNvFilm.getTitreJt().setText(StringTool.take(selectedValuesList.get(0), '/', '.'));
                    vueD.removeAll();
                    vueD.add(jpNvFilm.getJpanel());

                    jf.setContentPane(vueD);
                    jf.validate();
                    jf.repaint();
                }
            }
        });
        jButton.setText("OK");
        JPanel j = new JPanel();
        j.add(jButton);
        vueG.add(j);
        jf.setContentPane(vueG);
        jf.validate();
        jf.repaint();
//         jf.pack();
    }

    public void doEndFilmImport() {
        JOptionPane.showMessageDialog(jf, "film inclu à la bibliotèque", "import OK", JOptionPane.INFORMATION_MESSAGE);
//        jf.setContentPane(null);
        jf.validate();
        jf.repaint();
        importPerformed(filmPathCol);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        controleur.exit();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == itemFilmChercher){
            jf.setContentPane((new VueChercherFilm(this, controleur )).getPabel());
        }
        jf.validate();
        jf.repaint();
    }

    List<Film> chercherFilm(String text) {
        ChooserText<Film> chooserText = new ChooserText<Film>() {

            @Override
            public boolean choose(Film o) {
                return o.getTitre().toLowerCase().contains(text.toLowerCase());
            }
        };
        chooserText.setText(text);
        Comparator<Film> comparator = new Comparator<Film>() {

            @Override
            public int compare(Film o1, Film o2) {
                return (o1.getTitre().compareToIgnoreCase(o2.getTitre()));
            }
        };
        List<Film> filmsMatch = Chooser.chooseAndOrder(controleur.getFilms(), chooserText, comparator);
        return filmsMatch;
    }
    
    

}
