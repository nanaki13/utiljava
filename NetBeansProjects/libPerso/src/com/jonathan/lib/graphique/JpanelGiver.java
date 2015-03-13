/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.graphique;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class JpanelGiver {
    public static JPanel makeJPanelWithIn(Component c){
        JPanel j =new JPanel();
        j.add(c);
        return j;
    }
}
