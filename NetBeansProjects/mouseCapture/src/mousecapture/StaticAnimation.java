/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import static mousecapture.Ardoise.background;
import mousecapture.core.MouseCapture;
import static mousecapture.core.MouseCapture.mvts;

/**
 *
 * @author jonathan
 */
class StaticAnimation implements Animation {

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, null, null);
        mvts.stream().forEach((mvt) -> {
            List<Point> points = mvt.getPoints();
            g2d.setColor(mvt.getCouleur());
            for (int i = 0; i < points.size(); i++) {
                if (i != 0) {
                   
                    Point a = points.get(i);
                    Point b = points.get(i - 1);
                    Line2D.Float lFloat = new Line2D.Float(new Point2D.Float(a.x,a.y),new Point2D.Float(b.x,b.y));
                    g2d.draw(lFloat);
//                    g2d.drawLine(a.x, a.y, b.x, b.y);
//                    g2d.drawLine(a.x, a.y, b.x, b.y);
//                    g2d.drawLine(a.x+1, a.y+1, b.x+1, b.y+1);
//                    g2d.drawLine(a.x+-1, a.y-1, b.x-1, b.y-1);
//                    g2d.drawLine(a.x+1, a.y+1, b.x+1, b.y+1);
//                        g2d.drawPolygon(new Polygon(new int[] {}, ypoints, i));Rect(a.x-1, a.y-1, 3, 3);
//                    g2d.drawLine(a.x, a.y, b.x, b.y);
//                    g2d.drawLine(a.x+1, a.y+1, b.x+1, b.y+1);
                }
                else{
                    if(mvt.getNom()!=null && !mvt.getNom().isEmpty()){
                        Point a = points.get(i);
                        g2d.drawString(mvt.getNom(), a.x, a.y);
                    }
                   
                }

            }
        });
        
    }

    @Override
    public boolean isFinish() {
        return false;
    }
    

}


