/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.test;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class PojoTest {
    
    private String name;
    private List<Integer> poids;
    private int age;
    private boolean con;

    public PojoTest() {
        this.name = "toto";
        poids = Arrays.asList(23,42,53,53);
        age = 12;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPoids() {
        return poids;
    }

    public void setPoids(List<Integer> poids) {
        this.poids = poids;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCon() {
        return con;
    }

    public void setCon(boolean con) {
        this.con = con;
    }
    
    
    
    
}
