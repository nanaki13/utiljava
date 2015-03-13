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
public interface  Vue {

    public void setControleur(Controleur controleur);
    public void receiveData(Object data);
    public void show();
    
    
}
