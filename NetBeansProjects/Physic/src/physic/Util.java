/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physic;

/**
 *
 * @author jonathan
 */
public class Util {
    private Util(){};
    public static int[] getTabInt(int start , int stop, int lenth){
        int[]  ret = new int[lenth];
        int pas = (stop - start)/lenth;
        
        for(int i = start, j=0 ; i < stop ; i+=pas,j++){
            ret[j] = i;
        }
        return ret;
    }
    
}
