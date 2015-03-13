/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static mousecapture.Ardoise.background;
import mousecapture.core.MouseCapture;
import static mousecapture.core.MouseCapture.mvts;

/**
 *
 * @author jonathan
 */
public class DynamicAnimation implements Animation {

    private long timeRec = 0;
    private Long initTime = null;
    private Set<MouvementData> started = new HashSet<MouvementData>();
    private Set<MouvementData> finished = new HashSet<MouvementData>();
    private boolean init = false;
    private int countFinish = 0;

    public void paintComponent(Graphics g) {
        if (initTime == null) {
            initTime = System.currentTimeMillis();
            timeRec = 0;
        }
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, null, null);
        for (MouvementData m : mvts) {
            if (!started.contains(m) && !finished.contains(m)) {
                if (m.getStart() < timeRec) {
                    started.add(m);
                }
            }
        }
        Iterator<MouvementData> iteratorMvt = finished.iterator();
        while (iteratorMvt.hasNext()) {
            MouvementData started1 = iteratorMvt.next();
            List<Point> points = started1.getPoints();
           
            g2d.setColor(started1.getCouleur());
            if (points.size() < 2) {
                iteratorMvt.remove();
            }
            for (int i = 0; i < points.size(); i++) {
                if (i != 0) {
                        Point a = points.get(i);
                        Point b = points.get(i - 1);
                        g2d.drawLine(a.x, a.y, b.x, b.y);

                }

            }

        }
        iteratorMvt = started.iterator();
        while (iteratorMvt.hasNext()) {
            MouvementData started1 = iteratorMvt.next();
            List<Point> points = started1.getPoints();
            int somme = started1.getStart();
            g2d.setColor(started1.getCouleur());
            if (points.size() < 2) {
                iteratorMvt.remove();
                finished.add(started1);
            }
            for (int i = 0; i < points.size(); i++) {
                if (i != 0) {
                    somme += MouseCapture.dt;
                    if (somme < timeRec) {
                        Point a = points.get(i);
                        Point b = points.get(i - 1);
                        g2d.drawLine(a.x, a.y, b.x, b.y);
                        if (i == points.size() - 1) {
                            iteratorMvt.remove();
                            finished.add(started1);
                        }
                    }

                }

            }

        }

        timeRec = System.currentTimeMillis() - initTime;
    }

    @Override
    public boolean isFinish() {
        boolean finish = mvts.size() == finished.size();
     
        if (finish) {
            finished.clear();
            initTime = null;
        }
        return finish;
    }
}
