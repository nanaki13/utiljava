/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.util.Collection;

/**
 *
 * @author jonathan
 */
interface Id {
    public int getId();
    public void setId(int id);
    public static int getFreeId(Collection<?> id){
        int max = 1;
        for(Object  i : id){
            if(((Id)i).getId() == 0){
                ((Id)i).setId(max);
            }
            max = Math.max(max, ((Id)i).getId());
        }
        return max+1;
    }
   
    
}
