/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static libjeux.Carte.CouleurCarte;

/**
 *
 * @author jonathan
 */
public class JoueurDefault implements JoueurInterface, Serializable {

    private transient List<Carte> cartes;
    private int id;
    private String name;
//    private Carte.CouleurCarte atout;
//    private short ordre;
    private transient VuePlateau vue;
//    transient int i = 0;
    private Equipe equipe;
//    private transient VuePlateauConsole v;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final transient PlitDeCarte plitDeCarte;
    private Equipe otherEquipe;

    JoueurDefault(String jonathan, int id) {
        this();
        this.name = jonathan;
        this.id = id;
    }

    JoueurDefault(int id) {
        this();
        this.id = id;
    }
    
     JoueurDefault(String name) {
        this();
        this.name = name;
    }

    JoueurDefault() {

        this.plitDeCarte = new PlitDeCarte();
        

    }

    public String getName() {
        if (name == null) {
            System.out.println("entre votre nom : ");
            name = readInLine();
        }
        return name;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
        vue = new VuePlateauConsole();
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

//    public void carteJouer(Carte c, JoueurInterface j) {
//    }
    public Equipe getEquipe() {
        return equipe;
    }

//    public void setEquipe(Equipe e) {
//        this.equipe = e;
//    }

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
    public String toString() {
        return getName();
    }

    public void carteJouer(Carte c, JoueurInterface j, Integer numTour) {
        vue.afficheCarteJouer( c,  j,  numTour);
       j = findJoueur(j.getId());
//        System.out.println("findJ = "+j);
        plitDeCarte.add(c, j);
        if (numTour == 3) {
            plitDeCarte.count();
        }
    }

    public JoueurInterface getEquipier() {
        if (equipe.joueur1.equals(this)) {
            return equipe.joueur2;
        } else {
            return equipe.joueur1;
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

    public void message(String string) {
//        if (this == equipe.joueur1)
        System.out.println(string);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
        final JoueurDefault other = (JoueurDefault) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setEquipes(Equipe mine, Equipe other) {
        System.out.println("setEquipes : this : "+this+" , equipe : "+mine+", othuer : "+other);
        this.equipe = mine;
        this.otherEquipe = other;

    }
    
    

    private JoueurInterface findJoueur(Integer id) {
        if (equipe.joueur1.getId() == id) {
            return equipe.joueur1;
        }
        if (equipe.joueur2.getId() == id) {
            return equipe.joueur2;
        }
        if (otherEquipe.joueur1.getId() == id) {
            return otherEquipe.joueur1;
        }
        if (otherEquipe.joueur2.getId() == id) {
            return otherEquipe.joueur2;
        }
        return null;

    }
}


