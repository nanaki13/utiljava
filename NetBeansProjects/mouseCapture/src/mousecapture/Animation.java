/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.awt.Graphics;

/**
 *
 * @author jonathan
 */
public interface Animation {
    public void paintComponent(Graphics g);

    public boolean isFinish();
}
