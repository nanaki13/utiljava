/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.NumberJson;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorNombre extends ValidatorAbstract{
    

   

    public ValidatorNombre(ReaderCompteur rc) {
        super(rc);
        
    }

  

    @Override
    public boolean processValidation() throws IOException {
 
        boolean pointRenconter=false;
        char c = getLastRead();
        if(c == '-'){
            builder.append('-');
            c = read();
        }
        boolean continu  = true;
        while (continu) {
            if( (c >= ZERO && c<= NEUF)){
                builder.append(c);
                c = read();    
            }
            else if(c==POINT ){
                if(!pointRenconter){
                    builder.append(c);
                    pointRenconter = true;
                }
                else{
                    valid = false;
                    error="un point en trop";
                    return valid;
                } 
                 c = read();    
            }
            else{
                continu = false;
            }
        }
        valid = true;
        joi = new NumberJson(builder.toString());
        return valid;
    }
    
    
    
    
}
