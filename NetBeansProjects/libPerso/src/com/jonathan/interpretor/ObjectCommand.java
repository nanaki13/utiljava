/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.interpretor;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
interface ObjectCommand {
    default public Object exec(Command c){
        try {
            return this.getClass().getMethod(c.getCommandName(), Command.class).invoke(this, c);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ObjectCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
