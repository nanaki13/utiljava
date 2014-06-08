/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.test;

import com.jonathan.lib.graphique.JFrameGiver;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 *
 * @author jonathan
 */
public class Test {
    
    public static void main(String[] args){
        JFrame jf = JFrameGiver.getJFrameAutoClosable("test");
//        
        JPanel jpa = new JPanel();
        
        jpa.setPreferredSize(new Dimension(300, 300));
        
        jf.setContentPane(jpa);
        JPanel jpa2 = new JPanel();
        jpa2.setPreferredSize(new Dimension(100, 100));
        JPanel jpa3 = new JPanel();
        jpa3.setPreferredSize(new Dimension(100, 800));
        JScrollPane jScrollPane = new JScrollPane(jpa3);
        jScrollPane.setPreferredSize(new Dimension(100, 100));
//        jScrollPane.add(jpa3);
//        jScrollPane.revalidate();
//        jScrollPane.repaint();
        
        jf.setVisible(true);
//        jpa2.add(jScrollPane);
//        jpa.add(jScrollPane);
        jf.pack();
        JPopupMenu jp = new JPopupMenu();
        jp.add(jScrollPane);
        jp.pack();
        jp.show(jpa, 10, 10);
        
    }
    
    
}
