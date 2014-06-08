/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorAbstract;
import com.jonathan.json.NumberJson;

/**
 *
 * @author jonathan
 */
class ValidatorNombre extends ValidatorAbstract{
    

   

    public ValidatorNombre(String input, int indiceDebut) {
        super(input, indiceDebut);
        
    }

  

    @Override
    public boolean processValidation() {
        int i = indiceDebut;
        boolean pointRenconter=false;
        char c = input.charAt(i);
        boolean continu  = true;
        while (continu) {
            if( (c >= ZERO && c<= NEUF)){
                i++;
                builder.append(c);
                c = input.charAt(i);
                
            }
            else if(c==POINT ){
                if(!pointRenconter){
                    builder.append(c);
                    pointRenconter = true;
                }
                else{
                    valid = false;
                    error="un point en trop";
                    indiceFin = i;
                    charFin = c;
                    return valid;
                } 
                 i++;
                 c = input.charAt(i);
            }
            else{
                continu = false;
            }
        }
        valid = true;
        indiceFin = i;
        charFin = c;
        joi = new NumberJson(builder.toString());
        return valid;
    }
    
    
    
    
}
