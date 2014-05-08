/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.JsonObject;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TextJson;
import parser.ParserJson;

/**
 *
 * @author jonathan
 */
public class ValidatorObjet extends ValidatorAbstract {
    
    public static void main(String[] args) throws ValidatorException {
        JsonObject jo = new JsonObject();
        JsonObject jo2 = new JsonObject();
        jo2.put("bli", "deseded");
        NumberJson numberJson = new NumberJson(3);
        ArrayJson aj = new ArrayJson();
        aj.add(numberJson);
        aj.add(new TextJson("bab abab"));
        jo.put("iiii", new NumberJson("3.2"));
        jo.put("nom", "paul");
        jo.put("obj", jo2);
        jo.put("array", aj);
        jo.setGuillemetOnKey(false);
        System.out.println(jo.toStringJsonPretty());
        ParserJson parserJson = new ParserJson(jo.toStringJsonPretty());
        JsonObject parse = parserJson.parse();
        parse.setGuillemetOnKey(true);
        System.out.println(parse.toStringJsonPretty());



    }

    public ValidatorObjet(String input, int indiceDebut) {
        super(input, indiceDebut);

    }
    private JsonObject jsonObject;
    @Override
    public boolean processValidation()  {
        char c;
        int i = indiceDebut;
        jsonObject = new JsonObject();
        String key;
        ValidatorAbstract validator = new ValidatorWhite(input, i);
        validator.processValidation();
        if (validator.charFin != '{') {
            error = "pas d'accolade ouvrante";
            charFin = validator.charFin;
            return false;
        }
        i = validator.getIndiceFin() + 1;
        while (true) {

            validator = new ValidatorWhite(input, i);
            validator.processValidation();
//            System.out.println("validator.charFin : "+validator.charFin);
            ValidatorAbstract validatorMot = new ValidatorMot(input, validator.indiceFin);
            validatorMot.processValidation();
            if(!validatorMot.valid){
                error  =validatorMot.error;
                charFin = validatorMot.charFin;
                indiceFin = validatorMot.indiceFin;
                return false;
            }
//            System.out.println(validatorMot.getIndiceFin()+" **** "+validator.getIndiceFin());
            if (validatorMot.getIndiceFin() == validator.getIndiceFin()) {
                valid = false;
                charFin = input.charAt(i);
                indiceFin = i;
                error = "pas de clef";
                return valid;
            } else {
                key = validatorMot.builder.toString();
//                System.out.println("key : "+key);
                validator = new ValidatorWhite(input, validatorMot.getIndiceFin());
                validator.processValidation();
                if (validator.getCharFin() == ':') {
                    validator = new ValidatorWhite(input, validator.getIndiceFin() + 1);
                    validator.processValidation();
                    charFin = validator.charFin;
                    indiceFin = validator.getIndiceFin();
                    validator = FinderValidator.getGoodValidator(validator);
                    if(validator == null){   
                        error = "valeur d'objet non conforme";
                        valid = false;
                        return valid;
                    }
                    valid = validator.processValidation();
                    if (!valid) {
                        charFin = validator.charFin;
                        error = validator.error;
                        return valid;
                    }
                    jsonObject.put(key, validator.joi);
                    validator = new ValidatorWhite(input, validator.getIndiceFin());
                    validator.processValidation();
                    c = validator.getCharFin();
                    if (c == ',') {
                        i = validator.getIndiceFin() + 1;
                    } else if (c == '}') {
                        indiceFin = validator.getIndiceFin()+1;
                        charFin = c;
                        error = "aucune erreur";
                        valid = true;
                        joi=jsonObject;
                        return valid;
                    } else {
                        charFin = c;
                        indiceFin = validator.getIndiceFin();
                        error = "pas de ',' ou de '}' après un objet";
                        valid = false;
                        return valid;
                    }
                } else {
                    charFin = validator.charFin;
                    indiceFin = validator.indiceFin;
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
