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
public class Point {
    public double x;
    public double y;
    
    public Point(){
        
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    @Override
     public Point clone(){
        return new Point(x, y);
    }
    
    public void set(Point p){
        set(p.x, p.y);
    }
    public void bouge(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }
    public void bouge(Vecteur v){
        this.x += v.x;
        this.y += v.y;
    }
    public double distance(double x, double y){
        return Math.sqrt((this.x - x)*(this.x - x) + (this.y - y)*(this.y - y));
    }
    
    public double distance(Point p){
        return distance(p.x, p.y);
    }
    
    
    public static Point millieu(Point p1 , Point p2){
        return new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
    }
    
     @Override
    public String toString() {
        return "Point2D{" + "x=" + x + ", y=" + y + '}';
    }
    
}
