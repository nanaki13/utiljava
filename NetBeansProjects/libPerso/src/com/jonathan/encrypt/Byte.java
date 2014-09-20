/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author jonathan
 */
public class Byte {
    /**
     * sum the byte from to, each byte is dec
     * @param bs
     * @param from
     * @param to
     * @return 
     */
    static int getValue(byte[] bs, int from, int to) {
        int inc = (to > from)?1:-1;
        int somme=0;
        int mask = 0xff;
        for(int i = from,j=0 ; i!=to + inc & i<bs.length & i>=0; i+=inc,j+=8){
            somme = (((bs[i]&0xff)<<j)&mask)|somme;
            mask<<=8;
        }
        return somme;
    }
     static int getValue(byte[] bs) {
       return getValue(bs, 0, bs.length);
    }
//   private static 
   private int theByte;
   private static byte maskUnit = 1;
  
   public Byte(byte b){
       setTheByte(b);

   }

    private Byte() {
    }

    public final void setTheByte(byte b) {
        theByte = b;

   
    }
   
   
   
   
   public String toString(){
       char[] c = new char[8];
       for(byte i = 0 ; i < 8 ; i++){
           
//           System.out.println(theByte >> i);
           int b = theByte >>> i;
           b = b & maskUnit;
           if(b == 0){
               c[7 - i] = '0';
           }else{
                c[7 - i] = '1';
           }
       }
       return new String(c);
   }
   public static void main(String[] args) throws IOException{
//      Byte ub = new Byte((byte)2);
//       System.out.println(ub);
//      
//       ub = new Byte((byte)128);
//       System.out.println(ub);
//        ub = new Byte((byte)-127);
//       System.out.println(ub.getValue());
////       System.out.println(ub.getValue());
////       System.out.println((byte)-1);
//       byte[] tb = new byte[]{-127,-127,-127};
//       System.out.println(getValue(tb));
//       
//       System.out.println(2<<23);
//       System.out.println(64*64*64*64);
       
//       String b = "bonjour";
//       ByteArrayOutputStream baos = new ByteArrayOutputStream();
//       ByteArrayInputStream bais = new ByteArrayInputStream(b.getBytes());
//       System.out.println("len "+b.getBytes().length);
//       read64(bais, baos);
//       byte[] toByteArray = baos.toByteArray();
//       bais = new ByteArrayInputStream(toByteArray);
//       baos = new ByteArrayOutputStream();
//       decode64(bais, baos);
//       String s = new String(baos.toByteArray(), "utf-8");
//       System.out.println("\n"+s);
       System.out.println(0xff);
       byte b = -2;
       System.out.println((new Byte(b)));
       System.out.println((byte)0xff);
       int i = b&0xff;
       System.out.println(i);
       
       
       
        
   }
   public static String toString(byte[] bs){
       StringBuilder sb = new StringBuilder();
       Byte b = new Byte();
       for(int i = 0 ; i < bs.length ; i++){
           b.setTheByte(bs[i]);
           if(i!=0){
               sb.append('.');
           }
           sb.append(b);
       }
       return sb.toString();
   }
   

   
   
   
   
}
