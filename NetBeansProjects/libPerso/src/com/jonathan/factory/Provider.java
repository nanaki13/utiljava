/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.factory;

/**
 *
 * @author jonathan
 */
public interface Provider<T> {
    public T newInstance();

    public T provide(Object ... params );
}
