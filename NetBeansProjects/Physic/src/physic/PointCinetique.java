/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physic;

/**
 *
 * @author jonathan
 */
public class PointCinetique {
    public Point2D point2D;
    public Vitesse vitesse;
    public void move(){
        point2D.x+=vitesse.dx;
        point2D.y+=vitesse.dy;
    }

    public PointCinetique(Point2D point2D, Vitesse vitesse) {
        this.point2D = point2D;
        this.vitesse = vitesse;
    }
    
}
