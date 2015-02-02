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
public class CompCarte implements Comparator<Carte>{
    private  Carte.CouleurCarte atout;
    private  Carte.CouleurCarte demande;

    public CompCarte(Carte.CouleurCarte atout , Carte.CouleurCarte demande){
        this.atout = atout;
        this.demande = demande;
    }

    public CompCarte() {
        
    }

    public void setAtout(Carte.CouleurCarte atout) {
        this.atout = atout;
    }

    public void setDemande(Carte.CouleurCarte demande) {
        this.demande = demande;
    }
    
    public int compare(Carte o1, Carte o2) {
        if( o1.getCouleurCarte() == atout && o2.getCouleurCarte() != atout){
            return 1;
        }else if(o1.getCouleurCarte() != atout && o2.getCouleurCarte() == atout){
            return -1;
        }else if(o1.getCouleurCarte() == demande && o2.getCouleurCarte() == demande || o1.getCouleurCarte() == atout && o2.getCouleurCarte() == atout){
            return o1.getForce() - o2.getForce();
        }else{
            return 0;
        }
    }

    public Carte.CouleurCarte getAtout() {
        return atout;
    }

    public Carte.CouleurCarte getDemande() {
        return demande;
    }

    
    @Override
    public String toString() {
        return "CompCarte{" + "atout=" + atout + ", demande=" + demande + '}';
    }
    
    
}
