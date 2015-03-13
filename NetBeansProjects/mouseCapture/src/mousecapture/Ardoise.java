/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import mousecapture.core.MouseCapture;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class Ardoise extends JPanel {

    public static final Image background = Toolkit.getDefaultToolkit().createImage("tournesol.jpg");

    private AnimationArdoise animation = AnimationArdoise.STATIC;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        System.out.println("paintComponent");
        animation.getAnim().paintComponent(g);
    }

    public void setAnimationArdoise(AnimationArdoise aimation) {
       
        this.animation = aimation;
        if (aimation == AnimationArdoise.DYNAMIC) {
            TimerAnimation t = new TimerAnimation(() -> {
                repaint();
            }, () -> {
                setAnimationArdoise(AnimationArdoise.STATIC);
                MouseCapture.dynamicAnimationFinished();
            }, MouseCapture.dt, aimation.getAnim());
            t.start();
        }
    }

}
