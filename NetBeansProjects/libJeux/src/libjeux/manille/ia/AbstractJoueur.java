/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.ia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import libjeux.manille.object.Carte;
import libjeux.manille.object.Equipe;
import libjeux.manille.object.JoueurInterface;
import libjeux.manille.object.PlitDeCarte;

/**
 *
 * @author jonathan
 */
public abstract class AbstractJoueur implements JoueurInterface{
    
    private int id;
    protected String name;
    protected transient List<Carte> cartes;
    protected Equipe equipe;
    protected Equipe other;
    protected JoueurInterface equipier;
    protected Carte.CouleurCarte atout;
    protected final transient PlitDeCarte plitDeCarte;
    protected boolean  weGo;
    private Map<Integer , JoueurInterface > jouersByid;
    
    public AbstractJoueur(String jonathan, int id) {
        this();
        this.name = jonathan;
        this.id = id;
    }

    public AbstractJoueur(int id) {
        this();
        this.id = id;
    }
    
    public AbstractJoueur(String name) {
        this();
        this.name = name;
    }

    public AbstractJoueur() {

        this.plitDeCarte = new PlitDeCarte();
        

    }
    
    public void carteJouer(Carte c, JoueurInterface j,Integer numTour ){
        plitDeCarte.add(c, j);
         j = findJoueur(j.getId());
//        System.out.println("findJ = "+j);
        plitDeCarte.add(c, j);
        if (numTour == 3) {
            plitDeCarte.count();
        }
    }
    @Override
    public String toString() {
        return getName();
    }
    

    public String getName() {
        return name;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipes(Equipe mine, Equipe other) {
        if(mine.getJoueur1().equals(this)){
            this.equipier = mine.getJoueur2();
        }else{
            this.equipier = mine.getJoueur1();
        }
        this.equipe = mine;
        this.other = other;
        jouersByid = new HashMap<Integer, JoueurInterface>();
        jouersByid.put(id, this);
        jouersByid.put(equipier.getId(), equipier);
        jouersByid.put(other.getJoueur1().getId(), other.getJoueur1());
        jouersByid.put(other.getJoueur2().getId(), other.getJoueur2());
    }

    

    public JoueurInterface getEquipier() {
        return equipier;
    }

  

    public void message(String string) {
        System.out.println(name+" : "+string);
    }

    public void setAtout(Carte.CouleurCarte atout, String string) {
        this.atout = atout;
        plitDeCarte.setAtout(atout);
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public Integer getId() {
        return id;
    }
    public void setTourDe(int idJoueur){
        JoueurInterface get = jouersByid.get(idJoueur);
        if(get.equals(this) || get.equals(equipier)){
            weGo = true;
        }else{
            weGo = false;
        }
    }
      @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractJoueur other = (AbstractJoueur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
     protected JoueurInterface findJoueur(int id) {
        if (equipe.getJoueur1().getId() == id) {
            return equipe.getJoueur1();
        }
        if (equipe.getJoueur2().getId() == id) {
            return equipe.getJoueur2();
        }
        if (other.getJoueur1().getId() == id) {
            return other.getJoueur1();
        }
        if (other.getJoueur2().getId() == id) {
            return other.getJoueur2();
        }
        return null;

    }
    
}
