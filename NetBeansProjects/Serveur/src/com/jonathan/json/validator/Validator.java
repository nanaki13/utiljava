/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorException;

/**
 *
 * @author jonathan
 */
interface Validator {
    public boolean isValid() throws ValidatorException;
    
}
