/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author jonathan
 */
public class ReaderCompteur extends Reader {

    private final Reader reader;
    private int nbLigne = 1;
    private int nbCharDeLigne;
    private char lastRead;

    public ReaderCompteur(Reader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int ret = reader.read(cbuf, off, len);
        for (int i = off; i < off + len; i++) {
            char c = cbuf[i];
            if (c == '\n') {
                nbLigne++;
                nbCharDeLigne = 0;
            } else {
                nbCharDeLigne++;
            }
        }
        lastRead = cbuf[off + len - 1];
        return ret;
    }

    @Override
    public int read() throws IOException {
        int ret = reader.read();
        if (ret != -1) {
            if (ret == '\n') {
                nbLigne++;
                nbCharDeLigne = 0;
            } else {
                nbCharDeLigne++;
            }
            lastRead = (char) ret;
        }
        return ret;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
    
    public int readNextNoWhite() throws IOException{
 
        int c = read();
        boolean continu  = true;
        while (continu) {
            if( c == '\n' || c =='\r' || c == ' ' || c == '\t'){
                c = read();
            }
            else{
                continu = false;
            }
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        String s = "      *dvsdvsv\ndsvsdvsd";
        StringReader sr = new StringReader(s);
        ReaderCompteur rc = new ReaderCompteur(sr);
        int c;
        char[] buff = new char[10];
//        while ((c = rc.read()) != -1) {
//            System.out.println(rc.nbCharDeLigne + " " + rc.nbLigne);
//        }
//        System.out.println((char)rc.readNextNoWhite());
//        System.out.println(rc.getNbCharDeLigne());
        rc.read(buff, 0, 10);
        System.out.println(rc.lastRead);
    }

    public int getNbLigne() {
        return nbLigne;
    }

    public int getNbCharDeLigne() {
        return nbCharDeLigne;
    }

    public char getLastRead() {
        return lastRead;
    }
    
    

}
