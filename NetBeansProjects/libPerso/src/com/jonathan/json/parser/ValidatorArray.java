/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.ArrayJson;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorArray extends ValidatorAbstract {

    public ValidatorArray(ReaderCompteur rc) {
        super(rc);
    }

    @Override
    public boolean processValidation() throws IOException {

        ValidatorAbstract validator;
        ArrayJson aj = new ArrayJson();
        joi = new ArrayJson();
        char c;
        readNextNoWhite();
        if(getLastRead() == ']'){
            return true;
        }
        while (true) {
            
            validator = FinderValidator.getGoodValidator(rc);
            if (validator == null) {
                error = "valeur d'objet non conforme dans une array";
                valid = false;
                return valid;
            }

            valid = validator.processValidation();
            if (!valid) {
                error = validator.error;

                return valid;
            } else {
                aj.add(validator.joi);
                 if(getLastRead()==' ' || getLastRead()=='\r' || getLastRead()=='\t' || getLastRead()=='\n')
                        readNextNoWhite();
                 c = getLastRead();
                if (c == ']') {
                    error = "aucune erreur";
                    valid = true;
                    joi = aj;
                    readIfNotRoot();
                    return valid;
                } else if (c == ',') {
                    
                } else {
                    error = "caractère autre que ',' ou fin d'array ']' dans un objet de type array";
                    return false;
                }  
            }
            readNextNoWhite();
        }
    }
}
