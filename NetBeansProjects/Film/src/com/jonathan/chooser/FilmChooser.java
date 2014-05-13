/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.chooser;

import com.jonathan.lib.collections.Chooser;
import com.jonathan.metier.Film;

/**
 *
 * @author jonathan
 */
public class FilmChooser extends Chooser<Film>{

    private String mot;
    @Override
    public boolean choose(Film o) {
        return o.getTitre().contains(mot);
    }
    

    
}
