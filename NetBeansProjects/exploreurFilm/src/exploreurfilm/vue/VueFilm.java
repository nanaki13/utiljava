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

import com.jonathan.lib.collections.ChooserText;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import com.jonathan.lib.string.StringTool;
import com.jonathan.metier.Film;
import com.jonathan.metier.Genre;
import com.jonathan.metier.Pays;
import exploreurfilm.Controleur;
import exploreurfilm.StringMaker;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 *
 * @author jonathan
 */
public class VueFilm implements ActionListener, MouseListener, ObjectListener<Genre> {

    private JTextField titreJt;
    private JTextField dateRealisationJt;
    private JTextFieldPP origineJt;
    private JTextFieldPP lieuxDeTournageJt;
    private JTextArea synopsisJt;
    public static final ImageIcon CLOSEICONE = new ImageIcon("./close.png");
    private JLabel titreJl;
    private JLabel dateRealisationJl;
    private JLabel origineJl;
    private JLabel lieuxDeTournageJl;
    private JLabel synopsisjL;
    private JButton enregistrer;
    private final JPanel genresDuFilmPanel;
//    private List<JLabel> removable = new LinkedList<JLabel>();
    private Form cheminForm;
    private final Form mainJpanel;
    private Film film;
    private final JLabel genrejL;
    private final JComboBox<Genre> genreJc;
    private final List<Genre> allGenres;
    private List<Genre> genreDuFilm;
    private List<Pays> pays;
    private List<String> chemins = new LinkedList<>();
    private boolean justInit;
    private Controleur controleur;

    public VueFilm(List<Genre> genres, List<Pays> pays, Controleur c) {
        this.allGenres = genres;
        genreDuFilm = new LinkedList<Genre>();
        this.pays = pays;
        controleur = c;
        titreJl = new JLabel("titre");
        dateRealisationJl = new JLabel("date de réalisation");
        origineJl = new JLabel("pays de réalisation");
        lieuxDeTournageJl = new JLabel("lieux de tournage");
        synopsisjL = new JLabel("synopsis");
        genrejL = new JLabel("genre");
        cheminForm = new Form();
//        formJP.set
        titreJt = new JTextField(10);
        dateRealisationJt = new JTextField(10);
        enregistrer = new JButton();
        genresDuFilmPanel = new JPanel();
        enregistrer.addActionListener(this);

        Comparator<Pays> comparator = new Comparator<Pays>() {

            @Override
            public int compare(Pays o1, Pays o2) {
                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        };
        ChooserText<Pays> chooser;
        chooser = new ChooserText<Pays>() {

            @Override
            public boolean choose(Pays o) {
                String trim = text.trim();
                String toLowerCase = trim.toLowerCase();
                if (!toLowerCase.isEmpty()) {
                    return o.getNom().toLowerCase().contains(toLowerCase);
                } else {
                    return false;
                }
            }
        };
        origineJt = new JTextFieldPP<>(pays, chooser, 10);
        origineJt.setComparator(comparator);
        origineJt.setStringMaker(new StringMaker<Pays>() {
            @Override
            public String buildString(Pays t) {
                return t.getNom();
            }
        });
//        lieuxDeTournageJt = new JTextField(10);
        lieuxDeTournageJt = new JTextFieldPP<>(pays, chooser, 10);
        lieuxDeTournageJt.setComparator(comparator);
        lieuxDeTournageJt.setStringMaker(new StringMaker<Pays>() {
            @Override
            public String buildString(Pays t) {
                return t.getNom();
            }
        });
        synopsisJt = new JTextArea(10, 20);
        
        Genre[] genreArray = genres.toArray(new Genre[0]);
        GenreComboBoxModel genreModel = new GenreComboBoxModel(genreArray);

        genreJc = new JComboBox<>(genreModel);
        genreJc.setRenderer(new exploreurfilm.vue.ListCellRenderer<Genre>(new StringMaker<Genre>() {

            @Override
            public String buildString(Genre t) {
                return t.getNom();
            }
        })
        );

        genreJc.addActionListener(this);

        mainJpanel = new Form();
        mainJpanel.add(titreJl);
        mainJpanel.add(titreJt);
        mainJpanel.nextLine();
        mainJpanel.add(genrejL);
        mainJpanel.add(genreJc);
        mainJpanel.nextLine();
        mainJpanel.addS(genresDuFilmPanel, Form.MAX_SIZE);
        mainJpanel.nextLine();
        mainJpanel.add(dateRealisationJl);
        mainJpanel.add(dateRealisationJt);
        mainJpanel.nextLine();
        mainJpanel.add(origineJl);
        mainJpanel.add(origineJt);
        mainJpanel.nextLine();
        mainJpanel.add(lieuxDeTournageJl);
        mainJpanel.add(lieuxDeTournageJt);
        mainJpanel.nextLine();
        mainJpanel.add(synopsisjL);
        mainJpanel.add(synopsisJt);
        mainJpanel.nextLine();
        mainJpanel.add(cheminForm);
        mainJpanel.nextLine();
        mainJpanel.addWithJPanel(enregistrer, Form.MAX_SIZE);
        mainJpanel.nextLine();
        justInit = true;
//        mainJpanel.add(cheminsJP);

//
//        SpringUtilities.makeCompactGrid(mainJpanel,
//                7, 2, //rows, cols
//                6, 6, //initX, initY
//                6, 6);       //xPad, yPad
    }

    public JPanel getJpanel() {

        return mainJpanel;
    }

    public JTextField getTitreJt() {
        return titreJt;
    }

    public JTextField getDateRealisationJt() {
        return dateRealisationJt;
    }

    public JTextField getOrigineJt() {
        return origineJt;
    }

    public JTextField getLieuxDeTournageJt() {
        return lieuxDeTournageJt;
    }

    public JTextArea getSynopsisJt() {
        return synopsisJt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genreJc) {
            Genre selectedItem = (Genre) genreJc.getSelectedItem();
            String nom = ((Genre) (genreJc.getSelectedItem())).getNom();
            if (nom.equals("ajouter")) {
//                    JOptionPane jOptionPane = new JOptionPane();
                String newNomGenre = JOptionPane.showInputDialog(mainJpanel, "entrer le genre", "nouveau genre", JOptionPane.QUESTION_MESSAGE);
                if (newNomGenre != null && !newNomGenre.trim().isEmpty()) {
                    Genre genre = new Genre(Controleur.findFreeId(allGenres), newNomGenre);
                    genreJc.addItem(genre);
                    genreJc.setSelectedItem(genre);
                    allGenres.add(genre);
                    genreDuFilm.add(genre);
                    RemovablePanelObject<Genre> r = new RemovablePanelObject(this, CLOSEICONE,this,genre);
                    JLabel jl = new JLabel(genre.getNom());
                    r.add(jl);
                    genresDuFilmPanel.add(r);
                }
            } else if (selectedItem.getId() != -1) {
                if(!genreDuFilm.contains(selectedItem)){
                    genreDuFilm.add(selectedItem);
                    RemovablePanelObject<Genre> r = new RemovablePanelObject(this, CLOSEICONE,this,selectedItem);
                    JLabel jl = new JLabel(selectedItem.getNom());
                    r.add(jl);
                    genresDuFilmPanel.add(r);
                    System.out.println(synopsisJt.getPreferredSize());
                    mainJpanel.revalidate();
                    mainJpanel.repaint();
                }
                
            }
        } else if (e.getSource() == enregistrer) {
            controleur.newFilm(getNewFilm());

        }

    }

    public Film getNewFilm() {
        if (film == null) {
            film = new Film();
        }
        film.setTitre(titreJt.getText());
        film.setSynopsis(synopsisJt.getText());
        film.setDateRealisation(null);
        for (String ch : chemins) {
            film.addChemin(ch);
        }
        if(!genreDuFilm.isEmpty()){
            film.setGenres(new LinkedList<Genre>(genreDuFilm) );
        }
        
        Film ret = film;
        clearAll();
        return ret;
    }

//    public List<String> getChemins() {
//        if(chemins == null){
//            chemins = new LinkedList<>();
//        }
//        return chemins;
//    }
    void addChemin(String elementAt) {
        justInit = false;
        cheminForm.setBackground(Color.blue);
        if (!chemins.contains(elementAt)) {
            if (chemins.isEmpty()) {
                mainJpanel.savePos();
            }
            chemins.add(elementAt);
            JLabel jLabel = new JLabel("chemin " + chemins.size());
            jLabel.setToolTipText(elementAt);
//            jLabel.setHorizontalAlignment(SwingConstants.LEFT);
//            removable.add(jLabel);
            cheminForm.addS(jLabel, Form.LEFT);
            cheminForm.nextLine();
        }
    }

    void remove() {
        if (!justInit) {
            chemins.clear();
//            for(JLabel j : removable){
            cheminForm.removeAll();
//            }
//            mainJpanel.restorePos();
        }
    }

    void addChemins(List<String> selectedValuesList) {
        for (String s : selectedValuesList) {
            addChemin(s);
        }
        enregistrer.setText("OK");
//        mainJpanel.addWithEncapse(enregistrer);

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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void event(Genre t) {
        genreDuFilm.remove(t);
    }

    private void clearAll() {
        chemins.clear();
        genreDuFilm.clear();
        synopsisJt.setText("");
        genresDuFilmPanel.removeAll();
        film = null;
        
    }

}
