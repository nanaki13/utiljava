/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class Base64 {
    private static byte mask64 = ((2 << 5) - 1 );
    private static byte dec64 = 6;
    private char[] char64;
    private Map<Character, java.lang.Byte> charToValue;
//    private char[] base; 

    static {

    }
    private char pad;

    public Base64() {
        init();

    }

    public static char[] getCharsBase64() {
        Base64 base64 = new Base64();
        return base64.char64;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

//        int value = Byte.getValue(new byte[]{(byte)0xff,(byte)0xff,(byte)0xff});
//        System.out.println(value);
        Base64 b = new Base64();
        byte[] bs;
//        bs = new byte[]{(byte)0xff,(byte)0x00,(byte)0xff,(byte)0x00,(byte)0x02};
//        bs = "bonjour".getBytes();
//        System.out.println(Arrays.toString(bs));
//        String encode64 = b.encode(bs);
//        System.out.println(encode64);
        byte[] decode64 = b.decode("Q29kYWdl".getBytes("utf-8"), "utf-8");
        System.out.println(Byte.toString(decode64));
//        System.out.println(new String(decode64));
    }


    public String encode(byte[] bs) {
        int del = (bs.length % 3 == 0) ? 0 : 4;

        int outSize = (bs.length / 3) * 4 + del;
        byte[] charPos = new byte[outSize];
        byte[] posFin = null;
        for (int i = 0, j = 0; i < bs.length; i = i + 3, j = j + 4) {
            int value;
            if (i + 2 < bs.length) {
                value = Byte.getValue(bs, i + 2, i);
            } else if (i + 1 < bs.length) {
                value = Byte.getValue(bs, i + 1, i);
                posFin = new byte[1];
                posFin[0] = 64;
            } else {
                value = Byte.getValue(bs, i, i);
                posFin = new byte[2];
                posFin[0] = 64;
                posFin[1] = 64;
            }
            byte[] value64;
            if (posFin == null) {
                value64 = getValue64(value, 4);
            } else {
                if (posFin.length == 1) {
                    value64 = getValue64(value, 3);
                    value64 = new byte[]{value64[0], value64[1], value64[2], posFin[0]};
                } else {
                    value64 = getValue64(value, 2);
                    value64 = new byte[]{value64[0], value64[1], posFin[1], posFin[0]};
                }
            }
            System.arraycopy(value64, 0, charPos, j, value64.length);
        }
        StringBuilder bl = new StringBuilder();
        for (int i = 0; i < charPos.length; i++) {
            byte charPo = charPos[i];
            bl.append(char64[charPo]);

        }

        return bl.toString();

    }

    public byte[] encode(byte[] bs, String encoding) throws UnsupportedEncodingException {

        return Base64.this.encode(bs).getBytes(encoding);

    }

    public byte[] decode(byte[] bs, String encoding) throws UnsupportedEncodingException {

        String s = new String(bs, encoding);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < s.length(); i = i + 4) {
            int somme = 0;
            int dec = 0;
            byte deltaEnd = 0;
            for (int j = 3; j > -1; j--) {
                java.lang.Byte value = charToValue.get(s.charAt(i + j));
                if (value == 64) {
                    deltaEnd--;
                } else {
                    somme = (value << dec) | somme;
                    dec += 6;
                }
            }
            for (int j = 16 + (deltaEnd << 3); j > -1; j = j - 8) {
//                System.out.println("(byte)(somme>>>j)&0xff = "+((byte)(somme>>>j)&0xff));
                baos.write((byte) (somme >>> j) & 0xff);
            }
        }
        return baos.toByteArray();

    }

    private void init() {
        char64 = new char[65];
        pad = '=';
        charToValue = new HashMap<>();
        for (int i = 'a'; i < 'a' + 26; i++) {
            char64[i - 'a' + 26] = (char) i;
        }
        for (int i = 'A'; i < 'A' + 26; i++) {
            char64[i - 'A' ] = (char) i;
        }
        for (int i = '0'; i < '0' + 10; i++) {
            char64[i - '0'+52] = (char) i;
        }
        char64[62] = '+';
        char64[63] = '/';
        char64[64] = '=';
        for (byte i = 0; i < 65; i++) {
            charToValue.put(char64[i], i);
        }
    }
    static byte[] getValue64(int value,int size){
       byte[] ret = new byte[size];
       for(int i = 0 ; i < size ; i++){
           byte b = (byte) (value & mask64);
           ret[size -1 -i] = b;
           value>>>=dec64;   
       }
       return ret;
   }
}
