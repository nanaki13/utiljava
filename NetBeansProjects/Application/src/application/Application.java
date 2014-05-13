/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import com.jonathan.json.JsonObjectInterface;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import storage.DataJson;
import storage.ObjectReaderException;


/**
 *
 * @author jonathan
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws  ObjectReaderException, IOException {
        Config config = new Config();
        config.setFirstTime(true);
        config.setDataPath("/home/jonathan/trzst");
        DataJson.objectToFile(config, "config.json");
        
        config = DataJson.FileToObject(Config.class, "config.json" );
        
        
    }
    
}
