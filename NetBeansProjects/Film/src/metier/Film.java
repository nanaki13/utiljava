package metier;

import java.util.ArrayList;
import java.util.Date;

public class Film {

    private int id;
    private String titre;
    private Date dateRealisation;
    private Pays origine;
    private Pays lieuxDeTournage;
    private String synopsis;
    private ArrayList<Realisateur> realisateurs;
    private ArrayList<Acteur> acteurs;

    private Genre genre;
    private int duree;
    private String chemin;

    public Film(int id) {
        this.id = id;
    }

    public Film() {

    }

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(ArrayList<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    public int getId() {
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

    public ArrayList<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(ArrayList<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

}
