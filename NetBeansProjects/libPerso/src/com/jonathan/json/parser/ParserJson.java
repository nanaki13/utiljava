/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.JsonObject;
import com.jonathan.json.JsonObjectInterface;
import com.jonathan.reader.ReaderCompteur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ParserJson {

    private final static Logger logger = Logger.getLogger(ParserJson.class.getName());

    private ReaderCompteur rc;

    public ParserJson(InputStream is) {
        rc = new ReaderCompteur(new BufferedReader(new InputStreamReader(is)));
    }

    public ParserJson(String input) {
        rc = new ReaderCompteur(new StringReader(input));
    }

    public ParserJson(File f) throws FileNotFoundException {
        rc = new ReaderCompteur(new BufferedReader(new FileReader(f)));
    }

    public ParserJson(Reader r) {
        rc = new ReaderCompteur(r);
    }

    public JsonObjectInterface parse() throws  IOException, ValidatorException {
        rc.readNextNoWhite();
        ValidatorAbstract goodValidator = FinderValidator.getGoodValidator(rc);
        goodValidator.setRoot(true);
        if (!goodValidator.processValidation()) {
            rc.close();
            throw new ValidatorException("erreur durant le parsing : " + goodValidator.getError() + " sur le caractère : " + rc.getLastRead() + " ligne : " + rc.getNbLigne() + " col : " + rc.getNbCharDeLigne());
        } else {
            rc.close();
            return goodValidator.getJson();
        }
    }

}
