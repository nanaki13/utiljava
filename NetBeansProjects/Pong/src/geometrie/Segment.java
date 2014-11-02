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
public class Segment extends Droite implements Forme{
    public Point p1;
    public Point p2;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    public Segment(Point p1, Point p2) {
        super(p1, p2);
        this.p1 = p1;
        this.p2 = p2;
        minX = Math.min(p1.x, p2.x);
        maxX = Math.max(p1.x, p2.x);
        minY = Math.min(p1.y, p2.y);
        maxY = Math.max(p1.y, p2.y);
    }

    @Override
    public boolean isOnMe(Point p) {
        return p.x >= minX && p.x <= maxX  && p.y >= minY && p.y <= maxY && super.isOnMe(p);
    }

    @Override
    public Point getCentre() {
        return Point.millieu(p1, p2);
    }
    
    
    
}
