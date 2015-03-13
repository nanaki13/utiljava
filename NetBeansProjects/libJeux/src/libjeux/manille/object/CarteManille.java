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
public class CarteManille extends Carte implements Serializable {

    private static final CouleurCarte[] coules = Carte.CouleurCarte.values();
    private static final NomCarte[] noms = Carte.NomCarte.values();
    private static final CarteManille[][] jeux = new CarteManille[coules.length][noms.length];

    static {

        for (int coul = 0; coul < coules.length; coul++) {
            for (int nom = 0; nom < noms.length; nom++) {
                jeux[coul][nom] = new CarteManille(noms[nom], coules[coul]);
            }
        }
    }

    public static CarteManille get(int nom, int coul) {
        return jeux[coul][nom];
    }

    public static CarteManille get(Carte.NomCarte nom, Carte.CouleurCarte coul) {
        return jeux[coul.ordinal()][nom.ordinal()];
    }
    private int point;

    public CarteManille(Carte.NomCarte nomCarte, Carte.CouleurCarte couleurCarte) {
        super(nomCarte, couleurCarte);
        switch (nomCarte) {
            case sept:
                this.force = 0;
                this.point = 0;
                break;
            case huit:
                this.force = 1;
                this.point = 0;
                break;
            case neuf:
                this.force = 2;
                this.point = 0;
                break;
            case valet:
                this.force = 3;
                this.point = 1;
                break;
            case dame:
                this.force = 4;
                this.point = 2;
                break;
            case roi:
                this.force = 5;
                this.point = 3;
                break;
            case as:
                this.force = 6;
                this.point = 4;
                break;
            case dix:
                this.force = 7;
                this.point = 5;
        }
    }

    public CarteManille(String carte) throws CarteException {
        super(carte);
        switch (getNom()) {
            case sept:
                this.force = 0;
                this.point = 0;
                break;
            case huit:
                this.force = 1;
                this.point = 0;
                break;
            case neuf:
                this.force = 2;
                this.point = 0;
                break;
            case valet:
                this.force = 3;
                this.point = 1;
                break;
            case dame:
                this.force = 4;
                this.point = 2;
                break;
            case roi:
                this.force = 5;
                this.point = 3;
                break;
            case as:
                this.force = 6;
                this.point = 4;
                break;
            case dix:
                this.force = 7;
                this.point = 5;
        }
    }

    @Override
    public int getPoint() {
        return point;
    }

    public int compareTo(Carte o) {
        return this.force - o.force;
    }

}
