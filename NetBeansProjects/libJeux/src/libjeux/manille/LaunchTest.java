/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille;

import libjeux.manille.object.JoueurDefault;
import libjeux.manille.object.PointPlit;
import libjeux.manille.object.PlitDeCarte;
import libjeux.manille.object.CarteManille;
import libjeux.manille.object.Carte;
import libjeux.manille.Manille;

/**
 *
 * @author jonathan
 */
public class LaunchTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JoueurDefault j1e1 = new JoueurDefault("Jonathan",0);
        JoueurDefault j2e1 = new JoueurDefault("Julie",1);
        JoueurDefault j1e2 = new JoueurDefault("Matthieu",2);
        JoueurDefault j2e2 = new JoueurDefault("Antonin",3);
        Manille m = new Manille(null);
        m.setJoueurs(j1e1, j2e1, j1e2, j2e2);
        m.run();

 
    }
    public static void testPoint(){
           JoueurDefault j1e1 = new JoueurDefault("Jonathan",0);
        JoueurDefault j2e1 = new JoueurDefault("Julie",1);
        JoueurDefault j1e2 = new JoueurDefault("Matthieu",2);
        JoueurDefault j2e2 = new JoueurDefault("Antonin",3);
                
        CarteManille m1 = new CarteManille(Carte.NomCarte.dix, Carte.CouleurCarte.pique);
        CarteManille m2 = new CarteManille(Carte.NomCarte.dame, Carte.CouleurCarte.pique);
        CarteManille m3 = new CarteManille(Carte.NomCarte.as, Carte.CouleurCarte.coeur);
        CarteManille m4 = new CarteManille(Carte.NomCarte.dame, Carte.CouleurCarte.trefle);
        PlitDeCarte p  = new PlitDeCarte();
        p.setAtout(Carte.CouleurCarte.trefle);
//        p.setDemande(Carte.CouleurCarte.pique);
        p.add(m1, j1e1);
        p.add(m2, j2e1);
        p.add(m3, j1e2);
        p.add(m4, j2e2);
        PointPlit count = p.count();
        System.out.println("point : "+count.getPoint()+" meneur : "+count.getMeneur().getName());
    }
    
}
