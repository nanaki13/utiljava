/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.JsonObject;
import com.jonathan.json.JsonObjectInterface;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TextJson;

/**
 *
 * @author jonathan
 */
public class ValidatorObjet extends ValidatorAbstract {

    public ValidatorObjet(String input, int indiceDebut) {
        super(input, indiceDebut);

    }
    private JsonObject jsonObject;
    @Override
    public boolean processValidation() throws ValidatorException {
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
        boolean continu;
        continu = true;
        while (true) {

            validator = new ValidatorWhite(input, i);
            validator.processValidation();
            ValidatorAbstract validatorMot = new ValidatorMot(input, validator.indiceFin);
            validatorMot.processValidation();
            
            if (validatorMot.getIndiceFin() == validator.getIndiceFin()) {
                valid = false;
                charFin = input.charAt(i);
                indiceFin = i;
                error = "pas de clef";
                return valid;
            } else {
                key = validatorMot.builder.toString();
                validator = new ValidatorWhite(input, validatorMot.getIndiceFin());
                validator.processValidation();
                if (validator.getCharFin() == ':') {
                    validator = new ValidatorWhite(input, validator.getIndiceFin() + 1);
                    validator.processValidation();
                    char decideur = validator.getCharFin();
                    i = validator.getIndiceFin();
                    if (decideur == '\"') {
                        validator = new ValidatorChaine(input, validator.getIndiceFin() + 1);

                    } else if (decideur == '{') {
                        validator = new ValidatorObjet(input, i);

                    } else if (decideur >= '0' && decideur <= '9') {
                        validator = new ValidatorNombre(input, i);
                    } else if (decideur == '[') {
                        validator = new ValidatorArray(input, i);
                    }else if (decideur == 'n') {
                        validator = new ValidatorNull(input,i);
                    }else{
                        charFin = validator.charFin;
                        indiceFin = validator.getIndiceFin();
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
