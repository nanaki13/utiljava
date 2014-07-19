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

import com.jonathan.metier.Film;
import exploreurfilm.Controleur;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author jonathan
 */
class VueChercherFilm implements ActionListener{
    private ExploreurFilm exploreurFilm;
    private Controleur controleur;
    private JTextField jt;
    private Form f;
    private Form fFilm = new Form();
    private JScrollPane js = new JScrollPane(fFilm);
    private JPanel jp;
    private List<JButton> buttons;

    public VueChercherFilm(ExploreurFilm exploreurFilm, Controleur controleur) {
        this.exploreurFilm = exploreurFilm;
        this.controleur = controleur;
    }

    Container getPabel() {
        jp = new JPanel(new BorderLayout());
        f = new Form();
        JLabel jl = new JLabel("nom : ");
        f.addWithJPanel(jl);
        jt = new JTextField(15);
        f.add(jt);
        f.nextLine();
        JButton jb = new JButton("Chercher");
        f.addWithJPanel(jb, Form.MAX_SIZE);
        jb.addActionListener(this);
        jp.add(f, BorderLayout.NORTH);
        jp.add(js, BorderLayout.WEST);
        return jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Film> chercherFilm = exploreurFilm.chercherFilm(jt.getText());
        fFilm.removeAll();
        for(final Film fi : chercherFilm){
             JLabel jl = new JLabel(fi.getTitre());
            fFilm.add(jl);
            JButton jb = new JButton("lire");
            jb.addActionListener(new ActionListener() {

                 @Override
                 public void actionPerformed(ActionEvent e) {
                     try {
                         //                     fi.getChemins().get(0);
                         ProcessBuilder builder = new ProcessBuilder("vlc" , fi.getChemins().get(0));
                         builder.start();
                       
                     } catch (IOException ex) {
                         Logger.getLogger(VueChercherFilm.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
            fFilm.add(jb);
            fFilm.nextLine();
        }
        
        jp.validate();
        jp.getParent().repaint();
    }

   

   
    
}
