/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

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
    public boolean processValidation() throws ValidatorException {
        int i = indiceDebut;
        char c = input.charAt(i);
        boolean continu  = true;
        while (continu) {
            if( c == retourLigne || c ==tabu || c == espace){
                i++;
                if(i>= input.length()){
                    throw new ValidatorException("chaine sans fin");
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
