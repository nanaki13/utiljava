/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import geometrie.Point;
import geometrie.PointCinetique;
import geometrie.Rectangle;
import geometrie.Vecteur;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static pong.Context.CONTINU;
import static pong.Context.J1;
import static pong.Context.J2;
import static pong.Context.RAYON_BALL;

/**
 *
 * @author jonathan
 */
public class PongEngine {

   
    private PointCinetique pcBall;
    private PointCinetique pcBarGauche;
    private PointCinetique pcBarDroite;
    private int scoreJ1 = 0;
    private int scoreJ2 = 0;
    private Ball ball;
    private Bar barDroite;
    private Bar barGauche;

    public PongEngine() {

        init();
    }

    public void init() {
        Context.init();
        pcBall = Context.pcBall;
        pcBarGauche = Context.pcBarGauche;
        pcBarDroite = Context.pcBarDroite;
        barDroite = Context.barDroite;
        barGauche = Context.barGauche;
        ball = Context.ball;
    }

    public int nextState(int width, int height) {

//        System.out.println("pcBall.x"+pcBall.x);
        if (Context.pcBall.x > width - RAYON_BALL) {
            return J1;
        } else if (Context.pcBall.x < RAYON_BALL) {
            return J2;
        }
        if (Context.pcBall.y > height - RAYON_BALL) {
            Context.pcBall.vitesse.y = -Math.abs(Context.pcBall.vitesse.y);
        } else if (pcBall.y < RAYON_BALL) {
            Context.pcBall.vitesse.y = Math.abs(pcBall.vitesse.y);
        }
        processCollision();
        Point clone = pcBall.clone();
        ball.move(pcBall.vitesse);
        barDroite.move(pcBarDroite.vitesse);
        barGauche.move(pcBarGauche.vitesse);
        if (pcBall.vitesse.x > barGauche.getLargueur() + pcBarGauche.vitesse.x) {

            if (clone.x > barGauche.centre.x && pcBall.x < barGauche.centre.x
                    || clone.x < barGauche.centre.x && pcBall.x > barGauche.centre.x) {
                pcBall.x = pcBarGauche.x - pcBall.x + pcBarGauche.x;
                pcBall.vitesse.x = -pcBall.vitesse.x;
            }
            pcBall.vitesse.x = pcBall.vitesse.x * 0.75;
            pcBall.vitesse.y = pcBall.vitesse.y * 0.75;
        }
        if (pcBall.vitesse.x > barDroite.getLargueur() + pcBarDroite.vitesse.x) {

            if (clone.x > barDroite.centre.x && pcBall.x < barDroite.centre.x
                    || clone.x < barDroite.centre.x && pcBall.x > barDroite.centre.x) {
                pcBall.x = pcBarDroite.x - pcBall.x + pcBarDroite.x;
                pcBall.vitesse.x = -pcBall.vitesse.x;
            }
            pcBall.vitesse.x = pcBall.vitesse.x * 0.75;
            pcBall.vitesse.y = pcBall.vitesse.y * 0.75d;
        }

        return CONTINU;
    }

    private void processCollision() {
        Point[] points = ball.getPoints();
//        System.out.println(Arrays.toString(points));
        List<Point> inBarD = new LinkedList<>();
        List<Point> inBarG = new LinkedList<>();
        for (Point p : points) {
            if (barDroite.isIn(p)) {
                inBarD.add(p);
            }
            if (barGauche.isIn(p)) {
                inBarG.add(p);
            }
        }
        if (!inBarD.isEmpty() && !inBarG.isEmpty()) {
            pcBall.vitesse.x = 0;
            pcBall.vitesse.y = 0;
        } else if (!inBarD.isEmpty()) {
            processBar(inBarD, barDroite);

        } else if (!inBarG.isEmpty()) {
            processBar(inBarG, barGauche);

        }
    }

    public static void modifieVitesseBall(Rectangle r, Ball ball, PointCinetique pcBall) {

    }

    private void processBar(List<Point> inBarD, Bar bar) {
        List<Vecteur> vs = new LinkedList<>();
        for (Point p : inBarD) {
            vs.add(new Vecteur(bar.centre, p));
        }
        Vecteur somme = new Vecteur(0, 0);
        for (Vecteur v : vs) {
            somme = somme.add(v);
        }

        if (somme.isNull()) {
            if (!pcBall.vitesse.isNull()) {
                pcBall.vitesse.oppose();
            } else {

            }
        } else {

            somme.oppose();

            somme.tourne((Math.PI / 3) * (pcBall.y - bar.centre.y) * Math.signum(pcBall.x - bar.centre.x) / bar.getDemiHauteur());

            somme.multipli(1.03d * pcBall.vitesse.norme() / somme.norme());

            pcBall.vitesse = somme;
//                pcBall.vitesse = pcBall.vitesse.add(((PointCinetique)bar.centre).vitesse);

        }
        pcBall.vitesse.oppose();
//      Point h = bar.centre.clone();
//      h.bouge(0, bar.getDemiHauteur());
//      Point b = bar.centre.clone();
//      b.bouge(0, - bar.getDemiHauteur());
//      UtilGeom.reflexion(pcBall.vitesse, new Droite(h,b));
//     
//      System.out.println((pcBall.y - bar.centre.y)/bar.getDemiHauteur());
//      pcBall.vitesse.tourne((Math.PI/4)*(pcBall.y - bar.centre.y)/bar.getDemiHauteur());
//       if(Vecteur.scalaire(pcBall.vitesse, new Vecteur(bar.centre, pcBall))<0){
//          pcBall.vitesse.x = -pcBall.vitesse.x;
//      }
//       pcBall.vitesse = pcBall.vitesse.add(((PointCinetique)bar.centre).vitesse);

    }
}
