/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

/**
 *
 * @author jonathan
 */
public interface JsonObjectInterface {
    public static String INDENTSPACE="  ";
    public TypeJson  getType();
    public String toStringJson();
    public int toStringJsonPretty(StringBuilder out , int indent );
}
