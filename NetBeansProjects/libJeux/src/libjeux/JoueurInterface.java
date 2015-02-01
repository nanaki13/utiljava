/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.util.List;

/**
 *
 * @author jonathan
 */
public interface JoueurInterface {
    public String getName();
    public void setCartes(List<Carte> cartes);
    public void carteJouer(Carte c, JoueurInterface j,Integer numTour );
    public Carte  joux();
//    public void setOrdre(short ordre);
//    public void carteJouer(Carte c, short ordre);
    Equipe  getEquipe();
    void setEquipes(Equipe mine,Equipe other);
//    public void setAtout(Carte.CouleurCarte atout) ;
     public Carte.CouleurCarte chooseAtout();
     JoueurInterface getEquipier();

    public Carte.CouleurCarte chooseAtoutEnFace();

    public void message(String string);

    public void setAtout(Carte.CouleurCarte atout, String string);
    public void setId(Integer id);
    public Integer getId();
}
