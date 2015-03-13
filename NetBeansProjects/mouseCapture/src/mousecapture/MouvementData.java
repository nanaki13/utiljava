/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Color;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class MouvementData implements Serializable{
    private static final long serialVersionUID = 7625638177863792604l;
    private final List<Point> points = new LinkedList<>();
    private int start;
//    private String groupName;
    private transient GroupeMouvement group;
    private int nb;
    private String nom;

    public String getNom() {
        return nom;
    }

    public GroupeMouvement getGroup() {
        return group;
    }

    public void setGroup(GroupeMouvement group) {
        this.group = group;
    }

//    public String getGroupName() {
//        return groupName;
//    }

//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    
    public void setNom(String nom) {
        this.nom = nom;
    }
    private Color color = Color.GREEN;

    public  int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    public void add(Point p){
        points.add(p);
    }

    public void setStart(int start) {
        this.start = start;
    }


    public List<Point> getPoints() {
        return points;
    }

    public int getStart() {
        return start;
    }

    public Color getCouleur() {
        return color;
    }
    
    public String getHtmlColor(){
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
    
    
    
}
