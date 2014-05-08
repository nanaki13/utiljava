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
import lib.string.StringTool;
import metier.Pays;

/**
 *
 * @author jonathan
 */
public class JPanelNvFilm {

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

    public JPanelNvFilm() {

        titreJl = new JLabel("titre");
        dateRealisationJl = new JLabel("date de réalisation");
        origineJl = new JLabel("pays de réalisation");
        lieuxDeTournageJl = new JLabel("lieux de tournage");
        synopsisjL = new JLabel("synopsis");

        titreJt = new JTextField(10);
        dateRealisationJt = new JTextField(10);
        origineJt = new JTextField(10);
        lieuxDeTournageJt = new JTextField(10);
        synopsisJt = new JTextArea(10, 20);

        mainJpanel = new JPanel(new SpringLayout());
        mainJpanel.add(titreJl);
        mainJpanel.add(titreJt);
        mainJpanel.add(dateRealisationJl);
        mainJpanel.add(dateRealisationJt);
        mainJpanel.add(origineJl);
        mainJpanel.add(origineJt);
        mainJpanel.add(lieuxDeTournageJl);
        mainJpanel.add(lieuxDeTournageJt);
        mainJpanel.add(synopsisjL);
        mainJpanel.add(synopsisJt);

        SpringUtilities.makeCompactGrid(mainJpanel,
                5, 2, //rows, cols
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

}
