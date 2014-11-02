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
public class Bar extends Parent {
    
    private  BallFx ball;
    public Bar(){
        ball = new BallFx();
        
//        ball.
        Group group = new Group();
        getChildren().add(group);
        group.getChildren().add(ball);
    }
    public final void moveBall(Point p){
        ball.setTranslateX(p.x - pong.Context.RAYON_BALL);
        ball.setTranslateY(p.y- pong.Context.RAYON_BALL);
    }

    void moveBarD(PointCinetique pcBarDroite) {
    }

    void moveBarG(PointCinetique pcBarGauche) {
    }
    
}
