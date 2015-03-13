/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.util;

/**
 *
 * @author jonathan
 */
public class Pair<T,U> {
    private T one;
    private U two;

    public Pair(T one, U two) {
        this.one = one;
        this.two = two;
    }

    public Pair() {
    }

    public T getOne() {
        return one;
    }

    public void setOne(T one) {
        this.one = one;
    }

    public U getTwo() {
        return two;
    }

    public void setTwo(U two) {
        this.two = two;
    }
    
}
