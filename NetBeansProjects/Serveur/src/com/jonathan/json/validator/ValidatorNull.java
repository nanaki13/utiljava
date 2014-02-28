/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json.validator;

import com.jonathan.json.JsonNull;

/**
 *
 * @author jonathan
 */
class ValidatorNull extends ValidatorAbstract {

    public ValidatorNull(String input, int indiceDebut) {
         super(input, indiceDebut);
    }

    @Override
    public boolean processValidation() throws ValidatorException {
        if(input.substring(indiceDebut, indiceDebut+4).equals("null")){
            joi = JsonNull.NULL;
            indiceFin = indiceDebut + 4;
            charFin = input.charAt(indiceFin);
            return true;
        }
        else{
            error="mauvais objet null";
            return false;
        }
            
    }
    
}
