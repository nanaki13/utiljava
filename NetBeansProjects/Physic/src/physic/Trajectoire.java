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
public abstract class Trajectoire {
    public int t;
    public abstract int fx(int t);
    public abstract int fy(int t);
    public Point2D getPoint(int t){
        return new Point2D(fx(t), fy(t));
    }
    
    public Point2D[] getPoints(int[] t){
        Point2D[] ret = new Point2D[t.length];
        for(int i = 0 ; i < t.length ; i++){
            ret[i] = getPoint(t[i]);
        }
        return ret;
    }
}
