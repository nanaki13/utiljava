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
public class UserRequest<T> {
   
    private T request;
    public void setRequest(T request ){
        this.request=request;
    }
    public T getRequest(){
        return request;
    }

    public UserRequest(T request) {
        this.request = request;
    }
    
    
}
