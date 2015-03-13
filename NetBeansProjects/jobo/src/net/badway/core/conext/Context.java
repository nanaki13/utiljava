/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.core.conext;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class Context {
    private Context(){}
    private final Map<String,Object> appObjects = new HashMap<>(20);
    
    public <T> T get(){
        return (T) appObjects;
    }
    public <T> void set(String id , T t){
        appObjects.put(id, t);
    }
    
    void init(){
        
    }
}
