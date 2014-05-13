/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib;

import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class Balise {
    private String nom;
    private ArrayList<String> attribues;
    private ArrayList<String> valeurs;
    private ArrayList<Balise> baliseDedans;
    private int indentation;

    public Balise(String nom) {
        this.nom = nom;
        attribues = new ArrayList<>();
        valeurs  = new ArrayList<>();
        baliseDedans=null;
        indentation=0;
    }
    public void insert(Balise balise){
        baliseDedans.add(balise);
        balise.indentation++;
    }

    public void ajoutAttr(String nom , String valeur){
        this.valeurs.add(valeur);
         this.attribues.add(nom);
    }
    
    public String chaineAttr(){
        String retourString = "";
        for (int i = 0; i < attribues.size(); i++) {
            String nomAttr = attribues.get(i);
            String valeur = valeurs.get(i);
            retourString += nomAttr+"=\""+valeur+"\" ";
        }
        return retourString;
    }
    public String stringOuverture(){
        String retour;
        retour = "< "+nom+" "+chaineAttr()+">";
        return retour;
    }
    public String stringFermeture(){
       String retour;
        retour = "</"+nom+ ">";
        return retour; 
    }
    
    
}
