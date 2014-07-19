/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import java.io.IOException;

/**
 *
 * @author jonathan
 */
public interface JsonObjectInterface {
    public static String INDENTSPACE="  ";
    public TypeJson  getType();
    public Appendable toStringJson(Appendable out)throws IOException;
    public int toStringJsonPretty(Appendable out , int indent ) throws IOException;
}
