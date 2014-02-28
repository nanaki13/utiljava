/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json.validator;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author jonathan
 */
public class StringTool {
    
    public static void main(String[] args) throws IOException{

    
    }
    public static final char RET = '\n';
    public static final char SPACE = ' ';
    public static final char RETF = '\r';
    public static final char TAB = '\t';
    public static final char a = 'a';
    public static final char z = 'z';
    public static final char A = 'A';
    public static final char Z = 'Z';
    public static final char ZERO = '0';
    public static final char NEUF = '9';
    public static final char POINT = '.';
    public static int cptLine = 0;
    public static char  lastChar=0;
    
    static int nextNonWhite(final String in, int start){
        char c= in.charAt(start);
        while (true) {            
            if(c==RET || c==SPACE|| c== RETF || c== TAB){
                start++;
                c= in.charAt(start);
                cptLine++;
            }
            else{
                return start;
            }
        }
    }
    static void nextNonWhite(final InputStreamReader in) throws IOException{

        char c  = lastChar;
        while ((c =  (char)in.read())!=-1) {
            if(c==RET || c==SPACE|| c== RETF || c== TAB){
                if(c==RET)
                    cptLine++;
              //  in.
            }
            else return;
        }
    }
    static String nextNombre(final InputStreamReader in) throws IOException{

        char c ;
        StringBuilder b = new StringBuilder();
        boolean unChiffre = false;
        boolean point = false;
        while ((c =  (char)in.read())!=-1) {
            if(c>=ZERO && c<=NEUF){
                b.append(c);
                
                if(!unChiffre)
                    unChiffre = true;
            }
            else if(c==POINT && !point && unChiffre){
                b.append(c);
                point = true;
            }
            else return b.toString();
        }
        return null;
    }
    static int nextNonAlpha(final String in, int start){
        char c= in.charAt(start);
        while (true) {            
            if((c>=a && c<=z) || (c>= A && c<= Z)){
                start++;
            //    System.out.println(c);
                c= in.charAt(start);
            }
            else{
                return start;
            }
        }
    }
    public static String nextAlpha(final InputStreamReader  in) throws IOException{
     //   InputStreamReader inputStreamReader = new InputStreamReader(in);
            StringBuilder out;
            out=new StringBuilder();
            char c;
            while ((c = (char) in.read())!=-1) {            
                if((c>=a && c<=z) || (c>= A && c<= Z)){
                   out.append(c);

                }    
                else
                    return out.toString();
            }   
            return null;
        
    }   
}
