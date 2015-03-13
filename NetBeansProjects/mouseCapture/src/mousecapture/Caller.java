/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

/**
 *
 * @author jonathan
 * @param <T>
 */
public abstract class Caller<T> {
    protected T o;
    public Caller(T o){
        this.o=o;
    }
    public abstract Object call();
    
    @Override
    public String toString(){
       
        return call().toString();
    }
}
