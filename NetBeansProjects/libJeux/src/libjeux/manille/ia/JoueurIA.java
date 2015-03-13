/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import libjeux.manille.object.Carte;
import libjeux.manille.object.CarteManille;
import libjeux.manille.object.Equipe;
import libjeux.manille.object.JoueurInterface;
import libjeux.manille.vue.VuePlateauConsole;
import libjeux.util.Pair;
import libjeux.util.randomtool.RandTool;
import libjeux.util.search.Search;

/**
 *
 * @author jonathan
 */
public class JoueurIA extends AbstractJoueur implements JoueurInterface {

    private List<Carte> carreau = new ArrayList<Carte>(8);
    private List<Carte> pique = new ArrayList<Carte>(8);
    private List<Carte> trefle = new ArrayList<Carte>(8);
    private List<Carte> coeur = new ArrayList<Carte>(8);
    private int nbCoeur;
    private int nbTrefle;
    private int nbPique;
    private int nbCarreau;
    private int nbAtoutEnJeux = 0;

    private final Random random = new Random();
    private static final String[] names = {"Jean", "Paul", "Pierre", "Andre"};
    private VuePlateauConsole vue = new VuePlateauConsole();
    private MemosCarte memosCarte = new MemosCarte();

    public JoueurIA(String jonathan, int id) {
        super(jonathan, id);
        name = names[id % names.length];
    }

    public JoueurIA(int id) {
        super(id);
        name = names[id % names.length];
    }

    public JoueurIA(String name) {
        super(name);
        this.name = names[random.nextInt(names.length)];
    }

    public JoueurIA() {
        name = names[getId() % names.length];
        this.name = names[random.nextInt(names.length)];
    }

    @Override
    public void carteJouer(Carte c, JoueurInterface j, Integer numTour) {
        super.carteJouer(c, j, numTour);
        memosCarte.add(c);
        switch (c.getCouleurCarte()) {
            case carreau:
                carreau.add(c);
                break;
            case pique:
                pique.add(c);
                break;
            case coeur:
                coeur.add(c);
                break;
            case trefle:
                trefle.add(c);
                break;
        }
        if (c.getCouleurCarte() == atout) {
            nbAtoutEnJeux++;
        }

    }

    public Carte joux() {
        List<Carte> whatCanPlay = plitDeCarte.whatCanPlay(cartes, this);
        Carte get = null;
        boolean encoreAtout = encoreAtout();
        boolean iHaveAtout = IHaveAtout();
        if (weGo) {

            if (encoreAtout && iHaveAtout && (plitDeCarte.getMeneur() == null || !plitDeCarte.getMeneur().equals(equipier))) {
                List<Carte> myAtout = getMyAtout();
                Carte masterAtout = getMasterInColor(myAtout);
                if (masterAtout != null) {
                    return masterAtout;

                } else {
                    if (!myAtout.isEmpty()) {
                        return myAtout.get(myAtout.size() / 2);
                    }
                }
            }
        } else {

        }
        if (get == null) {
            if (plitDeCarte.getMeneur() == null || plitDeCarte.getMeneur().equals(equipier)) {
                get = getMasterNoAtout(whatCanPlay);
                if (get == null && plitDeCarte.getMeneur() != null) {
                    Carte master = plitDeCarte.getMaster();
                    if (memosCarte.isMasterInColor(master) && !encoreAtout) {
                        whatCanPlay.get(whatCanPlay.size() - 1);
                    }
                }
            } else if (!plitDeCarte.getMeneur().equals(equipier)) {
                get = whatCanPlay.get(0);
            }
        }

        if (get == null) {
            get = getMasterInColorNoAtout(whatCanPlay);
            if (get == null) {
                get = RandTool.random(sansAtout(whatCanPlay));
            }

        }
        if (get == null) {
            get = whatCanPlay.get(0);
        }

        remove(get);
        return get;
    }

    public List<Carte> sansAtout(List<Carte> ca) {
        LinkedList<Carte> ret = new LinkedList<Carte>();
        for (Carte c : ca) {
            if (c.getCouleurCarte() != atout) {
                ret.add(c);
            }
        }
        return ret;
    }

    public Carte getMasterInColor(List<Carte> whatCanPlay) {

        if (!whatCanPlay.isEmpty()) {
            Collections.sort(whatCanPlay);

            if (memosCarte.isMasterInColor(whatCanPlay.get(whatCanPlay.size() - 1))) {
                return whatCanPlay.get(whatCanPlay.size() - 1);
            }
        }
        return null;
    }

    public Carte getMasterInColorNoAtout(List<Carte> whatCanPlay) {

        if (!whatCanPlay.isEmpty()) {
            whatCanPlay = new LinkedList<Carte>(whatCanPlay);
            Iterator<Carte> iterator = whatCanPlay.iterator();
            while (iterator.hasNext()) {
                Carte c = iterator.next();
                if (c.getCouleurCarte() == atout) {
                    iterator.remove();
                }
            }
            Collections.sort(whatCanPlay);

            if (memosCarte.isMasterInColor(whatCanPlay.get(whatCanPlay.size() - 1))) {
                return whatCanPlay.get(whatCanPlay.size() - 1);
            }
        }
        return null;
    }

    public Carte.CouleurCarte chooseAtout() {
        if (nbCarreau > 3) {
            return Carte.CouleurCarte.carreau;
        } else if (nbTrefle > 3) {
            return Carte.CouleurCarte.trefle;
        } else if (nbCoeur > 3) {
            return Carte.CouleurCarte.coeur;
        } else if (nbPique > 3) {
            return Carte.CouleurCarte.pique;
        } else {
            return maxCoulPoint();
        }

    }

    public Carte.CouleurCarte chooseAtoutEnFace() {
        return Carte.CouleurCarte.carreau;
    }

    @Override
    public void setCartes(List<Carte> cartes) {
        super.setCartes(cartes);
        nbAtoutEnJeux = 0;
        System.out.print(name + "(" + equipe + ") : ");
        vue.afficheMain(cartes);
        for (Carte c : cartes) {
            switch (c.getCouleurCarte()) {
                case carreau:
                    nbCarreau++;
                    break;
                case coeur:
                    nbCoeur++;
                    break;
                case trefle:
                    nbTrefle++;
                    break;
                case pique:
                    nbPique++;
                    break;
            }
            if (c.getCouleurCarte().equals(atout)) {
                nbAtoutEnJeux++;
            }
        }
    }

    boolean encoreAtout() {
        return nbAtoutEnJeux != 8;
    }

    private Carte.CouleurCarte maxCoulPoint() {
        int countCoeur = 0;
        int countCarreau = 0;
        int countTrfle = 0;
        int countpique = 0;
        for (Carte c : cartes) {
            switch (c.getCouleurCarte()) {
                case carreau:
                    countCarreau += c.getPoint();
                    break;
                case coeur:
                    countCoeur += c.getPoint();
                    nbCoeur++;
                    break;
                case trefle:
                    countTrfle += c.getPoint();
                    nbTrefle++;
                    break;
                case pique:
                    countpique += c.getPoint();
                    nbPique++;
                    break;
            }

        }
        Pair<Carte.CouleurCarte, Integer> coulPointCoeur = new Pair<Carte.CouleurCarte, Integer>(Carte.CouleurCarte.coeur, countCoeur);
        Pair<Carte.CouleurCarte, Integer> coulPointCar = new Pair<Carte.CouleurCarte, Integer>(Carte.CouleurCarte.carreau, countCarreau);
        Pair<Carte.CouleurCarte, Integer> coulPointTr = new Pair<Carte.CouleurCarte, Integer>(Carte.CouleurCarte.trefle, countTrfle);
        Pair<Carte.CouleurCarte, Integer> coulPointPi = new Pair<Carte.CouleurCarte, Integer>(Carte.CouleurCarte.pique, countpique);

        return RandTool.random(Search.findMax(coulPointCoeur, coulPointTr, coulPointPi, coulPointCar));

    }

    private boolean IHaveAtout() {
        for (Carte c : cartes) {
            if (c.getCouleurCarte() == atout) {
                return true;
            }
        }
        return false;
    }

    private List<Carte> getMesCartes(Carte.CouleurCarte coul) {
        switch (coul) {
            case carreau:
                return carreau;
            case trefle:
                return trefle;
            case coeur:
                return coeur;
            case pique:
                return pique;
        }
        return null;
    }

    private List<Carte> getMyAtout() {
        List<Carte> ret = new ArrayList<Carte>(8);
        for (Carte c : cartes) {
            if (c.getCouleurCarte() == atout) {
                ret.add(c);
            }
        }
        return ret;
    }

    private void remove(Carte get) {
        cartes.remove(get);
        switch (get.getCouleurCarte()) {
            case carreau:
                carreau.remove(get);
                break;
            case pique:
                pique.remove(get);
                break;
            case coeur:
                coeur.remove(get);
                break;
            case trefle:
                trefle.remove(get);
                break;
        }
    }

    private Carte getMaster(List<Carte> whatCanPlay) {
        LinkedList<Carte> m = new LinkedList<Carte>();
        boolean encoreAtout = encoreAtout();
        for (Carte c : whatCanPlay) {
            if (encoreAtout && c.getCouleurCarte() == atout) {
                if (memosCarte.isMasterInColor(c)) {
                    m.add(c);
                }
            } else if (!encoreAtout) {
                if (memosCarte.isMasterInColor(c)) {
                    m.add(c);
                }
            }
        }
        if (m.isEmpty()) {
            return null;
        }
        return m.getLast();
    }

    private Carte getMasterNoAtout(List<Carte> whatCanPlay) {
        LinkedList<Carte> m = new LinkedList<Carte>();
        boolean encoreAtout = encoreAtout();
        for (Carte c : whatCanPlay) {
            if (!encoreAtout) {
                if (c.getCouleurCarte() != atout && memosCarte.isMasterInColor(c)) {
                    m.add(c);
                }
            }
        }
        if (m.isEmpty()) {
            return null;
        }
        return m.getLast();
    }

}
