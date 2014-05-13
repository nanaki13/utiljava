/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.lib.collections;

/**
 *
 * @author jonathan
 */
public abstract class ChooserText<T> extends Chooser<T>{

    protected String text;

    public void setText(String text) {
        this.text = text;
    }
    
    
}
