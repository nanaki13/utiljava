/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.metier;

import java.util.List;

/**
 *
 * @author jonathan
 */
public class Participation {
    private List<Film> films;
    private Metier metier;

    public Participation(List<Film> films, Metier metier) {
        this.films = films;
        this.metier = metier;
    }

    public Participation() {
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Metier getMetier() {
        return metier;
    }

    public void setMetier(Metier metier) {
        this.metier = metier;
    }
    
    

    
    
    
    
}
