/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import mousecapture.core.MouseCapture;

/**
 *
 * @author jonathan
 */
public class EffacerAction extends AbstractAction {
//    private ArrayList<Boolean> selectedIndexs;

    public EffacerAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MouseCapture.effacerAction();
    }

   
    
}
