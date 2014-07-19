/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import com.jonathan.lib.string.StringTool;
import java.io.IOException;

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
    public Appendable toStringJson(Appendable out) throws IOException {
        if(data!=null)
            out.append( encloser+StringTool.echapeInvisibleChar(data, encloser)+encloser);
        else out.append("null");
        return out;
    }
    @Override
    public String toString() {
        return data;
    }

    @Override
    public int toStringJsonPretty(Appendable b ,int indent) throws IOException {
        toStringJson(b);
        return indent;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setEncloser(char encloser) {
        this.encloser = encloser;
    }
    
}