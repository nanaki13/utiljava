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
public class Rectangle  {
    public Point centre;
//    public int y;
    private double demiLargueur;
    private double demiHauteur;
    private double largueur;
    private double hauteur;

    public Rectangle(double x, double y, double demiLargueur, double demiHauteur) {
        this(new Point(x, y), demiLargueur,demiHauteur);
    }

    public Rectangle(Point p, double demiLargueur, double demiHauteur) {
        this.centre = p;
        this.demiLargueur = demiLargueur;
        this.demiHauteur = demiHauteur;
        largueur = 2 * demiLargueur;
        hauteur = 2 * demiHauteur;
    }
    
    
    public boolean isIn(Point p){
        return p.x >= this.centre.x - demiLargueur && p.x <= this.centre.x + demiLargueur && p.y >= this.centre.y  - demiHauteur  && p.y<= this.centre.y + demiHauteur;
    }
    public double  getHauteur(){
        return hauteur;
    }
    
     public double  getLargueur(){
        return largueur;
    }
    
    public double getDemiDiagonal(){
        return Math.sqrt(demiHauteur * demiHauteur + demiLargueur * demiLargueur);
    }
    public Point getXminYminSommet(){
        return new Point(centre.x - demiLargueur, centre.y - demiHauteur);
    }
    public Point getXmaxYminSommet(){
        return new Point(centre.x + demiLargueur, centre.y - demiHauteur);
    }
    public Point getXminYmaxSommet(){
        return new Point(centre.x - demiLargueur, centre.y + demiHauteur);
    }
    public Point getXmaxYmaxSommet(){
        return new Point(centre.x + demiLargueur, centre.y + demiHauteur);
    }
    public Segment[] getSegments(){
        Segment[] ss = new Segment[4];
        Point xminYminSommet = getXminYminSommet();
        Point xmaxYminSommet = getXmaxYminSommet();
        Point xmaxYmaxSommet = getXmaxYmaxSommet();
        Point xminYmaxSommet = getXminYmaxSommet();
        ss[0] = new Segment(xminYminSommet, xmaxYminSommet);
        ss[1] = new Segment(xmaxYminSommet, xmaxYmaxSommet);
        ss[2] = new Segment(xmaxYmaxSommet, xminYmaxSommet);
        ss[3] = new Segment(xminYmaxSommet, xminYminSommet);
        return ss;
    }

    public double getDemiLargueur() {
        return demiLargueur;
    }
    
    public double getDemiHauteur() {
        return demiHauteur;
    }
}
