/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.metier;

import java.util.Date;

/**
 *
 * @author jonathan
 */
public class RecompenseFilmDate {
    
    private Recompense recompense;
    private Film film;
    private Date date;

    public Recompense getRecompense() {
        return recompense;
    }

    public void setRecompense(Recompense recompense) {
        this.recompense = recompense;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
