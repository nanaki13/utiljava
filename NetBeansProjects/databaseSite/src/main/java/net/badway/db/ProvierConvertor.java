/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db;

import java.lang.reflect.Field;

/**
 *
 * @author jonathan
 */
interface ProvierConvertor {

    public ObjectConvertor getObjectFieldAndMethod(Class<? > aClass) ;
}
