/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author jonathan
 */
class RepertoireFilter implements FileFilter {

    public RepertoireFilter() {
    }

    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory()){
            return true;
        }
        else return false;
    }
    
}
