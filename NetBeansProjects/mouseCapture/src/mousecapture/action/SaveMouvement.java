/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture.action;

import com.jonathan.application.Controleur;
import com.jonathan.application.UserRequest;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import mousecapture.core.EnumRequestUser;
import static mousecapture.core.MouseCapture.saveMvt;
import mousecapture.core.UserRequestR;

/**
 *
 * @author jonathan
 */
public class SaveMouvement extends AbstractAction {
    private final Controleur controleur;
    private static final UserRequestR<EnumRequestUser> REQUEST_SAVE_MVT= new UserRequestR(EnumRequestUser.CLOSING_APP);

    public SaveMouvement(String save,Controleur controleur) {
        super(save);
        this.controleur = controleur;
        REQUEST_SAVE_MVT 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserRequest request;
        controleur.userRequest(request);
        saveMvt();
    }

}
