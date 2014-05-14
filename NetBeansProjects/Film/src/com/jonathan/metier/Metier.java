/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.metier;

/**
 *
 * @author jonathan
 */
public class Metier {
    private int id;
    private String nom;

    public Metier(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Metier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
