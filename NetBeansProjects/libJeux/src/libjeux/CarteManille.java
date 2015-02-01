/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.io.Serializable;

/**
 *
 * @author jonathan
 */
public class CarteManille extends Carte implements Serializable {

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

    @Override
    public int getPoint() {
        return point;
    }

}
