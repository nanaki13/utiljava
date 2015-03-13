/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class DataStoring {

    static {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public static void set(String id,Object o) throws IOException, ClassNotFoundException {
        FileOutputStream fr =null;
        try {
            fr = new FileOutputStream("./data/" + id);
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fr);
            objectInputStream.writeObject(o);
        }finally{
            if(fr!=null)fr.close();
        }

    }
    public static <T> T get(String id) throws  ClassNotFoundException{
        FileInputStream fr =null;
        try {
            
            fr = new FileInputStream("./data/" + id);
            
            ObjectInputStream objectInputStream = new ObjectInputStream(fr);
           
            return (T) objectInputStream.readObject();
        } catch (IOException ex) {
            Logger.getLogger(DataStoring.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(fr!=null)try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(DataStoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        

    

}
