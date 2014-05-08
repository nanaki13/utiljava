/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;

/**
 *
 * @author jonathan
 */
public class Scanneur {

    public static File[] listeCheminFilm(File repertoire ) {
        File[] listIn ;
        File[] list = null;
        File[] listeRep;
        File[][] listeFilmRep;
        int k;
        int nbSousFilm = 0;
        if (repertoire.isDirectory()) {
           
            listeRep = repertoire.listFiles(new RepertoireFilter());
            listeFilmRep = new File[listeRep.length][];
            for (int i = 0; i < listeRep.length; i++) {
                File file = listeRep[i];
                listeFilmRep[i] = listeCheminFilm(file); 
                nbSousFilm+=listeFilmRep[i].length;
                
            }
            listIn = repertoire.listFiles(new FilmFileFilter());
//            System.out.println("repertoire : "+repertoire);
//            System.out.println("nombre de film dans rep : "+listIn.length);
            list = new File[nbSousFilm + listIn.length];
            k = 0;
            for (int i = 0; i < listeRep.length; i++) {
                for (File item : listeFilmRep[i]) {
                    list[k] = item;
                    k++;
                }
            }
            listIn = repertoire.listFiles(new FilmFileFilter());
            for (File listIn1 : listIn) {
                System.out.println("         " + listIn1);
                list[k] = listIn1;
                k++;
            }
        }
        return list;
    }
}
//        list= repertoire.listFiles(filter.accept(, name));
// 
//			if (list != null){
//				for ( int i = 0; i < list.length; i++) {
//					// Appel récursif sur les sous-répertoires
//					listeCheminFilm( list[i],type);
//				} 
//			} else {
//				System.err.println(repertoire + " : Erreur de lecture.");
//			}
//		}
//			if (repertoire.getPath()!= type){
//				Resize size = new Resize(repertoire.getPath());
//			}
//	}    

