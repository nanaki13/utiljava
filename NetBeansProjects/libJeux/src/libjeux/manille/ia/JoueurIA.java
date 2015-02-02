/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.ia;

import java.util.ArrayList;
import java.util.List;
import libjeux.manille.object.Carte;
import libjeux.manille.object.Equipe;
import libjeux.manille.object.JoueurInterface;

/**
 *
 * @author jonathan
 */
public class JoueurIA extends AbstractJoueur implements JoueurInterface {

    private List<Carte> carreau = new ArrayList<Carte>(8);
    private List<Carte> pique = new ArrayList<Carte>(8);
    private List<Carte> trefle = new ArrayList<Carte>(8);
    private List<Carte> coeur = new ArrayList<Carte>(8);
    private int nbCoeur;
    private int nbTrefle;
    private int nbPique;
    private int nbCarreau;
    

    @Override
    public void carteJouer(Carte c, JoueurInterface j, Integer numTour) {
        super.carteJouer(c, j, numTour);
        switch (c.getCouleurCarte()) {
            case carreau:
                carreau.add(c);
                break;
            case pique:
                pique.add(c);
                break;
            case coeur:
                coeur.add(c);
                break;
            case trefle:
                trefle.add(c);
                break;
        }

    }

    public Carte joux() {
        return null;
    }

    public Carte.CouleurCarte chooseAtout() {
        if (nbCarreau > 3){
            return Carte.CouleurCarte.carreau;
        }else if (nbTrefle > 3){
            return Carte.CouleurCarte.trefle;
        }else if (nbCoeur > 3){
            return Carte.CouleurCarte.coeur;
        }else if (nbPique > 3){
            return Carte.CouleurCarte.pique;
        }else{
            return maxCoulPoint();
        }
        
       
    }

    public Carte.CouleurCarte chooseAtoutEnFace() {
        return null;
    }

    @Override
    public void setCartes(List<Carte> cartes) {
        super.setCartes(cartes); 
        for(Carte c : cartes ){
            switch (c.getCouleurCarte()){
                case carreau : 
                    nbCarreau++;
                    break;
                case coeur : 
                    nbCoeur++;
                    break;
                case trefle : 
                    nbTrefle++;
                    break;
                case pique : 
                    nbPique++;
                    break;
            }
        }
    }

    private Carte.CouleurCarte maxCoulPoint() {
        int countCoeur = 0;
        int countCarreau = 0;
        int countTrfle = 0;
        int countpique = 0;
        for(Carte c : cartes){
            switch (c.getCouleurCarte()){
                case carreau : 
                    countCarreau+=c.getPoint();
                    break;
                case coeur : 
                    countCoeur+=c.getPoint();
                    nbCoeur++;
                    break;
                case trefle : 
                    countTrfle+=c.getPoint();
                    nbTrefle++;
                    break;
                case pique : 
                    countpique+=c.getPoint();
                    nbPique++;
                    break;
            }
            
        }
    }
    

}
