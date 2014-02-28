/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

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
        boolean continu  = true;
        while (continu) {
            if( (c >= a && c<= z) || ( c >= A && c <= Z) || (c >= ZERO && c<= NEUF)||c=='_'){
                builder.append(c);
                i++;
                c = input.charAt(i);
            }
            else{
                valid = false;
                indiceFin = i;
                charFin = c;
                return valid;
            }
        }
        indiceFin = i;
        charFin = c;
        return valid;
    }
    
    
    
    
}
