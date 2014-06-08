/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorMot extends ValidatorAbstract{
    

   



    ValidatorMot(ReaderCompteur rc) {
        super(rc);
    }

    @Override
    public boolean processValidation() throws IOException {

        if(getLastRead()=='"'){
            ValidatorAbstract v = new ValidatorChaine(rc);
            valid = v.processValidation();
            if(valid){
                builder.append(v.builder);
                return valid;
            }else{
                error= v.error;
                return valid;
            }
        }
        char c;
        builder.append(getLastRead());
        while (true) {
            c = read();
            if( (c >= a && c<= z) || ( c >= A && c <= Z) || (c >= ZERO && c<= NEUF)||c=='_'){
                builder.append(c);
            }
            else{
                    valid =true;
                    return valid;
            }
        }
    }
    
    
    
    
}
