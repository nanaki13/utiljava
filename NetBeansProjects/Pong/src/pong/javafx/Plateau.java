/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong.javafx;

import geometrie.Point;
import geometrie.PointCinetique;
import javafx.scene.Group;
import javafx.scene.Parent;

/**
 *
 * @author jonathan
 */
public class Plateau extends Parent {
    
    private  BallFx ball;
    private final Bar barD;
    private final Bar barG;
    public Plateau(){
        ball = new BallFx();
        barD = new Bar();
        barG = new Bar();
        ball = new BallFx();
        
//        ball.
        Group group = new Group();
        getChildren().add(group);
        group.getChildren().add(ball);
        group.getChildren().add(barD);
        group.getChildren().add(barG);
    }
    public final void moveBall(Point p){
        ball.setTranslateX(p.x - pong.Context.RAYON_BALL);
        ball.setTranslateY(p.y- pong.Context.RAYON_BALL);
    }

    void moveBarD(Point pcBarDroite) {
        barD.setTranslateX(pcBarDroite.x);
        barD.setTranslateY(pcBarDroite.y);
        
    }

    void moveBarG(Point pcBarGauche) {
        barD.setTranslateX(pcBarGauche.x);
        barD.setTranslateY(pcBarGauche.y);
    }
    
}
