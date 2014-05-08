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

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import metier.Film;

/**
 *
 * @author jonathan
 */
public class ListFilmModel<E> extends DefaultListModel<E>{
    
    private List<E> data;

    public ListFilmModel(List<E> data){
        this.data = data;
    }
    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public E getElementAt(int index) {
        return data.get(index);
    }

    
    
}
