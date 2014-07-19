package com.jonathan.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Film {

    private Integer id;
    private String titre;
    private Date dateRealisation;
    private Pays origine;
    private Pays lieuxDeTournage;
    private String synopsis;
    private List<MembreFilm> realisateurs;
    private List<MembreFilm> acteurs;

    private List<Genre> genres;
    private int duree;
    private List<String> chemins;

    public Film(Integer id) {
        this.id = id;
    }

    public Film() {

    }

    public List<MembreFilm> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<MembreFilm> acteurs) {
        this.acteurs = acteurs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public Pays getOrigine() {
        return origine;
    }

    public void setOrigine(Pays origine) {
        this.origine = origine;
    }

    public Pays getLieuxDeTournage() {
        return lieuxDeTournage;
    }

    public void setLieuxDeTournage(Pays lieuxDeTournage) {
        this.lieuxDeTournage = lieuxDeTournage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<MembreFilm> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(List<MembreFilm> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public List<String> getChemins() {
        return chemins;
    }
    
     public void  setChemins(List<String> chemins) {
        this.chemins = chemins;
    }
    
    public void addChemin(String chemin) {
        if(chemins == null){
            chemins = new ArrayList<>();
        }
        chemins.add(chemin);
        
    }
}
