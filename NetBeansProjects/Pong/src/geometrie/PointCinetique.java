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
public class PointCinetique extends Point{

    public Vecteur vitesse;
    public void move(){
        bouge(vitesse);
    }

    public PointCinetique(int x ,int y , Vecteur vitesse) {
        this.x = x;
        this.y=y;
        this.vitesse = vitesse;
    }
    
}
