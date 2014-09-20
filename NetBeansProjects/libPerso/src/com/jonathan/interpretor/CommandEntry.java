/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.interpretor;

/**
 *
 * @author jonathan
 */
public class CommandEntry<T,V> {
    private T arg;
    private V val;

    public T getArg() {
        return arg;
    }

    public void setArg(T arg) {
        this.arg = arg;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "CommandEntry{" + "arg=" + arg + ", val=" + val + '}';
    }
    
    
    
}
