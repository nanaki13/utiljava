package com.jonathan.metier;

import java.util.List;

public class MembreFilm extends Personne {

    private List<Participation> participations;

    private List<RecompenseFilmDate> recompenses;

    public MembreFilm() {
    }

    public MembreFilm(List<Participation> participations, List<RecompenseFilmDate> recompenses) {
        this.participations = participations;
        this.recompenses = recompenses;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<RecompenseFilmDate> getRecompenses() {
        return recompenses;
    }

    public void setRecompenses(List<RecompenseFilmDate> recompenses) {
        this.recompenses = recompenses;
    }

    
    public List<RecompenseFilmDate> getRecompense() {
        return recompenses;
    }

    public void setRecompense(List<RecompenseFilmDate> recompenses) {
        this.recompenses = recompenses;
    }

}
