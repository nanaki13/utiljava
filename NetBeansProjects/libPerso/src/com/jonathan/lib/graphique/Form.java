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

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jonathan
 */
public class Form extends JPanel {

    public static final int LEFT = 1;
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints c = new GridBagConstraints();
    private int line = 0;
    private int col = 0;
    private int colMax = 0;
    private int lineMem = 0;
    private int colMem = 0;
    public static final int MAX_SIZE = 0;

    public Form() {
        super();
        init();
       
    }

    public Form(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    public Form(LayoutManager layout) {
        super(layout);
        init();
    }

    public Form(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    

    public Component add(Component co) {
//        co.setBackground(Color.red);
        c.gridx = col;
        c.gridy = line;
        c.gridwidth = 1;
        c.gridheight = 1;
        col++;
        colMax = Math.max(col, colMax);
//        c.weightx = 12;
        add(co, c);
        return this;
    }

//    public Component addWithAlign(Component co,int align){
//        c.gridx = col;
//        c.gridy = line;
//        c.anchor = align;
//        col++;
//        c.weightx = 0.5;
//        add(co,c);
//        return this;
//    }
    public void nextLine() {
        line++;
        col = 0;
    }

    public void savePos() {
        lineMem = line;
        colMem = col;
    }

    public void restorePos() {
        line = lineMem;
        col = colMem;
    }

    public void removeAll() {
        line = 0;
        col = 0;
        super.removeAll();
    }

    public GridBagConstraints getGridBagConstraints() {
        return c;
    }

    public static void main() {

    }

    public Component addWithJPanel(Component c) {
        JPanel jp = new JPanel();
        jp.add(c);
        return add(jp);
    }
     public Component addWithJPanel(Component c, LayoutManager l) {
        JPanel jp = new JPanel();
        jp.setBackground(Color.red);
        jp.setLayout(l);
        jp.add(c);
        return add(jp);
    }

    public Component addWithJPanel(Component c, int option) {
        JPanel jp = new JPanel();
        jp.add(c);
        return addS(jp, option);
    }
    
    public Component addWithJPanel(Component c, int option, LayoutManager l) {
        JPanel jp = new JPanel();
        jp.setLayout(l);
        jp.add(c);
        return addS(jp, option);
    }

    public Component addS(Component co, int option) {
        switch (option) {
            case MAX_SIZE:
                c.gridx = col;
                c.gridy = line;
                c.gridwidth = colMax;
                c.gridheight = 1;
//                col++;

//                colMax = Math.max(col, colMax);
                //        c.weightx = 12;
                add(co, c);
                nextLine();
                break;
            case LEFT:
                c.gridx = col;
                c.gridy = line;
                c.gridwidth = 1;
                c.gridheight = 1;
//                co.set
                col++;
                c.weightx = 0.5;
//                c.anchor = GridBagConstraints.WEST;

//                colMax = Math.max(col, colMax);
                //        c.weightx = 12;
                add(co, c);
             
                break;

        }
        return this;
    }

    private void init() {
         setLayout(gbl);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(2, 2, 2, 2);
    }
}
