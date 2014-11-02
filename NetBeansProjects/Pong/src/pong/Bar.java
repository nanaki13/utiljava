/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import geometrie.Movable;
import geometrie.Point;
import geometrie.Rectangle;
import geometrie.Vecteur;

/**

import geometrie.Movable;
import geometrie.Point;
import geometrie.Rectangle;
import geometrie.Vecteur;
 *
 * @author jonathan
 */
public class Bar extends Rectangle implements Movable{

    public Bar(double x, double y, double demiLargueur, double demiHauteur) {
        super(x, y, demiLargueur, demiHauteur);
    }


    public Bar(Point p, double demiLargueur, double demiHauteur) {
        super(p, demiLargueur, demiHauteur);
    }
    

    @Override
    public void move(Vecteur v) {
        centre.bouge(v);
    }
    
}
