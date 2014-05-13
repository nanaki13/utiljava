package com.jonathan.metier;

import java.util.Date;

public class Personne{

	private int id;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private Date dateDeMort;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Date getDateDeMort() {
        return dateDeMort;
    }

    public void setDateDeMort(Date dateDeMort) {
        this.dateDeMort = dateDeMort;
    }
        
}