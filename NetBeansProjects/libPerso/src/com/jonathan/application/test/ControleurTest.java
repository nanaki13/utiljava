/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application.test;

import com.jonathan.application.AbstractControleur;
import com.jonathan.application.ApplicatonEntite;
import com.jonathan.application.Controleur;
import com.jonathan.application.Source;
import com.jonathan.application.Vue;

/**
 *
 * @author jonathan
 */
public class ControleurTest extends AbstractControleur{

//    @Override
//    public void setData(Object o, Source whoAskingSetData) {
//    }

    @Override
    public void run() {
        Object get = allData.get("labelData");
        vue.receiveData(get);
        vue.show();
    }

 

   
    
}
