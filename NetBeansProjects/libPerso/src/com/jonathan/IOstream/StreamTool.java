/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.IOstream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;

/**
 *
 * @author jonathan
 */
public class StreamTool {

    public static int readUntil(OutputStream out, InputStream in, byte... end) throws IOException {
        int nbGood = 0;
        int read = in.read();
        byte[] buf = new byte[end.length];

        boolean continu = true;
        int count = 0;
        while (continu) {
            if (read == end[nbGood]) {
                nbGood++;
                if (nbGood == end.length) {
                    continu = false;
                } else {
                    buf[nbGood - 1] = (byte) read;
                }
            } else {
                if (nbGood == 0) {
                    out.write(read);
                } else {
                    out.write(buf, 0, nbGood);
                    nbGood = 0;
                }

            }
            if (continu) {
                read = in.read();
            }
            count++;
        }
        return count;
    }
    public static void main(String [] args) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] b= new byte[]{1,2,4,5,3,4,5}; 
        byte[] b= "salut tout\n\r le mon \n\r".getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        
        readUntil(baos, bais, new byte[]{'\n','\r'});
        System.out.println(new String( baos.toByteArray()));
    }

    public static void readUntil(InputStreamReader in, StringBuilder out, char... fin) throws IOException {
        int c;
        int countOK = 0;
        boolean changeCount = false;
        c = in.read();
        boolean notFind = true;
        StringBuilder buff = new StringBuilder();
        while (notFind && c != -1) {
            if (c == fin[countOK]) {
                countOK++;
                changeCount = true;
                buff.append((char) c);
            } else {
                out.append((char) c);
            }
            if (!changeCount) {
                if (countOK != 0) {
                    countOK--;
                }
                out.append(buff);
                buff.setLength(0);
            }
            if (countOK == fin.length) {
                //                 out.append(buff);
                return;
            }
            changeCount = false;
            c = in.read();
        }
    }
}
