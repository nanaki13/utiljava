/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jonathan
 */
public abstract class AbstractContext implements Context,Map<String,Object>{
    private final Map<String , Object> contextObjects = new HashMap<>();   

    
    @Override
    public int size() {
        return contextObjects.size();
    }

    @Override
    public boolean isEmpty() {
         return contextObjects.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
         return contextObjects.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return contextObjects.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return contextObjects.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return contextObjects.put(key,value);
    }

    @Override
    public Object remove(Object key) {
        return contextObjects.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        contextObjects.putAll(m);
    }

    @Override
    public void clear() {
        contextObjects.clear();;
    }

    @Override
    public Set<String> keySet() {
        return contextObjects.keySet();
    }

    @Override
    public Collection<Object> values() {
         return contextObjects.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return contextObjects.entrySet();
    }

    @Override
    public String toString() {
        return "object du context : \n"+contextObjects.toString(); 
    }

    @Override
    public <T> T getObject(String name) {
        return (T) get(name);
    }

    @Override
    public Map<String,Object> getAllData() {
        return  contextObjects;
    }
     
    
        
}
