/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrie;

/**
 *
 * @author jonathan
 */
public class Cercle implements Forme{
    public Point centre;
    public double rayon;

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }
    
    public Point getPointInDirection(double x, double y){
        double norme = Math.sqrt(x*x + y*y);
        double cos = x / norme;
        double sin = y / norme;  
        return new Point(centre.x + cos * rayon, centre.y + sin * rayon);
    }
    
    public Point getXpositivePoint(){
        return new Point(centre.x + rayon, centre.y);
    }
    
    public Point getXnegaitvePoint(){
        return new Point(centre.x - rayon, centre.y);
    }
    
    public Point getYpositivePoint(){
        return new Point(centre.x, centre.y + rayon);
    }
    
    public Point getYnegaitvePoint(){
        return new Point(centre.x, centre.y  - rayon);
    }
    
    public Point getPointOn(double angle ){
        return new Point(rayon * Math.cos(angle), rayon * Math.sin(angle));
    }

    @Override
    public Point getCentre() {
        return centre;
    }
    

}
