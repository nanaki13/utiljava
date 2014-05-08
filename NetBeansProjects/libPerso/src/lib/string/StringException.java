/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib.string;

/**
 *
 * @author jonathan
 */
public class StringException extends Exception {

    private int index;
    private char cchar;
    public StringException(String msg ,int index ,char cchar ) {
        super(msg);
        this.index = index;
        this.cchar = cchar;
      
    } 

    public int getIndex() {
        return index;
    }

    public char getCchar() {
        return cchar;
    }
    
}
