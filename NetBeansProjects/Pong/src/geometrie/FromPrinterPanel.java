/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrie;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class FromPrinterPanel extends JPanel{
    private List<Object> forms = new ArrayList<Object>();
    
    public JPanel addForm(Object o ){
        forms.add(o);
        return this;
    }
    
     @Override
     protected void paintComponent(Graphics g){

         for(Object form : forms){

             if(form instanceof Cercle){
                 Cercle c = (Cercle) form; 

                 g.drawOval(
                         (int)Math.round(c.centre.x),
                         (int)Math.round( c.centre.y), 
                         (int)Math.round( c.rayon), 
                         (int)Math.round( c.rayon));
             }
                 
         }
     }
     
     public static void main(String[] args){
         JFrame jf = new JFrame();
        FromPrinterPanel printerJpanel = new FromPrinterPanel();
        printerJpanel.addForm(new Cercle(new Point(50, 50), 10));
        printerJpanel.setPreferredSize(new Dimension(150, 150));
        jf.setContentPane(printerJpanel);
        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
     }
      
}
