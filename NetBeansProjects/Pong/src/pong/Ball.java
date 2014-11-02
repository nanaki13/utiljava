/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import geometrie.Cercle;
import geometrie.Movable;
import geometrie.Point;
import geometrie.Vecteur;

/**

import geometrie.Cercle;
import geometrie.Movable;
import geometrie.Point;
import geometrie.Vecteur;
 *
 * @author jonathan
 */
public class Ball extends Cercle implements Movable{
    private Point[] points;
    public Ball(Point centre, double rayon, int fragment) {
        super(centre, rayon);
        Point o = new Point(0,0);
        points = new Point[fragment];
        int j = 0;
        for(double i = 0 ; i< 2*Math.PI; i+=2*Math.PI/fragment){
            Point p = new Point(Math.cos(i) * rayon, Math.sin(i) *rayon);
            Vecteur v = new Vecteur(o,centre);
            p.bouge(v);
            points[j] = p;
            j++;
        }
        
    }

    @Override
    public void move(Vecteur v) {
        centre.bouge(v);
        for(Point p : points){
            p.bouge(v);
        }
    }

    public Point[] getPoints() {
        return points;
    }
    
    
}
