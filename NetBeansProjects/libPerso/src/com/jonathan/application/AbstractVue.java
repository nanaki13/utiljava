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
public abstract class AbstractVue implements Vue{
    protected Controleur controleur;

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
    
}
