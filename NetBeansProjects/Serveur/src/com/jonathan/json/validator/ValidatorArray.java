/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.ArrayJson;

/**
 *
 * @author jonathan
 */
class ValidatorArray extends ValidatorAbstract {

    public ValidatorArray(String input, int indiceDebut) {
        super(input, indiceDebut);
    }

    @Override
    public boolean processValidation() throws ValidatorException {
        char c = input.charAt(indiceDebut);
        ValidatorAbstract validator ;
        ArrayJson aj = new ArrayJson();
        joi=new ArrayJson();
        if(c!='['){
            error = "pas de parenthèse ouvrante";
            return false;
        }
        else{
            int i = StringTool.nextNonWhite(input, indiceDebut+1);
            c = input.charAt(i);
            while(true){
                if(c=='{'){
                    validator = new ValidatorObjet(input, i);
                }
                else if(c<=NEUF && c>=ZERO){
                    validator = new ValidatorNombre(input, i);
                }else if(c=='"'){
                    validator = new ValidatorChaine(input, i+1);
                }else if(c=='['){
                    validator = new ValidatorArray(input, i);
                }
                else{
                    error = "pas de caractère valable pour la détermination d'une valeur dans une array";
                    charFin = c;
                    valid = false;
                    return valid;
                }
                valid = validator.processValidation();
                if (!valid) {
                    error = validator.error;
                    charFin = validator.charFin;
                    indiceFin = validator.indiceFin;
                    return valid;
                }
                
                else{
                    aj.add(validator.joi);
                    i = StringTool.nextNonWhite(input, validator.getIndiceFin());
                    c = input.charAt(i);
                    if(c == ']'){
                        indiceFin = i+1;
                        error = "aucune erreur";
                        valid = true;
                        joi=aj;
                        return valid;
                    }
                    else if(c == ','){
                        i = StringTool.nextNonWhite(input,i+1);
                    }
                    else{
                        error = "caractère autre que ',' ou fin d'array ']' dans un objet de type arret";
                        charFin = c;
                        return false;
                    }
                    c = input.charAt(i);   
                }
            }
        }
    }
    
}
