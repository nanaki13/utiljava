/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie;

import math.Complex;

/**
 *
 * @author jonathan
 */
public class Vecteur {

    

    public double x;
    public double y;

    public Vecteur() {

    }

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vecteur(Point p1, Point p2) {
        this(p2.x - p1.x, p2.y - p1.y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vecteur clone() {
        return new Vecteur(x, y);
    }

    public void set(Vecteur p) {
        set(p.x, p.y);
    }

    public void add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public double norme() {
        return Math.sqrt(x * x + y * y);
    }

    public static Vecteur faitUnVecteur(Point p1, Point p2) {
        return new Vecteur(p2.x - p1.x, p2.y - p1.y);
    }

    public static double scalaire(Vecteur v1, Vecteur v2) {
        return v1.x * v2.x - v1.y * v2.y;
    }
    
    public static Vecteur add(Vecteur v1, Vecteur v2) {
        return new Vecteur(v1.x + v2.x, v1.y * v2.x) ;
    }
    public  Vecteur add(Vecteur v) {
        return add(this, v) ;
    }

    public double scailaire(Vecteur v) {
        return scalaire(this, v);
    }
    public boolean memeSens(Vecteur v1 , Vecteur v2){
        return scalaire(v1, v2)>0;
    }
    public boolean memeSens( Vecteur v){
        return memeSens(this, v);
    }
    public boolean estHortogo(Vecteur v1 , Vecteur v2){
        return scalaire(v1, v2) == 0;
    }
    public boolean estHortogo( Vecteur v){
        return estHortogo(this, v);
    }
    public static Vecteur[] unitVecteur(Droite d) {
        Vecteur v1;
        Vecteur v2;
        if (!d.isHorizontal() && !d.isVertical()) {
            double a = d.getA();
            double b = d.getB();
            double x1 = 1 / Math.sqrt(1 + a * a);
            double x2 = -x1;
            v1 = new Vecteur(x1, d.image(x1));
            v2 = new Vecteur(x2, d.image(x2));
            
        } else {
            if (d.isHorizontal()) {
                v1 = new Vecteur(1, 0);
                v2 = new Vecteur(-1, 0);
            }
            else{
                v1 = new Vecteur(0, 1);
                v2 = new Vecteur(0, -1);
            }
        }
        return new Vecteur[]{v1, v2};
    }
    
    public static Vecteur getSymetrique(Droite d, Vecteur v){
        Vecteur[] unitVecteur = unitVecteur(d);
        if(v.estHortogo(unitVecteur[0])){
            return v.getOppose();
        }
        else{
            Vecteur base;
            if(unitVecteur[0].memeSens(v)){
                base = unitVecteur[0];
            }
            else{
                base = unitVecteur[1];
            }
            Vecteur basem = multiplie(base , v.norme() * 2 );
            return add(basem, v.getOppose());
        }
    }
    public Vecteur getOppose(){
        return new Vecteur(-x,- y);
    }
    public void oppose(){
        x = -x;
        y = -y;
    }
    public static Vecteur multiplie(Vecteur v, double d) {
        return new Vecteur(d * v.x, d * v.y);
    }
    
    public static void main(String[] args){
        Droite identite = Droite.getIdentite();
        Vecteur v = new Vecteur(0, 1);
        Vecteur vs = getSymetrique(identite, v);
        System.out.println();
    }

    @Override
    public String toString() {
        return "Vecteur{" + "x=" + x + ", y=" + y + '}';
    }

    public void multipli(double d) {
        x *=d;
        y*=d;
    }

    public boolean isNull() {
        return x== 0d && y ==0d;
    }

    public void tourne(double d) {
        Complex c = new Complex(x, y);
        Complex rot  = new Complex(Math.cos(d), Math.sin(d));
        Complex mult = c.mult(rot);
        x = mult.re;
        y = mult.im;
    }
            
    

}
