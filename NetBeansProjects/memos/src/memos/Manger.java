/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memos;

import com.jonathan.serial.ReadWrite;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Manger {

    static String path = "./memos.gz";

    public static List<MemoDao> getMemos() throws ManagerException {
        try {
            return ReadWrite.readFileGZ(MemoDao.class, path);
        } catch (IOException | ClassNotFoundException ex) {
            throw new ManagerException(ex);
        }
    }

    public static void saveMemos(List<MemoDao> memos) throws ManagerException {
        try {
       ReadWrite.writeToFileGZ(memos, path);
       } catch (IOException | ClassNotFoundException ex) {
            throw new ManagerException(ex);
        }
    }
}
