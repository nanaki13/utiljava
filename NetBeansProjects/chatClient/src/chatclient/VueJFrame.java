/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jonathan
 */
public class VueJFrame extends JFrame implements VueInterface, Listener<String>{
    private final JTextArea area;
    private String readed;
    
    public VueJFrame(String title){
        super(title);
        JPanel content = new JPanel();
        Form mainPanel = new Form();
        area = new JTextArea( 10, 30);
        area.setEditable(false);
        JScrollPane js = new JScrollPane(area);
        mainPanel.addWithJPanel(js);
        mainPanel.nextLine();
         PromptPanel panel = new PromptPanel(7, 30, "envoyer");
        panel.setListener(this);
       
        mainPanel.addWithJPanel(panel);
        content.add(mainPanel);
        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
         panel.getTextArea().requestFocus();
    }
//    @Override
    public void print(String string) {
        area.append(string+"\n");
    }

//    @Override
    public synchronized String read() {
        try {
            wait();
            return readed;
        } catch (InterruptedException ex) {
            Logger.getLogger(VueJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    @Override
    public synchronized void event(String event) {
        readed = event;
        notify();
    }

//    @Override
    public String getClientName() {
       return JOptionPane.showInputDialog("entrer votre nom");
    }
    
}
