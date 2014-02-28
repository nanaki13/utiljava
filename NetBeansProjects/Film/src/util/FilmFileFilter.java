/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 *
 * @author jonathan
 */
public class FilmFileFilter implements FileFilter /* FilenameFilter*/ {

    @Override
    public boolean accept(File pathname) {
        String cheminAbsolue = pathname.getAbsolutePath();
        int posDerPoint = cheminAbsolue.lastIndexOf('.');
        String extension = cheminAbsolue.substring(posDerPoint + 1);
       // System.out.println(extension);
        if (extension.equalsIgnoreCase("avi")
                || extension.equalsIgnoreCase("mp4")
                || extension.equalsIgnoreCase("mkv")) {
            return true;
        }

        return false;
    }
//    @Override
//    public boolean accept(File dir, String name) {
//       System.out.println(dir);
//       System.out.println(name);
//        return false;
//    }
}
