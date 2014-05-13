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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author jonathan
 */
public class VueNvFilm implements ActionListener {

    private JTextField titreJt;
    private JTextField dateRealisationJt;
    private JTextField origineJt;
    private JTextField lieuxDeTournageJt;
    private JTextArea synopsisJt;

    private JLabel titreJl;
    private JLabel dateRealisationJl;
    private JLabel origineJl;
    private JLabel lieuxDeTournageJl;
    private JLabel synopsisjL;
    private final JPanel mainJpanel;

    private String chemin;
    private final JLabel genrejL;
    private final JComboBox<Genre> genreJc;
    private final List<Genre> genres;

    public VueNvFilm(List<Genre> genres) {
        this.genres = genres;

        titreJl = new JLabel("titre");
        dateRealisationJl = new JLabel("date de réalisation");
        origineJl = new JLabel("pays de réalisation");
        lieuxDeTournageJl = new JLabel("lieux de tournage");
        synopsisjL = new JLabel("synopsis");
        genrejL = new JLabel("genre");

        titreJt = new JTextField(10);
        dateRealisationJt = new JTextField(10);
        origineJt = new JTextField(10);
        lieuxDeTournageJt = new JTextField(10);
        synopsisJt = new JTextArea(10, 20);
        Genre[] genreArray = genres.toArray(new Genre[genres.size()]);
        GenreComboBoxModel genreModel = new GenreComboBoxModel(genreArray);

        genreJc = new JComboBox<>(genreModel);
        genreJc.setRenderer(new ListCellRenderer<Genre>() {

            @Override
            public Component getListCellRendererComponent(JList<? extends Genre> list, Genre value, int index, boolean isSelected, boolean cellHasFocus) {
                return new JLabel(value.getNom());
            }

        });
  
        genreJc.addActionListener(this);

        mainJpanel = new JPanel(new SpringLayout());
        mainJpanel.add(titreJl);
        mainJpanel.add(titreJt);
        mainJpanel.add(genrejL);
        mainJpanel.add(genreJc);
        mainJpanel.add(dateRealisationJl);
        mainJpanel.add(dateRealisationJt);
        mainJpanel.add(origineJl);
        mainJpanel.add(origineJt);
        mainJpanel.add(lieuxDeTournageJl);
        mainJpanel.add(lieuxDeTournageJt);
        mainJpanel.add(synopsisjL);
        mainJpanel.add(synopsisJt);

        SpringUtilities.makeCompactGrid(mainJpanel,
                6, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad
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

    private int findId() {
        if (genres.isEmpty()) {
            return 1;
        } else {
            int idBefore = -1;
            int idCandidat = -1;
            for (Genre genre : genres) {
                if (idBefore == -1) {
                    idBefore = genre.getId();
                }
                idCandidat = Math.max(genre.getId(), idBefore);

            }
            return ++idCandidat;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genreJc) {
            String nom = ((Genre) (genreJc.getSelectedItem())).getNom();
            if (nom.equals("ajouter")) {
//                    JOptionPane jOptionPane = new JOptionPane();
                String newNomGenre = JOptionPane.showInputDialog(mainJpanel, "entrer le genre", "nouveau genre", JOptionPane.QUESTION_MESSAGE);
                if (newNomGenre != null && !newNomGenre.trim().isEmpty()) {
                    Genre genre = new Genre(Controleur.findFreeId(genres), newNomGenre);
                    genreJc.addItem(genre);
                    genreJc.setSelectedItem(genre);
                    genres.add(genre);
                }

            }
        }

    }
    
    public Film getNewFilm(){
        Film ret = new Film();
        ret.setTitre(titreJt.getText());
        ret.setSynopsis(synopsisJt.getText());
        ret.setDateRealisation(null);
        
      return new Film();
    }

}
