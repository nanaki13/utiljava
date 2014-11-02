/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import pong.Context;
import pong.PongEngine;

/**
 *
 * @author jonathan
 */
public class PongJPanel extends JPanel implements  ActionListener, KeyListener{
//    private static final int _WIDTH = 500;
//    private static final int _HEIGHT = 350;
//    private static final int RAYON_BALL = 5;
//    private static final int DIAM_BALL = 2 * RAYON_BALL;
//    private static final int DEMI_LARG_BAR = 8;
//    private static final int DEMI_HAUTEUR_BAR = 40;
//    private static final int LARG_BAR = DEMI_LARG_BAR * 2;
//    private static final int HAUTEUR_BAR = DEMI_HAUTEUR_BAR * 2;
//    private static final int J1 =1;
//    private static final int J2 =2;
    public static  Timer timer;
//    private  PointCinetique pcBall;
//    private  PointCinetique pcBarGauche;
//    private  PointCinetique pcBarDroite;
    private PongEngine engine;
    private  JFrame jf;
    private  int scoreJ1 = 0;
    private  int scoreJ2 = 0;
//    
    public PongJPanel(){
        setPreferredSize(new Dimension(Context._WIDTH, Context._HEIGHT));
        jf = new JFrame();  
        jf.setContentPane(this);
        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.addKeyListener(this);
       
        
        addKeyListener(this);
        this.jf = jf;
        jf.setTitle(score());
    }
    public void start(){
         timer = new Timer(40, this);
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        super.paintComponent(g); 
        Graphics2D g2D = (Graphics2D) g ;
        g2D.setColor(Color.red);
        int UpGaux = Math.round((float)(Context.pcBall.x - Context.RAYON_BALL));
        int UpGy = Math.round((float)(Context.pcBall.y- Context.RAYON_BALL));
        g2D.fillOval(UpGaux,UpGy ,Math.round((float)( Context.DIAM_BALL)),Math.round((float)( Context.DIAM_BALL)));
        g2D.setColor(Color.blue);
        g2D.fillRoundRect(round(Context.pcBarGauche.x - Context.DEMI_LARG_BAR), round(Context.pcBarGauche.y- Context.DEMI_HAUTEUR_BAR), Context.LARG_BAR, Context.HAUTEUR_BAR,10,10);
        g2D.setColor(Color.green);
//        pcBarDroite.x = getSize().width -10;
        g2D.fillRoundRect(round(Context.pcBarDroite.x) - Context.DEMI_LARG_BAR, round(Context.pcBarDroite.y)- Context.DEMI_HAUTEUR_BAR, Context.LARG_BAR, Context.HAUTEUR_BAR,10,10);
        
    }
    public static int round(double d){
        return Math.round((float)(d));
    }
    
    public static void main(String[] args){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        int rep;
        rep = engine.nextState(getWidth(), getHeight());
        switch (rep){
            case Context.CONTINU : 
                break;
                 case Context.J1 : 
                     defaite(Context.J2);
                break;
                      case Context.J2 : 
                          defaite(Context.J1);
                break;
                
        }
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Déplacement joueur 1
        if(e.getKeyCode() == 90){
            Context.pcBarGauche.vitesse.y = -4;
        }
        else if(e.getKeyCode() == 83){
           Context.pcBarGauche.vitesse.y = 4; 
        }else if(e.getKeyCode() == 68){
           Context.pcBarGauche.vitesse.x = 4; 
        }else if(e.getKeyCode() == 81){
           Context.pcBarGauche.vitesse.x = -4; 
        }
        //Déplacement joueur 2
        if(e.getKeyCode() == 38){
            Context.pcBarDroite.vitesse.y = -4;
        }
        else if(e.getKeyCode() == 40){
           Context.pcBarDroite.vitesse.y = 4;
        }else if(e.getKeyCode() == 37){
           Context.pcBarDroite.vitesse.x = -4;
        }else if(e.getKeyCode() == 39){
           Context.pcBarDroite.vitesse.x = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //fin déplacement Joueur 1
        if(e.getKeyCode() == 90){
            Context.pcBarGauche.vitesse.y = 0;
        }
        else if(e.getKeyCode() == 83){
           Context.pcBarGauche.vitesse.y = 0; 
        }else if(e.getKeyCode() == 68){
           Context.pcBarGauche.vitesse.x = 0; 
        }else if(e.getKeyCode() == 81){
           Context.pcBarGauche.vitesse.x = 0; 
        }
        //fin déplacement Joueur 2
        if(e.getKeyCode() == 38){
            Context.pcBarDroite.vitesse.y = 0;
        }
        else if(e.getKeyCode() == 40){
           Context.pcBarDroite.vitesse.y = 0;
        }else if(e.getKeyCode() == 37){
           Context.pcBarDroite.vitesse.x = 0;
        }else if(e.getKeyCode() == 39){
           Context.pcBarDroite.vitesse.x = 0;
        }
    }

    private void defaite(int J) {
        timer.stop();
        int ret = -1;
        int gagnant = 0;
        switch(J){
            case Context.J1 : 
                scoreJ2++;
                gagnant = 2;
                
                break;
            case Context.J2 : 
                scoreJ1++;
                gagnant = 1;
                break;
        }
        jf.setTitle(score());
        ret = JOptionPane.showConfirmDialog(jf, "le joueur "+gagnant+" à gagner\n rejouer?","joueur "+gagnant+" vainqueur",JOptionPane.YES_NO_OPTION);
        if(ret == JOptionPane.YES_OPTION){
            engine.init();
            timer.start();
        }
        else{
            System.exit(0);
        }
    }
    
    private String score(){
        return scoreJ1+" - "+scoreJ2;
    }

    public void setEngine(PongEngine engine) {
        this.engine = engine;
    }

   
    
}
