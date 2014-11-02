/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong.javafx;

import javafx.scene.image.Image;
import rsc.AccesRessources;

/**
 *
 * @author jonathan
 */
public class Ressource {
    private static final String  IMAGE = "image";
    public static final Image BALL_IMAGE ;
    static {
        String path = IMAGE+"/ball"+pong.Context.RAYON_BALL+".png";
        System.out.println(path);
       BALL_IMAGE =new Image(AccesRessources.class.getResourceAsStream(IMAGE+"/ball"+pong.Context.RAYON_BALL+".png"));
    }
}
