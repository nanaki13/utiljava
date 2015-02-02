/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.object;

import java.io.Serializable;

/**
 *
 * @author jonathan
 */
public class Equipe implements Serializable {
    JoueurInterface joueur1;
    JoueurInterface joueur2;
    private int score;
    private int id;
    private String name;
    private int scoreTotal = 0;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Equipe){
            return this.id == ((Equipe)obj).id;
        }
        return super.equals(obj); 
    }

    
    public Equipe(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void finManche(){
        if(score > 34){
            scoreTotal += score -34;  
        }
        score = 0;
    }

    
    public void setJoueur1(JoueurInterface joueur1) {
        this.joueur1 = joueur1;
    }

    public void setJoueur2(JoueurInterface joueur2) {
        this.joueur2 = joueur2;
    }

    String getName() {
        return name;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    @Override
    public String toString() {
        return "Equipe{" + "name=" + name + '}';
    }

    public JoueurInterface getJoueur1() {
        return joueur1;
    }

    public JoueurInterface getJoueur2() {
        return joueur2;
    }
    
    
    
}
