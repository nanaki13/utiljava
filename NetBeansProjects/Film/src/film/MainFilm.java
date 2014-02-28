/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package film;

import java.io.File;
import metier.Film;
import util.Scanneur;



/**
 *
 * @author jonathan
 */
public class MainFilm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File[] listeCheminFilm;
        Film[] films;
        // TODO code application logic here
        listeCheminFilm = Scanneur.listeCheminFilm(new File("/home/jonathan/Vidéos"));
        films = new Film[listeCheminFilm.length];
        for (int i = 0; i < listeCheminFilm.length; i++) {
            File file = listeCheminFilm[i];
          //  System.out.println(file.getAbsolutePath());
            films[i] = new Film();
            films[i].setChemin(file.getAbsolutePath());
        }
        System.out.println("nombre de film trouvé : "+films.length);
    }
}
