/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.vue;

import java.util.List;
import libjeux.manille.object.Carte;
import libjeux.manille.object.JoueurInterface;

/**
 *
 * @author jonathan
 */
public interface VuePlateau {
    
    public void afficheMain(List<Carte> main);
    
    public void afficheChoix(List<Carte> main,List<Carte> playable);

    public void afficheCarteJouer(Carte c, JoueurInterface j, Integer numTour);
}
