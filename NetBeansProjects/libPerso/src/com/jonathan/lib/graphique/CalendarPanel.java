/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author jonathan
 */
public class CalendarPanel extends JPanel {

    JPanel calendrierJPanel;
    Border border = BorderFactory.createLineBorder(Color.red);
    Dimension celluleDim = new Dimension(50, 25);

    public CalendarPanel(int month) {
        build();
    }

    private void build() {
        GridLayout gridLayout = new GridLayout(5, 7);
        calendrierJPanel = new JPanel(gridLayout);
        celluleDim = new Dimension(50, 25);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel jPanel = new JPanel();
                jPanel.setPreferredSize(celluleDim);
                jPanel.setBorder(border);
                calendrierJPanel.add(jPanel);
            }
        }
        add(calendrierJPanel);
    }
    
    public static void main(String[] args){
        JFrame jFrameAutoClosable = JFrameGiver.getJFrameAutoClosable("test cal");
        jFrameAutoClosable.setContentPane(new CalendarPanel(2));
        jFrameAutoClosable.pack();
        jFrameAutoClosable.setVisible(true);
    }
}
