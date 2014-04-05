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
        boolean openGuillemet = false;
        if(c=='"'){
            openGuillemet = true;
            i++;
            c = input.charAt(i);
        }
        while (true) {
            if( (c >= a && c<= z) || ( c >= A && c <= Z) || (c >= ZERO && c<= NEUF)||c=='_'){
                builder.append(c);
                i++;
                c = input.charAt(i);
            }
            else{
                if(openGuillemet){
                    if(c=='"'){
                        i++;
                        c = input.charAt(i);
                        indiceFin = i;
                        valid = true;
                    }else{
                         c = input.charAt(i);
                        indiceFin = i;
                        valid = false;
                        error = "guillemt clef nom fermé";
                    }
                    return valid;
                    
                } else{
                    valid =true;
                    indiceFin = i;
                    charFin = c;
                    return valid;
                }
            }
        }
    }
    
    
    
    
}
