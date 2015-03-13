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
public interface Context extends Map<String,Object>,ApplicatonEntite{

    public <T> T getObject(String name);

    public Map<String,Object> getAllData();
    public void saveData(String name);
    
}
