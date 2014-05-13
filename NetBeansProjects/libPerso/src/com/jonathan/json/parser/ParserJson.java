/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.JsonObject;
import com.jonathan.json.validator.ValidatorException;
import com.jonathan.json.validator.ValidatorObjet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ParserJson {
    private final static Logger logger = Logger.getLogger(ParserJson.class.getName());
    private  String input;
    public ParserJson(){}
    public ParserJson(String input){
        this.input = input;
    }
    public ParserJson(File f){
        
        try {
            
            FileReader fileReader = new FileReader(f);
            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder builder = new StringBuilder(500);
            String line =  br.readLine();
            while(line!=null){
                builder.append(line);
                line =  br.readLine();
            }
            this.input = builder.toString();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
    }
    public JsonObject parse() throws ValidatorException{
        if(input == null) throw new ValidatorException("input null");
        ValidatorObjet v = new ValidatorObjet(input, 0);
        if(v.processValidation() == true){
            return v.getJsonObject();
        }else{
            throw new ValidatorException("erreur durant le parsing : "+v.getError()
                    + " sur le caractère : "+v.getCharFin());
        }
        
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
    
}
