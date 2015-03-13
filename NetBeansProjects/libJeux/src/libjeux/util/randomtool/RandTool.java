/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.util.randomtool;

import java.util.List;
import java.util.Random;

/**
 *
 * @author jonathan
 */
public class RandTool {
    private final static Random random  = new Random();
     

    public static <T>  T random(List<T> list) {
        
        return list.isEmpty() ? null : list.get(random.nextInt(list.size()));
    }
    
}
