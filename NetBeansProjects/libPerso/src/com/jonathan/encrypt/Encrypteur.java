/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.encrypt;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class Encrypteur {
    public  char[] base = {'0','1'};
    public static final char min = Character.MAX_VALUE;
    public static final char max = Character.MIN_VALUE;
    public static final int delta = max - min;
    private final Map<Character, Integer> decryptMap;
    
    public Encrypteur(){
        decryptMap = new HashMap<Character, Integer>();
        for(int i = 0 ; i < base.length ; i++){
            decryptMap.put(base[i], i);
        }
        
    }
    public static String encript(String toEncript, String key){
        StringBuilder b = new StringBuilder();
        for(int i = 0 ; i < toEncript.length() ; i++){
            int x = toEncript.charAt(i) + delta;
            int j = (x + key.charAt(i % key.length())) % delta;
            j = j - delta;
            b.append((char) j);
        }
        return b.toString();
    }
    
    public static String decript(String toDeEncript, String key){
         StringBuilder b = new StringBuilder();
        for(int i = 0 ; i < toDeEncript.length() ; i++){
            int x = toDeEncript.charAt(i) + delta;
            int j = (x - key.charAt(i % key.length())) % delta;
            j = j - delta;
            b.append((char) j);
        }
        return b.toString();
    }
    
    public static void main (String[] args ){
//        String key = "ab ";
//        String encript = encript("salut comment ça va", key);
//        System.out.println(encript);
//        String decript = decript(encript, key);
//        System.out.println(decript);
        Encrypteur e = new Encrypteur();
        System.out.println(e.encodeBase("abcde"));
        System.out.println(e.decodeBase(e.encodeBase("salut")));
    }
    
    public  String byteString (int x){
        int d , r;
        StringBuilder br = new StringBuilder();
        r = x % base.length;
        d = x / base.length;
        br.append(base[r]);
        while( d != 0){
            r = d % base.length;
            d = d / base.length;
            br.append(base[r]);
        }
        return br.reverse().toString();
    }
    public  String byteString (byte x){
        int d , r;
        StringBuilder br = new StringBuilder();
        r = x % base.length;
        d = x / base.length;
        br.append(base[r]);
        while( d != 0){
            r = d % base.length;
            d = d / base.length;
            br.append(base[r]);
        }
        return br.reverse().toString();
    }
    public  String encodeBase(String x){
        StringBuilder br = new StringBuilder();
        for(int i = 0 ; i < x.length() ; i++){
            br.append(byteString(x.charAt(i))+".");
        }
        return br.toString();
    }
    public  String decodeBase(String x){
        StringBuilder br = new StringBuilder();
        int fac = 1;
        int somme = 0;
        for(int i = x.length()-1 ; i >= 0 ; i--){
            char c = x.charAt(i);
            if(c == '.'){
                br.append((char)somme);
                fac = 1;
                somme = 0;
            }
                
            else{
                somme += decodeBase(c) * fac ;
                fac = fac * base.length;
            }
        }
        br.append((char)somme);
                fac = 1;
                somme = 0;
        return br.reverse().toString();
    }
    public  int decodeBase(char x){
        return decryptMap.get(x);
        
    }
    public  String encodeBase(char[] x){
        StringBuilder br = new StringBuilder();
        for(int i = 0 ; i < x.length ; i++){
            br.append(byteString(x[i]));
        }
        return br.reverse().toString();
    }
}
