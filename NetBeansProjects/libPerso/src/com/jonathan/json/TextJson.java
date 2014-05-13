/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import com.jonathan.lib.string.StringTool;

/**
 *
 * @author jonathan
 */
public class TextJson implements JsonObjectInterface,FeuilleJson<String>{
        private String data;
        private char encloser = '"';

    public TextJson(String data) {
        this.data = data;
    }
    public TextJson(String data, char encloser) {
        this.data = data;
        this.encloser = encloser;
    }

    public TextJson() {
        
    }
        
    @Override
    public String getValue() {
        return data;
    }

    @Override
    public TypeJson getType() {
        return TypeJson.TEXT;
    }

    @Override
    public String toStringJson() {
        if(data!=null)
            return encloser+StringTool.echapeInvisibleChar(data, encloser)+encloser;
        else return "null";
    }
    @Override
    public String toString() {
        return data;
    }

    @Override
    public int toStringJsonPretty(StringBuilder b ,int indent) {
        b.append(toStringJson());
        return indent;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setEncloser(char encloser) {
        this.encloser = encloser;
    }
    
}