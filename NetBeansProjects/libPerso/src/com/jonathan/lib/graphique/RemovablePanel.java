/*
 * Copyright (C) 2014 jonathan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jonathan.lib.graphique;

import com.jonathan.lib.graphique.JFrameGiver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author jonathan
 */
public class RemovablePanel extends JPanel implements MouseListener{

    private static final Border border = BorderFactory.createLineBorder(Color.gray,1);
    private static final Border borderEmpty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
    private final JLabel jl;
    public RemovablePanel(MouseListener ml) {
        super();
        setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("./close.png");
//        icon.
        JPanel jp = new JPanel(new BorderLayout());
        jl = new JLabel(icon);
        jl.addMouseListener(ml);
        jl.addMouseListener(this);
        
        
        jp.add(jl, BorderLayout.NORTH);
        
        add(jp, BorderLayout.EAST);

    }
    public RemovablePanel(MouseListener ml,ImageIcon icon ) {
        super();
        setLayout(new BorderLayout());
        JPanel jp = new JPanel(new BorderLayout());
        jl = new JLabel(icon);
        jl.addMouseListener(ml);
        jl.addMouseListener(this);
        jl.setBorder(borderEmpty);
        jp.add(jl, BorderLayout.NORTH);
        add(jp, BorderLayout.EAST);
    }
    public static void main(String[] args){
        JFrame jFrameAutoClosable = JFrameGiver.getJFrameAutoClosable("test");
        RemovablePanel r = new RemovablePanel(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("coucou");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(100,15));
        jp.setBackground(Color.red);
        r.add(jp);
        jFrameAutoClosable.getContentPane().add(r);
        jFrameAutoClosable.pack();
        jFrameAutoClosable.setVisible(true);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        Container parent = this.getParent();
        this.getParent().remove(this);
        parent.validate();
        parent.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       jl.setBorder(border);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        jl.setBorder(borderEmpty);
    }
    
}
