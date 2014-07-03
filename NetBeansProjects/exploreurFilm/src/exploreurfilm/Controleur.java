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

import exploreurfilm.vue.ExploreurFilm;
import com.jonathan.json.parser.ParserJson;

import com.jonathan.metier.Film;
import com.jonathan.metier.Genre;
import com.jonathan.metier.MembreFilm;
import com.jonathan.metier.Metier;
import com.jonathan.metier.Pays;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import storage.DataJson;
import storage.DataJsonException;
import sun.java2d.loops.CompositeType;

/**
 *
 * @author jonathan
 */
public class Controleur implements ControleurInterface{
    
    private final List<Genre> genres;
    private List<Film> films;
    private List<Metier> metiers;
    private List<Pays> pays;
    private ExploreurFilm exploreurFilm;

    private final List<MembreFilm> membreFilm;
    private final DataJson dataJson;
    
    public Controleur() throws DataJsonException{
        dataJson = new DataJson();
        genres = dataJson.deserialise(Genre.class, "genres.json");
        metiers = dataJson.deserialise(Metier.class, "metiers.json");
        pays = dataJson.deserialise(Pays.class, "pays.json");
        membreFilm = dataJson.deserialise(MembreFilm.class, "membresFilm.json");
        films = dataJson.deserialise(Film.class, "films.json");
    }

    @Override
    public void exit() {
        dataJson.serialiseLazy(genres, "genres.json");
        dataJson.serialiseLazy(metiers, "metiers.json");
        dataJson.serialiseLazy(pays, "pays.json");
        dataJson.serialiseLazy(films, "films.json");
        
        System.exit(0);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Film> getFilms() {
        return films;
    }

    public List<Metier> getMetiers() {
        return metiers;
    }

    public List<Pays> getPays() {
        return pays;
    }

    public List<MembreFilm> getMembreFilm() {
        return membreFilm;
    }

    
 
    
    public static int findFreeId(List<?> objects) {
        if (objects.isEmpty()) {
            return 1;
        } else {
            try {
                int idBefore = -1;
                int idCandidat = -1;
                Object ret;
                Method methodGet = objects.get(0).getClass().getMethod("getId", (Class<?>[]) null);
                System.out.println(methodGet.getParameterTypes());
                for (Object o : objects) {
                    ret = methodGet.invoke(o, (Object[]) null);
                    
                    if(ret instanceof Integer)
                        idCandidat = (Integer) ret;
                    else if(Integer.TYPE.isInstance(o))
                        idCandidat = (int) ret;
                    if (idBefore == -1) {
                        idBefore = idCandidat;
                    }
                    idCandidat = Math.max(idCandidat, idBefore);
                    
                }
                idCandidat++;
                System.out.println("idCandidat : "+idCandidat);
                return idCandidat;
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
    
    public void newFilm(Film f){
        if(f.getId() == null){
             int findFreeId = findFreeId(films);
             f.setId(findFreeId);
             films.add(f);
        }
        exploreurFilm.doEndFilmImport();
        
    }

    public void setExploreurFilm(ExploreurFilm exploreurFilm) {
        this.exploreurFilm = exploreurFilm;
    }
    
    
    
}
