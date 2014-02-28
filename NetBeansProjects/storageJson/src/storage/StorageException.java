/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

/**
 *
 * @author jonathan
 */
class StorageException extends Exception {

    public StorageException(String msg) {
        super(msg);
    }

    StorageException(java.lang.Exception ex) {
        super(ex);
    }
    
}
