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

import com.jonathan.json.parser.ParserJson;

import com.jonathan.metier.Film;
import com.jonathan.metier.Genre;
import com.jonathan.metier.MembreFilm;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.DataJson;
import sun.java2d.loops.CompositeType;

/**
 *
 * @author jonathan
 */
public class Controleur implements ControleurInterface{
    
    private final List<Genre> genres;
    private List<Film> films;

    private final List<MembreFilm> membreFilm;
    private final DataJson dataJson;
    
    public Controleur(){
        dataJson = new DataJson();
        genres = dataJson.deserialise(Genre.class, "genres.json");
        membreFilm = dataJson.deserialise(MembreFilm.class, "membresFilm.json");
        films = dataJson.deserialise(Film.class, "films.json");
    }

    @Override
    public void exit() {
        dataJson.serialiseLazy(genres, "genres.json");
        System.exit(0);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Film> getFilms() {
        return films;
    }

 
    
    public static int findFreeId(List<?> objects) {
        if (objects.isEmpty()) {
            return 1;
        } else {
            try {
                int idBefore = -1;
                int idCandidat = -1;
                Method methodGet = objects.get(0).getClass().getMethod("getId", (Class<?>[]) null);
                System.out.println(methodGet.getParameterTypes());
                for (Object o : objects) {
                    idCandidat = (int) methodGet.invoke(o, (Object[]) null);
                    if (idBefore == -1) {
                        idBefore = idCandidat;
                    }
                    idCandidat = Math.max(idCandidat, idBefore);
                    
                }
                return ++idCandidat;
            } catch (    NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
        }
    }
    
    public static void main(String[] args){
        Genre g = new Genre(1, "cacca");
        List<Genre> l = new ArrayList<>();
        l.add(g);
        System.out.println(findFreeId(l));
    }
    
    
    
}
