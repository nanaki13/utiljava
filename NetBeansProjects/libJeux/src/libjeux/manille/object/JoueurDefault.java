/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.object;

import libjeux.manille.object.PlitDeCarte;
import libjeux.manille.object.JoueurInterface;
import libjeux.manille.object.Carte;
import libjeux.manille.vue.VuePlateauConsole;
import libjeux.manille.vue.VuePlateau;
import libjeux.manille.object.Equipe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libjeux.manille.ia.AbstractJoueur;
import static libjeux.manille.object.Carte.CouleurCarte;

/**
 *
 * @author jonathan
 */
public class JoueurDefault extends AbstractJoueur implements JoueurInterface, Serializable {


    private transient VuePlateau vue;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public JoueurDefault(String jonathan, int id) {
        super(jonathan, id);
    }

    public JoueurDefault(int id) {
        super(id);
    }

    public JoueurDefault(String name) {
        super(name);
    }

    public JoueurDefault() {
        super();
        vue = new VuePlateauConsole();
    }

    

    @Override
    public String getName() {
        if (name == null) {
            System.out.println("entre votre nom : ");
            name = readInLine();
        }
        return name;
    }

    @Override
    public void setCartes(List<Carte> cartes) {
        super.setCartes(cartes);
        
        System.out.println(name + " : ");
        vue.afficheMain(cartes);
        System.out.println();

//            v.afficheMain(mainJ2E1.toArray(new Carte[0]));
//            System.out.println("");
//            v.afficheMain(mainJ1E2.toArray(new Carte[0]));
//            System.out.println("");
//            v.afficheMain(mainJ2E2.toArray(new Carte[0]));
    }

    public Carte joux() {
        System.out.println(name + ", choisir une carte : \n");
        List<Carte> whatCanPlay = plitDeCarte.whatCanPlay(cartes, this);
        vue.afficheChoix(cartes, whatCanPlay);
        String readInLine;
        int parseInt;
        Carte get = null;
        do {
            try {
//                readInLine = readInLine();
                readInLine = "0";
                parseInt = Integer.parseInt(readInLine);
                if (parseInt > -1 && parseInt < whatCanPlay.size()) {
                    get = whatCanPlay.get(parseInt);
                }
            } catch (NumberFormatException e) {
                System.out.println("recommencer");
            }

        } while (get == null);
        cartes.remove(get);
        return get;

    }



    public void setAtout(Carte.CouleurCarte atout, String string) {
        message(string);

        plitDeCarte.setAtout(atout);
    }

    public Carte.CouleurCarte chooseAtout() {
        System.out.println(getName() + " : choisir l'atout (pi,co,ca,tr): \n");
        String readInLine = readInLine();
//        System.out.println("readInLine : " + readInLine);
        CouleurCarte atout;
        while (true) {
            if ("pi".equals(readInLine)) {
                atout = Carte.CouleurCarte.pique;
                return atout;

            } else if ("co".equals(readInLine)) {
                atout = Carte.CouleurCarte.coeur;
                return atout;
            } else if ("ca".equals(readInLine)) {
                atout = Carte.CouleurCarte.carreau;
                return atout;
            } else if ("tr".equals(readInLine)) {
                atout = Carte.CouleurCarte.trefle;
                return atout;
            } else {
                return null;
            }

        }

    }

    public synchronized String readInLine() {

        try {
            String readLine = br.readLine();

            return readLine;
        } catch (IOException ex) {
            Logger.getLogger(JoueurDefault.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    

    @Override
    public void carteJouer(Carte c, JoueurInterface j, Integer numTour) {
        super.carteJouer(c, j, numTour);
        vue.afficheCarteJouer( c,  j,  numTour);
      
    }

    public JoueurInterface getEquipier() {
        if (equipe.getJoueur1().equals(this)) {
            return equipe.getJoueur2();
        } else {
            return equipe.getJoueur1();
        }
    }

    public CouleurCarte chooseAtoutEnFace() {
        System.out.println(getName() + " : choisir l'atout (pi,co,ca,tr): \n");
        String readInLine = readInLine();
        CouleurCarte atout;
//        System.out.println("readInLine : " + readInLine);
        while (true) {
            if ("pi".equals(readInLine)) {
                atout = Carte.CouleurCarte.pique;
                return atout;

            } else if ("co".equals(readInLine)) {
                atout = Carte.CouleurCarte.coeur;
                return atout;
            } else if ("ca".equals(readInLine)) {
                atout = Carte.CouleurCarte.carreau;
                return atout;
            } else if ("tr".equals(readInLine)) {
                atout = Carte.CouleurCarte.trefle;
                return atout;
            } else {
                System.out.println("recommencer");
            }
            readInLine = readInLine();

        }

    }

  

    

  

    
    
    

   
}


