/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrie;

import geometrie.Point;
import geometrie.Segment;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Contact {
    private List<Point> points;
    private Segment segment;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoint(List<Point> points) {
        this.points = points;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Contact(List<Point> point, Segment segment) {
        this.points = point;
        this.segment = segment;
    }

    public Contact(List<Point> points) {
        this.points = points;
    }

    public Contact(Segment segment) {
        this.segment = segment;
    }
    
     public Contact() {
         this.points = new LinkedList<>();
    }
     
    
}
