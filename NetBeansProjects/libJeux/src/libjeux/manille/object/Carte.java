/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.object;

import java.io.Serializable;

/**
 *
 * @author jonathan
 */
public abstract class Carte implements Serializable,Comparable<Carte>{

    protected short force;
    private final NomCarte nom;
    private final CouleurCarte couleurCarte;
    public static final char PIQUE = '\u2660';
    public static final char TREFLE = '\u2663';
    public static final char COEUR = '\u2665';
    public static final char CARREAU = '\u2666';
    
    

    public Carte(short force, NomCarte nom, CouleurCarte couleurCarte) {
        this.force = force;
        this.nom = nom;
        this.couleurCarte = couleurCarte;
    }
     public Carte(String carte) throws CarteException {
       String nomStr = carte.substring(0, carte.indexOf(' '));
       String coul = carte.substring(carte.indexOf(' ')).trim();
      if(nomStr.equals("7")){
          nom = NomCarte.sept;
      }else if(nomStr.equals("8")){
          nom = NomCarte.huit;
      }else if(nomStr.equals("9")){
          nom = NomCarte.neuf;
      }else if(nomStr.equals("10")){
          nom = NomCarte.dix;
      }else if(nomStr.equals("va")){
          nom = NomCarte.valet;
      }else if(nomStr.equals("da")){
          nom = NomCarte.dame;
      }else if(nomStr.equals("ro")){
          nom = NomCarte.roi;
      }else if(nomStr.equals("as")){
          nom = NomCarte.as;
      }else throw new CarteException("erreur : "+nomStr+" n'existe pas");
      
      if(coul.equals("co")){
          couleurCarte = CouleurCarte.coeur;
      }else if(coul.equals("ca")){
          couleurCarte = CouleurCarte.carreau;
      }else if(coul.equals("pi")){
          couleurCarte = CouleurCarte.pique;
      }else if(coul.equals("tr")){
          couleurCarte = CouleurCarte.trefle;
      }
      else throw new CarteException("erreur : ("+coul+") n'existe pas");
    }
    public Carte(NomCarte nom, CouleurCarte couleurCarte) {
        this.nom = nom;
        this.couleurCarte = couleurCarte;
    }
    
    

    protected void setForce(short force) {
        this.force = force;
    }
    

    public static enum NomCarte {

        sept, huit, neuf, dix, valet, dame, roi, as;

        public String abv() {
            switch (this) {
                case as:
                    return "As";
                case roi:
                    return "Ro";
                case dame:
                    return "Da";
                case valet:
                    return "Va";
                case dix:
                    return "10";
                case neuf:
                    return " 9";
                case huit:
                    return " 8";
                case sept:
                    return " 7";
                    default:return null;
            }
        }

    }

    public static enum CouleurCarte {

       coeur, pique,  carreau, trefle;

        @Override
        public String toString() {
            switch (this) {
                case carreau:
                    return "" + CARREAU;
                case coeur:
                    return "" + COEUR;
                case trefle:
                    return "" + TREFLE;
                case pique:
                    return "" + PIQUE;
                    default:return null;
            }

        }

    }

    @Override
    public String toString() {
        return this.nom.abv()+this.couleurCarte;

    }

    public short getForce() {
        return force;
    }

    public NomCarte getNom() {
        return nom;
    }

    public CouleurCarte getCouleurCarte() {
        return couleurCarte;
    }
    
    public abstract int getPoint();
    
    

}
