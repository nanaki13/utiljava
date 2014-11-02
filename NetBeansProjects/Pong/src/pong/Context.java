/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import geometrie.PointCinetique;
import geometrie.Vecteur;


public class Context {
    public static final int _WIDTH = 500;
    public static final int _HEIGHT = 350;
    public static final int RAYON_BALL = 21;
    public static final int DIAM_BALL = 2 * RAYON_BALL;
    public static final int DEMI_LARG_BAR = 8;
    public static final int DEMI_HAUTEUR_BAR = 40;
    public static final int LARG_BAR = DEMI_LARG_BAR * 2;
    public static final int HAUTEUR_BAR = DEMI_HAUTEUR_BAR * 2;
    public static final int J1 = 1;
    public static final int J2 = 2;
    public static final int CONTINU = 0;
    public static  PointCinetique pcBall ;
    public static PointCinetique pcBarGauche;
    public static PointCinetique pcBarDroite;
    public static Ball ball ;
    public static Bar barDroite ;
    public static Bar barGauche ;
    static void init() {
        Context.pcBall = new PointCinetique(_WIDTH/2,_HEIGHT/2, new Vecteur(4,0));
        Context.pcBarGauche = new PointCinetique(DEMI_LARG_BAR + 2, _HEIGHT / 2, new Vecteur(0,0));
        Context.pcBarDroite = new PointCinetique(_WIDTH - DEMI_LARG_BAR - 2, _HEIGHT / 2, new Vecteur(0,0));
        Context.ball = new Ball(pcBall, RAYON_BALL,8);
        Context.barDroite = new Bar(pcBarDroite, DEMI_LARG_BAR, DEMI_HAUTEUR_BAR);
        Context.barGauche = new Bar(pcBarGauche, DEMI_LARG_BAR, DEMI_HAUTEUR_BAR);
    }
}
