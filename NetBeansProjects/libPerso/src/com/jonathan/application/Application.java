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
public abstract class Application implements ApplicatonEntite,ApplicationInterface{
    protected Application application;
    protected Context context;
    protected Controleur controleur;
    protected Vue vue;
    private final Object monitor = new Object();
 
    
    void init(Context context , Controleur controleur , Vue vue){
        this.context = context;
        this.vue = vue;
        this.controleur = controleur;
    }

    void launch(Context context, Controleur controleur, Vue vue) throws InterruptedException {
//         synchronized(monitor){
//            monitor.wait();
//        }
        init(context, controleur, vue);
        Object o =context.getAllData();
         controleur.setApplication(this);
        controleur.receiveData(o,this,controleur);
        vue.setControleur(controleur);
        controleur.setVue(vue);
        controleur.run();
        started();
    }

    public abstract void started() ;
}
