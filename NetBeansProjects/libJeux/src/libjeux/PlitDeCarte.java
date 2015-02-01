/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class PlitDeCarte {

    List<Carte> plit = new LinkedList<Carte>();
    private Carte powerFull;
//    private Carte.CouleurCarte atout;
//    private Carte.CouleurCarte demande;
    private JoueurInterface meneur;
    private int joueurCount = 0;
    private int point = 0;
    private final CompCarte compCarte;

    public PlitDeCarte() {
        compCarte = new CompCarte();
    }

    public void add(Carte c, JoueurInterface j) {
//        System.out.println("c : "+c+" j:"+j);
        plit.add(c);
//        joueurs.add(j);
        if (joueurCount == 0) {
            this.powerFull = c;
            this.meneur = j;
           
            compCarte.setDemande(c.getCouleurCarte());
        }
        if (compCarte.compare(c, this.powerFull) > 0) {
            this.powerFull = c;
            this.meneur = j;
        }
        point += c.getPoint();
        joueurCount++;
    }

    public PointPlit count() {
        joueurCount = 0;

        PointPlit pointPlit = new PointPlit(this.meneur, point + 1);

        point = 0;
        plit.clear();
        meneur = null;
       
        powerFull = null;
        compCarte.setDemande(null);
        return pointPlit;

    }

    public void setAtout(Carte.CouleurCarte atout) {
        compCarte.setAtout(atout);
    }

//    public void setDemande(Carte.CouleurCarte demande) {
//        compCarte.setDemande(demande);
//    }

    public List<Carte> whatCanPlay(List<Carte> main, JoueurInterface j) {
        List<Carte> ret = new LinkedList<Carte>();
//        System.out.println("meneur =" +meneur);
        
//           System.out.println("j =" +j);
        if(meneur == null){
            ret.addAll(main);
            return ret;
        }
        for (Carte c : main) {
            if (c.getCouleurCarte() == compCarte.getDemande()) {
                ret.add(c);
            }
        }
        if (ret.isEmpty()) {
            for (Carte c : main) {
                if (c.getCouleurCarte() == compCarte.getAtout()) {
                    ret.add(c);
                }
            }
            if ((meneur!=null && meneur.getEquipe().equals(j.getEquipe())) || compCarte.getDemande() == compCarte.getAtout()) {
                for (Carte c : main) {
                    if (c.getCouleurCarte() != compCarte.getAtout()) {
                        ret.add(c);
                    }
                }
            }
        } 
        return ret;
        
    }

    @Override
    public String toString() {
        return "PlitDeCarte{" + "plit=" + plit + ", meneur=" + meneur + ", point=" + (point + 1) + '}';
    }

}
