/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.object;

import java.util.Comparator;

/**
 *
 * @author jonathan
 */
public class DefaultCompCarte implements Comparator<Carte>{

    public int compare(Carte o1, Carte o2) {
        if(o1.getCouleurCarte() == o2.getCouleurCarte()){
            return o1.getForce() - o2.getForce();
        }else{
            return o1.getCouleurCarte().ordinal() - o2.getCouleurCarte().ordinal();
        }
    }
    
}
