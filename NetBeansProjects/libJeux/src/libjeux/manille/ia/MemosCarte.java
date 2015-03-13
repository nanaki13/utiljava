/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.ia;

import libjeux.manille.object.Carte;
import libjeux.manille.object.CarteException;
import libjeux.manille.object.CarteManille;

/**
 *
 * @author jonathan
 */
public class MemosCarte {
 
    public final Carte[][] jeux = new Carte[Carte.CouleurCarte.values().length][Carte.NomCarte.values().length];
    
    public void add(Carte c){
        jeux[c.getCouleurCarte().ordinal()][c.getForce()] = c;
    }
    
    public boolean isMasterInColor(Carte c){
        int indiceCoul = c.getCouleurCarte().ordinal();
        Carte[] couls  =jeux[indiceCoul];
        int indiceNom = c.getForce();
        for(int i = indiceNom+1 ; i <couls.length ; i++  ){
            
            if(couls[i] == null){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws CarteException {
        MemosCarte m = new MemosCarte();
        m.add(new CarteManille("8 co"));
        System.out.println(m.isMasterInColor(new CarteManille("7 co")));
    }
}
