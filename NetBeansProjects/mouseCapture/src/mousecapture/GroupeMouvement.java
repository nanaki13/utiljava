/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jonathan
 */
public class GroupeMouvement implements Serializable{
     private static final long serialVersionUID = 1l;
    private final List<MouvementData> lMvt = new ArrayList<>();
    private String name;

    public GroupeMouvement(String name) {
        this.name = name;
    }

    public GroupeMouvement() {
    }

    public List<MouvementData> getlMvt() {
        return lMvt;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupeMouvement other = (GroupeMouvement) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
