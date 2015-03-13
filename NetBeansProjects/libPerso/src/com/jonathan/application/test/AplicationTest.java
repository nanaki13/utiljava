/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.application.test;

import com.jonathan.application.Application;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jonathan
 */
public class AplicationTest extends Application{

    @Override
    public void started() {
        Set<Map.Entry<String, Object>> entrySet = context.getAllData().entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            System.out.println(entry);
        }
        
    }
    
}
