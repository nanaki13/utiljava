/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application;


/**
 *
 * @author jonathan
 */
public class ApplicationLauncher {
    /**
     *  com.jonathan.application.test.AplicationTest com.jonathan.application.test.ContextTest com.jonathan.application.test.ControleurTest com.jonathan.application.test.VueTest
     * @param args application class,  context class,  controleur class,  vue class, 
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
        Application application;
        Class<?> forName = Class.forName(args[0]);
        application = (Application) forName.newInstance();
        forName = Class.forName(args[1]);
        Context context  = (Context) forName.newInstance();
        forName = Class.forName(args[2]);
        Controleur controleur  = (Controleur) forName.newInstance();
        forName = Class.forName(args[3]);
        Vue vue= (Vue) forName.newInstance();
        
        application.launch(context,controleur,vue);
    }
}
