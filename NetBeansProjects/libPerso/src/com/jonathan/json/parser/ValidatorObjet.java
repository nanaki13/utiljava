/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.JsonObject;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TextJson;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
public class ValidatorObjet extends ValidatorAbstract {
    
    public static void main(String[] args) throws ValidatorException, IOException {
        JsonObject jo = new JsonObject();
        JsonObject jo2 = new JsonObject();
        jo2.put("bli", "deseded");
        NumberJson numberJson = new NumberJson(3);
        ArrayJson aj = new ArrayJson();
        aj.add(numberJson);
        aj.add(new TextJson("bab abab"));
        jo.put("iiii", new NumberJson("-3.2"));
        jo.put("nom", "paul");
        jo.put("obj", jo2);
        jo.put("array", aj);
        jo.put("bool", true);
        jo.setGuillemetOnKey(true);
        System.out.println(jo.toStringJsonPretty());
        ParserJson parserJson = new ParserJson(jo.toStringJsonPretty());
        JsonObject parse = (JsonObject) parserJson.parse();
        parse.setGuillemetOnKey(true);
        System.out.println(parse.toStringJsonPretty());



    }


    public ValidatorObjet(ReaderCompteur rc) {
        super(rc);
    }
    private JsonObject jsonObject;
    @Override
    public boolean processValidation() throws IOException  {
 
        char c;
        jsonObject = new JsonObject();
        String key;
        while (true) {
            rc.readNextNoWhite();
            ValidatorAbstract validatorMot = new ValidatorMot(rc);
            validatorMot.processValidation();
            if(!validatorMot.valid){
                error  =validatorMot.error;
                return false;
            }
            key = validatorMot.builder.toString();
//            System.out.println(key);
            if (key.isEmpty()) {
                valid = false;
                error = "pas de clef";
                return valid;
            } else {
                ValidatorAbstract validator ;
                if(getLastRead() != ':')
                    readNextNoWhite();
                if (getLastRead() == ':') {
                    rc.readNextNoWhite();
                    validator = FinderValidator.getGoodValidator(rc);
                    if(validator == null){   
                        error = "valeur d'objet non conforme";
                        valid = false;
                        return valid;
                    }
//                    System.out.println(key);
                    valid = validator.processValidation();
                    if (!valid) {
                        error = validator.error;
                        return valid;
                    }
                    jsonObject.put(key, validator.joi);
//                    System.out.println("joi  "+validator.joi.toStringJson());
//                    System.out.println("***  "+getLastRead());
                    if(getLastRead()==' ' || getLastRead()=='\r' || getLastRead()=='\t' || getLastRead()=='\n')
                        readNextNoWhite();
                    
                    if (getLastRead() == ',') {
                    } else if (getLastRead() == '}') {
                        error = "aucune erreur";
                        valid = true;
                        joi=jsonObject;
                        readIfNotRoot();
                        return valid;
                    } else {
                        error = "pas de ',' ou de '}' après un objet";
                        valid = false;
                        return valid;
                    }
                } else {
                    error = " clef nom précédé de : ";
                    valid = false;
                    return false;
                }
            }
        }
    }
    public JsonObject getJsonObject(){
        return (JsonObject) joi;
    }
}
