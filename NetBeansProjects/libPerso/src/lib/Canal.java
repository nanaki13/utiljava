/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author jonathan
 */
public interface Canal<T> {
    public T emet();
    
    public void recoit( T t );
}
