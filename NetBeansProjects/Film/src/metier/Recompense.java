/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.Date;

/**
 *
 * @author jonathan
 */
public class Recompense {
    private int id;
    private String label;
    private Date anne;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getAnne() {
        return anne;
    }

    public void setAnne(Date anne) {
        this.anne = anne;
    }
    
}
