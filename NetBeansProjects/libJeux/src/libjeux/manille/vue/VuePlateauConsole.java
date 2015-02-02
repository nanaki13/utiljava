/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.vue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import libjeux.manille.object.Carte;
import libjeux.manille.object.DefaultCompCarte;
import libjeux.manille.object.JoueurInterface;

/**
 *
 * @author jonathan
 */
public class VuePlateauConsole implements VuePlateau{

    private DefaultCompCarte compCarte = new DefaultCompCarte();
    public void afficheMain(List<Carte> main) {
//        System.out.println(Arrays.asList(main));
       
        Collections.sort(main,compCarte);
        for(Carte c : main){
            System.out.print(c+":");
        }
    }

    public void afficheChoix(List<Carte> main,List<Carte> playable) {
        Collections.sort(main,compCarte);
        int i = 0;
        for(Carte c : main){
            if(playable.contains(c)){
                System.out.print(c+"("+i+"):");
                i++;
            }else{
                System.out.print(c+"(X):");
            }
                
        }
    }

    public void afficheCarteJouer(Carte c, JoueurInterface j, Integer numTour) {
        System.out.println(j+" : "+c);
    }

    
    
}
