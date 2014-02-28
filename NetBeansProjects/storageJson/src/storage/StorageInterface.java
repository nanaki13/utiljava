/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.jonathan.json.JsonObject;

/**
 *
 * @author jonathan
 */
public interface StorageInterface {
    public void connect(String filePath)throws StorageException;
 //   public void close();
    public JsonObject get(int id);
    public void save(JsonObject jsonObject) throws StorageException;
}
