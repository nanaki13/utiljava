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

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author jonathan
 */
public class RemovablePanelObject<T> extends RemovablePanel{

    private T obj;
    private ObjectListener<T> ol;
    public RemovablePanelObject(MouseListener ml, ImageIcon icon, ObjectListener<T> ol, T obj) {
        super(ml, icon);
        this.obj = obj;
        this.ol = ol;
    }
    
     @Override
    public void mouseClicked(MouseEvent e) {
        
        super.mouseClicked(e);
        ol.event(obj);
    }
    
}
