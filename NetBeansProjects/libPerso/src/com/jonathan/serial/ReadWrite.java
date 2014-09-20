/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.serial;

import com.jonathan.compress.Compressor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author jonathan
 */
public class ReadWrite {

    public static <T> List<T> readFile(Class<T> readedClass, String file) throws IOException, ClassNotFoundException {
        List<T> ret = new LinkedList<>();
        readFile(readedClass, ret, file);
        return ret;
    }
    
     public static <T> void readFile(Class<T> readedClass,Collection<T> out, String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            while (true) {
                out.add(readedClass.cast(ois.readObject()));
            }
        } catch (EOFException e) {
            ois.close();
            bis.close();
            fis.close();
        }

    }
    
    public static void writeToFile(Collection<?> list, String file) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        list.forEach((e) -> {
            try{
                 oos.writeObject(e);
            }catch (IOException ex){
                ex.printStackTrace();
            }
            
        });
        bos.flush();
        bos.close();
        

    }
    
    public static <T> List<T> readFileGZ(Class<T> readedClass, String file) throws IOException, ClassNotFoundException {
        List<T> ret = new LinkedList<>();
        readFileGZ(readedClass, ret, file);
        return ret;
    }
    
     public static <T> void readFileGZ(Class<T> readedClass,Collection<T> out, String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(bis);    
        ObjectInputStream ois = new ObjectInputStream(gZIPInputStream);
        try {
            while (true) {
                out.add(readedClass.cast(ois.readObject()));
            }
        } catch (EOFException e) {
            ois.close();
            bis.close();
            fis.close();
        }

    }
    public static void writeToFileGZ(Collection<?> list, String file) throws IOException, ClassNotFoundException {
//        FileOutputStream fos = new FileOutputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        list.forEach((e) -> {
            try{
                 oos.writeObject(e);
            }catch (IOException ex){
                ex.printStackTrace();
            }
            
        });
        bos.flush();
        bos.close();
        Compressor.compressGZ(baos.toByteArray(), file);
        

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        List<String> l = new ArrayList<>();
        for(int i = 0 ; i < 10000;i++){
            l.add("qsgrsrggdg"+i+System.currentTimeMillis());
        }
        writeToFileGZ(l, "String.gz");
//        l = readFileGZ(String.class, "String.gz");
//        l.forEach((e)-> System.out.println(e));
//        l = new ArrayList<>();
//        for(int i = 0 ; i < 10000;i++){
//            l.add("qsgrsrggdg"+i+System.currentTimeMillis());
//        }
        writeToFile(l, "String.ser");
        l = readFile(String.class, "String.ser");
//        l.forEach((e)-> System.out.println(e));
    }
}
