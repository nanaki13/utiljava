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

/**
 *
 * @author jonathan
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
        Config config = new Config();
        config.setFirstTime(true);
        config.setDataPath("/home/jonathan/trzst");
        JsonObjectInterface objectToJsonObject = DataJson.objectToJsonObject(config);
        File f = new File("test.json");
        FileWriter fw = new FileWriter(f);
        fw.write(objectToJsonObject.toStringJson());
        fw.close();
    }
    
}
