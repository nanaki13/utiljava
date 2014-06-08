/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorAbstract;

/**
 *
 * @author jonathan
 */
class ValidatorWhite extends ValidatorAbstract{
    

    private static final char retourLigne = '\n';
    private static final char espace = ' ';
    private static final char tabu = '\t';

    public ValidatorWhite(String input, int indiceDebut) {
        super(input, indiceDebut);
    }
 


    @Override
    public boolean processValidation() {
        int i = indiceDebut;
        char c = input.charAt(i);
        boolean continu  = true;
        while (continu) {
            if( c == retourLigne || c ==tabu || c == espace){
                i++;
                if(i>= input.length()){
                    error = "chaine sans fin";
                    indiceFin = i;
                    charFin= input.charAt(i-1);
                    return false;
                }
                c = input.charAt(i);
            }
            else{
                continu = false;
            }
        }
        indiceFin = i;
        charFin = c;
        return true;
    }


    
    
    
    
}
