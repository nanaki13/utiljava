/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application.test;

import com.jonathan.application.AbstractContext;
import com.jonathan.application.AbstractContext;

/**
 *
 * @author jonathan
 */
public class ContextTest extends AbstractContext{

    @Override
    public void saveData(String name) {
    }
    public ContextTest(){
       
        super();
        put("labelData", "coucou");
    }
    
}
