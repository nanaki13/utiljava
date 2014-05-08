/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import com.jonathan.json.JsonObjectInterface;

/**
 *
 * @author jonathan
 */
abstract class ValidatorAbstract implements Validator{
    
    private boolean isCheck = false;
    protected char charFin;
    protected int indiceFin;
    protected String error;
    protected boolean valid;
    protected final String input;
    protected int indiceDebut;
    
    protected static final char ZERO = '0';
    protected static final char NEUF = '9';
    protected static final char POINT = '.';
    protected static final char a = 'a';
    protected static final char z = 'z';
    protected static final char A = 'A';
    protected static final char Z = 'Z';
    
    protected StringBuilder builder;
    protected JsonObjectInterface joi;

    public ValidatorAbstract(String input, int indiceDebut) {
        this.input = input;
        this.indiceDebut = indiceDebut;
        builder = new StringBuilder();
     //   System.out.println(indiceDebut);
    }

    public void setIndiceDebut(int indiceDebut) {
        this.indiceDebut = indiceDebut;
    }
    
    
    public int getIndiceFin(){
        return indiceFin;
    }

   public char getCharFin(){
        return charFin;
    }
   public String getError(){
        return error;
    }
   public String getInput(){
        return input;
    }
    @Override
    public boolean isValid() throws ValidatorException{
        if(!isCheck) valid = processValidation();
        isCheck = true;
        return valid;
    }
    
    abstract public boolean processValidation() ;
    
    
    
}
