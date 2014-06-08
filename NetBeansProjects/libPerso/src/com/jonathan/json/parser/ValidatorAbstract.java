/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.parser;

import com.jonathan.json.JsonObjectInterface;
import com.jonathan.reader.ReaderCompteur;
import java.io.IOException;

/**
 *
 * @author jonathan
 */
abstract class ValidatorAbstract {
    
//    private boolean isCheck = false;

   
//    protected int indiceFin;
    protected String error;
    protected boolean valid;
//    protected final String input;
//    protected int indiceDebut;
    
    protected static final char ZERO = '0';
    protected static final char NEUF = '9';
    protected static final char POINT = '.';
    protected static final char a = 'a';
    protected static final char z = 'z';
    protected static final char A = 'A';
    protected static final char Z = 'Z';
    
    protected StringBuilder builder;
    protected JsonObjectInterface joi;
    protected ReaderCompteur rc;
    protected boolean root = false;

    
     public ValidatorAbstract(ReaderCompteur rc) {
        this.rc = rc;
//        this.input = null;
        builder = new StringBuilder();
     //   System.out.println(indiceDebut);
    }

//    public void setIndiceDebut(int indiceDebut) {
//        this.indiceDebut = indiceDebut;
//    }
    
    
    public int getNbLigne(){
         return rc.getNbLigne();
    }

    public int getNbCharDeLigne(){
        return rc.getNbCharDeLigne();
    }
   public char getLastRead(){
        return rc.getLastRead();
    }
   public String getError(){
        return error;
    }
//   public String getInput(){
//        return input;
//    }
//    @Override
//    public boolean isValid() throws ValidatorException{
//        if(!isCheck) valid = processValidation();
//        isCheck = true;
//        return valid;
//    }
    
    abstract public boolean processValidation()throws IOException ;
    
    public void readIfNotRoot() throws IOException{
        if(!root)
            read();
    }
    
    public char read() throws IOException{
        int c = rc.read();
        if(c == -1){
            throw new IOException("fin du flux");
        }
        else
            return (char) c;
    }
    
    public char readNextNoWhite() throws IOException{
        int c = rc.readNextNoWhite();
        if(c == -1){
            throw new IOException("fin du flux");
        }
        else
            return (char) c;
    }
    
    public JsonObjectInterface getJson(){
        return joi;
    }
    
    public void setRoot(boolean root){
        this.root = root;
    }
    
}
