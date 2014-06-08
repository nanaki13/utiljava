/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.TextJson;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jonathan.lib.string.StringException;
import com.jonathan.lib.string.StringTool;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorChaine extends ValidatorAbstract{
    



    ValidatorChaine(ReaderCompteur rc) {
        super(rc);
    }


    @Override
    public boolean processValidation() throws IOException {
            String data = StringTool.replaceEscapeCharByTrueChar(rc);
            joi = new TextJson(data);
            builder.append(data);
            readIfNotRoot();
            return true;   
    }
    
    
    
    
}
