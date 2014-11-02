/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatclient;


import javax.swing.JFrame;

/**
 *
 * @author jonathan
 */
public class JFrameGiver {
    public static JFrame getJFrameAutoClosable(String titre){
        JFrame jf = new JFrame(titre);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jf;
    }
}
