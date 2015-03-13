/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.util.search;

import java.util.LinkedList;
import java.util.List;
import libjeux.util.Pair;

/**
 *
 * @author jonathan
 */
public class Search {
     public static <T> List<T> findMax(Pair<T, Integer>... coulPoint) {
        List<T> ret = new LinkedList<T>();
        Integer max = null;
        for (Pair<T, Integer> p : coulPoint) {
            if (max == null) {
                max = p.getTwo();
            } else {
                max = Math.max(max, p.getTwo());
            }
        }
        for (Pair<T, Integer> p : coulPoint) {
            if (max == p.getTwo()) {
                ret.add(p.getOne());
            }
        }
        return ret;
    }
}
