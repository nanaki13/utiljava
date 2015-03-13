/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture.view.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mousecapture.core.MouseCapture;

/**
 *
 * @author jonathan
 */
public class MousePressedListener extends MouseAdapter {

   

   

    @Override
    public void mousePressed(MouseEvent e) {
        MouseCapture.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseCapture.mouseReleased(e);
    }

}
