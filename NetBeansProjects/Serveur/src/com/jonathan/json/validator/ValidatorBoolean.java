/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorAbstract;
import com.jonathan.json.BooleanJson;

/**
 *
 * @author jonathan
 */
class ValidatorBoolean extends ValidatorAbstract {

    public ValidatorBoolean(String input, int indiceDebut) {
         super(input, indiceDebut);
    }

    @Override
    public boolean processValidation()  {
        if(input.substring(indiceDebut, indiceDebut+4).equals("true")){
            joi = BooleanJson.TRUE;
            indiceFin = indiceDebut + 4;
            charFin = input.charAt(indiceFin);
            return true;
        }else if(input.substring(indiceDebut, indiceDebut+5).equals("false")){
            joi = BooleanJson.FALSE;
            indiceFin = indiceDebut + 5;
            charFin = input.charAt(indiceFin);
            return true;
        }
        else{
            error="mauvais objet boolean";
            indiceFin = indiceDebut;
            return false;
        }
            
    }
    
}
