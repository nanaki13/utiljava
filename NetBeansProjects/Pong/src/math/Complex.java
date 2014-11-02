/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package math;

/**
 *
 * @author jonathan
 */
public class Complex {
    
    public double re;
    public double im;
    public static final Complex i = new Complex(0, 1);
    public static final Complex un = new Complex(1, 0);

    public Complex(double re, double im ) {
        this.im = im;
        this.re = re;
    }
    
    public static Complex add(Complex c1, Complex c2){
        return new Complex(c1.re + c2.re,c1.im + c2.im);
    }
    public Complex add(Complex c){
        return add(this, c);
    }
    public static Complex getOppose(Complex c){
        return new Complex(-c.re, -c.im);
    }
    public Complex addToMe(Complex c){
        re += re + c.re;
        im +=im + c.im;
        return this;
    }
    public static Complex minus(Complex c1, Complex c2){
        return add(c1 ,getOppose(c2) );
    }
    public static Complex mult(Complex c1, Complex c2){
        return new Complex(c1.re * c2.re - c1.im * c2.im,c1.re * c2.im + c1.im * c2.re);
    }
    
    public  Complex mult(Complex c){
        return new Complex(re * c.re - im * c.im,re * c.im + im * c.re);
    }
    public  Complex mult(double reel){
        return new Complex(reel* re,reel * im);
    }
    
    @Override
    public String toString(){
        return re + " + " + im +" * i";
    }
    public static void main(String [] args){
        Complex c1 = new Complex(1, 0);
        Complex c2 = new Complex(1, 0);
        c1.getClass().getMethods()[0].getGenericParameterTypes()[0].getTypeName();
        System.out.println(mult(i,un).add(i).mult(3));
    }
}
