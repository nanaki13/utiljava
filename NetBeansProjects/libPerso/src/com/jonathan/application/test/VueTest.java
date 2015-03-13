/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application.test;

import com.jonathan.application.AbstractVue;
import com.jonathan.application.Controleur;
import com.jonathan.application.Vue;
import com.jonathan.lib.graphique.JFrameGiver;
import com.jonathan.lib.graphique.JpanelGiver;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class VueTest extends AbstractVue{

    private JFrame j;
    @Override
    public void receiveData(Object data) {
        JPanel makeJPanelWithIn = JpanelGiver.makeJPanelWithIn(new JLabel((String)data));
        j.getContentPane().removeAll();
        j.getContentPane().add(makeJPanelWithIn);
        
        
    }

    public VueTest() {

        j = JFrameGiver.getJFrameAutoClosable("teset");
    }

    @Override
    public void show() {
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
    
    

   
    
}
