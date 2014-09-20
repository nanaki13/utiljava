/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.compress;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author jonathan
 */
public class Compressor {

    public static byte[] compressGZ(byte[] toCommpress) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        GZIPOutputStream out = new GZIPOutputStream(bos);
        out.write(toCommpress);
        bos.flush();
        bos.close();
        out.close();
        return baos.toByteArray();

    }

    public static void compressGZ(byte[] toCommpress, String file) throws IOException {
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (FileOutputStream fos = new FileOutputStream(file); //        BufferedOutputStream bos = new BufferedOutputStream(fos);
                GZIPOutputStream out = new GZIPOutputStream(fos)) {
            out.write(toCommpress);
        }
    }

    public static byte[] decompressGZ(String file) throws IOException {
        ByteArrayOutputStream baos;
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (FileInputStream fos = new FileInputStream(file); //        BufferedOutputStream bos = new BufferedOutputStream(fos);
                GZIPInputStream out = new GZIPInputStream(fos)) {
            byte[] buff = new byte[1024];
            baos = new ByteArrayOutputStream();
            while (out.read(buff) != -1) {
                baos.write(buff);
            }
        }
        return baos.toByteArray();
    }
}
