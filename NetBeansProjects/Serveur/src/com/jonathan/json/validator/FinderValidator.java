/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.parser.ValidatorChaine;
import com.jonathan.json.parser.ValidatorNull;
import com.jonathan.json.parser.ValidatorArray;
import com.jonathan.json.parser.ValidatorBoolean;
import com.jonathan.json.parser.ValidatorAbstract;
import com.jonathan.json.parser.ValidatorNombre;
import com.jonathan.json.parser.ValidatorObjet;

/**
 *
 * @author jonathan
 */
public class FinderValidator {

    public static ValidatorAbstract getGoodValidator(ValidatorAbstract validatorIn) {
        char decideur = validatorIn.getLastRead();
        int i = validatorIn.getIndiceFin();
        String input = validatorIn.getInput();
        ValidatorAbstract validator = null;
        if (decideur == '\"') {
            validator = new ValidatorChaine(input, i);

        } else if (decideur == '{') {
            validator = new ValidatorObjet(input, i);

        } else if (decideur >= '0' && decideur <= '9') {
            validator = new ValidatorNombre(input, i);
        } else if (decideur == '[') {
            validator = new ValidatorArray(input, i);
        } else if (decideur == 'n') {
            validator = new ValidatorNull(input, i);
        } else if (decideur == 't' || decideur == 'f') {
            validator = new ValidatorBoolean(input, i);
        }
        return validator;
    }

    public static ValidatorAbstract getGoodValidator( int i, String input) {
        char decideur = input.charAt(i);
        ValidatorAbstract validator = null;
        if (decideur == '\"') {
            validator = new ValidatorChaine(input, i);

        } else if (decideur == '{') {
            validator = new ValidatorObjet(input, i);

        } else if (decideur >= '0' && decideur <= '9') {
            validator = new ValidatorNombre(input, i);
        } else if (decideur == '[') {
            validator = new ValidatorArray(input, i);
        } else if (decideur == 'n') {
            validator = new ValidatorNull(input, i);
        } else if (decideur == 't' || decideur == 'f') {
            validator = new ValidatorBoolean(input, i);
        }
        return validator;
    }
}
