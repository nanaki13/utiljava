/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json;

import java.io.IOException;

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
    public Appendable toStringJson(Appendable out ) throws IOException {
        out.append("null");
        return out;
    }

    @Override
    public int toStringJsonPretty(Appendable out, int indent) throws IOException {
        out.append("null");
        return indent;
    }
    
}
