/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrie;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class UtilGeom {
    
   

    

    public static void reflexion(Vecteur r, Droite d) {
        if(d.isHorizontal()){
            r.y = -r.y;
        } else if (d.isVertical()){
            r.x = -r.x;
        }
        else{
            Double a = d.getA();
            r.x = ((1 - a*a )  * r.x + 2 * a * r.y)/(1 + a*a);
            r.y = ((a*a - 1)  * r.y + 2 * a * r.x)/(1 + a*a);
        }
    }
    
    private UtilGeom() {
        
    }

    ;
    public static int[] getTabInt(int start, int stop, int lenth) {
        int[] ret = new int[lenth];
        int pas = (stop - start) / lenth;

        for (int i = start, j = 0; i < stop; i += pas, j++) {
            ret[j] = i;
        }
        return ret;
    }

    public static List<Contact> pointsDeContact(Rectangle r, Cercle ce) {

        LinkedList<Contact> ret  = new LinkedList();
        Contact c;
        if(r.getDemiDiagonal() + ce.rayon < r.centre.distance(ce.centre)){
            return ret;
        }
        for(Segment s : r.getSegments()){
            Point[] pointsDeContact = pointsDeContact(s, ce);
            if(pointsDeContact != null){
                c = new Contact();
                c.setPoint(Arrays.asList(pointsDeContact));
                c.setSegment(s);
                ret.add(c);
            }
        }
        return ret;
    }

    public static Point[] pointsDeContact(Droite d, Cercle ce) {
        double a ,b,c;
        double[] solution2d;
        double coeffA ;
        double coeffB  ;
        double cx = ce.centre.x;
        double cy = ce.centre.y;
        double r = ce.rayon;
        Point[] ret = new Point[2];
        Point p1 , p2;
        if (d.isHorizontal()) {
            a = 1;
            b = - 2 * cy;
            coeffB  = d.getB();
            c = cx*cx + coeffB * coeffB - 2d * coeffB * cy + cy * cy - r*r;
            solution2d = solution2d(a, b, c);
            if (solution2d == null) {
                return null;
            } else if (solution2d.length == 2) {
                p1 = new Point(solution2d[0],coeffB);
                p2 = new Point(solution2d[1], coeffB);

                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
                if(d.isOnMe(p2)){
                    ret[1] = p2;
                }
//                return new Point[]{new Point(solution2d[0],coeffB), new Point(solution2d[1], coeffB)};
            } else if (solution2d.length == 1) {
                p1 = new Point(solution2d[0],coeffB);
                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
//                return new Point[]{new Point(solution2d[0], coeffB)};
            }

        } else if (d.isVertical()) {
            a = 1;
            b = - 2 * cy;
            double x0 = d.getX0();
            c = cx* cx+ x0 * x0 - 2d * x0 * cy + cy * cy - r*r;
            solution2d = solution2d(a, b, c);
            if (solution2d == null) {
                return null;
            } else if (solution2d.length == 2) {
                p1 = new Point(x0, solution2d[0]);
                p2 = new Point(x0, solution2d[1]);
                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
                if(d.isOnMe(p2)){
                    ret[1] = p2;
                }
//                return new Point[]{new Point(x0, solution2d[0]), new Point(x0, solution2d[1])};
            } else if (solution2d.length == 1) {
                  p1 = new Point(x0, solution2d[0]);
                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
//                return new Point[]{new Point(x0, solution2d[0])};
            }
        } else {
            coeffA = d.getA();
            a = 1 + coeffA * coeffA;
            coeffB  = d.getB();
            b = 2 * (coeffA * coeffB - cx - coeffA * cy);
            c = cx * cx + cy * cy + coeffB * coeffB -2 * coeffB * cy - r*r;
            solution2d = solution2d(a, b, c);
            if (solution2d == null) {
                return null;
            } else if (solution2d.length == 2) {
                 p1 = new Point(solution2d[0], d.image(solution2d[0]));
                p2 = new Point(solution2d[1], d.image(solution2d[1]));
                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
                if(d.isOnMe(p2)){
                    ret[1] = p1;
                }
//                return new Point[]{new Point(solution2d[0], d.image(solution2d[0])), new Point(solution2d[1], d.image(solution2d[1]))};
            } else if (solution2d.length == 1) {
                 p1 = new Point(solution2d[0], d.image(solution2d[0]));
                if(d.isOnMe(p1)){
                    ret[0] = p1;
                }
//                return new Point[]{new Point(solution2d[0], d.image(solution2d[0]))};
            }
        }
        if(ret[0] == null && ret[1] == null)
            return null;
        else if(ret[0] != null && ret[1] == null)
            return new Point[]{ret[0]};
        else if(ret[0] == null && ret[1] != null)
            return new Point[]{ret[1]};
        else
            return new Point[]{ret[0],ret[1]};

    }

    public static double delta(double a, double b, double c) {
        return (b * b) - (4 * a * c);
    }

    public static double[] solution2d(double a, double b, double c) {
        double delta = delta(a, b, c);
        if (delta < 0) {
            return null;
        } else if (delta > 0) {
            return new double[]{(-b + Math.sqrt(delta)) / (2d * a), (-b - Math.sqrt(delta)) / (2d * a)};
        } else {
            return new double[]{-b / 2 * a};
        }

    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);
        Point p = new Point(0, 0);
        Droite d = new Segment(p1, p2);
//        System.out.println(d.getAffineString());
//        System.out.println(d.isOnMe(p));
//        System.out.println(d.image(1));
//        System.out.println(d.antecedant(2));

        Cercle c = new Cercle(p, 1);

//        Point[] pointDeContact = UtilGeom.pointsDeContact(d, c);
//        if (pointDeContact != null) {
//            System.out.println(pointDeContact.length);
//            if (pointDeContact.length > 0) {
//                System.out.println(pointDeContact[0]);
//            }
//            if (pointDeContact.length > 1) {
//                System.out.println(pointDeContact[1]);
//                System.out.println(d.image(pointDeContact[0].x));
//                System.out.println(pointDeContact[0].y);
//                System.out.println(pointDeContact[0].y == d.image(pointDeContact[0].x));
//                System.out.println(d.image(pointDeContact[0].x));
//                
//                System.out.println(d.isOnMe(pointDeContact[0]));
//            }
//        }
        
        Rectangle r = new Rectangle(p, 0.9, 0.9);
        long currentTimeMillis = System.currentTimeMillis();
//        Point[] pointsDeContact = pointsDeContact(r, c);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println("temp : " + (currentTimeMillis2 - currentTimeMillis));
        
        
//         for(Point pp : pointsDeContact){
//            System.out.println(pp);
//        }

    }

}
