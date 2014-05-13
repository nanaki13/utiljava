/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.string;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class StringTool {

    public static final int LEFT = 0;
    public static final int MIDDLE = 1;
    public static final int RIGHT = 2;
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

   

    public static String raccourci(String in, int size) {
        String ret;
        String troisPt = "...";
        if (in == null || in.length() < 5 || size >= in.length() || size < 6) {
            ret = in;
        } else {
            int demiLength;
            int delta = 0;
            if (size % 2 == 0) {
                demiLength = size / 2;
                delta = 2;
            } else {
                demiLength = size / 2;
                delta = 1;
            }
            String deb = in.substring(0, demiLength - 1);
            String fin = in.substring(in.length() - demiLength + delta, in.length());
            ret = deb + troisPt + fin;
        }
        return ret;
    }

    public static String raccourci(String in, int size, int pos) {
        switch (pos) {
            case MIDDLE:
                return raccourci(in, size);
            case LEFT:
                String ret;
                String troisPt = "...";
                if (in == null || in.length() < 5 || size >= in.length() || size < 6) {
                    ret = in;
                } else {
                    String fin = in.substring(in.length() - size + 3);
                    ret = troisPt + fin;
                }
                return ret;
            default:
                return in;
        }

    }

    public static String take(String in, char first, char second) {
        String ret;
        int indexFirst = in.lastIndexOf(first);
        int indexSecond = in.lastIndexOf(second);
        if (indexFirst == -1) {
            indexFirst = 0;
        }
        if (indexSecond == -1) {
            indexSecond = in.length() - 1;
        }
        ret = in.substring(indexFirst + 1, indexSecond);
        return ret;
    }

    public static String addcslashes(String str, char ... charlist ) {
        if (str == null) {
            throw new NullPointerException("str is null");
        } else if (charlist == null) {
            throw new NullPointerException("charlist is null");
        }

        StringBuilder builder = new StringBuilder(str.length());
        int i = 0, j;
        while (i < str.length()) {
            boolean continu = true, find = false;
            j = 0;
            while (continu) {
                if (str.charAt(i) == charlist[j]) {
                    builder.append('\\').append(charlist[j]);
                    continu = false;
                    find = true;
                }
                j++;
                if (j == charlist.length) {
                    continu = false;
                }
            }
            if (!find) {
                builder.append(str.charAt(i));
            }
            i++;
        }
        return builder.toString();
    }
    /**
     * Remplace les charactères invisibles (\n, \t, ...) par leurs représentaions
     * @param str
     * @param start
     * @param stop
     * @return la chaine avec : sdjsdkf \n sdfsdfsd.
     */
    public static String echapeInvisibleChar(String str , int start, int stop) {
        if (str == null) {
            throw new NullPointerException("str is null");
        }

        StringBuilder builder = new StringBuilder(stop - start);
        int i = start;
        while (i != stop + 1) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\r':
                    builder.append("\\r");
                    break;
                case '\t':
                    builder.append("\\t");
                    break;
                case '\n':
                    builder.append("\\n");
                    break;
                case '\b':
                    builder.append("\\b");
                    break;
                case '\\':
                    builder.append("\\\\");
                    break;
                case '\f':
                    builder.append("\\f");
                    break;
                default:
                    builder.append(charAt);
            }
            i++;
        }
        return builder.toString();
    }
/**
     * Remplace les charactères invisibles (\n, \t, ...) par leurs représentaions
     * @param str
     * @param start
     * @param stop
     * @return la chaine avec : sdjsdkf \n sdfsdfsd.
     */
    public static String echapeInvisibleChar(String str , int start, int stop, char ... charList) {
       String ret = echapeInvisibleChar(str, start, stop);
       ret = addcslashes(ret, charList);
       return ret;
    }
    
     public static String echapeInvisibleChar(String str , char ... charList) {
       String ret = echapeInvisibleChar(str, 0, str.length() - 1);
       ret = addcslashes(ret, charList);
       return ret;
    }

     /**
     * Remplace les chaines \n, \t, ... par les vrais.
     * @param str
     * @param start
     * @param stop
     * @return la chaine avec les char remplacés
     */
    public static String replaceEscapeCharByTrueChar(String str, int start, int stop) {
        if (str == null) {
            throw new NullPointerException("str is null");
        }

        StringBuilder builder = new StringBuilder(stop - start);
        int i = start;
        int length = str.length();
        while (i != stop +1) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                i++;
                if (i == length) {
                    throw new IndexOutOfBoundsException("out of str");
                } else {
                    charAt = str.charAt(i);
                    switch (charAt) {
                        case 'r':
                            builder.append('\r');
                            break;
                        case 't':
                            builder.append('\t');
                            break;
                        case 'n':
                            builder.append('\n');
                            break;
                        case 'b':
                            builder.append('\b');
                            break;
                        case '\\':
                            builder.append('\\');
                            break;
                        case 'f':
                            builder.append('\f');
                            break;
                        default:
                            builder.append(charAt);
                    }
                }
            }
            else{
                builder.append(charAt);
            }
            i++;
        }
        return builder.toString();
    }
    
    public static int nextNonWhite(final String in, int start){
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
    public static void nextNonWhite(final InputStreamReader in) throws IOException{

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
    public static String nextNombre(final InputStreamReader in) throws IOException{

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
    public static int nextNonAlpha(final String in, int start){
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
    
    public static int getIndexEnd(String str, int start) throws StringException{
        char opener = str.charAt(start);
        char c = str.charAt(start + 1);
        int i = start + 1;
        while(c  != opener){
            if( c == '\\'){
                i++;
            }
            i++;
            if(i == str.length()){
                throw new StringException("sortie de la chaine sans fermeture \n index : "+i+"\n opener : "+opener,i,c);
            }
            c = str.charAt(i);
        }
        return i;
    }
    
     public static void main(String[] args) {
         String s = "      \"zbbb\\\"bq\\\"\\nsdqsce nbb\\cbz\"           ";
         int i = 0; 
         char c = s.charAt(i);
         while(c!= '"' ){
             i++;
             c = s.charAt(i);
         }
        
         
        try {
            int stop = getIndexEnd(s, i);
            System.out.println("i = "+i+"stop = "+stop);
            System.out.println("i = "+s.indexOf(a)+"stop = "+s.lastIndexOf(a));
            System.out.println( replaceEscapeCharByTrueChar(s, i+1, stop - 1));
            
        } catch (StringException ex) {
            Logger.getLogger(StringTool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
