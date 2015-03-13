///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mousecapture;
//
//import java.awt.Rectangle;
//import java.awt.Shape;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.PathIterator;
//import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
//
///**
// *
// * @author jonathan
// */
//public class GrosseLigne implements Shape{
//
//    private Point p1;
//    private Point p2;
//    private int width ;
//    private final int minX;
//    private final int maxX;
//    private final int minY;
//    private final int maxY;
//    private final Rectangle bounds;
//
//    public GrosseLigne(Point p1, Point p2, int width) {
//        this.p1 = p1;
//        this.p2 = p2;
//        this.width = width;
//        minX = Math.min(p1.x, p2.x);
//        maxX = Math.max(p1.x, p2.x);
//        minY = Math.min(p1.y, p2.y);
//        maxY = Math.max(p1.y, p2.y);
//        bounds = new Rectangle(maxX, minX, width, width);
//        
//        
//    }
//    
//    @Override
//    public Rectangle getBounds() {
//        return bounds;
//    }
//
//    @Override
//    public Rectangle2D getBounds2D() {
//        return bounds;
//    }
//
//    @Override
//    public boolean contains(double x, double y) {
//    }
//
//    @Override
//    public boolean contains(Point2D p) {
//    }
//
//    @Override
//    public boolean intersects(double x, double y, double w, double h) {
//    }
//
//    @Override
//    public boolean intersects(Rectangle2D r) {
//    }
//
//    @Override
//    public boolean contains(double x, double y, double w, double h) {
//    }
//
//    @Override
//    public boolean contains(Rectangle2D r) {
//    }
//
//    @Override
//    public PathIterator getPathIterator(AffineTransform at) {
//    }
//
//    @Override
//    public PathIterator getPathIterator(AffineTransform at, double flatness) {
//    }
//    
//}
