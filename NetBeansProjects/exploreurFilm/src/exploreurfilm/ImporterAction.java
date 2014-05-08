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

import static exploreurfilm.SystemProprietes.USER_HOME;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import javax.swing.Action;
import javax.swing.JFileChooser;
import util.Scanneur;

/**
 *
 * @author jonathan
 */
public class ImporterAction implements ActionListener{
    
    private final List<String> filmPathCol;
    private final ExploreurFilm exploreurFilm;

 

    ImporterAction(ExploreurFilm exploreurFilm) {
        this.filmPathCol = new ArrayList<>();
        this.exploreurFilm = exploreurFilm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        filmPathCol.clear();
        JFileChooser jFileChooser = new JFileChooser(USER_HOME);
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //  jFileChooser.
        //  jFileChooser.setVisible(true);
        int returnValue = jFileChooser.showDialog(null, "importer");
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            File[] listeCheminFilm = Scanneur.listeCheminFilm(file);
            for(File Filmfile : listeCheminFilm){
                filmPathCol.add(Filmfile.getAbsolutePath());
            }
            exploreurFilm.importPerformed(filmPathCol);
        }
      //  jFileChooser.
    }
    public List<String> getFilmPathCol(){
        return filmPathCol;
    }

   
}
