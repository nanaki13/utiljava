/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorAbstract;
import com.jonathan.json.TextJson;

/**
 *
 * @author jonathan
 */
class ValidatorChaine extends ValidatorAbstract{
    



    public ValidatorChaine(String input, int indiceDebut){
        super(input, indiceDebut);
        
    }


    @Override
    public boolean processValidation() {
        int i = indiceDebut;
        char c = input.charAt(i);
        System.out.println("validateur chaine : "+c);
        if(c!='"'){
            indiceFin=i;
            charFin=c;
            error = "pas d'ouverture de chaine";
            return false;
        }else{
            i++;
            c = input.charAt(i);
        }
        while (true) {
            if(c == '\\'){
                builder.append('\\');
                i = i+1;
                c = input.charAt(i);
            }else if( c == '"'){
               valid=true;
                indiceFin = ++i;
                charFin = input.charAt(i);
                joi = new TextJson(builder.toString());
                return valid;
            }
            builder.append(c);
            i++;
            if(i==input.length()){
               valid = false;
               error="chaine sans fin";
               return valid;
            }
            c = input.charAt(i);
        }
        
    }
    
    
    
    
}
