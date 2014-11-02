/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrie;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class PrinterConsol implements Printer{
    public int limitY;
    public int limitX;
    @Override
    public void print(Point2D[] points) {
        for(Point2D p : points){
            try {
                print(p);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrinterConsol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void print(Point2D point) {
        
        for( int y =  limitY; y !=-1; y--){
            for(int x = 0 ; x < limitX ; x++)
                if(point.x == x && point.y == y){
                    System.out.print('X');
                }
                else{
                    System.out.print(' ');
                }
            System.out.println();        
        }
    }
    
}
