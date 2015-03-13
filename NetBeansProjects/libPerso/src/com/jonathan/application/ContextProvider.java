/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application;

/**
 *
 * @author jonathan
 */
public class ContextProvider {
    private final Context context;

    public Context getContext() {
        return context;
    }

    

    public ContextProvider(Context context) {
        this.context = context;
    }
    
}
