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
public interface Controleur extends ApplicatonEntite{

    public void receiveData(Object o, ApplicatonEntite from, ApplicatonEntite to);

    public void run();

    void setVue(Vue vue);

    void setApplication(ApplicationInterface application);

    public void userRequest(UserRequest userResquest);
    
}
