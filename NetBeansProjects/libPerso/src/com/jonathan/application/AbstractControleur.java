/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application;

import java.util.Map;

/**
 *
 * @author jonathan
 */
public abstract class AbstractControleur implements Controleur{
    protected Vue vue;
    protected Map<String , Object> allData;
    protected ApplicationInterface application;

    /**
     *
     * @param o
     * @param from
     * @param to
     * @param whoAskingSetData
     * @param whoAskingSetData
     */
    @Override
    public void receiveData(Object o, ApplicatonEntite from, ApplicatonEntite to) {
        if(from == application){
            allData = (Map<String , Object>) o;
        }
    }

 

    @Override
    public void setVue(Vue vue) {
        this.vue = vue;
    }
    
    public <T extends Vue > T getVue(){
        return (T) vue;
    }

    @Override
    public void setApplication(ApplicationInterface application){
        this.application = application;
    }
    
}
