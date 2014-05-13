package com.jonathan.metier;

import java.util.ArrayList;



public class Realisateur extends Acteur{

    private ArrayList<Film> filmsRealise;

    public ArrayList<Film> getFilmsRealise() {
        return filmsRealise;
    }

    public void setFilmsRealise(ArrayList<Film> filmsRealise) {
        this.filmsRealise = filmsRealise;
    }
    
}