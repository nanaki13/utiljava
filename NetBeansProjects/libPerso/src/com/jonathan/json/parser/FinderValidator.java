/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.reader.ReaderCompteur;

/**
 *
 * @author jonathan
 */
class FinderValidator {

    public static ValidatorAbstract getGoodValidator(ReaderCompteur rc) {
        char decideur = rc.getLastRead();
        ValidatorAbstract validator = null;
        if (decideur == '"' || decideur == '\'') {
            validator = new ValidatorChaine(rc);

        } else if (decideur == '{') {
            validator = new ValidatorObjet(rc);

        } else if (decideur >= '0' && decideur <= '9' || decideur == '-') {
            validator = new ValidatorNombre(rc);
        } else if (decideur == '[') {
            validator = new ValidatorArray(rc);
        } else if (decideur == 'n') {
            validator = new ValidatorNull(rc);
        } else if (decideur == 't' || decideur == 'f') {
            validator = new ValidatorBoolean(rc);
        }
        return validator;
    }
}
