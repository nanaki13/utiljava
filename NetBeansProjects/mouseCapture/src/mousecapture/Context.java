/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import com.jonathan.application.AbstractContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Context extends AbstractContext{
  
    private List<GroupeMouvement> groupsMouvement = Collections.synchronizedList(new ArrayList<>());
    private List<MouvementData> mouvementDatas  = Collections.synchronizedList(new ArrayList<>());
    
    public Context(){
        put("groupsMouvement", groupsMouvement);
        put("mouvementDatas", groupsMouvement);
    }

    public List<GroupeMouvement> getGroupsMouvement() {
        return groupsMouvement;
    }

    public void setGroupsMouvement(List<GroupeMouvement> groupsMouvement) {
        this.groupsMouvement = groupsMouvement;
    }

    public List<MouvementData> getMouvementDatas() {
        return mouvementDatas;
    }

    public void setMouvementDatas(List<MouvementData> mouvementDatas) {
        this.mouvementDatas = mouvementDatas;
    }
    
    
}
