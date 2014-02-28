/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import com.jonathan.json.FeuilleJson;
import com.jonathan.json.JsonObjectInterface;

/**
 *
 * @author jonathan
 */
public class TextJson implements JsonObjectInterface,FeuilleJson<String>{
        private String data;

    public TextJson(String data) {
        this.data = data;
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
            return '"'+data+'"';
        else return null;
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
}