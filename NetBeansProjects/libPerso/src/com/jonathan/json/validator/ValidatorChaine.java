/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.TextJson;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jonathan.lib.string.StringException;
import com.jonathan.lib.string.StringTool;

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
        try {
            int stop = StringTool.getIndexEnd(input, i);
            String data = StringTool.replaceEscapeCharByTrueChar(input, i+1, stop-1);
            joi = new TextJson(data);
            builder.append(data);
            indiceFin = stop + 1;
            charFin = input.charAt(indiceFin);
            return true;
        } catch (StringException ex) {
            Logger.getLogger(ValidatorChaine.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getMessage();
            charFin = ex.getCchar();
            indiceFin = ex.getIndex();
            return false;
        }
        
        
    }
    
    
    
    
}
