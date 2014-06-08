/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.BooleanJson;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
class ValidatorBoolean extends ValidatorAbstract {

    public ValidatorBoolean(ReaderCompteur rc) {
        super(rc);
    }

    @Override
    public boolean processValidation() throws IOException {
        char[] buff;
        if (getLastRead() == 't') {
            buff = new char[3];
            rc.read(buff, 0, 3);
            if (('t' + (new String(buff))).equals("true")) {
                joi = BooleanJson.TRUE;
                readIfNotRoot();
                return true;
            } else {
                error = "mauvais objet boolean";
                return false;
            }
        } else {
            buff = new char[4];
            rc.read(buff, 0, 4);
            if (('f' + (new String(buff))).equals("false")) {
                joi = BooleanJson.FALSE;
                readIfNotRoot();
                return true;
            } else {
                error = "mauvais objet boolean";
                return false;
            }
        }
    }

}
