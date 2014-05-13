/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jonathan
 * @param <T>
 */
public abstract class Chooser<T> {

    public abstract boolean choose(T o);

    public static <U> List<U> choose(Collection<U> listIn , Chooser<U> chooser ) {
        List<U> list = new ArrayList<>();
        for(U u : listIn){
            if(chooser.choose(u))
                list.add(u);
        }
        return list;
    }
    
    public static <U> List<U> chooseAndOrder(Collection<U> listIn ,
            Chooser<U> chooser , Comparator<U> comparator ) {
        List<U> list = choose(listIn, chooser);
        Collections.sort(list, comparator);
        return list;
    }

}
