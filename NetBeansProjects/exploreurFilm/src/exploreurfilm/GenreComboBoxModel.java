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

import com.jonathan.metier.Genre;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jonathan
 */
class GenreComboBoxModel extends DefaultComboBoxModel<Genre>{

    public GenreComboBoxModel(Genre[] genreArray) {
        super(genreArray);
       
        Genre addNewGenre = new  Genre();
         addNewGenre.setId(-1);
        addNewGenre.setNom(" ");
         insertElementAt(addNewGenre, 0);
        addNewGenre = new  Genre();
        addNewGenre.setId(-1);
        addNewGenre.setNom("ajouter");
         insertElementAt(addNewGenre, 0);
       
    }
    
}
