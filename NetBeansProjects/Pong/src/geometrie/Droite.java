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
public class Droite {
    private Double a ;
    private Double b ;
    private double x0 ;
    private double y0 ;
    private boolean horizontal = false;
    private boolean vertical = false;
    private boolean undefine = false;
    public Droite(Double a, Double b){
        this.a = a;
        this.b = b;
        if( a==0){
            horizontal = true;
            y0 = b;
        }
        if( a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY){
            vertical = true;
            undefine = true;
        }
    }
    public Droite(double x0){
        vertical = true;
        this.x0 = x0;
    }
    public Droite(Point p1, Point p2 ){
        if(p1.x == p2.x ){
            x0 = p1.x;
            if(p1.y==p2.y)
                undefine = true;
            vertical = true;
        }
        else{
            a = (p1.y - p2.y)/(p1.x - p2.x);
            
            b = (p1.x*p2.y - p2.x*p1.y)/(p1.x - p2.x);
            if( a == 0){
                horizontal = true;
                y0 = b;
            }
        }
    }
    public Double image( double x){
        if( b == null){
            return null;
        } else {
            return a * x + b;
        }
    }
    
    public Double antecedant( double y){
        if(vertical){
            return x0;
        }
        else{
            return (y - b)/a;
        }
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isVertical() {
        return vertical;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }
    
    
    
    public boolean isOnMe(Point p){
        if(horizontal){ 
            return p.y == b;
        } else if(vertical){
            return p.x == x0;
        }
        else{
            return (image(p.x) == p.y);
        }
    }
    
    public String getAffineString(){
        if(undefine){
            return "indéfinit";
        }
        if(horizontal){
            return " y = "+b;
        }else if(vertical){
            return " x = "+x0;
        }else{
            String A = ( a == 1)? "" : a.toString()+" * ";
            String B = ( b == 0 || b == -0)? "" : (( b > 0) ? "+ " : "")+ b.toString();
            return "y = " +A + "x "+B;
        }
    }
    

    double getX0() {
        return x0;
    }
    
    public static Droite getIdentite(){
        return new Droite(1d, 0d);
    }
    public static Droite getHorizontal(double y0){
        return new Droite(0d, y0);
    }
    public static Droite getVertical(double x0){
        return new Droite(x0);
    }
    
    
}
