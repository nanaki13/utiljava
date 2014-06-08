/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorChaine;
import com.jonathan.json.parser.ValidatorAbstract;

/**
 *
 * @author jonathan
 */
class ValidatorMot extends ValidatorAbstract{
    

   

    public ValidatorMot(String input, int indiceDebut) {
        super(input, indiceDebut);
        
    }

    @Override
    public boolean processValidation() {
        int i = indiceDebut;
        char c = input.charAt(i);
        if(c=='"'){
            ValidatorAbstract v = new ValidatorChaine(input, indiceDebut);
            valid = v.processValidation();
            charFin = v.charFin;
            indiceFin = v.indiceFin;
            if(valid){
                builder.append(v.builder);
                return valid;
            }else{
                error= v.error;
                return valid;
            }
        }
        while (true) {
            if( (c >= a && c<= z) || ( c >= A && c <= Z) || (c >= ZERO && c<= NEUF)||c=='_'){
                builder.append(c);
                i++;
                c = input.charAt(i);
            }
            else{
                    valid =true;
                    indiceFin = i;
                    charFin = c;
                    return valid;
            }
        }
    }
    
    
    
    
}
