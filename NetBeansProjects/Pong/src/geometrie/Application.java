/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrie;

import pong.swing.PongJPanel;
import pong.PongEngine;

/**
 *
 * @author jonathan
 */
public class Application {
    private final PongEngine engine;
    private final PongJPanel vue;

    public Application() {
        this.engine = new PongEngine();
        this.vue = new PongJPanel();    
    }
    public void start(){
        vue.setEngine(engine);
        vue.start();
    }
    
    public static void main(String[] args){
        Application application = new Application();
        application.start();
    }
    
}
