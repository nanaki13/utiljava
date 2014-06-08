/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json.parser;

import com.jonathan.json.JsonNull;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorNull extends ValidatorAbstract {

    public ValidatorNull(ReaderCompteur rc) {
         super(rc);
    }

    @Override
    public boolean processValidation() throws IOException  {
        char[] buff = new char[3]; 
        rc.read(buff, 0, 3);
        if(('n'+(new String(buff))).equals("null")){
            joi = JsonNull.NULL;
            readIfNotRoot();
            return true;
        }
        else{
            error="mauvais objet null";
            return false;
        }
            
    }
    
}
