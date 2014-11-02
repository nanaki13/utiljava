/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.graphique;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author jonathan
 */
public class PromptPanel extends Form{
    private JTextArea textArea;
    private Listener<String> listener;
    private String buttonText = "OK";
    private boolean effaceText = true;

    public PromptPanel(LayoutManager layout, boolean isDoubleBuffered,int rows,int cols) {
        super(layout, isDoubleBuffered);
        build(rows, cols);
    }

    public PromptPanel(LayoutManager layout,int rows,int cols) {
        super(layout);
        build(rows, cols);
    }

    public PromptPanel(boolean isDoubleBuffered,int rows,int cols) {
        super(isDoubleBuffered);
        build(rows, cols);
    }

    public PromptPanel(int rows,int cols) {
        super();
        build(rows, cols);
    }
    public PromptPanel(int rows,int cols,String buttonText) {
        super();
        this.buttonText =  buttonText;
        build(rows, cols);
    }

    private void build(int rows,int cols) {
        textArea = new JTextArea(rows, cols);
        add(textArea);
        JButton jb = new JButton(buttonText);
        nextLine();
        addWithJPanel(jb);
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listener.event(textArea.getText());
                if(PromptPanel.this.effaceText){
                    textArea.setText("");
                }
            }
        });
    }

    public void setListener(Listener<String> listener) {
        this.listener = listener;
    }
    
    
    public static void main(String[] args){
        PromptPanel panel = new PromptPanel(15, 30, "envoyer");
        panel.setListener(new Listener<String>() {

            @Override
            public void event(String event) {
                System.out.println(event);
            }
        });
        JFrame jFrameAutoClosable = JFrameGiver.getJFrameAutoClosable("test prompt");
        jFrameAutoClosable.setContentPane(panel);
        jFrameAutoClosable.pack();
        jFrameAutoClosable.setVisible(true);
        
                
    }
    
}
