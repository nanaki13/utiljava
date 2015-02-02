/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille;

import libjeux.manille.object.PointPlit;
import libjeux.manille.object.PlitDeCarte;
import libjeux.manille.object.JoueurInterface;
import libjeux.manille.object.Equipe;
import libjeux.manille.object.CarteManille;
import libjeux.manille.object.Carte;
import bsh.EvalError;
import bsh.Interpreter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jonathan
 */
public class Manille {

    private Carte[] jeux;
    private Equipe[] equipes;
    private List<Carte> mainJ1E1;
    private List<Carte> mainJ2E1;
    private List<Carte> mainJ1E2;
    private List<Carte> mainJ2E2;
    private final Random rand;
    private JoueurInterface gaucheDonneur;
    private PlitDeCarte plit;
    private Carte.CouleurCarte atout;
    private JoueurInterface jouerCourant;
    private JoueurInterface[] joueurs = new JoueurInterface[4];
    private ActionManilleListener actionMailleListener;
    private int gaucheDonIndex;

    public Manille(ActionManilleListener actionMailleListener) {
        this.actionMailleListener = actionMailleListener;
        equipes = new Equipe[2];
        equipes[0] = new Equipe("E1");
        equipes[1] = new Equipe("E2");
        rand = new Random();
        plit = new PlitDeCarte();

    }

    public void setJoueurs(JoueurInterface j1Equipe1, JoueurInterface j2Equipe1, JoueurInterface j1Equipe2, JoueurInterface j2Equipe2) {

        joueurs[0] = j1Equipe1;
        joueurs[2] = j2Equipe1;
        joueurs[1] = j1Equipe2;
        joueurs[3] = j2Equipe2;
        equipes[0].setJoueur1(j1Equipe1);
        equipes[0].setJoueur2(j2Equipe1);
        equipes[1].setJoueur1(j1Equipe2);
        equipes[1].setJoueur2(j2Equipe2);
        if (actionMailleListener != null) {
            actionMailleListener.settingEquipe(j1Equipe1, j2Equipe1, j1Equipe2, j2Equipe2, equipes[0], equipes[0]);
        }
        j1Equipe1.setEquipes(equipes[0], equipes[1]);
        j2Equipe1.setEquipes(equipes[0], equipes[1]);
        j1Equipe2.setEquipes(equipes[1], equipes[0]);
        j2Equipe2.setEquipes(equipes[1], equipes[0]);
    }

    public void run() {

        chooseMeneur();
        jouerCourant = gaucheDonneur;
        while (equipes[0].getScoreTotal() < 104 && equipes[1].getScoreTotal() < 104) {
            melangeEtDistribu();
            askAtout();
            next();

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    Carte joux = jouerCourant.joux();
                    for (int k = 0; k < 4; k++) {
                        joueurs[k].carteJouer(joux, jouerCourant, j);
                    }
                    System.out.println(jouerCourant.getName() + " : " + joux);
                    plit.add(joux, jouerCourant);
                    next();
                }
                System.out.println(plit);
                PointPlit count = plit.count();
                jouerCourant = count.getMeneur();

                Equipe equipe = jouerCourant.getEquipe();
                equipe.setScore(equipe.getScore() + count.getPoint());
            }
            calcScore();
            sayAll(equipes[0].getJoueur1().getName() + ", " + equipes[0].getJoueur2().getName() + " : " + equipes[0].getScoreTotal() + "\n" + equipes[1].getJoueur1().getName() + ", " + equipes[1].getJoueur2().getName() + " : " + equipes[1].getScoreTotal());
//            next();
            gaucheDonneur = nextGauchDonneur();

        }

    }

    private int indiceNextDonneur(){
        gaucheDonIndex++;
        return gaucheDonIndex%4;
    }
    private JoueurInterface nextGauchDonneur(){
        System.out.println("gaucheDonIndex : "+gaucheDonIndex);
        return joueurs[indiceNextDonneur()];
    }
    private void chooseMeneur() {
        int nBgin = rand.nextInt(4);
        switch (nBgin) {
            case 0:
                gaucheDonIndex = 0;
                gaucheDonneur = equipes[0].getJoueur1();
                break;
            case 1:
                gaucheDonIndex = 1;
                gaucheDonneur = equipes[1].getJoueur2();
                break;
            case 2:
                gaucheDonIndex = 2;
                gaucheDonneur = equipes[0].getJoueur2();
                break;
            case 3:
                gaucheDonIndex = 3;
                gaucheDonneur = equipes[1].getJoueur1();
                break;

        }
    }

    private void askAtout() {
        atout = gaucheDonneur.chooseAtout();

        if (atout == null) {
            sayAll(gaucheDonneur.getName() + " dit en face");
            atout = gaucheDonneur.getEquipier().chooseAtoutEnFace();
        }
        for (JoueurInterface j : joueurs) {
            j.setAtout(atout, gaucheDonneur.getName() + " dit " + atout);

        }

        plit.setAtout(atout);
    }

    private void next() {
        if (jouerCourant == null) {
            jouerCourant = gaucheDonneur;
        }
        for (int i = 0; i < 4; i++) {
            if (joueurs[i] == jouerCourant) {
                jouerCourant = joueurs[(i + 1) % 4];
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, EvalError {
        Interpreter interpreter = new Interpreter();
        interpreter.set("var", new Date());
        System.out.println(interpreter.eval(" System.out.println(var)"));
    }

    private void sayAll(String string) {
        for (JoueurInterface ji : joueurs) {
            ji.message(string);
        }
    }

    private void melangeEtDistribu() {
        Carte.CouleurCarte[] couleurCartes = Carte.CouleurCarte.values();
        Carte.NomCarte[] nomCartes = Carte.NomCarte.values();
        int size = couleurCartes.length * nomCartes.length;
        jeux = new Carte[size];
        for (int i = 0; i < couleurCartes.length; i++) {
            for (short j = 0; j < nomCartes.length; j++) {
                jeux[i * nomCartes.length + j] = new CarteManille(nomCartes[j], couleurCartes[i]);
            }
        }
        System.out.println(Arrays.asList(jeux));

        List<Integer> pos = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            pos.add(i);
        }
        Carte[] jeuxCopy = new Carte[couleurCartes.length * nomCartes.length];

        for (int i = 0; i < size; i++) {
            Integer get = pos.get(rand.nextInt(pos.size()));
            jeuxCopy[i] = jeux[get];
            pos.remove(get);
        }
        jeux = jeuxCopy;
//        System.out.println(Arrays.asList(jeux));
//        System.out.println(Arrays.asList(jeux));
        Carte[][] mains = new Carte[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                mains[i][j] = jeux[i * 8 + j];
            }
        }
        mainJ1E1 = new LinkedList<Carte>(Arrays.asList(mains[0]));
        mainJ2E1 = new LinkedList<Carte>(Arrays.asList(mains[1]));
        mainJ1E2 = new LinkedList<Carte>(Arrays.asList(mains[2]));
        mainJ2E2 = new LinkedList<Carte>(Arrays.asList(mains[3]));
        equipes[0].getJoueur1().setCartes(mainJ1E1);
        equipes[0].getJoueur2().setCartes(mainJ2E1);
        equipes[1].getJoueur1().setCartes(mainJ1E2);
        equipes[1].getJoueur2().setCartes(mainJ2E2);
    }

    private void calcScore() {
        equipes[0].finManche();
        equipes[1].finManche();
    }

}
