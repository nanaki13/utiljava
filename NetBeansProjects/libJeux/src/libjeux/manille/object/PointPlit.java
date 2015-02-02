/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.object;

import libjeux.manille.object.JoueurInterface;

/**
 *
 * @author jonathan
 */
public class PointPlit {
    final JoueurInterface meneur;
    final int point;

    public JoueurInterface getMeneur() {
        return meneur;
    }

    public int getPoint() {
        return point;
    }
    

    PointPlit(JoueurInterface meneur, int point) {
        this.meneur = meneur;
        this.point = point;
    }
    
}
