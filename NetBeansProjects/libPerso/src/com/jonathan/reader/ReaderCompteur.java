/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.reader;

import com.jonathan.interpretor.Command;
import com.jonathan.interpretor.ReaderCommand;
import com.jonathan.lib.string.StringTool;
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

    public int readNextNoWhite() throws IOException {

        int c = read();
        boolean continu = true;
        while (continu) {
            if (c == '\n' || c == '\r' || c == ' ' || c == '\t') {
                c = read();
            } else {
                continu = false;
            }
        }
        return c;
    }

    public String readLine() throws IOException {

        int c = read();
        StringBuilder bl = new StringBuilder();
        boolean continu = true;
        while (continu) {
            if (c != '\n' && c != '\r') {
                c = read();
                if (c == -1) {
                    return null;
                }
                bl.append((char) c);
            } else {
                continu = false;
            }
        }
        return bl.toString();
    }

    public String readNextWord() throws IOException {
        int read = read();
        StringBuilder b = new StringBuilder();
        while (isWhite(read) && read != -1) {
            read = read();
        }
        if (read != '\"') {
            while (!isWhite(read) && read != -1) {
                b.append((char) read);
                read = read();
            }
        }
        else{
            return StringTool.replaceEscapeCharByTrueChar(this);
        }

        if (b.length() == 0 && read == -1) {
            return null;
        }
        return b.toString();
    }

    public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader("  sdfsfa -vsvsv \"aze\\\"r  a\"\n");
        ReaderCommand rc = new ReaderCommand(sr);
        Command read = rc.read();
        System.out.println(read);
        
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

    public static boolean isWhite(int c) {
        return (c == '\n' || c == '\r' || c == ' ' || c == '\t');
    }

}
