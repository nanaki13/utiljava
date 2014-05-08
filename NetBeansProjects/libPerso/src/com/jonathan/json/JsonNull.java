/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json;

/**
 *
 * @author jonathan
 */
public class JsonNull implements JsonObjectInterface{
    public static final JsonNull NULL;

    static {
        NULL = new JsonNull();
    }
    public static JsonObjectInterface getNull() {
        return NULL;
    }


    private JsonNull() {
    }
    @Override
    public TypeJson getType() {
        return TypeJson.NULL;
    }

    @Override
    public String toStringJson() {
        return "null";
    }

    @Override
    public int toStringJsonPretty(StringBuilder out, int indent) {
        out.append("null");
        return indent;
    }
    
}
